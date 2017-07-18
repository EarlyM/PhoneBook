package ua.phonebook.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.phonebook.dao.ContactDAO;
import ua.phonebook.dao.UserDAO;
import ua.phonebook.dao.filestorage.ContactFileStorage;
import ua.phonebook.dao.filestorage.UserFileStorage;

import java.io.File;
import java.io.IOException;

@Configuration
@ConditionalOnProperty(prefix = "dataSource", name = "enabled", havingValue = "false")
public class FileStorageConfig {

    @Value("${filePath.user}")
    String userFilePath;

    @Value("${filePath.contact}")
    String contactFilePath;


    @Bean
    public ContactDAO getContactDAO() throws IOException {
        File file = new File(contactFilePath);
        if(!file.exists()){
            file.createNewFile();
        }
        return new ContactFileStorage(file);
    }

    @Bean
    public UserDAO getUserDAO() throws IOException {
        File file = new File(userFilePath);
        if(!file.exists()){
            file.createNewFile();
        }
        return new UserFileStorage(file);
    }
}
