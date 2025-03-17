function mobileNav() {
    var navbar = document.querySelector('.container-mobile .navbar');
    if (navbar.style.display === "block") {
        navbar.style.display = "none";
    } else {
        navbar.style.display = "block";
    }
}