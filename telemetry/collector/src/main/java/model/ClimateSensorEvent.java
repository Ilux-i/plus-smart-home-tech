package model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import model.state.DeviceType;

@Getter
@Builder
// Событие климатического датчика, содержащее информацию о температуре, влажности и уровне CO2
public class ClimateSensorEvent extends BaseEvent {
    @NotNull
    private Integer temperatureC;       // Уровень температуры по шкале Цельсия.
    @NotNull
    private Integer humidity;           // Влажность.
    @NotNull
    private Integer co2Level;           // Уровень CO2.
    private DeviceType type;

    @Override
    public DeviceType getType() {
        return DeviceType.CLIMATE_SENSOR_EVENT;
    }
}