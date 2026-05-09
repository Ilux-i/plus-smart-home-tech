package collector.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import collector.model.state.DeviceType;

@Getter
@Builder
// Событие датчика освещенности, содержащее информацию о качестве связи и уровне освещенности
public class LightSensorEvent extends BaseEvent {
    @NotNull
    private Integer linkQuality;    // Качество связи
    @NotNull
    private Integer luminosity;     // Уровень освещенности

    @Override
    public String getType() {
        return DeviceType.LIGHT_SENSOR.toString();
    }
}
