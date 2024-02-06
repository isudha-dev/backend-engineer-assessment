package com.midas.app.providers.external.stripe;

import com.midas.app.enums.ProviderType;
import com.midas.app.models.Account;
import com.midas.app.providers.payment.CreateAccount;
import com.midas.app.providers.payment.PaymentProvider;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class StripePaymentProvider implements PaymentProvider {
  private final Logger logger = LoggerFactory.getLogger(StripePaymentProvider.class);

  private final StripeConfiguration configuration;

  /** providerName is the name of the payment provider */
  @Override
  public String providerName() {
    return "stripe";
  }

  /**
   * createAccount creates a new account in the payment provider.
   *
   * @param details is the details of the account to be created.
   * @return Account
   */
  @Override
  public Account createAccount(CreateAccount details) {
    Stripe.apiKey = System.getenv("STRIPE_API_KEY");

    CustomerCreateParams params =
        CustomerCreateParams.builder()
            .setName(details.getFirstName() + " " + details.getLastName())
            .setEmail(details.getEmail())
            .build();

    Account account = new Account();
    try {
      Customer customer = Customer.create(params);
      account.setProviderId(customer.getId());
      account.setProviderType(ProviderType.STRIPE);
      account.setFirstName(details.getFirstName());
      account.setLastName(details.getLastName());
      account.setEmail(details.getEmail());
    } catch (StripeException e) {
      System.out.println(e.getMessage());
    }
    return account;
  }
}
