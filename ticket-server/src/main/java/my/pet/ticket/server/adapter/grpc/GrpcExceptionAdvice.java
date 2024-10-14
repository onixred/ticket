package my.pet.ticket.server.adapter.grpc;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import my.pet.ticket.server.adapter.persistence.PersistenceAdapterException;
import my.pet.ticket.server.application.domain.service.DomainServiceException;
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

  @GrpcExceptionHandler(DomainServiceException.class)
  public StatusRuntimeException domainServiceException(
      DomainServiceException exception) {
    return Status.INVALID_ARGUMENT.withDescription(exception.getMessage()).asRuntimeException();
  }

  @GrpcExceptionHandler(PersistenceAdapterException.class)
  public StatusRuntimeException persistenceAdapterException(
      PersistenceAdapterException exception) {
    return Status.INVALID_ARGUMENT.withDescription(exception.getMessage()).asRuntimeException();
  }

}
