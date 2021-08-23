package algohub.repository.algorithm;

import algohub.controller.algorithm.dto.AlgoListDto;
import algohub.domain.algorithm.AlgorithmInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface AlgorithmRepo {

    // 알고리즘 리스트 조회
    List<AlgoListDto> getAlgoList(String a_c_id);

    // 알고리즘 문제 등록
    void setAlgoSave(Map<String, Object> paramMap);

    // 알고리즘 문제 검색
    List<AlgorithmInfo> searchAlgorithm(String search) throws Exception;
}
