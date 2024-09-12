package my.pet.ticket.logging;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventLog  implements Event {

    INIT("Настройка конфигурации ","Ошибка при настройке конфига"),

    T_FIND("Поиск задачи ","Ошибка при поиске задачи"),
    T_CREATE("Создание задачи ","Ошибка при создании задачи"),
    T_REMOVE("Удаление задачи ","Ошибка при удалении задачи"),

    K_SEND("Отправка сообщения Kafka","Ошибка при отправке сообщения"),
    K_GET("Получение сообщения Kafka","Ошибка при получении сообщения");

    private final String description;

    private final String errorCode;


}
