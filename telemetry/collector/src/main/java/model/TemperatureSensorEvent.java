package model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import model.state.DeviceType;

@Getter
@Builder
// Событие датчика температуры, содержащее информацию о температуре в градусах Цельсия и Фаренгейта
public class TemperatureSensorEvent extends  BaseEvent {
    @NotNull
    private Integer temperatureC;   // Температура в градусах Цельсия
    @NotNull
    private Integer temperatureF;   // Температура в градусах Фаренгейта
    private DeviceType type;

    @Override
    public DeviceType getType() {
        return DeviceType.TEMPERATURE_SENSOR_EVENT;
    }
}