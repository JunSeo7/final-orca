<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Home</title>
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

            <table class="salaryWrite">
                <thead>
                    <tr>
                        <th>글 번호</th>
                        <th>사번</th>
                        <th>이름</th>
                        <th>국민연금</th>
                        <th>건강보험</th>
                        <th>장기요양보험</th>
                        <th>고용보험</th>
                        <th>소득세</th>
                        <th>지방소득세</th>
                        <th>직급수당</th>
                        <th>상여금</th>
                        <th>휴일근로수당</th>
                        <th>연장근로수당</th>
                        <th>식대</th>
                        <th>최종 급여(원)</th>
                        <th>날짜</th>
                    </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </main>
</body>

</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script>
  $.ajax({
    url: "http://127.0.0.1:8080/orca/salary",
    method: "GET", 
    success: function(x){

      const voList = x;
      console.log(voList);

      const tbody = document.querySelector("tbody");
      let str = "";
      for(let i=0; i < voList.length; ++i){
        str += "<tr>";
        str += "<td>" + voList[i].payrollNo + "</td>";
        str += "<td>" + voList[i].empNo + "</td>";
        str += "<td>" + voList[i].name + "원"  + "</td>";
        str += "<td>" + voList[i].totalSalary + "</td>";
        str += "<td>" + voList[i].paymentDate + "</td>";
        str += "</tr>";
      }
      tbody.innerHTML = str;

    },
  

  });
</script>