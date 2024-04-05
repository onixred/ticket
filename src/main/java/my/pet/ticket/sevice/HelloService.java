package my.pet.ticket.sevice;


import io.grpc.examples.proto.GreeterServiceGrpc;
import io.grpc.examples.proto.HelloReply;
import io.grpc.examples.proto.HelloRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloService extends GreeterServiceGrpc.GreeterServiceImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        super.sayHello(request, responseObserver);
    }
}
