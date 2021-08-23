package algohub.service.algorithm.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class AlgoSaveDto {
    private String p_title;
    private String p_link;
    private String p_category;
    private String p_content;
    private String code;
    private String language;
}
