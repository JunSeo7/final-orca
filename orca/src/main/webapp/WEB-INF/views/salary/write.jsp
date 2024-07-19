<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ page import="com.groupware.orca.user.vo.UserVo" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>salary write</title>
                <!-- 파비콘 -->
                <link rel="icon" href="img/logo.png" type="image/png">
                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                <link rel="stylesheet"
                    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

                <!-- my css -->
                <link rel="stylesheet" href="/css/salary/write.css">

                <!-- 공용 css -->
                <link rel="stylesheet" href="/css/layout/aside.css">
                <script defer src="/js/layout/aside.js"></script>

                <link rel="icon" href="/img/logo.png" type="image/png">
                <link rel="stylesheet" href="/css/layout/header.css">
                <script defer src="/js/layout/header.js"></script>

            </head>

            <body>
                <% UserVo loginUserVo=(UserVo) session.getAttribute("loginUserVo"); String
                    imgChangeName=(loginUserVo.getImgChangeName() !=null) ? loginUserVo.getImgChangeName()
                    : "profile.png" ; %>

                    <header>
                        <div class="header-left">
                            <a href="/orca/home"><img src="/img/header/logo.png" alt="Logo" class="logo"></a>
                            <a href="/orca/home" style="text-decoration: none; color:black;">
                                <h2>ORCA</h2>
                            </a>
                        </div>
                        <div class="header-right">
                            <span class="icon"><img src="/img/header/bell.png" alt="bell" class="icon"></span>
                            <span class="icon"><img src="/img/header/organization-chart.png" alt="organization-chart"
                                    class="icon organizationChart"></span>
                            <span class="icon"><img src="/img/header/settings.png" alt="settings" class="icon"></span>
                        </div>
                    </header>

                    <button id="toggleSidebar" class="sidebar-toggle" onclick="toggleSidebar()">메뉴</button>
                    <aside id="sidebar" class="sidebar">

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

                            <div id="empNo"></div>
                            <div id="partName"></div>
                            <div id="position"></div>
                            <div id="phone"></div>
                            <div id="extensionCall"></div>
                            <div id="email"></div>
                            <div id="change-password">비밀번호 변경</div>
                            <button onclick="logout()">로그아웃</button>
                        </div>



                        <!-- <div class="profile" onclick="toggleProfile()">
                                <img src="profile.png" alt="Profile Picture" class="profile-pic">
                                <p class="profile-info">회계 1팀 | <span>양파쿵야</span></p>
                            </div>
                            <hr> -->
                        <nav>
                            <div class="sidebar-nav">
                                <div class="approval title-toggle">◾ 급여 관리</div>
                                <div class="approval-list">
                                    <div class="toggle"><a href="/orca/salaryWrite">◽ 급여 입력</a></div>
                                    <div class="toggle"><a href="/orca/salaryList">◽ 급여 목록</a></div>
                                </div>
                                <div class="calnedar title-toggle">◾ 4대보험</div>
                                <div class="calendar-link link">
                                    <div class="calendar-wirte toggle"><a href="/orca/ratesByOne">◽ 조회 - 수정</a>
                                    </div>
                                </div>
                            </div>
                        </nav>
                    </aside>
                    <main id="salartWriteMain">

                        <div class="salaryWrite" id="content">
                            <h1>급여 입력</h1>

                            <form action="/orca/salary/write" method="post" id="salaryForm">
                                사원번호 :<input type="text" name="empNo" class="empNo">
                                <br>
                                직급수당: <input type="text" name="position" class="position">
                                <br>
                                상여금: <input type="text" name="bonus" id="bonus">
                                <br>
                                식대: <input type="text" name="meals" id="meals">
                                <br>
                                휴일근무시간: <input type="text" name="holidayTime" id="holidayTime">
                                <br>
                                연장근무시간: <input type="text" name="overTime" id="overTime">
                                <br>
                                자녀 수: <input type="text" name="person" id="person">
                                <br>
                                <input type="submit" name="작성하기">
                            </form>

                    </main>
            </body>

            </html>

            <script>
                $(document).ready(function () {
                    $('#salaryForm').submit(function (e) {
                        e.preventDefault(); // 폼 제출 기본 동작 방지

                        // 폼 데이터를 가져오기
                        console.log("폼 제출 시작");

                        // 직접 DOM으로 데이터 가져오기
                        var empNo = document.querySelector('.empNo').value;
                        var position = document.querySelector('.position').value;
                        var bonus = document.getElementById('bonus').value;
                        var meals = document.getElementById('meals').value;
                        var holidayTime = document.getElementById('holidayTime').value;
                        var overTime = document.getElementById('overTime').value;
                        var person = document.getElementById('person').value;

                        console.log(empNo);
                        console.log(position);

                        // AJAX 요청
                        $.ajax({
                            url: 'http://127.0.0.1:8080/orca/salary/write',
                            method: 'POST', // POST 방식으로 요청
                            dataType: 'json',
                            data: {
                                empNo: empNo,
                                position: position,
                                bonus: bonus,
                                meals: meals,
                                holidayTime: holidayTime,
                                overTime: overTime,
                                person: person
                            },
                            success: function (response) {
                                // 성공적으로 데이터를 서버에 전송한 후 처리할 내용
                                console.log('데이터 전송 성공');

                                if (response == 1) {
                                    alert("급여 작성 성공하셨습니다.");
                                    window.location.href = "/orca/salaryList";
                                } else {
                                    alert("급여 작성 실패하셨습니다.");
                                    window.location.href = "orca/salaryWrite";
                                }
                            },
                            error: function (xhr, status, error) {
                                // 오류 발생 시 처리
                                console.error('데이터 전송 실패:', error);
                                // 추가적인 오류 처리 로직을 여기에 추가할 수 있습니다.
                            }
                        });
                    });
                });

            </script>