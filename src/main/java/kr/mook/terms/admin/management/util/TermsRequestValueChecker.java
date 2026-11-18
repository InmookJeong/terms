package kr.mook.terms.admin.management.util;

import kr.mook.terms.admin.management.dto.TermsManagementRequestDto;

/**
 * 이용약관 정보 관리를 위해 요청된 값의 정합성 체크 기능 수행
 * 
 * @version v.1.0
 * @since 2026.11.18
 * @author Jeong, Inmook
 */
public class TermsRequestValueChecker {

    /**
     * 관리자가 작성한 이용약관 정보에 빈 값(EMPTY), 잘못된 형식의 값(NOT-VALID), 이미 저장된 값(DUPLICATION)이 있는지 확인하여 오류 상태 코드 반환<br/>
     * 
     * @param termsManagementRequestDto 관리자가 작성한 이용약관 정보
     * @return 오류 상태코드, 빈 값이 반환될 경우 오류가 없음을 의미
     */
    public static String getErrorStatusCode(final TermsManagementRequestDto termsManagementRequestDto, long termsId) {

        if(termsManagementRequestDto.getTitle() == null || "".equals(termsManagementRequestDto.getTitle()))
            return "EMPTY-TITLE";

        if(termsManagementRequestDto.getContents() == null || "".equals(termsManagementRequestDto.getContents()))
            return "EMPTY-CONTENTS";
        
        if(termsManagementRequestDto.getContentType() == null || "".equals(termsManagementRequestDto.getContentType()))
            return "EMPTY-CONTENT-TYPE";
        
        if(termsManagementRequestDto.getOrderNo() == null || termsManagementRequestDto.getOrderNo().intValue() <= 0)
            return "EMPTY-ORDER-NO";
        
        if(termsManagementRequestDto.getUseYn() == null || "".equals(termsManagementRequestDto.getUseYn()))
            return "EMPTY-USE-YN";
        
        if(!"Y".equals(termsManagementRequestDto.getUseYn().toUpperCase()) && !"N".equals(termsManagementRequestDto.getUseYn().toUpperCase()))
            return "NOT-VALID-USE-YN";
        
        if(termsManagementRequestDto.getRequireYn() == null || "".equals(termsManagementRequestDto.getRequireYn()))
            return "EMPTY-REQUIRE-YN";
        
        if(!"Y".equals(termsManagementRequestDto.getRequireYn().toUpperCase()) && !"N".equals(termsManagementRequestDto.getRequireYn().toUpperCase()))
            return "NOT-VALID-REQUIRE-YN";

        return "";
    }

}
