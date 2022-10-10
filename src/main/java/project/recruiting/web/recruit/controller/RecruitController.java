package project.recruiting.web.recruit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.recruiting.domain.recruit.service.RecruitService;
import project.recruiting.web.recruit.dto.request.ApplyRequest;
import project.recruiting.web.recruit.dto.request.RegisterRequest;
import project.recruiting.web.recruit.dto.request.UpdateRequest;
import project.recruiting.web.recruit.dto.response.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecruitController {

    private final RecruitService recruitService;

    /**
     * 채용공고 등록
     * @require 회사_id, position, reward, content, tool
     */
    @PostMapping
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return recruitService.register(request);
    }

    /**
     * 채용공고 수정
     * @require position, reward, content, tool
     */
    @PostMapping("/{recruitId}")
    public UpdateResponse update(@PathVariable long recruitId,
                                 @RequestBody UpdateRequest request) {
        return recruitService.update(recruitId, request);
    }

    /**
     * 채용공고 삭제
     * @require recruit_id
     */
    @DeleteMapping("/{recruitId}")
    public String delete(@PathVariable long recruitId) {

        recruitService.delete(recruitId);
        return "채용공고가 삭제되었습니다.";
    }

    /**
     * 채용공고 목록
     * @return recruit_id, company_name, country, city, position, reward, tool
     */
    @GetMapping
    public MultiResponse getList() {

        List<ListResponse> response = recruitService.getList();
        return new MultiResponse(response);
    }

    /**
     * 채용공고 검색
     * @return recruit_id, company_name, country, city, position, reward, tool
     */
    @GetMapping
    public ListResponse search(@RequestParam String content) {  //todo : 검색 조건 추가

        recruitService.search(content);
//        return new MultiResponse(listResponse);
        return new ListResponse();
    }


    /**
     * 채용공고 상세 페이지
     * @return recruit_id, company_name, country, city, position, reward, tool, content, 해당 회사가 올린 다른 채용공고 (id List)
     */
    @GetMapping("/{recruitId}")
    public SingleResponse getSingle(@PathVariable long recruitId) {
        return recruitService.getSingle(recruitId);
    }

    /**
     * 채용공고 지원
     * @require recruit_id, user_id
     */
    @PostMapping("/apply")
    public String apply(@RequestBody ApplyRequest applyDto) {

        recruitService.apply(applyDto);
        return "지원 등록 되었습니다!";
    }
}
