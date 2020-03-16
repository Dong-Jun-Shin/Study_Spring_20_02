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


--댓글 테이블 생성
create table spring_reply(
    r_num number not null ,
    b_num number not null ,
    r_name varchar2(10) null ,
    r_content varchar2(2000) null ,
    r_pwd varchar2(18) null ,
    r_date date default sysdate,
    constraint spring_reply_pk primary key(r_num),
    constraint spring_reply_fk foreign key(b_num) references spring_board(b_num)
 ) ;

comment on table spring_reply is '댓글 정보';
comment on column spring_reply.r_num is '댓글번호';
comment on column spring_reply.b_num is '게시판 글번호';
comment on column spring_reply.r_name is '댓글 작성자';
comment on column spring_reply.r_content is '댓글 내용';
comment on column spring_reply.r_pwd is '댓글 비밀번호';
comment on column spring_reply.r_date is '댓글 등록일';

create sequence spring_reply_seq
start with 1
increment by 1
nocycle;

-- 댓글 테이블 생성
create table spring_reply(
r_num number not null ,
b_num number not null ,
r_name varchar2(10) null ,
r_content varchar2(2000) null ,
r_pwd varchar2(18) null ,
r_date date default sysdate,
constraint spring_reply_pk primary key(r_num),
constraint spring_reply_fk foreign key(b_num) references spring_board(b_num)
 ) ;

comment on table&nbsp; spring_reply is '댓글 정보';
comment on column spring_reply.r_num is '댓글번호';
comment on column spring_reply.b_num is '게시판 글번호';
comment on column spring_reply.r_name is '댓글 작성자';
comment on column spring_reply.r_content is '댓글 내용';
comment on column spring_reply.r_pwd is '댓글 비밀번호';
comment on column spring_reply.r_date is '댓글 등록일';

create sequence spring_reply_seq
start with 1
increment by 1
nocycle;


--테이블 생성
CREATE TABLE spring_gallery(
    g_num NUMBER NOT NULL,
    g_name VARCHAR2(10) NOT NULL,
    g_subject VARCHAR2(50 CHAR) NOT NULL,
    g_content VARCHAR2(100 CHAR) NOT NULL,
    g_thumb VARCHAR2(100) NOT NULL,
    g_file VARCHAR2(100) NOT NULL,
    g_pwd VARCHAR2(18) NOT NULL,
    g_date DATE DEFAULT SYSDATE,
    
    CONSTRAINT spring_gallery_pk PRIMARY KEY(g_num)
);

COMMENT ON TABLE "JAVAUSER"."SPRING_GALLERY" IS '갤러리 정보';
COMMENT ON COLUMN "JAVAUSER"."SPRING_GALLERY"."G_NUM" IS '갤러리 번호';
COMMENT ON COLUMN "JAVAUSER"."SPRING_GALLERY"."G_NAME" IS '갤러리 이름';
COMMENT ON COLUMN "JAVAUSER"."SPRING_GALLERY"."G_SUBJECT" IS '갤러리 제목';
COMMENT ON COLUMN "JAVAUSER"."SPRING_GALLERY"."G_CONTENT" IS '갤러리 내용';
COMMENT ON COLUMN "JAVAUSER"."SPRING_GALLERY"."G_THUMB" IS '갤러리 썸네일';
COMMENT ON COLUMN "JAVAUSER"."SPRING_GALLERY"."G_FILE" IS '갤러리 사진';
COMMENT ON COLUMN "JAVAUSER"."SPRING_GALLERY"."G_PWD" IS '갤러리 비밀번호';
COMMENT ON COLUMN "JAVAUSER"."SPRING_GALLERY"."G_DATE" IS '갤러리 등록일';

CREATE SEQUENCE spring_gallery_seq
START WITH 1
INCREMENT BY 1
NOCYCLE
CACHE 2;
