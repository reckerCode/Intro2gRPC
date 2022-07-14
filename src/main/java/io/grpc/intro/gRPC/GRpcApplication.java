package io.grpc.intro.gRPC;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Defining the Service
 * We start by defining a service, specifying methods
 * that can be called remotely along with their parameters
 * and return types
 * This is done in the .proto file using the protocol
 * buffers. They are also used for describing the
 * structure of the payload messages.
 *
 * Creating the Client:
 *  gRPC provides a channel construct that abstracts out the
 *  underlying details like connection, connection pooling,
 *  load balancing, etc.
 *
 *  We need to create a stub which we'll use to make the actual
 *  remote call to hello(). The stud is the primary way for
 *  clients to interacts with the server. When using auto-generated
 *  stubs, the stubs class will have constructors for wrapping the
 *  channel.
 *
 *  Here we're using a blocking/synchronous stub so that the RPC call
 *  waits for the server to respond, and will either return a response
 *  or raise an exception. There are two other types of stubs provided by
 *  gRPC, which facilitate non-blocking/asynchronous call.
 *
 *  Finally, time to make the hello() RPC call. Here we pass the HelloRequest.
 *  We can use the auto-generated setters to set the firstName, lastName
 *  attributes of the HelloRequest object. We get back the HelloResponse
 *  object returned from the server.
 *
 *
 */
@SpringBootApplication
public class GRpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(GRpcApplication.class, args);

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        io.grpc.intro.gRPC.HelloServiceGrpc.HelloServiceBlockingStub stub
                = io.grpc.intro.gRPC.HelloServiceGrpc.newBlockingStub(channel);

        io.grpc.intro.gRPC.HelloResponse helloResponse = stub.hello(io.grpc.intro.gRPC.HelloRequest.newBuilder()
                .setFirstName("Baeldung")
                .setLastName("gRPC")
                .build());

        channel.shutdown();
    }

}
