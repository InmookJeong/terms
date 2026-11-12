package kr.mook.terms.admin.management.api;

import java.util.Locale;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.mook.terms.admin.management.dto.TermsManagementRequestDto;
import kr.mook.terms.admin.management.dto.TermsManagementResponseDto;
import kr.mook.terms.admin.management.service.TermsManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * 이용약관 관리(저장/수정/삭제/상태변경 등)를 위한 API
 * 
 * @version v.1.0
 * @since 2026.11.05
 * @author Jeong, Inmook
 */
@Slf4j
@Tag(name = "이용약관 관리(저장/수정/삭제/상태변경 등)를 위한 API", description = "관리자가 이용약관 정보를 저장하거나 수정, 삭제 또는 상태 변경을 할 수 있다.")
@RestController
@RequestMapping("/api/v1/terms")
@RequiredArgsConstructor
public class TermsManagementController {

    private final TermsManagementService termsManagementService;

    /**
     * 이용약관 정보 저장을 위한 API
     * 
     * @param termsManagementRequestDto 관리자가 작성한 이용약관 정보
     * @return 이용약관 정보 저장 결과 상태 코드 및 메시지, 이용약관 아이디 반환
     */
    @PostMapping
    public ResponseEntity<TermsManagementResponseDto> saveTerms(@RequestBody TermsManagementRequestDto termsManagementRequestDto, final Locale locale) {
        log.info("##### 이용약관 정보 저장 기능 실행.");
        TermsManagementResponseDto termsManagementResponseDto = this.termsManagementService.saveTerms(termsManagementRequestDto, locale);
        String statusCode = termsManagementResponseDto.getStatusCode();

        // 저장 실패
        if("400".equals(statusCode))
            return ResponseEntity.badRequest().body(termsManagementResponseDto);

        // 저장 과정에서 서버 오류 발생
        if("500".equals(statusCode))
            return ResponseEntity.internalServerError().body(termsManagementResponseDto);

        // 저장 성공
        return ResponseEntity.ok(termsManagementResponseDto);
    }
}
