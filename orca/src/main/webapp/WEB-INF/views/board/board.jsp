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
    <style>
        .comment {
            border-bottom: 1px solid #ddd;
            padding: 10px;
        }
        .comment.reply {
            margin-left: 20px;
            border-left: 2px solid #ddd;
            padding-left: 10px;
        }
        .comment-container {
            display: flex;
            flex-direction: column;
        }
        .reply-container {
            margin-left: 20px;
            border-left: 2px solid #ddd;
            padding-left: 10px;
        }
        .bookmark-button {
            cursor: pointer;
            color: #ffbb33;
        }
        .bookmarked {
            color: #ffbb33;
        }
    </style>
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
                <li><a href="/orca/board/statistics">통계</a></li>
                <li><a href="#" onclick="loadBookmarks()">북마크 목록</a></li>
            </ul>
        </nav>
    </aside>
    <main id="content">
        <h2>게시판 목록</h2>
        <select id="categorySelect">
            <option value="1">자유 게시판</option>
            <option value="2">팀 게시판</option>
            <option value="3">익명 게시판</option>
            <option value="bookmark">북마크</option>
        </select>
        <input type="text" id="searchTitle" placeholder="제목으로 검색">
        <button id="searchBtn">검색</button>
        <a href="/orca/board/insert">게시물 작성하기</a>
        <a href="/orca/board/statistics">게시물 통계보기</a>
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
                <i class="far fa-bookmark bookmark-button" data-board-no="" onclick="toggleBookmark(this)"></i>
            </div>
            <div class="post-likes">
                <span id="like-count">0</span> 좋아요
            </div>
            <div id="hit-container">조회수: <span id="hit"></span></div>
            <hr>
            <div id="modal-content"></div>
            <div id="comments-container" class="comment-container"></div>
            <textarea id="new-comment-content" placeholder="댓글을 입력하세요"></textarea>
            <button onclick="addComment()">댓글 작성</button>
            <div id="map"></div>
            <button id="btn-kakao" class="kakao-share-button">💬</button>
        </div>
    </div>
    <script type="module">
        import { initializeApp } from "https://www.gstatic.com/firebasejs/10.12.2/firebase-app.js";
        import { getAnalytics } from "https://www.gstatic.com/firebasejs/10.12.2/firebase-analytics.js";
        import { getAuth, onAuthStateChanged, signInWithEmailAndPassword } from "https://www.gstatic.com/firebasejs/10.12.2/firebase-auth.js";
        import { getFirestore, doc, getDoc, setDoc, deleteDoc } from "https://www.gstatic.com/firebasejs/10.12.2/firebase-firestore.js";

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
        const analytics = getAnalytics(app);
        const auth = getAuth();
        const db = getFirestore(app);

        function checkAuthState() {
            return new Promise((resolve, reject) => {
                onAuthStateChanged(auth, user => {
                    if (user) {
                        resolve(user);
                    } else {
                        reject('로그인 필요');
                    }
                });
            });
       }

        window.toggleLike = function() {
            checkAuthState().then(user => {
                const boardNo = document.getElementById('modal-title').dataset.boardNo;
                const likeRef = doc(db, 'likes', boardNo, 'users', user.uid);

                getDoc(likeRef).then(docSnap => {
                    if (docSnap.exists()) {
                        deleteDoc(likeRef).then(() => {
                            document.getElementById('like-button').classList.remove('liked');
                            updateLikeCount(boardNo, -1);
                        });
                    } else {
                        setDoc(likeRef, { liked: true }).then(() => {
                            document.getElementById('like-button').classList.add('liked');
                            updateLikeCount(boardNo, 1);
                        });
                    }
                });
            }).catch(error => {
                alert(error + " 후 이용해 주세요.");
            });
        }
    </script>
    <script type="text/javascript">
        var map;
        var currentUserNo = '<%= ((UserVo) session.getAttribute("loginUserVo")) != null ? ((UserVo) session.getAttribute("loginUserVo")).getEmpNo() : "" %>';

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
                if (categoryNo === 'bookmark') {
                    loadBookmarks();
                } else {
                    $("#jqGrid").jqGrid('setGridParam', {
                        url: '/orca/board/list/' + categoryNo,
                        page: 1
                    }).trigger('reloadGrid');
                }
            });

            $("#searchBtn").on("click", function () {
                var title = $("#searchTitle").val();
                var categoryNo = $("#categorySelect").val();
                if (isNaN(categoryNo)) {
                    alert("카테고리 번호가 잘못되었습니다.");
                    return;
                }
                $("#jqGrid").jqGrid('setGridParam', {
                    url: '/orca/board/search',
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
                url: '/orca/board/list/' + categoryNo,
                mtype: "GET",
                styleUI: 'jQueryUI',
                datatype: "json",
                colModel: [
                    {label: '게시판 번호', name: 'boardNo', width: 30},
                    {label: '제목', name: 'title', key: true, width: 75, formatter: titleFormatter},
                    {label: '조회수', name: 'hit', width: 50},
                    {label: '썸네일', name: 'content', width: 50, formatter: extractImage}
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
                url: "/orca/board/" + boardNo,
                method: "GET",
                dataType: "json",
                success: function (response) {
                    $('#modal-title').text(response.title);
                    $('#hit').text(response.hit);
                    $('#teamName').text(response.teamName);
                    $('#modal-content').html(response.content ? response.content : '내용이 없습니다.');
                    $('#modal-title').attr('data-board-no', boardNo); // 여기서 attr 사용
                    $('#enrolldate').text(response.enrollDate);
                    $('#insert-name').text(response.employeeName);
                    $('#bookmark-button').attr('data-board-no', boardNo); // 여기서 attr 사용

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
                    checkBookmarkStatus(boardNo);
                },
                error: function () {
                    alert("게시물 상세 정보를 불러오는데 실패했습니다.");
                }
            });
        }

        function getCommentHtml(comment) {
            var isReply = comment.replyCommentNo !== null;
            var commentClass = isReply ? 'comment reply' : 'comment';

            var html = '<div class="' + commentClass + '" data-comment-no="' + comment.boardChatNo + '">';
            var employeeName = comment.employeeName;
            var teamName = comment.teamName;

            if (comment.isAnonymous === "Y") {
                employeeName = '***';
                teamName = '***';
            } else if (!employeeName) {
                employeeName = '알 수 없음';
            }

            html += '<div class="author">작성자: ' + employeeName + '</div>';
            html += '<div class="team">팀: ' + teamName + '</div>';
            html += '<div class="date">' + comment.enrollDate + '</div>';
            html += '<div class="content">' + comment.content + '</div>';

            html += '<div class="actions">';
            html += '<button onclick="editComment(' + comment.boardChatNo + ')">수정</button>';
            html += '<button onclick="deleteComment(' + comment.boardChatNo + ')">삭제</button>';
            html += '<button onclick="replyComment(' + comment.boardChatNo + ')">답글</button>';
            html += '</div>';

            html += '</div>';

            return html;
        }

        function showComments(boardNo) {
            $.ajax({
                url: "/orca/board/comment/list?boardNo=" + boardNo,
                method: "GET",
                dataType: "json",
                success: function (response) {
                    var commentsHtml = '';
                    var commentMap = {};

                    response.forEach(function (comment) {
                        commentMap[comment.boardChatNo] = comment;
                        comment.replies = [];
                    });

                    response.forEach(function (comment) {
                        if (comment.replyCommentNo !== null) {
                            commentMap[comment.replyCommentNo].replies.push(comment);
                        }
                    });

                    response.forEach(function (comment) {
                        if (comment.replyCommentNo === null) {
                            commentsHtml += getCommentHtml(comment);
                            commentsHtml += '<div class="reply-container">';
                            comment.replies.forEach(function (reply) {
                                commentsHtml += getCommentHtml(reply);
                            });
                            commentsHtml += '</div>';
                        }
                    });

                    $('#comments-container').html(commentsHtml);
                },
                error: function () {
                    alert("댓글을 불러오는데 실패했습니다.");
                }
            });
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
                isAnonymous: "N",
                replyCommentNo: null // 부모 댓글이 없음을 나타냄
            };

            var categoryNo = $("#categorySelect").val();
            if (categoryNo == '3') {
                comment.isAnonymous = "Y";
            }

            $.ajax({
                url: "/orca/board/comment/add",
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

        function replyComment(replyCommentNo) {
            var content = prompt("답글 내용을 입력하세요:");
            if (content) {
                var boardNo = $('#modal-title').data('boardNo');
                var comment = {
                    boardNo: boardNo,
                    content: content,
                    isAnonymous: "N",
                    replyCommentNo: replyCommentNo
                };

                var categoryNo = $("#categorySelect").val();
                if (categoryNo == '3') {
                    comment.isAnonymous = "Y";
                }

                $.ajax({
                    url: "/orca/board/comment/add",
                    method: "POST",
                    contentType: "application/json",
                    data: JSON.stringify(comment),
                    success: function () {
                        showComments(boardNo);
                    },
                    error: function () {
                        alert("답글 작성에 실패했습니다.");
                    }
                });
            }
        }

        function editComment(boardChatNo) {
            var currentContent = $('[data-comment-no="' + boardChatNo + '"]').find('div:nth-child(4)').text();
            var newContent = prompt("댓글 내용을 수정하세요:", currentContent);
            if (newContent !== null) {
                $.ajax({
                    url: "/orca/board/comment/edit",
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
                    url: "/orca/board/comment/delete",
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
            window.location.href = '/orca/board/updatePage?boardNo=' + boardNo;
        }

        Kakao.init('417c2d6869f3c660f4e0370cf828ba62');

        function deleteModal() {
            if (confirm("정말로 이 게시물을 삭제하시겠습니까?")) {
                var boardNo = $('#modal-title').data('boardNo');
                $.ajax({
                    url: "/orca/board/" + boardNo,
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
            var linkUrl = 'http://127.0.0.1:8080/orca/board/' + boardNo;
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

        function checkBookmarkStatus(boardNo) {
            $.ajax({
                url: "/orca/bookmark/list",
                method: "GET",
                success: function (response) {
                    $('.bookmark-button').each(function () {
                        const btnBoardNo = $(this).data('boardNo');
                        if (response.some(bookmark => bookmark.boardNo == btnBoardNo)) {
                            $(this).addClass('bookmarked');
                        } else {
                            $(this).removeClass('bookmarked');
                        }
                    });
                },
                error: function () {
                    console.error("북마크 상태를 확인하는데 실패했습니다.");
                }
            });
        }

        function toggleBookmark(element) {
            const boardNo = document.getElementById('modal-title').dataset.boardNo; // dataset 사용
            const isBookmarked = $(element).hasClass('bookmarked');

            if (isBookmarked) {
                $.ajax({
                    url: "/orca/bookmark/deleteByBoardNo/" + boardNo,
                    method: "DELETE",
                    success: function () {
                        $(element).removeClass('bookmarked');
                    },
                    error: function () {
                        alert("북마크 삭제에 실패했습니다.");
                    }
                });
            } else {
                $.ajax({
                    url: "/orca/bookmark/add",
                    method: "POST",
                    contentType: "application/json",
                    data: JSON.stringify({ boardNo: boardNo }),
                    success: function () {
                        $(element).addClass('bookmarked');
                    },
                    error: function () {
                        alert("북마크 추가에 실패했습니다.");
                    }
                });
            }
        }

        function loadBookmarks() {
            $("#jqGrid").jqGrid('setGridParam', {
                url: '/orca/bookmark/list',
                mtype: "GET",
                datatype: "json",
                colModel: [
                    {label: 'No', name: 'boardNo', width: 30},
                    {label: 'Title', name: 'title', key: true, width: 75, formatter: titleFormatter}

                ],
                viewrecords: true,
                width: 1400,
                height: 600,
                rowNum: 50,
                pager: "#jqGridPager"
            }).trigger('reloadGrid');
        }

        function deleteBookmark(bookmarkNo) {
            $.ajax({
                url: "/orca/bookmark/delete/" + bookmarkNo,
                method: "DELETE",
                success: function () {
                    loadBookmarks();
                },
                error: function () {
                    alert("북마크 삭제에 실패했습니다.");
                }
            });
        }
    </script>
</body>
</html>
