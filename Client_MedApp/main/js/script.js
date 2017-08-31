
/*Script to populate appointments in the calender and provide a click functionality to each appointment on
*
* the Calender*/

function populateAppointments(json){

    var nav = new DayPilot.Navigator("nav");
    nav.showMonths = 3;
    nav.skipMonths = 3;
    nav.selectMode = "week";

    nav.onTimeRangeSelected = function(args) {
        dp.startDate = args.day;
        dp.update();
    };

    var dp = new DayPilot.Calendar("dp");
    dp.viewType = "Week";

    var events = [];

    var appointments = json.Appointment;

    dp.startDate =  appointments[0]["@attributes"].date + "T" + appointments[0]["@attributes"].time;

    for(var i=0; i<appointments.length; i++) {

        var hour = appointments[i]["@attributes"].time.split(":")[0];
        var min = appointments[i]["@attributes"].time.split(":")[1];
        var sec = appointments[i]["@attributes"].time.split(":")[2];

            min = parseInt(min) + 15;

        if(min > 60){
            hour++;
            min = min - 60;
        }
        if(min<10){
            min = "0"+ min;
        }
        console.log(min);
        var endTime = hour + ":" + min + ":" + sec;

        var event = {
            "id" : appointments[i]["@attributes"].id,
            "text" : "<a href='" + appointments[i].uri
            +"'>"
            + appointments[i].patient.name +
            "</a>",
            "start" : appointments[i]["@attributes"].date + "T" + appointments[i]["@attributes"].time,
            "end" : appointments[i]["@attributes"].date + "T" + endTime

        };
        events.push(event);
    }

    dp.events.list = events;



    dp.init();
    nav.init();

    nav.select(new DayPilot.Date(appointments[0]["@attributes"].date));
    nav.update();


    $('td').on("click", "");

    $('.calendar_default_event').on("click", function (event) {
       alert("Click Me to View the XML");
    });
}