<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Calendar</title>
     <!-- 파비콘 -->
        <link rel="stylesheet" href="/css/calendar/showCalendar.css">
        <script defer src="/js/calendar/showCalendar.js"></script>
</head>
<body>
    <header>
            <div class="header-left" onclick="loadPage('home.jsp')">
                <img src="logo.png" alt="Logo" class="logo">
                <h2>ORCA</h2>
            </div>
            <div class="header-right">
                <span>알림</span>
                <span>조직도</span>
                <span>설정</span>
            </div>
        </header>
        <button id="toggleSidebar" onclick="toggleSidebar()">메뉴</button>
        <aside id="sidebar">
            <div class="profile" onclick="toggleProfile()">
                <img src="profile.png" alt="Profile Picture" class="profile-pic">
                <P>SW팀 | <span>양파쿵야</span></P>
                <hr>
            </div>

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
            <div class="aside-Calendar-sell">
                <div class="aside-Calendar">
                    <div class="new-schedule" onclick="submitNewEvent()">
                        일정 등록
                    </div>
                </div>
                <div class="selectContainer">
                    <select id="yearSelect" onchange="handleMonthChange()"></select>
                    <select id="monthSelect" onchange="handleMonthChange()"></select>
                </div>
                <div id="sidebarCalendarContainer">
                    <div id="sidebarCalendar"></div>
                </div> <!-- 사이드 캘린더 추가 -->
            </div>

            <hr>
            <nav>
                <ul>
                    <div class="my-calendar-title">내 캘린더</div>
                    <div class="my-calendar">
                        <li><a href="" onclick="loadPage('settings.jsp'); toggleCheck(this); return false;">사내 캘린더</a></li>
                        <li><a href="" onclick="loadPage('home.jsp'); toggleCheck(this); return false;">내 캘린더</a></li>
                        <li><a href="" onclick="loadPage('chat.jsp'); toggleCheck(this); return false;">팀 캘린더</a></li>
                    </div>
                </ul>
            </nav>
        </aside>
        <main id="content">
            <div id="calendar"></div>
        </main>

        <div class="bar">사내 워크숍</div>
        <div class="new-calendar-form" id="newEventForm">
            <div class="form-header">
                <h2>일정 등록</h2>
                <button class="cancel-button" onclick="hideNewEventForm()">✖</button>
            </div>
            <div class="form-body">
                <form id="eventForm" action="/orca/calendar/writeCalendar" method="post">
                    <div class="form-group">
                        <label for="eventTitle">일정 제목</label>
                        <input type="text" id="eventTitle" name="title" required>
                    </div>
                    <div class="form-group">
                        <label for="eventDescription">일정 내용</label>
                        <textarea id="eventDescription" name="content" rows="4"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="startDate">시작일</label>
                        <input type="date" id="startDate" name="startDate" required>
                    </div>
                    <div class="form-group">
                        <label for="endDate">종료일</label>
                        <input type="date" id="endDate" name="endDate" required>
                    </div>
                    <div class="form-group">
                        <label for="repeatOption">반복 옵션</label>
                        <select id="repeatOption" name="repeatYn">
                            <option value="N">반복 안 함</option>
                            <option value="Y">반복 추가</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="shareScope">공유 범위</label>
                        <select id="shareScope" name="range">
                            <option value="individual">개인</option>
                            <option value="team">팀</option>
                        </select>
                    </div>
                    <div class="form-footer">
                        <button type="submit">일정 등록</button>
                    </div>
                </form>
            </div>
        </div>
</main>

</body>
</html>
