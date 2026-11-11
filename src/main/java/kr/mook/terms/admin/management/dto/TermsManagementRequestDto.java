package kr.mook.terms.admin.management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 이용약관 관리(저장/수정)를 위한 DTO
 * 
 * @version v.1.0
 * @since 2026.11.05
 * @author Jeong, Inmook
 */
@Data
@AllArgsConstructor
public class TermsManagementRequestDto {

    @Schema(description = "이용약관 제목", example = "사이트 이용 및 데이터 수집에 관한 동의")
    private String title;

    @Schema(description = "이용약관 내용", example = "○○○ 플랫폼은 회원 가입을 한 후 사용할 수 있습니다.")
    private String contents;
    
    @Schema(description = "이용약관 내용 유형(TEXT/HTML/JSON/MARKDOWN 등)", example = "HTML")
    private String contentType;
    
    @Schema(description = "이용약관 출력 순번", example = "1")
    private Integer orderNo;
    
    @Schema(description = "이용약관 사용여부(Y/N)", example = "Y")
    private String useYn;
    
    @Schema(description = "이용약관 동의 필수여부(Y/N)", example = "Y")
    private String requireYn;
}
