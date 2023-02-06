
//Easyier
const minus = getQuerySelector("input.minus");
const plus = getQuerySelector("input.plus");

plus.addEventListener(()=>{
    increaseQuantity();
});

minus.addEventListener(()=>{
    decreaseQuantity();
});

const output = document.querySelector("input[title=Qty]");

const increaseQuantity = () => {
    changeQty(1);   
}
const decreaseQuantity = () => {
    changeQty(-1);   
}

const changeQty = (num) => {
       let current = output.value;
       if(current >= 1){
           current += num;
       }
       output.value = current;
}


