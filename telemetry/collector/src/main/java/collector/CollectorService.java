package collector;

import collector.kafka.AvroMapper;
import collector.kafka.KafkaClient;
import collector.model.BaseEvent;
import collector.model.device.BaseDeviceEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.kafka.telemetry.event.*;

import java.time.Duration;
import java.util.Collections;

@Log4j2
@Service
@RequiredArgsConstructor
public class CollectorService {

    private static final String SENSOR_TOPIC = "telemetry.sensors.v1";
    private static final String HUB_TOPIC = "telemetry.hubs.v1";

    private final KafkaClient kafkaClient;
    private final AvroMapper avroMapper;

    public void sendSensorEvent(BaseEvent event) {
        SpecificRecordBase avroRecord = avroMapper.mapSensorEventToAvro(event); // Маппинг в avro

        Producer<String, SpecificRecordBase> producer = kafkaClient.getProducer(); // Получение producer

        ProducerRecord<String, SpecificRecordBase> record = new ProducerRecord<>( // Формирование записи
                SENSOR_TOPIC,
                event.getHubId(),
                avroRecord);

        producer.send(record, (metadata, exception) -> { // Отправка записи в топик
            if (exception != null) {
                log.error("ERROR sending sensor event [ID: {}, Hub: {}]: {}",
                        event.getId(),
                        event.getHubId(),
                        exception.getMessage()
                );
            } else {
                log.debug("SUCCESS sent sensor event to partition {}, offset {}",
                        metadata.partition(),
                        metadata.offset()
                );
            }
        });
    }

    public void sendHubEvent(BaseDeviceEvent event) {
        SpecificRecordBase avroRecord = avroMapper.mapHubEventToAvro(event); // Маппинг в avro

        Producer<String, SpecificRecordBase> producer = kafkaClient.getProducer(); // Получение producer

        ProducerRecord<String, SpecificRecordBase> record = new ProducerRecord<>( // Формирование записи
                HUB_TOPIC,
                event.getHubId(),
                avroRecord);

        producer.send(record, (metadata, exception) -> { // Отправка записи в топик
            if (exception != null) {
                log.error("ERROR sending hub event [Hub: {}, Type: {}]: {}",
                        event.getHubId(),
                        event.getType(),
                        exception.getMessage());
            }
        });
    }

    public void startConsumingSensors() {
        new Thread(() -> { // Запускаем поток с прослушкой топика
            Consumer<String, SpecificRecordBase> consumer = kafkaClient.getConsumer("sensor-consumer-group");
            consumer.subscribe(Collections.singletonList(SENSOR_TOPIC)); // подписываемся нна топик

            try {
                while (!Thread.currentThread().isInterrupted()) {
                    ConsumerRecords<String, SpecificRecordBase> records = consumer.poll(Duration.ofMillis(100)); // прослушиваем топик

                    for (var record : records) {
                        SpecificRecordBase value = record.value();

                        if (value instanceof SensorEventAvro) {
                            SensorEventAvro event = (SensorEventAvro) value;
                            processSensorEvent(event); // смотрим сообщение
                        }
                    }
                }
            } finally {
                consumer.close();
            }
        }).start();
    }

    private void processSensorEvent(SensorEventAvro event) {
        // Вывод основных полей
        System.out.println("Received Sensor Event:");
        System.out.println("  ID: " + event.getId());
        System.out.println("  Hub: " + event.getHubId());
        System.out.println("  Timestamp: " + event.getTimestamp());

        // Вывод payload
        Object payload = event.getPayload();
        if (payload instanceof LightSensorAvro light) {
            System.out.println("  Type: LIGHT_SENSOR");
            System.out.println("  Luminosity: " + light.getLuminosity());
        } else if (payload instanceof MotionSensorAvro motion) {
            System.out.println("  Type: MOTION_SENSOR");
            System.out.println("  Motion: " + motion.getMotion());
        } else if (payload instanceof ClimateSensorAvro climate) {
            System.out.println("  Type: CLIMATE_SENSOR");
            System.out.println("  Temperature (C): " + climate.getTemperatureC());
            System.out.println("  Humidity: " + climate.getHumidity());
            System.out.println("  CO2 Level: " + climate.getCo2Level());
        } else if (payload instanceof SwitchSensorAvro switchSensor) {
            System.out.println("  Type: SWITCH_SENSOR");
            System.out.println("  State: " + (switchSensor.getState() ? "ON" : "OFF"));

        } else if (payload instanceof TemperatureSensorAvro tempSensor) {
            System.out.println("  Type: TEMPERATURE_SENSOR");
            System.out.println("  Sensor ID (internal): " + tempSensor.getId());
            System.out.println("  Temperature (C): " + tempSensor.getTemperatureC());
            System.out.println("  Temperature (F): " + tempSensor.getTemperatureF());

        } else {
            System.out.println("  Type: UNKNOWN or NULL payload");
        }
    }

}
