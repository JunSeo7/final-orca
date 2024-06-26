<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>결재양식 수정</title>

     <!--파비콘-->
        <link rel="icon" href="img/logo.png" type="image/png">

        <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/summernote.css" rel="stylesheet">

        <link rel="stylesheet" href="main.css">
        <script defer src="main.js"></script>

        <link rel="stylesheet" href="/css/template/edit.css">
        <script defer src="/js/template/edit.js"></script>

</head>
<body>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<%@ include file="/WEB-INF/views/template/aside.jsp" %>
<form action="/template/add" method="POST" enctype="multipart/form-data">
      <main id="content">
             <div class="container">
               <h1>결재양식 수정</h1>
               <form id="templateForm">
                   <div class="form-group">
                       <label for="category">카테고리:</label>
                       <select id="category" name="categoryNo" required>
                          <option value="">--선택--</option>
                          <option value="1">경비 및 지출 관리</option>
                          <option value="2">인사 및 근태 관리</option>
                          <option value="3">프로젝트 및 업무 관리</option>
                          <option value="4">교육 및 지원</option>
                          <option value="5">기타</option>
                       </select>
                   </div>
                     <div class="form-group">
                         <label for="name">결재 양식명:</label>
                         <input type="text" id="name" name="title" required>
                     </div>
                     <div class="form-group">
                         <textarea id="summernote" name="content"></textarea>
                     </div>
                     <button type="submit">등록</button>
                 </form>
             </div>
         </main>
         <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
         <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
         <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/summernote.min.js"></script>
</form>
</body>
</html>
