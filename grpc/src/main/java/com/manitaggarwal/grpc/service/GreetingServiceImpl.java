package com.manitaggarwal.grpc.service;

import com.manitaggarwal.grpc.GreetingRequest;
import com.manitaggarwal.grpc.GreetingResponse;
import com.manitaggarwal.grpc.GreetingServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greet(GreetingRequest request,
                      StreamObserver<GreetingResponse> responseObserver) {
        System.out.println("request.getFirstName() = " + request.getFirstName());
        responseObserver.onNext(GreetingResponse.newBuilder()
                .setResult("Hello " + request.getFirstName())
                .build());
        responseObserver.onCompleted();
    }
}
