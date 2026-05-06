package model.device;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import model.BaseEvent;
import model.state.DeviceType;
import model.state.HubState;

@Getter
@Builder
// Событие, сигнализирующее о добавлении нового устройства в систему
public class DeviceAddedEvent extends BaseEvent {
    @NotNull
    private DeviceType deviceType;  // Типы событий датчиков
    @NotNull
    private HubState type;          // Типы событий от хаба
}