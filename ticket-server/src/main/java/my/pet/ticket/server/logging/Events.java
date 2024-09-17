package my.pet.ticket.server.logging;

import lombok.Getter;

@Getter
public enum Events {

    BP_START("Business process start"),

    BP_IN_PROGRESS("Business process in progress"),

    BP_END("Business process ended");

    private final String name;

    Events(String name) {
        this.name = name;
    }

    public Event getName() {
        return new DefaultEvent(name);
    }
}
