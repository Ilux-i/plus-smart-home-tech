package collector.model.scenario;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import collector.model.state.ConditionType;
import collector.model.state.ConditionOperation;

@Getter
@Builder
// Условие сценария, которое содержит информацию о датчике, типе условия, операции и значении
public class ScenarioCondition {
    @NotNull
    private String sensorId;           // Идентификатор датчика, связанного с условием
    @NotNull
    private ConditionType type;        // Тип условия
    @NotNull
    private ConditionOperation operation;   // Операции
    private Integer value;              // Значение, используемое в условии
}