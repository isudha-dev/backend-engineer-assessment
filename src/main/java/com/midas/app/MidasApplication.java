package com.midas.app;

import com.midas.app.activities.AccountActivityImpl;
import com.midas.app.workflows.CreateAccountWorkflow;
import com.midas.app.workflows.CreateAccountWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MidasApplication {

  public static void main(String[] args) {
    SpringApplication.run(MidasApplication.class, args);
    //    ConfigurableApplicationContext appContext =
    //    WorkerFactory factory = appContext.getBean(WorkerFactory.class);
    //    AccountActivity accountActivity = appContext.getBean(AccountActivity.class);
    //    Worker worker = factory.newWorker(CreateAccountWorkflow.QUEUE_NAME);
    //    worker.registerWorkflowImplementationTypes(CreateAccountWorkflowImpl.class);
    //    worker.registerActivitiesImplementations(accountActivity);
    //    factory.start();

    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
    WorkflowClient client = WorkflowClient.newInstance(service);
    WorkerFactory factory = WorkerFactory.newInstance(client);
    Worker worker = factory.newWorker(CreateAccountWorkflow.QUEUE_NAME);
    worker.registerWorkflowImplementationTypes(CreateAccountWorkflowImpl.class);
    worker.registerActivitiesImplementations(new AccountActivityImpl());
    factory.start();
  }
}
