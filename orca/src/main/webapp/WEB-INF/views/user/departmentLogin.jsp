<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ page import="com.groupware.orca.user.vo.UserVo" %>
            <!DOCTYPE html>
            <html lang="ko">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>부서 로그인</title>
                <script defer src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                <script defer src="/js/user/departmentLogin.js"></script>
            </head>

            <body>
                <div class="login-container">
                    <h1>부서 로그인</h1>
                    <form action="departmentLogin" method="post" id="departmentLogin">
                        <div class="form-group">
                            <label for="password">비밀번호:</label>
                            <input type="password" id="password" name="password" required>
                        </div>
                        <button type="submit">로그인</button>
                    </form>
                </div>
            </body>

            </html>

            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f4f4f4;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    height: 100vh;
                    margin: 0;
                }

                .login-container {
                    background-color: #ffffff;
                    padding: 40px;
                    /* 패딩을 늘려서 컨테이너 크기를 키움 */
                    border-radius: 8px;
                    box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
                    width: 400px;
                    /* 컨테이너 너비를 키움 */
                    padding-top: 20px;
                }

                h1 {
                    text-align: center;
                    margin-bottom: 35px;
                    /* 마진을 늘려서 더 넓게 보이게 함 */
                }

                .form-group {
                    margin-bottom: 20px;
                    /* 마진을 늘려서 더 넓게 보이게 함 */
                }

                label {
                    display: block;
                    margin-bottom: 10px;
                    /* 마진을 늘려서 더 넓게 보이게 함 */
                }

                input[type="password"],
                button {
                    width: 100%;
                    /* 버튼과 입력 필드의 너비를 100%로 설정하여 일치시킴 */
                    padding: 15px;
                    /* 패딩을 늘려서 입력 필드와 버튼을 키움 */
                    border: 1px solid #ccc;
                    border-radius: 4px;
                    font-size: 16px;
                    /* 폰트 크기를 키움 */
                    box-sizing: border-box;
                    /* 패딩과 테두리를 포함하여 요소의 크기를 계산 */
                }

                button {
                    background-color: #007bff;
                    color: #fff;
                    font-size: 18px;
                    /* 폰트 크기를 키움 */
                    cursor: pointer;
                    border: none;
                }

                button:hover {
                    background-color: #0056b3;
                }
            </style>