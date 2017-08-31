<?php
/**
 * Created by PhpStorm.
 * User: Harish Alagesan
 * Date: 5/14/2017
 * Time: 1:22 AM
 */


$title="Alter Appointments";
require_once  "main/starter/page_start.php";
require_once "main/starter/functions.php";
require_once "main/starter/header.php";
require_once "main/class/addAppointment.php";
require_once "main/class/RestService.php";

$method = "post";
$mode = "EDIT";
$arr = array();
$aptDate=$aptTime=$phleID=$phyID=$patID=$dxCode1=$dxCode2=$lbTest1ID=$lbTest2ID=$pscID="";

$aptID = "";

/*Build the edit pge with Appointment ID , Date and Time fpr display*/

/*EDIT appointments */
if (!isset($_GET['aptID'])){

    $sXML = RestService::download_page('http://localhost:8080/Rest_IST756/AptService/Appointments');

    $xml = simplexml_load_string($sXML);
    $json = json_encode($xml);
    $arr = json_decode($json,true);

    echo addAppointment::build_ApptList($arr, false);
}
else {

    $aptID = $_GET['aptID'];

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $xml_str = '<?xml version="1.0" encoding="utf-8" ?>' .
            '<appointment>' .
            '<date>' . $_POST["aptDate"] . '</date>' .
            '<time>' . $_POST["aptTime"] . '</time>' .
            '<patientId>' . $_POST["patID"] . '</patientId>' .
            '<physicianId>' . $_POST["phyID"] . '</physicianId>' .
            '<pscId>' . $_POST["pscID"] . '</pscId>' .
            '<phlebotomistId>' . $_POST["phleID"] . '</phlebotomistId>' .
            '<labTests>' .
            '<test id="' . $_POST["lbTest1ID"] . '" dxcode="' . $_POST["dxCode1"] . '" />' .
            '<test id="' . $_POST["lbTest2ID"] . '" dxcode="' . $_POST["dxCode2"] . '" />' .
            '</labTests>' .
            '</appointment>';

        /*Create XML to update edited appointment with PUT method*/

        $url = "http://localhost:8080/Rest_IST756/AptService/Appointments/{$aptID}";

        $put_data = array("xml" => $xml_str);


        $stream_options = array(
            'http' => array(
                'method' => 'PUT',
                'header' => 'Content-type: application/xml' . "\r\n",
                'content' => http_build_query($put_data)));


        $context = stream_context_create($stream_options);
        $response = file_get_contents($url, null, $context);

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
            echo " ---> Appointment Edited";
        }




    }

    /*Display the edited changed back in the same page*/
        $arr = addAppointment::getData("http://localhost:8080/Rest_IST756/AptService/Appointments/" . $aptID);

        $aptDate = $arr["Appointment"]["@attributes"]["date"];
        $aptTime = $arr["Appointment"]["@attributes"]["time"];
        $patID = $arr["Appointment"]["patient"]["@attributes"]["id"];
        $phleID = $arr["Appointment"]["phlebotomist"]["@attributes"]["id"];
        $pscID = $arr["Appointment"]["psc"]["@attributes"]["id"];
        $labTests = $arr["Appointment"]["allLabTests"]["appointmentLabTest"];

        if (isset($labTests[0])) {
            $lbTest1ID = $labTests[0]['@attributes']['labTestId'];
            $dxCode1 = $labTests[0]['@attributes']['dxcode'];
        } else {
            $lbTest1ID = $labTests['@attributes']['labTestId'];
            $dxCode1 = $labTests['@attributes']['dxcode'];
        }
        if (isset($labTests[1])) {
            $lbTest2ID = $labTests[1]['@attributes']['labTestId'];
            $dxCode2 = $labTests[1]['@attributes']['dxcode'];
        }


    require_once "appointmentForm.php";

}
require_once "main/starter/footer.php";


?>