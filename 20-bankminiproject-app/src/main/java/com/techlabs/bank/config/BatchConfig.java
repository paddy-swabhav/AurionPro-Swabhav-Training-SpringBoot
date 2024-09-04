//import javax.sql.DataSource;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.item.database.JdbcCursorItemReader;
//import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
//import org.springframework.batch.item.file.FlatFileItemWriter;
//import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.batch.core.step.tasklet.TaskletStep;
//import org.springframework.batch.repeat.RepeatContext;
//
//import com.techlabs.bank.entity.EmailDetails;
//import com.techlabs.bank.entity.Transaction;
//import com.techlabs.bank.repository.AccountRepository;
//import com.techlabs.bank.service.EmailService;
//
//@Configuration
//@EnableBatchProcessing
//public class BatchConfig {
//
//    @Autowired
//    private EmailService emailService;
//    
//    @Autowired
//    private AccountRepository accountRepository;
//    
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Bean
//    public JdbcCursorItemReader<Transaction> databaseReader(DataSource dataSource) {
//        return new JdbcCursorItemReaderBuilder<Transaction>()
//            .dataSource(dataSource)
//            .name("transactionJdbcCursorItemReader")
//            .sql("SELECT transaction_id, amount, date, type, receiver_account_number FROM transactions WHERE sender_account_number = :accountNumber OR receiver_account_number = :accountNumber")
//            .beanRowMapper(Transaction.class)
//            .build();
//    }
//
//    @Bean
//    public FlatFileItemWriter<Transaction> csvWriter() {
//        return new FlatFileItemWriterBuilder<Transaction>()
//            .name("transactionCsvWriter")
//            .resource(new FileSystemResource("src/main/resources/Transaction_Statement.csv"))
//            .delimited()
//            .delimiter(",")
//            .names("Transaction_Id", "Amount", "Date", "Transaction_Type", "Receiver_Account")
//            .build();
//    }
//
//    @Bean
//    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean
//    public Step exportStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager, 
//            JdbcCursorItemReader<Transaction> reader, FlatFileItemWriter<Transaction> writer) {
//        return new StepBuilder("exportCsvStep", jobRepository)
//            .<Transaction, Transaction>chunk(20, transactionManager)
//            .reader(reader)
//            .writer(writer)
//            .build();
//    }
//
//    @Bean
//    public Step emailStep(JobRepository jobRepository) {
//        return new StepBuilder("emailStep", jobRepository)
//            .tasklet(emailTasklet())
//            .build();
//    }
//
//    @Bean
//    public Tasklet emailTasklet() {
//        return (contribution, chunkContext) -> {
//            // Extracting the account number from JobParameters
//            JobParameters jobParameters = chunkContext.getStepContext().getJobParameters();
//            Long accountNumber = jobParameters.getLong("accountNumber");
//
//            // Fetching email and sending the statement
//            String email = accountRepository.findById(accountNumber).get().getCustomer().getEmail();
//            emailService.SendStatementMail(new EmailDetails(email, "Your Transaction Statement", "Please find the attached transaction statement.", "src/main/resources/Transaction_Statement.csv"));
//
//            return RepeatStatus.FINISHED;
//        };
//    }
//
//    @Bean
//    public Job exportUserJob(JobRepository jobRepository, Step exportStep, Step emailStep, JobCompletionNotificationListener completionNotificationListener) {
//        return new JobBuilder("exportUserJob", jobRepository)
//            .listener(completionNotificationListener)
//            .start(exportStep)
//            .next(emailStep)
//            .build();
//    }
//    
//    // Method to run the job with the account number as a parameter
//    public void runJobWithAccountNumber(long accountNumber) throws Exception {
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addLong("accountNumber", accountNumber)
//                .addLong("time", System.currentTimeMillis())
//                .toJobParameters();
//
//        jobLauncher.run(exportUserJob(jobRepository, exportStep(jobRepository, transactionManager(dataSource), databaseReader(dataSource), csvWriter()), emailStep(jobRepository), completionNotificationListener), jobParameters);
//
//    }
//}
