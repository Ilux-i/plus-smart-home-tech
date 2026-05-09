package collector.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import collector.model.state.DeviceType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// Событие датчика движения
public class MotionSensorEvent extends BaseEvent {
    @NotNull
    private Integer linkQuality;    // Качество связи
    @NotNull
    private Boolean motion;         // Наличие/отсутствие движения
    @NotNull
    private Integer voltage;        // Напряжение

    @Override
    public String getType() {
        return DeviceType.MOTION_SENSOR.toString();
    }
}
