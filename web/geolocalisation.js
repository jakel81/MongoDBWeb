/* global google */

var lblMessage;

// ----------
function init() {

    lblMessage = document.getElementById("lblMessage");

    // --- GEOLOCALISATION HTML5
    if (navigator.geolocation) {
        // --- Demande de la position
        // --- Cette methode prend en parametre
        // --- une fonction de callback qu'elle va appeler
        // --- en lui fournissant le parametre position contenant les coordonnees.
        navigator.geolocation.getCurrentPosition(obtenirLatEtLong, echec);
    } else {
        lblMessage.innerHTML = "Votre navigateur ne gère pas la géolocalisation";
    }
} /// init

// ----------------------
function obtenirLatEtLong(position) {
    // --- Latitude
    var latitude = position.coords.latitude;
    // --- Longitude
    var longitude = position.coords.longitude;

    document.getElementById("lat").value = latitude;
    document.getElementById("lng").value = longitude;


    // La Map
    afficherCarte(latitude, longitude);
} /// obtenirLatEtLong

// -----------
function echec(erreur) {
    switch (erreur.code) {
        case erreur.TIMEOUT:
            navigator.geolocation.getCurrentPosition(obtenirLatEtLong, echec);
            break;
        case erreur.POSITION_UNAVAILABLE:
            document.getElementById("lblMessage").innerHTML = "Position indisponible";
            break;
        case erreur.PERMISSION_DENIED:
            document.getElementById("lblMessage").innerHTML = "Permission refusée";
            break;
        case erreur.UNKNOWN_ERROR:
            document.getElementById("lblMessage").innerHTML = "Erreur inconnue";
            break;
        default:
            document.getElementById("lblMessage").innerHTML = "Echec de l'obtention de coordonnées";
            break;
    }
} /// echec

// -------------------
function afficherCarte(latitude, longitude) {
    // --- Affiche une carte a latitude, longitude, zoom (De 1 a 20)
    var coordonnees = new google.maps.LatLng(latitude, longitude);

    // --- Les options
    var options = {
        zoom: 14,
        mapTypeId: google.maps.MapTypeId.SATELLITE,
        center: coordonnees
    };

    // --- La carte
    var carte = new google.maps.Map(document.getElementById("divMap"), options);

    // --- Un marqueur
    var marqueur = new google.maps.Marker({
        map: carte,
        position: coordonnees
    });

    var zoneMarqueurs = new google.maps.LatLngBounds();

    var tTR = document.getElementsByTagName("tr");
    console.log(tTR);
    for (var i = 1; i < tTR.length; i++) {
        var tTD = tTR[i].childNodes;
        console.log(tTD[3]);
        console.log(tTD[5]);

        var lat = parseFloat(tTD[3].innerHTML);
        var lng = parseFloat(tTD[5].innerHTML);
        console.log(lat);
        console.log(lng);

        var position2 = new google.maps.LatLng(lat, lng);

        var marqueur1 = new google.maps.Marker({
            map: carte,
            position: position2
        });

        zoneMarqueurs.extend(marqueur1.getPosition());
    }
    carte.fitBounds(zoneMarqueurs);

} /// getMap

// --- Au chargement
window.onload = init;
