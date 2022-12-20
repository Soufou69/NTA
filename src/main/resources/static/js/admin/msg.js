openCard= (event) =>{
    console.log(event.currentTarget.parentNode.getElementsByClassName("card-content")[0]);
    event.currentTarget.parentNode.getElementsByClassName("card-content")[0].classList.toggle("is-hidden");
    event.currentTarget.parentNode.getElementsByClassName("card-content")[0].classList.toggle("is-activeCard");

    event.currentTarget.parentNode.getElementsByClassName("card-footer")[0].classList.toggle("is-hidden");
    event.currentTarget.parentNode.getElementsByClassName("card-footer")[0].classList.toggle("is-activeCard");
    
}

window.onload = () => {
    var e = document.getElementsByClassName("card-header");
    console.log(e)
    var el = Array.from(e);
    el.map((element) => {
        element.addEventListener("click", openCard);
    });
};