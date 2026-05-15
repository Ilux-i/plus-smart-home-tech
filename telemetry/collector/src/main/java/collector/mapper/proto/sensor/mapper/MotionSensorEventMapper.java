package collector.mapper.proto.sensor.mapper;

import collector.model.sensor.MotionSensorEvent;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.MotionSensorProto;
import ru.yandex.practicum.grpc.telemetry.event.SensorEventProto;

@Component
public class MotionSensorEventMapper implements SensorProtoMapper<MotionSensorEvent> {
    @Override
    public Class<MotionSensorEvent> getEventType() {
        return MotionSensorEvent.class;
    }

    @Override
    public void mapPayload(MotionSensorEvent event, SensorEventProto.Builder builder) {
        MotionSensorProto payload = MotionSensorProto.newBuilder()
                .setLinkQuality(event.getLinkQuality())
                .setMotion(event.getMotion())
                .setVoltage(event.getVoltage())
                .build();

        builder.setMotionSensor(payload);
    }
}