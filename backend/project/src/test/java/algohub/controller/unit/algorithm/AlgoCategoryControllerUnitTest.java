package algohub.controller.unit.algorithm;

import algohub.config.EnableMockMvc;
import algohub.controller.algorithm.AlgoCategoryController;
import algohub.domain.algorithm.AlgoCategory;
import algohub.repository.algorithm.AlgoCategoryRepo;
import algohub.service.algorithm.AlgoCategoryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@EnableMockMvc
@ExtendWith(SpringExtension.class)
@WebMvcTest(AlgoCategoryController.class) // 컨트롤러에 대한 것만 SpringContext처럼 테스트하고 나머지는 Mock의 stub 코드 만들어야함
class AlgoCategoryControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AlgoCategoryService algoCategoryService;

    @MockBean
    private AlgoCategoryRepo algoCategoryRepository;

    @Test
    @DisplayName("알고리즘 카테고리 컨트롤러 단위테스트")
    void algoCategoriesTest() throws Exception {

        List<AlgoCategory> categories = new ArrayList<>();
        categories.add(AlgoCategory.builder().p_category("그리드").build());

        given(algoCategoryService.getAlgoCategory()).willReturn(categories);

        mvc.perform(MockMvcRequestBuilders.get("/api/categories").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("그리드")))
                .andDo(MockMvcResultHandlers.print());
    }

}