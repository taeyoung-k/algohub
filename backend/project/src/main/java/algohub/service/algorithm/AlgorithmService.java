package algohub.service.algorithm;

import algohub.controller.algorithm.dto.AlgoListDto;
import algohub.domain.algorithm.AlgorithmInfo;
import algohub.repository.algorithm.AlgorithmRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class AlgorithmService {

    private final AlgorithmRepo mapper;
    
    // 알고리즘 리스트 조회
    public List<AlgoListDto> getAlgoList(String a_c_id) {
        log.info("getAlgoList");

        return mapper.getAlgoList(a_c_id);
    }

    // 알고리즘 문제 등록
    public void setAlgoSave(Map<String, Object> paramMap) {
        log.info("setAlgoSave");

        mapper.setAlgoSave(paramMap);
    }

    // 알고리즘 문제 검색
    public List<AlgorithmInfo> searchAlgorithm(String search) throws Exception {
        return mapper.searchAlgorithm(search);
    }
}
