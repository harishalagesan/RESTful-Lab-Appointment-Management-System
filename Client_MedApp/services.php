<?php
/**
 * Created by PhpStorm.
 * User: Harish Alagesan
 * Date: 5/12/2017
 * Time: 8:31 PM
 */


$title="Services";
require_once  "main/starter/page_start.php";
require_once "main/starter/functions.php";
require_once "main/starter/header.php";

/*PHP CuRL to get the data*/
function download_page($path){
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL,$path);
    curl_setopt($ch, CURLOPT_FAILONERROR,1);
    curl_setopt($ch, CURLOPT_FOLLOWLOCATION,1);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER,1);
    curl_setopt($ch, CURLOPT_TIMEOUT, 15);
    $retValue = curl_exec($ch);
    curl_close($ch);
    return $retValue;
}

/*The Wadl link displayed to view all services*/
$sXML = download_page('http://localhost:8080/Rest_IST756/AptService/Services');

$xml = simplexml_load_string($sXML);
$json = json_encode($xml);
$arr = json_decode($json,true);

echo "<ul>";
    echo "<h3 class='teal-text' style='padding-left:10em ; padding-top:5em'>".$arr['intro']."</h3>";
    echo "<h4><a href='http://localhost:8080/Rest_IST756/application.wadl' style='padding-left:12em'>".$arr['wadl']."</a></h4>";
echo "</ul>";
?>








<?php
require_once "main/starter/footer.php";

?>