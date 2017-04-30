package lv.axel.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class UserDTO {
    @Size(min = 3,message = "Логин должен быть больше 3 символов")
    private String name;
    @Size(min = 3,message = "Пароль должен быть больше 3 символов")
    private String password;
}
