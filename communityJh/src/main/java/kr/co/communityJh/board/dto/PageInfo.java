package kr.co.communityJh.board.dto;

import lombok.*;
import org.springframework.data.domain.Page;

/**
 * @author jhlee
 * page info
 * paging 관련 VO
 */
@Getter
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageInfo {
    private static final int PAGEABLE_SIZE = 5; // 페이지 수
    private Long totalElements; // 총 게시글 수
    private int startPageNumber; // 시작 페이지
    private int pageNumber; // 현재 페이지
    private int endPageNumber; // 마지막 페이지
    private int totalPages; // 전체 페이지 수
    private int size; // 페이지별 게시글 수
    @Builder
    public PageInfo(Page<BoardInfoDto> boardPageRequestDto) {

        this.pageNumber = boardPageRequestDto.getNumber();
        this.totalElements = boardPageRequestDto.getTotalElements();
        this.totalPages = boardPageRequestDto.getTotalPages();
        this.size = boardPageRequestDto.getSize();

        this.startPageNumber = this.pageNumber - (PAGEABLE_SIZE-1) <= 0 ?
                0 : (this.pageNumber / PAGEABLE_SIZE) * PAGEABLE_SIZE;
        this.endPageNumber = ((((this.pageNumber) / PAGEABLE_SIZE) +1) * PAGEABLE_SIZE) - 1;

        // 잘못된 요청.
        if (this.pageNumber >= totalPages) {
            this.startPageNumber = 0;
            this.endPageNumber = 0;
        }else if(totalPages < this.endPageNumber) {
            this.endPageNumber = totalPages-1;
        }
    }
}
