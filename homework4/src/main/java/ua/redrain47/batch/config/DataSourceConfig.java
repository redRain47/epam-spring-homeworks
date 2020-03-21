package ua.redrain47.batch.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import ua.redrain47.batch.model.User;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    @Bean
    public RowMapper<User> rowMapper() {
        return (resultSet, rowNum) -> {
            Long userId = resultSet.getLong("id");
            String name = resultSet.getString("fullName");
            String email = resultSet.getString("email");
            BigDecimal balance = resultSet.getBigDecimal("balance");
            return new User(userId, name, email, balance);
        };
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        Properties props = new Properties();

        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader("./src/main/resources/" +
                            "application.properties"));
            props.load(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataSource.setUrl(props.getProperty("spring.datasource.url"));
        dataSource.setUsername(props.getProperty("spring.datasource.username"));
        dataSource.setPassword(props.getProperty("spring.datasource.password"));
        return dataSource;
    }
}
