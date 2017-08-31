<?php
/**
 * Created by PhpStorm.
 * User: Harish Alagesan
 * Date: 5/12/2017
 * Time: 8:37 PM
 */

$title="Appointments";
require_once  "main/starter/page_start.php";
require_once "main/starter/functions.php";
require_once "main/starter/header.php";
require "main/class/addAppointment.php";
require "main/class/RestService.php";

$display="";
$count= 0;

/*Display all appointments on a calender and click of each appointment leads to the appointment xml*/

$sXML = RestService::download_page('http://localhost:8080/Rest_IST756/AptService/Appointments');

$xml = simplexml_load_string($sXML);
$json = json_encode($xml);
$arr = json_decode($json,true);

foreach ($arr as $arrkey=>$key)
{
    $count = count($key);
}
echo "<div style=\"float:left; width: 160px;\">
  <div id=\"nav\"></div>
</div>
<div style=\"margin-left: 160px;\">
  <div id=\"dp\"></div>
</div>";

/*function to add the appointments to the calender*/
echo "<script> populateAppointments(".$json.")</script>";
?>
<?php
require_once "main/starter/footer.php";

?>