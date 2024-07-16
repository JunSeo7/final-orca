<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>salary detail</title>
    <!-- 파비콘 -->
    <link rel="icon" href="img/logo.png" type="image/png">
    <link rel="stylesheet" href="/css/managementSupport/main.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script defer src="/js/managementSupport/main.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/css/salary/detail.css">
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
                    <div class="toggle">◽ 급여 입력</div>
                    <div class="toggle">◽ 급여 목록</div>
                </div>
                <div class="calnedar title-toggle">◾ 4대보험</div>
                <div class="calendar-link link">
                    <div class="calendar-list toggle">◽입력 - 수정</div>
                    <!-- <div class="calendar-wirte toggle">◽ 조회</div> -->
                </div>
            </div>
        </nav>
    </aside>
    <main>
        <div class="main" id="content">
            <h1>급여 목록조회</h1>

            <div id="detailArea">

            </div>
            
        </div>
    </main>
</body>

</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script>

    function detail(x){

       

        $.ajax({
        url: "http://127.0.0.1:8080/orca/salary",
        method: "GET",
        data: {
            payrollNo: x.payrollNo
        } , 
        success: function(){
            const detailArea = document.querySelector("#detailArea");

            let str = "";
            str += "<h2>상세조회</h2>";
            str += "<h3>번호 : " + x.payrollNo + "</h3>";
            str += "<h3>사원번호 : " + x.empNo + "</h3>";
            str += "<h3>지급날짜 : " + x.name + "</h3>";
            str += "<h3>이름 : " + x.nationalPension + "</h3>";
            str += "<h3>국민연금 : " + x.healthInsurance + "</h3>";
            str += "<h3>건강보험 : " + x.longCare + "</h3>";
            str += "<h3>장기요양보험 : " + x.employmentInsurance + "</h3>";
            str += "<h3>고용보험 : " + x.incomeTax + "</h3>";
            str += "<h3>소득세 : " + x.localIncomeTax + "</h3>";
            str += "<h3>지방소득세 : " + x.position + "</h3>";
            str += "<h3>직급수당 : " + x.bonus + "</h3>";
            str += "<h3>보너스 : " + x.holiday + "</h3>";
            str += "<h3>휴일근무수당 : " + x.overTimeWork + "</h3>";
            str += "<h3>연장근로수당 : " + x.meals + "</h3>";
            str += "<h3>식대 : " + x.totalSalary + "</h3>";
            str += "<h3>최종급여 : " + x.paymentDate + "</h3>";
            // str += `<button onclick='edit(${x.no});'>수정하기</button>`;
            // str += `<button onclick='del(${x.no});'>삭제하기</button>`; 

            detailArea.innerHTML = str;
        },


        });
    }
    

    
</script>