<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>salary retesList</title>
    <!-- 파비콘 -->
    <link rel="icon" href="img/logo.png" type="image/png">
    <link rel="stylesheet" href="/css/managementSupport/main.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script defer src="/js/managementSupport/main.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
            <h1>4대 보험 요율 조회</h1>

            <div id="ratesList">

            </div>

            <form id="editRates">
              국민연금: <input type="text" name="pensionPercentage"><br>
              건강보험: <input type="text" name="healthInsurancePercentage"><br>
              장기요양보험: <input type="text" name="longCarePercentage"><br>
              고용보험: <input type="text" name="employmentInsurancePercentage"><br>
              지방소득세: <input type="text" name="localIncomeTaxPersentage"><br>
              글 번호: <input type="text" name="ratesNo"><br>
              <input type="submit" value="수정하기">
          </form>


        </div>
    </main>
</body>

</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script>
      $.ajax({
          url: "http://127.0.0.1:8080/orca/salary/ratesList",
          method: "GET",
          success: function(data) {

            const voList = data;

            const ratesList = document.querySelector("#ratesList");
            let str = "";
            for (let i = 0; i < voList.length; ++i) {
                str += "<div>";
                str += "<h3>글 번호 : " + voList[i].ratesNo + "</h3>";
                str += "<h3>기준연도 : " + voList[i].baseYear + "</h3>";
                str += "<h3>국민연금 : " + voList[i].pensionPercentage + "</h3>";
                str += "<h3>건강보험 : " + voList[i].healthInsurancePercentage + "</h3>";
                str += "<h3>장기요양보험 : " + voList[i].longCarePercentage + "</h3>";
                str += "<h3>고용보험 : " + voList[i].employmentInsurancePercentage + "</h3>";
                str += "<h3>지방 소득세 : " + voList[i].localIncomeTaxPersentage + "</h3>";
                str += "<button onclick='edit(" + voList[i].ratesNo + ");'>수정 하기</button>";
                str += "</div>";
            }
              ratesList.innerHTML = str;

              document.querySelector("#editArea input[name='position']").value = data.position;
            document.querySelector("#editArea input[name='bonus']").value = data.bonus;
            document.querySelector("#editArea input[name='meals']").value = data.meals;
            document.querySelector("#editArea input[name='holidayTime']").value = data.holidayTime;
            document.querySelector("#editArea input[name='overTime']").value = data.overTime;
            document.querySelector("#editArea input[name='person']").value = data.person;
          }
      });


  // $.ajax({


  //   url: "http://127.0.0.1:8080/orca/salary/ratesList",
  //   method: "get",
  //   data: {
  //     ratesNo:ratesNo
  //   },
  //   success: function(data){

  //     const ratesList = document.querySelector("#ratesList");
  //     let str = "";
  //     str += "<div>";
  //     str += "<h3>글 번호 : " + data.ratesNo + "</h3>";
  //     str += "<h3>기준연도 : " + data.baseYear + "</h3>";
  //     str += "<h3>국민연금 : " + data.pensionPercentage + "</h3>";
  //     str += "<h3>건강보험 : " + data.healthInsurancePercentage + "</h3>";
  //     str += "<h3>장기요양보험 : " + data.longCarePercentage + "</h3>";
  //     str += "<h3>고용보험 : " + data.employmentInsurancePercentage + "</h3>";
  //     str += "<h3>지방 소득세 : " + data.localIncomeTaxPersentage + "</h3>";
  //     str += "<td><button onclick='edit(" + data.ratesNo + ");'>수정</button></td>";
  //     str += "</div>";
  //     ratesList.innerHTML = str;


  //   },
  // });
</script>

<script>

  $.ajax({

  });
</script>