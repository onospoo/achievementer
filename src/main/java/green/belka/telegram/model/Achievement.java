package green.belka.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


public class Achievement {

    @Setter
    @Getter

    private Long id;

    @Setter
    @Getter
    private List<User> users;

    @Getter
    @Setter
    private Long authorId;

    @Setter
    @Getter
    private String description;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Long cost;

    @Getter
    @Setter
    private Long status;

    @Getter
    @Setter
    @JsonIgnore
    private LocalDate create_date;

    @Getter
    @Setter
    private Long achievement_limit;

}
//	@OneToOne(optional = false, mappedBy="id")
//    private User author;

    /*
    @Setter
    @Getter
    private Long limit;

    @Setter
    @Getter
    private LocalDate createDate;

    @PreUpdate
    protected void onUpdate() {
        createDate = LocalDate.now();
    }

    @Setter
    @Getter
    private LocalDate finishDate;
    */
