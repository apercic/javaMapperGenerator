import java.util.ArrayList;
import java.util.List;

public class CatMapper {

    public CatDto fromViewToDto(CatView view) {
        CatDto dto = new CatDto();
        dto.setName(view.getName());
        return dto;
    }

    public CatView fromDtoToView(CatDto dto) {
        CatView view = new CatView();
        view.setName(dto.getName());
        return view;
    }

    public List<CatDto> fromViewListToDtoList(List<CatView> viewList) {
        List<CatDto> dtoList = new ArrayList<>();
        viewList.forEach(p -> dtoList.add(fromViewToDto(p)));
        return dtoList;
    }

    public List<CatView> fromDtoListToViewList(List<CatDto> dtoList) {
        List<CatView> viewList = new ArrayList<>();
        dtoList.forEach(p -> viewList.add(fromDtoToView(p)));
        return viewList;
    }

}