<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <div class="container">
        <form action="employeeRegistration" id="employee-registration" method="post" enctype="multipart/form-data">
            <div class="container-grid">
                <div class="header">사원 등록</div>
                <div class="main-top">
                    <div><span>이름 </span><input type="text" id="name" name="name" maxlength="20" required></div>
                    <div>
                        <span>부서 </span>
                        <select id="dept" name="deptCode" required>
                            <option value="">선택하세요</option>
                        </select>

                        <span>팀 </span>
                        <select id="team" name="teamCode" required>
                            <option value="">선택하세요</option>
                        </select>
                    </div>
                    <div><span>주민등록번호</span><input type="text" id="social-security-no" name="socialSecurityNo" pattern="[0-9]{6}-[0-9]{7}" required></div>
                    <div>
                        <span>직급 </span>
                        <select id="positionCode" name="positionCode" required>
                            <option value="">선택하세요</option>
                        </select>
                    </div>
                    <div><span>이메일 </span><input type="email" id="email" name="email" maxlength="50" required></div>
                    <div><span>휴대전화 </span><input type="text" id="phone" name="phone" maxlength="11" pattern="[0-9]*" required></div>
                    <div><span>비밀번호 </span><input type="password" id="password" name="password" pattern="[0-9]*" maxlength="20" required></div>
                    <div><span>내선전화 </span><input type="text" id="ext" name="extensionCall" maxlength="11" pattern="[0-9]*" required></div>
                    <div><span>신장 </span><input type="text" id="height" name="height" maxlength="3" pattern="[0-9]*" required></div>
                    <div>
                        <span>성별 </span>
                        <select id="gender" name="gender" required>
                            <option value="">선택하세요</option>
                            <option value="M">남</option>
                            <option value="F">여</option>
                        </select>
                        <span>혈액형 </span>
                        <select id="bloodType" name="bloodType" required>
                            <option value="">선택하세요</option>
                            <option value="A">A형</option>
                            <option value="B">B형</option>
                            <option value="O">O형</option>
                            <option value="AB">AB형</option>
                            <option value="RH-O">RH-O형</option>
                        </select>
                    </div>
                    <div><span>체중 </span><input type="text" id="weight" name="weight" maxlength="3" pattern="[0-9]*" required></div>
                    <div><span>급여 </span><input type="text" id="salary" name="salary" maxlength="9" pattern="[0-9]*" required></div>
                    <div><span>종교 </span><input type="text" id="religion" name="religion" maxlength="30" required></div>
                    <div><label for="bankName">은행명</label>
                        <select id="bankName" name="bankName" required>
                            <option value="">선택하세요</option>
                            <option value="농협은행">농협은행</option>
                            <option value="국민은행">국민은행</option>
                            <option value="신한은행">신한은행</option>
                            <option value="우리은행">우리은행</option>
                            <option value="하나은행">하나은행</option>
                            <option value="신협은행">신협은행</option>
                            <option value="IBK기업은행">IBK기업</option>
                        </select>
                        <span>계좌번호 </span>
                        <input type="text" id="bankNumber" name="bankNumber" maxlength="19" pattern="[0-9]*" required>
                    </div>
                    <div class="address"><span>주소 </span><input type="text" id="address" name="address" maxlength="100" required></div>
                    <div><span>이미지 </span><input type="file" id="image" name="image" accept=".jpg, .jpeg, .png"></div>
                </div>
                <div class="footer"><input class="employeeSubmit" type="submit" value="사원 등록"></div>
            </div>
        </form>
    </div>

    <style>
        .header {
            font-size: 23px;
            margin-bottom: 14px;
        }

        .container {
            background-color: rgb(255, 255, 255);
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.112);
            width: 820px;
            height: 530px;
            margin-top: 20px;
            border-radius: 10px;
        }

        .container-grid {
            justify-items: center;
            display: grid;
            grid-template-rows: 0.13fr 2fr 0.1fr;
            width: 100%;
            height: 530px;
            font-weight: 900;
        }

        .main-top {
            display: grid;
            grid-template-columns: 1.25fr 2fr;
            grid-template-rows: repeat(8, 1fr);
            justify-items: end;
            align-items: center;
        }

        .footer {
            display: flex;
            justify-content: center;
        }

        .main-top input,
        .main-top select {
            margin: 7px;
            height: 22px;
            margin-bottom: 15px;
            width: max-content;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .main-top input:focus,
        .main-top select:focus {
            border-color: #007bff;
            outline: none;
        }

        #password,
        #ext {
            margin-bottom: 20px;
        }

        .employeeSubmit {
            padding: 7px 12px;
            border: none;
            background-color: #007bff;
            color: white;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            font-size: 14px;
            cursor: pointer;
            font-weight: 900;
        }

        .employeeSubmit:hover {
            background-color: #0056b3;
        }
    </style>