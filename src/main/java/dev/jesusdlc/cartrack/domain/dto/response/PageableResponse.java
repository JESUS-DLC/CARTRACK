package dev.jesusdlc.cartrack.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PageableResponse<T> {
    private List<T> content;
    private Integer numberPage;
    private Long totalBrands;
    private Integer sizePage;
    private Integer totalPages;
}
