<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <div class="container">
        <div class="search-container">
            <form action="/search" method="GET">
                <select id="searchCategory" name="category">
                    <option value="name">이름</option>
                    <option value="empNo">사번</option>
                    <option value="department">부서</option>
                </select>
                <input type="text" id="searchInput" name="keyword" placeholder="검색어 입력">
                <input type="submit" value="검색">
            </form>
        </div>
        <div class="content-container">
            <div class="employee-container">
                
            </div>
            <div class="pageFooter">
                <div class="totalPage"></div>
            </div>
        </div>
    </div>
    <style>
        .search-container {
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.166);
            border-radius: 3px;
            padding-top: 2.5px;
            padding-bottom: 2.5px;
            margin-bottom: 25px;
        }

        .container select,
        .container input[type="text"],
        .container input[type="submit"] {
            margin: 10px 0;
            padding: 4px;
            box-sizing: border-box;
            font-size: 14px;
            font-weight: 900;
        }

        .container input:focus,
        .container select:focus {
            border-color: #007bff;
            outline: none;
        }

        .search-container input[type="text"]::placeholder {
            color: #b9b6b6;
        }

        .container select {
            border: none;
            padding-right: 30px;
            padding-left: 15px;
            width: 10%;
        }

        .container input[type="text"] {
            width: 81.5%;
            border: none;
            border-left: 2px solid rgb(188, 186, 186);
            padding-left: 18px;
        }

        .container input[type="submit"] {
            background-color: #6eadff;
            border: none;
            cursor: pointer;
            width: 6.5%;
            border-radius: 3px;
        }

        .container input[type="submit"]:hover {
            background-color: #579af2;
        }

        .content-container {
            background-color: rgb(255, 255, 255);
            padding: 30px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.171);
            width: 950px;
            height: 470px;
            margin-top: 20px;
            border-radius: 3px;
            padding-bottom: 10px;
        }

        .employee-info {
            margin-bottom: 7px;
            display: flex;
            width: 100%;
            border: 2.5px solid rgba(169, 165, 165, 0.804);
            gap: 13px;
            padding: 8px;
            border-radius: 3px;
            align-items: center;
            font-weight: 900;
            color: rgb(82, 81, 81);
        }

        .employee-info:hover {
            border-color: #007bff;
            cursor: pointer;
        }

        .employee-profile {
            font-weight: 100;
            color: rgb(200, 198, 198);
            width: 33px;
            height: 33px;
            object-fit: cover;
            border: 1px solid rgb(195, 193, 193);
            border-radius: 40px;
            margin-right: 16px;
        }

        .employee-name {
            font-size: 20px;
            color: black;
        }

        .employee-position {
            font-size: 12px;
            color: rgb(82, 81, 81);
        }

        .employee-department {
            font-size: 12px;
            color: rgb(82, 81, 81);
        }

        .pageFooter {
            text-align: center;
            margin: 10px 0;
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

        a{
            text-decoration: none;
            cursor: pointer;
        }
    </style>