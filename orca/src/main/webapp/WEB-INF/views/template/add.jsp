<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>결재양식 등록</title>

     <!--파비콘-->
     <link rel="icon" href="img/logo.png" type="image/png">

     <script defer src="/js/template/add.js"></script>
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
           <h1>결재양식 등록</h1>
               <form action="/orca/template/add" method="POST" enctype="multipart/form-data">
                  <table class="document-table">
                  <tr>
                    <th>카테고리</th>
                       <td>
                           <select id="category" name="categoryNo" required>
                              <option value="">--선택--</option>
                              <option value="1">경비 및 지출 관리</option>
                              <option value="2">인사 및 근태 관리</option>
                              <option value="3">프로젝트 및 업무 관리</option>
                              <option value="4">교육 및 지원</option>
                              <option value="5">기타</option>
                           </select>
                       </td>
                    </tr>

                    <tr>
                        <th>결재 양식명</th>
                        <td>
                            <input type="text" id="name" name="title" required>
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
                    <button type="submit">등록</button>

               </form>
           </div>
      </main>

</form>
</body>
</html>
