package my.pet.ticket.server.common.logging;

import lombok.Data;

@Data
public class DefaultDetail
    implements Detail {

  private final String errorCode;

  private final String description;

  public DefaultDetail(String errorCode) {
    this(errorCode, null);
  }

  public DefaultDetail(String errorCode, String description) {
    this.errorCode = errorCode;
    this.description = description;
  }

}
