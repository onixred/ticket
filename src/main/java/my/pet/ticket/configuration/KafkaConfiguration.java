package my.pet.ticket.configuration;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import my.pet.ticket.etcd.EtcdClient;
import my.pet.ticket.kafka.consumer.KafkaListeners;
import my.pet.ticket.kafka.model.TicketInfo;
import my.pet.ticket.property.KafkaProperty;
import my.pet.ticket.property.TopicName;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

/**
 * KafkaConfiguration класс для конфигурации бинов Kafka
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

    public static final String KAFKA_BOOTSTRAP_SERVERS = "/kafka.bootstrap-servers";
    public static final String KAFKA_TOPIC_NAME = "/kafka.topic.name";
    public static final String KAFKA_CONSUMER_GROUP_ID = "/kafka.consumer.group-id";
    private final EtcdClient etcdClient;

    @Bean
    public KafkaProperty kafkaProperty(){
        KafkaProperty kafkaProperty = new KafkaProperty();
        etcdClient.readEtcd(String.class, KAFKA_BOOTSTRAP_SERVERS, null, kafkaProperty::setBootstrapServers);
        etcdClient.readEtcd(String.class, KAFKA_TOPIC_NAME, null, kafkaProperty::setTopicName);
        etcdClient.readEtcd(String.class, KAFKA_CONSUMER_GROUP_ID, null, kafkaProperty::setConsumerGroupId);

        return kafkaProperty;
    }

    @Bean
    public ProducerFactory<String, TicketInfo> producerFactory(KafkaProperty kafkaProperty) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperty.getBootstrapServers());
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, TicketInfo> kafkaTemplate(KafkaProperty kafkaProperty) {
        return new KafkaTemplate<>(producerFactory(kafkaProperty));
    }


    @Bean
    public ConsumerFactory<String, TicketInfo> consumerFactory(KafkaProperty kafkaProperty) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperty.getBootstrapServers());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperty.getConsumerGroupId());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(
                props, new StringDeserializer(), new JsonDeserializer<>(TicketInfo.class));
    }

    @Bean
    public KafkaMessageListenerContainer<String, TicketInfo> ticketListener(KafkaProperty kafkaProperty) {
        ContainerProperties containerProperties = new ContainerProperties(kafkaProperty.getTopicName());
        containerProperties.setMessageListener(new KafkaListeners());

        KafkaMessageListenerContainer<String, TicketInfo> listenerContainer = new KafkaMessageListenerContainer<>(
                consumerFactory(kafkaProperty), containerProperties);
        listenerContainer.setAutoStartup(false);
        listenerContainer.start();

        return listenerContainer;
    }

    @Bean
    public TopicName topicName(KafkaProperty kafkaProperty) {
        return new TopicName(kafkaProperty.getTopicName());
    }
}
