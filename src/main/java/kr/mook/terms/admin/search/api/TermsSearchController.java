package kr.mook.terms.admin.search.api;

import java.util.Locale;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.mook.terms.admin.search.dto.TermsSearchResponseDto;
import kr.mook.terms.admin.search.service.TermsSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * 이용약관 정보 조회를 위한 API
 * 
 * @version v.1.0
 * @since 2026.11.24
 * @author Jeong, Inmook
 */
@Slf4j 
@Tag(name = "이용약관 정보를 조회하기 위한 API", description = "관리자가 이용약관의 상세 정보를 조회하거나 이용약관 목록을 조회할 수 있다.")
@RestController
@RequestMapping("/api/v1/terms/search")
@RequiredArgsConstructor
public class TermsSearchController {

    private final TermsSearchService termsSearchService;

    @GetMapping("/{id}")
    public ResponseEntity<TermsSearchResponseDto> detail(@PathVariable("id") long termsId, final Locale locale) {
        log.info("##### 이용약관 조회 기능 실행. 조회 대상 이용약관 아이디 : {}", termsId);
        TermsSearchResponseDto termsSearchResponseDto = this.termsSearchService.detail(termsId, locale);
        return this._getResponseEntity(termsSearchResponseDto);
    }

    /**
     * 상태코드에 따른 결과 반환 객체 생성
     * 
     * @param termsSearchResponseDto
     * @return
     */
    private ResponseEntity<TermsSearchResponseDto> _getResponseEntity(final TermsSearchResponseDto termsSearchResponseDto) {
        String statusCode = termsSearchResponseDto.getStatusCode();

        // 저장 실패
        if("400".equals(statusCode))
            return ResponseEntity.badRequest().body(termsSearchResponseDto);

        // 처리 과정에서 서버 오류 발생
        if("500".equals(statusCode))
            return ResponseEntity.internalServerError().body(termsSearchResponseDto);

        // 수정 성공
        return ResponseEntity.ok(termsSearchResponseDto);
    }
}
