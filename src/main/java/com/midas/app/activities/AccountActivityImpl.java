package com.midas.app.activities;

import com.midas.app.models.Account;
import com.midas.app.providers.external.stripe.StripeConfiguration;
import com.midas.app.providers.external.stripe.StripePaymentProvider;
import com.midas.app.providers.payment.CreateAccount;

public class AccountActivityImpl implements AccountActivity {
  @Override
  public Account saveAccount(Account account) {
    return createPaymentAccount(account);
  }

  @Override
  public Account createPaymentAccount(Account account) {
    StripeConfiguration stripeConfiguration = new StripeConfiguration();

    StripePaymentProvider stripePaymentProvider = new StripePaymentProvider(stripeConfiguration);
    return stripePaymentProvider.createAccount(
        new CreateAccount(
            account.getId().toString(),
            account.getFirstName(),
            account.getLastName(),
            account.getEmail()));
  }
}
