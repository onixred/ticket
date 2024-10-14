package my.pet.ticket.server;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.test.EtcdClusterExtension;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@DirtiesContext
@Testcontainers
@ContextConfiguration(initializers = {ApplicationTest.Initializer.class})
@ActiveProfiles("dev")
public class ApplicationTest {

  @RegisterExtension
  private static final EtcdClusterExtension ETCD_CLUSTER = EtcdClusterExtension.builder()
      .withImage("quay.io/coreos/etcd:v3.4.34").withNodes(1).build();

  @Container
  public static PostgreSQLContainer<?> POSTGRESQL_CONTAINER = new PostgreSQLContainer<>(
      "postgres:11.1").withReuse(true).withDatabaseName("ticket");

  @Test
  void testApplicationStartup() {
  }

  static class Initializer implements
      ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    @SneakyThrows
    public void initialize(ConfigurableApplicationContext applicationContext) {
      Client client = Client.builder().endpoints(ETCD_CLUSTER.clientEndpoints()).build();
      KV kv = client.getKVClient();
      kv.put(ByteSequence.from("configs.dev.data.spring.datasource.driver-class-name".getBytes()),
          ByteSequence.from(POSTGRESQL_CONTAINER.getDriverClassName().getBytes())).get();
      kv.put(ByteSequence.from("configs.dev.data.spring.datasource.url".getBytes()),
          ByteSequence.from(POSTGRESQL_CONTAINER.getJdbcUrl().getBytes())).get();
      kv.put(ByteSequence.from("configs.dev.data.spring.datasource.username".getBytes()),
          ByteSequence.from(POSTGRESQL_CONTAINER.getUsername().getBytes())).get();
      kv.put(ByteSequence.from("configs.dev.data.spring.datasource.password".getBytes()),
          ByteSequence.from(POSTGRESQL_CONTAINER.getPassword().getBytes())).get();
      kv.put(ByteSequence.from("test.value".getBytes()),
          ByteSequence.from("value".getBytes())).get();
      TestPropertyValues.of(
              "spring.etcd.client.endpoints[0]=" + ETCD_CLUSTER.clientEndpoints().get(0).toString())
          .applyTo(applicationContext);
    }

  }

}