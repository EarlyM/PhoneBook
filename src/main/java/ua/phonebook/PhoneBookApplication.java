package ua.phonebook;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class PhoneBookApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PhoneBookApplication.class);
    }

    public static void main(String[] args) throws Exception {
        String propertiesPath = "";
        for(String arg : args){
            String[] param = arg.split("=");
            if(param[0].equals("-Dlardi.conf")){
                propertiesPath = param[1];
                break;
            }
        }
        new SpringApplicationBuilder(PhoneBookApplication.class).properties("spring.config.location=" + propertiesPath).run(args);
	}
}
