<?php
/**
 * Created by PhpStorm.
 * User: Harish Alagesan
 * Date: 5/14/2017
 * Time: 1:22 AM
 */


$title="Set Appointments";
require_once  "main/starter/page_start.php";
require_once "main/starter/functions.php";
require_once "main/starter/header.php";
require_once "main/class/addAppointment.php";
require_once "main/class/RestService.php";

$method = "post";
$mode = "ADD";

/*Adding new appointments*/

$aptDate=$aptTime=$phleID=$phyID=$patID=$dxCode1=$dxCode2=$lbTest1ID=$lbTest2ID=$pscID="";

/*Generating the XML for adding the appointment from user selected data*/
if ($_SERVER['REQUEST_METHOD'] == 'POST')
{
$xml_str = '<?xml version="1.0" encoding="utf-8" ?>'.
    '<appointment>'.
    '<date>'.$_POST["aptDate"].'</date>'.
    '<time>'.$_POST["aptTime"].'</time>'.
    '<patientId>'.$_POST["patID"].'</patientId>'.
    '<physicianId>'.$_POST["phyID"].'</physicianId>'.
    '<pscId>'.$_POST["pscID"].'</pscId>'.
    '<phlebotomistId>'.$_POST["phleID"].'</phlebotomistId>'.
    '<labTests>'.
    '<test id="'.$_POST["lbTest1ID"].'" dxcode="'.$_POST["dxCode1"].'" />'.
    '<test id="'.$_POST["lbTest2ID"].'" dxcode="'.$_POST["dxCode2"].'" />'.
    '</labTests>'.
    '</appointment>';

/*PHP CuRL to post appointments*/

$url = "http://localhost:8080/Rest_IST756/AptService/Appointments";
$post_data = array("xml" => $xml_str);
$stream_options = array(
    'http' => array(
        'method'  => 'POST',
        'header'  => 'Content-type: application/xml' . "\r\n",
        'content' =>  http_build_query($post_data)));

$context  = stream_context_create($stream_options);
$response = file_get_contents($url, null, $context);


/*Parse xml tp show message*/
    $data ="";
    $xmlnew=simplexml_load_string($response) or die("Error: Cannot create object");
    $json = json_encode($xmlnew);
    $arr = json_decode($json,true);
    foreach($arr as $key=>$value)
    {
        $data = $key;
    }

    echo $response;
    if($data =="uri")
    {
        echo " ---> Appointment Added";
    }
}

require_once "appointmentForm.php";

require_once "main/starter/footer.php";

?>