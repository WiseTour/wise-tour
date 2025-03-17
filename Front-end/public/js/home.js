window.addEventListener("scroll", function () {
    const header = document.getElementById("header");
    if (window.scrollY > 50) {
        header.style.backgroundColor = "#F8CA26";
    } else {
        header.style.backgroundColor = "transparent";
    }
});

function mobileNav() {
    var x = document.getElementById("header-container-mobile");
    if (x.style.display === "block") {
        x.style.display = "none";
    } else {
        x.style.display = "block";
    }
}