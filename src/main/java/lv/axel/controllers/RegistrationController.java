package lv.axel.controllers;

import lv.axel.domain.User;
import lv.axel.model.UserDTO;
import lv.axel.services.UserService;
import lv.axel.services.dtoConverters.UserDTOConverter;
import lv.axel.services.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;;
import java.util.List;
import java.util.Map;

@RestController
public class RegistrationController {

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "admin/createNewUser", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {

        userValidator.validate(userDTO, result);
        if (result.hasErrors()) {
            return new ResponseEntity<>(getErrorMap(result), HttpStatus.CONFLICT);
        }
        User user = UserDTOConverter.convertToEntity(userDTO);
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Map<String, List<String>> getErrorMap(BindingResult result) {
        Map<String, List<String>> errors = new HashMap<>();
        for (FieldError error : result.getFieldErrors()) {
            String errorFieldName = error.getField();
            if (errors.containsKey(errorFieldName)) {
                errors.get(error.getField()).add(error.getDefaultMessage());
            } else {
                List<String> errList = new ArrayList<>();
                errList.add(error.getDefaultMessage());
                errors.put(errorFieldName, errList);
            }
        }
        return errors;
    }
}




