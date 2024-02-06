package com.midas.app.apis;

import com.midas.app.dtos.AccountDto;
import com.midas.app.dtos.CreateAccountDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface AccountsApi {

  ResponseEntity<AccountDto> createUserAccount(CreateAccountDto createAccountDto);

  ResponseEntity<List<AccountDto>> getUserAccounts();
}
