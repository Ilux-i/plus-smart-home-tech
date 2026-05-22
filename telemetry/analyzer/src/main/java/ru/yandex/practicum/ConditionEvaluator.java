package ru.yandex.practicum;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.*;
import ru.yandex.practicum.model.Condition;
import ru.yandex.practicum.model.ScenarioCondition;

import java.util.Map;

@Log4j2
@Component
public class ConditionEvaluator {

    public boolean evaluate(ScenarioCondition scenarioCondition,
                            Map<String, SensorStateAvro> sensorStates) {
        SensorStateAvro state = sensorStates.get(scenarioCondition.getSensor().getId());
        if (state == null) return false;

        Condition conditionEntity = scenarioCondition.getCondition();
        String operation = conditionEntity.getOperation();

        Object thresholdValue = scenarioCondition.getCondition().getValue();

        log.debug("Проверка условия: sensorId={}, type={}, operation={}, threshold={}",
                scenarioCondition.getSensor().getId(),
                conditionEntity.getType(),
                operation,
                thresholdValue);

        Object actualValue = extractValue(state, conditionEntity.getType());
        if (actualValue == null) return false;

        return compare(actualValue, operation, thresholdValue);
    }

    private Object extractValue(SensorStateAvro state, String conditionType) {
        Object data = state.getData();
        switch (conditionType) {
            case "MOTION":
                if (data instanceof MotionSensorAvro) {
                    return ((MotionSensorAvro) data).getMotion();
                }
                break;
            case "LUMINOSITY":
                if (data instanceof LightSensorAvro) {
                    return ((LightSensorAvro) data).getLuminosity();
                }
                break;
            case "SWITCH":
                if (data instanceof SwitchSensorAvro) {
                    return ((SwitchSensorAvro) data).getState();
                }
                break;
            case "TEMPERATURE":
                if (data instanceof ClimateSensorAvro) {
                    return ((ClimateSensorAvro) data).getTemperatureC();
                } else if (data instanceof TemperatureSensorAvro) {
                    return ((TemperatureSensorAvro) data).getTemperatureC();
                }
                break;
            case "CO2LEVEL":
                if (data instanceof ClimateSensorAvro) {
                    return ((ClimateSensorAvro) data).getCo2Level();
                }
                break;
            case "HUMIDITY":
                if (data instanceof ClimateSensorAvro) {
                    return ((ClimateSensorAvro) data).getHumidity();
                }
                break;
            default:
                return null;
        }
        return null;
    }

    private boolean compare(Object actual, String operation, Object threshold) {
        log.debug("Сравнение: actual={}, operation={}, threshold={}", actual, operation, threshold);

        return switch (actual) {
            case Boolean boolActual -> compareBoolean(boolActual, operation, threshold);
            case Integer intActual -> compareInteger(intActual, operation, threshold);
            default -> false;
        };
    }

    private boolean compareBoolean(boolean actual, String operation, Object threshold) {
        boolean thresholdValue = extractBooleanThreshold(threshold);
        return "EQUALS".equals(operation) && actual == thresholdValue;
    }

    private boolean compareInteger(int actual, String operation, Object threshold) {
        Integer thresholdValue = extractIntegerThreshold(threshold);
        if (thresholdValue == null) return false;

        return switch (operation) {
            case "EQUALS" -> actual == thresholdValue;
            case "GREATER_THAN" -> actual > thresholdValue;
            case "LOWER_THAN" -> actual < thresholdValue;
            default -> false;
        };
    }

    private boolean extractBooleanThreshold(Object threshold) {
        return switch (threshold) {
            case Boolean b -> b;
            case Integer i -> i != 0;
            default -> false;
        };
    }

    private Integer extractIntegerThreshold(Object threshold) {
        return switch (threshold) {
            case Integer i -> i;
            case Long l -> l.intValue();
            default -> null;
        };
    }
}