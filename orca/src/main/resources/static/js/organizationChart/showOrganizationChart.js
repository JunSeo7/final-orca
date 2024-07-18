$(document).ready(function () {
    getDepartment();

    // 부서 선택 시 getTeam 호출
    $('#dept').on('change', function () {
        const selectedDeptCode = $(this).val();
        if (selectedDeptCode) {
            getTeam(selectedDeptCode);
        }
    });

    // 조직도 제출 폼 이벤트 핸들러
    $('#organizationChart').on('submit', function (event) {
        console.log("입력됨");
        event.preventDefault();

        const deptCode = $('#dept').val();
        const teamCode = $('#team').val();

        console.log(deptCode);
        console.log(teamCode);

        $.ajax({
            url: $(this).attr('action'),
            method: "get",
            dataType: 'json',
            data: {
                deptCode: deptCode,
                teamCode: teamCode
            },
            success: (response) => {
                console.log(response);
                updateOrgChart(response);
            },
            error: (xhr, status, error) => {
                console.log(error);
            }
        });
    });
});

function getDepartment() {
    $.ajax({
        type: 'get',
        url: '/orca/organizationChart/getDepartment',
        dataType: 'json',
        success: function (response) {
            console.log(response);  // 받아온 데이터를 콘솔에 출력하여 확인

            // 부서 데이터를 추출하여 옵션으로 추가
            const deptSelect = $('#dept');
            response.forEach(dept => {
                const option = $('<option></option>');
                option.val(dept.deptCode);
                option.text(dept.partName);
                deptSelect.append(option);
            });
        },
        error: function (error) {
            console.error('데이터 로드 실패', error);
        }
    });
}

function getTeam(deptCode) {
    $.ajax({
        type: 'get',
        url: '/orca/organizationChart/getTeam',
        dataType: 'json',
        data: {
            deptCode: deptCode
        },
        success: function (response) {
            console.log(response);  // 받아온 데이터를 콘솔에 출력하여 확인

            const teamSelect = $('#team');
            teamSelect.empty();
            response.forEach(team => {
                const option = $('<option></option>');
                option.val(team.teamCode);
                option.text(team.teamName);
                teamSelect.append(option);
            });
        },
        error: function (error) {
            console.error('데이터 로드 실패', error);
        }
    });
}

function updateOrgChart(data) {
    const orgChart = $('#org-chart');
    orgChart.empty();  // 기존의 조직도 내용 제거

    const levels = {
        exaggeration: [],  // 부장
        manager: [],       // 대리
        chief: [],         // 주임
        staff: []          // 사원
    };

    // 직급에 따라 분류
    data.forEach(person => {
        const position = person.nameOfPosition.toLowerCase();  // 직급을 소문자로 변환하여 사용
        if (position.includes('부장')) {
            levels.exaggeration.push(person);
        } else if (position.includes('대리')) {
            levels.manager.push(person);
        } else if (position.includes('주임')) {
            levels.chief.push(person);
        } else if (position.includes('사원')) {
            levels.staff.push(person);
        }
    });

    // 조직도 생성
    for (const [level, persons] of Object.entries(levels)) {
        if (persons.length > 0) {
            const levelDiv = $('<div></div>').addClass('level').addClass(level);
            persons.forEach(person => {
                const box = $('<div></div>').addClass('box');
                const title = $('<div></div>').addClass('title').text(person.nameOfPosition);
                const name = $('<div></div>').addClass('name').text(person.name);
                box.append(title, name);
                levelDiv.append(box);
            });
            orgChart.append(levelDiv);
        }
    }
}
