package collector.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import collector.model.state.DeviceType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// Событие климатического датчика, содержащее информацию о температуре, влажности и уровне CO2
public class ClimateSensorEvent extends BaseEvent {
    @NotNull
    private Integer temperatureC;       // Уровень температуры по шкале Цельсия.
    @NotNull
    private Integer humidity;           // Влажность.
    @NotNull
    private Integer co2Level;           // Уровень CO2.

    @Override
    public String getType() {
        return DeviceType.CLIMATE_SENSOR.toString();
    }
}