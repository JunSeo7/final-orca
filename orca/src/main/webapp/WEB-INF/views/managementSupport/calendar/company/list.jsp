<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <div class="container">
        <h2>사내 캘린더 조회</h2>
        <table class="custom-table">
            <thead>
                <tr>
                    <th>제목</th>
                    <th>내용</th>
                    <th>작성자</th>
                    <th>시작일</th>
                    <th>종료일</th>
                    <th>등록일</th>
                </tr>
            </thead>
            <tbody>
                <!-- 여기에 데이터를 추가하세요 -->
            </tbody>
        </table>
        <div class="pageFooter">
            <div class="totalPage"></div>
        </div>
    </div>
    <style>
        .pageFooter {
            text-align: center;
            margin: 15px 0;
        }

        .pageFooter a {
            display: inline-block;
            margin: 0 3px;
            padding: 3px 10px;
            color: black;
            border: 1px solid #ddd;
            border-radius: 4px;
            transition: background-color 0.3s, color 0.3s;
            line-height: 1.5;
            /* 높이 동일하게 */
        }

        .pageFooter a:hover {
            background-color: #f1f1f1;
            color: #0056b3;
        }

        .pageFooter a.current-page {
            background-color: #007bff;
            color: white;
            border-color: #007bff;
            font-weight: bold;
        }

        .pageFooter a.disabled {
            pointer-events: none;
            color: #aaa;
            border-color: #ddd;
        }

        .container h2 {
            display: flex;
            justify-content: center;
            margin-top: 0px;
            margin-bottom: 20px;
        }

        .container {
            width: 1100px;
            height: 570px;
            margin: 0 auto;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 25px;
            padding-bottom: 10px;
            border-radius: 10px;
            margin-bottom: 25px;
        }
        .custom-table {
            width: 100%;
            border-collapse: collapse;
        }
        
        .custom-table th,
        .custom-table td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
            height: 20px;
        }
        
        /* 각 열에 대한 고정 너비 지정 */
        .custom-table th:nth-child(1),
        .custom-table td:nth-child(1) {
            width: 180px; /* 제목의 너비 */
        }
        
        .custom-table th:nth-child(2),
        .custom-table td:nth-child(2) {
            width: 250px; /* 내용의 너비 */
        }
        
        .custom-table th:nth-child(3),
        .custom-table td:nth-child(3) {
            width: 60px; /* 작성자의 너비 */
        }
        
        .custom-table th:nth-child(4),
        .custom-table td:nth-child(4) {
            width: 100px; /* 시작일의 너비 */
        }
        
        .custom-table th:nth-child(5),
        .custom-table td:nth-child(5) {
            width: 100px; /* 종료일의 너비 */
        }
        
        .custom-table th:nth-child(6),
        .custom-table td:nth-child(6) {
            width: 100px; /* 등록일의 너비 */
        }
        
        .custom-table th {
            background-color: #f2f2f2;
        }
    </style>