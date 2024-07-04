<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.groupware.orca.user.vo.UserVo" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 목록</title>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.15.5/css/ui.jqgrid.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/board/board.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.15.5/jquery.jqgrid.min.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=417c2d6869f3c660f4e0370cf828ba62&libraries=services,places"></script>
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
    <script defer src="/js/board/board.js"></script>
</head>
<body>
    <header>
        <div class="header-left" onclick="loadPage('home.jsp')">
            <img src="logo.png" alt="Logo" class="logo">
            <h2>ORCA</h2>
        </div>
        <div class="header-right">
            <span>알림</span>
            <span>조직도</span>
            <span>설정</span>
        </div>
    </header>
    <button id="toggleSidebar" onclick="toggleSidebar()">메뉴</button>
    <aside id="sidebar">
        <div class="profile" onclick="toggleProfile()">
            <img src="profile.png" alt="Profile Picture" class="profile-pic">
            <p>SW팀 | <span>양파쿵야</span></p>
        </div>
        <hr>
        <div id="profileDetail" class="profile-detail hidden">
            <p>상태 설정</p>
            <p>상태 메시지</p>
            <p>@멘션 확인하기</p>
            <p>파일 리스트</p>
            <p>직책</p>
            <p>생년월일</p>
            <p>휴대전화</p>
            <p>raji1004@naver.com</p>
            <button onclick="logout()">로그아웃</button>
        </div>
        <nav>
            <ul>
                <li><a href="#" onclick="loadPage('home.jsp')">홈</a></li>
                <li><a href="#" onclick="loadPage('chat.jsp')">채팅</a></li>
                <li><a href="#" onclick="loadPage('calendar.jsp')">캘린더/할일</a></li>
                <li><a href="#" onclick="loadPage('documents.jsp')">문서관리</a></li>
                <li><a href="#" onclick="loadPage('attendance.jsp')">근태</a></li>
                <li><a href="#" onclick="loadPage('vote.jsp')">투표</a></li>
                <li><a href="#" onclick="loadPage('drive.jsp')">드라이브</a></li>
                <li><a href="#" onclick="loadPage('mail.jsp')">메일</a></li>
                <li><a href="#" onclick="loadPage('settings.jsp')">설정</a></li>
                <li><a href="/board/statistics">통계</a></li>
            </ul>
        </nav>
    </aside>
    <main id="content">
        <h2>게시판 목록</h2>
        <select id="categorySelect">
            <option value="1">자유 게시판</option>
            <option value="2">팀 게시판</option>
            <option value="3">익명 게시판</option>
        </select>
        <input type="text" id="searchTitle" placeholder="제목으로 검색">
        <button id="searchBtn">검색</button>
        <a href="/board/insert">게시물 작성하기</a>
        <a href="/board/statistics">게시물 통계보기</a>
        <table id="jqGrid"></table>
        <div id="jqGridPager"></div>
    </main>
    <div class="modal modal-close">
        <div class="modal-content">
            <button class="closeButton" onclick="closeModal()">닫기</button>
            <button class="updateButton" onclick="redirectToUpdatePage()">수정</button>
            <button class="deleteButton" onclick="deleteModal()">삭제</button>
            <h1 id="modal-title"></h1>
            <div id="enrolldate"></div>
            <div id="insert-name"></div>
            <div id="teamName"></div>
            <div class="post-actions">
                <i class="far fa-heart like-button" id="like-button" onclick="toggleLike()"></i>
            </div>
            <div class="post-likes">
                <span id="like-count">0</span> 좋아요
            </div>
            <div id="hit-container">조회수: <span id="hit"></span></div>
            <hr>
            <div id="modal-content"></div>
            <div id="comments-container" class="comments"></div>
            <textarea id="new-comment-content" placeholder="댓글을 입력하세요"></textarea>
            <button onclick="addComment()">댓글 작성</button>
            <div id="map"></div>
            <button id="btn-kakao" class="kakao-share-button">💬</button>
        </div>
    </div>

    <script type="module">
        import { initializeApp } from "https://www.gstatic.com/firebasejs/10.12.2/firebase-app.js";
        import { getFirestore, doc, getDoc, updateDoc, increment } from "https://www.gstatic.com/firebasejs/10.12.2/firebase-firestore.js";

        const firebaseConfig = {
            apiKey: "AIzaSyBBDpdglycOaD-K2xeciSs3e0DvNvgQyGk",
            authDomain: "finalboard-e002b.firebaseapp.com",
            projectId: "finalboard-e002b",
            storageBucket: "finalboard-e002b.appspot.com",
            messagingSenderId: "827563179973",
            appId: "1:827563179973:web:649d2f81301439863cd5ac",
            measurementId: "G-CGL4488CMS"
        };

        const app = initializeApp(firebaseConfig);
        const db = getFirestore(app);

        window.toggleLike = async function() {
            const boardNo = document.getElementById('modal-title').dataset.boardNo;
            const likeRef = doc(db, 'posts', boardNo);

            try {
                const docSnap = await getDoc(likeRef);
                if (docSnap.exists()) {
                    await updateDoc(likeRef, {
                        likes: increment(1)
                    });
                    document.getElementById('like-button').classList.add('liked');
                    updateLikeCount(boardNo, 1);
                } else {
                    console.log("No such document!");
                }
            } catch (error) {
                console.error("Error updating document: ", error);
            }
        }

        window.updateLikeCount = function(boardNo, delta) {
            const likeCountElement = document.getElementById('like-count');
            const currentCount = parseInt(likeCountElement.textContent, 10);
            likeCountElement.textContent = currentCount + delta;
        }

        window.showModal = function(boardNo) {
            $.ajax({
                url: "/board/" + boardNo,
                method: "GET",
                dataType: "json",
                success: function(response) {
                    $('#modal-title').text(response.title).data('boardNo', boardNo);
                    $('#hit').text(response.hit);
                    $('#teamName').text(response.teamName);
                    $('#modal-content').html(response.content ? response.content : '내용이 없습니다.');
                    $('#enrolldate').text(response.enrollDate);
                    $('#insert-name').text(response.employeeName);

                    $('.modal').removeClass('modal-close');
                    const lat = parseFloat(response.latitude);
                    const lng = parseFloat(response.longitude);
                    if (!isNaN(lat) && !isNaN(lng)) {
                        $('#map').show();
                        map.relayout();
                        var moveLatLon = new kakao.maps.LatLng(lat, lng);
                        map.setCenter(moveLatLon);
                        var marker = new kakao.maps.Marker({
                            position: new kakao.maps.LatLng(lat, lng)
                        });
                        marker.setMap(map);
                    } else {
                        $('#map').hide();
                    }
                    showComments(boardNo);
                    checkLikeStatus(boardNo);
                },
                error: function() {
                    alert("게시물 상세 정보를 불러오는데 실패했습니다.");
                }
            });
        }

        window.checkLikeStatus = async function(boardNo) {
            const likeRef = doc(db, 'posts', boardNo);
            try {
                const docSnap = await getDoc(likeRef);
                if (docSnap.exists()) {
                    const likeData = docSnap.data();
                    if (likeData && likeData.likes) {
                        document.getElementById('like-count').textContent = likeData.likes;
                    }
                } else {
                    console.log("No such document!");
                }
            } catch (error) {
                console.error("Error getting document: ", error);
            }
        }
    </script>

    <script type="text/javascript">
        var map;
        var currentUserNo = '<%= ((UserVo) session.getAttribute("loginUserVo")).getEmpNo() %>';

        $(document).ready(function () {
            var mapContainer = document.getElementById('map'),
                mapOption = {
                    center: new kakao.maps.LatLng(33.450701, 126.570667),
                    level: 3
                };
            map = new kakao.maps.Map(mapContainer, mapOption);
            kakao.maps.event.addListener(map, 'click', function (mouseEvent) {
                var latlng = mouseEvent.latLng;
                marker.setPosition(latlng);
                var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, 경도는 ' + latlng.getLng() + ' 입니다';
                alert(message);
            });

            var categoryNo = $("#categorySelect").val();
            loadGrid(categoryNo);

            $("#categorySelect").on("change", function () {
                categoryNo = $(this).val();
                $("#jqGrid").jqGrid('setGridParam', {
                    url: '/board/list/' + categoryNo,
                    page: 1
                }).trigger('reloadGrid');
            });

            $("#searchBtn").on("click", function () {
                var title = $("#searchTitle").val();
                var categoryNo = parseInt($("#categorySelect").val(), 10);
                if (isNaN(categoryNo)) {
                    alert("카테고리 번호가 잘못되었습니다.");
                    return;
                }
                $("#jqGrid").jqGrid('setGridParam', {
                    url: 'board/search',
                    postData: {
                        title: encodeURIComponent(title),
                        categoryNo: categoryNo
                    },
                    page: 1
                }).trigger('reloadGrid');
            });
        });

        function loadGrid(categoryNo) {
            $("#jqGrid").jqGrid({
                url: '/board/list/' + categoryNo,
                mtype: "GET",
                styleUI: 'jQueryUI',
                datatype: "json",
                colModel: [

                     {label: 'Image', name: 'content', width: 50, formatter: extractImage},
                  {label: '조회수 ', name: 'hit', width: 50},
                    {label: '제목', name: 'title', key: true, width: 75, formatter: titleFormatter},
                       {label :'작성시간' , name:'enrollDate',width:30}
                ],
                viewrecords: true,
                width: 1400,
                height: 600,
                rowNum: 50,
                pager: "#jqGridPager"
            });
        }

        function titleFormatter(cellvalue, options, rowObject) {
            return "<a href='javascript:;' onclick='showModal(" + rowObject.boardNo + ")'>" + cellvalue + "</a>";
        }

        function extractImage(cellValue, options, rowObject) {
            var imgTag = $(cellValue).find('img').prop('outerHTML');
            return imgTag ? imgTag : '';
        }

        function showModal(boardNo) {
            $.ajax({
                url: "/board/" + boardNo,
                method: "GET",
                dataType: "json",
                success: function (response) {
                    kakao.maps.load();
                    $('#modal-title').text(response.title);
                    $('#hit').text(response.hit);
                    $('#teamName').text(response.teamName);
                    $('#modal-content').html(response.content ? response.content : '내용이 없습니다.');
                    $('#modal-title').data('boardNo', boardNo);
                    $('#enrolldate').text(response.enrollDate);
                    $('#insert-name').text(response.employeeName);

                    $('.modal').removeClass('modal-close');
                    const lat = parseFloat(response.latitude);
                    const lng = parseFloat(response.longitude);
                    if (!isNaN(lat) && !isNaN(lng)) {
                        $('#map').show();
                        map.relayout();
                        var moveLatLon = new kakao.maps.LatLng(lat, lng);
                        map.setCenter(moveLatLon);
                        var marker = new kakao.maps.Marker({
                            position: new kakao.maps.LatLng(lat, lng)
                        });
                        marker.setMap(map);
                    } else {
                        $('#map').hide();
                    }
                    showComments(boardNo);
                    checkLikeStatus(boardNo);
                },
                error: function () {
                    alert("게시물 상세 정보를 불러오는데 실패했습니다.");
                }
            });
        }

        function showComments(boardNo) {
            $.ajax({
                url: "/board/comment/list?boardNo=" + boardNo,
                method: "GET",
                dataType: "json",
                success: function (response) {
                    var commentsHtml = '';
                    response.forEach(function (comment) {
                        commentsHtml += getCommentHtml(comment);
                    });
                    $('#comments-container').html(commentsHtml);
                },
                error: function () {
                    alert("댓글을 불러오는데 실패했습니다.");
                }
            });
        }

        function getCommentHtml(comment) {
            var html = '<div class="comment" data-comment-no="' + comment.boardChatNo + '">';
            var employeeName = comment.employeeName;
            var teamName = comment.teamName;

            if (comment.isAnonymous === "Y") {
                employeeName = '***';
                teamName = '***';
            } else if (!employeeName) {
                employeeName = '알 수 없음';
            }

            html += '<div>작성자: ' + employeeName + '</div>';
            html += '<div>팀: ' + teamName + '</div>';
            html += '<div>' + comment.enrollDate + '</div>';
            html += '<div>' + comment.content + '</div>';

            html += '<button onclick="editComment(' + comment.boardChatNo + ')">수정</button>';
            html += '<button onclick="deleteComment(' + comment.boardChatNo + ')">삭제</button>';

            html += '</div>';
            return html;
        }

        function addComment() {
            var content = $('#new-comment-content').val();
            if (!content.trim()) {
                alert("댓글 내용을 입력하세요.");
                return;
            }
            var boardNo = $('#modal-title').data('boardNo');
            var comment = {
                boardNo: boardNo,
                content: content,
                isAnonymous: "N" // 기본값 설정
            };

            var categoryNo = $("#categorySelect").val();
            if (categoryNo == '3') {
                comment.isAnonymous = "Y";
            }

            $.ajax({
                url: "/board/comment/add",
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(comment),
                success: function () {
                    $('#new-comment-content').val('');
                    showComments(boardNo);
                },
                error: function () {
                    alert("댓글 작성에 실패했습니다.");
                }
            });
        }

        function editComment(boardChatNo) {
            var currentContent = $('[data-comment-no="' + boardChatNo + '"]').find('div:nth-child(4)').text();
            var newContent = prompt("댓글 내용을 수정하세요:", currentContent);
            if (newContent !== null) {
                $.ajax({
                    url: "/board/comment/edit",
                    method: "POST",
                    contentType: "application/json",
                    data: JSON.stringify({boardChatNo: boardChatNo, content: newContent}),
                    success: function () {
                        showComments($('#modal-title').data('boardNo'));
                    },
                    error: function () {
                        alert("댓글 수정에 실패했습니다.");
                    }
                });
            }
        }

        function deleteComment(boardChatNo) {
            if (confirm("정말로 이 댓글을 삭제하시겠습니까?")) {
                $.ajax({
                    url: "/board/comment/delete",
                    method: "POST",
                    data: {boardChatNo: boardChatNo},
                    success: function () {
                        showComments($('#modal-title').data('boardNo'));
                    },
                    error: function () {
                        alert("댓글 삭제에 실패했습니다.");
                    }
                });
            }
        }

        function closeModal() {
            $('.modal').addClass('modal-close');
        }

        function redirectToUpdatePage() {
            var boardNo = $('#modal-title').data('boardNo');
            window.location.href = '/board/updatePage?boardNo=' + boardNo;
        }

        Kakao.init('417c2d6869f3c660f4e0370cf828ba62');

        function deleteModal() {
            if (confirm("정말로 이 게시물을 삭제하시겠습니까?")) {
                var boardNo = $('#modal-title').data('boardNo');
                $.ajax({
                    url: "/board/" + boardNo,
                    method: "DELETE",
                    success: function () {
                        closeModal();
                        $("#jqGrid").trigger("reloadGrid");
                    },
                    error: function () {
                        alert("게시물 삭제에 실패했습니다.");
                    }
                });
            }
        }

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
