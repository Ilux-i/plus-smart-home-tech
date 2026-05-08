package collector.kafka;

import collector.model.*;
import collector.model.device.BaseDeviceEvent;
import collector.model.device.DeviceAddedEvent;
import collector.model.device.DeviceRemovedEvent;
import collector.model.scenario.ScenarioAddedEvent;
import collector.model.scenario.ScenarioRemovedEvent;
import collector.model.scenario.ScenarioCondition;
import collector.model.device.DeviceAction;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.*;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AvroMapper {

    public SpecificRecordBase mapSensorEventToAvro(BaseEvent event) {
        SensorEventAvro avroEvent = new SensorEventAvro();

        avroEvent.setId(event.getId());
        avroEvent.setHubId(event.getHubId());

        if (event.getTimestamp() != null) {
            avroEvent.setTimestamp(event.getTimestamp().toEpochMilli());
        } else {
            avroEvent.setTimestamp(Instant.now().toEpochMilli());
        }

        switch (event) {
            case ClimateSensorEvent cse -> {
                ClimateSensorAvro data = new ClimateSensorAvro();

                data.setTemperatureC(cse.getTemperatureC());
                data.setHumidity(cse.getHumidity());
                data.setCo2Level(cse.getCo2Level());

                avroEvent.setPayload(data);
            }
            case LightSensorEvent lse -> {
                LightSensorAvro data = new LightSensorAvro();

                data.setLinkQuality(lse.getLinkQuality());
                data.setLuminosity(lse.getLuminosity());

                avroEvent.setPayload(data);
            }
            case MotionSensorEvent mse -> {
                MotionSensorAvro data = new MotionSensorAvro();

                data.setLinkQuality(mse.getLinkQuality());
                data.setMotion(mse.getMotion());
                data.setVoltage(mse.getVoltage());

                avroEvent.setPayload(data);
            }
            case SwitchSensorEvent sse -> {
                SwitchSensorAvro data = new SwitchSensorAvro();

                data.setState(sse.getState());

                avroEvent.setPayload(data);
            }
            case TemperatureSensorEvent tse -> {
                TemperatureSensorAvro data = new TemperatureSensorAvro();

                data.setId(tse.getId());
                data.setHubId(tse.getHubId());
                data.setTimestamp(tse.getTimestamp() != null ? tse.getTimestamp().toEpochMilli() : Instant.now().toEpochMilli());
                data.setTemperatureC(tse.getTemperatureC());
                data.setTemperatureF(tse.getTemperatureF());

                avroEvent.setPayload(data);
            }
            default -> throw new IllegalArgumentException("Unknown sensor event type: " + event.getClass().getSimpleName());
        }

        return avroEvent;
    }

    public SpecificRecordBase mapHubEventToAvro(BaseDeviceEvent event) {
        HubEventAvro avroEvent = new HubEventAvro();

        avroEvent.setHubId(event.getHubId());

        if (event.getTimestamp() != null) {
            avroEvent.setTimestamp(event.getTimestamp().toEpochMilli());
        } else {
            avroEvent.setTimestamp(Instant.now().toEpochMilli());
        }

        switch (event) {
            case DeviceAddedEvent dae -> {
                DeviceAddedEventAvro data = new DeviceAddedEventAvro();

                data.setId(dae.getId());
                data.setType(DeviceTypeAvro.valueOf(dae.getType()));

                avroEvent.setPayload(data);
            }

            case DeviceRemovedEvent dre -> {
                DeviceRemovedEventAvro data = new DeviceRemovedEventAvro();
                data.setId(dre.getId());
                avroEvent.setPayload(data);
            }

            case ScenarioAddedEvent sae -> {
                ScenarioAddedEventAvro data = new ScenarioAddedEventAvro();
                data.setName(sae.getName());

                List<ScenarioConditionAvro> avroConditions = sae.getConditions().stream()
                        .map(this::mapConditionToAvro)
                        .collect(Collectors.toList());
                data.setConditions(avroConditions);

                List<DeviceActionAvro> avroActions = sae.getActions().stream()
                        .map(this::mapActionToAvro)
                        .collect(Collectors.toList());
                data.setActions(avroActions);

                avroEvent.setPayload(data);
            }

            case ScenarioRemovedEvent sre -> {
                ScenarioRemovedEventAvro data = new ScenarioRemovedEventAvro();
                data.setName(sre.getName());
                avroEvent.setPayload(data);
            }

            default -> throw new IllegalArgumentException("Unknown hub event type: " + event.getClass().getSimpleName());
        }

        return avroEvent;
    }

    private ScenarioConditionAvro mapConditionToAvro(ScenarioCondition condition) {
        ScenarioConditionAvro avro = new ScenarioConditionAvro();

        avro.setSensorId(condition.getSensorId());
        avro.setType(ConditionTypeAvro.valueOf(condition.getType().name()));
        avro.setOperation(ConditionOperationAvro.valueOf(condition.getOperation().name()));
        avro.setValue(condition.getValue());

        return avro;
    }

    private DeviceActionAvro mapActionToAvro(DeviceAction action) {
        DeviceActionAvro avro = new DeviceActionAvro();

        avro.setSensorId(action.getSensorId());
        avro.setType(ActionTypeAvro.valueOf(action.getType().name()));
        avro.setValue(action.getValue());

        return avro;
    }
}