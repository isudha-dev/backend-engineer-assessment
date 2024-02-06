package com.midas.app.dtos;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateAccountDto {
  private UUID id;
  private String firstName;
  private String lastName;
  private String email;
}
