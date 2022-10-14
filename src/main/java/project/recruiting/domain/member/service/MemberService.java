package project.recruiting.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.recruiting.config.exception.BusinessException;
import project.recruiting.domain.member.entity.Member;
import project.recruiting.domain.member.repository.MemberRepository;

import static project.recruiting.config.exception.ErrorCode.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * recruitService 호출용 -> Entity 반환
     */
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));
    }
}
