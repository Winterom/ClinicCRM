package auth_service.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class PaginationEntity<T> {
    @Getter@Setter
    private Integer totalPage;
    @Getter@Setter
    private Integer currentPage;
    @Getter@Setter
    private List<T> content;

    public PaginationEntity(Integer totalPage, Integer currentPage, List<T> content) {
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.content = content;
    }

}
