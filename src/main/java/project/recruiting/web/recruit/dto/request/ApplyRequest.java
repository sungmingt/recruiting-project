package project.recruiting.web.recruit.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class ApplyRequest {

    private Long recruitId;
    private Long userId;
}
