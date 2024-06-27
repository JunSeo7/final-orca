window.onload = function () {
    const text1 = "ORCA GROUPWARE";
    const i1 = 0;
    const container1 = document.getElementById("text1");

    typeWriter(text1, i1, container1);

    setTimeout(function () {
        const text2 = "'협력으로 만드는 내일의 기쁨'";
        const i2 = 0;
        const container2 = document.getElementById("text2");
        typeWriter(text2, i2, container2);
    }, 1500);

    setTimeout(function () {
        const text3 = "지금 바로 시작해보세요.";
        const i3 = 0;
        const container3 = document.getElementById("text3");
        typeWriter(text3, i3, container3);
    }, 3000);
    setTimeout(function () {
        const hoverText = document.querySelector(".login-text");
        hoverText.classList.add("login-text-hover");
    }, 3500);
};
function typeWriter(text, i, container) {
    if (i < text.length) {
        container.innerHTML += text.charAt(i);
        i++;
        setTimeout(function () {
            typeWriter(text, i, container);
        }, 60); // 100밀리초마다 한 글자씩 출력
    }
}
