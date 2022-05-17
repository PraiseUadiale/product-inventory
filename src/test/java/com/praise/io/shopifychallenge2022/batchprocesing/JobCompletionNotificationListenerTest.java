package com.praise.io.shopifychallenge2022.batchprocesing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JobCompletionNotificationListener.class})
@ExtendWith(SpringExtension.class)
class JobCompletionNotificationListenerTest {

  @MockBean
  private EntityManager entityManager;

  @Autowired
  private JobCompletionNotificationListener jobCompletionNotificationListener;

  @Test
  void testAfterJob() {
    JobExecution jobExecution = new JobExecution(
        new JobExecution(new JobExecution(new JobExecution(new JobExecution(123L)))));
    this.jobCompletionNotificationListener.afterJob(jobExecution);
    assertEquals(
        "JobExecution: id=123, version=null, startTime=null, endTime=null, lastUpdated=null, status=STARTING,"
            + " exitStatus=exitCode=UNKNOWN;exitDescription=, job=[null], jobParameters=[{}]",
        jobExecution.toString());
    assertFalse(jobExecution.isRunning());
    assertTrue(jobExecution.getStepExecutions().isEmpty());
    assertEquals(BatchStatus.STARTING, jobExecution.getStatus());
    assertTrue(jobExecution.getFailureExceptions().isEmpty());
    assertEquals(123L, jobExecution.getId().longValue());
  }
}

