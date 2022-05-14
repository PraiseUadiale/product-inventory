package com.praise.io.shopifychallenge2022.batchprocesing;

import com.praise.io.shopifychallenge2022.model.Product;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private final EntityManager entityManager;

  @Autowired
  public JobCompletionNotificationListener(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  @Transactional
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("JOB FINISHED! Defaulting Product visibility");

      TypedQuery<Product> query =
          entityManager.createQuery("select c  from Product c", Product.class);
      query
          .getResultList()
          .forEach(
              e -> {
                e.setDeleted(Boolean.FALSE);
                entityManager.persist(e);
              });
    }
  }
}
