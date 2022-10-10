package project.recruiting.web.recruit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.recruiting.domain.recruit.Recruit;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class ListResponse {

    private Long recruitId;
    private String companyName;
    private String country;
    private String city;
    private String position;
    private Long reward;
    private String tool;

    public static List<ListResponse> toListResponse(List<Recruit> recruitList) {
        return recruitList.stream()
                .map(recruit -> new ListResponse(recruit.getId(), recruit.getCompany().getName(), recruit.getCompany().getCountry(),
                                                 recruit.getCompany().getCity(), recruit.getPosition(), recruit.getReward(), recruit.getTool()))
                .collect(Collectors.toList());
    }
}
