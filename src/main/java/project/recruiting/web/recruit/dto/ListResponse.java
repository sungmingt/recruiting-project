package project.recruiting.web.recruit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
