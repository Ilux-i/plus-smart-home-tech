package collector;

import collector.kafka.AvroMapper;
import collector.kafka.KafkaClient;
import collector.model.BaseEvent;
import collector.model.device.BaseDeviceEvent;
import jakarta.annotation.PostConstruct;
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

    // Старта прослушки топиков
    @PostConstruct
    public void init() {
        startConsumingSensors();
        startConsumingHubs();
    }

    // Работа с топиком датчиков

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
        switch (payload) {
            case LightSensorAvro light -> {
                System.out.println("  Type: LIGHT_SENSOR");
                System.out.println("  Luminosity: " + light.getLuminosity());
            }
            case MotionSensorAvro motion -> {
                System.out.println("  Type: MOTION_SENSOR");
                System.out.println("  Motion: " + motion.getMotion());
            }
            case ClimateSensorAvro climate -> {
                System.out.println("  Type: CLIMATE_SENSOR");
                System.out.println("  Temperature (C): " + climate.getTemperatureC());
                System.out.println("  Humidity: " + climate.getHumidity());
                System.out.println("  CO2 Level: " + climate.getCo2Level());
            }
            case SwitchSensorAvro switchSensor -> {
                System.out.println("  Type: SWITCH_SENSOR");
                System.out.println("  State: " + (switchSensor.getState() ? "ON" : "OFF"));
            }
            case TemperatureSensorAvro tempSensor -> {
                System.out.println("  Type: TEMPERATURE_SENSOR");
                System.out.println("  Sensor ID (internal): " + tempSensor.getId());
                System.out.println("  Temperature (C): " + tempSensor.getTemperatureC());
                System.out.println("  Temperature (F): " + tempSensor.getTemperatureF());
            }
            case null, default -> System.out.println("  Type: UNKNOWN or NULL payload");
        }
    }


    // Работа с топиком хаба

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

    public void startConsumingHubs() {
        new Thread(() -> {
            Consumer<String, SpecificRecordBase> consumer = kafkaClient.getConsumer("hub-consumer-group"); // Получаем producer
            consumer.subscribe(Collections.singletonList(HUB_TOPIC)); // Подписываемся на топик

            try {
                while (!Thread.currentThread().isInterrupted()) {
                    ConsumerRecords<String, SpecificRecordBase> records = consumer.poll(Duration.ofMillis(100));

                    for (var record : records) {
                        SpecificRecordBase value = record.value();

                        if (value instanceof HubEventAvro) {
                            HubEventAvro event = (HubEventAvro) value;
                            processHubEvent(event); // Смотрим сообщение
                        }
                    }
                }
            } finally {
                consumer.close();
            }
        }).start();
    }

    private void processHubEvent(HubEventAvro event) {
        System.out.println("Received Hub Event:");
        System.out.println("  Hub ID: " + event.getHubId());
        System.out.println("  Timestamp: " + event.getTimestamp());

        Object payload = event.getPayload();

        switch (payload) {
            case DeviceAddedEventAvro added -> {
                System.out.println("  Type: DEVICE_ADDED");
                System.out.println("  Device ID: " + added.getId());
                System.out.println("  Device Type: " + added.getType());
            }
            case DeviceRemovedEventAvro removed -> {
                System.out.println("  Type: DEVICE_REMOVED");
                System.out.println("  Device ID: " + removed.getId());
            }
            case ScenarioAddedEventAvro scenarioAdded -> {
                System.out.println("  Type: SCENARIO_ADDED");
                System.out.println("  Scenario Name: " + scenarioAdded.getName());
                System.out.println("  Conditions Count: " + (scenarioAdded.getConditions() != null ? scenarioAdded.getConditions().size() : 0));
                System.out.println("  Actions Count: " + (scenarioAdded.getActions() != null ? scenarioAdded.getActions().size() : 0));
            }
            case ScenarioRemovedEventAvro scenarioRemoved -> {
                System.out.println("  Type: SCENARIO_REMOVED");
                System.out.println("  Scenario Name: " + scenarioRemoved.getName());
            }
            case null, default -> System.out.println("  Type: UNKNOWN or NULL payload");
        }
    }

}
