package my.pet.ticket.server.application.port.configuration;

import java.util.Map;

public interface ConfigurationPort {

  String put(String key, String value);

  String getFirst(String key);

  Map<String, String> getAll(String key);

  Map<String, String> getAllByProfile(String profile);

}
