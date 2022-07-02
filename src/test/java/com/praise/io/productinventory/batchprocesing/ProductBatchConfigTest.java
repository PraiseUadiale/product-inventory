//package com.praise.io.shopifychallenge2022.batchprocesing;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.flow.FlowStep;
//import org.springframework.batch.core.repository.dao.JdbcExecutionContextDao;
//import org.springframework.batch.core.repository.dao.JdbcJobExecutionDao;
//import org.springframework.batch.core.repository.dao.JdbcJobInstanceDao;
//import org.springframework.batch.core.repository.dao.JdbcStepExecutionDao;
//import org.springframework.batch.core.repository.support.SimpleJobRepository;
//import org.springframework.batch.core.step.tasklet.TaskletStep;
//import org.springframework.batch.item.database.JdbcBatchItemWriter;
//import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
//
//class ProductBatchConfigTest {
//
//  @Test
//  void testReader() {
//    JdbcJobInstanceDao jobInstanceDao = new JdbcJobInstanceDao();
//    JdbcJobExecutionDao jobExecutionDao = new JdbcJobExecutionDao();
//    JdbcStepExecutionDao stepExecutionDao = new JdbcStepExecutionDao();
//    JobBuilderFactory jobBuilderFactory = new JobBuilderFactory(
//        new SimpleJobRepository(jobInstanceDao, jobExecutionDao, stepExecutionDao,
//            new JdbcExecutionContextDao()));
//    JdbcJobInstanceDao jobInstanceDao1 = new JdbcJobInstanceDao();
//    JdbcJobExecutionDao jobExecutionDao1 = new JdbcJobExecutionDao();
//    JdbcStepExecutionDao stepExecutionDao1 = new JdbcStepExecutionDao();
//    SimpleJobRepository jobRepository = new SimpleJobRepository(jobInstanceDao1, jobExecutionDao1,
//        stepExecutionDao1,
//        new JdbcExecutionContextDao());
//
//    assertTrue((new ProductBatchConfig(jobBuilderFactory,
//        new StepBuilderFactory(jobRepository, new ResourcelessTransactionManager()))).reader()
//        .isSaveState());
//  }
//
//  @Test
//  void testImportUserJob() {
//    JdbcJobInstanceDao jobInstanceDao = new JdbcJobInstanceDao();
//    JdbcJobExecutionDao jobExecutionDao = new JdbcJobExecutionDao();
//    JdbcStepExecutionDao stepExecutionDao = new JdbcStepExecutionDao();
//    JobBuilderFactory jobBuilderFactory = new JobBuilderFactory(
//        new SimpleJobRepository(jobInstanceDao, jobExecutionDao, stepExecutionDao,
//            new JdbcExecutionContextDao()));
//    JdbcJobInstanceDao jobInstanceDao1 = new JdbcJobInstanceDao();
//    JdbcJobExecutionDao jobExecutionDao1 = new JdbcJobExecutionDao();
//    JdbcStepExecutionDao stepExecutionDao1 = new JdbcStepExecutionDao();
//    SimpleJobRepository jobRepository = new SimpleJobRepository(jobInstanceDao1, jobExecutionDao1,
//        stepExecutionDao1,
//        new JdbcExecutionContextDao());
//
//    ProductBatchConfig productBatchConfig = new ProductBatchConfig(jobBuilderFactory,
//        new StepBuilderFactory(jobRepository, new ResourcelessTransactionManager()));
//    JobCompletionNotificationListener listener = new JobCompletionNotificationListener(null);
//    Job actualImportUserJobResult = productBatchConfig.importUserJob(listener, new FlowStep());
//    assertTrue(actualImportUserJobResult
//        .getJobParametersIncrementer() instanceof org.springframework.batch.core.launch.support.RunIdIncrementer);
//    assertTrue(actualImportUserJobResult.isRestartable());
//    assertEquals("importUserJob", actualImportUserJobResult.getName());
//    assertTrue(actualImportUserJobResult
//        .getJobParametersValidator() instanceof org.springframework.batch.core.job.DefaultJobParametersValidator);
//  }
//
//  @Test
//  void testStep1() {
//    JdbcJobInstanceDao jobInstanceDao = new JdbcJobInstanceDao();
//    JdbcJobExecutionDao jobExecutionDao = new JdbcJobExecutionDao();
//    JdbcStepExecutionDao stepExecutionDao = new JdbcStepExecutionDao();
//    JobBuilderFactory jobBuilderFactory = new JobBuilderFactory(
//        new SimpleJobRepository(jobInstanceDao, jobExecutionDao, stepExecutionDao,
//            new JdbcExecutionContextDao()));
//    JdbcJobInstanceDao jobInstanceDao1 = new JdbcJobInstanceDao();
//    JdbcJobExecutionDao jobExecutionDao1 = new JdbcJobExecutionDao();
//    JdbcStepExecutionDao stepExecutionDao1 = new JdbcStepExecutionDao();
//    SimpleJobRepository jobRepository = new SimpleJobRepository(jobInstanceDao1, jobExecutionDao1,
//        stepExecutionDao1,
//        new JdbcExecutionContextDao());
//
//    ProductBatchConfig productBatchConfig = new ProductBatchConfig(jobBuilderFactory,
//        new StepBuilderFactory(jobRepository, new ResourcelessTransactionManager()));
//    Step actualStep1Result = productBatchConfig.step1(new JdbcBatchItemWriter<>());
//    assertEquals("step1", actualStep1Result.getName());
//    assertFalse(actualStep1Result.isAllowStartIfComplete());
//    assertTrue(((TaskletStep) actualStep1Result)
//        .getTasklet() instanceof org.springframework.batch.core.step.item.ChunkOrientedTasklet);
//    assertEquals(2147483647, actualStep1Result.getStartLimit());
//  }
//}
//
