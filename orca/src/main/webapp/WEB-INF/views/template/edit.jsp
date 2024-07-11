<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>결재 양식 수정</title>

     <!--파비콘-->
     <link rel="icon" href="/img/header/logo.png" type="image/png">

     <script defer src="/js/template/edit.js"></script>
     <link rel="stylesheet" href="/css/template/add.css">

     <script defer src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

     <%-- 썸머노트 라이브러리 연결 --%>
     <link href="/css/template/summernote/summernote-lite.css" rel="stylesheet">
     <script defer src="/js/template/summernote/summernote-lite.js"></script>
     <script defer src="/js/template/summernote/summernote-ko-KR.js"></script>


</head>
<body>

<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<%@ include file="/WEB-INF/views/template/aside.jsp" %>


      <main id="content">
          <h1>결재양식 수정</h1>
          <form id="editForm" action="/orca/template/edit" method="POST" >
            <input hidden type="text" name="templateNo" id="templateNo" value="${templateNo}">
              <table class="document-table">
                  <tr>
                      <th>카테고리</th>
                      <td>
                          <select id="category" name="categoryNo">
                              <!-- 카테고리는 fetchCategories() 함수에서 동적으로 추가됩니다. -->
                          </select>
                      </td>
                  </tr>

                  <tr>
                      <th>결재 양식명</th>
                      <td>
                          <input type="text" id="name" name="title">
                      </td>
                  </tr>

                  <tr>
                      <th colspan='2'> 양식 내용 </th>
                  </tr>
                  <tr>
                      <td colspan='2'>
                          <textarea id="summernote" name="content"></textarea>
                      </td>
                  </tr>
              </table>
              <br>
              <br>
              <button type="submit" class="submit_btn">수정</button>
          </form>
      </main>

</form>
</body>
</html>
