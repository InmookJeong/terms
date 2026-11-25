package kr.mook.terms.admin.search.service;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import kr.mook.terms.admin.search.dto.TermsSearchResponseDto;
import kr.mook.terms.admin.search.persistence.TermsSearchMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 관리자용 이용약관 정보 조회 서비스 구현
 * 
 * @version v.1.0
 * @since 2026.11.12
 * @author Jeong, Inmook
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TermsSearchServiceImpl implements TermsSearchService {

    private final MessageSource messageSource;
    public final TermsSearchMapper termsSearchMapper;

    /**
     * 이용약관 정보가 이용약관 테이블에 저장되어 있는지 확인
     */
    @Override
    public boolean exists(final long termsId) {
        return this.termsSearchMapper.countByTermsId(termsId) > 0;
    }

    /**
     * 이용약관 정보가 이용약관 조회 테이블에 저장되어 있는지 확인
     */
    @Override
    public boolean existsUserTermsSearch(final long termsId) {
        return this.termsSearchMapper.countTermsSearchByTermsId(termsId) > 0;
    }

    /**
     * 이용약관 정보의 제목이 중복되었는지 확인
     */
    @Override
    public boolean duplicateTitle(final String title, final long termsId) {
        HashMap<String, Object> termsMap = new HashMap<String, Object>();
        termsMap.put("title", title);
        termsMap.put("termsId", termsId);
        return this.termsSearchMapper.countByTitle(termsMap) > 0;
    }

    /**
     * 사용중인 이용약관 정보의 출력 순번가 중복되었는지 확인
     */
    @Override
    public boolean duplicateOrderNo(final int orderNo, final long termsId) {
        HashMap<String, Object> termsMap = new HashMap<String, Object>();
        termsMap.put("orderNo", orderNo);
        termsMap.put("termsId", termsId);
        return this.termsSearchMapper.countByOrderNo(termsMap) > 0;
    }

    /**
     * 이용약관 아이디를 통한 이용약관 상세 정보 조회
     */
    @Override
    public TermsSearchResponseDto detail(final long termsId, final Locale locale) {
        TermsSearchResponseDto termsSearchResponseDto = TermsSearchResponseDto.builder().build();
        if(!this.exists(termsId)) {
            String message = this.messageSource.getMessage("ERROR-TERMS-NOT-EXISTS", null, locale);
            termsSearchResponseDto.setStatusCode("400");
            termsSearchResponseDto.setMessage(message);
            return termsSearchResponseDto;
        }

        String message = this.messageSource.getMessage("TERMS-SEARCH", null, locale);
        termsSearchResponseDto.setStatusCode("200");
        termsSearchResponseDto.setMessage(message);
        termsSearchResponseDto.setCount(1);
        termsSearchResponseDto.setResult(this.termsSearchMapper.get(termsId));        
        return termsSearchResponseDto;
    }
}
