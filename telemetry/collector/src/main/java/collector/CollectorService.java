package collector;

import collector.kafka.AvroMapper;
import collector.kafka.KafkaClient;
import collector.model.BaseEvent;
import collector.model.device.BaseDeviceEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;


@Log4j2
@Service
@RequiredArgsConstructor
public class CollectorService {

    private static final String SENSOR_TOPIC = "telemetry.sensors.v1";
    private static final String HUB_TOPIC = "telemetry.hubs.v1";


    private final KafkaClient kafkaClient;
    private final AvroMapper avroMapper;

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

}
