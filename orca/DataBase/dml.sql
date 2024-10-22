--------------------------------------------------------
-- 인사 더미 데이터 (PERSONNEL_INFORMATION)
--------------------------------------------------------

INSERT INTO DEPARTMENT(DEPT_CODE, PARTNAME, PASSWORD)
VALUES(SEQ_DEPT_CODE.NEXTVAL, '미정', 'pass1234');
INSERT INTO DEPARTMENT(DEPT_CODE, PARTNAME, PASSWORD)
VALUES(SEQ_DEPT_CODE.NEXTVAL, '인사관리부', 'hr5678');
INSERT INTO DEPARTMENT(DEPT_CODE, PARTNAME, PASSWORD)
VALUES(SEQ_DEPT_CODE.NEXTVAL, '경영지원부', 'support91011');
INSERT INTO DEPARTMENT(DEPT_CODE, PARTNAME, PASSWORD)
VALUES(SEQ_DEPT_CODE.NEXTVAL, '회계부', 'accounting1213');
INSERT INTO DEPARTMENT(DEPT_CODE, PARTNAME, PASSWORD)
VALUES(SEQ_DEPT_CODE.NEXTVAL, '마케팅부', 'marketing1415');
INSERT INTO DEPARTMENT(DEPT_CODE, PARTNAME, PASSWORD)
VALUES(SEQ_DEPT_CODE.NEXTVAL, '영업부', 'sales1617');
INSERT INTO DEPARTMENT(DEPT_CODE, PARTNAME, PASSWORD)
VALUES(SEQ_DEPT_CODE.NEXTVAL, '연구개발부', 'rnd1819');
INSERT INTO DEPARTMENT(DEPT_CODE, PARTNAME, PASSWORD)
VALUES(SEQ_DEPT_CODE.NEXTVAL, '정보기술부', 'it2021');
INSERT INTO DEPARTMENT(DEPT_CODE, PARTNAME, PASSWORD)
VALUES(SEQ_DEPT_CODE.NEXTVAL, '고객서비스부', 'customer2223');
--팀 INSERT
INSERT INTO DEPARTMENT_TEAM(TEAM_CODE, DEPT_CODE, TEAM_NAME)
VALUES(SEQ_TEAM_CODE.NEXTVAL, (SELECT DEPT_CODE FROM DEPARTMENT WHERE PARTNAME = '미정'), '미정');
-- 인사관리부 (인사)
INSERT INTO DEPARTMENT_TEAM(TEAM_CODE, DEPT_CODE, TEAM_NAME)
VALUES(SEQ_TEAM_CODE.NEXTVAL, (SELECT DEPT_CODE FROM DEPARTMENT WHERE PARTNAME = '인사관리부'), '채용팀');
INSERT INTO DEPARTMENT_TEAM(TEAM_CODE, DEPT_CODE, TEAM_NAME)
VALUES(SEQ_TEAM_CODE.NEXTVAL, (SELECT DEPT_CODE FROM DEPARTMENT WHERE PARTNAME = '인사관리부'), '교육팀');
INSERT INTO DEPARTMENT_TEAM(TEAM_CODE, DEPT_CODE, TEAM_NAME)
VALUES(SEQ_TEAM_CODE.NEXTVAL, (SELECT DEPT_CODE FROM DEPARTMENT WHERE PARTNAME = '인사관리부'), '복리후생팀');
INSERT INTO DEPARTMENT_TEAM(TEAM_CODE, DEPT_CODE, TEAM_NAME)
VALUES(SEQ_TEAM_CODE.NEXTVAL, (SELECT DEPT_CODE FROM DEPARTMENT WHERE PARTNAME = '인사관리부'), '인재개발팀');
-- 경영지원부 (경영)
INSERT INTO DEPARTMENT_TEAM(TEAM_CODE, DEPT_CODE, TEAM_NAME)
VALUES(SEQ_TEAM_CODE.NEXTVAL, (SELECT DEPT_CODE FROM DEPARTMENT WHERE PARTNAME = '경영지원부'), '경영지원1팀');
INSERT INTO DEPARTMENT_TEAM(TEAM_CODE, DEPT_CODE, TEAM_NAME)
VALUES(SEQ_TEAM_CODE.NEXTVAL, (SELECT DEPT_CODE FROM DEPARTMENT WHERE PARTNAME = '경영지원부'), '경영지원2팀');
INSERT INTO DEPARTMENT_TEAM(TEAM_CODE, DEPT_CODE, TEAM_NAME)
VALUES(SEQ_TEAM_CODE.NEXTVAL, (SELECT DEPT_CODE FROM DEPARTMENT WHERE PARTNAME = '경영지원부'), '사무관리팀');
INSERT INTO DEPARTMENT_TEAM(TEAM_CODE, DEPT_CODE, TEAM_NAME)
VALUES(SEQ_TEAM_CODE.NEXTVAL, (SELECT DEPT_CODE FROM DEPARTMENT WHERE PARTNAME = '경영지원부'), '전산팀');

-- 회계부 (회계)
INSERT INTO DEPARTMENT_TEAM(TEAM_CODE, DEPT_CODE, TEAM_NAME)
VALUES(SEQ_TEAM_CODE.NEXTVAL, (SELECT DEPT_CODE FROM DEPARTMENT WHERE PARTNAME = '회계부'), '예산팀');
INSERT INTO DEPARTMENT_TEAM(TEAM_CODE, DEPT_CODE, TEAM_NAME)
VALUES(SEQ_TEAM_CODE.NEXTVAL, (SELECT DEPT_CODE FROM DEPARTMENT WHERE PARTNAME = '회계부'), '세무팀');
INSERT INTO DEPARTMENT_TEAM(TEAM_CODE, DEPT_CODE, TEAM_NAME)
VALUES(SEQ_TEAM_CODE.NEXTVAL, (SELECT DEPT_CODE FROM DEPARTMENT WHERE PARTNAME = '회계부'), '재무팀');
INSERT INTO DEPARTMENT_TEAM(TEAM_CODE, DEPT_CODE, TEAM_NAME)
VALUES(SEQ_TEAM_CODE.NEXTVAL, (SELECT DEPT_CODE FROM DEPARTMENT WHERE PARTNAME = '회계부'), '결산팀');
--직급 INSERT
-- 직급 INSERT 쿼리문
INSERT INTO POSITION(POSITION_CODE, NAME_OF_POSITION) VALUES(SEQ_POSITION_CODE.NEXTVAL, '회장');
INSERT INTO POSITION(POSITION_CODE, NAME_OF_POSITION) VALUES(SEQ_POSITION_CODE.NEXTVAL, '전무');
INSERT INTO POSITION(POSITION_CODE, NAME_OF_POSITION) VALUES(SEQ_POSITION_CODE.NEXTVAL, '상무');
INSERT INTO POSITION(POSITION_CODE, NAME_OF_POSITION) VALUES(SEQ_POSITION_CODE.NEXTVAL, '대표이사');
INSERT INTO POSITION(POSITION_CODE, NAME_OF_POSITION) VALUES(SEQ_POSITION_CODE.NEXTVAL, '이사');
INSERT INTO POSITION(POSITION_CODE, NAME_OF_POSITION) VALUES(SEQ_POSITION_CODE.NEXTVAL, '부장');
INSERT INTO POSITION(POSITION_CODE, NAME_OF_POSITION) VALUES(SEQ_POSITION_CODE.NEXTVAL, '차장');
INSERT INTO POSITION(POSITION_CODE, NAME_OF_POSITION) VALUES(SEQ_POSITION_CODE.NEXTVAL, '과장');
INSERT INTO POSITION(POSITION_CODE, NAME_OF_POSITION) VALUES(SEQ_POSITION_CODE.NEXTVAL, '대리');
INSERT INTO POSITION(POSITION_CODE, NAME_OF_POSITION) VALUES(SEQ_POSITION_CODE.NEXTVAL, '주임');
INSERT INTO POSITION(POSITION_CODE, NAME_OF_POSITION) VALUES(SEQ_POSITION_CODE.NEXTVAL, '사원');

--인사 정보 INSERT
--대표이사
INSERT INTO PERSONNEL_INFORMATION(EMP_NO, NAME, POSITION_CODE, DEPT_CODE, TEAM_CODE, GENDER, SOCIAL_SECURITY_NO, PASSWORD, PHONE, EXTENSION_CALL, EMAIL, ADDRESS, HEIGHT, WEIGHT, BLOOD_TYPE, SALARY, BANK_NUMBER)
VALUES ('2024070001', '이경빈' ,4, 1, 1, 'M', '0104043323456', '3123456', '01071291234', '03154212345', 'person01@naver.com', '서울특별시 역삼역 3번출구', '158', '46', 'B', 3000000, '국민은행-94320200941212');
--인사 부장
INSERT INTO PERSONNEL_INFORMATION(EMP_NO, NAME, POSITION_CODE, DEPT_CODE, TEAM_CODE, GENDER, SOCIAL_SECURITY_NO, PASSWORD, PHONE, EXTENSION_CALL, EMAIL, ADDRESS, HEIGHT, WEIGHT, BLOOD_TYPE, SALARY, BANK_NUMBER)
VALUES ('2024070012', '정성호' ,6, 2, 2, 'M', '9604041323456', '1123456', '01021391234', '03154212347', 'person13@naver.com', '서울특별시 역삼역 길구봉구', '128', '66', 'A', 2000000, '신협은행-14322200941212');
-- 인사 사원
INSERT INTO PERSONNEL_INFORMATION(EMP_NO, NAME, POSITION_CODE, DEPT_CODE, TEAM_CODE, GENDER, SOCIAL_SECURITY_NO, PASSWORD, PHONE, EXTENSION_CALL, EMAIL, ADDRESS, HEIGHT, WEIGHT, BLOOD_TYPE, SALARY, BANK_NUMBER)
VALUES ('2024070092', '문준서' ,11, 2, 2, 'M', '0202023323426', '3122456', '01068791234', '03154212350', 'person92@naver.com', '경기도 포천시 태봉로 153, 포천아파트999동 1034호', '128', '98', 'O', 600000, '신협은행-14322200941552');

--인사 부장
INSERT INTO PERSONNEL_INFORMATION(EMP_NO, NAME, POSITION_CODE, DEPT_CODE, TEAM_CODE, GENDER, SOCIAL_SECURITY_NO, PASSWORD, PHONE, EXTENSION_CALL, EMAIL, ADDRESS, HEIGHT, WEIGHT, BLOOD_TYPE, SALARY, BANK_NUMBER)
VALUES ('2024070011', '나지원' ,6, 2, 3, 'F', '9602023323426', '2122499', '01062341234', '03154212350', 'person11@naver.com', '경기도 안양시 안양로 193, 안양아파트999동 1034호', '167', '50', 'A', 6000000, '농협은행-34322200941352');
-- 인사 사원
INSERT INTO PERSONNEL_INFORMATION(EMP_NO, NAME, POSITION_CODE, DEPT_CODE, TEAM_CODE, GENDER, SOCIAL_SECURITY_NO, PASSWORD, PHONE, EXTENSION_CALL, EMAIL, ADDRESS, HEIGHT, WEIGHT, BLOOD_TYPE, SALARY, BANK_NUMBER)
VALUES ('2024070093', '최가은' ,11, 2, 3, 'F', '9801011234567', '2122499', '01012345678', '0311234567', 'cgaeun93@company.com', '서울특별시 강남구 테헤란로 123, 강남빌딩 101호', '165', '50', 'B', 3200000, 'KB국민은행-12345678901234');
INSERT INTO PERSONNEL_INFORMATION(EMP_NO, NAME, POSITION_CODE, DEPT_CODE, TEAM_CODE, GENDER, SOCIAL_SECURITY_NO, PASSWORD, PHONE, EXTENSION_CALL, EMAIL, ADDRESS, HEIGHT, WEIGHT, BLOOD_TYPE, SALARY, BANK_NUMBER)
VALUES ('2024070091', '이해인' ,11, 2, 3, 'F', '9102022345678', '2122499', '01098765432', '0319876543', 'lhain91@company.com', '부산광역시 해운대구 우동 456, 해운대타워 202호', '160', '50', 'O', 3500000, '신한은행-98765432109876');

-- 회계 차장
INSERT INTO PERSONNEL_INFORMATION(EMP_NO, NAME, POSITION_CODE, DEPT_CODE, TEAM_CODE, GENDER, SOCIAL_SECURITY_NO, PASSWORD, PHONE, EXTENSION_CALL, EMAIL, ADDRESS, HEIGHT, WEIGHT, BLOOD_TYPE, SALARY, BANK_NUMBER)
VALUES ('2024070016', '최지연' ,7, 4, 11, 'F', '9704042327453', '2123356', '01016691234', '03154212349', 'person19@naver.com', '서울특별시 강남구 강남아파트 315동 1220호 ', '168', '66', 'AB', 6000000, '신협은행-14325550941682');
--회계 사원
INSERT INTO PERSONNEL_INFORMATION(EMP_NO, NAME, POSITION_CODE, DEPT_CODE, TEAM_CODE, GENDER, SOCIAL_SECURITY_NO, PASSWORD, PHONE, EXTENSION_CALL, EMAIL, ADDRESS, HEIGHT, WEIGHT, BLOOD_TYPE, SALARY, BANK_NUMBER)
VALUES ('2024070090', '이주형' ,11, 4, 11, 'M', '6003033327411', '1123456', '01042852221', '03154212340', 'person29@naver.com', '포항 노틸러스 103호', '230', '120', 'B', 130000, '국민은행-94325550933181');
-- 경영 사원
INSERT INTO PERSONNEL_INFORMATION(EMP_NO, NAME, POSITION_CODE, DEPT_CODE, TEAM_CODE, GENDER, SOCIAL_SECURITY_NO, PASSWORD, PHONE, EXTENSION_CALL, EMAIL, ADDRESS, RELIGION, HEIGHT, WEIGHT, BLOOD_TYPE, SALARY, BANK_NUMBER)
VALUES ('2024070099', '삼경빈' ,8, 3, 6, 'M', '6003033327911', '1123451', '01043852221', '03154212340', 'person99@naver.com', '인천광역시 101번거리', '무교' ,'230', '120', 'B', 130000, '국민은행-94325510933181');

INSERT INTO PERSONNEL_INFORMATION(EMP_NO, NAME, POSITION_CODE, DEPT_CODE, TEAM_CODE, GENDER, SOCIAL_SECURITY_NO, PASSWORD, PHONE, EXTENSION_CALL, EMAIL, ADDRESS, HEIGHT, WEIGHT, BLOOD_TYPE, SALARY, BANK_NUMBER)
VALUES ('2024070096', '최혜원' ,11, 4, 11, 'F', '0303034327453', '4123356', '01079652222', '03154212340', 'person20@naver.com', '안산드레스', '180', '70', 'AB', 100000, '신협은행-14325550949981');

