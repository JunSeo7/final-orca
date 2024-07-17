<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Home</title>
            <!-- 파비콘 -->

            <script defer src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <link rel="stylesheet" href="/css/layout/main.css">
            <script defer src="/js/user/passwordForm.js"></script>
        </head>

        <body>

            <%@ include file="/WEB-INF/views/layout/header.jsp" %>
                <%@ include file="/WEB-INF/views/layout/aside.jsp" %>

                    <main id="content">
                        <div class="pcontainer">
                            <h2>비밀번호 변경</h2>
                            <form id="passwordForm" action="changePassword" method="post">
                                <label for="currentPassword">현재 비밀번호</label>
                                <input type="password" id="currentPassword" name="currentPassword" required>

                                <label for="newPassword">새 비밀번호</label>
                                <input type="password" id="newPassword" name="newPassword" required>

                                <label for="confirmPassword">비밀번호 확인</label>
                                <input type="password" id="confirmPassword" name="confirmPassword" required>

                                <button type="submit">비밀번호 변경</button>
                            </form>
                        </div>
                    </main>
                    <style>
                        #content{
                            display: flex;
                            justify-content: center;
                            align-items: center;
                        }
                        .pcontainer {
                            width: 410px;
                            background-color: #fff;
                            padding: 25px;
                            border-radius: 8px;
                            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                        }

                        .pcontainer form {
                            display: flex;
                            flex-direction: column;
                        }

                        .pcontainer label {
                            margin-bottom: 8px;
                        }

                        input[type="password"] {
                            padding: 8px;
                            margin-bottom: 16px;
                            border: 1px solid #ccc;
                            border-radius: 4px;
                        }

                        .pcontainer button {
                            padding: 10px 20px;
                            background-color: #007bff;
                            color: #fff;
                            border: none;
                            border-radius: 4px;
                            cursor: pointer;
                        }

                        .pcontainer button:hover {
                            background-color: #0056b3;
                        }
                    </style>
        </body>

        </html>