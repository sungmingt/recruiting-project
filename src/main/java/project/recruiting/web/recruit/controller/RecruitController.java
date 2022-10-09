package project.recruiting.web.recruit.controller;

import org.springframework.web.bind.annotation.*;
import project.recruiting.web.recruit.dto.*;

@RestController
public class RecruitController {


    /**
     * 채용공고 등록
     * @require 회사_id, position, reward, content, tool
     */
    @PostMapping
    public RegisterResponse register(@RequestBody RegisterRequest request) {

        recruitService.register(request);

        return new RegisterResponse();
    }

    /**
     * 채용공고 수정
     * @require position, reward, content, tool
     */
    @PostMapping("/{recruitId}")
    public UpdateResponse update(@PathVariable long recruitId,
                                 @RequestBody UpdateRequest request) {

        recruitService.update(request);
        return new UpdateResponse();
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
    public ListResponse getList() {

        recruitService.getList();
        return new MultiResponse(listResponse);
    }

    /**
     * 채용공고 검색
     * @return recruit_id, company_name, country, city, position, reward, tool
     */
    @GetMapping
    public ListResponse search(@RequestParam String content) {  //todo : 검색 조건 추가

        recruitService.search(content);
        return new MultiResponse(listResponse);
    }

    /**
     * 채용공고 상세 페이지
     * @return recruit_id, company_name, country, city, position, reward, tool, content, 해당 회사가 올린 다른 채용공고 (id List)
     */
    @GetMapping("/{recruitId}")
    public SingleResponse getSingle(@PathVariable long recruitId) {

        recruitService.getSingle(recruitId);
        return new SingleResponse;
    }

    /**
     * 채용공고 지원
     * @require recruit_id, user_id
     */
    @PostMapping("/apply")
    public String apply(@RequestBody ApplyDto applyDto) {

        recruitService.apply(applyDto);
        return "지원 등록 되었습니다!";
    }
}
