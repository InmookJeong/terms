package kr.mook.terms.admin.management.util;

import java.util.Locale;

import kr.mook.terms.admin.management.dto.TermsManagementResponseDto;

public class TermsResponseMaker {

    /**
     * 오류 메시지를 포함한 결과 반환
     * 
     * @param message 저장/수정/삭제 실패 결과에 대한 메시지
     * @param locale 다국어 처리를 위한 언어 형식
     * @return 오류 메시지를 포함한 결과 반환
     */
    public static TermsManagementResponseDto getErrorResponse(final String message, final Locale locale) {
        return TermsManagementResponseDto.builder()
                                         .statusCode("400")
                                         .message(message)
                                         .termsId(0)
                                         .build();
    }

    /**
     * 저장/수정/삭제 성공에 대한 결과 반환
     * 
     * @param termsId 저장/수정 성공한 이용약관 아이디. 삭제의 경우 0이 전달됨
     * @param message 저장/수정/삭제 성공 결과에 대한 메시지
     * @param locale 다국어 처리를 위한 언어 형식
     * @return 저장/수정/삭제 성공에 대한 결과 반환
     */
    public static TermsManagementResponseDto getSuccessResponse(final long termsId, final String message, final Locale locale) {
        return TermsManagementResponseDto.builder()
                                         .statusCode("200")
                                         .message(message)
                                         .termsId(termsId)
                                         .build();
    }

}
