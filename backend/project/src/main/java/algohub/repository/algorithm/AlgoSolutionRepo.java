package algohub.repository.algorithm;

import algohub.controller.algorithm.dto.AlgoSolutionListDto;
import algohub.domain.algorithm.SourceComment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface AlgoSolutionRepo {

    // 알고리즘 풀이 등록
    List<AlgoSolutionListDto> getSolutionWriter(AlgoSolutionListDto algoSolutionList);
    List<AlgoSolutionListDto> getSolution(AlgoSolutionListDto algoSolutionList);

    void setSolution(Map<String, Object> data);

    // 풀이 댓글 작성
    void writeSourceComment(SourceComment sourceComment) throws Exception;

    // 풀이 댓글 조회
    List<SourceComment> getSourceCommentList(int s_id) throws Exception;

    // 풀이 댓글 수정
    void editSourceComment(SourceComment sourceComment) throws Exception;

    // 풀이 댓글 삭제
    void deleteSourceComment(int s_cm_id) throws Exception;
}
