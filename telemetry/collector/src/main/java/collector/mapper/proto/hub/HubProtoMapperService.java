package collector.mapper.proto.hub;

import collector.mapper.proto.hub.mapper.HubProtoMapper;
import collector.model.hub.BaseDeviceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import com.google.protobuf.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class HubProtoMapperService {
    private final HubProtoMapperFactory mapperFactory;

    public HubEventProto mapToProto(BaseDeviceEvent event) {
        HubEventProto.Builder builder = HubEventProto.newBuilder();

        // Общие поля
        builder.setHubId(event.getHubId());
        builder.setTimestamp(toProtoTimestamp(event.getTimestamp()));

        // Делегирование специфичному мапперу
        @SuppressWarnings("unchecked")
        HubProtoMapper<BaseDeviceEvent> mapper =
                (HubProtoMapper<BaseDeviceEvent>) mapperFactory.getMapper(event.getClass());

        mapper.mapPayload(event, builder);
        return builder.build();
    }

    private Timestamp toProtoTimestamp(Instant instant) {
        Instant time = instant != null ? instant : Instant.now();
        return Timestamp.newBuilder()
                .setSeconds(time.getEpochSecond())
                .setNanos(time.getNano())
                .build();
    }
}