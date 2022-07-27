package kr.co.communityJh.board.dto;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;

/**
 * @author jhlee
 * 
 * Board Entity DTO
 */
@Getter
@Setter
//@AllArgsConstructor
@ToString
public class BoardPageWithSearchDto {
	private String searchText;
	private String option;
	private int page = 0;
	private int size = 2;
	private String sortBy = "createDate";
	private Sort sort;
	private String direction = "desc";

	public Sort getSort() {
		if(this.direction.equals("desc")){
			sort = Sort.by(sortBy).descending();
		}else{
			sort = Sort.by(sortBy).ascending();
		}
		return sort;
	}

	public String[] getOption(){
		if(StringUtils.isEmpty(option)){
			return null;
		}
		return option.split("");
	}
}
