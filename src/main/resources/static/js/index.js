stockCG = [];
stockPROC = [];
stockRAM = [];


fetch("../php/products/product_database.json")
    .then(response => {
        return response.json();
    })
    .then(data => {
        stockCG = data["products"]["stockCG"];
        stockPROC = data["products"]["stockPROC"];
        stockRAM = data["products"]["stockRAM"];
    });



function displayProducts(product) {

    if (product === 'cg') {
        // On affiche les cartes graphiques
        document.getElementById('cg').style.display = 'block';
        // On cache les autres catégories de produits
        document.getElementById('proc').style.display = 'none';
        document.getElementById('ram').style.display = 'none';
    }

    if (product === 'proc') {
        // On affiche les processeurs
        document.getElementById('proc').style.display = 'block';
        // On cache les autres catégories de produits
        document.getElementById('cg').style.display = 'none';
        document.getElementById('ram').style.display = 'none';
    }

    if (product === 'ram') {
        // On affiche les cartes graphiques
        document.getElementById('ram').style.display = 'block';
        // On cache les autres catégories de produits
        document.getElementById('proc').style.display = 'none';
        document.getElementById('cg').style.display = 'none';
    }

}


document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.collapsible');
    var instances = M.Collapsible.init(elems, {
        accordion: true
    });

});

function hideStock(product_id) {
    const elmt = document.getElementById('product_' + product_id + '_price');
    const current_state = elmt.getAttribute('style').split(';')[0].split(':')[1];
    console.log(current_state);
    if (current_state === ' none') {
        elmt.style.display = 'block';
    } else {
        elmt.style.display = 'none';
    }
}

function showCurrentStock(product_type, product_id) {

    const elmt = document.getElementById('product_' + product_id + '_price');

    if (product_type === 'cg') {

        stockCG.forEach(value => {
            if (value.id === product_id) {
                elmt.innerHTML = value.stock + ' pièce(s) en stock !';
            }
        });
    }

    if (product_type === 'proc') {

        stockPROC.forEach(value => {
            if (value.id === product_id) {
                elmt.innerHTML = value.stock + ' pièce(s) en stock !';
            }
        });
    }

    if (product_type === 'ram') {

        stockRAM.forEach(value => {
            if (value.id === product_id) {
                elmt.innerHTML = value.stock + ' pièce(s) en stock !';
            }
        });
    }
}

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

function changeQuantity(type, product_id, product_type) {
    const p_id = product_id.split('_')[1];
    if (type === '+') {
        if (product_type === 'cg') {
            stockCG.forEach(value => {
                if (value.id === p_id) {
                    const max_stock = value.stock;
                    const current_Value = document.getElementById(product_id).value;
                    if (current_Value < max_stock) {
                        document.getElementById(product_id).value = parseInt(current_Value) + 1;
                    }
                }
            });

        } else if (product_type === 'proc') {
            stockPROC.forEach(value => {
                if (value.id === p_id) {
                    const max_stock = value.stock;
                    const current_Value = document.getElementById(product_id).value;
                    if (current_Value < max_stock) {
                        document.getElementById(product_id).value = parseInt(current_Value) + 1;
                    }
                }
            });

        } else if (product_type === 'ram') {
            stockRAM.forEach(value => {
                if (value.id === p_id) {
                    const max_stock = value.stock;
                    const current_Value = document.getElementById(product_id).value;
                    if (current_Value < max_stock) {
                        document.getElementById(product_id).value = parseInt(current_Value) + 1;
                    }
                }
            });

        }
    }

    if (type === '-') {
        if (product_type === 'cg') {
            stockCG.forEach(value => {
                if (value.id === p_id) {
                    const current_Value = document.getElementById(product_id).value;
                    if (current_Value > 0) {
                        document.getElementById(product_id).value = parseInt(current_Value) - 1;
                    }
                }
            });

        } else if (product_type === 'proc') {
            stockPROC.forEach(value => {
                if (value.id === p_id) {
                    const current_Value = document.getElementById(product_id).value;
                    if (current_Value > 0) {
                        document.getElementById(product_id).value = parseInt(current_Value) - 1;
                    }
                }
            });

        } else if (product_type === 'ram') {
            stockRAM.forEach(value => {
                if (value.id === p_id) {
                    const current_Value = document.getElementById(product_id).value;
                    if (current_Value > 0) {
                        document.getElementById(product_id).value = parseInt(current_Value) - 1;
                    }
                }
            });

        }
    }
}



function register() {
    document.getElementById("register_form").style.display = "block";
    document.getElementById("login_form").style.display = "none";


}

function logout() {
    document.getElementById("register_form").style.display = "none";
    document.getElementById("login_form").style.display = "block";
    document.getElementById("user_details").style.display = "none";

}


