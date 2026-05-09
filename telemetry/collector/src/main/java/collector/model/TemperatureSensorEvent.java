package collector.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import collector.model.state.DeviceType;

@Getter
@Builder
// Событие датчика температуры, содержащее информацию о температуре в градусах Цельсия и Фаренгейта
public class TemperatureSensorEvent extends  BaseEvent {
    @NotNull
    private Integer temperatureC;   // Температура в градусах Цельсия
    @NotNull
    private Integer temperatureF;   // Температура в градусах Фаренгейта

    @Override
    public String getType() {
        return DeviceType.TEMPERATURE_SENSOR.toString();
    }
}