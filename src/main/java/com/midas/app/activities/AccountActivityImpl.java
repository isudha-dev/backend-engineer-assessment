package com.midas.app.activities;

import com.midas.app.models.Account;
import com.midas.app.providers.external.stripe.StripeConfiguration;
import com.midas.app.providers.external.stripe.StripePaymentProvider;
import com.midas.app.providers.payment.CreateAccount;
import com.midas.app.services.AccountService;

public class AccountActivityImpl implements AccountActivity {
  StripeConfiguration stripeConfiguration;
  AccountService accountService;

  public AccountActivityImpl(
      StripeConfiguration stripeConfiguration, AccountService accountService) {
    this.stripeConfiguration = stripeConfiguration;
    this.accountService = accountService;
  }

  @Override
  public Account saveAccount(Account account) {
    return accountService.createAccount(account);
  }

  @Override
  public Account createPaymentAccount(Account account) {
    StripePaymentProvider stripePaymentProvider = new StripePaymentProvider(stripeConfiguration);

    CreateAccount createAccount =
        new CreateAccount(
            account.getId().toString(),
            account.getFirstName(),
            account.getLastName(),
            account.getEmail());
    return stripePaymentProvider.createAccount(createAccount);
  }
}
