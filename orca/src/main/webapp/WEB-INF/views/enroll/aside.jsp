<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>

        <!DOCTYPE html>
        <html>

        <head>
            <title>ORCA</title>
            <!-- 파비콘 -->
            <link rel="icon" href="/img/logo.png" type="image/png">

            <link rel="stylesheet" href="/css/document/aside.css">
            <script defer src="/js/enroll/enroll.js"></script>
        </head>

        <body>
            <aside id="sidebar">
                <div class="profile" onclick="toggleProfile()">
                    <img src="profile.png" alt="Profile Picture" class="profile-pic">
                    <p>SW팀 | <span>양파쿵야</span></p>
                </div>
                <hr>
                <div id="profileDetail" class="profile-detail hidden">
                    <p>상태 설정</p>
                    <p>상태 메시지</p>
                    <p>@멘션 확인하기</p>
                    <p>파일 리스트</p>
                    <p>직책</p>
                    <p>생년월일</p>
                    <p>휴대전화</p>
                    <p>raji1004@naver.com</p>
                    <button onclick="logout()">로그아웃</button>
                </div>
                <nav>
                    <ul>
                        <li><a href="#" onclick="toggleSubMenu('attendanceMenu')">근태</a>
                            <ul id="attendanceMenu" class="submenu hidden">
                                <li><a href="/orca/work/workInfo">근무정보</a></li>
                                <li><a href="/orca/vacation/vacationWrite">휴가신청</a></li>
                                <li><a href="/orca/sick/sickLeave">병가신청</a></li>
                                <li><a href="/orca/etc/etcWrite">기타신청</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </aside>

        </body>

        </html>