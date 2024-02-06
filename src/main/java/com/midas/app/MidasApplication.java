package com.midas.app;

import com.midas.app.activities.AccountActivity;
import com.midas.app.workflows.CreateAccountWorkflow;
import com.midas.app.workflows.CreateAccountWorkflowImpl;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MidasApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext appContext = SpringApplication.run(MidasApplication.class, args);
    WorkerFactory factory = appContext.getBean(WorkerFactory.class);
    AccountActivity accountActivity = appContext.getBean(AccountActivity.class);
    Worker worker = factory.newWorker(CreateAccountWorkflow.QUEUE_NAME);
    worker.registerWorkflowImplementationTypes(CreateAccountWorkflowImpl.class);
    worker.registerActivitiesImplementations(accountActivity);
    factory.start();
  }
}
