<?php

include_once("php/fonction-panier.php");
$strJsonFileContents = file_get_contents("php/products/product_database.json");
$product = json_decode($strJsonFileContents, true); // show contents

if (isset($_POST['basket'])) {
    ajouterArticle($_POST['item_id'] ,$_POST['number_of_item'], $_POST['item_price']);

}
?>

<div id="ram" class="col s12" style="display: block;">
    <div>
        <div class="row">
            <div class="col s11">
                <h5 class="left-align">Découvrez nos séléctions de Mémoires RAM !</h5>
            </div>
        </div>
        <div class="row">
            <ul class="collapsible">
                <?php foreach($product["products"]["stockRAM"] as $item): ?>
                <li class="active" id="<?=$item["id"]?>">
                    <div class="collapsible-header">
                        <i class="material-icons">shopping_cart</i>
                        <div class="col">
                            <label style="color: black; font-size: 16px;"><?=$item["title"]?></label>
                        </div>
                        <div class="col offset-s3">
                            <label><?=$item["ref"]?></label>
                        </div>
                        <div class="col offset-s1" style="margin-left:  auto; margin-right: 0;">
                            <label><?=$item["price"]?> €</label>
                        </div>
                    </div>
                    <div class="collapsible-body">
                        <div class="row">
                            <div class="col offset-s1">
                                <img class="thumbnail" src="<?=$item["img_src"]?>" width="250" height="250"/>
                            </div>
                            <div class="col offset-s1">
                                <ul class="desc_product">
                                    <?php foreach($item["desc"] as $desc): ?>
                                        <li><i class="material-icons">star</i> <?=$desc?></li>
                                    <?php endforeach ?>
                                </ul>
                            </div>
                        </div>
                        <form name="basket" action="" method="post">
                        <div class="row"  style="margin-top: 70px; margin-left: auto; margin-right: 0;">
                            <div class="col offset-s7" style="margin-top: 25px;">
                                <label id="product_<?=$item["id"]?>_price" class="price_label" style="display: none; color: #006fac; font-size: 20px; font-weight: bold;">XX pièce(s) en stock !</label>
                            </div>
                            <div class="col" style="margin-top: 30px;">
                                <i onclick="changeQuantity('+', 'product_<?=$item["id"]?>_quantity', 'ram')" style="border: black 1px solid; border-radius: 6px; font-size: 15px;" class="material-icons">add</i>
                            </div>
                            <div class="input-field col" style="width: 100px;">
                                <input name="item_id" value="<?=$item["title"]?>" style="display: none;" />
                                <input name="item_price" value="<?=$item["price"]?>" style="display: none;" />
                                <input name="number_of_item" value="1" id="product_<?=$item["id"]?>_quantity" min="0" max="999" type="number" />
                            </div>
                            <div class="col" style="margin-top: 30px;">
                                <i onclick="changeQuantity('-', 'product_<?=$item["id"]?>_quantity', 'ram')" style="border: black 1px solid; border-radius: 6px; font-size: 15px;" class="material-icons">remove</i>
                            </div>
                        </div>
                        <div class="row" style="margin-top: 20px;">
                            <div class="col offset-s7">
                                <a style="background-color: #006fac;" class="waves-effect waves-light btn" onclick="hideStock('<?=$item["id"]?>'); showCurrentStock('ram', '<?=$item["id"]?>');"><i class="material-icons right">inventory_2</i>Voir le stock</a>
                            </div>
                            <div class="col">
                                <button style="background-color: #006fac;" class="btn waves-effect waves-light" type="submit" name="basket">Ajouter au panier
                                    <i class="material-icons right">add_shopping_cart</i>
                                </button>
                            </div>
                        </div>
                        </form>
                    </div>
                </li>
                <?php endforeach ?>
            </ul>
        </div>
    </div>
</div>

<div id="myModal" class="modal">
    <img class="modal-content" id="img01">
    <div id="caption"></div>
</div>