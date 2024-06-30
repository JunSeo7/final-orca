<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시물 및 조회수 통계</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
        <h2>게시물 및 조회수 통계</h2>
       <a href="/board">게시물 목록 돌아가기</a>
        <canvas id="statsChart" width="400" height="200"></canvas>
    </div>

    <script>
        async function fetchStatsByDate() {
            const response = await fetch('/board/statsByDate');
            return response.json();
        }

        function renderChart(data) {
            const ctx = document.getElementById('statsChart').getContext('2d');
            const labels = data.map(item => item.ENROLL_DATE_STR);  // 날짜를 라벨로 사용
            const postCounts = data.map(item => item.POST_COUNT);  // 게시물 수 데이터
            const viewCounts = data.map(item => item.VIEWS);  // 조회수 데이터

            new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [
                        {
                            label: '게시물 수',
                            data: postCounts,
                            backgroundColor: 'rgba(255, 99, 132, 0.2)',
                            borderColor: 'rgba(255, 99, 132, 1)',
                            borderWidth: 1
                        },
                        {
                            label: '조회수',
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

        document.addEventListener('DOMContentLoaded', () => {
            fetchStatsByDate().then(data => {
                renderChart(data);
            });
        });
    </script>
</body>
</html>
