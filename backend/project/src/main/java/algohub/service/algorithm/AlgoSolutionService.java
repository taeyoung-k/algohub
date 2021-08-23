package algohub.service.algorithm;

import algohub.controller.algorithm.dto.AlgoSolutionListDto;
import algohub.domain.algorithm.SourceComment;
import algohub.repository.algorithm.AlgoSolutionRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class AlgoSolutionService {

    private final AlgoSolutionRepo mapper;

    public List<AlgoSolutionListDto> getSolutionWriter(AlgoSolutionListDto algoSolutionList) {
        log.info("getSolutionWriter");

        return mapper.getSolutionWriter(algoSolutionList);
    }

    public List<AlgoSolutionListDto> getSolution(AlgoSolutionListDto algoSolutionList) {
        log.info("getSolution");

        return mapper.getSolution(algoSolutionList);
    }

    public void setSolution(Map<String, Object> data) {
        log.info("setSolution");

        mapper.setSolution(data);
    }

    // 풀이 댓글 작성
    public void writeSourceComment(SourceComment sourceComment) throws Exception {
        mapper.writeSourceComment(sourceComment);
    }

    // 풀이 댓글 조회
    public List<SourceComment> getSourceCommentList(int s_id) throws Exception {
        return mapper.getSourceCommentList(s_id);
    }

    // 풀이 댓글 수정
    public void editSourceComment(SourceComment sourceComment) throws Exception {
        mapper.editSourceComment(sourceComment);
    }

    // 풀이 댓글 삭제
    public void deleteSourceComment(int s_cm_id) throws Exception {
        mapper.deleteSourceComment(s_cm_id);
    }
}
