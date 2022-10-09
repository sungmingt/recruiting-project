package project.recruiting.domain.recruit;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@Entity
@Getter
public class Recruit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "company_id")
    private String position;
    private Long reward;
    private String tool;
    private String content;

}
