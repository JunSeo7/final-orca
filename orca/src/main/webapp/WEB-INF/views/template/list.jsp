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

<main id="content">
<h2>결재 양식 목록</h2>
        <div class="search_box">
            <select class="search_select" id="searchType">
                <option value="categoryName">카테고리</option>
                <option value="title">제목</option>
            </select>
            <input class="search_text" id="searchText" type="text" placeholder="검색어 입력">
            <img class="search_img" src="/img/document/search.png" alt="검색 아이콘" onclick="searchTemplate()">
        </div>

        <div id="searchResults">
         <c:forEach var="template" items="${templateList}">
          <div class="template-lines-box template-div" data-template-no="${template.templateNo}">
              <div class="template-lines">
                 <span class="template-title">카테고리 : ${template.categoryName}</span>
                 <br>
                 <span class="template-title">양식명 : ${template.title}</span>
                 <br>
                 <span class="template-enroll">생성날짜 : ${template.enrollDate}</span>
                 <hr>
                 <a class="template-lines-btn" onclick="openModal()">
                     <img class="edit_img" src="/img/document/edit.png" alt="수정 아이콘">
                 </a>
                 <a class="template-lines-btn delete-btn" data-template-no="${template.templateNo}">
                     <img class="delete_img" src="/img/document/delete.png" alt="삭제 아이콘">
                 </a>
             </div>
         </div>
     </c:forEach>
      </div>
     </main>


</body>
</html>
