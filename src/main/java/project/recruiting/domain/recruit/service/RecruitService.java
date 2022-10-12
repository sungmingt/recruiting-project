package project.recruiting.domain.recruit.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.recruiting.domain.company.entity.Company;
import project.recruiting.domain.company.service.CompanyService;
import project.recruiting.domain.member.entity.Member;
import project.recruiting.domain.member.service.MemberService;
import project.recruiting.domain.recruit.entity.Recruit;
import project.recruiting.domain.recruit.repository.RecruitRepository;
import project.recruiting.web.recruit.dto.request.ApplyRequest;
import project.recruiting.web.recruit.dto.request.RegisterRequest;
import project.recruiting.web.recruit.dto.request.UpdateRequest;
import project.recruiting.web.recruit.dto.response.ListResponse;
import project.recruiting.web.recruit.dto.response.RegisterResponse;
import project.recruiting.web.recruit.dto.response.SingleResponse;
import project.recruiting.web.recruit.dto.response.UpdateResponse;

import java.util.List;

import static project.recruiting.web.recruit.dto.response.ListResponse.toListResponse;
import static project.recruiting.web.recruit.dto.response.RegisterResponse.*;
import static project.recruiting.web.recruit.dto.response.SingleResponse.*;
import static project.recruiting.web.recruit.dto.response.UpdateResponse.*;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RecruitService {

    private final RecruitRepository recruitRepository;
    private final CompanyService companyService;
    private final MemberService memberService;

    /**
     * 등록
     */
    public RegisterResponse register(RegisterRequest request) {

        Company company = companyService.findCompany(request.getCompanyId());
        Recruit recruit = request.toEntity();

        recruit.setCompany(company);

        Recruit saved = recruitRepository.save(recruit);
        return toRegisterResponse(saved);
    }

    /**
     * 수정
     */
    public UpdateResponse update(long recruitId, UpdateRequest request) {

        Recruit recruit = findById(recruitId);

        recruit.update(request.getPosition(), request.getReward(), request.getContent(), request.getTool());
        return toUpdateResponse(recruit);
    }

    /**
     * 삭제
     */
    public void delete(long recruitId) {
        recruitRepository.deleteById(recruitId);
    }

    /**
     * 목록
     */
    @Transactional(readOnly = true)
    public List<ListResponse> getList() {  //todo : 페이징 적용 여부, fetch join
        return toListResponse(recruitRepository.findAll());
    }

    /**
     * 검색
     * @param content
     */
    @Transactional(readOnly = true)
    public List<ListResponse> search(String content) { //todo : 검색 조건 여러개 적용 여부

        List<Recruit> recruitList = recruitRepository.search(content);
        return toListResponse(recruitList);
    }

    /**
     * 상세
     * @param recruitId
     */
    @Transactional(readOnly = true)  //todo : fetch join
    public SingleResponse getSingle(long recruitId) {

        Recruit recruit = findById(recruitId);
        List<Long> others = recruitRepository.findByCompanyId(recruit.getCompany().getId());

        return toSingleResponse(recruit, others);
    }

    /**
     * 지원
     * @param applyDto
     */
    public void apply(ApplyRequest applyDto) {

        Member member = memberService.findMember(applyDto.getUserId());

        if (member.getRecruit() != null) {
            throw new RuntimeException("이미 지원하신 채용공고가 존재합니다.");
        }

        Recruit recruit = findById(applyDto.getRecruitId());
        member.setRecruit(recruit); //dirty check
    }

    /**
     * 조회 재사용 메서드
     */
    private Recruit findById(Long recruitId) {
        return recruitRepository.findById(recruitId)
                .orElseThrow(() -> new RuntimeException("해당 채용공고가 존재하지 않습니다.")); //todo : custom exception
    }
}
