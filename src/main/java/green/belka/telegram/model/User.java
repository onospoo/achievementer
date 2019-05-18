package green.belka.telegram.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class User {

    @Setter
    @Getter
     private Long id;

    @Setter
    @Getter
     private List<Achievement> achievements;

    @Setter
    @Getter
    private Role role;

    @Getter
    @Setter
    private String nickname;

    @Getter
    @Setter
    private String first_name;

    @Getter
    @Setter
    private String last_name;

    @Getter
    @Setter
    private Long score;
}