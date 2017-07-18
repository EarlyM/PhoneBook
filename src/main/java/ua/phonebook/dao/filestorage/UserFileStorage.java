package ua.phonebook.dao.filestorage;

import org.springframework.beans.factory.annotation.Autowired;
import ua.phonebook.dao.UserDAO;
import ua.phonebook.model.entity.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class UserFileStorage implements UserDAO {

    private File userFile;

    public UserFileStorage(File userFilePath) throws IOException {
        userFile = userFilePath;
    }

    @Override
    public User findUserByName(String name) {
        String temp;
        String[] userInfo;
        try(BufferedReader bufferedReader = Files.newBufferedReader(userFile.toPath())) {
            while ((temp = bufferedReader.readLine()) != null) {
                userInfo = temp.split(",");
                if (name.equals(userInfo[0])) {
                    User user = new User();
                    user.setUsername(userInfo[0]);
                    user.setPassword(userInfo[1]);
                    user.setRole(userInfo[2]);
                    user.setFio(userInfo[3]);
                    return user;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

        @Override
    public void createUser(User user) {
        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(userFile.toPath(), StandardOpenOption.APPEND)) {
            bufferedWriter.write(user.toString());
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