--사원 더미데이터 생성
DECLARE
   v_name           VARCHAR2(50);
   v_position_code   NUMBER;
   v_dept_code       NUMBER;
   v_team_code       NUMBER;
   v_gender          CHAR(1);
   v_social_security CHAR(14);
   v_password        VARCHAR2(100);
   v_phone           CHAR(11);
   v_extension_call  CHAR(11);
   v_email           VARCHAR2(50);
   v_address         VARCHAR2(100);
   v_religion        VARCHAR2(20);
   v_height          NUMBER;
   v_weight          NUMBER;
   v_blood_type      VARCHAR2(3);
   v_salary          NUMBER;
   v_bank_number     VARCHAR2(30);

   -- 은행명 배열 선언
   v_banks SYS.ODCIVARCHAR2LIST := SYS.ODCIVARCHAR2LIST(
      '농협은행', '국민은행', '신한은행', '우리은행', '하나은행',
      '신협은행', 'IBK기업은행'
   );

   -- 성과 이름 배열 선언
   v_surnames SYS.ODCIVARCHAR2LIST := SYS.ODCIVARCHAR2LIST(
      '김', '이', '박', '최', '정', '강', '조', '윤', '임', '오', '서', '한', '구', '홍', '류', '천', '황'
   );

   v_first_names SYS.ODCIVARCHAR2LIST := SYS.ODCIVARCHAR2LIST(
      '수로', '민식', '지훈', '지우', '서연', '예진', '하늘', '지아', '준호', '영수', '영희', '민준',
      '유진', '지현', '보미', '진수', '정우', '다은', '승현', '세진', '수빈', '태훈', '수진', '하윤',
      '지연', '지우', '현호', '미연', '수정', '미연', '미진', '미주', '태수', '준우', '현진', '현준',
      '태식', '태주', '강산', '경민', '호민', '다연', '진수', '혜정', '민시', '주원', '주민', '현수',
      '태수', '민정', '민지', '명수', '재석', '지환', '준하', '유이', '사랑', '정민', '경수', '혜주'
   );

   v_max_employees NUMBER := 500;
   v_remaining_employees NUMBER := v_max_employees - 6;

BEGIN
   -- 포지션 코드 1부터 6까지 각각 한 명씩 생성
   FOR pos_code IN 1..6 LOOP
      v_name := v_surnames(TRUNC(DBMS_RANDOM.VALUE(1, v_surnames.COUNT))) || v_first_names(TRUNC(DBMS_RANDOM.VALUE(1, v_first_names.COUNT)));
      v_position_code := pos_code;
      v_dept_code := TRUNC(DBMS_RANDOM.VALUE(1, 6));
      v_team_code := TRUNC(DBMS_RANDOM.VALUE(1, 13));
      v_gender := CASE WHEN DBMS_RANDOM.VALUE(0, 1) < 0.5 THEN 'M' ELSE 'F' END;
      v_social_security := TO_CHAR(TRUNC(DBMS_RANDOM.VALUE(1000000000000, 9999999999999)));
      v_password := DBMS_RANDOM.STRING('X', 8) || DBMS_RANDOM.STRING('A', 2);
      v_phone := '010' || LPAD(TRUNC(DBMS_RANDOM.VALUE(10000000, 99999999)), 8, '0');
      v_extension_call := LPAD(TRUNC(DBMS_RANDOM.VALUE(10000000000, 99999999999)), 11, '0');
      v_email := DBMS_RANDOM.STRING('L', 5) || '@example.com';
      v_address := '서울특별시 ' || DBMS_RANDOM.STRING('L', 3) || '로 ' || DBMS_RANDOM.STRING('L', 3) || '동';
      v_religion := CASE TRUNC(DBMS_RANDOM.VALUE(1, 6))
                      WHEN 1 THEN '불교'
                      WHEN 2 THEN '천주교'
                      WHEN 3 THEN '개신교'
                      WHEN 4 THEN '원주율'
                      WHEN 5 THEN '유교'
                      ELSE '무교'
                    END;
      v_height := TRUNC(DBMS_RANDOM.VALUE(150, 200));
      v_weight := TRUNC(DBMS_RANDOM.VALUE(50, 100));
      v_blood_type := CASE TRUNC(DBMS_RANDOM.VALUE(1, 5))
                        WHEN 1 THEN 'A'
                        WHEN 2 THEN 'B'
                        WHEN 3 THEN 'AB'
                        WHEN 4 THEN 'O'
                        ELSE 'X'
                      END;
      v_salary := TRUNC(DBMS_RANDOM.VALUE(3000000, 7000000));
      v_bank_number := v_banks(TRUNC(DBMS_RANDOM.VALUE(1, v_banks.COUNT))) || '-' ||
                        LPAD(TRUNC(DBMS_RANDOM.VALUE(1000000000000000, 9999999999999999)), 14, '0');

      -- 데이터 삽입
      INSERT INTO PERSONNEL_INFORMATION (
         EMP_NO, NAME, POSITION_CODE, DEPT_CODE, TEAM_CODE, GENDER, SOCIAL_SECURITY_NO,
         PASSWORD, PHONE, EXTENSION_CALL, EMAIL, ADDRESS, RELIGION, HEIGHT, WEIGHT,
         BLOOD_TYPE, SALARY, BANK_NUMBER
      ) VALUES (
         TO_CHAR(SYSDATE, 'YYYYDD') || LPAD(SEQ_EMP_NO.NEXTVAL, 4, '0'), v_name, v_position_code,
         v_dept_code, v_team_code, v_gender, v_social_security, v_password, v_phone,
         v_extension_call, v_email, v_address, v_religion, v_height, v_weight, v_blood_type,
         v_salary, v_bank_number
      );
   END LOOP;

   -- 나머지 직원들 생성 (포지션 코드 7 이상)
   FOR i IN 1..v_remaining_employees LOOP
      v_name := v_surnames(TRUNC(DBMS_RANDOM.VALUE(1, v_surnames.COUNT))) || v_first_names(TRUNC(DBMS_RANDOM.VALUE(1, v_first_names.COUNT)));
      v_position_code := TRUNC(DBMS_RANDOM.VALUE(7, 11)); -- 7부터 시작
      v_dept_code := TRUNC(DBMS_RANDOM.VALUE(1, 6));
      v_team_code := TRUNC(DBMS_RANDOM.VALUE(1, 13));
      v_gender := CASE WHEN DBMS_RANDOM.VALUE(0, 1) < 0.5 THEN 'M' ELSE 'F' END;
      v_social_security := TO_CHAR(TRUNC(DBMS_RANDOM.VALUE(1000000000000, 9999999999999)));
      v_password := DBMS_RANDOM.STRING('X', 8) || DBMS_RANDOM.STRING('A', 2);
      v_phone := '010' || LPAD(TRUNC(DBMS_RANDOM.VALUE(10000000, 99999999)), 8, '0');
      v_extension_call := LPAD(TRUNC(DBMS_RANDOM.VALUE(10000000000, 99999999999)), 11, '0');
      v_email := DBMS_RANDOM.STRING('L', 5) || '@example.com';
      v_address := '서울특별시 ' || DBMS_RANDOM.STRING('L', 3) || '로 ' || DBMS_RANDOM.STRING('L', 3) || '동';
      v_religion := CASE TRUNC(DBMS_RANDOM.VALUE(1, 6))
                      WHEN 1 THEN '불교'
                      WHEN 2 THEN '천주교'
                      WHEN 3 THEN '개신교'
                      WHEN 4 THEN '원주율'
                      WHEN 5 THEN '유교'
                      ELSE '무교'
                    END;
      v_height := TRUNC(DBMS_RANDOM.VALUE(150, 200));
      v_weight := TRUNC(DBMS_RANDOM.VALUE(50, 100));
      v_blood_type := CASE TRUNC(DBMS_RANDOM.VALUE(1, 5))
                        WHEN 1 THEN 'A'
                        WHEN 2 THEN 'B'
                        WHEN 3 THEN 'AB'
                        WHEN 4 THEN 'O'
                        ELSE 'X'
                      END;
      v_salary := TRUNC(DBMS_RANDOM.VALUE(3000000, 7000000));
      v_bank_number := v_banks(TRUNC(DBMS_RANDOM.VALUE(1, v_banks.COUNT))) || '-' ||
                        LPAD(TRUNC(DBMS_RANDOM.VALUE(1000000000000000, 9999999999999999)), 14, '0');

      -- 데이터 삽입
      INSERT INTO PERSONNEL_INFORMATION (
         EMP_NO, NAME, POSITION_CODE, DEPT_CODE, TEAM_CODE, GENDER, SOCIAL_SECURITY_NO,
         PASSWORD, PHONE, EXTENSION_CALL, EMAIL, ADDRESS, RELIGION, HEIGHT, WEIGHT,
         BLOOD_TYPE, SALARY, BANK_NUMBER
      ) VALUES (
         TO_CHAR(SYSDATE, 'YYYYDD') || LPAD(SEQ_EMP_NO.NEXTVAL, 4, '0'), v_name, v_position_code,
         v_dept_code, v_team_code, v_gender, v_social_security, v_password, v_phone,
         v_extension_call, v_email, v_address, v_religion, v_height, v_weight, v_blood_type,
         v_salary, v_bank_number
      );
   END LOOP;

   COMMIT;
END;
/

COMMIT;

-------------------------------------------------------------------------------------------------------
--------------------------------------------------------
-- 결재 상태 리스트 더미 데이터 (APPR_STAGE_LIST)
--------------------------------------------------------
INSERT INTO APPR_STAGE_LIST (APPR_STAGE_NO, APPR_STAGE_NAME) VALUES (SEQ_APPR_STAGE_LIST.NEXTVAL, '대기');
INSERT INTO APPR_STAGE_LIST (APPR_STAGE_NO, APPR_STAGE_NAME) VALUES (SEQ_APPR_STAGE_LIST.NEXTVAL, '승인');
INSERT INTO APPR_STAGE_LIST (APPR_STAGE_NO, APPR_STAGE_NAME) VALUES (SEQ_APPR_STAGE_LIST.NEXTVAL, '반려');

--------------------------------------------------------
-- 문서 상태 리스트 더미 데이터 (DOC_STATUS_LIST)
--------------------------------------------------------
INSERT INTO DOC_STATUS_LIST (DOC_STATUS_NO, DOC_STATUS_NAME) VALUES (SEQ_DOC_STATUS_LIST.NEXTVAL, '임시저장');
INSERT INTO DOC_STATUS_LIST (DOC_STATUS_NO, DOC_STATUS_NAME) VALUES (SEQ_DOC_STATUS_LIST.NEXTVAL, '기안');
INSERT INTO DOC_STATUS_LIST (DOC_STATUS_NO, DOC_STATUS_NAME) VALUES (SEQ_DOC_STATUS_LIST.NEXTVAL, '종결');
INSERT INTO DOC_STATUS_LIST (DOC_STATUS_NO, DOC_STATUS_NAME) VALUES (SEQ_DOC_STATUS_LIST.NEXTVAL, '반려');
INSERT INTO DOC_STATUS_LIST (DOC_STATUS_NO, DOC_STATUS_NAME) VALUES (SEQ_DOC_STATUS_LIST.NEXTVAL, '결재취소');

--------------------------------------------------------
-- 문서 템플릿 카테고리 더미 데이터 (DOC_TEMPLATE_CATEGORY)
--------------------------------------------------------
INSERT INTO DOC_TEMPLATE_CATEGORY (CATEGORY_NO, NAME) VALUES (SEQ_DOC_TEMPLATE_CATEGORY.NEXTVAL, '경비 및 지출 관리');
INSERT INTO DOC_TEMPLATE_CATEGORY (CATEGORY_NO, NAME) VALUES (SEQ_DOC_TEMPLATE_CATEGORY.NEXTVAL, '인사 및 근태관리');
INSERT INTO DOC_TEMPLATE_CATEGORY (CATEGORY_NO, NAME) VALUES (SEQ_DOC_TEMPLATE_CATEGORY.NEXTVAL, '프로젝트 및 업무관리');
INSERT INTO DOC_TEMPLATE_CATEGORY (CATEGORY_NO, NAME) VALUES (SEQ_DOC_TEMPLATE_CATEGORY.NEXTVAL, '교육 및 지원');
INSERT INTO DOC_TEMPLATE_CATEGORY (CATEGORY_NO, NAME) VALUES (SEQ_DOC_TEMPLATE_CATEGORY.NEXTVAL, '기타');

