package collector.mapper.proto.sensor.mapper;

import collector.model.sensor.SwitchSensorEvent;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;
import ru.yandex.practicum.grpc.telemetry.event.SwitchSensorProto;

@Component
public class SwitchSensorEventMapper implements SensorProtoMapper<SwitchSensorEvent> {
    @Override public Class<SwitchSensorEvent> getEventType() { return SwitchSensorEvent.class; }

    @Override
    public void mapPayload(SwitchSensorEvent event, SensorEventProto.Builder builder) {
        SwitchSensorProto payload = SwitchSensorProto.newBuilder()
                .setState(event.getState())
                .build();
        builder.setSwitchSensor(payload);
    }
}