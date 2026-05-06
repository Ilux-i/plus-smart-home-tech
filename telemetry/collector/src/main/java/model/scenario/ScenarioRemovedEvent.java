package model.scenario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import model.state.HubState;

import java.time.Instant;

@Getter
@Builder
// Событие удаления сценария из системы. Содержит информацию о названии удаленного сценария
public class ScenarioRemovedEvent {
    @NotNull
    private String hubId;                          // Идентификатор хаба, связанный с событием
    @NotNull
    private Instant timestamp;                     // Временная метка события
    @NotNull
    @Size(min = 3, max = 2147483646)
    private String name;                            // Название добавленного сценария
    @NotNull
    private HubState type;                          // Типы событий от хаба
}