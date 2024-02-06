package com.midas.generated.model;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class CreateAccountDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}
