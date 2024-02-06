package com.midas.apis;

import com.midas.dtos.AccountDto;
import com.midas.dtos.CreateAccountDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface AccountsApi {

  ResponseEntity<AccountDto> createUserAccount(CreateAccountDto createAccountDto);

  ResponseEntity<List<AccountDto>> getUserAccounts();
}
