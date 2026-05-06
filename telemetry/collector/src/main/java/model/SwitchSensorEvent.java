package model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import model.state.DeviceType;

@Getter
@Builder
// Событие датчика переключателя, содержащее информацию о текущем состоянии переключателя
public class SwitchSensorEvent extends BaseEvent {
    @NotNull
    private Boolean state;          // Текущее состояние переключателя
    private DeviceType type;

    @Override
    public DeviceType getType() {
        return DeviceType.SWITCH_SENSOR_EVENT;
    }
}
