package ua.phonebook.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.phonebook.model.entity.Contact;
import java.util.regex.Pattern;

@Component
public class ContactValidator implements Validator {

    Pattern phonePattern = Pattern.compile("\\+38\\d{10}");
    Pattern emailPattern = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
    @Override
    public boolean supports(Class<?> aClass) {
        return Contact.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Contact contact = (Contact) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "surname", "Должно быть больше 4 и больше символов");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","name", "Должно быть больше 4 и больше символов");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "patronymic", "patronymic", "Должно быть больше 4 и больше символов");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobilePhone","mobilePhone", "Не верный формат. Пример: +380669999999");

        if(contact.getSurname().length() < 4) errors.rejectValue("surname", "surname", "Должно быть больше 4 и больше символов");
        if(contact.getName().length() < 4) errors.rejectValue("name","name", "Должно быть больше 4 и больше символов");
        if(contact.getPatronymic().length() < 4) errors.rejectValue("patronymic", "patronymic", "Должно быть больше 4 и больше символов");
        if(!phonePattern.matcher(contact.getMobilePhone()).matches()) errors.rejectValue("mobilePhone","mobilePhone", "Не верный формат. Пример: +380669999999");
        if(!contact.geteMail().isEmpty() && !emailPattern.matcher(contact.geteMail()).matches()) errors.rejectValue("eMail", "eMail", "Не действительная почта");

    }
}
