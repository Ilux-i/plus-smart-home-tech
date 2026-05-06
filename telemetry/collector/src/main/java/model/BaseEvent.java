package model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import model.state.DeviceType;

import java.time.Instant;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        defaultImpl = DeviceType.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MotionSensorEvent.class, name = "MOTION_SENSOR_EVENT"),
        @JsonSubTypes.Type(value = TemperatureSensorEvent.class, name = "TEMPERATURE_SENSOR_EVENT"),
        @JsonSubTypes.Type(value = LightSensorEvent.class, name = "LIGHT_SENSOR_EVENT"),
        @JsonSubTypes.Type(value = ClimateSensorEvent.class, name = "CLIMATE_SENSOR_EVENT"),
        @JsonSubTypes.Type(value = SwitchSensorEvent.class, name = "SWITCH_SENSOR_EVENT")
})
@Getter
@Builder
// Базовый класс для событий от датчиков
public abstract class BaseEvent {
    @NotNull
    private String id;                    // Идентификатор события датчика.
    @NotNull
    private String hubId;                 // Идентификатор хаба, связанного с событием.
    private Instant timestamp;            // Временная метка события. По умолчанию устанавливается текущее время.

    @NotNull
    public abstract DeviceType getType();
}