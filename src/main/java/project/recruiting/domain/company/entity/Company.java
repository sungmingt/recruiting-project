package project.recruiting.domain.company.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import project.recruiting.domain.recruit.entity.Recruit;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Company {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "company", cascade = REMOVE)
    private List<Recruit> recruitList = new ArrayList<>();

    private String name;
    private String country;
    private String city;

    public Company(Long id, String name, String country, String city) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
    }

    public Company(String name, String country, String city) {
        this.name = name;
        this.country = country;
        this.city = city;
    }
}
