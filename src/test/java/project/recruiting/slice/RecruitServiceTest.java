package project.recruiting.slice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.recruiting.domain.company.entity.Company;
import project.recruiting.domain.company.service.CompanyService;
import project.recruiting.domain.member.entity.Member;
import project.recruiting.domain.member.service.MemberService;
import project.recruiting.domain.recruit.entity.Recruit;
import project.recruiting.domain.recruit.repository.RecruitRepository;
import project.recruiting.domain.recruit.service.RecruitService;
import project.recruiting.web.recruit.dto.request.ApplyRequest;
import project.recruiting.web.recruit.dto.request.RegisterRequest;
import project.recruiting.web.recruit.dto.request.UpdateRequest;
import project.recruiting.web.recruit.dto.response.RegisterResponse;
import project.recruiting.web.recruit.dto.response.SingleResponse;
import project.recruiting.web.recruit.dto.response.UpdateResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class RecruitServiceTest {

    @Mock
    private RecruitRepository recruitRepository;
    @Mock
    private MemberService memberService;
    @Mock
    private CompanyService companyService;
    @InjectMocks
    private RecruitService recruitService;


    @DisplayName("채용공고 등록하기")
    @Test
    void registerTest() {
        //given
        Company company = new Company(1L, "네이버", "한국", "서울");
        RegisterRequest request = new RegisterRequest(1L, "백엔드", 10000L, "백엔드 java 개발자 채용", "java");

        given(recruitRepository.save(any(Recruit.class)))
                .willReturn(request.toEntity());
        given(companyService.findCompany(anyLong()))
                .willReturn(company);

        //when
        RegisterResponse response = recruitService.register(request);

        //then
        assertThat(response.getPosition()).isEqualTo(request.getPosition());
        assertThat(response.getContent()).isEqualTo(request.getContent());
        assertThat(response.getReward()).isEqualTo(request.getReward());
    }

    @DisplayName("채용공고 수정하기")
    @Test
    void updateTest() {
        //given
        UpdateRequest request = new UpdateRequest(null, 15000L, "백엔드 python 개발자 채용", "python");
        Recruit existing = new Recruit("백엔드", 10000L, "백엔드 java 개발자 채용", "java");

        given(recruitRepository.findById(anyLong()))
                .willReturn(Optional.of(existing));

        //when
        UpdateResponse response = recruitService.update(1L, request);

        //then
        assertThat(response.getPosition()).isEqualTo(existing.getPosition());
        assertThat(response.getContent()).isEqualTo(request.getContent());
        assertThat(response.getReward()).isEqualTo(request.getReward());
        assertThat(response.getTool()).isEqualTo(request.getTool());
    }

    @DisplayName("채용공고 상세 페이지")
    @Test
        //mock의 단점 : 필드 추가 시 생성자 다 바꿔야 한다!! + 데이터 미리 넣어놓는것도 테스트마다 다 다르다!!! 힘들다!!!
    void getSingleTest() {
        //given
        Company company = new Company(1L, "네이버", "한국", "서울");
        Recruit recruit = new Recruit(1L, company, new ArrayList<>(), "백엔드", 10000L, "백엔드 java 개발자 채용", "java");  //todo : 생성자 관련 리팩토링

        List<Long> others = List.of(3L, 15L, 87L);

        given(recruitRepository.findById(anyLong()))
                .willReturn(Optional.of(recruit));

        given(recruitRepository.findByCompanyId(anyLong()))
                .willReturn(others);

        //when
        SingleResponse response = recruitService.getSingle(recruit.getId());

        //then
        assertThat(response.getPosition()).isEqualTo(recruit.getPosition());
        assertThat(response.getContent()).isEqualTo(recruit.getContent());
        assertThat(response.getReward()).isEqualTo(recruit.getReward());
        assertThat(others).containsAll(response.getOthers());
    }

    @DisplayName("사용자는 1개의 채용공고에만 지원할 수 있다.")
    @Test
    void applyRestrictTest() {
        //given
        Recruit existing = new Recruit("백엔드 java", 50000L, "java 개발자 채용", "java, spring");
        Member member = new Member(1L, "김코딩", existing);

        ApplyRequest request = new ApplyRequest(1L, 1L);

        given(memberService.findMember(anyLong()))
                .willReturn(member);

        //when //then
        assertThatThrownBy(() -> recruitService.apply(request))
                .hasMessage("이미 지원하신 채용공고가 존재합니다.");

        assertThat(member.getRecruit().getPosition())
                .isEqualTo(existing.getPosition());

        assertThat(member.getRecruit().getTool())
                .isEqualTo(existing.getTool());

    }
}
