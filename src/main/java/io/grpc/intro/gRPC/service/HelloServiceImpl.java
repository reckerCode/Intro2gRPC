package io.grpc.intro.gRPC.service;

import io.grpc.intro.gRPC.HelloRequest;
import io.grpc.intro.gRPC.HelloResponse;
import io.grpc.intro.gRPC.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * The default implementation of the abstract class HelloServiceImplBase
 * is to throw runtime exception io.grpc.StatusRuntimeException saying
 * that the method is unimplemented
 *
 * If we compare the signature of hello() with the one we wrote in the
 * HelloService.proto file, we'll notice that it does not return
 * HelloResponse. Instead, it takes the second argument as
 * StreamObserver<HelloResponse>, which is a response observer,
 * a call back for the server to call with its response.
 * This way the client gets an option to make a blocking call or a non-blocking call
 * gRPC uses builder for creating objects. We use HelloResponse.newBuilder()
 * and set this object to the responseObserver's onNext() method to
 * send it to the client.
 *
 * Finally, we need to call onComplete() to specify that we've finished
 * dealing with the RPC, else the connection wil be hung, and the
 * client will just wait for more information to come in.
 */

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        String greetings = "Hello, " +
                request.getFirstName() +
                " " +
                request.getLastName();

        HelloResponse response = HelloResponse.newBuilder().setGreeting(greetings).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
