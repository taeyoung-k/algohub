package algohub.controller.algorithm;

import algohub.domain.algorithm.AlgoCategory;
import algohub.service.algorithm.AlgoCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API 1번 알고리즘 분류 목록
 * 작성자 : 김태영 (2021-04-18)
 * 내용 : 알고리즘 카테고리
 */
@RequiredArgsConstructor
@RestController
public class AlgoCategoryController {

    private final AlgoCategoryService categoryService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/api/categories")
    public Map<String, Object> category(Model model) {
        logger.info("알고리즘 카테고리 컨트롤러");

        List<AlgoCategory> categories = categoryService.getAlgoCategory();
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", Response.SC_OK);
        map.put("message", HttpStatus.OK);
        map.put("categories", categories);

        return map;
    }
}
