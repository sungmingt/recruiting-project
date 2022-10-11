package project.recruiting.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.recruiting.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
