package kr.mook.terms.admin.search.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 이용약관 조회 결과를 반환하기 위한 DTO
 * 
 * @version v.0.1
 * @since 2026.11.24
 * @author Jeong, Inmook
 */
@Data
@Builder
@AllArgsConstructor
public class TermsSearchResponseDto {

    @Schema(description = "이용약관 조회 결과 상태코드", example = "NOT-EXISTS")
    private String statusCode;

    @Schema(description = "이용약관 조회 결과 메시지", example = "이용약관 정보를 조회하였습니다.")
    private String message;

    @Schema(description = "이용약관 조회 건수", example = "1")
    private long count;

    @Schema(description = "이용약관 조회 결과(상세 정보 또는 목록)", example = "[{termsId:1, title:사이트 이용 및 정보 수집에 대한 이용 약관, ...}]")
    private Object result;
}
