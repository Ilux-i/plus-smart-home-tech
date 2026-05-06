import model.BaseEvent;
import model.device.DeviceAddedEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class CollectorController {

    // Эндпоинт для обработки событий от датчиков
    @PostMapping("/sensors")
    public void processingEventsFromSensors(
            @RequestBody BaseEvent event
            ) {

    }

    // Эндпоинт для обработки событий от хаба
    @PostMapping("/hubs")
    public void processingEventsFromHub(
            @RequestBody DeviceAddedEvent deviceAddedEvent
    ) {

    }

}
