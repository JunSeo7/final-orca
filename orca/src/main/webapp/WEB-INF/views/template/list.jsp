<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>결재 양식 목록</title>

        <!--파비콘-->
        <link rel="icon" href="/img/header/logo.png" type="image/png">

        <link rel="stylesheet" href="/css/template/list.css">
        <script defer src="/js/template/list.js"></script>

 <!-- jQuery 라이브러리 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
        <%@ include file="/WEB-INF/views/layout/header.jsp" %>
        <%@ include file="/WEB-INF/views/template/aside.jsp" %>

<main id="content">
<div class="content-title">
    <h2>결재 양식 목록</h2>

    <div class="search_box">
        <select class="search_select" id="searchType">
            <option value="categoryName">카테고리</option>
            <option value="title">제목</option>
        </select>
        <input class="search_text" id="searchText" type="text" placeholder="검색어 입력">
        <img class="search_img" src="/img/document/search.png" alt="검색 아이콘" onclick="searchTemplate()">
    </div>
</div>
        <hr>
         <p>* 결재양식을 등록하고 관리합니다.</p>
        <a href="/orca/template/add">
             <button class="template-add-btn" >+ 결재양식 등록</button>
         </a>


        <c:choose>
            <c:when test="${empty templateList}">
            <div class="no-template">등록된 결재 양식이 없습니다.</div>
            </c:when>
            <c:otherwise>
                <div class="template-box">
                     <c:forEach var="template" items="${templateList}">

                          <div class="template" data-template-no="${template.templateNo}">
                             <span class="template-category">카테고리 : ${template.categoryName}</span>
                             <br>
                             <span class="template-title">양식명 : ${template.title} [${template.templateNo}]</span>
                             <br>
                             <span class="template-enroll">생성날짜 : ${template.enrollDate}</span>
                             <hr>
                             <a class="template-btn edit-btn" data-template-no="${template.templateNo}">
                                 <img class="edit_img" src="/img/document/edit.png" alt="수정 아이콘">
                             </a>
                             <a class="template-btn delete-btn" data-template-no="${template.templateNo}">
                                 <img class="delete_img" src="/img/document/delete.png" alt="삭제 아이콘">
                             </a>
                          </div>

                     </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
</main>


</body>
</html>
