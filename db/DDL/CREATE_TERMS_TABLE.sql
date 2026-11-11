CREATE TABLE TERMS (
	TERMS_ID BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '이용약관 아이디, 기본 키(PK), 이용 약관 안내문을 관리하기 위한 고유 값이며, 숫자 형태로 제공된다. 아이디는 등록된 순서대로 발급된다.',
    TITLE VARCHAR(100) NOT NULL COMMENT '이용약관 제목, 이용 약관 안내문의 제목을 저장한다.',
    CONTENTS LONGTEXT NOT NULL COMMENT '이용약관 내용, 이용 약관 안내문의 내용을 저장한다. 관리자가 텍스트 입력기를 통해 입력한 내용은 HTML 코드 형태로 저장되며, 그 외의 일반 텍스트나 JSON 또는 MARKDOWN 형식으로도 저장된다.',
    CONTENT_TYPE VARCHAR(50) NOT NULL COMMENT '내용 유형, 이용약관 내용의 유형을 저장한다. 단순한 TEXT부터 HTML, JSON, MARKDOWN 등의 타입을 지원한다.',
    ORDER_NO INT NOT NULL COMMENT '출력 순번, 이용 약관 목록 조회 시 정렬 순번을 저장한다.',
    USE_YN CHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부, 이용 약관 안내문의 사용 여부를 저장한다. 사용 여부 값을 선택하지 않았을 경우를 대비하여 기본 값을 Y로 설정한다. 이용 약관 안내문을 사용하지 않을 경우 관리자가 값을 N으로 설정한다.',
    REQUIRE_YN CHAR(1) NOT NULL DEFAULT 'Y' COMMENT '필수여부, 이용 약관 안내문의 필수 여부를 저장한다. 필수 여부가 Y인 경우 회원가입 시 이용 약관 안내문에 대한 동의를 필수로 선택해야 한다. 필수가 Y인 이용 약관 안내문에 동의하지 않으면 회원가입을 할 수 없다. 필수 여부가 N인 경우에는 이용 약관 안내문에 동의하지 않아도 회원가입을 진행할 수 있다.',
    CREATE_ID BIGINT NOT NULL COMMENT '생성자 아이디, 이용 약관 안내문을 최초 작성 및 등록한 사용자의 아이디를 저장한다.',
    CREATE_DATE DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성일자, 이용 약관 안내문을 최초 작성 및 등록한 시간을 저장한다. 시간은 yyyy-MM-dd HH:mm:ss.SSS 형식으로 저장된다.',
    UPDATE_ID BIGINT COMMENT '수정자 아이디, 이용 약관 안내문을 수정한 사용자의 아이디를 저장한다. 이용 약관 안내문을 최초 등록할 때 수정자 아이디 값은 필수 입력되어야 하는 값이 아니지만 이용 약관 안내문의 제목이나 내용을 수정할 때에는 필수로 입력되어야 한다.',
    UPDATE_DATE DATETIME COMMENT '수정일시, 이용 약관 안내문을 수정한 시간을 저장한다. 이용 약관 안내문을 최초 등록할 때 수정시간 값은 필수 입력되어야 하는 값이 아니지만 이용 약관 안내문의 제목이나 내용을 수정할 때에는 필수로 입력되어야 한다. 시간은 yyy-MM-dd HH:mm:ss.SSS 형식으로 저장된다.'
) COMMENT = '이용 약관 테이블, 이용 약관 정보를 관리한다.';