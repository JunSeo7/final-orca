<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>결재 작성</title>
    <!-- 파비콘 -->
    <link rel="icon" href="img/logo.png" type="image/png">

    <script defer src="/js/document/write.js"></script>
    <link rel="stylesheet" href="/css/document/write.css">

     <script defer src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

     <!--Bootstrap Icons 라이브러리 연결-->
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.12/themes/default/style.min.css" />
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

     <!--js 라이브러리 연결-->
     <script defer src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.12/jstree.min.js"></script>

     <%-- 썸머노트 라이브러리 연결 --%>
     <link href="/css/template/summernote/summernote-lite.css" rel="stylesheet">
     <script defer src="/js/template/summernote/summernote-lite.js"></script>
     <script defer src="/js/template/summernote/summernote-ko-KR.js"></script>

    <!-- jsTree Checkbox Plugin -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.12/jstree.checkbox.min.js"></script>


</head>
<body>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<%@ include file="/WEB-INF/views/document/aside.jsp" %>

<main id="content">
    <h1>결재 작성</h1>
    <form id="documentForm" method="post" action="/orca/document/write" enctype="multipart/form-data">
        <table class="document-table">
            <tr>
                <th>결재 프로세스</th>
                <td>
                    <select id="categoryNo" name="categoryNo" onChange="fetchTemplatesByCategory(this.value)">
                    <!-- 카테고리 옵션 -->
                    </select>
                </td>
                <th>결재 양식</th>
                <td>
                    <select id="templateNo" name="templateNo">
                        <!-- 결재 양식 옵션 -->
                    </select>
                </td>
            </tr>
            <tr>
                <th>결재선</th>
                <td>

                    <!-- <select id="approver" name="approver" required>
                    디폴트로 양식에 있는 기본결재선이 나오고, 수정버튼을 누르면 나만의 결재선 제목목록을 보여줌
                    </select>-->

                </td>
                 <th>긴급 여부</th>
                    <td>
                        <label><input type="radio" name="urgent" value="Y"> 예</label>
                        <label><input type="radio" name="urgent" value="N" checked> 아니오</label>
                    </td>
                </tr>
                 <tr>
                    <th>결재선 프로세스</th>
                    <td colspan='3'>
                        <div class="approval-process" name="approvalLineVoList">
                            <!-- 결재선 프로세스 -->
                        </div>
                        <input type="button" value="수정">
                    </td>
                </tr>
                <tr>
               <th>공람(참조인)</th>
                   <td colspan="3">
                       <div id="referrerList">
                           <!-- 참조인 목록 -->
                       </div>
                       <button type="button" onclick="openOrganizationModal()">추가</button>
                   </td>
                </tr>
            </table>
            <table class="document-table">
            <tr>
                <th>제목</th>
                <td>
                    <input type="text" name="title" id="title">
                </td>
            </tr>
            <tr>
                <th colspan='2'> 요청 내용 </th>
            </tr>
            <tr>
                <td colspan='2'>
                    <textarea id="summernote" name="content"></textarea>
                </td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td>
                    <input type="file" name="fileList" multiple>
                </td>
            </tr>
            <tr>
                <th>상태</th>
                <td>
                    <select name="status" id="status">
                        <option value="1">임시저장</option>
                        <option value="2">기안</option>
                    </select>
                </td>
            </tr>
        </table>
        <br>
        <br>
        <button type="submit" class="approval-btn">기안</button>
    </form>
</main>




</body>
</html>

<div id="organizationModal" class="modal" hidden>
    <div class="modal-content">
        <span class="close" onclick="closeOrganizationModal()">&times;</span>
        <h2>참조인 추가</h2>
        <div id="jstree"></div>
        <button type="button" onclick="confirmSelection()">확인</button>
    </div>
</div>

