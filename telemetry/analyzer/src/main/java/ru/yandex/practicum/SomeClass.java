package ru.yandex.practicum;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.grpc.telemetry.hubrouter.HubRouterControllerGrpc;


@Service
public class SomeClass {
    private final HubRouterControllerGrpc.HubRouterControllerBlockingStub hubRouterClient;

    public SomeClass(@GrpcClient("hub-router")
                     HubRouterControllerGrpc.HubRouterControllerBlockingStub hubRouterClient) {
        this.hubRouterClient = hubRouterClient;
    }
}
