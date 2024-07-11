document.addEventListener("DOMContentLoaded", function() {
    const docElements = document.querySelectorAll(".document");

    docElements.forEach(element => {
        element.addEventListener("click", function() {
            const docNo = this.getAttribute("data-doc-no");
            window.location.href = `/orca/document/detail?docNo=${docNo}`;
        });
    });
});