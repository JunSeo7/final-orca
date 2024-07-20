<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.groupware.orca.user.vo.UserVo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시물 및 조회수 통계</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script defer src="/js/board/sta.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/board/sta.css">
    <style>
        .chart-container {
            width: 80%;
            margin: auto;
            padding: 20px;
        }
    </style>
</head>
<body>
       <header>
          <div class="header-left">
                     <a href="/orca/home"><img src="/img/header/logo.png" alt="Logo" class="logo"></a>
                     <a href="/orca/home" style="text-decoration: none; color:black;">
                         <h2>ORCA</h2>
                     </a>
                 </div>
                 <div class="header-right">
                     <span class="icon"><img src="/img/header/bell.png" alt="bell" class="icon"></span>
                     <span class="icon"><img src="/img/header/organization-chart.png" alt="organization-chart" class="icon"></span>
                     <span class="icon"><img src="/img/header/settings.png" alt="settings" class="icon"></span>
                 </div>
        </header>
    <button id="toggleSidebar" onclick="toggleSidebar()">메뉴</button>
       <% UserVo loginUserVo=(UserVo) session.getAttribute("loginUserVo"); String
                           imgChangeName=(loginUserVo.getImgChangeName() !=null) ? loginUserVo.getImgChangeName()
                           : "profile.png" ; %>
    <aside id="sidebar">
        <div class="profile" onclick="toggleProfile()">
            <img src="/upload/user/<%= imgChangeName %>" alt="Profile Picture" class="profile-pic">

                                                    <p class="profile-info">
                                                        <%= loginUserVo.getTeamName() %> | <span>
                                                                <%= loginUserVo.getName() %>
                                                            </span>
                                                    </p>
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
                <li><a href="#" onclick="loadPage('home.jsp')">홈</a></li>
                <li><a href="#" onclick="loadPage('chat.jsp')">채팅</a></li>
                <li><a href="#" onclick="loadPage('calendar.jsp')">캘린더/할일</a></li>
                <li><a href="#" onclick="loadPage('documents.jsp')">문서관리</a></li>
                <li><a href="#" onclick="loadPage('attendance.jsp')">근태</a></li>
                <li><a href="#" onclick="loadPage('vote.jsp')">투표</a></li>
                <li><a href="#" onclick="loadPage('drive.jsp')">드라이브</a></li>
                <li><a href="#" onclick="loadPage('mail.jsp')">메일</a></li>
                <li><a href="#" onclick="loadPage('settings.jsp')">설정</a></li>
            </ul>
        </nav>
    </aside>
    <div class="chart-container">

        <a href="/orca/board">게시물 목🔙</a>
        <canvas id="statsChart" width="400" height="200"></canvas>
    </div>

    <script>
        function ajaxStatsByDate() {
            return $.ajax({
                url: '/orca/board/statsByDate',
                method: 'GET',
                dataType: 'json'
            });
        }

        function renderChart(data) {
            const ctx = document.getElementById('statsChart').getContext('2d');
            const labels = data.map(item => item.ENROLL_DATE_STR);
            const postCounts = data.map(item => item.POST_COUNT);
            const viewCounts = data.map(item => item.VIEWS);

            new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [
                        {
                            label: '총 게시물 수',
                            data: postCounts,
                            backgroundColor: 'rgba(255, 99, 132, 0.2)',
                            borderColor: 'rgba(255, 99, 132, 1)',
                            borderWidth: 1
                        },
                        {
                            label: '총 조회수',
                            data: viewCounts,
                            backgroundColor: 'rgba(54, 162, 235, 0.2)',
                            borderColor: 'rgba(54, 162, 235, 1)',
                            borderWidth: 1
                        }
                    ]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        }

        $(document).ready(function() {
            ajaxStatsByDate().done(function(data) {
                renderChart(data);
            });
        });
    </script>
</body>
</html>
