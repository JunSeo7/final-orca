<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <div class="container">
        <form action="employeeRegistration" id="employee-registration" method="post">
            <div class="container-grid">
                <div class="header">사원 등록</div>
                <div class="main-top">
                    <div><span>이름 </span><input type="text" id="name" name="name"></div>
                    <div>
                        <span>부서 </span>
                        <select id="dept" name="dept" required>
                            <option value="1">인사관리부</option>
                            <option value="1">경영지원부</option>
                            <option value="1">마인부우</option>
                        </select>

                        <span>팀 </span>
                        <select id="team" name="team" required>
                            <option value="1">인사 1팀</option>
                        </select>
                    </div>
                    <div><span>주민등록번호 </span><input type="text" id="social-security-no" name="social-security-no" required></div>
                    <div>
                        <span>직급 </span>
                        <select id="position" name="position" required>
                            <option value="1">직급을 선택하세요</option>
                        </select>
                    </div>
                    <div><span>이메일 </span><input type="email" id="email" name="email" required></div>
                    <div><span>휴대전화 </span><input type="text" id="phone" name="phone" required></div>
                    <div><span>비밀번호 </span><input type="password" id="password" name="password" required></div>
                    <div><span>내선전화 </span><input type="text" id="ext" name="ext" required></div>
                    <div><span>신장 </span><input type="text" id="height" name="height" required></div>
                    <div>
                        <span>성별 </span>
                        <select id="gender" name="gender" required>
                            <option value="F">성별을 선택하세요</option>
                        </select>
                        <span>혈액형 </span>
                        <select id="bloodType" name="bloodType" required>
                            <option value="A">혈액형을 선택하세요</option>
                        </select>
                    </div>
                    <div><span>체중 </span><input type="text" id="weight" name="weight" required></div>
                    <div><span>급여 </span><input type="text" id="salary" name="salary" required></div>
                    <div><span>종교 </span><input type="text" id="religion" name="religion" required></div>
                    <div><span>계좌번호 </span><input type="text" id="bankNumber" name="bankNumber" required></div>
                    <div class="address"><span>주소 </span><input type="text" id="address" name="address" required></div>
                    <div><span>이미지 </span><input type="file" id="image" name="image"></div>
                </div>
                <div class="footer"><input class="employeeSubmit" type="submit"></div>
            </div>
        </form>
    </div>

    <style>
        .header {
            font-size: 20px;
        }

        .container {
            background-color: rgb(130, 168, 241);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 800px;
            height: 550px;
            margin-bottom: 20px;
        }

        .container-grid {
            justify-items: center;
            display: grid;
            grid-template-rows: 0.13fr 1.5fr 0.1fr;
            width: 100%;
            height: 590px;
            font-weight: 900;
        }

        .main-top {
            display: grid;
            grid-template-columns: 1.25fr 2fr;
            grid-template-rows: repeat(8, 0.7fr);
            justify-items: end;
            align-items: center;
        }

        .main-bottom {
            display: grid;
            justify-items: end;
            align-items: center;
            grid-template-rows: 1fr 1fr;
            grid-template-columns: 1fr 3fr;
        }

        .footer {
            display: flex;
            justify-content: center;
        }

        .main-top input,
        .main-top select {
            border: 1px solid #1b1b1b;
            border-radius: 3px;
            height: 21px;
            margin-bottom: 0px;
            background-color: rgb(214, 213, 213);
            padding: 0px;
        }

        #password,
        #ext {
            margin-bottom: 14px;
        }

        #image {
            width: 200px;
        }

        .employeeSubmit {
            padding: 5px 12px;
            border: 2px solid black;
            border-radius: 5px;
            background-color: rgb(214, 213, 213);
            color: black;
            font-size: 16px;
            cursor: pointer;
            font-weight: 900;
        }

        .employeeSubmit:hover {
            background-color: rgb(166, 165, 165);
        }
    </style>