--------------------------------------------------------
-- 문서 템플릿 더미 데이터 (DOC_TEMPLATE)
--------------------------------------------------------
INSERT INTO DOC_TEMPLATE (TEMPLATE_NO, CATEGORY_NO, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOC_TEMPLATE.NEXTVAL, 1, '구매 품의서', '<p><br></p><h1>구매 품의서</h1><h5>* 아래와 같이 구매를 신청하오니 검토 후 결재 하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>구매 항목:</b></p><p><b><br></b></p><p><b>구매 수량:</b></p><p><b><br></b></p><p><b>구매 금액:</b></p><p><b><br></b></p><p><b>구매 이유:</b></p><p><b><br></b></p><p><b>요청 납기 일정:</b></p><p><b><br></b></p><p><b>첨부 자료: (견적서, 카탈로그)</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 필요 시 견적서, 카탈로그 등의 관련 자료를 반드시 첨부합니다.<br>* 구매 품의서는 필요한 물품이 생길 때 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', SYSDATE);
INSERT INTO DOC_TEMPLATE (TEMPLATE_NO, CATEGORY_NO, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOC_TEMPLATE.NEXTVAL, 1, '지출 결의서 (100만원 이하)', '<h1><br></h1><h1>지출 결의서 (100만원 이하)</h1><h5>* 아래와 같이 지출 내역을 작성하고, 검토 후 결재를 요청드립니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>지출 항목:</b></p><p><b><br></b></p><p><b>지출 금액:</b></p><p><b><br></b></p><p><b>지출 세부 내역:</b></p><p><b><br></b></p><p><b>총 합계:</b></p><p><b><br></b></p><p><b>첨부 파일: (영수증)</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 첨부 파일은 필수이며, 지출 내역을 뒷받침할 수 있는 자료를 반드시 포함해야 합니다.<br>* 지출 결의서는 지출이 발생한 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', SYSDATE);
INSERT INTO DOC_TEMPLATE (TEMPLATE_NO, CATEGORY_NO, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOC_TEMPLATE.NEXTVAL, 1, '지출 결의서 (100만원 이상)', '<h1><br></h1><h1>지출 결의서 (100만원 이상)</h1><h5>* 아래와 같이 지출 내역을 작성하고, 검토 후 결재를 요청드립니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>지출 항목:</b></p><p><b><br></b></p><p><b>지출 금액:</b></p><p><b><br></b></p><p><b>지출 세부 내역:</b></p><p><b><br></b></p><p><b>총 합계:</b></p><p><b><br></b></p><p><b>첨부 파일: (영수증)</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 첨부 파일은 필수이며, 지출 내역을 뒷받침할 수 있는 자료를 반드시 포함해야 합니다.<br>* 지출 결의서는 지출이 발생한 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', SYSDATE);
INSERT INTO DOC_TEMPLATE (TEMPLATE_NO, CATEGORY_NO, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOC_TEMPLATE.NEXTVAL, 1, '지출 결의서 (1000만원 이상)', '<h1><br></h1><h1>지출 결의서 (1000만원 이상)</h5>* 아래와 같이 지출 내역을 작성하고, 검토 후 결재를 요청드립니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>지출 항목:</b></p><p><b><br></b></p><p><b>지출 금액:</b></p><p><b><br></b></p><p><b>지출 세부 내역:</b></p><p><b><br></b></p><p><b>총 합계:</b></p><p><b><br></b></p><p><b>첨부 파일: (영수증)</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 첨부 파일은 필수이며, 지출 내역을 뒷받침할 수 있는 자료를 반드시 포함해야 합니다.<br>* 지출 결의서는 지출이 발생한 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', SYSDATE);
INSERT INTO DOC_TEMPLATE (TEMPLATE_NO, CATEGORY_NO, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOC_TEMPLATE.NEXTVAL, 1, 'IT 자산 구매 요청', '<h2><br></h2><h1>IT 자산 구매 요청</h1><h5>* 아래와 같이 구매를 신청하오니 검토 후 재가하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>사용자 :</b></p><p><b><br></b></p><p><b>사용 목적: 예)신규 입사자 장비 지원을 위해 필요</b></p><p><b><br></b></p><p><b>구매요청 품목 : 다음중 택 1(사무용 PC, 연구용 PC, 조립PC(사양기재), 노트북, 모니터, 전산 소모품)</b></p><p><b><br></b></p><p><b>구매 수량:</b></p><p><b><br></b></p><p><b>구매 금액:</b></p><p><b><br></b></p><p><b>OS 설치여부:</b></p><p><b><br></b></p><p><b>기타 사항:</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><br></p><h5>* 기기 호환성과 기존 장비와의 연동 여부를 확인해야 합니다.<br>* 설치 및 설정 관련 지원이 필요한 경우, IT 지원팀에 미리 요청해야 합니다.<br>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 필요 시 견적서 등 관련 자료를 첨부합니다.<br>* IT 자산 구매 요청서는 필요 발생 시 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', SYSDATE);
INSERT INTO DOC_TEMPLATE (TEMPLATE_NO, CATEGORY_NO, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOC_TEMPLATE.NEXTVAL, 1, '출장비 정산서(국내)', '<h2><br></h2><h1>출장비 정산서 (국내)</h1><h5>* 아래와 같이 출장비를 정산하오니 검토 후 결재하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>출장 목적:</b></p><p><b><br></b></p><p><b>출장 기간:</b></p><p><b><br></b></p><p><b>출장 장소:</b></p><p><b><br></b></p><p><b>총 출장비: 1,500,000원</b></p><p><b><br></b></p><p><b>세부 항목:</b></p><p><span style="white-space: normal;"><b>예) 교통비: 500,000원</b></span></p><p><span style="white-space: normal;"><b>숙박비: 600,000원</b></span></p><p><span style="white-space: normal;"><b>식비: 400,000원</b></span></p><p><b><br></b></p><p><b>첨부 파일: (교통비 영수증, 숙박비 영수증)</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><h5><br>* 모든 영수증과 증빙 자료는 원본을 제출하며, 사본은 기록으로 보관해야 합니다.<br>* 출장 일정과 관련된 모든 비용을 명확히 기재해야 합니다.<br>* 출장비 정산서는 출장 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', SYSDATE);
INSERT INTO DOC_TEMPLATE (TEMPLATE_NO, CATEGORY_NO, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOC_TEMPLATE.NEXTVAL, 1, '출장비 정산서(해외)', '<h2><br></h2><h1>출장비 정산서 (해외)</h1><h5>* 아래와 같이 출장비를 정산하오니 검토 후 결재하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>출장 목적:</b></p><p><b><br></b></p><p><b>출장 기간:</b></p><p><b><br></b></p><p><b>출장 장소:</b></p><p><b><br></b></p><p><b>총 출장비: 1,500,000원</b></p><p><b><br></b></p><p><b>세부 항목:</b></p><p><span style="white-space: normal;"><b>예) 교통비: 500,000원</b></span></p><p><span style="white-space: normal;"><b>숙박비: 600,000원</b></span></p><p><span style="white-space: normal;"><b>식비: 400,000원</b></span></p><p><b><br></b></p><p><b>첨부 파일: (교통비 영수증, 숙박비 영수증)</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><h5><br>* 모든 영수증과 증빙 자료는 원본을 제출하며, 사본은 기록으로 보관해야 합니다.<br>* 출장 일정과 관련된 모든 비용을 명확히 기재해야 합니다.<br>* 출장비 정산서는 출장 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', SYSDATE);
INSERT INTO DOC_TEMPLATE (TEMPLATE_NO, CATEGORY_NO, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOC_TEMPLATE.NEXTVAL, 2, '휴가 신청서', '휴가 신청서 내용', SYSDATE);
INSERT INTO DOC_TEMPLATE (TEMPLATE_NO, CATEGORY_NO, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOC_TEMPLATE.NEXTVAL, 2, '병가 신청서(일반)', '병가 신청서(일반) 내용', SYSDATE);
INSERT INTO DOC_TEMPLATE (TEMPLATE_NO, CATEGORY_NO, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOC_TEMPLATE.NEXTVAL, 2, '병가 신청서(장기)', '병가 신청서(장기) 내용', SYSDATE);
INSERT INTO DOC_TEMPLATE (TEMPLATE_NO, CATEGORY_NO, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOC_TEMPLATE.NEXTVAL, 2, '시간 외 수당 청구서', '시간 외 수당 청구서 내용', SYSDATE);
INSERT INTO DOC_TEMPLATE (TEMPLATE_NO, CATEGORY_NO, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOC_TEMPLATE.NEXTVAL, 2, '재택근무 신청서', '<h2><br></h2><h1>재택근무 신청서</h1><h5>* 아래와 같이 재택근무를 신청하오니 검토 후 결재하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>재택근무 기간:</b></p><p><b><br></b></p><p><b>재택근무 사유: </b>예) 개인 사정으로 인한 재택근무 필요</p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 재택근무 도구와 필요한 IT 지원 사항을 명시합니다.<br>* 재택근무 신청서는 가능한 한 조기에 제출하여야 합니다.<br>* 재택근무 기간 동안의 업무 계획과 보고 방식을 명확히 하여야 합니다.</h5><p><br></p>', SYSDATE);
INSERT INTO DOC_TEMPLATE (TEMPLATE_NO, CATEGORY_NO, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOC_TEMPLATE.NEXTVAL, 2, '퇴사 신청서(사직서)', '<p><br></p><h1>퇴사 신청서 (사직서)</h1><h5>* 아래와 같이 퇴사를 신청하오니 검토 후 결재하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>퇴사 예정일:</b></p><p><b><br></b></p><p><b>퇴사 사유: </b>개인 사유 (진로 변경)</p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><br></p><h5>* 잔여 연차 및 급여 정산 사항을 확인합니다.<br>* 회사 자산 반납 절차를 명시합니다.<br>* 퇴사 예정일은 회사의 인수인계 기간을 고려하여 충분한 시간을 두고 작성합니다.<br>* 퇴사 사유는 간략하게 작성하되, 필요한 경우 인사팀과 별도로 상담합니다.</h5>', SYSDATE);
INSERT INTO DOC_TEMPLATE (TEMPLATE_NO, CATEGORY_NO, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOC_TEMPLATE.NEXTVAL, 3, '프로젝트 제안서', '<p><br></p><h1>프로젝트 제안서</h1><h5>* 아래와 같이 프로젝트를 제안하오니 검토 후 재가하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>프로젝트 명:</b></p><p><b><br></b></p><p><b>프로젝트 목적:</b></p><p><b><br></b></p><p><b>프로젝트 기간:</b></p><p><b><br></b></p><p><b>프로젝트 예산:</b></p><p><b><br></b></p><p><b>프로젝트 세부 내용:</b></p><p><b>예 )</b></p><p><b>요구사항 분석: 2023년 7월 1일~7월 31일</b></p><p><b>설계 및 개발: 2023년 8월 1일~11월 30일</b></p><p><b>테스트 및 배포: 2023년 12월 1일~12월 31일</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 프로젝트 팀 구성과 각 팀원의 역할을 명시합니다.<br>* 성과 측정 지표를 설정하고, 프로젝트 성공 여부를 평가할 기준을 명확히 합니다.<br>* 프로젝트 제안서는 충분한 사전 조사와 계획을 바탕으로 작성해야 합니다.<br>* 필요한 경우 추가 자료(예: 시장 조사, 예상 수익 분석)를 첨부합니다.</h5>', SYSDATE);
INSERT INTO DOC_TEMPLATE (TEMPLATE_NO, CATEGORY_NO, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOC_TEMPLATE.NEXTVAL, 3, '회의록', '<p><br></p><h1>회의록</h1><h5>* 회의록 작성 관련 문의는 기획팀으로 연락 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>회의 일자:</b></p><p><b><br></b></p><p><b>회의 장소:</b></p><p><b><br></b></p><p><b>참석자:</b></p><p><b><br></b></p><p><b>회의 내용:</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><br></p><h5>* 의결 사항과 그에 따른 후속 조치를 명확히 기재합니다.<br>* 회의록 검토를 위한 일정과 피드백 절차를 명시합니다.<br>* 중요한 논의 사항과 결론은 누락 없이 기록해야 합니다.<br>* 회의록은 회의 직후 작성하여 참석자들과 공유(참조자)합니다.</h5>', SYSDATE);
INSERT INTO DOC_TEMPLATE (TEMPLATE_NO, CATEGORY_NO, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOC_TEMPLATE.NEXTVAL, 4, '교육 신청서 (사내, 단기 국내 위탁)', '<p><br></p><h1>교육 신청서 (사내, 단기 국내 위탁)</h1><h5>* 아래와 같이 직무 교육을 신청하오니 검토 후 재가하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>교육 명:</b></p><p><b><br></b></p><p><b>교육 기간:</b></p><p><b><br></b></p><p><b>교육 장소:</b></p><p><b><br></b></p><p><b>교육 목적:</b></p><p><b><br></b></p><p><b>첨부 파일: </b>(교육 자료)</p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><br></p><h5>* 교육 내용이 직무와 관련이 있는지 확인하고, 사전 준비가 필요한 경우 미리 준비합니다.<br>* 교육 후 보고서를 작성하여 상사에게 제출할 계획을 수립합니다.<br>* 교육 신청서는 가능한 한 조기에 제출하여야 합니다.<br>* 필요 시 교육 관련 자료를 첨부합니다.</h5>', SYSDATE);
INSERT INTO DOC_TEMPLATE (TEMPLATE_NO, CATEGORY_NO, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOC_TEMPLATE.NEXTVAL, 4, '교육 신청서 (장기 국내 위탁, 해외 파견)', '<p><br></p><h1>교육 신청서 (장기 국내 위탁, 해외 파견)</h1><h5>* 아래와 같이 직무 교육을 신청하오니 검토 후 재가하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>교육 명:</b></p><p><b><br></b></p><p><b>교육 기간:</b></p><p><b><br></b></p><p><b>교육 장소:</b></p><p><b><br></b></p><p><b>교육 목적:</b></p><p><b><br></b></p><p><b>첨부 파일: </b>(교육 자료)</p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><br></p><h5>* 교육 내용이 직무와 관련이 있는지 확인하고, 사전 준비가 필요한 경우 미리 준비합니다.<br>* 교육 후 보고서를 작성하여 상사에게 제출할 계획을 수립합니다.<br>* 교육 신청서는 가능한 한 조기에 제출하여야 합니다.<br>* 필요 시 교육 관련 자료를 첨부합니다.</h5>', SYSDATE);


--------------------------------------------------------
-- 기본 결재선 템플릿 더미 데이터 (APPR_LINE_TEMPLATE)
--------------------------------------------------------

-- 결재선 템플릿 추가
INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, TEMPLATE_NO, APPR_LINE_NAME, CREATED_DATE) VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, 1, '구매 품의서 결재선', SYSDATE);
INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, TEMPLATE_NO, APPR_LINE_NAME, CREATED_DATE) VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, 2, '지출 결의서 (100만원 이하) 결재선', SYSDATE);
INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, TEMPLATE_NO, APPR_LINE_NAME, CREATED_DATE) VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, 3, '지출 결의서 (100만원 이상) 결재선', SYSDATE);
INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, TEMPLATE_NO, APPR_LINE_NAME, CREATED_DATE) VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, 4, '지출 결의서 (1000만원 이상) 결재선', SYSDATE);
INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, TEMPLATE_NO, APPR_LINE_NAME, CREATED_DATE) VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, 5, 'IT 자산 구매 요청', SYSDATE);
INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, TEMPLATE_NO, APPR_LINE_NAME, CREATED_DATE) VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, 6, '출장비 정산서(국내) 결재선', SYSDATE);
INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, TEMPLATE_NO, APPR_LINE_NAME, CREATED_DATE) VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, 7, '출장비 정산서(해외) 결재선', SYSDATE);
INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, TEMPLATE_NO, APPR_LINE_NAME, CREATED_DATE) VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, 8, '휴가 신청서 결재선', SYSDATE);
INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, TEMPLATE_NO, APPR_LINE_NAME, CREATED_DATE) VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, 9, '병가 신청서(일반) 결재선', SYSDATE);
INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, TEMPLATE_NO, APPR_LINE_NAME, CREATED_DATE) VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, 10, '병가 신청서(장기) 결재선', SYSDATE);
INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, TEMPLATE_NO, APPR_LINE_NAME, CREATED_DATE) VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, 11, '시간 외 수당 청구서 결재선', SYSDATE);
INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, TEMPLATE_NO, APPR_LINE_NAME, CREATED_DATE) VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, 12, '재택근무 신청서 결재선', SYSDATE);
INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, TEMPLATE_NO, APPR_LINE_NAME, CREATED_DATE) VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, 13, '퇴사 신청서(사직서) 결재선', SYSDATE);
INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, TEMPLATE_NO, APPR_LINE_NAME, CREATED_DATE) VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, 14, '프로젝트 제안서 결재선', SYSDATE);
INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, TEMPLATE_NO, APPR_LINE_NAME, CREATED_DATE) VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, 15, '회의록 결재선', SYSDATE);
INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, TEMPLATE_NO, APPR_LINE_NAME, CREATED_DATE) VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, 16, '교육 신청서(사내, 단기국내위탁) 결재선', SYSDATE);
INSERT INTO APPR_LINE_TEMPLATE (APPR_LINE_NO, TEMPLATE_NO, APPR_LINE_NAME, CREATED_DATE) VALUES (SEQ_APPR_LINE_TEMPLATE.NEXTVAL, 17, '교육 신청서(장기 국내 위탁, 해외 파견) 결재선', SYSDATE);


