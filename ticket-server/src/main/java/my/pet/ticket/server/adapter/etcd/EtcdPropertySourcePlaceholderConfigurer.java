package my.pet.ticket.server.adapter.etcd;

import io.etcd.jetcd.Client;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;

@Configuration
public class EtcdPropertySourcePlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {

  private static final String CONFIGURATION_SERVER_HOST_PROPERTY = "spring.etcd.client.endpoints";

  private ConfigurableListableBeanFactory beanFactory;

  @Override
  public PropertySources getAppliedPropertySources() throws IllegalStateException {
    MutablePropertySources appliedPropertySources = (MutablePropertySources) super.getAppliedPropertySources();
    PropertySource<?> environmentPropertySource = appliedPropertySources.get(
        "environmentProperties");
    if (environmentPropertySource != null) {
      ConfigurableEnvironment configurableEnvironment = (ConfigurableEnvironment) environmentPropertySource.getSource();
      addCustomPropertySource(environmentPropertySource, configurableEnvironment);
    }
    return appliedPropertySources;
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
      throws BeansException {
    super.postProcessBeanFactory(beanFactory);
    this.beanFactory = beanFactory;
  }

  protected void addCustomPropertySource(PropertySource<?> environmentPropertySource,
      ConfigurableEnvironment configurableEnvironment) {
    EtcdAdapter etcdAdapter = registerEtcdAdapter(environmentPropertySource);
    Map<String, Object> properties = new HashMap<>();
    for (String profile : configurableEnvironment.getActiveProfiles()) {
      properties.putAll(etcdAdapter.getAllByProfile(profile));
    }
    configurableEnvironment.getPropertySources()
        .addFirst(new MapPropertySource("customPropertySource", properties));
  }

  protected EtcdAdapter registerEtcdAdapter(PropertySource<?> environmentPropertySource) {
    List<String> etcdEndpoints = getListOfProperty(environmentPropertySource,
        CONFIGURATION_SERVER_HOST_PROPERTY);
    if (etcdEndpoints.isEmpty()) {
      throw new EtcdAdapterException("Etcd endpoints is not presented");
    }
    Client etcdClient = createEtcdClient(etcdEndpoints);
    return createEtcdAdapter(etcdClient);
  }

  protected List<String> getListOfProperty(PropertySource<?> environmentPropertySource,
      String property) {
    List<String> list = new ArrayList<>();
    if (!property.isEmpty()) {
      int index = 0;
      while (environmentPropertySource.containsProperty(property + "[" + index + "]")) {
        list.add(
            (String) environmentPropertySource.getProperty(property + "[" + index + "]"));
        index++;
      }
    }
    return list;
  }

  protected Client createEtcdClient(List<String> endpoints) {
    Client etcdClient = Client.builder().endpoints(endpoints.toArray(new String[]{}))
        .build();
    this.beanFactory.registerSingleton("etcdClient", etcdClient);
    return etcdClient;
  }

  protected EtcdAdapter createEtcdAdapter(Client client) {
    EtcdAdapter etcdAdapter = new EtcdAdapter(client);
    this.beanFactory.registerSingleton("etcdAdapter", etcdAdapter);
    return etcdAdapter;
  }

}
