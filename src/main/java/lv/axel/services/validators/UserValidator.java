package lv.axel.services.validators;

import lv.axel.domain.User;
import lv.axel.model.UserDTO;
import lv.axel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO user = (UserDTO) o;

        User userFromDB = userService.getUserByName(user.getName());
        if ((userFromDB != null) && (userFromDB.getName().equals(user.getName()))) {
            errors.rejectValue("name", "", "Логин " + user.getName() + " уже занят !");
        }
    }

}
