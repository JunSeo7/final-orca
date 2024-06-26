<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 목록</title>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.15.5/css/ui.jqgrid.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/board.css">

    <style>
        .kakao-share-button {
            width: 50px;
            height: 50px;
            background-color: #FFEB00;
            border: none;
            border-radius: 50%;
            background-image: url('/images/kakao-logo.png');
            background-size: cover;
            background-position: center;
            cursor: pointer;
            display: inline-block;
            margin: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            transition: transform 0.3s;
        }

        .kakao-share-button:hover {
            transform: scale(1.1);
        }

        #map {
            width: 100%;
            height: 400px;
        }
    </style>
</head>
<body>
    <main id="content">
        <h2>게시판 목록</h2>
        <select id="categorySelect">
            <option value="1">자유 게시판</option>
            <option value="2">팀 게시판</option>
            <option value="3">익명 게시판</option>
        </select>
        <input type="text" id="searchKeyword" placeholder="검색어 입력">
        <button id="searchBtn">검색</button>
        <table id="jqGrid"></table>
        <div id="jqGridPager"></div>
    </main>
    <div class="modal modal-close">
        <div class="modal-content">
            <button class="closeButton" onclick="closeModal()">닫기</button>
            <button class="updateButton" onclick="redirectToUpdatePage()">수정</button>
            <button class="deleteButton" onclick="deleteModal()">삭제</button>
            <h1 id="modal-title"></h1>
            <div id="hit-container">조회수: <span id="hit"></span></div>
            <hr>
            <div id="modal-content">
                <div id="map"></div>
            </div>
            <button id="btn-kakao" class="kakao-share-button">💬</button>
        </div>
    </div>

    <!-- jQuery 및 기타 스크립트 -->
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.15.5/jquery.jqgrid.min.js"></script>
    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <script src="http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=417c2d6869f3c660f4e0370cf828ba62&libraries=services"></script>

    <script type="text/javascript">
        function loadKakaoMapScript() {
            return new Promise((resolve, reject) => {
                if (window.kakao && kakao.maps) {
                    resolve();
                    return;
                }

                const script = document.createElement('script');
                script.src = 'https://dapi.kakao.com/v2/maps/sdk.js?appkey=417c2d6869f3c660f4e0370cf828ba62&libraries=services';
                script.onload = () => {
                    kakao.maps.load(() => {
                        resolve();
                    });
                };
                script.onerror = reject;
                document.head.appendChild(script);
            });
        }

        function initMap(lat, lng) {
            if (!kakao || !kakao.maps) {
                console.error("Kakao Maps API가 로드되지 않았습니다.");
                return;
            }
            var mapContainer = document.getElementById('map');
            var mapOption = {
                center: new kakao.maps.LatLng(lat, lng),
                level: 3
            };
            var map = new kakao.maps.Map(mapContainer, mapOption);

            var marker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(lat, lng)
            });
            marker.setMap(map);

            kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
                var latlng = mouseEvent.latLng;
                marker.setPosition(latlng);
                var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, 경도는 ' + latlng.getLng() + ' 입니다';
                alert(message);
            });
        }

        function showModal(boardNo) {
            $.ajax({
                url: "/board/" + boardNo,
                method: "GET",
                dataType: "json",
                success: function(response) {
                    console.log("AJAX 요청 성공:", response);
                    $('#modal-title').text(response.title);
                    $('#hit').text(response.hit);
                    $('#modal-content').html(response.content ? response.content : '내용이 없습니다.');
                    $('#modal-title').data('boardNo', boardNo);
                    $('.modal').removeClass('modal-close');

                    const lat = parseFloat(response.latitude);
                    const lng = parseFloat(response.longitude);

                    console.log("Latitude 값:", response.latitude);
                    console.log("Longitude 값:", response.longitude);
                    console.log("변환된 Latitude 값:", lat);
                    console.log("변환된 Longitude 값:", lng);

                    if (!isNaN(lat) && !isNaN(lng)) {
                        loadKakaoMapScript().then(() => {
                            initMap(lat, lng);
                        }).catch(error => {
                            console.error("카카오 맵 스크립트를 로드하는데 실패했습니다:", error);
                        });
                    } else {
                        console.error("잘못된 지도 좌표 값입니다.");
                    }
                },
                error: function() {
                    alert("게시물 상세 정보를 불러오는데 실패했습니다.");
                }
            });
        }

        function closeModal() {
            $('.modal').addClass("modal-close");
        }

        function redirectToUpdatePage() {
            var boardNo = $('#modal-title').data('boardNo');
            window.location.href = "/board/updatePage?boardNo=" + boardNo;
        }

        function deleteModal() {
            var boardNo = $('#modal-title').data('boardNo');
            if (confirm("정말로 삭제하시겠습니까?")) {
                $.ajax({
                    url: "/board/delete/" + boardNo,
                    method: "POST",
                    success: function(response) {
                        alert(response);
                        closeModal();
                        $("#jqGrid").trigger('reloadGrid');
                    },
                    error: function() {
                        alert("게시물을 삭제하는데 실패했습니다.");
                    }
                });
            }
        }

        $(document).ready(function() {
            function loadGrid(categoryNo) {
                $("#jqGrid").jqGrid({
                    url: '/board/list/' + categoryNo,
                    mtype: "GET",
                    styleUI: 'jQueryUI',
                    datatype: "json",
                    colModel: [
                        { label: 'No', name: 'boardNo', width: 30 },
                        { label: 'Title', name: 'title', key: true, width: 75, formatter: titleFormatter },
                        { label: 'Views', name: 'hit', width: 50 }
                    ],
                    viewrecords: true,
                    width: 900,
                    height: 300,
                    rowNum: 20,
                    pager: "#jqGridPager"
                });
            }

            var categoryNo = $("#categorySelect").val();
            loadGrid(categoryNo);

            $("#categorySelect").on("change", function() {
                categoryNo = $(this).val();
                $("#jqGrid").jqGrid('setGridParam', {
                    url: '/board/list/' + categoryNo,
                    page: 1
                }).trigger('reloadGrid');
            });

            $("#searchBtn").on("click", function() {
                var keyword = $("#searchKeyword").val();
                $("#jqGrid").jqGrid('setGridParam', {
                    url: '/board/search',
                    postData: { keyword: keyword, categoryNo: categoryNo },
                    page: 1
                }).trigger('reloadGrid');
            });
        });

        function titleFormatter(cellvalue, options, rowObject) {
            return "<a href='javascript:;' onclick='showModal(" + rowObject.boardNo + ")'>" + cellvalue + "</a>";
        }

        Kakao.init('417c2d6869f3c660f4e0370cf828ba62');

        document.getElementById('btn-kakao').addEventListener('click', function() {
            var boardNo = $('#modal-title').data('boardNo');
            var title = $('#modal-title').text();
            var description = $('#modal-content').text().substring(0, 100);
            var linkUrl = 'http://127.0.0.1:8080/board/' + boardNo;
            var imageUrl = 'https://via.placeholder.com/300';

            Kakao.Link.sendDefault({
                objectType: 'feed',
                content: {
                    title: title,
                    description: description,
                    imageUrl: imageUrl,
                    link: {
                        mobileWebUrl: linkUrl,
                        webUrl: linkUrl
                    }
                },
                buttons: [
                    {
                        title: '웹으로 보기',
                        link: {
                            mobileWebUrl: linkUrl,
                            webUrl: linkUrl
                        }
                    }
                ]
            });
        });
    </script>
</body>
</html>
