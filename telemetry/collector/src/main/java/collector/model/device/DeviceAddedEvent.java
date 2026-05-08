package collector.model.device;

import collector.model.state.HubState;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import collector.model.state.DeviceType;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// Событие, сигнализирующее о добавлении нового устройства в систему
public class DeviceAddedEvent extends BaseDeviceEvent {
    @NotNull
    private String id;
    @NotNull
    private DeviceType deviceType;  // Типы событий датчиков


    @Override
    public String getType() {
        return HubState.DEVICE_ADDED.toString();
    }
}