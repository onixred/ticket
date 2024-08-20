package my.pet.ticket.exception;

public class TicketNotFoundException extends RuntimeException {

    public static final String TICKET_NOT_FOUND = "Ticket not found";

    public TicketNotFoundException(String message) {
        super(message);
    }

    public TicketNotFoundException() {
        super(TICKET_NOT_FOUND);
    }
}