package algohub.controller.algorithm;

import algohub.controller.algorithm.dto.AlgoSolutionListDto;
import algohub.controller.algorithm.dto.AlgoSolutionSaveDto;
import algohub.domain.algorithm.SourceComment;
import algohub.service.algorithm.AlgoSolutionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "localhost:8080")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/solution")
public class AlgoSolutionController {

    private final AlgoSolutionService algoSolutionService;

    HttpSession session;

    // API 5번 문제 해설(풀이) 조회 : 글 작성자 풀이 내용과 글 작성자 포함 풀이 내용을 합쳐서 JSON 반환
    @GetMapping("/{p_title}/language/{language}")
    Map<String, Object> algoSolution(@PathVariable("p_title") String p_title, @PathVariable("language") String language) throws Exception{
        log.info("algoSolution = {}", this.getClass());

        AlgoSolutionListDto algoSolutionList = new AlgoSolutionListDto();
        algoSolutionList.setP_title(p_title);
        algoSolutionList.setLanguage(language);

        List<AlgoSolutionListDto> solutionWriter = algoSolutionService.getSolutionWriter(algoSolutionList);
        List<AlgoSolutionListDto> solutionList = algoSolutionService.getSolution(algoSolutionList);
        Map<String, Object> map = new HashMap<>();

        String _p_title = solutionWriter.get(0).getP_title();
        String _p_link = solutionWriter.get(0).getP_link();

        String _language;

        try {
            _language = solutionList.get(0).getLanguage();
        } catch (Exception e) {
            _language = " ";
        }

        map.put("language", _language);
        map.put("statusCode", Response.SC_OK);
        map.put("message", HttpStatus.OK);
        map.put("p_title", _p_title);
        map.put("p_link", _p_link);
        map.put("writer", solutionWriter);
        map.put("source", solutionList);

        return map;
    }

    // API 6번 풀이 등록 : 문제에 대한 풀이 등록
    @PostMapping("/writing")
    Map<String, Object> setSolution(@ModelAttribute AlgoSolutionSaveDto data, HttpServletRequest request) {
        log.info("setSolution = {}", this.getClass());

        session = request.getSession();
        String m_id = (String) session.getAttribute("user");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("solutionData", data);
        paramMap.put("current_user", m_id);

        algoSolutionService.setSolution(paramMap);

        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", Response.SC_OK);
        map.put("message", HttpStatus.OK);
        return map;
    }

    // 풀이 댓글 작성
    @PostMapping("/comments")
    public Map<String, Object> writeSourceComment(@ModelAttribute SourceComment sourceComment) throws Exception {
        Map<String, Object> responseMap = new HashMap<>();

        algoSolutionService.writeSourceComment(sourceComment);
        responseMap.put("statusCode", Response.SC_OK);
        responseMap.put("message", "댓글 작성 완료");

        return responseMap;
    }

    // 풀이 댓글 조회
    @GetMapping("/comments/{s_id}")
    public Map<String, Object> getSourceCommentList(@PathVariable int s_id) throws Exception {
        Map<String, Object> responseMap = new HashMap<>();
        List<SourceComment> sourceComments = algoSolutionService.getSourceCommentList(s_id);

        responseMap.put("comments", sourceComments);
        responseMap.put("statusCode", Response.SC_OK);
        responseMap.put("message", "댓글 조회 완료");

        return responseMap;
    }

    // 풀이 댓글 수정
    @PutMapping("/comments/{s_cm_id}")
    public Map<String, Object> editSourceComment(@PathVariable int s_cm_id, @ModelAttribute SourceComment sourceComment)
            throws Exception {
        Map<String, Object> responseMap = new HashMap<>();

        sourceComment.setS_cm_id(s_cm_id);
        algoSolutionService.editSourceComment(sourceComment);

        responseMap.put("statusCode", Response.SC_OK);
        responseMap.put("message", "댓글 수정 완료");
        return responseMap;
    }

    // 풀이 댓글 삭제
    @DeleteMapping("/comments/{s_cm_id}")
    public Map<String, Object> deleteSourceComment(@PathVariable int s_cm_id) throws Exception {
        Map<String, Object> responseMap = new HashMap<>();

        algoSolutionService.deleteSourceComment(s_cm_id);
        responseMap.put("statusCode", Response.SC_OK);
        responseMap.put("message", "댓글 삭제 완료");

        return responseMap;
    }

    // 풀이 추천 수 갱신

    // 댓글 추천 수 갱신
}
