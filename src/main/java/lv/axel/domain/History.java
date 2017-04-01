package lv.axel.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "date")
    private Date date;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "action")
    private String action;
    @Column(name = "description")
    private String description;

    public History(Date date, String userName, String action, String description) {
        this.date = date;
        this.userName = userName;
        this.action = action;
        this.description = description;
    }
}
