package project.recruiting.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.recruiting.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "select m from Member m left join fetch m.recruit where m.id = :memberId")
    Optional<Member> findById(@Param("memberId") Long memberId);
}
