package collector.mapper.proto.sensor.mapper;

import collector.model.sensor.BaseSensorEvent;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;

public interface SensorProtoMapper<T extends BaseSensorEvent> {
    Class<T> getEventType();
    void mapPayload(T event, SensorEventProto.Builder builder);
}