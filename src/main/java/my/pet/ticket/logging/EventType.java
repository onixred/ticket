package my.pet.ticket.logging;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EventType {

    APP_START("Application started", "Application start error"),
    APP_INIT("Application initialized", "Application initialize error"),
    APP_STOP("Application stopped", "Application stop error");

    private final String description;
    private final String errorCode;

}
