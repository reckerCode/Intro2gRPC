package io.grpc.intro.gRPC;

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
 *
 */
@SpringBootApplication
public class GRpcApplication {

	public static void main(String[] args) {
		SpringApplication.run(GRpcApplication.class, args);
	}

}
