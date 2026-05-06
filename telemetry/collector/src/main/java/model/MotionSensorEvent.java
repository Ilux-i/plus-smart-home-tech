package model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import model.state.DeviceType;

@Getter
@Builder
// Событие датчика движения
public class MotionSensorEvent extends BaseEvent {
    @NotNull
    private Integer linkQuality;    // Качество связи
    @NotNull
    private Boolean motion;         // Наличие/отсутствие движения
    @NotNull
    private Integer voltage;        // Напряжение
    private DeviceType type;

    @Override
    public DeviceType getType() {
        return DeviceType.MOTION_SENSOR_EVENT;
    }
}
