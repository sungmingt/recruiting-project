package project.recruiting.web.recruit.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.recruiting.domain.recruit.Recruit;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class RegisterRequest {

    private Long companyId;
    private String position;
    private Long reward;
    private String content;
    private String tool;

    public Recruit toEntity() {
        return new Recruit(this.getPosition(), this.getReward(), this.getContent(), this.getTool());
    }
}
