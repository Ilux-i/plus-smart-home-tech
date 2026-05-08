package collector.model.device;

import collector.model.state.HubState;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// Событие, сигнализирующее о удалении устройства из системы
public class DeviceRemovedEvent extends BaseDeviceEvent {
    @NotNull
    private String id;

    @Override
    public String getType() {
        return HubState.DEVICE_REMOVED.toString();
    }
}