<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${empty templateList}">
        <div class="no-template">키워드에 일치하는 결재 양식이 없습니다.</div>
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
                 <a class="template-btn" onclick="openModal()">
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

