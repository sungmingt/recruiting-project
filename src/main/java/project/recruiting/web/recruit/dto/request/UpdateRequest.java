package project.recruiting.web.recruit.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class UpdateRequest {

    private String position;
    private Long reward;
    private String content;
    private String tool;
}
