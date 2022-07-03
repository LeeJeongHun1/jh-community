package kr.co.zaritalk.dummy;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.communityJh.CommunityJhApplication;
import kr.co.communityJh.vo.Board;

@SpringBootTest(classes = CommunityJhApplication.class)
@Transactional
@AutoConfigureMockMvc
class DummyControllerTests {

	@Autowired MockMvc mockMvc;
	
	
	@Autowired ObjectMapper objectMapper;

	/**
	 * 게시글 작성 test
	 * @throws Exception
	 */
	@Test
	@DisplayName("게시글 작성")
	void postBoardSaveTest() throws Exception {
		Board board = Board.builder()
						.body("본문")
						.title("제목")
						.build();
						
		mockMvc.perform(post("/boards")
//				.accept(MediaType.)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
//				.param("body", "본문")
//				.param("title", "제목")
				.content(objectMapper.writeValueAsString(board))
				)
			.andExpect(status().isOk())
			.andDo(print());
	}

}
