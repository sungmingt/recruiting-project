package project.recruiting.web.recruit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private List<Long> others;  //////////// dto로 감싸기
}
