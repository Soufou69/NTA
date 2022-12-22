document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.collapsible');
    var instances = M.Collapsible.init(elems, {
        accordion: true
    });

});

//Zoom
//Modal pour permettre le zoom
const modal = document.getElementById('myModal');
const modalImg = document.getElementById("img01");
const captionText = document.getElementById("caption");
var img = document.querySelectorAll('.thumbnail');
    
for (var i=0; i<img.length; i++){
    
    img[i].onclick = function(){
    modal.style.display = "block";
    modalImg.src = this.src;
    modalImg.alt = this.alt;
    captionText.innerHTML = this.alt;
}
     
}
// Fermeture
modal.onclick = function() {
    img01.className += " out";
    setTimeout(function() {
       modal.style.display = "none";
       img01.className = "modal-content";
     }, 400);
    
 }       