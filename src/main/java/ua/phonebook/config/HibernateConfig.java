package ua.phonebook.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.phonebook.dao.ContactDAO;
import ua.phonebook.dao.UserDAO;
import ua.phonebook.dao.database.ContactDBImpl;
import ua.phonebook.dao.database.UserDBImpl;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ConditionalOnProperty(prefix = "dataSource", name = "enabled", havingValue = "true", matchIfMissing = true)
public class HibernateConfig {

    @Value("${dataSource.driverClassName}")
    private String driver;

    @Value("${dataSource.url}")
    private String url;

    @Value("${dataSource.username}")
    private String username;

    @Value("${dataSource.password}")
    private String password;

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;


    @Bean
    public DataSource dataSource(){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        return new HikariDataSource(config);
    }

    @Bean
    public SessionFactory sessionFactory() throws IOException {
        Properties properties = new Properties();

        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);

        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setPackagesToScan("ua.phonebook.model.entity");
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setHibernateProperties(properties);
        sessionFactoryBean.afterPropertiesSet();

        SessionFactory sessionFactory = sessionFactoryBean.getObject();
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() throws IOException {
        HibernateTransactionManager tx = new HibernateTransactionManager(sessionFactory());
        return tx;
    }

    @Bean
    public ContactDAO getContactDAO(){
        return new ContactDBImpl();
    }

    @Bean
    public UserDAO getUserDAO(){
        return new UserDBImpl();
    }

}
