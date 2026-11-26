package kr.mook.terms.common.util;

import kr.mook.terms.admin.management.dto.TermsManagementRequestDto;
import kr.mook.terms.admin.search.dto.TermsSearchRequestDto;
import kr.mook.terms.common.entity.Terms;

/**
 * 이용약관 정보 관리를 위해 전달된 값(TermsManagementRequestDto)을 데이터베이스에 저장할 수 있도록 Terms(Entity)로 변환<br/>
 * 또는 데이터베이스에서 조회한 데이터를 사용자에게 전달(TermsManagementResponseDto)할 수 있도록 변환
 * 
 * @version v.1.0
 * @since 2026.11.05
 * @author Jeong, Inmook
 */
public class TermsConverter {

    /**
     * 관리자로부터 전달받은 이용약관 정보(termsManagementRequestDto)를 데이터베이스에 저장할 수 있도록 Terms(Entity)로 변환
     * 
     * @param termsManagementRequestDto 관리자가 작성한 이용약관 정보
     * @return 변환된 Terms(Entity)
     */
    public static Terms toTerms(final TermsManagementRequestDto termsManagementRequestDto) {
        _setDefaultValue(termsManagementRequestDto);

        return Terms.builder()
                    .title(termsManagementRequestDto.getTitle())
                    .contents(termsManagementRequestDto.getContents())
                    .contentType(termsManagementRequestDto.getContentType())
                    .orderNo(termsManagementRequestDto.getOrderNo())
                    .useYn(termsManagementRequestDto.getUseYn().toUpperCase())
                    .requireYn(termsManagementRequestDto.getRequireYn().toUpperCase())
                    .build();
    }

    /**
     * 관리자로부터 전달받은 이용약관 조회 조건을 바탕으로 데이터베이스에서 이용약관 목록을 조회할 수 있도록 Terms(Entity)로 변환
     * 
     * @param termsSearchRequestDto 이용약관 목록 조회를 위한 객체
     * @return
     */
    public static Terms toTerms(final TermsSearchRequestDto termsSearchRequestDto) {
        _setDefaultValue(termsSearchRequestDto);

        return Terms.builder()
                    .title(termsSearchRequestDto.getTitle())
                    .contentType(termsSearchRequestDto.getType())
                    .useYn(termsSearchRequestDto.getUseYn().toUpperCase())
                    .requireYn(termsSearchRequestDto.getRequireYn().toUpperCase())
                    .build();
    }

    /**
     * 이용약관 정보 관리 시 Entity로 변환하기 전 사용여부, 필수여부의 빈 값 처리
     * @param termsManagementRequestDto
     */
    private static void _setDefaultValue(TermsManagementRequestDto termsManagementRequestDto) {
        if(termsManagementRequestDto.getUseYn() == null) termsManagementRequestDto.setUseYn("");
        if(termsManagementRequestDto.getRequireYn() == null) termsManagementRequestDto.setRequireYn("");

    }

    /**
     * 이용약관 목록 조회 시 Entity로 변환하기 전 사용여부, 필수여부의 빈 값 처리
     * @param termsManagementRequestDto
     */
    private static void _setDefaultValue(TermsSearchRequestDto termsSearchRequestDto) {
        if(termsSearchRequestDto.getUseYn() == null) termsSearchRequestDto.setUseYn("");
        if(termsSearchRequestDto.getRequireYn() == null) termsSearchRequestDto.setRequireYn("");
    }
}
