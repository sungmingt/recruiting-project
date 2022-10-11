package project.recruiting.slice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import project.recruiting.domain.company.entity.Company;
import project.recruiting.domain.company.repository.CompanyRepository;
import project.recruiting.domain.recruit.entity.Recruit;
import project.recruiting.domain.recruit.repository.RecruitRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RecruitRepositoryTest {

    @Autowired
    private RecruitRepository recruitRepository;
    @Autowired
    private CompanyRepository companyRepository;

    Company naver = new Company("네이버", "한국", "서울");
    Company google = new Company ("구글", "미국", "샌프란시스코");

    Recruit recruit1 = new Recruit(naver, "백엔드", 10000L, "백엔드 java 개발자 채용", "java");//todo : 생성자 관련 리팩토링
    Recruit recruit2 = new Recruit(google, "프론트엔드", 15000L, "프론트엔드 개발자 채용", "javascript");
    Recruit recruit3 = new Recruit(naver, "데브옵스", 20000L, "데브옵스 개발자 채용", "AWS");

    @BeforeEach
    void beforeEach() {
        companyRepository.save(naver);
        companyRepository.save(google);

        recruitRepository.save(recruit1);
        recruitRepository.save(recruit2);
        recruitRepository.save(recruit3);
    }

    @DisplayName("회사의 다른 채용공고 id 불러오기")
    @Test
    void getOthers() {
        //when
        List<Long> others = recruitRepository.findByCompanyId(naver.getId());

        //then
        assertThat(others).hasSize(2);
        assertThat(others).containsAll(List.of(recruit1.getId(), recruit3.getId()));
    }

    @DisplayName("채용공고 검색")
    @Test
    void search() {
        //when
        List<Recruit> recruitList = recruitRepository.search("네이버");
        List<Recruit> recruitList2 = recruitRepository.search("java");
        List<Recruit> recruitList3 = recruitRepository.search("AWS");

        //then
        assertThat(recruitList).hasSize(2);
        assertThat(recruitList2).hasSize(2);
        assertThat(recruitList3).hasSize(1);
    }
}
