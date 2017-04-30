package lv.axel.services.dtoConverters;

import lv.axel.domain.User;
import lv.axel.model.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDTOConverter {
    public static User convertToEntity(UserDTO userDTO){
        return new User(userDTO.getName(), new BCryptPasswordEncoder().encode( userDTO.getPassword()));
    }
}
