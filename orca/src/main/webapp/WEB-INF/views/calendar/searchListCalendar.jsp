<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>

        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Calendar</title>
            <!-- 파비콘 -->
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <link rel="stylesheet" href="/css/calendar/searchListCalendar.css">
            <script defer src="/js/calendar/searchListCalendar.js"></script>

            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        </head>

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
            <div class="calendar-link"><a href="showCalendar">내 캘린더</a></div>

            <div class="selectContainer">
                <select id="yearSelect" onchange="handleMonthChange()"></select>
                <select id="monthSelect" onchange="handleMonthChange()"></select>
            </div>
            <div id="sidebarCalendarContainer">
                <div id="sidebarCalendar"></div>
            </div> 
            
        </aside>
        <main id="content">
            <div class="container">
                <span>
                    <h2>캘린더 검색</h2>
                </span>
                <span class="search-container">
                    <form action="searchListCalendar" method="get" id="calendar-search-form">
                        <input type="text" name="keyword" class="search-text">
                    </form>
                    <i class="fas fa-search search-icon"></i>
                </span>
                <table class="custom-table">
                    <thead>
                        <tr>
                            <th>제목</th>
                            <th>내용</th>
                            <th>작성자</th>
                            <th>시작일</th>
                            <th>종료일</th>
                            <th>등록일</th>
                            <th>공유 범위</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- 여기에 데이터를 추가하세요 -->
                    </tbody>
                </table>
                <div class="pageFooter">
                    <div class="totalPage"></div>
                </div>
            </div>
        </main>

        </body>

        </html>