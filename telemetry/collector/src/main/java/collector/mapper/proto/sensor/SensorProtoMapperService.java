package collector.mapper.proto.sensor;

import collector.mapper.proto.sensor.mapper.SensorProtoMapper;
import collector.model.sensor.BaseSensorEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;
import com.google.protobuf.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class SensorProtoMapperService {
    private final SensorProtoMapperFactory mapperFactory;

    public SensorEventProto mapToProto(BaseSensorEvent event) {
        SensorEventProto.Builder builder = SensorEventProto.newBuilder();

        builder.setId(event.getId());
        builder.setHubId(event.getHubId());
        builder.setTimestamp(toProtoTimestamp(event.getTimestamp()));

        @SuppressWarnings("unchecked")
        SensorProtoMapper<BaseSensorEvent> mapper =
                (SensorProtoMapper<BaseSensorEvent>) mapperFactory.getMapper(event.getClass());

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