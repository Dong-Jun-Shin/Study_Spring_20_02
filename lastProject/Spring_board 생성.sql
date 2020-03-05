--기본 게시판 테이블 생성
CREATE TABLE spring_board(
    b_num NUMBER NOT NULL,
    b_name VARCHAR2(10) NOT NULL,
    b_title VARCHAR2(1000) NOT NULL,
    b_content CLOB,
    b_pwd VARCHAR2(18) NOT NULL,
    b_date DATE DEFAULT SYSDATE
);

ALTER TABLE spring_board
ADD CONSTRAINT spring_board_pk PRIMARY KEY(b_num);

COMMENT ON TABLE spring_board IS '게시판 정보';
COMMENT ON COLUMN spring_board.b_num IS '게시물 순번';
COMMENT ON COLUMN spring_board.b_name IS '게시물 작성자';
COMMENT ON COLUMN spring_board.b_title IS '게시물 제목';
COMMENT ON COLUMN spring_board.b_content IS '게시물 내용';
COMMENT ON COLUMN spring_board.b_pwd IS '게시물 비밀번호';
COMMENT ON COLUMN spring_board.b_date IS '게시물 등록일';

CREATE SEQUENCE spring_board_seq
START WITH 1
INCREMENT BY 1
NOCYCLE;

INSERT INTO spring_board(b_num, b_name, b_title, b_content, b_pwd) VALUES(spring_board_seq.NEXTVAL, '전상수', '공부가 너무 어려워요ㅜ', '전 아무것도 모르겠어요..ㅜ', 1234);
INSERT INTO spring_board(b_num, b_name, b_title, b_content, b_pwd) VALUES(spring_board_seq.NEXTVAL, '홍길동', 'CLOB이 뭔가요?', 'CLOB이란게 오라클 자료형에서 나왔는데 뭔지 하나도 모르겠어요.. 이게 뭐죠?', 1234);

UPDATE spring_board SET b_title=#{b_title}, b_content=#{b_content}, b_pwd=#{b_pwd}