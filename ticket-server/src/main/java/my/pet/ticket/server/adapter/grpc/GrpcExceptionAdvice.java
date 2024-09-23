package my.pet.ticket.server.adapter.grpc;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;

@GrpcAdvice
public class GrpcExceptionAdvice {

  @GrpcExceptionHandler(DataIntegrityViolationException.class)
  public StatusRuntimeException dataIntegrityViolationException(
      DataIntegrityViolationException exception) {
    return Status.INVALID_ARGUMENT.withDescription(exception.getMessage()).asRuntimeException();
  }

}