--------------------------------------------------------
-- 기본 결재자 정보 더미 데이터 (APPROVER_INFO)
--------------------------------------------------------
-- 구매 품의서 결재선 (APPR_LINE_NO 1) - 팀장 → [회계] 담당자
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070012, 1, 1, 1); -- 본인팀 팀장 결재
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070016, 1, 2, 1); -- 회계 차장(팀장) 최지연

-- 지출 결의서 결재선 (APPR_LINE_NO 2) - 100만원 이하: 팀장
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070012, 2, 1, 1); -- 본인팀 팀장 결재

-- 지출 결의서 결재선 (APPR_LINE_NO 3) - 100만원 이상: 팀장 → [회계] 담당자
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070012, 3, 1, 1); -- 본인팀 팀장 결재
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070016 , 3, 2, 1); -- 회계 차장(팀장) 최지연

-- 지출 결의서 결재선 (APPR_LINE_NO 4) - 1000만원 이상: 합의자 → 팀장 → [회계] 담당자 → 대표이사
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070093, 4, 1, 2); -- 합의자 문준서
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070012, 4, 2, 1); -- 본인팀 팀장 결재
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070016, 4, 3, 1); -- 회계 차장(팀장) 최지연
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070001, 4, 4, 1); -- 대표이사 이경빈

-- IT 자산 구매 요청 결재선 (APPR_LINE_NO 5) - 합의자 → 팀장 → [회계] 담당자 → 대표이사
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070093, 5, 1, 2); -- 합의자 문준서
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070012, 5, 2, 1); -- 본인팀 팀장 결재
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070016, 5, 3, 1); -- 회계 차장(팀장) 최지연
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070001, 5, 4, 1); -- 대표이사 이경빈

-- 출장비 정산서(국내) 결재선 (APPR_LINE_NO 6) - 팀장 → [인사] 담당자
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070016, 6, 1, 1); -- 회계 차장(팀장) 최지연
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070012, 6, 2, 1); -- 인사 부장(팀장) 정성호

-- 출장비 정산서(해외) 결재선 (APPR_LINE_NO 7) - 합의자 → 팀장 → [인사] 담당자 → 대표이사
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070090, 7, 1, 2); -- 합의자 이주형
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070016, 7, 2, 1); -- 회계 차장(팀장) 최지연
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070012, 7, 3, 1); -- 인사 부장(팀장) 정성호
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070001, 7, 4, 1); -- 대표이사 이경빈

-- 휴가 신청서 결재선 (APPR_LINE_NO 8) - 팀장
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070012, 8, 1, 1); -- 인사 부장(팀장) 정성호

-- 병가 신청서(일반) 결재선 (APPR_LINE_NO 9) - 팀장
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070012, 9, 1, 1); -- 인사 부장(팀장) 정성호

-- 병가 신청서(장기) 결재선 (APPR_LINE_NO 10) - 팀장 → [인사] 담당자
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070016, 10, 1, 1); -- 회계 차장(팀장) 최지연
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070012, 10, 2, 1); -- 인사 부장(팀장) 정성호

-- 시간 외 수당 청구서 결재선 (APPR_LINE_NO 11) - 팀장 → [인사] 담당자
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070016, 11, 1, 1); -- 회계 차장(팀장) 최지연
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070012, 11, 2, 1); -- 인사 부장(팀장) 정성호

-- 재택근무 신청서 결재선 (APPR_LINE_NO 12) - 팀장 → [인사] 담당자
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070016, 12, 1, 1); -- 회계 차장(팀장) 최지연
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070012, 12, 2, 1); -- 인사 부장(팀장) 정성호

-- 퇴사 신청서(사직서) 결재선 (APPR_LINE_NO 13) - 합의자 → 팀장 → [인사] 담당자 → 대표이사
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070016, 13, 1, 1); -- 회계 차장(팀장) 최지연
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070012, 13, 2, 1); -- 인사 부장(팀장) 정성호
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070001, 13, 3, 1); -- 대표이사 이경빈

-- 프로젝트 제안서 결재선 (APPR_LINE_NO 14) - 합의자 → 팀장 → 대표이사
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070090, 14, 1, 2); -- 합의자 이주형
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070016, 14, 2, 1); -- 회계 차장(팀장) 최지연
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070001, 14, 3, 1); -- 대표이사 이경빈

-- 회의록 결재선 (APPR_LINE_NO 15) - 팀장
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070012, 15, 1, 1); -- 인사 부장(팀장) 정성호

-- 교육 신청서(사내, 단기국내위탁) 결재선 (APPR_LINE_NO 16) - 팀장 → [인사] 담당자
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070016, 16, 1, 1); -- 회계 차장(팀장) 최지연
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070012, 16, 2, 1); -- 인사 부장(팀장) 정성호

-- 교육 신청서(장기국내위탁, 해외파견) 결재선 (APPR_LINE_NO 17) - 합의자 → 팀장 → [인사] 담당자 → 대표이사
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070090, 17, 1, 2); -- 합의자 이주형
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070016, 17, 2, 1); -- 회계 차장(팀장) 최지연
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070012, 17, 3, 1); -- 인사 부장(팀장) 정성호
INSERT INTO APPROVER_INFO (APPROVER_INFO_NO, APPROVER_NO, APPR_LINE_NO, SEQ, APPROVER_CLASSIFICATION_NO) VALUES (SEQ_APPROVER_INFO.NEXTVAL, 2024070001, 17, 4, 1); -- 대표이사 이경빈

COMMIT;

--------------------------------------------------------
-- 문서 더미 데이터 (DOCUMENT)
--------------------------------------------------------
-- 임시저장 상태
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE)
VALUES (SEQ_DOCUMENT.NEXTVAL, 1, 2024070091, 1, '구매 품의서 - 사무용 의자 50개', '<p><br></p><h1>구매 품의서</h1><h5>* 아래와 같이 구매를 신청하오니 검토 후 결재 하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>구매 항목: 사무용 의자</b></p><p><b><br></b></p><p><b>구매 수량: 50</b></p><p><b><br></b></p><p><b>구매 금액: 5,000,000원</b></p><p><b><br></b></p><p><b>구매 이유: 기존 의자 교체</b></p><p><b><br></b></p><p><b>요청 납기 일정: 2024-08-01</b></p><p><b><br></b></p><p><b>첨부 자료: 견적서, 카탈로그</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 필요 시 견적서, 카탈로그 등의 관련 자료를 반드시 첨부합니다.<br>* 구매 품의서는 필요한 물품이 생길 때 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', SYSDATE);
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE)
VALUES (SEQ_DOCUMENT.NEXTVAL, 2, 2024070091, 1, '지출 결의서 - 사무용품 구입', '<h1>지출 결의서 (100만원 이하)</h1><h5>* 아래와 같이 지출 내역을 작성하고, 검토 후 결재를 요청드립니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>지출 항목: 사무용품 구입</b></p><p><b><br></b></p><p><b>지출 금액: 500,000원</b></p><p><b><br></b></p><p><b>지출 세부 내역: A4 용지, 볼펜, 파일</b></p><p><b><br></b></p><p><b>총 합계: 500,000원</b></p><p><b><br></b></p><p><b>첨부 파일: 영수증</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 첨부 파일은 필수이며, 지출 내역을 뒷받침할 수 있는 자료를 반드시 포함해야 합니다.<br>* 지출 결의서는 지출이 발생한 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', SYSDATE);
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE)
VALUES (SEQ_DOCUMENT.NEXTVAL, 3, 2024070091, 1, '지출 결의서 - 사무용 의자 구입', '<h1>지출 결의서 (100만원 이상)</h1><h5>* 아래와 같이 지출 내역을 작성하고, 검토 후 결재를 요청드립니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>지출 항목: 사무용 의자</b></p><p><b><br></b></p><p><b>지출 금액: 2,000,000원</b></p><p><b><br></b></p><p><b>지출 세부 내역: 의자 본체, 조립 부품</b></p><p><b><br></b></p><p><b>총 합계: 2,000,000원</b></p><p><b><br></b></p><p><b>첨부 파일: 영수증</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 첨부 파일은 필수이며, 지출 내역을 뒷받침할 수 있는 자료를 반드시 포함해야 합니다.<br>* 지출 결의서는 지출이 발생한 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', SYSDATE);
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE)
VALUES (SEQ_DOCUMENT.NEXTVAL, 4, 2024070091, 1, '지출 결의서 - 서버 장비 구입', '<h1>지출 결의서 (1000만원 이상)</h1><h5>* 아래와 같이 지출 내역을 작성하고, 검토 후 결재를 요청드립니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>지출 항목: 서버 장비</b></p><p><b><br></b></p><p><b>지출 금액: 15,000,000원</b></p><p><b><br></b></p><p><b>지출 세부 내역: 서버 본체, 부품</b></p><p><b><br></b></p><p><b>총 합계: 15,000,000원</b></p><p><b><br></b></p><p><b>첨부 파일: 영수증</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 첨부 파일은 필수이며, 지출 내역을 뒷받침할 수 있는 자료를 반드시 포함해야 합니다.<br>* 지출 결의서는 지출이 발생한 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', SYSDATE);
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE)
VALUES (SEQ_DOCUMENT.NEXTVAL, 5, 2024070091, 1, 'IT 자산 구매 요청 - 노트북 10대', '<h1>IT 자산 구매 요청</h1><h5>* 아래와 같이 구매를 신청하오니 검토 후 재가하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>사용자 : 신입 사원</b></p><p><b><br></b></p><p><b>사용 목적: 신규 입사자 장비 지원을 위해 필요</b></p><p><b><br></b></p><p><b>구매요청 품목 : 노트북</b></p><p><b><br></b></p><p><b>구매 수량: 10</b></p><p><b><br></b></p><p><b>구매 금액: 15,000,000원</b></p><p><b><br></b></p><p><b>OS 설치여부: </b></p><p><b><br></b></p><p><b>기타 사항: 없음</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><br></p><h5>* 기기 호환성과 기존 장비와의 연동 여부를 확인해야 합니다.<br>* 설치 및 설정 관련 지원이 필요한 경우, IT 지원팀에 미리 요청해야 합니다.<br>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 필요 시 견적서 등 관련 자료를 첨부합니다.<br>* IT 자산 구매 요청서는 필요 발생 시 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', SYSDATE);
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE)
VALUES (SEQ_DOCUMENT.NEXTVAL, 16, 2024070096, 1, '교육 신청서 (사내, 단기 국내 위탁) - 기본 교육', '<h1>교육 신청서 (사내, 단기 국내 위탁)</h1><h5>* 아래와 같이 직무 교육을 신청하오니 검토 후 재가하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>교육 명: 기본 직무 교육</b></p><p><b><br></b></p><p><b>교육 기간: 2024-08-01 ~ 2024-08-05</b></p><p><b><br></b></p><p><b>교육 장소: 본사 교육실</b></p><p><b><br></b></p><p><b>교육 목적: 직무 능력 향상</b></p><p><b><br></b></p><p><b>첨부 파일: 교육 자료</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><br></p><h5>* 교육 내용이 직무와 관련이 있는지 확인하고, 사전 준비가 필요한 경우 미리 준비합니다.<br>* 교육 후 보고서를 작성하여 상사에게 제출할 계획을 수립합니다.<br>* 교육 신청서는 가능한 한 조기에 제출하여야 합니다.<br>* 필요 시 교육 관련 자료를 첨부합니다.</h5><p><br></p>', SYSDATE);
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE)
VALUES (SEQ_DOCUMENT.NEXTVAL, 6, 2024070096, 1, '출장비 정산서(국내) - 서울 출장', '<h1>출장비 정산서 [국내]</h1><h5>* 아래와 같이 출장비를 정산하오니 검토 후 결재하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>출장 목적: 서울 본사 회의 참석</b></p><p><b><br></b></p><p><b>출장 기간: 2024-08-01 ~ 2024-08-03</b></p><p><b><br></b></p><p><b>출장 장소: 서울 본사</b></p><p><b><br></b></p><p><b>총 출장비: 1,500,000원</b></p><p><b><br></b></p><p><b>세부 항목:</b></p><p><span style="white-space: normal;"><b>    교통비: 500,000원</b></span></p><p><span style="white-space: normal;"><b>    숙박비: 600,000원</b></span></p><p><span style="white-space: normal;"><b>    식비: 400,000원</b></span></p><p><b><br></b></p><p><b>첨부 파일: (교통비 영수증, 숙박비 영수증)</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><h5><br>* 모든 영수증과 증빙 자료는 원본을 제출하며, 사본은 기록으로 보관해야 합니다.<br>* 출장 일정과 관련된 모든 비용을 명확히 기재해야 합니다.<br>* 출장비 정산서는 출장 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', SYSDATE);
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE)
VALUES (SEQ_DOCUMENT.NEXTVAL, 7, 2024070096, 1, '출장비 정산서(해외) - 미국 출장', '<h1>출장비 정산서 (해외)</h1><h5>* 아래와 같이 출장비를 정산하오니 검토 후 결재하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>출장 목적: 미국 본사 회의 참석</b></p><p><b><br></b></p><p><b>출장 기간: 2024-08-01 ~ 2024-08-07</b></p><p><b><br></b></p><p><b>출장 장소: 미국 뉴욕</b></p><p><b><br></b></p><p><b>총 출장비: 5,000,000원</b></p><p><b><br></b></p><p><b>세부 항목:</b></p><p><span style="white-space: normal;"><b>    항공비: 3,000,000원</b></span></p><p><span style="white-space: normal;"><b>    숙박비: 1,200,000원</b></span></p><p><span style="white-space: normal;"><b>    식비: 800,000원</b></span></p><p><b><br></b></p><p><b>첨부 파일: (항공비 영수증, 숙박비 영수증)</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><h5><br>* 모든 영수증과 증빙 자료는 원본을 제출하며, 사본은 기록으로 보관해야 합니다.<br>* 출장 일정과 관련된 모든 비용을 명확히 기재해야 합니다.<br>* 출장비 정산서는 출장 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', SYSDATE);

