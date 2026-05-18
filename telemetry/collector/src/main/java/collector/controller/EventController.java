package collector.controller;

import com.google.protobuf.Empty;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.yandex.practicum.grpc.telemetry.collector.CollectorControllerGrpc;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;

import collector.mapper.proto.hub.HubProtoMapperService;
import collector.mapper.proto.sensor.SensorProtoMapperService;
import collector.model.hub.BaseDeviceEvent;
import collector.model.sensor.BaseSensorEvent;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class EventController extends CollectorControllerGrpc.CollectorControllerImplBase {

    private final SensorProtoMapperService sensorMapperService;
    private final HubProtoMapperService hubMapperService;

//    @Override
    public void collectSensorEvent(SensorEventProto request, StreamObserver<Empty> responseObserver) {
        try {
            BaseSensorEvent domainEvent = sensorMapperService.mapFromProto(request);

            log.info("Обработано событие датчика: {}", domainEvent.getClass().getSimpleName());

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Ошибка обработки события датчика", e);
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal error: " + e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        }
    }

//    @Override
    public void collectHubEvent(HubEventProto request, StreamObserver<Empty> responseObserver) {
        try {
            BaseDeviceEvent domainEvent = hubMapperService.mapFromProto(request);

            log.info("Обработано событие хаба: {}", domainEvent.getClass().getSimpleName());

            responseObserver.onNext(Empty.getDefaultInstance());
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("Ошибка обработки события хаба", e);
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal error: " + e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        }
    }
}