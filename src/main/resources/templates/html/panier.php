<?php
include_once("php/fonction-panier.php");

$erreur = false;
$show_delete = "";

if (creationPanier()) {
    $nbArticles=count($_SESSION['panier']['libelleProduit']);
    if ($nbArticles <= 0) {
        $show_delete = "disabled";

    } else {
        $show_delete = "";


    }
}

if(isset($_POST['delete_basket'])) {
    supprimePanier();


}

if(isset($_POST['delete_elmt'])) {

    supprimerArticle($_POST['delete_elmt_id']);
    $nbArticles=count($_SESSION['panier']['libelleProduit']);
}

if(isset($_POST['refresh_basket'])) {
    $nbArticles=count($_SESSION['panier']['libelleProduit']);
}



?>
<head>
<title>Votre panier</title>
</head>
<body>

<form method="post" action="">
<table class="responsive-table" style="width: 50%; margin-top: 150px; margin-bottom: 300px; margin-left: 150px;">
    <tr>
        <td colspan="4">Votre panier</td>
    </tr>
    <tr>
        <td style="padding: 5px;">Libellé</td>
        <td style="padding: 25px;">Quantité</td>
        <td style="padding: 25px;">Prix Unitaire</td>
        <td style="padding: 25px;">Action</td>
    </tr>
    <?php for ($i=0 ;$i < $nbArticles ; $i++): ?>
    <tr>
        <td style="padding: 25px;"><?=$_SESSION['panier']['libelleProduit'][$i]?></td>
        <td style="padding: 25px;"><input readonly type="text" size="4" value="<?=$_SESSION['panier']['qteProduit'][$i]?>" name="q[]"></td>
        <td style="padding: 25px;"><?=$_SESSION['panier']['prixProduit'][$i]?></td>
        <td style="padding: 25px;">
<!--            <a type="submit" href="index.php?page=panier?action=suppression&l=--><?//=$_SESSION['panier']['libelleProduit'][$i]?><!--">XX</a>-->
            <button style="background-color: #006fac;" class="btn-floating waves-effect waves-light" name="delete_elmt">
                <i class="material-icons right">delete</i>
                <input type="hidden" name="delete_elmt_id" value="<?=$_SESSION['panier']['libelleProduit'][$i]?>"/>
            </button>
        </td>
    </tr>
    <?php endfor ?>
    <tr><td colspan="2"> </td>
        <td style="padding: 25px;" colspan="2">Total  :  <?=MontantGlobal()?></td>
    </tr>
    <tr>
        <td colspan="4">
            <button name="refresh_basket" style="background-color: #006fac;" class="btn waves-effect waves-light">Rafraichir
            </button>
        </td>
        <td colspan="4">
            <button <?=$show_delete?> name="delete_basket" style="background-color: #006fac;" class="btn waves-effect waves-light">Supprimer le panier
            </button>
        </td>

    </tr>

</table>
</form>
</body>
</html>