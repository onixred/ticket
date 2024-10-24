package my.pet.ticket.infrastructure.logging;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EventType implements Event {

    APP_START("Приложение запущено", "Ошибка запуска приложения"),
    APP_STOP("Приложение остановлено", "Ошибка завершения приложения");

    private final String description;
    private final String errorCode;

}
