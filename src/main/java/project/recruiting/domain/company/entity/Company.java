package project.recruiting.domain.company.entity;

import lombok.Getter;
import project.recruiting.domain.recruit.Recruit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.*;

@Entity
@Getter
public class Company {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany
    private List<Recruit> recruitList = new ArrayList<>();

    private String name;
    private String country;
    private String city;
}