-- 기안 상태
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 1, 2024070091, 2, '구매 품의서 - 사무용 책상 30개', '<p><br></p><h1>구매 품의서</h1><h5>* 아래와 같이 구매를 신청하오니 검토 후 결재 하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>구매 항목: 사무용 책상</b></p><p><b><br></b></p><p><b>구매 수량: 30</b></p><p><b><br></b></p><p><b>구매 금액: 6,000,000원</b></p><p><b><br></b></p><p><b>구매 이유: 신규 입사자 배치</b></p><p><b><br></b></p><p><b>요청 납기 일정: 2024-08-02</b></p><p><b><br></b></p><p><b>첨부 자료: 견적서, 카탈로그</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 필요 시 견적서, 카탈로그 등의 관련 자료를 반드시 첨부합니다.<br>* 구매 품의서는 필요한 물품이 생길 때 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/01/15 09:00:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/01/20 09:00:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 2, 2024070091, 2, '지출 결의서 - 사무용 소모품 구입', '<h1>지출 결의서 (100만원 이하)</h1><h5>* 아래와 같이 지출 내역을 작성하고, 검토 후 결재를 요청드립니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>지출 항목: 사무용 소모품</b></p><p><b><br></b></p><p><b>지출 금액: 300,000원</b></p><p><b><br></b></p><p><b>지출 세부 내역: 클립, 스테이플러, 포스트잇</b></p><p><b><br></b></p><p><b>총 합계: 300,000원</b></p><p><b><br></b></p><p><b>첨부 파일: 영수증</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 첨부 파일은 필수이며, 지출 내역을 뒷받침할 수 있는 자료를 반드시 포함해야 합니다.<br>* 지출 결의서는 지출이 발생한 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/02/10 10:00:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/02/15 10:00:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 3, 2024070091, 2, '지출 결의서 - 사무용 책상 구입', '<h1>지출 결의서 (100만원 이상)</h1><h5>* 아래와 같이 지출 내역을 작성하고, 검토 후 결재를 요청드립니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>지출 항목: 사무용 책상</b></p><p><b><br></b></p><p><b>지출 금액: 3,000,000원</b></p><p><b><br></b></p><p><b>지출 세부 내역: 책상 본체, 서랍</b></p><p><b><br></b></p><p><b>총 합계: 3,000,000원</b></p><p><b><br></b></p><p><b>첨부 파일: 영수증</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 첨부 파일은 필수이며, 지출 내역을 뒷받침할 수 있는 자료를 반드시 포함해야 합니다.<br>* 지출 결의서는 지출이 발생한 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/03/20 10:00:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/03/25 10:00:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 4, 2024070091, 2, '지출 결의서 - 연구 장비 구입', '<h1>지출 결의서 (1000만원 이상)</h1><h5>* 아래와 같이 지출 내역을 작성하고, 검토 후 결재를 요청드립니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>지출 항목: 연구 장비</b></p><p><b><br></b></p><p><b>지출 금액: 12,000,000원</b></p><p><b><br></b></p><p><b>지출 세부 내역: 장비 본체, 부품</b></p><p><b><br></b></p><p><b>총 합계: 12,000,000원</b></p><p><b><br></b></p><p><b>첨부 파일: 영수증</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 첨부 파일은 필수이며, 지출 내역을 뒷받침할 수 있는 자료를 반드시 포함해야 합니다.<br>* 지출 결의서는 지출이 발생한 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/04/01 11:00:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/04/05 11:00:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 5, 2024070091, 2, 'IT 자산 구매 요청 - 모니터 5대', '<h1>IT 자산 구매 요청</h1><h5>* 아래와 같이 구매를 신청하오니 검토 후 재가하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>사용자 : 기존 직원</b></p><p><b><br></b></p><p><b>사용 목적: 기존 장비 교체</b></p><p><b><br></b></p><p><b>구매요청 품목 : 모니터</b></p><p><b><br></b></p><p><b>구매 수량: 5</b></p><p><b><br></b></p><p><b>구매 금액: 3,000,000원</b></p><p><b><br></b></p><p><b>OS 설치여부: 해당 없음</b></p><p><b><br></b></p><p><b>기타 사항: 없음</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><br></p><h5>* 기기 호환성과 기존 장비와의 연동 여부를 확인해야 합니다.<br>* 설치 및 설정 관련 지원이 필요한 경우, IT 지원팀에 미리 요청해야 합니다.<br>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 필요 시 견적서 등 관련 자료를 첨부합니다.<br>* IT 자산 구매 요청서는 필요 발생 시 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/05/10 14:00:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/05/15 14:00:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 16, 2024070096, 2, '교육 신청서 (사내, 단기 국내 위탁) - 심화 교육', '<h1>교육 신청서 (사내, 단기 국내 위탁)</h1><h5>* 아래와 같이 직무 교육을 신청하오니 검토 후 재가하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>교육 명: 심화 직무 교육</b></p><p><b><br></b></p><p><b>교육 기간: 2024-09-01 ~ 2024-09-10</b></p><p><b><br></b></p><p><b>교육 장소: 서울 교육센터</b></p><p><b><br></b></p><p><b>교육 목적: 직무 능력 심화</b></p><p><b><br></b></p><p><b>첨부 파일: 교육 자료</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><br></p><h5>* 교육 내용이 직무와 관련이 있는지 확인하고, 사전 준비가 필요한 경우 미리 준비합니다.<br>* 교육 후 보고서를 작성하여 상사에게 제출할 계획을 수립합니다.<br>* 교육 신청서는 가능한 한 조기에 제출하여야 합니다.<br>* 필요 시 교육 관련 자료를 첨부합니다.</h5><p><br></p>', TO_DATE('2024/06/01 15:00:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/06/05 15:00:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 6, 2024070096, 2, '출장비 정산서(국내) - 부산 출장', '<h1>출장비 정산서 [국내]</h1><h5>* 아래와 같이 출장비를 정산하오니 검토 후 결재하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>출장 목적: 부산 지사 방문</b></p><p><b><br></b></p><p><b>출장 기간: 2024-09-01 ~ 2024-09-03</b></p><p><b><br></b></p><p><b>출장 장소: 부산 지사</b></p><p><b><br></b></p><p><b>총 출장비: 1,500,000원</b></p><p><b><br></b></p><p><b>세부 항목:</b></p><p><span style="white-space: normal;"><b>    교통비: 500,000원</b></span></p><p><span style="white-space: normal;"><b>    숙박비: 600,000원</b></span></p><p><span style="white-space: normal;"><b>    식비: 400,000원</b></span></p><p><b><br></b></p><p><b>첨부 파일: (교통비 영수증, 숙박비 영수증)</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><h5><br>* 모든 영수증과 증빙 자료는 원본을 제출하며, 사본은 기록으로 보관해야 합니다.<br>* 출장 일정과 관련된 모든 비용을 명확히 기재해야 합니다.<br>* 출장비 정산서는 출장 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/07/01 10:30:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/07/05 10:30:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 7, 2024070096, 2, '출장비 정산서(해외) - 일본 출장', '<h1>출장비 정산서 (해외)</h1><h5>* 아래와 같이 출장비를 정산하오니 검토 후 결재하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>출장 목적: 일본 지사 회의 참석</b></p><p><b><br></b></p><p><b>출장 기간: 2024-09-01 ~ 2024-09-05</b></p><p><b><br></b></p><p><b>출장 장소: 일본 도쿄</b></p><p><b><br></b></p><p><b>총 출장비: 4,000,000원</b></p><p><b><br></b></p><p><b>세부 항목:</b></p><p><span style="white-space: normal;"><b>    항공비: 2,500,000원</b></span></p><p><span style="white-space: normal;"><b>    숙박비: 1,000,000원</b></span></p><p><span style="white-space: normal;"><b>    식비: 500,000원</b></span></p><p><b><br></b></p><p><b>첨부 파일: (항공비 영수증, 숙박비 영수증)</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><h5><br>* 모든 영수증과 증빙 자료는 원본을 제출하며, 사본은 기록으로 보관해야 합니다.<br>* 출장 일정과 관련된 모든 비용을 명확히 기재해야 합니다.<br>* 출장비 정산서는 출장 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/07/10 09:15:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/07/15 09:15:00', 'YYYY/MM/DD HH24:MI:SS'));

-- 종결 상태
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 1, 2024070091, 3, '구매 품의서 - 화이트보드 10개', '<p><br></p><h1>구매 품의서</h1><h5>* 아래와 같이 구매를 신청하오니 검토 후 결재 하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>구매 항목: 화이트보드</b></p><p><b><br></b></p><p><b>구매 수량: 10</b></p><p><b><br></b></p><p><b>구매 금액: 1,000,000원</b></p><p><b><br></b></p><p><b>구매 이유: 회의실 장비 교체</b></p><p><b><br></b></p><p><b>요청 납기 일정: 2024-08-03</b></p><p><b><br></b></p><p><b>첨부 자료: 견적서, 카탈로그</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 필요 시 견적서, 카탈로그 등의 관련 자료를 반드시 첨부합니다.<br>* 구매 품의서는 필요한 물품이 생길 때 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/02/20 08:00:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/02/25 08:00:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 2, 2024070091, 3, '지출 결의서 - 커피머신 구입', '<h1>지출 결의서 (100만원 이하)</h1><h5>* 아래와 같이 지출 내역을 작성하고, 검토 후 결재를 요청드립니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>지출 항목: 커피머신</b></p><p><b><br></b></p><p><b>지출 금액: 700,000원</b></p><p><b><br></b></p><p><b>지출 세부 내역: 커피머신 본체, 필터</b></p><p><b><br></b></p><p><b>총 합계: 700,000원</b></p><p><b><br></b></p><p><b>첨부 파일: 영수증</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 첨부 파일은 필수이며, 지출 내역을 뒷받침할 수 있는 자료를 반드시 포함해야 합니다.<br>* 지출 결의서는 지출이 발생한 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/04/10 10:10:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/04/15 10:10:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 3, 2024070091, 3, '지출 결의서 - 회의실 테이블 구입', '<h1>지출 결의서 (100만원 이상)</h1><h5>* 아래와 같이 지출 내역을 작성하고, 검토 후 결재를 요청드립니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>지출 항목: 회의실 테이블</b></p><p><b><br></b></p><p><b>지출 금액: 1,500,000원</b></p><p><b><br></b></p><p><b>지출 세부 내역: 테이블 본체, 조립 부품</b></p><p><b><br></b></p><p><b>총 합계: 1,500,000원</b></p><p><b><br></b></p><p><b>첨부 파일: 영수증</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 첨부 파일은 필수이며, 지출 내역을 뒷받침할 수 있는 자료를 반드시 포함해야 합니다.<br>* 지출 결의서는 지출이 발생한 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/05/20 11:00:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/05/25 11:00:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 4, 2024070091, 3, '지출 결의서 - 사무용 노트북 구입', '<h1>지출 결의서 (1000만원 이상)</h1><h5>* 아래와 같이 지출 내역을 작성하고, 검토 후 결재를 요청드립니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>지출 항목: 사무용 노트북</b></p><p><b><br></b></p><p><b>지출 금액: 11,000,000원</b></p><p><b><br></b></p><p><b>지출 세부 내역: 노트북 본체, 부품</b></p><p><b><br></b></p><p><b>총 합계: 11,000,000원</b></p><p><b><br></b></p><p><b>첨부 파일: 영수증</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 첨부 파일은 필수이며, 지출 내역을 뒷받침할 수 있는 자료를 반드시 포함해야 합니다.<br>* 지출 결의서는 지출이 발생한 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/06/01 14:00:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/06/05 14:00:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 5, 2024070091, 3, 'IT 자산 구매 요청 - 데스크탑 3대', '<h1>IT 자산 구매 요청</h1><h5>* 아래와 같이 구매를 신청하오니 검토 후 재가하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>사용자 : 연구팀</b></p><p><b><br></b></p><p><b>사용 목적: 연구 개발용</b></p><p><b><br></b></p><p><b>구매요청 품목 : 데스크탑</b></p><p><b><br></b></p><p><b>구매 수량: 3</b></p><p><b><br></b></p><p><b>구매 금액: 4,500,000원</b></p><p><b><br></b></p><p><b>OS 설치여부: </b></p><p><b><br></b></p><p><b>기타 사항: 없음</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><br></p><h5>* 기기 호환성과 기존 장비와의 연동 여부를 확인해야 합니다.<br>* 설치 및 설정 관련 지원이 필요한 경우, IT 지원팀에 미리 요청해야 합니다.<br>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 필요 시 견적서 등 관련 자료를 첨부합니다.<br>* IT 자산 구매 요청서는 필요 발생 시 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/07/10 13:00:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/07/15 13:00:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 16, 2024070091, 3, '교육 신청서 (사내, 단기 국내 위탁) - 관리 교육', '<h1>교육 신청서 (사내, 단기 국내 위탁)</h1><h5>* 아래와 같이 직무 교육을 신청하오니 검토 후 재가하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>교육 명: 관리 직무 교육</b></p><p><b><br></b></p><p><b>교육 기간: 2024-10-01 ~ 2024-10-05</b></p><p><b><br></b></p><p><b>교육 장소: 인천 교육센터</b></p><p><b><br></b></p><p><b>교육 목적: 관리 능력 향상</b></p><p><b><br></b></p><p><b>첨부 파일: 교육 자료</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><br></p><h5>* 교육 내용이 직무와 관련이 있는지 확인하고, 사전 준비가 필요한 경우 미리 준비합니다.<br>* 교육 후 보고서를 작성하여 상사에게 제출할 계획을 수립합니다.<br>* 교육 신청서는 가능한 한 조기에 제출하여야 합니다.<br>* 필요 시 교육 관련 자료를 첨부합니다.</h5><p><br></p>', TO_DATE('2024/07/15 16:00:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/07/20 16:00:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 6, 2024070091, 3, '출장비 정산서(국내) - 대구 출장', '<h1>출장비 정산서 [국내]</h1><h5>* 아래와 같이 출장비를 정산하오니 검토 후 결재하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>출장 목적: 대구 지사 방문</b></p><p><b><br></b></p><p><b>출장 기간: 2024-10-01 ~ 2024-10-03</b></p><p><b><br></b></p><p><b>출장 장소: 대구 지사</b></p><p><b><br></b></p><p><b>총 출장비: 1,500,000원</b></p><p><b><br></b></p><p><b>세부 항목:</b></p><p><span style="white-space: normal;"><b>    교통비: 500,000원</b></span></p><p><span style="white-space: normal;"><b>    숙박비: 600,000원</b></span></p><p><span style="white-space: normal;"><b>    식비: 400,000원</b></span></p><p><b><br></b></p><p><b>첨부 파일: (교통비 영수증, 숙박비 영수증)</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><h5><br>* 모든 영수증과 증빙 자료는 원본을 제출하며, 사본은 기록으로 보관해야 합니다.<br>* 출장 일정과 관련된 모든 비용을 명확히 기재해야 합니다.<br>* 출장비 정산서는 출장 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/07/05 09:30:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/07/10 09:30:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 7, 2024070091, 3, '출장비 정산서(해외) - 중국 출장', '<h1>출장비 정산서 (해외)</h1><h5>* 아래와 같이 출장비를 정산하오니 검토 후 결재하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>출장 목적: 중국 지사 회의 참석</b></p><p><b><br></b></p><p><b>출장 기간: 2024-10-01 ~ 2024-10-07</b></p><p><b><br></b></p><p><b>출장 장소: 중국 베이징</b></p><p><b><br></b></p><p><b>총 출장비: 4,500,000원</b></p><p><b><br></b></p><p><b>세부 항목:</b></p><p><span style="white-space: normal;"><b>    항공비: 2,800,000원</b></span></p><p><span style="white-space: normal;"><b>    숙박비: 1,200,000원</b></span></p><p><span style="white-space: normal;"><b>    식비: 500,000원</b></span></p><p><b><br></b></p><p><b>첨부 파일: (항공비 영수증, 숙박비 영수증)</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><h5><br>* 모든 영수증과 증빙 자료는 원본을 제출하며, 사본은 기록으로 보관해야 합니다.<br>* 출장 일정과 관련된 모든 비용을 명확히 기재해야 합니다.<br>* 출장비 정산서는 출장 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/07/20 10:20:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/07/25 10:20:00', 'YYYY/MM/DD HH24:MI:SS'));

