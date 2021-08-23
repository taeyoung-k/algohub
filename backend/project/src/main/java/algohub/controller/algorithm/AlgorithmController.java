package algohub.controller.algorithm;

import algohub.controller.algorithm.dto.AlgoListDto;
import algohub.controller.algorithm.dto.AlgoSaveDto;
import algohub.domain.algorithm.AlgorithmInfo;
import algohub.service.algorithm.AlgorithmService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "localhost:8080")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/algorithms")
public class AlgorithmController {

    private final AlgorithmService algorithmService;

    HttpSession session;

    // API 2번 알고리즘 문제 조회
    @GetMapping("/{a_c_id}")
    public Map<String, Object> algorithms(@PathVariable("a_c_id") String a_c_id) {
        Map<String, Object> map = new HashMap<>();
        List<AlgoListDto> algoList = algorithmService.getAlgoList(a_c_id);
        if(!algoList.isEmpty()) {
            String p_category = algoList.get(0).getP_category();
            int p_number = algoList.get(0).getP_number();
            map.put("statusCode", Response.SC_OK);
            map.put("message", HttpStatus.OK);
            map.put("p_category", p_category);
            map.put("p_number", p_number);
            map.put("algorithmList", algoList);
        }
        return map;
    }

    // API 3번 알고리즘 문제 등록
    @PostMapping("/writing")
    Map<String, Object> algoSave(@ModelAttribute AlgoSaveDto param, HttpServletRequest request) throws IOException {

        // 쿠키에 설정된 세션을 가져옴
        session = request.getSession();
        String m_id = (String) session.getAttribute("user");

        Map<String, Object> paramMap = new HashMap<>();

        // 알고리즘 분류가 N개이기 때문에 분리 후 Map에 파라미터 넣어서 서비스로 인자로 활용
        List<String> list = Arrays.asList(param.getP_category().split(","));

        paramMap.put("current_user", m_id);
        paramMap.put("algosave", param);
        paramMap.put("category", list);
        algorithmService.setAlgoSave(paramMap);

        Map<String, Object> returnCode = new HashMap<>();
        returnCode.put("statusCode", Response.SC_OK);
        returnCode.put("message", HttpStatus.OK);
        return returnCode;
    }

    // 문제 검색
    @GetMapping("/search/{search}")
    public Map<String, Object> searchAlgorithm(@PathVariable String search) throws Exception {
        Map<String, Object> responseMap = new HashMap<>();
        List<AlgorithmInfo> algorithmList = algorithmService.searchAlgorithm(search);

        responseMap.put("algorithmList", algorithmList);
        responseMap.put("statusCode", Response.SC_OK);
        responseMap.put("message", "문제 검색 완료");

        return responseMap;
    }
}
