package project.recruiting.domain.recruit.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.recruiting.domain.company.entity.Company;
import project.recruiting.domain.member.entity.Member;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.*;
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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "recruit")
    private List<Member> memberList = new ArrayList<>();

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

    public Recruit(Long id, Company company, String position, Long reward, String content, String tool) {
        this.id = id;
        this.company = company;
        this.position = position;
        this.reward = reward;
        this.content = content;
        this.tool = tool;
    }

    public Recruit(Company company, String position, Long reward, String content, String tool) {
        this.company = company;
        this.position = position;
        this.reward = reward;
        this.content = content;
        this.tool = tool;
    }

    //=========================

    public void setCompany(Company company) {
        this.company = company;
        company.getRecruitList().add(this);
    }

    public void update(String position, Long reward, String content, String tool) {

        ofNullable(position)
                .ifPresent(p -> this.position = p);
        ofNullable(reward)
                .ifPresent(r -> this.reward = r);
        ofNullable(content)
                .ifPresent(c -> this.content = c);
        ofNullable(tool)
                .ifPresent(t -> this.tool = t);
    }
}
