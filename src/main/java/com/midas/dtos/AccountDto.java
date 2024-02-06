package com.midas.generated.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class AccountDto {
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;


    public AccountDto id(UUID id) {
        this.id = id;
        return this;
    }

    public AccountDto firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AccountDto lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AccountDto email(String email) {
        this.email = email;
        return this;
    }

    public AccountDto createdAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public AccountDto updatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
