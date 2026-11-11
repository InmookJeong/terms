package kr.mook.terms.admin.management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 이용약관 관리(저장/수정/삭제/상태변경) 결과를 반환하기 위한 DTO
 * 
 * @version v.0.1
 * @since 2026.11.05
 * @author Jeong, Inmook
 */
@Data
@Builder
@AllArgsConstructor
public class TermsManagementResponseDto {

    @Schema(description = "이용약관 관리 결과 상태코드(200, 400, 500)", example = "200")
    private String statusCode;

    @Schema(description = "이용약관 관리 결과 메시지", example = "이용약관 정보를 저장하였습니다.")
    private String message;

    @Schema(description = "이용약관 아이디", example = "1")
    private long termsId;
}
