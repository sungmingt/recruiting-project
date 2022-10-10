package project.recruiting.domain.recruit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.recruiting.domain.company.entity.Company;

import javax.persistence.*;

import java.util.Optional;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Recruit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)  ////
    @JoinColumn(name = "company_id")
    private Company company;

    private String position;
    private Long reward;
    private String content;
    private String tool;

    public Recruit(String position, Long reward, String content, String tool) {
        this.position = position;
        this.reward = reward;
        this.content = content;
        this.tool = tool;
    }


    //=========================


    public void update(String position, Long reward, String content, String tool) {

        Optional.ofNullable(position)
                .ifPresent(p -> this.position = p);
        Optional.ofNullable(reward)
                .ifPresent(r -> this.reward = r);
        Optional.ofNullable(content)
                .ifPresent(c -> this.content = c);
        Optional.ofNullable(tool)
                .ifPresent(t -> this.tool = t);
    }
}
