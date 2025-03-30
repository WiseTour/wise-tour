function habilitarNavBarMobile() {
    var x = document.getElementById("header_container_mobile");
    if (x.style.display === "block") {
        x.style.display = "none";
    } else {
        x.style.display = "block";
    }
}