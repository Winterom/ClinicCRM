package auth_service.dto;


import java.util.List;

public class PaginationEntity<T> {
    private Integer totalPages;
    private Integer currentPage;
    private List<T> content;

    public PaginationEntity(Integer totalPages, Integer currentPage, List<T> content) {
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.content = content;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
