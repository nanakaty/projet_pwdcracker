<?php
// Mot de passe fixé
$motDePasseFixe = "0674";

// Récupérer le mot de passe envoyé en tant que paramètre GET
$motDePasse = $_GET["password"];

// Vérifier si le mot de passe correspond au mot de passe fixé
if ($motDePasse === $motDePasseFixe) {
    echo "Mot de passe correct !";
} else {
    echo "Mot de passe incorrect.";
}
?>
