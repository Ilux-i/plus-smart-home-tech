package collector.model.device;

import collector.model.state.HubState;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import collector.model.state.DeviceType;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.yandex.practicum.kafka.telemetry.event.DeviceTypeAvro;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// Событие, сигнализирующее о добавлении нового устройства в систему
public class DeviceAddedEvent extends BaseDeviceEvent {
    @NotNull
    private String id;
    @NotNull
    @JsonProperty("deviceType")
    private DeviceType type;  // Типы событий датчиков


    @Override
    public String getType() {
        return HubState.DEVICE_ADDED.toString();
    }

    public String getDeviceType() {
        return type.name();
    }
}