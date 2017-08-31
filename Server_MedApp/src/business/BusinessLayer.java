package business;

import DB.DBSingleton;
import com.jamesmurty.utils.XMLBuilder;
import components.data.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.time.LocalTime;
import java.util.*;
import java.lang.*;

/**
 * Created by Harish Alagesan on 5/10/2017.
 */
public class BusinessLayer {

    private boolean errFlag=false;
    private int errCode;
    private List<Object> objs;
    private DBSingleton dbSingleton;
    private Helper help;
    private ArrayList<String> errMsg;
    private String out;
    public BusinessLayer(){

        dbSingleton = DBSingleton.getInstance();
        objs = new ArrayList<>();
        help = new Helper();
        errMsg = new ArrayList<>();
        out = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    }

    /**
     * Elements of Wadl business case
     */
    public String wadldoc(String url) {

        try {
            XMLBuilder xml = XMLBuilder.create("AppointmentList")
                    .element("intro").text("Welcome to the LAMS Appointment Service").up()
                    .element("wadl").text(url);
            System.out.println(xml.asString());
            return xml.asString();

        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        errFlag = true;
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                "<AppointmentList>\n" +
                "    <error>ERROR:Service Error</error>\n" +
                "</AppointmentList>";
    }

    public boolean isErrFlag() {
        return errFlag;
    }

    public int getErrCode() {
        return errCode;
    }

    /**
     * Business logic to get all appointments and build XML
     */
    public String getallAppointments(String url,String id) {

        try {
            XMLBuilder xml = XMLBuilder.create("AppointmentList");
            if(id.equals("0")) {
                objs = dbSingleton.db.getData("Appointment", "");
            }else{
                objs = dbSingleton.db.getData("Appointment","id='"+id+"'");
            }
            for (Object obj : objs){
                Appointment apt = (Appointment) obj;
                xml = xml.importXMLBuilder(help.Helperxml(apt,url));
            }
            System.out.println(xml.asString());

            return xml.asString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        errFlag = true;
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                "<AppointmentList>\n" +
                "    <error>ERROR:Service Error</error>\n" +
                "</AppointmentList>";

    }

    /**
     * Business logic to get all patients and build XML
     */
    public String getallPatients(String url,String id) {

        try {
            XMLBuilder xml = XMLBuilder.create("Patients");
            if(id.equals("0")) {
                objs = dbSingleton.db.getData("Patient", "");
            }else{
                objs = dbSingleton.db.getData("Patient","id='"+id+"'");
            }
            for (Object obj : objs){
                Patient patient = (Patient) obj;
                xml = xml.importXMLBuilder(help.Helperxml(patient,url));
            }
            System.out.println(xml.asString());

            return xml.asString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        errFlag = true;
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                "<AppointmentList>\n" +
                "    <error>ERROR:Service Error</error>\n" +
                "</AppointmentList>";

    }

    /**
     * Business logic to get all Physicians and build XML
     */
    public String getallPhysicians(String url,String id) {

        try {
            XMLBuilder xml = XMLBuilder.create("Physicians");
            if(id.equals("0")) {
                objs = dbSingleton.db.getData("Physician", "");
            }else{
                objs = dbSingleton.db.getData("Physician","id='"+id+"'");
            }
            for (Object obj : objs){
                Physician physician = (Physician) obj;
                xml = xml.importXMLBuilder(help.Helperxml(physician,url));
            }
            System.out.println(xml.asString());

            return xml.asString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        errFlag = true;
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                "<AppointmentList>\n" +
                "    <error>ERROR:Service Error</error>\n" +
                "</AppointmentList>";

    }

    /**
     * Business logic to get all Phlebotomists and build XML
     */
    public String getallPhlebotomists(String url,String id) {

        try {
            XMLBuilder xml = XMLBuilder.create("Phlebotomists");
            if(id.equals("0")) {
                objs = dbSingleton.db.getData("Phlebotomist", "");
            }else{
                objs = dbSingleton.db.getData("Phlebotomist","id='"+id+"'");
            }
            for (Object obj : objs){
                Phlebotomist phlebotomist = (Phlebotomist) obj;
                xml = xml.importXMLBuilder(help.Helperxml(phlebotomist,url));
            }
            System.out.println(xml.asString());

            return xml.asString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        errFlag = true;
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                "<AppointmentList>\n" +
                "    <error>ERROR:Service Error</error>\n" +
                "</AppointmentList>";

    }

    /**
     * Business logic to get all labTests and build XML
     */
    public String getallLabTests(String url,String id) {

        try {
            XMLBuilder xml = XMLBuilder.create("LabTests");
            if(id.equals("0")) {
                objs = dbSingleton.db.getData("LabTest", "");
            }else{
                objs = dbSingleton.db.getData("LabTest","id='"+id+"'");
            }
            for (Object obj : objs){

                LabTest labTest = (LabTest) obj;
                xml = xml.importXMLBuilder(help.Helperxml(labTest,url));
            }
            System.out.println(xml.asString());

            return xml.asString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        errFlag = true;
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                "<AppointmentList>\n" +
                "    <error>ERROR:Service Error</error>\n" +
                "</AppointmentList>";

    }

    /**
     * Business logic to get all diagnosis and build XML
     */
    public String getallDiagnosis(String url,String id) {

        try {
            XMLBuilder xml = XMLBuilder.create("DiagnosisList");
            if(id.equals("0")) {
                objs = dbSingleton.db.getData("Diagnosis", "");
            }else{
                objs = dbSingleton.db.getData("Diagnosis","id='"+id+"'");
            }
            for (Object obj : objs){
                Diagnosis diagnosis = (Diagnosis) obj;
                xml = xml.importXMLBuilder(help.Helperxml(diagnosis,url));
            }
            System.out.println(xml.asString());

            return xml.asString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        errFlag = true;
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                "<AppointmentList>\n" +
                "    <error>ERROR:Service Error</error>\n" +
                "</AppointmentList>";

    }

    /**
     * Business logic to get all PSC and build XML
     */
    public String getallPSC(String url,String id) {

        try {
            XMLBuilder xml = XMLBuilder.create("PSCList");
            if(id.equals("0")) {
                objs = dbSingleton.db.getData("PSC", "");
            }else{
                objs = dbSingleton.db.getData("PSC","id='"+id+"'");
            }
            for (Object obj : objs){
                PSC psc = (PSC) obj;
                xml = xml.importXMLBuilder(help.Helperxml(psc,url));
            }
            System.out.println(xml.asString());

            return xml.asString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        errFlag = true;
        errCode = 1;
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                "<AppointmentList>\n" +
                "    <error>ERROR:Service Error</error>\n" +
                "</AppointmentList>";

    }

    /**
     * Business logic to create a new appointment and build the XML to post
     */
    public String postnewAppointments(String xml, String url) {
        try {
            XMLBuilder output = XMLBuilder.create("AppointmentList");
            if(help.validateXML(xml,help.getAptXSD())){
                help.parseXML(xml);
                if(help.checkTable("patient") && help.checkTable("physician") && help.checkTable("psc") && help.checkTable("phlebotomist"))
                {
                    if(help.chkavailAppointment()) {
                        List<AppointmentLabTest> labtestlist = new ArrayList<>();
                        help.getNewApptId();
                        Iterator it = help.getTestHashMap().entrySet().iterator();
                        do{
                            Map.Entry data = (Map.Entry) it.next();
                            if (help.checkTable("LabTest", data.getKey().toString()) && help.checkTable("diagnosis", data.getValue().toString())) {
                                AppointmentLabTest temp = new AppointmentLabTest(Integer.toString(help.getAppId()), data.getKey().toString(), data.getValue().toString());
                                temp.setDiagnosis(help.getDgobj());
                                temp.setLabTest(help.getLabobj());
                                labtestlist.add(temp);
                                continue;
                            } else {
                                errFlag = true;

                                output = output.element("error").t("Lab data is invalid").up();
                                return output.asString();
                            }
                        }while (it.hasNext());

                        Appointment appointment = new Appointment(Integer.toString(help.getAppId()), java.sql.Date.valueOf(help.getDate()), java.sql.Time.valueOf(LocalTime.parse(help.getApptTime())));
                        appointment.setPatientid(help.getPatobj());
                        appointment.setPhlebid(help.getPhlobj());
                        appointment.setPscid(help.getPscobj());
                        appointment.setAppointmentLabTestCollection(labtestlist);
                        if (dbSingleton.db.addData(appointment)) {
                            output = output.element("uri").text(url + "AptService/Appointments/" + help.getAppId());

                            return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+output.asString();

                        }
                    }else{
                        errFlag = true;

                        for(String err : help.getErrMsg()){
                            output = output.element("error").t(err).up();
                        }
                        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+output.asString();
                    }
                }
                else
                {
                    errFlag = true;

                    for(String err : help.getErrMsg()){
                        output = output.element("error").t(err).up();
                    }
                    return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+output.asString();
                }


            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        errFlag = true;


        return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                "<AppointmentList>\n" +
                "    <error>ERROR:Service Error</error>\n" +
                "</AppointmentList>";
    }


    /**
     * Business logic to update an existing appointment and build the XML to put
     */
    public String updateSpecificAppointments(String xml,String url, String id) {

        try{
            XMLBuilder output = XMLBuilder.create("AppointmentList");
            if (help.validateXML(xml,help.getUpaptXSD())) {
                help.parseXML(xml);
                if(help.checkTable("patient") && help.checkTable("physician") && help.checkTable("psc") && help.checkTable("phlebotomist"))
                {
                if(help.chkTime(help.getApptTime())) {
                    help.setAppId(id);
                    if (help.checkTable("apptID")) {
                        if (help.getDate() != null || !help.getDate().isEmpty()) {
                            help.getApptobj().setApptdate(java.sql.Date.valueOf(help.getDate()));
                        }
                        if (help.getApptTime() != null || !help.getApptTime().isEmpty()) {
                            help.getApptobj().setAppttime(java.sql.Time.valueOf(LocalTime.parse(help.getApptTime())));

                        }
                        if (help.checkTable("patient")) {
                            help.getApptobj().setPatientid(help.getPatobj());
                        }

                        if (help.checkTable("psc")) {
                            help.getApptobj().setPscid(help.getPscobj());
                        }
                        if (help.checkTable("phlebotomist")) {
                            help.getApptobj().setPhlebid(help.getPhlobj());
                        }
                        if (help.getTestHashMap().size() > 0) {
                            List<AppointmentLabTest> labtestlist = new ArrayList<>();
                            Iterator it = help.getTestHashMap().entrySet().iterator();
                            while (it.hasNext()) {
                                Map.Entry data = (Map.Entry) it.next();
                                if (help.checkTable("LabTest", data.getKey().toString()) && help.checkTable("diagnosis", data.getValue().toString())) {
                                    AppointmentLabTest temp = new AppointmentLabTest(Integer.toString(help.getAppId()), data.getKey().toString(), data.getValue().toString());
                                    temp.setDiagnosis(help.getDgobj());
                                    temp.setLabTest(help.getLabobj());
                                    labtestlist.add(temp);
                                    continue;
                                } else {
                                    errFlag = true;

                                    output = output.element("error").t("Lab data is invalid").up();
                                    return output.asString();
                                }
                            }
                            help.getApptobj().setAppointmentLabTestCollection((labtestlist));
                        }

                        if (dbSingleton.db.updateData(help.getApptobj())) {
                            output = output.element("uri").t(url + "AppointmentsAPI/Appointments/" + id);
                            return output.asString();
                        }

                    }
                }
                else{
                    errFlag = true;

                    for(String err : help.getErrMsg()){
                        output = output.element("error").t(err).up();
                    }
                    return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+output.asString();
                }
            }else{
                errFlag = true;

                for(String err : help.getErrMsg()){
                    output = output.element("error").t(err).up();
                }
                return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+output.asString();
                }
            }

                else
        {
            errFlag = true;

            for(String err : help.getErrMsg()){
                output = output.element("error").t(err).up();
            }
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+output.asString();
        }
            this.errFlag = true;

            for(String err : help.getErrMsg()){
                output = output.element("error").t(err).up();
            }
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+output.asString();
        }catch (Exception e){
            e.printStackTrace();
            this.errFlag=true;

            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><AppointmentList><error>Request cannot be processed</error></AppointmentList>";
        }
    }
}
