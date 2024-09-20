package my.pet.ticket.server.application.port.configuration;

import java.util.function.Consumer;

public interface ConfigurationPort {

    <T> T put (Class<T> type, String key, T value, Consumer<T> consumer);

    <T> T getFirst (Class<T> type, String key, T defaultValue, Consumer<T> consumer);

    default <T> T put (Class<T> type, String key, T value) {
        return put(type, key, value, t -> {});
    }

    default <T> T getFirst (Class<T> type, String key, T defaultValue) {
        return getFirst(type, key, defaultValue, t -> {});
    }

}
