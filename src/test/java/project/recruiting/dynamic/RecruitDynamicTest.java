package project.recruiting.dynamic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.recruiting.config.exception.ErrorCode;
import project.recruiting.domain.company.entity.Company;
import project.recruiting.domain.company.repository.CompanyRepository;
import project.recruiting.domain.member.entity.Member;
import project.recruiting.domain.member.repository.MemberRepository;
import project.recruiting.domain.member.service.MemberService;
import project.recruiting.domain.recruit.entity.Recruit;
import project.recruiting.domain.recruit.repository.RecruitRepository;
import project.recruiting.domain.recruit.service.RecruitService;
import project.recruiting.web.recruit.dto.request.ApplyRequest;
import project.recruiting.web.recruit.dto.request.RegisterRequest;
import project.recruiting.web.recruit.dto.request.UpdateRequest;
import project.recruiting.web.recruit.dto.response.ListResponse;
import project.recruiting.web.recruit.dto.response.SingleResponse;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static project.recruiting.config.exception.ErrorCode.*;

@SpringBootTest
class RecruitDynamicTest {

    @Autowired
    private RecruitService recruitService;

    @Autowired
    private RecruitRepository recruitRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @DisplayName("채용공고 동작 수행")
    @TestFactory
    Stream<DynamicTest> recruitTest(){
        final Company company1 = new Company(1L, "네이버", "한국", "서울");
        final Company company2 = new Company(2L, "카카오", "한국", "서울");
        final Member member = new Member("김코딩");

        companyRepository.save(company1);
        companyRepository.save(company2);
        memberRepository.save(member);

        return Stream.of(
                dynamicTest("네이버 채용공고 등록", () -> {
                    //given
                    final RegisterRequest request = new RegisterRequest(1L, "백엔드", 500000L, "백엔드 개발자 채용", "java");

                    //when
                    recruitService.register(request);

                    //then
                    assertThat(recruitRepository.findById(1L)).isNotNull();
                    assertThat(companyRepository.findById(1L)).isNotNull();
                }),

                dynamicTest("카카오 채용공고 추가 등록", () -> {
                    //given
                    final RegisterRequest request = new RegisterRequest(2L, "데브옵스", 300000L, "데브옵스 엔지니어 채용", "aws");

                    //when
                    recruitService.register(request);

                    //then
                    assertThat(recruitRepository.findById(2L)).isNotNull();
                    assertThat(companyRepository.findById(2L)).isNotNull();
                }),

                dynamicTest("채용공고 전체조회", () -> {
                    //when
                    List<ListResponse> responseList = recruitService.getList();

                    //then
                    assertThat(responseList).hasSize(2);
                }),

                dynamicTest("네이버 채용공고 상세", () -> {
                    //when
                    SingleResponse response = recruitService.getSingle(1L);

                    //then
                    assertThat(response.getPosition()).isEqualTo("백엔드");
                    assertThat(response.getTool()).isEqualTo("java");
                }),

                dynamicTest("네이버 채용공고 수정 (백엔드 -> 프론트엔드)", () -> {
                    //given
                    final UpdateRequest request = new UpdateRequest("프론트엔드", 400000L, "프론트엔드 개발자 채용", "javascript");

                    //when
                    recruitService.update(1L, request);

                    //then
                    Recruit findRecruit = recruitRepository.findById(1L).get();

                    assertThat(findRecruit.getPosition()).isEqualTo(request.getPosition());
                    assertThat(findRecruit.getContent()).isEqualTo(request.getContent());
                }),

                dynamicTest("채용공고 검색 (키워드 = aws)", () -> {
                    //given
                    String content = "aws";

                    //when
                    List<ListResponse> response = recruitService.search(content);

                    //then
                    assertThat(response).hasSize(1);
                    assertThat(response.get(0).get사용기술()).isEqualTo("aws");
                }),

                dynamicTest("카카오 채용공고 지원", () -> {
                    //given
                    Long memberId = 1L;
                    Long recruitId = 2L;
                    ApplyRequest request = new ApplyRequest(recruitId, memberId);

                    //when
                    recruitService.apply(request);

                    //then
                    Member findMember = memberRepository.findById(memberId).get();

                    assertThat(findMember.getRecruit().getTool()).isEqualTo("aws");
                    assertThat(findMember.getRecruit().getPosition()).isEqualTo("데브옵스");
                }),

                dynamicTest("네이버 채용공고 지원 - 중복지원 실패", () -> {
                    //given
                    Long memberId = 1L;
                    Long recruitId = 1L;
                    ApplyRequest request = new ApplyRequest(recruitId, memberId);

                    //when //then
                    assertThatThrownBy(() -> recruitService.apply(request)).hasMessage(ALREADY_APPLIED.getMessage());
                }),

                dynamicTest("네이버 채용공고 삭제", () -> {
                    //when
                    recruitService.delete(1L);

                    //then
                    assertThat(recruitService.getList()).hasSize(1);
                })
        );
    }
}
