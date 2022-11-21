<?php

if(isset($_POST['email'])){
    $to = "ing1pau@gmail.com"; // this is your Email address
    $from = $_POST['email']; // this is the sender's Email address
    $from = $_POST['email'];
    $name = $_POST['name'];
    $subject = $_POST['sujetMail'];

    $message = $name . " ". " a écrit:" . "\n\n" . $_POST['contenuMail'];
    $message2 ="Voici une copie de votre message : " . "\n\n" . $_POST['contenuMail'];

    $headers = "De:" . $from;
    $headers2 = "De:" . $to;
    mail($to,$subject,$message,$headers);
    mail($from,$subject,$message2,$headers2);
    // You can also use header('Location: thank_you.php'); to redirect to another page.
    header('Location: ../index.php');
}
?>


<main>
    <div class="container">
        <div class="row">
            <div class="col s12">
                <p>Pour nous contacter :</p>
                <form action="index.php?page=form" method="post" class="form" id="contact_form">
                    <div class="form">
                        <label for="name">Entrer votre nom: </label>
                        <input type="text" name="name" id="name" class="validate" required>
                        <span class="helper-text" data-error="Mauvais format ! Format : MonNom (ex: Jean)" data-success="Valide"></span>
                    </div>
                    <div class="form">
                        <label for="email">Entre votre adresse mail: </label>
                        <input aria-required="true" type="email" name="email" id="email" class="validate" required>
                        <span class="helper-text" data-error="Mauvais format ! Format : monmail@mon_fournisseurs.nom_de_domaine" data-success="Valide"></span>
                    </div>
                    <p>
                        <label>
                            <input name="genre" type="radio" checked />
                            <span>Homme</span>
                        </label>
                    </p>
                    <p>
                        <label>
                            <input name="genre" type="radio" />
                            <span>Femme</span>
                        </label>
                    </p>
                    <p>
                        <label>
                            <input name="genre" type="radio"  />
                            <span>Autre</span>
                        </label>
                    </p>
                    <div class="input-field col s12">
                        <select name="metier">
                            <option value="" disabled selected>Votre métier :</option>
                            <option value="1">Informaticien</option>
                            <option value="2">Mineur de crypto-monnaie</option>
                            <option value="3">Professeur à CY-Tech</option>
                            <option value="4">Étudiant à CY-Tech</option>
                            <option value="5">Autre</option>
                        </select>
                    </div>
                    <div class="form col s2">
                        <label for="name">Date de naissance: </label>
                        <input class="validate" min="1940-01-01" max="2022-01-01" type="date" name="dateNaissance" id="dateNaissance"  required>
                        <span class="helper-text" data-error="Mauvais format !" data-success="Valide"></span>
                    </div>
                    <div class="col s12">
                        <div class="form">
                            <label for="name">Sujet du mail </label>
                            <input type="text" name="sujetMail" id="sujetMail" required>
                            <span class="helper-text" data-error="Mauvais format !" data-success="Valide"></span>
                        </div>
                        <div class="form">
                            <label for="name">Votre mail :</label>
                            <input type="text" name="contenuMail" id="contenuMail" required>
                            <span class="helper-text" data-error="Mauvais format !" data-success="Valide"></span>
                        </div>
                        <div class="col form offset-s5" style="padding: 0 !important; margin-top: 20px;">
                            <button class="btn waves-effect waves-light" type="submit" name="action">Envoyer
                                <i class="material-icons right">send</i>
                            </button>

                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>



