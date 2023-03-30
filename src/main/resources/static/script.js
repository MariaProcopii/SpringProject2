const btn_toggle = document.querySelector('.btn-toggle');
const sidebar = document.querySelector('.sidebar');
const logo = document.querySelector('.logo');
const wrapper = document.querySelector('.wrapper');

btn_toggle.addEventListener("click", ()=>{
    sidebar.classList.toggle("active");
})

