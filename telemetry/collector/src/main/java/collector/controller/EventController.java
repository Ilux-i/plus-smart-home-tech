package collector.controller;

import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.log4j.Log4j2;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.yandex.practicum.grpc.telemetry.collector.CollectorControllerGrpc;
import ru.yandex.practicum.grpc.telemetry.event.MotionSensorProto;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;
import ru.yandex.practicum.grpc.telemetry.event.TemperatureSensorProto;

@Log4j2
@GrpcService
public class EventController extends CollectorControllerGrpc.CollectorControllerImplBase {

    public void collectSensorEvent(SensorEventProto request,
                                   StreamObserver<Empty> responseObserver) {
        try {
            log.info("Получено событие от датчика: {}", request.getId());

            // Обработка в зависимости от типа
            switch (request.getPayloadCase()) {
                case TEMPERATURE_SENSOR:
                    handleTemperatureSensor(request.getTemperatureSensor());
                    break;
                case MOTION_SENSOR:
                    handleMotionSensor(request.getMotionSensor());
                    break;
                // ... остальные типы
                default:
                    log.warn("Неизвестный тип события: {}", request.getPayloadCase());
            }

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Ошибка обработки события", e);
            responseObserver.onError(
                    Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        }
    }

    private void handleTemperatureSensor(TemperatureSensorProto sensor) {
        log.info("Температура: {}°C ({}°F)",
                sensor.getTemperatureC(),
                sensor.getTemperatureF());
    }

    private void handleMotionSensor(MotionSensorProto sensor) {
        log.info("Датчик движения: {}",
                sensor.getMotion());
    }
}
