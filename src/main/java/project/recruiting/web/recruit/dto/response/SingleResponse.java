package project.recruiting.web.recruit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.recruiting.domain.recruit.Recruit;

import java.util.List;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class SingleResponse {

    private Long recruitId;
    private String companyName;
    private String country;
    private String city;
    private String position;
    private Long reward;
    private String tool;
    private String content;
    private List<Long> others; //todo: dto로 래핑 고려

    public static SingleResponse toSingleResponse(Recruit recruit, List<Long> others) {
        return new SingleResponse(recruit.getId(), recruit.getCompany().getName(), recruit.getCompany().getCountry(),
                recruit.getCompany().getCity(), recruit.getPosition(), recruit.getReward(), recruit.getTool(), recruit.getContent(),
                others);
    }
}
