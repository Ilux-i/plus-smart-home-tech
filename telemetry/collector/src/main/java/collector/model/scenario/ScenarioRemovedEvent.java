package collector.model.scenario;

import collector.model.device.BaseDeviceEvent;
import collector.model.state.HubState;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// Событие удаления сценария из системы. Содержит информацию о названии удаленного сценария
public class ScenarioRemovedEvent extends BaseDeviceEvent {
    @NotNull
    @Size(min = 3, max = 2147483646)
    private String name;                            // Название добавленного сценария

    @Override
    public String getType() {
        return HubState.SCENARIO_REMOVED.toString();
    }
}