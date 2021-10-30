let selector_year_from = document.querySelector(".select_year_from");
let selector_year_to = document.querySelector(".select_year_to");

/*
---------------------------------------------------------------
        Заполнение селекторов для дат
 */


for (let i = 2022; i >= 1910; i--){
    let option1 = document.createElement("option");
    let option2 = document.createElement("option");
    if(i === 2022){
        option1.textContent = "Any";
        option2.textContent = "Any";

        option1.value = "Any";
        option2.value = "Any";
    }else {
        option1.classList.add("option_from_year");
        option2.classList.add("option_to_year");

        option1.innerHTML = String(i);
        option2.innerHTML = String(i);

        option1.value = String(i);
        option2.value = String(i);
    }

    selector_year_from.append(option1);
    selector_year_to.append(option2);
}
/*
-----------------------------------------------------------
 */

let selector_engine_from = document.querySelector(".select_engine_from");
let selector_engine_to = document.querySelector(".select_engine_to");

/*
        Заполнение селекторов для объема двигателя
 */
for (let i = 0.9; i <= 9.0; i = i + 0.1){
    let option1 = document.createElement("option");
    let option2 = document.createElement("option");

    if(i === 0.9){
        option1.innerHTML = "Any";
        option2.innerHTML = "Any";

        option1.value = "Any";
        option2.value = "Any";
    }else {
        option1.classList.add("option_from_engine");
        option2.classList.add("option_to_engine");

        option1.innerHTML = String(i.toFixed(1));
        option2.innerHTML = String(i.toFixed(1));

        option1.value = String(i.toFixed(1));
        option2.value = String(i.toFixed(1));
    }

    selector_engine_from.append(option1);
    selector_engine_to.append(option2);

}

/*
--------------------------------------------------------
 */

/*
        Функционал после выбора даты
 */

let opinions_from = document.querySelectorAll(".option_from_year");
let opinions_to = document.querySelectorAll(".option_to_year");

let label_from_year = document.querySelector(".label_from_year");
let label_to_year = document.querySelector(".label_to_year");



selector_year_from.addEventListener("change",function (){
    let value = this.value;
    label_from_year.style.opacity = "1";
    for (let i = 0; i < opinions_to.length; i++){
        opinions_to[i].value < value ? opinions_to[i].disabled = true : opinions_to[i].disabled = false;
    }
})

selector_year_to.addEventListener("change",function (){
    let value = this.value;
    label_to_year.style.opacity = "1";
    for (let i = 0; i < opinions_from.length; i++){
        opinions_from[i].value > value ? opinions_from[i].disabled = true :  opinions_from[i].disabled = false;
    }
})

/*
-------------------------------------------------------
 */

/*
        Функционал после выбора объема двигателя
 */

let opinions_from_engine = document.querySelectorAll(".option_from_engine");
let opinions_to_engine = document.querySelectorAll(".option_to_engine");

let label_from_engine = document.querySelector(".label_from_engine");
let label_to_engine = document.querySelector(".label_to_engine");

selector_engine_from.addEventListener("change",function (){
    let value = this.value;
    label_from_engine.style.opacity = "1";
    for (let i = 0; i < opinions_to_engine.length; i++){
        opinions_to_engine[i].value < value ? opinions_to_engine[i].disabled = true : opinions_to_engine[i].disabled = false;
    }
})

selector_engine_to.addEventListener("change",function (){
    let value = this.value;
    label_to_engine.style.opacity = "1";
    for (let i = 0; i < opinions_from_engine.length; i++){
        opinions_from_engine[i].value > value ? opinions_from_engine[i].disabled = true : opinions_from_engine[i].disabled = false;
    }
})

/*
---------------------------------------------------------------
 */


/*
        Ограничение на ввод любых символов, кроме цифр
 */
let price = document.querySelectorAll(".price");
let run = document.querySelector("#input_run");
let arr = [];

for (let i = 0; i < price.length; i++){
    arr[i] = price[i];
}
arr[arr.length] = run;


for (let i = 0; i < arr.length; i++) {
    arr[i].addEventListener("input", function () {
        let str = arr[i].value;
        let symbol = str.substring(str.length - 1);
        if (symbol.match("[0-9]") == null || str.length === 7) {
            let newStr = arr[i].value.substring(0, arr[i].value.length - 1);
            arr[i].value = newStr;
        }
    })
}

/*
---------------------------------------------------------------------
 */

/*
            Обработка нажатий на типы трансмиссиии
 */
let input_mechanic = document.querySelector("#input_mechanic");
let label_mechanic = document.querySelector("#label_mechanic");

let input_automat = document.querySelector("#input_automat");
let label_automat = document.querySelector("#label_automat");
let isMechanic = false;
let isAutomat = false;
input_mechanic.addEventListener("click", function (){
    if(!isMechanic){
        isMechanic = true;
        isAutomat = false;
        label_mechanic.style.backgroundColor = "#696969";
        label_automat.style.backgroundColor = "#242582";
    }else {
        isMechanic = false;
        label_mechanic.style.backgroundColor = "#242582";
        input_mechanic.checked = false;
    }
})

input_automat.addEventListener("click", function (){
    if(!isAutomat){
        isAutomat = true;
        isMechanic = false;
        label_automat.style.backgroundColor = "#696969";
        label_mechanic.style.backgroundColor = "#242582";
    }else {
        isAutomat = false;
        label_automat.style.backgroundColor = "#242582";
        input_automat.checked = false;
    }
})

/*
-----------------------------------------------------------------------
 */

/*
         Сброс всех параметров
 */

let btn_drop = document.querySelector("#drop");

btn_drop.addEventListener("click",function (){
    let year_from = document.querySelector(".select_year_from"); // year from
    year_from.value = document.querySelector("#start").value;
    label_from_year.style.opacity = "0";


    let year_to = document.querySelector(".select_year_to");  // year to
    year_to.value = document.querySelector("#end").value;
    label_to_year.style.opacity = "0";

    for(let i = 0; i < price.length; i++){   // price from to
        price[i].value = "";
    }

    let engine_from = document.querySelector(".select_engine_from"); // engine from
    engine_from.value = document.querySelector("#start_engine").value;
    label_from_engine.style.opacity = "0";

    let engine_to = document.querySelector(".select_engine_to"); // engine to
    engine_to.value = document.querySelector("#end_engine").value;
    label_to_engine.style.opacity = "0";

    isMechanic = false;
    isAutomat = false;

    let type_transmissions = document.querySelectorAll(".input_type_tr");  // type transmission

    for (let i = 0; i < type_transmissions.length; i++){
        type_transmissions[i].checked = false;
        label_mechanic.style.backgroundColor = "#242582";
        label_automat.style.backgroundColor = "#242582";
    }

    run.value = "";  // run

    document.querySelector("#input_name").value = ""; //  name
})
