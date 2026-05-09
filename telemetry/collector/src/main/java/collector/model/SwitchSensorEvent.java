package collector.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import collector.model.state.DeviceType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// Событие датчика переключателя, содержащее информацию о текущем состоянии переключателя
public class SwitchSensorEvent extends BaseEvent {
    @NotNull
    private Boolean state;          // Текущее состояние переключателя

    @Override
    public String getType() {
        return DeviceType.SWITCH_SENSOR.toString();
    }
}
