package com.praise.io.shopifychallenge2022.batchprocesing;

import com.praise.io.shopifychallenge2022.model.Product;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class ProductBatchConfig {

  public static final String[] FIELD_NAMES = {
    "id", "name", "serial_number", "quantity", "category", "price", "image_url"
  };
  public final JobBuilderFactory jobBuilderFactory;
  public final StepBuilderFactory stepBuilderFactory;

  @Autowired
  public ProductBatchConfig(
      JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
    this.jobBuilderFactory = jobBuilderFactory;
    this.stepBuilderFactory = stepBuilderFactory;
  }

  @Bean
  public JdbcBatchItemWriter<Product> writer(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<Product>()
        .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
        .sql(
            "INSERT INTO product (id, name, serial_number, quantity, category, price, image_url) "
                + " VALUES (:id, :name, :serialNumber, :quantity, :category, :price, :imageUrl)")
        .dataSource(dataSource)
        .build();
  }

  @Bean
  public FlatFileItemReader<ProductInput> reader() {
    return new FlatFileItemReaderBuilder<ProductInput>()
        .name("ProductItemReader")
        .resource(new ClassPathResource("products-dataset.csv"))
        .delimited()
        .names(FIELD_NAMES)
        .fieldSetMapper(
            new BeanWrapperFieldSetMapper<>() {
              {
                setTargetType(ProductInput.class);
              }
            })
        .build();
  }

  @Bean
  public ProductDataProcessor processor() {
    return new ProductDataProcessor();
  }

  @Bean
  public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
    return jobBuilderFactory
        .get("importUserJob")
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        .flow(step1)
        .end()
        .build();
  }

  @Bean
  public Step step1(JdbcBatchItemWriter<Product> writer) {
    return stepBuilderFactory
        .get("step1")
        .<ProductInput, Product>chunk(10)
        .reader(reader())
        .processor(processor())
        .writer(writer)
        .build();
  }
}
