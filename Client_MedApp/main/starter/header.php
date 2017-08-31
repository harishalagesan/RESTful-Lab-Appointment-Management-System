
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8" />
    <title>Lams Appointment<?= ($title) ? " | " . $title : "" ?></title>
    <link rel="stylesheet" href="<?=URL_CSS?>index.css">
    <script type="text/javascript" src="<?=URL_JS?>jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<?=URL_JS?>materialize.min.js"></script>
    <script type="text/javascript" src="<?=URL_JS?>jssor.slider-22.2.16.mini.js"></script>
    <script type="text/javascript" src="<?=URL_JS?>daypilot-all.min.js"></script>
    <!--<script type="text/javascript" src="<?=URL_JS?>jquery.min.js"></script>-->
    <script type="text/javascript" src="<?=URL_JS?>script.js"></script>
    <script type="text/javascript" src="<?=URL_JS?>materialize.clockpicker.js"></script>
    <script type="text/javascript">


        $(document).ready(function(){
            $('.carousel').carousel();
            $('.carousel.carousel-slider').carousel({
                fullWidth: true

            });

            $('select').material_select();
        });


    </script>

   <link rel="shortcut icon" type="image/x-icon" href="<?=URL_IMG?>favicon.ico">
</head>

<body>
<!--Navigation bar link display-->
<nav>
    <div class="nav-wrapper">
        <a href="<?php echo $_SERVER["PHP_SELF"];?>" class="brand-logo center">Lams Appointment</a>
        <ul id="nav-mobile" class="left hide-on-med-and-down">
            <li><a href="<?=URL_HOME?>">Home</a></li>
            <li><a href="<?=URL_SERVICE?>">Services</a></li>
            <li><a href="<?=URL_ALLAPT?>">View Appointments</a></li>

        </ul>

        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="<?=URL_SETAPT?>">Add Appointment</a></li>
            <li><a href="<?=URL_EDITAPT?>">Edit Appointments</a></li>
        </ul>
    </div>
</nav>