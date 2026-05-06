package model.device;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import model.state.HubState;

@Getter
@Builder
// Представляет действие, которое должно быть выполнено устройством.
public class DeviceAction {
    @NotNull
    private String sensorId;       // Идентификатор датчика, связанного с действием
    @NotNull
    private HubState type;          // Типы событий от хаба
    private Integer value;          // Необязательное значение, связанное с действием
}