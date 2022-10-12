package project.recruiting.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.recruiting.domain.recruit.entity.Recruit;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;

    //요구사항 조건 : 사용자는 1회만 지원 가능하다 -> N:1
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member(String name) {
        this.name = name;
    }

    //==============================

    public void setRecruit(Recruit recruit) {
        this.recruit = recruit;
        recruit.getMemberList().add(this);
    }
}
