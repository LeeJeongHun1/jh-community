package kr.co.communityJh.board.dto;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author jhlee
 * <p>
 * Board Entity DTO
 */
@Getter
@Setter
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardPageResponseDto<T> {
     private List<T> dtoList;
     private PageInfo pageInfo;
//     private int commentCount;
    @Builder(builderMethodName = "toResponse")
    public BoardPageResponseDto(Page<BoardInfoDto> boardPageRequestDto) {
        this.dtoList = (List<T>) boardPageRequestDto.getContent();
//        dtoList.get(0).g
        this.pageInfo = PageInfo.builder().boardPageRequestDto(boardPageRequestDto).build();

    }
}
