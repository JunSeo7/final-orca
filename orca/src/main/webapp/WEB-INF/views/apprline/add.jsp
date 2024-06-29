<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>결재선 등록</title>
    <link rel="icon" href="/img/logo.png" type="image/png">
    <link rel="stylesheet" href="/css/approvalLine/list.css">
</head>
<body>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<%@ include file="/WEB-INF/views/document/aside.jsp" %>

<main id="content">
    <form id="approvalLineForm" action="/orca/apprline/add" method="post">
        <label for="apprLineName">결재선 이름:</label>
        <input type="text" id="apprLineName" name="apprLineName">

        <label for="categoryNo">카테고리 번호:</label>
        <input type="number" id="categoryNo" name="categoryNo">

        <label for="templateNo">템플릿 번호:</label>
        <input type="number" id="templateNo" name="templateNo">

        <div id="approvers">
            <!-- 결재자 입력 필드들이 추가될 곳 -->
        </div>

        <button type="button" onclick="addApprover()">결재자 추가</button>
        <button type="submit">등록</button>
    </form>
</main>
</body>
</html>

    <script>
        let approverCount = 0;

        function addApprover() {
            const approversDiv = document.getElementById('approvers');
            const approverDiv = document.createElement('div');
            approverDiv.classList.add('approver');

            // HTML을 문자열로 직접 구성
            const html = `
                <div>
                    <label for="approverNo` + approverCount + `">결재자 번호:</label>
                    <input type="number" id="approverNo` + approverCount + `" name="approverVoList[` + approverCount + `].approverNo">

                    <label for="seq` + approverCount + `">순서:</label>
                    <input type="number" id="seq` + approverCount + `" name="approverVoList[` + approverCount + `].seq" value="` + (approverCount + 1) + `" readonly>

                    <label>결재자 분류 번호:</label>
                    <label>
                        <input type="radio" id="approverClassificationNo` + approverCount + `_1" name="approverVoList[` + approverCount + `].approverClassificationNo" value="1"> 결재자
                    </label>
                    <label>
                        <input type="radio" id="approverClassificationNo` + approverCount + `_2" name="approverVoList[` + approverCount + `].approverClassificationNo" value="2"> 합의자
                    </label>
                </div>
            `;

            approverDiv.innerHTML = html;
            approversDiv.appendChild(approverDiv);
            console.log("Added approver with count:", approverCount); // 디버깅 로그 추가
            approverCount++;  // 증가
        }
    </script>