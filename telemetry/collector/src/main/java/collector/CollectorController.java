package collector;

import collector.model.BaseEvent;
import collector.model.device.BaseDeviceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class CollectorController {

    private final CollectorService service;

    // Эндпоинт для обработки событий от датчиков
    @PostMapping("/sensors")
    public void processingEventsFromSensors(
            @RequestBody BaseEvent event
            ) {
        service.sendSensorEvent(event);
    }

    // Эндпоинт для обработки событий от хаба
    @PostMapping("/hubs")
    public void processingEventsFromHub(
            @RequestBody BaseDeviceEvent deviceEvent
    ) {
        service.sendHubEvent(deviceEvent);
    }

}
