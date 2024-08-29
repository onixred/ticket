package my.pet.ticket.configuration;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import my.pet.ticket.etcd.EtcdClient;
import my.pet.ticket.grpc.server.TicketServiceGrpcImpl;
import my.pet.ticket.logging.EventLog;
import my.pet.ticket.logging.Log;
import my.pet.ticket.property.GrpcProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * GrpcServerConfiguration класс для создания бинов запуска GRPC
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Configuration
@RequiredArgsConstructor
public class GrpcServerConfiguration {

    public static final String DATASOURCE_GRPC_PORT = "/grpc.port";
    private final EtcdClient etcdClient;

    private final TicketServiceGrpcImpl ticketServiceGrpcImpl;

    @Bean
    public GrpcProperty grpcProperty(){
        GrpcProperty grpcProperty = new GrpcProperty();
        etcdClient.readEtcd(String.class, DATASOURCE_GRPC_PORT, null, grpcProperty::setPort);
        return grpcProperty;
    }

    @Bean
    public Server grpcCustomServer(GrpcProperty grpcProperty) throws IOException, InterruptedException {
        int port = Integer.parseInt(grpcProperty.getPort());
        Server server = ServerBuilder.forPort(port)
                .addService(ticketServiceGrpcImpl)
                .build();

        Log.INFO("Server GRPC started, listening on " + port + ":", EventLog.INIT);

        server.start();
        server.awaitTermination();

        return server;
    }
}
