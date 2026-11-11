package kr.mook.terms.admin.management.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import kr.mook.terms.admin.management.dto.TermsManagementRequestDto;
import kr.mook.terms.admin.management.dto.TermsManagementResponseDto;
import kr.mook.terms.admin.management.persistence.TermsManagementMapper;
import kr.mook.terms.common.entity.Terms;
import kr.mook.terms.common.util.TermsConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 이용약관 관리(저장/수정/삭제/상태변경 등)를 위한 서비스 구현
 * 
 * @version v.1.0
 * @since 2026.11.05
 * @author Jeong, Inmook
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TermsManagementServiceImpl implements TermsManagementService {

    public final TermsManagementMapper termsManagementMapper;

    /**
     * 이용약관 정보 저장 서비스 구현
     */
    @Override
    public TermsManagementResponseDto saveTerms(final TermsManagementRequestDto termsManagementRequestDto) {
        log.info("##### 이용약관 정보 저장 서비스 실행.");
        log.info("##### 관리자가 작성한 이용약관 정보 : {}", termsManagementRequestDto);

        // 저장될 이용약관 정보의 값이 적절한지 검증
        TermsManagementResponseDto termsManagementResponseDto = TermsManagementResponseDto.builder().statusCode("200").build();
        this._makeDataEmptyErrorResponse(termsManagementRequestDto, termsManagementResponseDto);
        if(!"200".equals(termsManagementResponseDto.getStatusCode())) return termsManagementResponseDto;
        
        // 데이터베이스에 저장하기 위해 Terms Entity로 변환
        Terms terms = TermsConverter.toTerms(termsManagementRequestDto);
        log.info("##### 데이터베이스에 저장하기 위해 엔티티(Terms)로 변환 : {}", terms);
        terms.setCreateId(1L);
        terms.setCreateDate(LocalDateTime.now());

        termsManagementMapper.save(terms);
        log.info("##### 데이터베이스에 저장 후 엔티티(Terms) : {}", terms);
        
        this._makeSuccessResponse(termsManagementResponseDto, terms);
        return termsManagementResponseDto;
    }

    /**
     * 전달된 이용약관 정보의 값이 모두 입력되었는지 점검 후 결과 데이터 반환
     * 
     * @param termsManagementRequestDto 관리자가 작성한 이용약관 정보
     * @param termsManagementResponseDto 관리자가 작성한 이용약관 정보
     */
    private void _makeDataEmptyErrorResponse(final TermsManagementRequestDto termsManagementRequestDto, final TermsManagementResponseDto termsManagementResponseDto) {
        StringBuilder message = new StringBuilder();

        if(termsManagementRequestDto.getTitle() == null || "".equals(termsManagementRequestDto.getTitle())) {
            message.append("이용약관의 제목을 입력해주세요.");
        } else if(termsManagementRequestDto.getContents() == null || "".equals(termsManagementRequestDto.getContents())) {
            message.append("이용약관의 내용을 입력해주세요.");
        } else if(termsManagementRequestDto.getContentType() == null || "".equals(termsManagementRequestDto.getContentType())) {
            message.append("이용약관 내용의 유형을 입력해주세요.");
        } else if(termsManagementRequestDto.getOrderNo() == null || termsManagementRequestDto.getOrderNo().intValue() <= 0) {
            message.append("이용약관 출력 순번을 입력해주세요. 출력 순번은 1 이상의 숫자입니다.");
        } else if(termsManagementRequestDto.getUseYn() == null || "".equals(termsManagementRequestDto.getUseYn())) {
            message.append("이용약관 사용 여부를 입력해주세요. 이용약관 사용 여부는 Y 또는 N를 입력할 수 있습니다.");
        } else if(!"Y".equals(termsManagementRequestDto.getUseYn().toUpperCase()) && !"N".equals(termsManagementRequestDto.getUseYn().toUpperCase())) {
            message.append("이용약관 사용 여부는 Y 또는 N만 입력할 수 있습니다.");
        } else if(termsManagementRequestDto.getUseYn().length() > 1) {
            message.append("이용약관 사용 여부는 Y 또는 N만 입력할 수 있습니다.");
        } else if(termsManagementRequestDto.getRequireYn() == null || "".equals(termsManagementRequestDto.getRequireYn())) {
            message.append("이용약관 필수 여부를 입력해주세요. 이용약관 필수 여부는 Y 또는 N를 입력할 수 있습니다.");
        } else if(!"Y".equals(termsManagementRequestDto.getRequireYn().toUpperCase()) && !"N".equals(termsManagementRequestDto.getRequireYn().toUpperCase())) {
            message.append("이용약관 필수 여부는 Y 또는 N만 입력할 수 있습니다.");
        } else if(termsManagementRequestDto.getRequireYn().length() > 1) {
            message.append("이용약관 필수 여부는 Y 또는 N만 입력할 수 있습니다.");
        }

        if("".equals(message.toString())) return;

        termsManagementResponseDto.setStatusCode("400");
        termsManagementResponseDto.setMessage(message.toString());
    }

    /**
     * 저장된 이용약관 정보(이용약관 아이디)와 저장 성공 메시지, 상태 코드를 반환할 수 있도록 데이터 등록
     * 
     * @param termsManagementResponseDto 관리자에게 전달할 API 결과
     * @param terms 저장된 이용약관 정보
     */
    private void _makeSuccessResponse(final TermsManagementResponseDto termsManagementResponseDto, final Terms terms) {
        String message = "이용 약관 정보(" + terms.getTitle() + ")가 저장되었습니다.";
        termsManagementResponseDto.setStatusCode("200");
        termsManagementResponseDto.setMessage(message);
        termsManagementResponseDto.setTermsId(terms.getTermsId());
    }
}
