package algohub.controller.unit.algorithm;

import algohub.config.EnableMockMvc;
import algohub.controller.algorithm.AlgorithmController;
import algohub.controller.algorithm.dto.AlgoListDto;
import algohub.repository.algorithm.AlgorithmRepo;
import algohub.service.algorithm.AlgorithmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.BDDMockito.given;

@EnableMockMvc
@ExtendWith(SpringExtension.class)
@WebMvcTest(AlgorithmController.class)
public class AlgorithmControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AlgorithmService algorithmService;

    @MockBean
    private AlgorithmRepo algorithmRepo;

    @Test
    @DisplayName("알고리즘 문제 조회 컨트롤러 단위테스트")
    void test() throws Exception {

        String categoryId = "1";
        List<AlgoListDto> algoListDtoList = new ArrayList<>();
        algoListDtoList.add(AlgoListDto.builder()
                .p_title("테스트")
                .p_category("그래프")
                .p_link("www.xxx.com")
                .p_number(0)
                .a_id(1)
                .language("Java")
                .build());

        given(algorithmService.getAlgoList(categoryId)).willReturn(algoListDtoList);


        Map<Object, Object> map = new HashMap<>();

        if(!algoListDtoList.isEmpty()) {
            String p_category = algoListDtoList.get(0).getP_category();
            int p_number = algoListDtoList.get(0).getP_number();
            map.put("statusCode", Response.SC_OK);
            map.put("message", HttpStatus.OK);
            map.put("p_category", p_category);
            map.put("p_number", p_number);
            map.put("algorithmList", algoListDtoList);
        }

        Gson gson = new Gson();
        String result = gson.toJson(map);

        mvc.perform(MockMvcRequestBuilders.get("/api/algorithms/" + categoryId).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(result))
                .andDo(MockMvcResultHandlers.print());
    }
}