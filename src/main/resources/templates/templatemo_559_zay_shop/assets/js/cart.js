document.addEventListener("DOMContentLoaded", function () {
    const cartButton = document.getElementById("cart-button");
    const cartContainer = document.getElementById("cart-container");

    cartButton.addEventListener("click", function (event) {
        event.preventDefault();
        toggleCart();
    });

    function toggleCart() {
        if (cartContainer.style.display === "none") {
            cartContainer.style.display = "block";
        } else {
            cartContainer.style.display = "none";
        }
    }
});



