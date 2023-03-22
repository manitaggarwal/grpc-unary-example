package com.manitaggarwal.grpc;

import com.manitaggarwal.grpc.service.GreetingServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GrpcApplication {

    private final static int SERVER_PORT = 50051;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(SERVER_PORT)
                .addService(new GreetingServiceImpl())
                .build();
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));
        SpringApplication.run(GrpcApplication.class, args);
        server.awaitTermination();

    }

}
