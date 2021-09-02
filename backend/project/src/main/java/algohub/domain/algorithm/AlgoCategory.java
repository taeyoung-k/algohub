package algohub.domain.algorithm;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class AlgoCategory {
    private String p_category;
    private String p_content;
    private int p_number;
}
