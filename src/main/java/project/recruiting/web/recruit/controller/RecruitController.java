package project.recruiting.web.recruit.controller;

import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/recruit")
public class RecruitController {

    private final RecruitService recruitService;

    @Operation(summary = "채용공고 등록")
    @PostMapping
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return recruitService.register(request);
    }

    @Operation(summary = "채용공고 수정")
    @PostMapping("/{recruitId}")
    public UpdateResponse update(@PathVariable long recruitId,
                                 @RequestBody UpdateRequest request) {
        return recruitService.update(recruitId, request);
    }

    @Operation(summary = "채용공고 삭제")
    @DeleteMapping("/{recruitId}")
    public String delete(@PathVariable long recruitId) {

        recruitService.delete(recruitId);
        return "채용공고가 삭제되었습니다.";
    }

    @Operation(summary = "채용공고 목록")
    @GetMapping
    public List<ListResponse> getList() {
        return recruitService.getList();  //todo : 리스트 dto로 한번 더 감싸야 할지
    }

    @Operation(summary = "채용공고 검색")
    @GetMapping("/search")
    public List<ListResponse> search(@RequestParam String content) {  //todo : 검색 조건 추가
        return recruitService.search(content);
    }

    @Operation(summary = "채용공고 상세 페이지")
    @GetMapping("/{recruitId}")
    public SingleResponse getSingle(@PathVariable long recruitId) {
        return recruitService.getSingle(recruitId);
    }

    @Operation(summary = "채용공고 지원")
    @PostMapping("/apply")
    public String apply(@RequestBody ApplyRequest applyDto) {

        recruitService.apply(applyDto);
        return "지원 등록 되었습니다!";
    }
}
