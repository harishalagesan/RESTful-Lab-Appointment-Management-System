<?php

/**
 * Created by PhpStorm.
 * User: Harish Alagesan
 * Date: 5/13/2017
 * Time: 7:45 PM
 */


/*Function to build XML for adding appointments*/
class addAppointment
{

    static $base_url = "http://localhost:8080/Rest_IST756/AptService/";

    public static function build_Appointment($arr,$bool){

        $display = "";


        foreach ($arr as $arrkey=>$key)
        {



            for($i=0; $i< count($key); $i++ ) {

                $display .="<div class='row'>";
                $display .="<div class='col s12 m6'>";
                $display .="<div class='card blue-grey darken-1'>";
                $display .="<div class='card-content white-text'>";
                $display .="<span class='card-title'>Appointment</span>";

                //print_r($key[$i]['@attributes']);
                $display .= "<p> AppointMent ID:".$key[$i]['@attributes']['id']."</p>";
                $display .= "<p> Appointment Date".$key[$i]['@attributes']['date']."</p>";
                $display .= "<p> Appointment Time".$key[$i]['@attributes']['time']."</p>";

                $display .= "<p> Patient ID:".$key[$i]['patient']['@attributes']['id']."</p>";
                $display .= "<p> Patient Name:".$key[$i]['patient']['name']."</p>";
                $display .= "<p> Patient Address:".$key[$i]['patient']['address']."</p>";
                $display .= "<p> Patient Insurance Status:".$key[$i]['patient']['insurance']."</p>";
                $display .= "<p> Phlebotomist ID:".$key[$i]['phlebotomist']['@attributes']['id']."</p>";
                $display .= "<p> Phlebotomist Name:".$key[$i]['phlebotomist']['name']."</p>";
                $display .= "<p> PSC ID:".$key[$i]['psc']['@attributes']['id']."</p>";
                $display .= "<p> PSC Name:".$key[$i]['psc']['name']."</p>";
                $display .= "<p> LAB Test Appointment ID".$key[$i]['allLabTests']['appointmentLabTest']['@attributes']['appointmentId']."</p>";
                $display .= "<p> LAB Test Code".$key[$i]['allLabTests']['appointmentLabTest']['@attributes']['dxcode']."</p>";
                $display .= "<p> LAB Test ID".$key[$i]['allLabTests']['appointmentLabTest']['@attributes']['labTestId']."</p>";
                $display .= "<div class='card-action'>";
                $display .= "<a href ='".($key[$i]['uri'])."'>URI:".$key[$i]['uri']."</a>";
                $display .= "</div>";
                $display .= "</div>";
                $display .= "</div>";
                $display .= "</div>";
                $display .= "</div>";
            }
        }

        return $display;
    }

    /*Function to build the appointment list to be displayed fpr editing each appointment*/
    public static function build_ApptList($arr,$bool){
        $display = "";
        $display .= "<div id ='apptList' class='container white z-depth-2'><div class='col s3'></div>";
        $display .= "<ul class='col s6 collection'>";

        foreach ($arr as $arrkey=>$key) {
            for($i=0; $i< count($key); $i++ ) {
                $display .= "<li class='collection-item'><a href='"
                            . $_SERVER["PHP_SELF"]
                            . "?aptID=". $key[$i]['@attributes']['id'] . "'>"
                            ."<div>Appointment ID: ". $key[$i]['@attributes']['id'];

                $display .= "<div>Date: ".$key[$i]['@attributes']['date']."</div>";
                $display .= "<div>Time: ".$key[$i]['@attributes']['time']."</div></div></a>";
            }
        }
        $display .="</ul><div class='col s3'>";
        $display .="</div>";
        return $display;
    }

    /*function to get all the data to be filled int he appointment form*/
    public static function getData($url){

        $sXML = RestService::download_page($url);
        $xml = simplexml_load_string($sXML);
        $json = json_encode($xml);
        $arr = json_decode($json,true);
        return $arr;
    }

    /*Function to build the appointment list to be displayed fpr editing each appointment*/
    public static function buildList($entity, $name, $selected){
        $url = addAppointment::$base_url . $entity . "List";
        $data = addAppointment::getData($url);

        $entities = $data[$entity];

        $display = "<select id='".$name."' name='".$name."'>";

        foreach ($entities as $key=>$value){
            if($selected == $value['id']){
                $display .= "<option value='" . $value['id'] . "'' selected>" . $value['name'] . "</option>";
            }else {
                $display .= "<option value='" . $value['id'] . "''>" . $value['name'] . "</option>";
            }
        }

        $display .= "</select>";
        return $display;
    }

}