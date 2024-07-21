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
                                    <div class="toggle"><a href="/orca/accountingDivision/salaryWrite">◽ 급여 입력</a></div>
                                    <div class="toggle"><a href="/orca/accountingDivision/salaryList">◽ 급여 목록</a></div>
                                </div>
                                <div class="calnedar title-toggle">◾ 4대보험</div>
                                <div class="calendar-link link">
                                    <div class="calendar-wirte toggle"><a href="/orca/accountingDivision/ratesByOne">◽ 조회 - 수정</a>
                                    </div>
                                </div>
                            </div>
                        </nav>
                    </aside>
                    <main id="salartWriteMain">

                        <div class="salaryWrite" id="content">
                            <h1>급여 입력</h1>

                            <form action="/orca/salary/write" method="post" id="salaryForm">
                                <label for="employeeId">사원번호 </label>
                                <input type="text" name="empNo" class="empNo">

                                <label for="salary">직급수당</label>
                                <input type="text" name="position" class="position">

                                <label for="bonus">상여금</label>
                                <input type="text" name="bonus" class="bonus">

                                <label for="mealAllowance">식대</label>
                                <input type="text" name="meals" class="meals">

                                <label for="weekdayWorkingHours">휴일근무시간</label>
                                <input type="text" name="holidayTime" class="holidayTime">

                                <label for="overtimeHours">연장근무시간</label>
                                <input type="text" name="overTime" class="overTime">

                                <label for="numberOfChildren">자녀 수</label>
                                <input type="text" name="person" class="person">

                                <input type="submit" name="작성하기" id="submitButton" value="작성하기">
                            </form>
                        </div>

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
                        var empNo = $('.empNo').val();
                        var position = $('.position').val();
                        var bonus = $('.bonus').val();
                        var meals = $('.meals').val();
                        var holidayTime = $('.holidayTime').val();
                        var overTime = $('.overTime').val();
                        var person = $('.person').val();

                        console.log(empNo);
                        console.log(position);
                        console.log(bonus);
                        console.log(meals);
                        console.log(holidayTime);
                        console.log(overTime);
                        console.log(person);

                        // AJAX 요청
                        $.ajax({
                            url: '/orca/salary/write',
                            method: 'post', // POST 방식으로 요청
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
                                    window.location.href = "/orca/accountingDivision/salaryList";
                                } else {
                                    alert("급여 작성 실패하셨습니다.");
                                    window.location.href = "/orca/accountingDivision/salaryWrite";
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

            <style>
                #salartWriteMain {
                    margin-top: 10%;
                    margin-left: 35%;
                    margin-right: 40%;
                    width: 500px;
                    height: 500px;
                    border: 1px solid black;
                    text-align: center;
                }


                #salaryForm {
                    width: 500px;
                    height: 400px;
                    display: grid;
                    grid-template-columns: 1fr 1fr;
                    grid-template-rows: repeat(8, 1fr);
                    align-items: center;

                    justify-items: center;
                    /* 텍스트 상자를 수평으로 가운데 정렬 */



                }

                #salaryForm>label {
                    margin-left: 40%;

                }

                input {
                    width: 40%;
                    margin-right: 25%;
                }

                input.holidayTime {
                    width: 10%;
                }

                input.overTime {
                    width: 10%;
                }

                input.person {
                    width: 10%;
                }


                /* 제출 버튼을 두 칸 차지하도록 설정 */
                #submitButton {
                    grid-column: 1 / span 2;
                    grid-row: 8;

                    width: 70%;
                    height: 50px;

                    background: #67a8cd;
                    color: white;
                    border-style: outset;
                    border-color: #67a8cd;
                    font: bold15px arial, sans-serif;
                    text-shadow: none;
                    margin: 5%;


                }

                /* 기본 a 태그 스타일 */
                a {
                    color: #000000;
                    /* 기본 글자색을 검정색으로 설정 */
                    text-decoration: none;
                    /* 밑줄 제거 */
                }

                /* a 태그에 마우스를 올렸을 때 스타일 */
                a:hover {
                    color: #87CEEB;
                    /* 하늘색으로 변경 */
                }

                
            </style>