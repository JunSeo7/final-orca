<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사원 근무 정보</title>
    <style>
        /* Your existing CSS */
        .container {
            width: 80%;
            max-width: 1200px;
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            padding: 20px;
        }
        .container h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }
        .search-bar {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }
        .search-bar input {
            width: 22%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        .search-bar button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .search-bar button:hover {
            background-color: #45a049;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
            color: #333;
        }
        tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tbody tr:hover {
            background-color: #f1f1f1;
        }
        .pagination {
            text-align: center;
            margin-top: 20px;
        }
        button.pagination-button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            margin: 5px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 12px;
            transition: background-color 0.3s;
        }

        /* 버튼에 마우스 올렸을 때 */
        button.pagination-button:hover {
            background-color: #0056b3;
        }

        /* 현재 페이지 버튼 스타일 */
        button.pagination-button.current-page {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>사원 근무 정보</h1>
        <div>
            <input type="text" id="name" placeholder="이름">
            <input type="text" id="partName" placeholder="부서이름">
            <input type="date" id="startDate">
            <input type="date" id="endDate">
            <button id="searchButton">검색</button>
        </div>
        <div id="content">
            <table id="allWorkInfoTable">
                <thead>
                    <tr>
                        <th>사번</th>
                        <th>일자</th>
                        <th>이름</th>
                        <th>부서</th>
                        <th>직급</th>
                        <th>출근</th>
                        <th>퇴근</th>
                        <th>휴일근무</th>
                        <th>연장근무</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <div class="pagination" id="pagination"></div>
        </div>
    </div>
</body>
</html>

