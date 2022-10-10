package project.recruiting.domain.recruit.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.recruiting.domain.recruit.Recruit;
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


    /**
     * 등록
     */
    public RegisterResponse register(RegisterRequest request) {

        Recruit recruit = request.toEntity();
        Recruit saved = recruitRepository.save(recruit);

        return toRegisterResponse(saved);
    }

    /**
     * 수정
     */
    public UpdateResponse update(long recruitId, UpdateRequest request) {

        Recruit recruit = recruitRepository.findById(recruitId)
                .orElseThrow(() -> new RuntimeException("해당 채용공고가 존재하지 않습니다."));  //todo : -> custom exception

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
     * @return
     */
    @Transactional(readOnly = true)
    public ListResponse search(String content) { //todo : 검색 조건 여러개 적용 여부


        return new ListResponse();
    }

    /**
     * 상세
     * @param recruitId
     * @return
     */
    @Transactional(readOnly = true)  //todo : fetch join
    public SingleResponse getSingle(long recruitId) {

        Recruit recruit = recruitRepository.findById(recruitId)
                .orElseThrow(() -> new RuntimeException("해당 채용공고가 존재하지 않습니다."));  //todo : custom exception

        List<Long> others = recruitRepository.findByCompanyId(recruit.getCompany().getId());
        return toSingleResponse(recruit, others);
    }

    /**
     * 지원
     * @param applyDto
     */
    public void apply(ApplyRequest applyDto) {


    }
}
