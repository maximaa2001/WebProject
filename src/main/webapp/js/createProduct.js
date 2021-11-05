let inputsImage = document.querySelectorAll(".input_image");
let images= document.querySelectorAll(".image");

for (let i = 0; i < inputsImage.length; i++){
    inputsImage[i].addEventListener("change", function () {
        if (this.files[0]) {
            let fr = new FileReader();
            fr.addEventListener("load", function () {
                images[i].src = fr.result + "";
            }, false);

            fr.readAsDataURL(this.files[0]);
        }
    });
}

let selector_date = document.querySelector(".select_date");
let selector_engine = document.querySelector(".select_engine");

for (let i = 2021; i >= 1910; i--){
    let option = document.createElement("option");
    option.innerHTML = String(i);
    option.value = String(i);
    selector_date.append(option);
}

for (let i = 1.0; i <= 9.0; i = i + 0.1){
    let option = document.createElement("option");
    option.innerHTML = String(i.toFixed(1));
    option.value = String(i.toFixed(1));
    console.log(option.value);
    selector_engine.append(option);
}


let run = document.querySelector("#input_2");
let price = document.querySelector("#input_3");
let arr = [];

arr[0] = run;

arr[1] = price;


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