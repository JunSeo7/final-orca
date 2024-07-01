<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시물 작성</title>
    <a href="/board">게시물 목록보기</a>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/froala-editor/css/froala_editor.pkgd.min.css">

    <script src="https://cdn.jsdelivr.net/npm/froala-editor/js/froala_editor.pkgd.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=417c2d6869f3c660f4e0370cf828ba62&libraries=services,places"></script>
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
    <div class="form-container">
        <h1>게시물 작성</h1>
        <form id="postForm" action="/board/insert" method="post" enctype="multipart/form-data">
            <label for="categoryNo">카테고리</label>
            <select id="categoryNo" name="categoryNo" required>
                <option value="1">자유 게시판</option>
                <option value="2">팀 게시판</option>
                <option value="3">익명 게시판</option>
            </select>
            <label for="title">제목</label>
            <input type="text" id="title" name="title" required>

            <label for="content">내용</label>
            <div id="edit"></div>
            <input type="hidden" id="content" name="content">

            <label for="address">주소 검색</label>
            <input type="text" id="address" name="address" placeholder="주소를 입력하세요">
            <button type="button" onclick="searchAddress()">주소 검색</button>

            <input type="hidden" id="isAnonymous" name="isAnonymous">

            <input type="submit" value="제출">

            <div id="map" style="width:100%;height:350px;"></div>
            <input type="hidden" id="latitude" name="latitude">
            <input type="hidden" id="longitude" name="longitude">
        </form>
    </div>

    <script>
        var map;
        var marker;

        $(document).ready(function() {
            var editor = new FroalaEditor('#edit', {
                height: 400, /* 원하는 높이 값으로 설정 */
                width: '100%', /* 원하는 너비 값으로 설정 */
                imageUploadURL: '/board/uploadImage',
                imageUploadParams: function() {
                    return {
                        boardNo: $('#boardNo').val()
                    };
                },
                imageUploadParam: 'file',
                imageUploadMethod: 'POST',
                imageAllowedTypes: ['jpeg', 'jpg', 'png', 'gif'],
                imageMaxSize: 2 * 1024 * 1024
            });

            $('#postForm').on('submit', function() {
                $('#content').val(editor.html.get());

                // 카테고리가 익명 게시판이면 isAnonymous를 'Y'로 설정
                if ($('#categoryNo').val() == '3') {
                    $('#isAnonymous').val('Y');
                } else {
                    $('#isAnonymous').val('N');
                }
            });

            var mapContainer = document.getElementById('map');
            var mapOption = {
                center: new kakao.maps.LatLng(37.5665, 126.9780), // 서울 중심 좌표
                level: 3
            };

            map = new kakao.maps.Map(mapContainer, mapOption);
            marker = new kakao.maps.Marker({
                position: map.getCenter()
            });
            marker.setMap(map);

            kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
                var latlng = mouseEvent.latLng;
                marker.setPosition(latlng);
                $('#latitude').val(latlng.getLat());
                $('#longitude').val(latlng.getLng());
            });
        });

        function searchAddress() {
            new daum.Postcode({
                oncomplete: function(data) {
                    var addr = data.address; // 최종 주소 변수
                    document.getElementById("address").value = addr;

                    // 주소로 좌표를 검색합니다
                    var geocoder = new kakao.maps.services.Geocoder();
                    geocoder.addressSearch(data.address, function(results, status) {
                        if (status === kakao.maps.services.Status.OK) {
                            var result = results[0]; // 첫 번째 결과의 값을 활용합니다
                            var coords = new kakao.maps.LatLng(result.y, result.x);

                            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                            map.setCenter(coords);

                            // 마커를 결과값으로 받은 위치로 옮깁니다
                            marker.setPosition(coords);
                            $('#latitude').val(result.y);
                            $('#longitude').val(result.x);
                        }
                    });
                }
            }).open();
        }
    </script>
</body>
</html>
