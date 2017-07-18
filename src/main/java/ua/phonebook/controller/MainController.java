package ua.phonebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SpringSessionContext;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.phonebook.dao.UserDAO;
import ua.phonebook.model.Page;
import ua.phonebook.model.entity.Contact;
import ua.phonebook.model.entity.User;
import ua.phonebook.service.ContactService;
import ua.phonebook.service.UserService;
import ua.phonebook.validation.ContactValidator;
import ua.phonebook.validation.UserValidator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class MainController {

    private final static String PARAM_NAME_FIELD = "field";
    private final static String PARAM_NAME_SEARCH = "search";

    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;

    @Autowired
    private ContactValidator contactValidator;
    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(HttpSession session){
        session.removeAttribute(PARAM_NAME_SEARCH);
        session.removeAttribute(PARAM_NAME_FIELD);
        return "redirect:/phonebook";
    }

    @RequestMapping(value = "/phonebook", method = RequestMethod.GET)
    public String phoneBookPage(@RequestParam(defaultValue = "1") Integer pageNumber,
                                @RequestParam(defaultValue = "5") Integer row,
                                ModelMap model, HttpSession session, Authentication authentication){
        String search = (String) session.getAttribute(PARAM_NAME_SEARCH);
        String field = (String) session.getAttribute(PARAM_NAME_FIELD);

        Page page = contactService.getPage(pageNumber, row, authentication.getName(), search, field);

        model.addAttribute("page", page);
        return "phonebook";
    }

    @RequestMapping(value = "/phonebook", method = RequestMethod.POST)
    public String phoneBookPage(@RequestParam String search,@RequestParam String field, HttpSession session){
        session.setAttribute(PARAM_NAME_SEARCH, search);
        session.setAttribute(PARAM_NAME_FIELD, field);
        return "redirect:/phonebook";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String updateContact(@RequestParam Long id, ModelMap model){
        Contact contact = contactService.getContactById(id);
        model.addAttribute("contact", contact);
        return "form";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updateContact(@ModelAttribute Contact contact, BindingResult bindingResult){
        contactValidator.validate(contact, bindingResult);
        if(bindingResult.hasErrors()){
            return "form";
        }
        contactService.updateContact(contact);
        return "redirect:/";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createContact(ModelMap model){
        model.addAttribute("contact", new Contact());
        return "form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createContact(@ModelAttribute Contact contact, BindingResult bindingResult){
        contactValidator.validate(contact, bindingResult);
        if(bindingResult.hasErrors()){
            return "form";
        }

        contactService.createContact(contact);
        return "redirect:/phonebook";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteContact(@RequestParam Long id){
        contactService.deleteContact(id);
        return "redirect:/phonebook";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationNewUser(ModelMap model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationNewUser(@ModelAttribute User user, BindingResult bindingResult){
        userValidator.validate(user, bindingResult);
        if(!bindingResult.hasErrors()){
            userService.createUser(user, bindingResult);
            if(bindingResult.hasErrors()) {
                return "registration";
            }
            return "redirect:/login";
        }
        return "registration";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error, Model model){
        if(error != null ) model.addAttribute("error", "Не верный логин или пароль");

        return "login";
    }

}
