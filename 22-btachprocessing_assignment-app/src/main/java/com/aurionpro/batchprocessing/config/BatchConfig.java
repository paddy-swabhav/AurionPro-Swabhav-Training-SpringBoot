package com.aurionpro.batchprocessing.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.aurionpro.batchprocessing.entity.Employee;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Bean
    public JdbcCursorItemReader<Employee> databaseReader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Employee>()
            .dataSource(dataSource)
            .name("employeeJdbcCursorItemReader")
            .sql("SELECT employee_id, name, salary FROM employees")
            .beanRowMapper(Employee.class)
            .build();
    }

    @Bean
    public FlatFileItemWriter<Employee> csvWriter() {
        return new FlatFileItemWriterBuilder<Employee>()
            .name("employeeCsvWriter")
            .resource(new FileSystemResource("src/main/resources/employees.csv"))
            .delimited()
            .delimiter(",")
            .names("employeeId", "name", "salary")
            .build();
    }
    @Bean
	public DataSourceTransactionManager transactionManager(DataSource data) {
		return new DataSourceTransactionManager(data);
	}

    @Bean
    public Step exportStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager, 
            JdbcCursorItemReader<Employee> reader, FlatFileItemWriter<Employee> writer) {
        return new StepBuilder("exportCsvStep", jobRepository)
            .<Employee, Employee>chunk(10, transactionManager)
            .reader(reader)
            .writer(writer)
            .build();
    }
    

    @Bean
    public Job exportUserJob(JobRepository jobRepository, Step exportStep, JobCompletionNotificationListener completionNotificationListener) {
        return new JobBuilder("exportUserJob", jobRepository)
            .listener(completionNotificationListener)
            .start(exportStep)
            .build();
    }
}