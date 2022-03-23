package auth_service.dto;

import java.util.List;

public class WrapperEntityDto<T> {
    private List<T> listWrapper;

    public WrapperEntityDto() {
    }

    public List<T> getListWrapper() {
        return listWrapper;
    }

    public void setListWrapper(List<T> listWrapper) {
        this.listWrapper = listWrapper;
    }
}
