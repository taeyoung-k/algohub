package algohub.controller.integration.algorithm;

import algohub.config.EnableMockMvc;
import algohub.controller.algorithm.dto.AlgoListDto;
import algohub.repository.algorithm.AlgorithmRepo;
import algohub.service.algorithm.AlgorithmService;
import com.google.gson.Gson;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@EnableMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AlgorithmControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AlgorithmService algorithmService;

    @Autowired
    private AlgorithmRepo algorithmRepository;

    @Test
    @DisplayName("알고리즘 문제 조회 컨트롤러 통합테스트")
    void test() throws Exception {
        //given
        // 사용자가 추가되어야 함

        //when
        Gson gson = new Gson();
        String categoryId = "1";
        List<AlgoListDto> algoList = algorithmService.getAlgoList(categoryId);

        Map<Object, Object> map = new HashMap<>();

        if(!algoList.isEmpty()) {
            String p_category = algoList.get(0).getP_category();
            int p_number = algoList.get(0).getP_number();
            map.put("statusCode", Response.SC_OK);
            map.put("message", HttpStatus.OK);
            map.put("p_category", p_category);
            map.put("p_number", p_number);
            map.put("algorithmList", algoList);
        }

        String result = gson.toJson(map);

        //then
        mvc.perform(MockMvcRequestBuilders.get("/api/algorithms/" + categoryId).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(result))
                .andDo(MockMvcResultHandlers.print());
    }
}
