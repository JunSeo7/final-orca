<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Home</title>
            <script defer src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <link rel="stylesheet" href="/css/layout/main.css">
            <script defer src="/js/organizationChart/showOrganizationChart.js"></script>
        </head>

        <body>

            <%@ include file="/WEB-INF/views/layout/header.jsp" %>
                <%@ include file="/WEB-INF/views/layout/aside.jsp" %>

                    <main id="content">
                        <div class="container">
                            <div id="org-chart" class="org-chart"></div> <!-- 조직도 -->
                            <div class="controls">
                                <form action="getOrganizationChartList" id="organizationChart" method="get">
                                    <select id="dept" required>
                                        <option value="">선택하세요.</option>
                                    </select>
                                    <select id="team" required>
                                        <option value="">선택하세요.</option>
                                    </select>
                                    <br>
                                    <input type="submit" value="제출" id="button">
                                </form>
                            </div>
                        </div>
                    </main>

                    <style>
                        #button {
                            padding: 10px;
                            border: none;
                            border-radius: 5px;
                            background-color: #2980b9;
                            color: white;
                            font-size: 0.9em;
                            cursor: pointer;
                            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                            transition: background-color 0.3s, box-shadow 0.3s;
                        }

                        #button:hover {
                            background-color: #1c5984;
                            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
                        }

                        main {
                            margin-top: 90px;
                        }

                        .container {
                            display: flex;
                            flex-direction: row;
                            align-items: flex-start;
                        }

                        .org-chart {
                            display: flex;
                            flex-direction: column;
                            align-items: center;
                            background-color: white;
                            padding: 20px;
                            border-radius: 10px;
                            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
                            min-width: 90%;
                            max-width: max-content;
                            overflow: auto; 
                        }

                        .level {
                            display: flex;
                            justify-content: center;
                            margin: 20px 0;
                            position: relative;
                            width: 100%;
                        }

                        .level::after {
                            content: "";
                            position: absolute;
                            top: 0;
                            left: 50%;
                            height: 20px;
                            border-left: 2px solid #ccc;
                        }

                        .level .box {
                            background-color: #fff;
                            border: 2px solid #e3e3e3;
                            border-radius: 8px;
                            padding: 20px;
                            margin: 0 10px;
                            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                            text-align: center;
                            min-width: 120px;
                            max-width: 200px;
                            transition: box-shadow 0.3s, transform 0.3s;
                        }

                        .level .box:hover {
                            transform: translateY(-5px);
                            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
                        }

                        .level .box::before {
                            content: "";
                            position: absolute;
                            top: -20px;
                            left: 50%;
                            transform: translateX(-50%);
                            border-top: 2px solid #ccc;
                        }

                        .level.manager .box::before {
                            width: calc(100% - 20px);
                        }

                        .level.chief .box::before {
                            width: calc(100% - 40px);
                        }

                        .level.staff .box::before {
                            width: calc(100% - 60px);
                        }

                        .title {
                            font-weight: bold;
                            font-size: 1.2em;
                            margin-bottom: 10px;
                            color: #2c3e50;
                        }

                        .name {
                            font-size: 1em;
                            color: #34495e;
                        }

                        .level.manager .box {
                            border-color: #2980b9;
                        }

                        .level.chief .box {
                            border-color: #8e44ad;
                        }

                        .level.staff .box {
                            border-color: #16a085;
                        }

                        .exaggeration .box {
                            border-color: #e74c3c;
                        }

                        .controls {
                            display: flex;
                            flex-direction: column;
                            margin-left: 20px;
                        }

                        select {
                            padding: 10px;
                            margin-bottom: 10px;
                            border: 1px solid #ccc;
                            border-radius: 5px;
                            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                            font-size: 0.9em;
                        }
                    </style>
        </body>

        </html>