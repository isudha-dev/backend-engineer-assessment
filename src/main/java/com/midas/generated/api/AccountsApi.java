package com.midas.generated.api;

import com.midas.generated.model.AccountDto;
import com.midas.generated.model.CreateAccountDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountsApi {

    ResponseEntity<AccountDto> createUserAccount(CreateAccountDto createAccountDto);
    ResponseEntity<List<AccountDto>> getUserAccounts() ;
}
