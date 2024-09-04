package com.techlabs.bank.config;

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


import com.techlabs.bank.entity.Transaction;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Bean
    public JdbcCursorItemReader<Transaction> databaseReader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Transaction>()
            .dataSource(dataSource)
            .name("transactionJdbcCursorItemReader")
            .sql("SELECT transaction_id, amount, date,type,receiver_account_number FROM transactions")
            .beanRowMapper(Transaction.class)
            .build();
    }

    @Bean
    public FlatFileItemWriter<Transaction> csvWriter() {
        return new FlatFileItemWriterBuilder<Transaction>()
            .name("transactionCsvWriter")
            .resource(new FileSystemResource("src/main/resources/Transaction_Statement.csv"))
            .delimited()
            .delimiter(",")
            .names("Transaction_Id", "Amount", "Date","Transaction_Type","Receiver_Account")
            .build();
    }
    @Bean
	public DataSourceTransactionManager transactionManager(DataSource data) {
		return new DataSourceTransactionManager(data);
	}

    @Bean
    public Step exportStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager, 
            JdbcCursorItemReader<Transaction> reader, FlatFileItemWriter<Transaction> writer) {
        return new StepBuilder("exportCsvStep", jobRepository)
            .<Transaction, Transaction>chunk(20, transactionManager)
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
