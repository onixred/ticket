package my.pet.ticket.server.common.configuration;

import io.grpc.netty.NettyServerBuilder;
import io.netty.channel.ReflectiveChannelFactory;
import io.netty.channel.ServerChannel;
import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcServerConfiguration {

  @Bean
  public GrpcServerConfigurer keepAliveServerConfigurer() {
    return serverBuilder -> {
      if (serverBuilder instanceof NettyServerBuilder) {
        ((NettyServerBuilder) serverBuilder)
            .channelFactory(new ReflectiveChannelFactory<>(ServerChannel.class))
            .build();
      }
    };
  }

}
