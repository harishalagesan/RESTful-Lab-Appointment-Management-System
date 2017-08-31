<?php

    session_name( "KillingJoke" );
    session_start();

    define( "PATH_BASE" , "./" );
    define( "PATH_JS" , PATH_BASE . "js/" );
    define( "PATH_CSS", PATH_BASE . "css/" );
    define( "PATH_IMG", PATH_BASE . "images/" );

    define("URL_BASE", "http://localhost:8012/client/");
    define("URL_JS", URL_BASE . "main/js/");
    define("URL_CSS", URL_BASE . "main/css/");
    define("URL_IMG", URL_BASE . "main/images/");
    define("URL_HOME", URL_BASE . "index.php");
    define("URL_SERVICE", URL_BASE . "services.php");
    define("URL_ALLAPT", URL_BASE . "allAppointment.php");
    define("URL_EDITAPT", URL_BASE . "editAppointment.php");
    define("URL_SETAPT", URL_BASE . "setAppt.php");


?>