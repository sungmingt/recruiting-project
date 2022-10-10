package project.recruiting.web.recruit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class MultiResponse<T> {

    private List<T> data;
}
