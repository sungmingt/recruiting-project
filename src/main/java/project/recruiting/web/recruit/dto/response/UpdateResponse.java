package project.recruiting.web.recruit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.recruiting.domain.recruit.Recruit;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class UpdateResponse {

    private String position;
    private Long reward;
    private String content;
    private String tool;

    public static UpdateResponse toUpdateResponse(Recruit recruit) {
        return new UpdateResponse(recruit.getPosition(), recruit.getReward(), recruit.getContent(), recruit.getTool());
    }
}