-- 반려 상태
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 1, 2024070091, 4, '구매 품의서 - 프린터 5대', '<p><br></p><h1>구매 품의서</h1><h5>* 아래와 같이 구매를 신청하오니 검토 후 결재 하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>구매 항목: 프린터</b></p><p><b><br></b></p><p><b>구매 수량: 5</b></p><p><b><br></b></p><p><b>구매 금액: 2,500,000원</b></p><p><b><br></b></p><p><b>구매 이유: 기존 장비 교체</b></p><p><b><br></b></p><p><b>요청 납기 일정: 2024-08-04</b></p><p><b><br></b></p><p><b>첨부 자료: 견적서, 카탈로그</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 필요 시 견적서, 카탈로그 등의 관련 자료를 반드시 첨부합니다.<br>* 구매 품의서는 필요한 물품이 생길 때 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/03/01 09:40:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/03/05 09:40:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 2, 2024070091, 4, '지출 결의서 - 사무실 장식품 구입', '<h1>지출 결의서 (100만원 이하)</h1><h5>* 아래와 같이 지출 내역을 작성하고, 검토 후 결재를 요청드립니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>지출 항목: 사무실 장식품</b></p><p><b><br></b></p><p><b>지출 금액: 200,000원</b></p><p><b><br></b></p><p><b>지출 세부 내역: 화분, 그림</b></p><p><b><br></b></p><p><b>총 합계: 200,000원</b></p><p><b><br></b></p><p><b>첨부 파일: 영수증</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 첨부 파일은 필수이며, 지출 내역을 뒷받침할 수 있는 자료를 반드시 포함해야 합니다.<br>* 지출 결의서는 지출이 발생한 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/04/10 13:20:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/04/15 13:20:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 3, 2024070091, 4, '지출 결의서 - 노트북 구입', '<h1>지출 결의서 (100만원 이상)</h1><h5>* 아래와 같이 지출 내역을 작성하고, 검토 후 결재를 요청드립니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>지출 항목: 노트북</b></p><p><b><br></b></p><p><b>지출 금액: 1,200,000원</b></p><p><b><br></b></p><p><b>지출 세부 내역: 노트북 본체, 전원 어댑터</b></p><p><b><br></b></p><p><b>총 합계: 1,200,000원</b></p><p><b><br></b></p><p><b>첨부 파일: 영수증</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 첨부 파일은 필수이며, 지출 내역을 뒷받침할 수 있는 자료를 반드시 포함해야 합니다.<br>* 지출 결의서는 지출이 발생한 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/05/20 09:50:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/05/25 09:50:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 4, 2024070091, 4, '지출 결의서 - 프로젝트 모니터 구입', '<h1>지출 결의서 (1000만원 이상)</h1><h5>* 아래와 같이 지출 내역을 작성하고, 검토 후 결재를 요청드립니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>지출 항목: 프로젝트 모니터</b></p><p><b><br></b></p><p><b>지출 금액: 13,000,000원</b></p><p><b><br></b></p><p><b>지출 세부 내역: 모니터 본체, 부품</b></p><p><b><br></b></p><p><b>총 합계: 13,000,000원</b></p><p><b><br></b></p><p><b>첨부 파일: 영수증</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 첨부 파일은 필수이며, 지출 내역을 뒷받침할 수 있는 자료를 반드시 포함해야 합니다.<br>* 지출 결의서는 지출이 발생한 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/06/10 11:30:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/06/15 11:30:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 5, 2024070091, 4, 'IT 자산 구매 요청 - 프린터 2대', '<h1>IT 자산 구매 요청</h1><h5>* 아래와 같이 구매를 신청하오니 검토 후 재가하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>사용자 : 전 직원</b></p><p><b><br></b></p><p><b>사용 목적: 기존 장비 교체</b></p><p><b><br></b></p><p><b>구매요청 품목 : 프린터</b></p><p><b><br></b></p><p><b>구매 수량: 2</b></p><p><b><br></b></p><p><b>구매 금액: 2,000,000원</b></p><p><b><br></b></p><p><b>OS 설치여부: 해당 없음</b></p><p><b><br></b></p><p><b>기타 사항: 없음</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><br></p><h5>* 기기 호환성과 기존 장비와의 연동 여부를 확인해야 합니다.<br>* 설치 및 설정 관련 지원이 필요한 경우, IT 지원팀에 미리 요청해야 합니다.<br>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 필요 시 견적서 등 관련 자료를 첨부합니다.<br>* IT 자산 구매 요청서는 필요 발생 시 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/07/01 12:00:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/07/05 12:00:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 16, 2024070096, 4, '교육 신청서 (사내, 단기 국내 위탁) - 기술 교육', '<h1>교육 신청서 (사내, 단기 국내 위탁)</h1><h5>* 아래와 같이 직무 교육을 신청하오니 검토 후 재가하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>교육 명: 기술 직무 교육</b></p><p><b><br></b></p><p><b>교육 기간: 2024-11-01 ~ 2024-11-07</b></p><p><b><br></b></p><p><b>교육 장소: 대전 교육센터</b></p><p><b><br></b></p><p><b>교육 목적: 기술 능력 향상</b></p><p><b><br></b></p><p><b>첨부 파일: 교육 자료</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><br></p><h5>* 교육 내용이 직무와 관련이 있는지 확인하고, 사전 준비가 필요한 경우 미리 준비합니다.<br>* 교육 후 보고서를 작성하여 상사에게 제출할 계획을 수립합니다.<br>* 교육 신청서는 가능한 한 조기에 제출하여야 합니다.<br>* 필요 시 교육 관련 자료를 첨부합니다.</h5><p><br></p>', TO_DATE('2024/07/10 15:40:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/07/15 15:40:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 6, 2024070096, 4, '출장비 정산서(국내) - 광주 출장', '<h1>출장비 정산서 [국내]</h1><h5>* 아래와 같이 출장비를 정산하오니 검토 후 결재하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>출장 목적: 광주 지사 방문</b></p><p><b><br></b></p><p><b>출장 기간: 2024-11-01 ~ 2024-11-03</b></p><p><b><br></b></p><p><b>출장 장소: 광주 지사</b></p><p><b><br></b></p><p><b>총 출장비: 1,500,000원</b></p><p><b><br></b></p><p><b>세부 항목:</b></p><p><span style="white-space: normal;"><b>    교통비: 500,000원</b></span></p><p><span style="white-space: normal;"><b>    숙박비: 600,000원</b></span></p><p><span style="white-space: normal;"><b>    식비: 400,000원</b></span></p><p><b><br></b></p><p><b>첨부 파일: (교통비 영수증, 숙박비 영수증)</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><h5><br>* 모든 영수증과 증빙 자료는 원본을 제출하며, 사본은 기록으로 보관해야 합니다.<br>* 출장 일정과 관련된 모든 비용을 명확히 기재해야 합니다.<br>* 출장비 정산서는 출장 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/07/15 16:50:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/07/20 16:50:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE, CREDIT_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 7, 2024070096, 4, '출장비 정산서(해외) - 독일 출장', '<h1>출장비 정산서 (해외)</h1><h5>* 아래와 같이 출장비를 정산하오니 검토 후 결재하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>출장 목적: 독일 지사 회의 참석</b></p><p><b><br></b></p><p><b>출장 기간: 2024-11-01 ~ 2024-11-07</b></p><p><b><br></b></p><p><b>출장 장소: 독일 베를린</b></p><p><b><br></b></p><p><b>총 출장비: 6,000,000원</b></p><p><b><br></b></p><p><b>세부 항목:</b></p><p><span style="white-space: normal;"><b>    항공비: 3,500,000원</b></span></p><p><span style="white-space: normal;"><b>    숙박비: 1,500,000원</b></span></p><p><span style="white-space: normal;"><b>    식비: 1,000,000원</b></span></p><p><b><br></b></p><p><b>첨부 파일: (항공비 영수증, 숙박비 영수증)</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><h5><br>* 모든 영수증과 증빙 자료는 원본을 제출하며, 사본은 기록으로 보관해야 합니다.<br>* 출장 일정과 관련된 모든 비용을 명확히 기재해야 합니다.<br>* 출장비 정산서는 출장 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/07/20 11:30:00', 'YYYY/MM/DD HH24:MI:SS'), TO_DATE('2024/07/25 11:30:00', 'YYYY/MM/DD HH24:MI:SS'));

