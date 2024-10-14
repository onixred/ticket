package my.pet.ticket.server.common.utils;

import my.pet.ticket.server.application.domain.service.DomainServiceException;

public final class NameUtils {

  public static String[] parseFullName(String fullName) {
    String[] names = fullName.split(" ");
    if (names.length == 3) {
      return names;
    }
    throw new DomainServiceException("Full name contain more or less names");
  }

}
