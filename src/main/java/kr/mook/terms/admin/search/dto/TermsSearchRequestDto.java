package kr.mook.terms.admin.search.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 이용약관 목록 조회를 위한 조건을 전달하는 객체
 * 
 * @version v.1.0
 * @since 2026.11.26
 * @author Jeong, Inmook
 */
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class TermsSearchRequestDto {

    @Schema(description = "이용약관 제목", example = "사이트 이용 및 데이터 수집에 관한 동의")
    private String title;

    @Schema(description = "이용약관 내용 유형(TEXT/HTML/JSON/MARKDOWN 등)", example = "HTML")
    private String type;
    
    @Schema(description = "이용약관 사용여부(Y/N)", example = "Y")
    private String useYn;
    
    @Schema(description = "이용약관 동의 필수여부(Y/N)", example = "Y")
    private String requireYn;
}
