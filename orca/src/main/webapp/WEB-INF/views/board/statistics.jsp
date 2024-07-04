<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시물 및 조회수 통계</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        .chart-container {
            width: 80%;
            margin: auto;
            padding: 20px;
        }
    </style>
</head>
<body>
    <div class="chart-container">
        <h2>총 게시물 및 조회수 통계</h2>
       <a href="/orca/board">게시물 목록 돌아가기</a>
        <canvas id="statsChart" width="400" height="200"></canvas>
    </div>

    <script>
        function ajaxStatsByDate() {
            return $.ajax({
                url: '/orca/board/statsByDate',
                method: 'GET',
                dataType: 'json'
            });
        }

        function renderChart(data) {
            const ctx = document.getElementById('statsChart').getContext('2d');
            const labels = data.map(item => item.ENROLL_DATE_STR);
            const postCounts = data.map(item => item.POST_COUNT);
            const viewCounts = data.map(item => item.VIEWS);

            new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [
                        {
                            label: '총 게시물 수',
                            data: postCounts,
                            backgroundColor: 'rgba(255, 99, 132, 0.2)',
                            borderColor: 'rgba(255, 99, 132, 1)',
                            borderWidth: 1
                        },
                        {
                            label: '총 조회수',
                            data: viewCounts,
                            backgroundColor: 'rgba(54, 162, 235, 0.2)',
                            borderColor: 'rgba(54, 162, 235, 1)',
                            borderWidth: 1
                        }
                    ]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        }

        $(document).ready(function() {
            ajaxStatsByDate().done(function(data) {
                renderChart(data);
            });
        });
    </script>
</body>
</html>
