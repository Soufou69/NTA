function showHiddenSideBar(){
    if(document.getElementById("menu-toggle").getAttribute("class").includes("is-active")){
        document.getElementById("sidebar").setAttribute("style","position:absolute;left:-250")
        document.querySelector("nav").setAttribute("style","");
    }else{
        document.querySelector("nav").setAttribute("style","width: calc(100% - 250px)")
        document.getElementById("sidebar").setAttribute("style","position:absolute;left:0")
    }
    document.getElementById("menu-toggle").classList.toggle("is-active")
}