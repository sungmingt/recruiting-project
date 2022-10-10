package project.recruiting.web.recruit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.recruiting.domain.recruit.Recruit;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {

    private String position;
    private Long reward;
    private String content;
    private String tool;

    public static RegisterResponse toRegisterResponse(Recruit recruit) {
        return new RegisterResponse(recruit.getPosition(), recruit.getReward(), recruit.getContent(), recruit.getTool());
    }
}
