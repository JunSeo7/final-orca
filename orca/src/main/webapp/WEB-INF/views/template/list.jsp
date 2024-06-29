<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>결재 양식 목록</title>

        <!--파비콘-->
        <link rel="icon" href="/img/logo.png" type="image/png">

        <link rel="stylesheet" href="/css/template/list.css">
        <script defer src="/js/template/list.js"></script>

 <!-- jQuery 라이브러리 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
        <%@ include file="/WEB-INF/views/layout/header.jsp" %>
        <%@ include file="/WEB-INF/views/template/aside.jsp" %>

<main>
<h2>결재 양식 목록</h2>
        <div class="search_box">
            <select class="search_select">
                <option>카테고리</option>
                <option>제목</option>
            </select>
            <input class="search_text" type="text" placeholder="검색어 입력">
            <img class="search_img" src="img/document/search.png" alt="검색 아이콘">
        </div>

         <c:forEach var="template" items="${templateList}">
          <div class="approval-lines-box template-div" data-template-no="${template.templateNo}">
              <div class="approval-lines">
                 <span class="approval-title">카테고리 : ${template.categoryName}</span>
                 <br>
                 <span class="approval-title">양식명 : ${template.title}</span>
                 <br>
                 <span class="approval-enroll">생성날짜 : ${template.enrollDate}</span>
                 <hr>
                 <a class="approval-lines-btn" onclick="openModal()">
                     <img class="edit_img" src="/img/edit.png" alt="수정 아이콘">
                 </a>
                 <a class="approval-lines-btn delete-btn"data-template-no="${template.templateNo}">
                     <img class="delete_img" src="/img/delete.png" alt="삭제 아이콘">
                 </a>
             </div>
         </div>
     </c:forEach>
     </main>


</body>
</html>
