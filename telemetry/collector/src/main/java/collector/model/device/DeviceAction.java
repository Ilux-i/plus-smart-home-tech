package collector.model.device;

import collector.model.state.ActionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
// Представляет действие, которое должно быть выполнено устройством.
public class DeviceAction {
    @NotNull
    @JsonProperty("sensor_id")
    private String sensorId;       // Идентификатор датчика, связанного с действием
    @NotNull
    private ActionType type;          // Тип действия при срабатывании условия активации сценария
    private Integer value;          // Необязательное значение, связанное с действием
}