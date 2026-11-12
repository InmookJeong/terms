package kr.mook.terms.admin.search.service;

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

    @Override
    public boolean duplicateTitle(final String title) {
        return this.termsSearchMapper.countByTitle(title) > 0;
    }

    @Override
    public boolean duplicateOrderNo(final int orderNo) {
        return this.termsSearchMapper.countByOrderNo(orderNo) > 0;
    }

}
