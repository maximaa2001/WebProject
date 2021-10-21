let buttons = document.querySelectorAll(".btn_my_profile");
console.log("kdjgkbsx");

/*
выпадающее меню
*/
for (let i = 0; i < buttons.length; i++) {
    buttons[i].addEventListener("mousedown", function () {
        let submenu = document.querySelector(".hidden");
        console.log("ok");
        if (submenu != null) {
            submenu.classList.remove("hidden");
            submenu.classList.add("visible");
        } else {
            let submenu = document.querySelector(".visible");
            submenu.classList.remove("visible");
            submenu.classList.add("hidden");
        }
    })
}

/*
движение пунктов меню при наведении
*/
let listElements = document.querySelectorAll(".submenu_li");
let ID1 = [];
let ID2 = [];
for (let i = 0; i < listElements.length; i++) {
    listElements[i].addEventListener("mouseover",function (){
        clearInterval(ID2[i]);
        let xLeft = 0;
        ID1[i] = setInterval(function () {
            if (xLeft > 10) {
                clearInterval(ID1[i]);
            }else {
                xLeft = xLeft + 2;
                console.log(xLeft)
                listElements[i].style.left = xLeft + "px";
            }
        }, 20)
    })


    listElements[i].addEventListener("mouseout",function (){
        let arr = listElements[i].style.left.split("px");
        let xRight = arr[0];
        clearInterval(ID1[i]);
        ID2[i] = setInterval(function () {
            console.log(xRight)
            if (xRight < 1) {
                clearInterval(ID2[i]);
            }else {
                xRight = xRight - 2;
                listElements[i].style.left = xRight + "px";
            }
        }, 20)
    })
}