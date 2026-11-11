CREATE TABLE TERMS_AGREEMENTS (
	AGREEMENT_ID BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '이용약관 동의 아이디, 기본 키(PK), 이용약관 동의 정보를 관리하기 위한 고유 값이며, 수자 형태로 제공된다. 아이디는 등록된 순서대로 발급된다.',
    TERMS_ID BIGINT NOT NULL COMMENT '이용약관 아이디, 회원이 동의 또는 미동의한 이용약관의 아이디를 저장한다.',
    MEMBER_ID BIGINT NOT NULL COMMENT '회원 아이디, 이용약관에 동의 또는 미동의한 회원의 아이디를 저장한다.',
    AGREE_YN CHAR(1) NOT NULL DEFAULT 'Y' COMMENT '이용약관 동의여부, 이용약관의 동의 여부를 저장한다. 동의를 했을 경우 Y 값이 저장되며, 미동의를 했을 경우에는 N 값이 저장된다. 기본적으로 저장되는 값은 Y이며, 값은 반드시 저장되어야 한다.',
    AGREE_DATE VARCHAR(10) NOT NULL COMMENT '이용약관 동의 일자, 이용약관에 동의한 날짜를 저장한다. 날짜는 yyyy-MM-dd 형식으로 저장되며, 회원가입 시 자동으로 값이 저장된다.'
) COMMENT = '이용 약관 정보에 대해 사용자가 동의 또는 미동의한 내역을 관리한다.';