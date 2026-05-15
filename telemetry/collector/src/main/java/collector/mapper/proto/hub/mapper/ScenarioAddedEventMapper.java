package collector.mapper.proto.hub.mapper;

import collector.model.DeviceAction;
import collector.model.ScenarioCondition;
import collector.model.hub.ScenarioAddedEvent;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.*;

@Component
public class ScenarioAddedEventMapper implements HubProtoMapper<ScenarioAddedEvent> {
    @Override
    public Class<ScenarioAddedEvent> getEventType() {
        return ScenarioAddedEvent.class;
    }

    @Override
    public void mapPayload(ScenarioAddedEvent event, HubEventProto.Builder builder) {
        ScenarioAddedEventProto.Builder payloadBuilder = ScenarioAddedEventProto.newBuilder()
                .setName(event.getName());

        event.getConditions().stream()
                .map(this::mapConditionToProto)
                .forEach(payloadBuilder::addCondition);

        event.getActions().stream()
                .map(this::mapActionToProto)
                .forEach(payloadBuilder::addAction);

        builder.setScenarioAdded(payloadBuilder.build());
    }

    private ScenarioConditionProto mapConditionToProto(ScenarioCondition condition) {
        ScenarioConditionProto.Builder protoCond = ScenarioConditionProto.newBuilder()
                .setSensorId(condition.getSensorId())
                .setType(ConditionTypeProto.valueOf(condition.getType().name()))
                .setOperation(ConditionOperationProto.valueOf(condition.getOperation().name()));

        Integer val = condition.getValue();
        if (val != null) {
            protoCond.setIntValue(val);
        }
        return protoCond.build();
    }

    private DeviceActionProto mapActionToProto(DeviceAction action) {
        DeviceActionProto.Builder protoAction = DeviceActionProto.newBuilder()
                .setSensorId(action.getSensorId())
                .setType(ActionTypeProto.valueOf(action.getType().name()));

        if (action.getValue() != null) {
            protoAction.setValue(action.getValue());
        }
        return protoAction.build();
    }
}