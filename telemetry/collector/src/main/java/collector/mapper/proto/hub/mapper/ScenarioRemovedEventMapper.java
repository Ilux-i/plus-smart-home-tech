package collector.mapper.proto.hub.mapper;

import collector.model.hub.ScenarioRemovedEvent;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.grpc.telemetry.event.HubEventProto;
import ru.yandex.practicum.grpc.telemetry.event.ScenarioRemovedEventProto;

@Component
public class ScenarioRemovedEventMapper implements HubProtoMapper<ScenarioRemovedEvent> {
    @Override
    public Class<ScenarioRemovedEvent> getEventType() {
        return ScenarioRemovedEvent.class;
    }

    @Override
    public void mapPayload(ScenarioRemovedEvent event, HubEventProto.Builder builder) {
        ScenarioRemovedEventProto payload = ScenarioRemovedEventProto.newBuilder()
                .setName(event.getName())
                .build();
        builder.setScenarioRemoved(payload);
    }
}