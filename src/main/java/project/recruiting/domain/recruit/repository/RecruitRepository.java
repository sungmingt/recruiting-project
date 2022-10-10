package project.recruiting.domain.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.recruiting.domain.recruit.Recruit;

import java.util.List;

//@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {

//    List<Recruit> search(String content);

    //recruitId 컬럼만 뽑아오기 (id 추출 작업이 필요하다)
    @Query(value = "select id from recruit where company_id = :companyId", nativeQuery = true)
    List<Long>findByCompanyId(@Param("companyId") Long companyId);

}
