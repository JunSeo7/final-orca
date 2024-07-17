<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>salary List</title>
    <!-- 파비콘 -->
    <link rel="icon" href="img/logo.png" type="image/png">
    <link rel="stylesheet" href="/css/managementSupport/main.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/css/salary/list.css">
</head>

<body>
    <header>
        <div class="header-left">
            <img src="" alt="Logo" class="logo">
            <h2 class="header-title">ORCA</h2>
        </div>
        <div class="header-right">
            <span class="icon">알림</span>
            <span class="icon">조직도</span>
            <span class="icon">설정</span>
        </div>
    </header>

    <button id="toggleSidebar" class="sidebar-toggle" onclick="toggleSidebar()">메뉴</button>
    <aside id="sidebar" class="sidebar">
        <div class="profile" onclick="toggleProfile()">
            <img src="profile.png" alt="Profile Picture" class="profile-pic">
            <p class="profile-info">회계 1팀 | <span>양파쿵야</span></p>
        </div>
        <hr>
        <nav>
            <div class="sidebar-nav">
                <div class="approval title-toggle">◾ 급여 관리</div>
                <div class="approval-list">
                    <div class="toggle"><a href="/orca/salaryWrite">◽ 급여 입력</a></div>
                    <div class="toggle"><a href="/orca/salaryList">◽ 급여 목록</a></div>
                </div>
                <div class="calnedar title-toggle">◾ 4대보험</div>
                <div class="calendar-link link">
                    <div class="calendar-wirte toggle">◽ 조회 - 수정</div>
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
                    사원번호: <input type="text" name="empNo"><br>
                    직급수당: <input type="text" name="position"><br>
                    상여금: <input type="text" name="bonus"><br>
                    식대: <input type="text" name="meals"><br>
                    휴일근무시간: <input type="text" name="holidayTime"><br>
                    연장근무시간: <input type="text" name="overTime"><br>
                    자녀 수: <input type="text" name="person"><br>
                    <input type="submit" value="수정하기">
                </form>
            </div>

        </div>
    </main>
</body>

</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script>
    

    $.ajax({
    url: "http://127.0.0.1:8080/orca/salary/list",
    method: "GET",
    success: function(x){
        $('div.editArea').remove();
        const voList = x;
        console.log(voList);
        
        const tbody = document.querySelector("tbody");
        let str = "";
        for(let i=0; i < voList.length; ++i){
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
</script>


<script>

    function detail(payrollNo){
        $('h1.salary-list').remove();
        $('table.salaryList').remove();
        $('div.editArea').remove();
        $.ajax({
        url: "http://127.0.0.1:8080/orca/salary/detail",
        method: "GET",
        data: {
            payrollNo: payrollNo

        } , 
        success: function(data){
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
            str += "<button onclick='edit("+ data.payrollNo+ ");'>수정하기</button>";
            str += "<button onclick='del("+ data.payrollNo + ");'>삭제하기</button>"; 
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
        }

        // 폼 제출 시 처리
        $(document).ready(function() {
            $('#editForm').on('submit', function(e) {
                e.preventDefault();

                var empNo = $('#empNo').val();
                var position = $('#position').val();
                var bonus = $('#bonus').val();
                var meals = $('#meals').val();
                var holidayTime = $('#holidayTime').val();
                var overTime = $('#overTime').val();
                var person = $('#person').val();

                $.ajax({
                    url: "http://127.0.0.1:8080/orca/salary/edit",
                    method: "POST",
                    data: {
                        empNo:empNo,
                        position:position,
                        bonus:bonus,
                        meals:meals,
                        holidayTime:holidayTime,
                        overTime:overTime,
                        person:person
                    },
                    data: $(this).serialize(),
                    success: function(data) {
                        console.log(data);
                        // 수정 완료 후 상세보기 다시 표시
                        $('#editArea').hide();
                        // detail(data.payrollNo);
                        // $('#detailArea').show();
                        $('.salaryList').show();
                    }
                });
            });
        });

</script>

<script>

    function del(payrollNo){
        $.ajax({
            url: "http://127.0.0.1:8080/orca/salary/delete", 
            method: "delete",
            data: {
                payrollNo:payrollNo,
            },
            success: function(data){
                console.log("del result :"+ data);
            },
            
        });
    }

</script>