package kr.mook.terms.admin.search.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import kr.mook.terms.admin.search.persistence.TermsSearchMapper;
import lombok.RequiredArgsConstructor;

/**
 * 관리자용 이용약관 정보 조회 서비스 구현
 * 
 * @version v.1.0
 * @since 2026.11.12
 * @author Jeong, Inmook
 */
@Service
@RequiredArgsConstructor
public class TermsSearchServiceImpl implements TermsSearchService {

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
    public boolean existsInSearch(final long termsId) {
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
}
