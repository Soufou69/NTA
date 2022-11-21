<?php

 $forms_login = 'block';
 $forms_register = 'none';
 $details = 'none';
 $error_msg = "";

if (!isset($_SESSION['isConnected']))
{

    $forms_login = 'block';
    $forms_register = 'none';
    $details = 'none';
}
else {
    if($_SESSION['isConnected']) {
        $forms_login = 'none';
        $forms_register = 'none';
        $details = 'block';
    } else {
        $forms_login = 'block';
        $forms_register = 'none';
        $details = 'none';
    }
}


if(isset($_POST['email_login'])){

    $strJsonFileContents = file_get_contents("php/users_database.json");
    $users = json_decode($strJsonFileContents, true); // show contents
    $connectionState = false;

    foreach ($users as $user) {
        if ($user['email'] == $_POST['email_login'] and password_verify($_POST['password'], $user['password'])) {
            $connectionState = true;
            $_SESSION['isConnected'] = true;
            $_SESSION['email']= $_POST['email_login'];

        } else {
            $connectionState = false;
            $error_msg = "L'authentification a échouer, veuillez vérifier les informations et réeesayer !";
            $_SESSION['isConnected'] = false;
        }
    }

    if ($connectionState) {

        $_SESSION['email']= $_POST['email_login'];
        $forms_login = 'none';
        $forms_register = 'none';
        $details = 'block';
    }


} else if(isset($_POST['email_register'])){

    if ($_POST['email_register'] == "" or $_POST['name'] == "" or $_POST['password'] = "") {
        $error_msg = "Un problème de saisie des informations est survenue ! Pensez à vérifier les champs à remplir !";
    } else {
        $_SESSION['email']= $_POST['email_register'];

        $strJsonFileContents = file_get_contents("php/users_database.json");
        $tempArray = json_decode($strJsonFileContents, TRUE);
        $pswd = password_hash($_POST['register_pwd'], PASSWORD_DEFAULT);
        $tempArray[] = ['id' => session_id(), 'name' => $_POST['name'], 'email' => $_POST['email_register'], 'password' => $pswd];
        $jsonData = json_encode($tempArray);
        file_put_contents("php/users_database.json", $jsonData);
    }



}


if (isset($_POST['action'])) {
    switch ($_POST['action']) {
        case 'logout':
            session_destroy();
            break;
    }
}

if (isset($_POST['log_out'])) {
    session_destroy();
    $_SESSION['isConnected'] = false;
}


?>

<div class="container" id="login_form" style="display: <?php echo($forms_login) ?>;">
    <form action="" method="post">
        <div class="row center-align" id="user_box_1">
            <div class="col" id="user_box_2">
                <div style="border-bottom: black 1px solid; padding: 10px;">
                    <h5>ESPACE UTILISATEUR</h5>
                </div>
                <div class="row">
                    <div class="input-field col s12" style=" padding: 10px;">
                        <input name="email_login" id="email" type="email" class="validate">
                        <label for="email">Email</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12" style=" padding: 10px;">
                        <input name="password" id="password" type="password" class="validate">
                        <label for="password">Password</label>
                    </div>
                </div>
                <div class="row" >
                    <p style="color: red;"><?=$error_msg?></p>
                </div>
                <div class="row" style="border-bottom: black 1px solid; padding: 10px 10px 20px;">
                    <div class="col offset-s2" style="padding: 0 !important;">
                        <button style="background-color: #006fac;" class="btn waves-effect waves-light" name="login">Se connecter
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </div>
    </form>
                <div class="row" style="margin-bottom: 10px; !important;">
                    <div class="col" style="padding: 0 !important;">
                        <label style="display: block; margin-left: 40px;">Pas encore de compte ? Crée en un ici.</label>
                    </div>
                </div>
                <div class="row" style="padding: 10px;">
                    <div class="col offset-s5" style="padding: 0 !important;">
                        <a  style="background-color: #006fac; " class="btn-floating waves-effect waves-light"><i class="material-icons" onclick="register()">person_add_alt</i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>

<div class="container" id="register_form" style="display: <?php echo($forms_register) ?>;">
    <form action="" method="post">
    <div class="row center-align" id="user_box_1">
        <div class="col" id="user_box_2">
            <div style="border-bottom: black 1px solid; padding: 10px;">
                <h5>ESPACE UTILISATEUR</h5>
            </div>
            <div class="row">
                <div class="input-field col s12" style=" padding: 10px;">
                    <input id="name" name="name" type="text" class="validate">
                    <label for="name">Nom</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12" style=" padding: 10px;">
                    <input id="email" name="email_register" type="email" class="validate">
                    <label for="email">Email</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12" style=" padding: 10px;">
                    <input name="register_pwd" type="password" class="validate">
                    <label for="password">Password</label>
                </div>
            </div>
            <div class="row" >
                <p style="color: red;"><?=$error_msg?></p>
            </div>
            <div class="row" style="padding: 10px;">
                <div class="col offset-s2" style="padding: 0 !important;">
                    <button style="background-color: #006fac;" class="btn waves-effect waves-light" onclick="register();" name="action">S'ENREGISTRER
                        <i class="material-icons right">person_add_alt</i>
                    </button>
                </div>
            </div>
        </div>
    </div>
        </form>
</div>

<div class="container" id="user_details" style="padding: 10px; display: <?php echo($details) ?>; margin-top: 100px; margin-bottom: 150px;">
        <div class="row center-align">
            <div class="col">
                <div style="border-bottom: black 1px solid; padding: 10px;">
                    <h5>ESPACE UTILISATEUR</h5>
                </div>
            </div>
        </div>
        <div class="row center-align">
            <div class="col">
                <div style="padding: 10px;">
                    <h6>Mon adresse email :  <?=$_SESSION['email']?></h6>
                </div>
            </div>
        </div>
        <div class="row center-align">
            <div class="col">
                <div style="padding: 10px;">
                    <h6>Ma dernière commande :</h6>
                </div>
            </div>
            <div class="col">
                <div style="padding: 6px;">
                    <p style="color: #006fac;">Commande n°XXX</p>
                </div>
            </div>
        </div>
    <div class="row center-align">
        <div class="col">
            <form action="" method="post">
                <input  type="hidden" name="log_out">

            <button style="background-color: #006fac;" class="btn waves-effect waves-light" type="submit" name="action" onclick="logout();">SE DÉCONNECTER
                <i class="material-icons right">logout</i>
            </button>
            </form>
        </div>
    </div>
    </div>


<script type="text/javascript" src="../js/test.js"></script>