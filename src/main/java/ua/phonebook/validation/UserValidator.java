package ua.phonebook.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.phonebook.model.entity.User;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    private Pattern usernamePattern = Pattern.compile("[A-z]{3,}");
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if(!usernamePattern.matcher(user.getUsername()).matches()){
            errors.rejectValue("username", "username", "Логин минимум 3 латинских символа");
        }
        if(user.getPassword().trim().isEmpty() || user.getPassword().length() < 5){
            errors.rejectValue("password", "password", "Длинна пароля минимум 5 символов");
        }
        if(user.getFio().trim().isEmpty() || user.getFio().length() < 5){
            errors.rejectValue("fio", "fio", "ФИО минимум 5 символов");
        }
    }
}
