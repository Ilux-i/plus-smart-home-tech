package collector.mapper.proto.hub.mapper;

import collector.model.hub.BaseDeviceEvent;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;

public interface HubProtoMapper<T extends BaseDeviceEvent> {
    Class<T> getEventType();
    void mapPayload(T event, HubEventProto.Builder builder);
}
