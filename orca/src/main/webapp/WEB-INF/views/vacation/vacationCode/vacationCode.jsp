<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>휴가 관리</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #6eadff;
        }
        .container {
            width: 80%;
            margin: auto;
            padding-top: 50px;
        }
        .button-container {
            text-align: right;
            margin-bottom: 20px;
        }
        .button {
            background-color: #6eadff;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border: none;
            border-radius: 4px;
        }
        .button.edit {
            background-color: #6eadff;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>휴가 관리</h2>
        <div class="button-container">
            <button class="button" id="addVacationCodeBtn">휴가 등록</button>
            <button class="button delete" id="deleteVacationCodeBtn">휴가 삭제</button>
        </div>
        <table id="vacationCodesTable">
            <thead>
                <tr>
                    <th><input type="checkbox" id="checkAll"></th>
                    <th>휴가 코드</th>
                    <th>휴가 이름</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</body>
</html>
