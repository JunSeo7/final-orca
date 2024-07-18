<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ page import="com.groupware.orca.user.vo.UserVo" %>

            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>salary List</title>
                <!-- 파비콘 -->
                <link rel="icon" href="img/logo.png" type="image/png">
                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                <link rel="stylesheet"
                    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

                <!-- my css -->
                <link rel="stylesheet" href="/css/salary/list.css">

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
                    <main>
                        <div class="main" id="content">
                            <h1 class="salary-list">급여 목록조회</h1>

                            <table class="salaryList">
                                <thead>
                                    <tr>
                                        <th>글 번호</th>
                                        <th>사번</th>
                                        <th>이름</th>
                                        <th>최종 급여(원)</th>
                                        <th>날짜</th>
                                        <th>상세조회</th>
                                        <!-- <button onclick="detail();">조회</button> -->
                                    </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>


                            <div id="detailArea">

                            </div>

                            <div id="editArea" style="display:none;">
                                <h1>급여 수정</h1>
                                <form id="editForm">
                                    사원번호: <input type="text" name="empNo" id="empNo"><br>
                                    직급수당: <input type="text" name="position" id="position"><br>
                                    상여금: <input type="text" name="bonus" id="bonus"><br>
                                    식대: <input type="text" name="meals" id="meals"><br>
                                    휴일근무시간: <input type="text" name="holidayTime" id="holidayTime"><br>
                                    연장근무시간: <input type="text" name="overTime" id="overTime"><br>
                                    자녀 수: <input type="text" name="person" id="person"><br>
                                    <input type="submit" value="수정하기">
                                </form>
                            </div>

                        </div>
                    </main>
            </body>

            </html>

            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

            <script>

                salaryList();

                function salaryList() {
                    $.ajax({
                        url: "http://127.0.0.1:8080/orca/salary/list",
                        method: "get",
                        success: function (x) {
                            console.log(x);
                            $('div.editArea').remove();
                            const voList = x;
                            console.log(voList);

                            const tbody = document.querySelector("tbody");
                            let str = "";
                            for (let i = 0; i < voList.length; ++i) {
                                str += "<tr>";
                                str += "<td>" + voList[i].payrollNo + "</td>";
                                str += "<td>" + voList[i].empNo + "</td>";
                                str += "<td>" + voList[i].name + "</td>";
                                str += "<td>" + voList[i].totalSalary + "</td>";
                                str += "<td>" + voList[i].paymentDate + "</td>";
                                str += "<td><button onclick='detail(" + voList[i].payrollNo + ");'>조회</button></td>";
                                str += "</tr>";
                            }

                            tbody.innerHTML = str;

                        },

                    });
                }

            </script>


            <script>

                function detail(payrollNo) {
                    $('h1.salary-list').remove();
                    $('table.salaryList').remove();
                    $('div.editArea').remove();
                    $.ajax({
                        url: "http://127.0.0.1:8080/orca/salary/detail",
                        method: "GET",
                        data: {
                            payrollNo: payrollNo

                        },
                        success: function (data) {
                            const detailArea = document.querySelector("#detailArea");
                            console.log(data);
                            let str = "";
                            str += "<h1>급여 상세조회</h1>";
                            str += "<h3>번호 : " + data.payrollNo + "</h3>";
                            str += "<h3>사원번호 : " + data.empNo + "</h3>";
                            str += "<h3>이름 : " + data.name + "</h3>";
                            str += "<h3>국민연금 : " + data.nationalPension + "</h3>";
                            str += "<h3>건강보험 : " + data.healthInsurance + "</h3>";
                            str += "<h3>장기요양보험 : " + data.longCare + "</h3>";
                            str += "<h3>고용보험 : " + data.employmentInsurance + "</h3>";
                            str += "<h3>소득세 : " + data.incomeTax + "</h3>";
                            str += "<h3>지방소득세 : " + data.localIncomeTax + "</h3>";
                            str += "<h3>직급수당 : " + data.position + "</h3>";
                            str += "<h3>보너스 : " + data.bonus + "</h3>";
                            str += "<h3>휴일근무수당 : " + data.holiday + "</h3>";
                            str += "<h3>연장근로수당 : " + data.overTimeWork + "</h3>";
                            str += "<h3>식대 : " + data.meals + "</h3>";
                            str += "<h3>최종급여 : " + data.totalSalary + "</h3>";
                            str += "<h3>지급날짜 : " + data.paymentDate + "</h3>";
                            str += "<a href='http://127.0.0.1:8080/orca/salaryList'>급여 목록으로 돌아가기</a>";
                            str += "<button onclick='edit(" + data.payrollNo + ");'>수정하기</button>";
                            str += "<button onclick='del(" + data.payrollNo + ");'>삭제하기</button>";
                            detailArea.innerHTML = str;

                            // 수정 폼 태그 추가
                            document.querySelector("#editArea input[name='empNo']").value = data.empNo;
                            document.querySelector("#editArea input[name='position']").value = data.position;
                            document.querySelector("#editArea input[name='bonus']").value = data.bonus;
                            document.querySelector("#editArea input[name='meals']").value = data.meals;
                            document.querySelector("#editArea input[name='holidayTime']").value = data.holidayTime;
                            document.querySelector("#editArea input[name='overTime']").value = data.overTime;
                            document.querySelector("#editArea input[name='person']").value = data.person;




                        },


                    });
                }



            </script>

            <script>
                function edit(payrollNo) {
                    $('#detailArea').hide();
                    $('#editArea').show();


                    // 폼 제출 시 처리
                    $(document).ready(function () {
                        $('#editForm').on('submit', function (e) {
                            e.preventDefault();

                            let formData = {
                                payrollNo: payrollNo,
                                empNo: $('#empNo').val(),
                                position: $('#position').val(),
                                bonus: $('#bonus').val(),
                                meals: $('#meals').val(),
                                holidayTime: $('#holidayTime').val(),
                                overTime: $('#overTime').val(),
                                person: $('#person').val()

                            };

                            $.ajax({
                                url: "http://127.0.0.1:8080/orca/salary/edit",
                                method: "post",
                                dataType: 'json',
                                data: formData,
                                success: function (data) {
                                    alert("급여 수정 성공하셨습니다.");
                                    $('#editArea').hide();
                                    window.location.href = "/orca/salaryList";

                                },
                            });
                        });
                    });

                }

            </script>

            <script>

                function del(payrollNo) {
                    $.ajax({
                        url: "http://127.0.0.1:8080/orca/salary/delete",
                        method: "delete",
                        data: {
                            payrollNo: payrollNo,
                        },
                        success: function (data) {
                            alert("삭제 성공하셨습니다.");
                            window.location.href = "/orca/salaryList";
                        },

                    });
                }

            </script>


            
           