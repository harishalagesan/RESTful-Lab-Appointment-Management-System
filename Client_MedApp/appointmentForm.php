<?php
/**
 * Created by PhpStorm.
 * User: Harish Alagesan
 * Date: 5/15/2017
 * Time: 1:23 AM
 */

$cur_page= explode("/", $_SERVER['SCRIPT_NAME']);
if($cur_page[2] == "editAppointment.php"){

    $currentPage = $_SERVER["PHP_SELF"]."?aptID=".$aptID;
}
else{

    $currentPage = $_SERVER["PHP_SELF"];
}

?>

<!--Build the Appointment form for both set and edit appointment( same page )-->

<div class = "register_user">
    <div class="container white z-depth-2" >
        <ul class="tabs teal">
            <li class="tab col s3"><a class="white-text" style="padding-left: 22em; font-size: 20px">Services</a></li>
        </ul>
        <div id="register" class="col s12">
            <form class="col s12" name="input" action="<?php echo $currentPage;?>" method="<?php echo $method;?>" enctype="multipart/form-data">
                <div class="form-container" style="padding-left: 2em" >
                    <h3 class="teal-text" style="padding-left: 7em"><?php echo $mode;?> Appointment</h3>
                    <div class="row">
                        <input id="aptID" name="aptID"  value="<?php echo $aptID;?>" style="display:none">
                    </div>
                    <div class="row">
                        <div class="input-field col s6">
                            <input id="aptDate" name ="aptDate" type="date" class = "datepicker"  value="<?php echo $aptDate;?>" >
                            <label for="aptDate" style ="top : -2rem">Appointment Date</label>
                        </div>
                        <div class="input-field col s6">
                            <input id="aptTime" name = "aptTime" type="time" class = "clockpicker" data-default="08:00:00"  value="<?php echo $aptTime;?>">
                            <label for="aptTime" style ="top : -2rem">Appointment Time</label>
                        </div>

                    <div class="row">
                        <div class="input-field col s6">
                            <?php echo addAppointment::buildList("Patient", "patID",$patID);?>
<label for="patID">Patient ID</label>

</div>
<div class="input-field col s6">
    <?php echo addAppointment::buildList("Physician", "phyID", $phyID);?>
    <label for="Product_Price">Physician ID</label>
</div>
</div>
<div class="row">
    <div class="input-field col s6">
        <?php echo addAppointment::buildList("PSC", "pscID", $pscID);?>
        <label for="Product_Price">PSC ID</label>
    </div>
    <div class="input-field col s6">
        <?php echo addAppointment::buildList("Phlebotomist", "phleID", $phleID);?>
        <label for="Product_Price">Phlebotomist ID</label>
    </div>
</div>

<div class="row">
    <div class="input-field col s6">
        <?php echo addAppointment::buildList("LabTest", "lbTest1ID", $lbTest1ID);?>
        <label for="Product_Price">Lab Test 1 ID</label>
    </div>
    <div class="input-field col s6">
        <?php echo addAppointment::buildList("Diagnosis", "dxCode1", $dxCode1);?>
        <label for="Product_Price">dx Code 1</label>
    </div>
</div>

<div class="row">
    <div class="input-field col s6">
        <?php echo addAppointment::buildList("LabTest", "lbTest2ID", $lbTest2ID);?>
        <label for="Product_Price">Lab Test 2 ID</label>
    </div>
    <div class="input-field col s6">
        <?php echo addAppointment::buildList("Diagnosis", "dxCode2", $dxCode2);?>
        <label for="Product_Price">dx Code 2</label>
    </div>
</div>


<center>
    <button class="btn waves-effect waves-light teal" type="submit" name="add"><?php echo $mode;?> Appointment</button> <button class="btn waves-effect waves-light teal" type="reset" name="reset" value ="Reset Form">Reset</button>
</center>
</div>
</form>
</div>
</div>
</div>
