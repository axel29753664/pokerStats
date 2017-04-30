package lv.axel.domain;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "users")
@EqualsAndHashCode(exclude = "users")
@Entity
@Table(name = "Roles")
public class Role {
    @Id
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(String name) {
        this.name = name;
    }
}