-- 결재취소 상태
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 1, 2024070091, 5, '구매 품의서 - 복사기 2대', '<p><br></p><h1>구매 품의서</h1><h5>* 아래와 같이 구매를 신청하오니 검토 후 결재 하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>구매 항목: 복사기</b></p><p><b><br></b></p><p><b>구매 수량: 2</b></p><p><b><br></b></p><p><b>구매 금액: 4,000,000원</b></p><p><b><br></b></p><p><b>구매 이유: 기존 장비 교체</b></p><p><b><br></b></p><p><b>요청 납기 일정: 2024-08-05</b></p><p><b><br></b></p><p><b>첨부 자료: 견적서, 카탈로그</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 필요 시 견적서, 카탈로그 등의 관련 자료를 반드시 첨부합니다.<br>* 구매 품의서는 필요한 물품이 생길 때 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/02/15 10:10:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 2, 2024070091, 5, '지출 결의서 - 회의용 간식 구입', '<h1>지출 결의서 (100만원 이하)</h1><h5>* 아래와 같이 지출 내역을 작성하고, 검토 후 결재를 요청드립니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>지출 항목: 회의용 간식</b></p><p><b><br></b></p><p><b>지출 금액: 100,000원</b></p><p><b><br></b></p><p><b>지출 세부 내역: 쿠키, 음료수</b></p><p><b><br></b></p><p><b>총 합계: 100,000원</b></p><p><b><br></b></p><p><b>첨부 파일: 영수증</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 첨부 파일은 필수이며, 지출 내역을 뒷받침할 수 있는 자료를 반드시 포함해야 합니다.<br>* 지출 결의서는 지출이 발생한 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/03/20 11:10:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 4, 2024070091, 5, '지출 결의서 - 프로젝트 장비 구입', '<h1>지출 결의서 (1000만원 이상)</h1><h5>* 아래와 같이 지출 내역을 작성하고, 검토 후 결재를 요청드립니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>지출 항목: 프로젝트 장비</b></p><p><b><br></b></p><p><b>지출 금액: 4,000,000원</b></p><p><b><br></b></p><p><b>지출 세부 내역: 장비 본체, 부품</b></p><p><b><br></b></p><p><b>총 합계: 4,000,000원</b></p><p><b><br></b></p><p><b>첨부 파일: 영수증</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><b><br></b></p><h5>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 첨부 파일은 필수이며, 지출 내역을 뒷받침할 수 있는 자료를 반드시 포함해야 합니다.<br>* 지출 결의서는 지출이 발생한 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/04/01 10:00:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 5, 2024070091, 5, 'IT 자산 구매 요청 - 복합기 1대', '<h1>IT 자산 구매 요청</h1><h5>* 아래와 같이 구매를 신청하오니 검토 후 재가하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>사용자 : 관리팀</b></p><p><b><br></b></p><p><b>사용 목적: 기존 장비 교체</b></p><p><b><br></b></p><p><b>구매요청 품목 : 복합기</b></p><p><b><br></b></p><p><b>구매 수량: 1</b></p><p><b><br></b></p><p><b>구매 금액: 1,500,000원</b></p><p><b><br></b></p><p><b>OS 설치여부: 해당 없음</b></p><p><b><br></b></p><p><b>기타 사항: 없음</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><br></p><h5>* 기기 호환성과 기존 장비와의 연동 여부를 확인해야 합니다.<br>* 설치 및 설정 관련 지원이 필요한 경우, IT 지원팀에 미리 요청해야 합니다.<br>* 모든 항목은 명확하고 상세하게 기재해야 합니다.<br>* 필요 시 견적서 등 관련 자료를 첨부합니다.<br>* IT 자산 구매 요청서는 필요 발생 시 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/05/10 13:10:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 16, 2024070096, 5, '교육 신청서 (사내, 단기 국내 위탁) - 리더십 교육', '<h1>교육 신청서 (사내, 단기 국내 위탁)</h1><h5>* 아래와 같이 직무 교육을 신청하오니 검토 후 재가하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>교육 명: 리더십 직무 교육</b></p><p><b><br></b></p><p><b>교육 기간: 2024-12-01 ~ 2024-12-10</b></p><p><b><br></b></p><p><b>교육 장소: 부산 교육센터</b></p><p><b><br></b></p><p><b>교육 목적: 리더십 향상</b></p><p><b><br></b></p><p><b>첨부 파일: 교육 자료</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><p><br></p><h5>* 교육 내용이 직무와 관련이 있는지 확인하고, 사전 준비가 필요한 경우 미리 준비합니다.<br>* 교육 후 보고서를 작성하여 상사에게 제출할 계획을 수립합니다.<br>* 교육 신청서는 가능한 한 조기에 제출하여야 합니다.<br>* 필요 시 교육 관련 자료를 첨부합니다.</h5><p><br></p>', TO_DATE('2024/06/20 14:20:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 6, 2024070096, 5, '출장비 정산서(국내) - 대전 출장', '<h1>출장비 정산서 [국내]</h1><h5>* 아래와 같이 출장비를 정산하오니 검토 후 결재하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>출장 목적: 대전 지사 방문</b></p><p><b><br></b></p><p><b>출장 기간: 2024-12-01 ~ 2024-12-03</b></p><p><b><br></b></p><p><b>출장 장소: 대전 지사</b></p><p><b><br></b></p><p><b>총 출장비: 1,500,000원</b></p><p><b><br></b></p><p><b>세부 항목:</p><p><span style="white-space: normal;"><b>    교통비: 500,000원</b></span></p><p><span style="white-space: normal;"><b>    숙박비: 600,000원</b></span></p><p><span style="white-space: normal;"><b>    식비: 400,000원</b></span></p><p><b><br></b></p><p><b>첨부 파일: (교통비 영수증, 숙박비 영수증)</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><h5><br>* 모든 영수증과 증빙 자료는 원본을 제출하며, 사본은 기록으로 보관해야 합니다.<br>* 출장 일정과 관련된 모든 비용을 명확히 기재해야 합니다.<br>* 출장비 정산서는 출장 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/07/01 10:40:00', 'YYYY/MM/DD HH24:MI:SS'));
INSERT INTO DOCUMENT (DOC_NO, TEMPLATE_NO, WRITER_NO, STATUS, TITLE, CONTENT, ENROLL_DATE) VALUES (SEQ_DOCUMENT.NEXTVAL, 7, 2024070096, 5, '출장비 정산서(해외) - 호주 출장', '<h1>출장비 정산서 (해외)</h1><h5>* 아래와 같이 출장비를 정산하오니 검토 후 결재하여 주시기 바랍니다.</h5><p><br></p><p><b>-------------------------- 아 래 -----------------------------</b></p><p><b><br></b></p><p><b>출장 목적: 호주 지사 회의 참석</b></p><p><b><br></b></p><p><b>출장 기간: 2024-12-01 ~ 2024-12-07</b></p><p><b><br></b></p><p><b>출장 장소: 호주 시드니</b></p><p><b><br></b></p><p><b>총 출장비: 5,500,000원</b></p><p><b><br></b></p><p><b>세부 항목:</p><p><span style="white-space: normal;"><b>    항공비: 3,200,000원</b></span></p><p><span style="white-space: normal;"><b>    숙박비: 1,500,000원</b></span></p><p><span style="white-space: normal;"><b>    식비: 800,000원</b></span></p><p><b><br></b></p><p><b>첨부 파일: (항공비 영수증, 숙박비 영수증)</b></p><p><b><br></b></p><p><b>-----------------------------------------------------------------------</b></p><h5><br>* 모든 영수증과 증빙 자료는 원본을 제출하며, 사본은 기록으로 보관해야 합니다.<br>* 출장 일정과 관련된 모든 비용을 명확히 기재해야 합니다.<br>* 출장비 정산서는 출장 후 즉시 작성하여 제출해야 합니다.</h5><p><br></p>', TO_DATE('2024/07/15 14:30:00', 'YYYY/MM/DD HH24:MI:SS'));
COMMIT;

--------------------------------------------------------
-- 문서 - 결재선 더미 데이터 (APPR_LINE)
--------------------------------------------------------

-- 임시저장1

-- 구매 품의서 결재선 (DOC_NO 1)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 1, 1, 2024070011, 1, 2, 6, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 1, 2, 2024070016, 1, 4, 7, 1);

-- 지출 결의서 결재선 (DOC_NO 2) - 100만원 이하
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 2, 1, 2024070011, 1, 2, 6, 1);

-- 지출 결의서 결재선 (DOC_NO 3) - 100만원 이상
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 3, 1, 2024070011, 1, 2, 6, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 3, 2, 2024070016, 1, 4, 7, 1);

-- 지출 결의서 결재선 (DOC_NO 4) - 1000만원 이상
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 4, 1, 2024070093, 1, 2, 11, 2);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 4, 2, 2024070011, 1, 2, 6, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 4, 3, 2024070016, 1, 4, 7, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 4, 4, 2024070001, 1, 1, 4,  1);

-- IT 자산 구매 요청 결재선 (DOC_NO 5)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 5, 1, 2024070093, 1, 2, 11, 2);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 5, 2, 2024070011, 1, 2, 6, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 5, 3, 2024070016, 1, 4, 7, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 5, 4, 2024070001, 1, 1, 4, 1);

-- 교육 신청서(사내, 단기국내위탁) 결재선 (DOC_NO 6)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 6, 1, 2024070016, 1, 4, 7, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 6, 2, 2024070011, 1, 2, 6, 1);

-- 출장비 정산서(국내) 결재선 (DOC_NO 7)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 7, 1, 2024070016, 1, 4, 7, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 7, 2, 2024070011, 1, 2, 6, 1);

-- 출장비 정산서(해외) 결재선 (DOC_NO 8)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 8, 1, 2024070090, 1, 4, 11, 2);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 8, 2, 2024070016, 1, 4, 7, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 8, 3, 2024070011, 1, 2, 6, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 8, 4, 2024070001, 1, 1, 4, 1);

-- 기안2

-- 구매 품의서 결재선 (DOC_NO 9)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, "COMMENT", APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 9, 1, 2024070011, 2, 2, 6, 1, '승인합니다', '2024/01/21 11:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 9, 2, 2024070016, 1, 4, 7, 1);

-- 지출 결의서 결재선 (DOC_NO 10) - 100만원 이하
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 10, 1, 2024070011, 1, 2, 6, 1);

-- 지출 결의서 결재선 (DOC_NO 11) - 100만원 이상
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 11, 1, 2024070011, 2, 2, 6, 1, '2024/03/25 11:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 11, 2, 2024070016, 1, 4, 7, 1);

-- 지출 결의서 결재선 (DOC_NO 12) - 1000만원 이상
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 12, 1, 2024070093, 2, 2, 11, 2,'2024/04/05 15:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, "COMMENT", APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 12, 2, 2024070011, 2, 2, 6, 1, '유용하게 잘 사용하세요', '2024/04/06 11:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 12, 3, 2024070016, 1, 4, 7, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 12, 4, 2024070001, 1, 1, 4, 1);

-- IT 자산 구매 요청 결재선 (DOC_NO 13)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 13, 1, 2024070093, 2, 2, 11, 2, '2024/05/16 11:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, "COMMENT", APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 13, 2, 2024070011, 2, 2, 6, 1, '잘 사용하세요', '2024/05/16 11:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 13, 3, 2024070016, 1, 4, 7, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 13, 4, 2024070001, 1, 1, 4, 1);

-- 교육 신청서(사내, 단기국내위탁) 결재선 (DOC_NO 14)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 14, 1, 2024070016, 2, 4, 7, 1, '2024/06/06 11:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 14, 2, 2024070011, 1, 2, 6, 1);

-- 출장비 정산서(국내) 결재선 (DOC_NO 15)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 15, 1, 2024070016, 2, 4, 7, 1, '2024/07/06 11:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 15, 2, 2024070011, 1, 2, 6, 1);

-- 출장비 정산서(해외) 결재선 (DOC_NO 16)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 16, 1, 2024070090, 2, 4, 11, 2, '2024/07/16 11:12:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, "COMMENT", APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 16, 2, 2024070016, 2, 4, 7, 1, '다치지마 도토 도토잠보', '2024/07/16 12:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 16, 3, 2024070011, 2, 2, 6, 1, '2024/07/16 15:02:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 16, 4, 2024070001, 1, 1, 4, 1);

-- 종결

-- 구매 품의서 결재선 (DOC_NO 17)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, "COMMENT", APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 17, 1, 2024070011, 2, 2, 6, 1, '승인합니다', '2024/02/26 11:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 17, 2, 2024070016, 2, 4, 7, 1, '2024/02/26 15:00:00');

-- 지출 결의서 결재선 (DOC_NO 18) - 100만원 이하
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 18, 1, 2024070011, 2, 2, 6, 1, '2024/04/16 11:00:00');

-- 지출 결의서 결재선 (DOC_NO 19) - 100만원 이상
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 19, 1, 2024070011, 2, 2, 6, 1, '2024/05/26 11:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 19, 2, 2024070016, 2, 4, 7, 1, '2024/05/26 15:00:00');

-- 지출 결의서 결재선 (DOC_NO 20) - 1000만원 이상
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 20, 1, 2024070093, 2, 2, 11, 2, '2024/06/05 15:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, "COMMENT", APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 20, 2, 2024070011, 2, 2, 6, 1, '유용하게 잘 사용하세요', '2024/06/06 10:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 20, 3, 2024070016, 2, 4, 7, 1, '2024/06/06 11:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 20, 4, 2024070001, 2, 1, 4,  1, '2024/06/06 12:00:00');

-- IT 자산 구매 요청 결재선 (DOC_NO 21)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 21, 1, 2024070093, 2, 2, 11, 2, '2024/07/15 15:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, "COMMENT", APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 21, 2, 2024070011, 2, 2, 6, 1, '잘 사용하세요', '2024/07/16 11:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 21, 3, 2024070016, 2, 4, 7, 1, '2024/07/16 13:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 21, 4, 2024070001, 2, 1, 4,  1, '2024/07/16 15:00:00');

-- 교육 신청서(사내, 단기국내위탁) 결재선 (DOC_NO 22)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 22, 1, 2024070016, 2, 4, 7, 1, '2024/07/20 17:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 22, 2, 2024070011, 2, 2, 6, 1, '2024/07/21 10:00:00');

-- 출장비 정산서(국내) 결재선 (DOC_NO 23)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 23, 1, 2024070016, 2, 4, 7, 1, '2024/07/10 13:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 23, 2, 2024070011, 2, 2, 6, 1, '2024/07/11 10:00:00');

-- 출장비 정산서(해외) 결재선 (DOC_NO 24)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 24, 1, 2024070090, 2, 4, 11, 2, '2024/07/25 10:50:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, "COMMENT", APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 24, 2, 2024070016, 2, 4, 7, 1, '다치지마 도토 도토잠보', '2024/07/25 12:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 24, 3, 2024070011, 2, 2, 6, 1, '2024/07/26 10:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 24, 4, 2024070001, 2, 1, 4, 1, '2024/07/26 13:00:00');
COMMIT;

-- 반려

-- 구매 품의서 결재선 (DOC_NO 25)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, "COMMENT", APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 25, 1, 2024070011, 2, 6, 3, 1, '반려합니다', '2024/03/06 13:00:00');

-- 지출 결의서 결재선 (DOC_NO 26) - 100만원 이하
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, "COMMENT", APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 26, 1, 2024070011, 2, 6, 3, 1, '반려합니다', '2024/04/15 15:00:00');

-- 지출 결의서 결재선 (DOC_NO 27) - 100만원 이상
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, "COMMENT", APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 27, 1, 2024070011, 2, 6, 3, 1, '반려합니다', '2024/05/26 13:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 27, 2, 2024070016, 1, 4, 7, 1);

-- 지출 결의서 결재선 (DOC_NO 28) - 1000만원 이상
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, "COMMENT", APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 28, 1, 2024070093, 3, 2, 11, 2, '반려합니다', '2024/06/15 13:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 28, 2, 2024070011, 1, 2, 6, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 28, 3, 2024070016, 1, 4, 7, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 28, 4, 2024070001, 1, 1, 4, 1);

-- IT 자산 구매 요청 결재선 (DOC_NO 29)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 29, 1, 2024070093, 2, 2, 11, 2, '2024/07/05 13:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, "COMMENT", APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 29, 2, 2024070011, 2, 6, 3, 1, '반려합니다', '2024/07/05 15:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 29, 3, 2024070016, 1, 4, 7, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 29, 4, 2024070001, 1, 1, 4, 1);

-- 교육 신청서(사내, 단기국내위탁) 결재선 (DOC_NO 30)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, "COMMENT", APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 30, 1, 2024070016, 3, 4, 7, 1, '반려합니다', '2024/07/16 10:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 30, 2, 2024070011, 1, 2, 6, 1);

-- 출장비 정산서(국내) 결재선 (DOC_NO 31)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, "COMMENT", APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 31, 1, 2024070016, 3, 4, 7, 1, '반려합니다', '2024/07/21 10:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 31, 2, 2024070011, 1, 2, 6, 1);

-- 출장비 정산서(해외) 결재선 (DOC_NO 32)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO, "COMMENT", APPROVAL_DATE)
VALUES (SEQ_APPR_LINE.NEXTVAL, 32, 1, 2024070090, 3, 4, 11, 2, '반려합니다', '2024/07/26 13:00:00');
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 32, 2, 2024070016, 1, 4, 7, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 32, 3, 2024070011, 1, 2, 6, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 32, 4, 2024070001, 1, 1, 4, 1);

COMMIT;

-- 결재취소 상태

-- 구매 품의서 결재선 (DOC_NO 33)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 33, 1, 2024070011, 1, 2, 6, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 33, 2, 2024070016, 1, 4, 7, 1);

-- 지출 결의서 결재선 (DOC_NO 34) - 100만원 이하
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 34, 1, 2024070011, 1, 2, 6, 1);

-- 지출 결의서 결재선 (DOC_NO 35) - 1000만원 이상
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 35, 1, 2024070093, 1, 2, 11, 2);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 35, 2, 2024070011, 1, 2, 6, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 35, 3, 2024070016, 1, 4, 7, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 35, 4, 2024070001, 1, 1, 4, 1);

