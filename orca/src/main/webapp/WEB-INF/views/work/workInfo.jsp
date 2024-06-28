<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ORCA</title>
    <link rel="stylesheet" href="test.css">
    <script defer src="test.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/layout/header.jsp" %>
    <button id="toggleSidebar" onclick="toggleSidebar()">메뉴</button>
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
                        <li><a href="#" onclick="loadPage('vacation.jsp')">휴가신청</a></li>
                        <li><a href="#" onclick="loadPage('sickleave.jsp')">병가신청</a></li>
                        <li><a href="#" onclick="loadPage('etc.jsp')">기타신청</a></li>
                        <li><a href="#" onclick="loadPage('workinfo.jsp')">근무정보</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </aside>
    <main id="content">

      <div class="container">
        <h1>근무 정보</h1>
        <div class="date-selection">
            <label for="start-date">기간 : </label>
            <input type="date" id="start-date" name="start-date" style="color: dimgray;">
            <label for="end-date">-</label>
            <input type="date" id="end-date" name="end-date" style="color: dimgray;">
            <button onclick="fetchData()" class="t-btn">조회</button>
        </div>
        <table>
            <thead>
                <tr>
                    <th>날짜</th>
                    <th>요일</th>
                    <th>출근</th>
                    <th>퇴근</th>
                    <th>지각</th>
                    <th>조퇴</th>
                    <th>결근</th>
                    <th>연장(시간)</th>
                    <th>휴무(시간)</th>
                </tr>
            </thead>
            <tbody id="data-table-body">
                <!-- 데이터 표시 -->
            </tbody>
        </table>
    </div>

    </main>
</body>
</html>

