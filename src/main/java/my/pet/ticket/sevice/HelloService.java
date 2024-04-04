package my.pet.ticket.sevice;

import io.grpc.examples.helloworld.GreeterServiceGrpc.GreeterServiceImplBase;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloService extends GreeterServiceImplBase{

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        super.sayHello(request, responseObserver);
    }
}
