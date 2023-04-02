const items = [
    { name: "Producto 1", price: 25 },
    { name: "Producto 2", price: 50 },
    { name: "Producto 3", price: 75 }
];

const itemList = document.querySelector(".item-list");
const totalPrice = document.querySelector(".total-price");

function displayItems() {
    let total = 0;
    items.forEach(item => {
        const li = document.createElement("li");
        li.innerHTML = `<span class="item-name">${item.name}</span><span class="item-price">$${item.price}</span>`;
        itemList.appendChild(li);
        total += item.price;
    });

    totalPrice.textContent = `$${total}`;
}
function toggleCart() {
  const cartSection = document.getElementById("shopping-cart-section");

  if (cartSection.style.display === "none" || !cartSection.style.display) {
    cartSection.style.display = "block";
  } else {
    cartSection.style.display = "none";
  }
}

displayItems();
document.getElementById("cart-button").addEventListener("click", function(event) {
  event.preventDefault();
  toggleCart();
});

