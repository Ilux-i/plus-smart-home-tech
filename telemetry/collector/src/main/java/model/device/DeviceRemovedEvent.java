package model.device;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import model.BaseEvent;
import model.state.HubState;

@Getter
@Builder
// Событие, сигнализирующее о удалении устройства из системы
public class DeviceRemovedEvent extends BaseEvent {
    @NotNull
    private HubState type;          // Типы событий от хаба
}