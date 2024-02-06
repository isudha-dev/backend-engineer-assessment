package com.midas.app.workflows;

import com.midas.app.activities.AccountActivity;
import com.midas.app.models.Account;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class CreateAccountWorkflowImpl implements CreateAccountWorkflow {

  private static final String WITHDRAW = "Withdraw";
  private final RetryOptions retryoptions =
      RetryOptions.newBuilder()
          .setInitialInterval(Duration.ofSeconds(1))
          .setMaximumInterval(Duration.ofSeconds(100))
          .setBackoffCoefficient(2)
          .setMaximumAttempts(500)
          .build();
  private final ActivityOptions defaultActivityOptions =
      ActivityOptions.newBuilder()
          .setStartToCloseTimeout(Duration.ofSeconds(5))
          .setRetryOptions(retryoptions)
          .build();
  private final Map<String, ActivityOptions> perActivityMethodOptions =
      new HashMap<String, ActivityOptions>() {
        {
          put(
              WITHDRAW,
              ActivityOptions.newBuilder().setHeartbeatTimeout(Duration.ofSeconds(5)).build());
        }
      };
  private final AccountActivity account =
      Workflow.newActivityStub(
          AccountActivity.class, defaultActivityOptions, perActivityMethodOptions);

  @Override
  public Account createAccount(Account details) {

    Account newAccount = account.saveAccount(details);
    account.createPaymentAccount(details);
    return newAccount;
  }
}