-- IT 자산 구매 요청 결재선 (DOC_NO 36)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 36, 1, 2024070093, 1, 2, 11, 2);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 36, 2, 2024070011, 1, 2, 6, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 36, 3, 2024070016, 1, 4, 7, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 36, 4, 2024070001, 1, 1, 4, 1);

-- 교육 신청서(사내, 단기국내위탁) 결재선 (DOC_NO 37)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 37, 1, 2024070016, 1, 4, 7, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 37, 2, 2024070011, 1, 2, 6, 1);

-- 출장비 정산서(국내) 결재선 (DOC_NO 38)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 38, 1, 2024070016, 1, 4, 7, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 38, 2, 2024070011, 1, 2, 6, 1);

-- 출장비 정산서(해외) 결재선 (DOC_NO 39)
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 39, 1, 2024070090, 1, 4, 11, 2);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 39, 2, 2024070016, 1, 4, 7, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 39, 3, 2024070011, 1, 2, 6, 1);
INSERT INTO APPR_LINE (APPR_LINE_NO, DOC_NO, SEQ, APPROVER_NO, APPROVAL_STAGE, DEPT_CODE, POSITION_CODE, APPROVER_CLASSIFICATION_NO)
VALUES (SEQ_APPR_LINE.NEXTVAL, 39, 4, 2024070001, 1, 1, 4, 1);

COMMIT;


--------------------------------------------------------
-- 문서 파일 더미 데이터 (DOC_FILES)
--------------------------------------------------------
INSERT INTO DOC_FILES (FILE_NO, DOC_NO, CHANGE_NAME, ORIGIN_NAME, DEL_YN) VALUES (SEQ_DOC_FILES.NEXTVAL, 6, '교육 신청서 (사내, 단기 국내 위탁)_06.jpg', '교육 신청서.jpg', 'N');
INSERT INTO DOC_FILES (FILE_NO, DOC_NO, CHANGE_NAME, ORIGIN_NAME, DEL_YN) VALUES (SEQ_DOC_FILES.NEXTVAL, 8, '출장비 정산서(해외)_08.jpg', '출장비 정산서.jpg', 'N');
INSERT INTO DOC_FILES (FILE_NO, DOC_NO, CHANGE_NAME, ORIGIN_NAME, DEL_YN) VALUES (SEQ_DOC_FILES.NEXTVAL, 15, '출장비 정산서(국내)_15.png', '출장비 정산서(국내).png', 'N');

--------------------------------------------------------
-- 문서 참조자 리스트 더미 데이터 (DOC_REFERENCE_LIST)
--------------------------------------------------------
INSERT INTO DOC_REFERENCE_LIST (REFERENCE_LIST_NO, DOC_NO, REFERRER_NO) VALUES (SEQ_DOC_REFERENCE_LIST.NEXTVAL, 8, 2024070090);
INSERT INTO DOC_REFERENCE_LIST (REFERENCE_LIST_NO, DOC_NO, REFERRER_NO) VALUES (SEQ_DOC_REFERENCE_LIST.NEXTVAL, 16, 2024070090);
INSERT INTO DOC_REFERENCE_LIST (REFERENCE_LIST_NO, DOC_NO, REFERRER_NO) VALUES (SEQ_DOC_REFERENCE_LIST.NEXTVAL, 24, 2024070093);
INSERT INTO DOC_REFERENCE_LIST (REFERENCE_LIST_NO, DOC_NO, REFERRER_NO) VALUES (SEQ_DOC_REFERENCE_LIST.NEXTVAL, 32, 2024070090);
INSERT INTO DOC_REFERENCE_LIST (REFERENCE_LIST_NO, DOC_NO, REFERRER_NO) VALUES (SEQ_DOC_REFERENCE_LIST.NEXTVAL, 39, 2024070090);

--------------------------------------------------------
-- 메시지 더미 데이터 (MESSAGE)
--------------------------------------------------------
INSERT INTO MESSAGE (MSG_NO, SENDER_NO, RECEIVER_NO, CONTENT, SEND_DATE, READ_DATE) VALUES (SEQ_MESSAGE.NEXTVAL, 2024070012, 2024070016, '첫 번째 메시지 내용', SYSDATE, SYSDATE);
INSERT INTO MESSAGE (MSG_NO, SENDER_NO, RECEIVER_NO, CONTENT, SEND_DATE, READ_DATE) VALUES (SEQ_MESSAGE.NEXTVAL, 2024070016, 2024070012, '두 번째 메시지 내용', SYSDATE, SYSDATE);

--------------------------------------------------------
-- 게시판 카테고리 데이터 삽입
--------------------------------------------------------

INSERT INTO BOARD_CATEGORY (CATEGORY_NO, CATEGORY) VALUES (SEQ_BOARD_CATEGORY.NEXTVAL, '자유 게시판');
INSERT INTO BOARD_CATEGORY (CATEGORY_NO, CATEGORY) VALUES (SEQ_BOARD_CATEGORY.NEXTVAL, '팀 게시판');
INSERT INTO BOARD_CATEGORY (CATEGORY_NO, CATEGORY) VALUES (SEQ_BOARD_CATEGORY.NEXTVAL, '익명 게시판');
INSERT INTO BOARD_CATEGORY (CATEGORY_NO, CATEGORY) VALUES (SEQ_BOARD_CATEGORY.NEXTVAL, '북마크');

--------------------------------------------------------
   -- BOARD_PENALTY_CATEGORY 테이블에 예시 데이터 삽입
--------------------------------------------------------
INSERT INTO BOARD_PENALTY_CATEGORY (PENALTY_CATEGORY_NO, CATEGORY_NAME) VALUES (1, '스팸 또는 광고');
INSERT INTO BOARD_PENALTY_CATEGORY (PENALTY_CATEGORY_NO, CATEGORY_NAME) VALUES (2, '혐오 발언 또는 폭력');
INSERT INTO BOARD_PENALTY_CATEGORY (PENALTY_CATEGORY_NO, CATEGORY_NAME) VALUES (3, '개인 정보 침해');
INSERT INTO BOARD_PENALTY_CATEGORY (PENALTY_CATEGORY_NO, CATEGORY_NAME) VALUES (4, '저작권 침해');
INSERT INTO BOARD_PENALTY_CATEGORY (PENALTY_CATEGORY_NO, CATEGORY_NAME) VALUES (5, '기타');




-------------------------------------------------------
-- 4대보험 요율 삽입
-------------------------------------------------------
INSERT INTO RATES(RATES_NO,BASE_YEAR,PENSION_PERCENTAGE,HEALTH_INSURANCE_PERCENTAGE,LONG_CARE_PERCENTAGE,EMPLOYMENT_INSURANCE_PERCENTAGE,LOCAL_INCOME_TAX_PERSENTAGE)VALUES(SEQ_RATES_NO.NEXTVAL,TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 'YYYY-MM-DD'),0.045,0.03545,0.1295,0.009,0.1);


-- 2024070099 사원의 일주일치 데이터
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (111, 2024070099, TO_DATE('2024-07-01', 'YYYY-MM-DD'), TO_TIMESTAMP('09:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 1.5, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (2, 2024070099, TO_DATE('2024-07-02', 'YYYY-MM-DD'), TO_TIMESTAMP('09:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 2.0, 0);

-- 2024070091 사원의 일주일치 데이터
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (8, 2024070091, TO_DATE('2024-07-01', 'YYYY-MM-DD'), TO_TIMESTAMP('08:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 1.0, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (9, 2024070091, TO_DATE('2024-07-02', 'YYYY-MM-DD'), TO_TIMESTAMP('08:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 1.5, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (10, 2024070091, TO_DATE('2024-07-03', 'YYYY-MM-DD'), TO_TIMESTAMP('08:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 2.0, 0);


-- 2024070011 사원의 일주일치 데이터
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (15, 2024070011, TO_DATE('2024-07-01', 'YYYY-MM-DD'), TO_TIMESTAMP('09:30:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 2.0, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (16, 2024070011, TO_DATE('2024-07-02', 'YYYY-MM-DD'), TO_TIMESTAMP('09:30:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 1.5, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (17, 2024070011, TO_DATE('2024-07-03', 'YYYY-MM-DD'), TO_TIMESTAMP('09:30:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 1.0, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (18, 2024070011, TO_DATE('2024-07-04', 'YYYY-MM-DD'), TO_TIMESTAMP('09:30:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 2.5, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (19, 2024070011, TO_DATE('2024-07-05', 'YYYY-MM-DD'), TO_TIMESTAMP('09:30:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 0.5, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (20, 2024070011, TO_DATE('2024-07-06', 'YYYY-MM-DD'), TO_TIMESTAMP('09:30:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 3.0, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (21, 2024070011, TO_DATE('2024-07-07', 'YYYY-MM-DD'), TO_TIMESTAMP('09:30:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 1.5, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (50, 2024070011, TO_DATE('2024-07-08', 'YYYY-MM-DD'), TO_TIMESTAMP('09:05:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 2.0, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (51, 2024070011, TO_DATE('2024-07-09', 'YYYY-MM-DD'), TO_TIMESTAMP('09:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('17:00:00', 'HH24:MI:SS'), 2.0, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (52, 2024070011, TO_DATE('2024-07-10', 'YYYY-MM-DD'), TO_TIMESTAMP('09:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('17:58:00', 'HH24:MI:SS'), 2.0, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (53, 2024070011, TO_DATE('2024-07-11', 'YYYY-MM-DD'), TO_TIMESTAMP('09:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 1.0, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (54, 2024070011, TO_DATE('2024-07-12', 'YYYY-MM-DD'), TO_TIMESTAMP('09:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 1.5, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (55, 2024070011, TO_DATE('2024-07-13', 'YYYY-MM-DD'), TO_TIMESTAMP('09:03:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 1.0, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (56, 2024070011, TO_DATE('2024-07-14', 'YYYY-MM-DD'), TO_TIMESTAMP('09:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 2.5, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (57, 2024070011, TO_DATE('2024-07-15', 'YYYY-MM-DD'), TO_TIMESTAMP('09:11:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 1.0, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (58, 2024070011, TO_DATE('2024-07-16', 'YYYY-MM-DD'), TO_TIMESTAMP('09:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 2.0, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (60, 2024070011, TO_DATE('2024-07-23', 'YYYY-MM-DD'), TO_TIMESTAMP('09:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 1.0, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (61, 2024070011, TO_DATE('2024-07-24', 'YYYY-MM-DD'), TO_TIMESTAMP('08:57:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 2.5, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (62, 2024070011, TO_DATE('2024-07-25', 'YYYY-MM-DD'), TO_TIMESTAMP('09:01:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 1.5, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (63, 2024070011, TO_DATE('2024-07-21', 'YYYY-MM-DD'), TO_TIMESTAMP('09:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 1.0, 0);
-- 2024070016 사원의 일주일치 데이터
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (22, 2024070016, TO_DATE('2024-07-01', 'YYYY-MM-DD'), TO_TIMESTAMP('10:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 1.0, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (23, 2024070016, TO_DATE('2024-07-02', 'YYYY-MM-DD'), TO_TIMESTAMP('10:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 1.5, 0);

-- 2024070012 사원의 일주일치 데이터
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (29, 2024070012, TO_DATE('2024-07-01', 'YYYY-MM-DD'), TO_TIMESTAMP('09:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 1.0, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (30, 2024070012, TO_DATE('2024-07-02', 'YYYY-MM-DD'), TO_TIMESTAMP('09:15:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 1.5, 0);

-- 2024070096 사원의 일주일치 데이터
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (36, 2024070096, TO_DATE('2024-07-01', 'YYYY-MM-DD'), TO_TIMESTAMP('09:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 1.0, 0);
INSERT INTO WORK_INFO (WORK_NO, EMP_NO, WORK_DATE, START_TIME, END_TIME, OVERTIME_WORK, HOLIDAY_WORK) VALUES (37, 2024070096, TO_DATE('2024-07-02', 'YYYY-MM-DD'), TO_TIMESTAMP('09:00:00', 'HH24:MI:SS'), TO_TIMESTAMP('18:00:00', 'HH24:MI:SS'), 1.5, 0);



INSERT INTO VACATION_REFERENCE (vacation_code, vacation_name) VALUES ('V001', '연차 휴가');
INSERT INTO VACATION_REFERENCE (vacation_code, vacation_name) VALUES ('V002', '병가');
INSERT INTO VACATION_REFERENCE (vacation_code, vacation_name) VALUES ('V003', '육아 휴직');
INSERT INTO VACATION_REFERENCE (vacation_code, vacation_name) VALUES ('V004', '보상 휴가');
INSERT INTO VACATION_REFERENCE (vacation_code, vacation_name) VALUES ('V005', '무급 휴가');
INSERT INTO VACATION_REFERENCE (vacation_code, vacation_name) VALUES ('V006', '출산 휴가');
INSERT INTO VACATION_REFERENCE (vacation_code, vacation_name) VALUES ('V007', '배우자 출산 휴가');
INSERT INTO VACATION_REFERENCE (vacation_code, vacation_name) VALUES ('V008', '경조사 휴가');
INSERT INTO VACATION_REFERENCE (vacation_code, vacation_name) VALUES ('V009', '배심원 의무 휴가');
INSERT INTO VACATION_REFERENCE (vacation_code, vacation_name) VALUES ('V010', '학습 휴가');


commit;




COMMIT;


-- 사내 캘린더 더미 데이터
BEGIN
    FOR i IN 1..120 LOOP
        -- 랜덤한 START_DATE를 생성
        DECLARE
            v_start_date DATE := TRUNC(SYSDATE + DBMS_RANDOM.VALUE(-300, 220));
            v_end_date DATE;
        BEGIN
            -- END_DATE를 START_DATE 기준으로 -3일에서 +4일 사이로 설정
            v_end_date := v_start_date + ROUND(DBMS_RANDOM.VALUE(-0, 3));

            -- 데이터 삽입
            INSERT INTO CALENDAR (
                CALENDAR_NO, WRITER_NO, TITLE, CONTENT, START_DATE, END_DATE, RANGE
            ) VALUES (
                SEQ_CALENDAR_NO.NEXTVAL,
                2024070099,
                '회의 일정 ' || TO_CHAR(DBMS_RANDOM.VALUE(1, 1000), 'FM0999'),
                '이 내용은 사내 회의 일정과 관련된 상세 정보입니다. 회의 일시는 ' ||
                TO_CHAR(v_start_date, 'YYYY-MM-DD') ||
                '이며, 세부 사항은 추후 공지됩니다.',
                TO_CHAR(v_start_date, 'YYYY-MM-DD'),
                TO_CHAR(v_end_date, 'YYYY-MM-DD'),
                'company'
            );
        END;
    END LOOP;
    COMMIT;
END;