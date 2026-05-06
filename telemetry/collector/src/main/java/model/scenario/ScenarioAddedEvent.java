package model.scenario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import model.device.DeviceAction;
import model.state.HubState;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
// Событие добавления сценария в систему. Содержит информацию о названии сценария, условиях и действиях.
public class ScenarioAddedEvent {
    @NotNull
    private String hubId;                          // Идентификатор хаба, связанный с событием
    @NotNull
    private Instant timestamp;                // Временная метка события
    @NotNull
    @Size(min = 3, max = 2147483646)
    private String name;                            // Название добавленного сценария
    private List<ScenarioCondition> conditions;     // Список условий, которые связаны со сценарием. Не может быть пустым
    private List<DeviceAction> actions;             // Список действий, которые должны быть выполнены в рамках сценария. Не может быть пустым
    private HubState type;                          // Тип события от хаба
}