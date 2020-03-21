package ua.redrain47.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import ua.redrain47.batch.model.User;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("ua.redrain47.batch")
@EnableBatchProcessing
public class BatchConfig {
    // email credentials are not real
    private static final String EMAIL = "uuupppsss@gmail.com";
    private static final String PASSWORD = "1_2_3_4";
    private static final String sqlQuery = "SELECT u.id, u.fullName, u.email, u.balance FROM users u;";
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public ItemReader<User> itemReader(DataSource dataSource, RowMapper<User> rowMapper) {
        JdbcCursorItemReader<User> itemReader = new JdbcCursorItemReader<>();
        itemReader.setDataSource(dataSource);
        itemReader.setSql(sqlQuery);
        itemReader.setRowMapper(rowMapper);
        return itemReader;
    }

    @Bean
    public ItemProcessor<User, User> itemProcessor() {
        return user -> user.isLowBalance() ? user : null;
    }

    @Bean
    public ItemWriter<User> itemWriter(JavaMailSender javaMailSender) {
        return new NotificationWriter(javaMailSender);
    }

    @Bean
    public Step step(ItemReader<User> itemReader,
                     ItemProcessor<User, User> itemProcessor,
                     ItemWriter<User> itemWriter) {
        return stepBuilderFactory.get("step")
                .<User, User>chunk(5)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public Job userJob(Step step) {
        return jobBuilderFactory.get("userJob")
                .incrementer(new RunIdIncrementer())
                .flow((step))
                .end()
                .build();
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(EMAIL);
        mailSender.setPassword(PASSWORD);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
