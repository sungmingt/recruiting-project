package project.recruiting.domain.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.recruiting.domain.recruit.entity.Recruit;

import java.util.List;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {

    //일단 요구조건대로 작성, 검색방식 변경여부 확인
    @Query(value = "select * from recruit join company on recruit.company_id = company.id" +
            " where company.name like %:content%" +
            " or company.city like %:content%" +
            " or recruit.position like %:content%" +
            " or recruit.tool like %:content%", nativeQuery = true)
    List<Recruit> search(@Param("content") String content);

    //특정 회사의 다른 채용공고 id 조회
    @Query(value = "select id from recruit where company_id = :companyId", nativeQuery = true)
    List<Long> findByCompanyId(@Param("companyId") Long companyId);

}
