package com.manitaggarwal.grpcclient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import static com.manitaggarwal.grpcclient.GreetingServiceGrpc.newBlockingStub;

@SpringBootApplication
public class GrpcClientApplication {

    private final static String CHANNEL_NAME = "localhost";
    private final static int SERVER_PORT = 50051;

    private final static List<String> list = List.of("Dan", "Pan");

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(GrpcClientApplication.class, args);
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(CHANNEL_NAME, SERVER_PORT)
                .usePlaintext()
                .build();

        com.manitaggarwal.grpcclient.GreetingServiceGrpc.GreetingServiceBlockingStub stub =
                newBlockingStub(channel);

        for (String name : list) {
            com.manitaggarwal.grpcclient.GreetingResponse response =
                    stub.greet(com.manitaggarwal.grpcclient.GreetingRequest.newBuilder()
                            .setFirstName(name)
                            .build());

            System.out.println(response.getResult());
        }

        channel.shutdown();
    }

}
