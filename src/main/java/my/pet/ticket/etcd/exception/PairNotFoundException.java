package my.pet.ticket.etcd.exception;

/**
 * PairsNotFoundException class for exception details
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
public class PairNotFoundException extends RuntimeException {
    public static final String PAIR_NOT_FOUND = "Pair not found";

    public PairNotFoundException() {
        super(PAIR_NOT_FOUND);
    }

    public PairNotFoundException(String message) {
        super(message);
    }
}
