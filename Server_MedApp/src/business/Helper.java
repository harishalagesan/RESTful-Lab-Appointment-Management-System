package business;

import DB.DBSingleton;
import com.jamesmurty.utils.XMLBuilder;
import components.data.*;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Shiva on 5/10/2017.
 */
public class Helper {

    private boolean errFlag=false;
    private int errCode;
    private ArrayList<String> errMsg;
    private String date;
    private String apptTime;
    private String patientId;
    private String physicianId;
    private String pscId;
    private String out;
    private String phlebotomistId;
    private HashMap<String,String> testHashMap;
    private List<Object> objs;
    LocalTime tm;

    private Appointment apptobj;
    private Patient patobj;
    private Physician phyobj;
    private PSC pscobj;
    private Phlebotomist phlobj;
    private LabTest labobj;
    private Diagnosis dgobj;



    private DBSingleton dbSingleton;
    /**
     * XSD to add appointment
     */
    private String aptXSD = "<xs:schema attributeFormDefault=\"unqualified\" elementFormDefault=\"qualified\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n" +
            "  <xs:element name=\"appointment\">\n" +
            "    <xs:complexType>\n" +
            "      <xs:sequence>\n" +
            "        <xs:element type=\"xs:date\" name=\"date\"/>\n" +
            "        <xs:element type=\"xs:string\" name=\"time\"/>\n" +
            "        <xs:element type=\"xs:short\" name=\"patientId\"/>\n" +
            "        <xs:element type=\"xs:byte\" name=\"physicianId\"/>\n" +
            "        <xs:element type=\"xs:short\" name=\"pscId\"/>\n" +
            "        <xs:element type=\"xs:byte\" name=\"phlebotomistId\"/>\n" +
            "        <xs:element name=\"labTests\">\n" +
            "          <xs:complexType>\n" +
            "            <xs:sequence>\n" +
            "              <xs:element name=\"test\" maxOccurs=\"unbounded\" minOccurs=\"0\">\n" +
            "                <xs:complexType>\n" +
            "                  <xs:simpleContent>\n" +
            "                    <xs:extension base=\"xs:string\">\n" +
            "                      <xs:attribute type=\"xs:int\" name=\"id\" use=\"optional\"/>\n" +
            "                      <xs:attribute type=\"xs:float\" name=\"dxcode\" use=\"optional\"/>\n" +
            "                    </xs:extension>\n" +
            "                  </xs:simpleContent>\n" +
            "                </xs:complexType>\n" +
            "              </xs:element>\n" +
            "            </xs:sequence>\n" +
            "          </xs:complexType>\n" +
            "        </xs:element>\n" +
            "      </xs:sequence>\n" +
            "    </xs:complexType>\n" +
            "  </xs:element>\n" +
            "</xs:schema>";


    /**
     * XSD to update appointment
     */
    private String upaptXSD = "<xs:schema attributeFormDefault=\"unqualified\" elementFormDefault=\"qualified\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n"+
            "  <xs:element name=\"appointment\">\n"+
            "    <xs:complexType>\n"+
            "      <xs:sequence>\n"+
            "        <xs:element type=\"xs:date\" name=\"date\" minOccurs=\"0\" maxOccurs=\"1\"/>\n"+
            "        <xs:element type=\"xs:string\" name=\"time\" minOccurs=\"0\" maxOccurs=\"1\"/>\n"+
            "        <xs:element type=\"xs:short\" name=\"patientId\" minOccurs=\"0\" maxOccurs=\"1\"/>\n"+
            "        <xs:element type=\"xs:byte\" name=\"physicianId\" minOccurs=\"0\" maxOccurs=\"1\"/>\n"+
            "        <xs:element type=\"xs:short\" name=\"pscId\" minOccurs=\"0\" maxOccurs=\"1\"/>\n"+
            "        <xs:element type=\"xs:byte\" name=\"phlebotomistId\" minOccurs=\"0\" maxOccurs=\"1\"/>\n"+
            "        <xs:element name=\"labTests\" minOccurs=\"0\" maxOccurs=\"1\">\n"+
            "          <xs:complexType>\n"+
            "            <xs:sequence>\n"+
            "              <xs:element name=\"test\" maxOccurs=\"unbounded\" minOccurs=\"0\">\n"+
            "                <xs:complexType>\n"+
            "                  <xs:simpleContent>\n"+
            "                    <xs:extension base=\"xs:string\">\n"+
            "                      <xs:attribute type=\"xs:int\" name=\"id\" use=\"required\"/>\n"+
            "                      <xs:attribute type=\"xs:float\" name=\"dxcode\" use=\"required\"/>\n"+
            "                    </xs:extension>\n"+
            "                  </xs:simpleContent>\n"+
            "                </xs:complexType>\n"+
            "              </xs:element>\n"+
            "            </xs:sequence>\n"+
            "          </xs:complexType>\n"+
            "        </xs:element>\n"+
            "      </xs:sequence>\n"+
            "     </xs:complexType>\n"+
            "    </xs:element>\n" +
            "  </xs:schema>";


    private int appId;


    public Helper(){

        dbSingleton = DBSingleton.getInstance();
        errMsg = new ArrayList<>();
        testHashMap =new HashMap<>();
        objs = new ArrayList<>();
    }

    /**
     * XML Validator
     */
    public boolean validateXML(String xml,String xsd){
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(new InputStreamReader(IOUtils.toInputStream(xsd,"UTF-8"))));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new InputStreamReader(IOUtils.toInputStream(xml,"UTF-8"))));
        } catch (Exception e) {
            e.printStackTrace();
            this.errFlag = true;
            this.errMsg.add("XML doc invalid");
            return false;
        }
        return true;
    }

    /**
     * XML Parser
     */
    public void parseXML(String xml) {
        try {
            DocumentBuilderFactory bdfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = bdfactory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new InputStreamReader(IOUtils.toInputStream(xml,"UTF-8"))));
            NodeList nList = doc.getElementsByTagName("appointment");
            NodeList nChildNodes = nList.item(0).getChildNodes();
            for(int i=0;i<nChildNodes.getLength();i++) {
                if (nChildNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nChildNodes.item(i);
                    if (eElement.getTagName() == "date") {
                        date = eElement.getTextContent();
                    }
                    if (eElement.getTagName() == "time") {
                        apptTime = eElement.getTextContent();
                    }
                    if (eElement.getTagName() == "apptID") {
                        appId =Integer.parseInt(eElement.getTextContent());
                    }
                    if (eElement.getTagName() == "patientId") {
                        patientId = eElement.getTextContent();
                    }
                    if (eElement.getTagName() == "physicianId") {
                        physicianId = eElement.getTextContent();
                    }
                    if (eElement.getTagName() == "pscId") {
                        pscId = eElement.getTextContent();
                    }
                    if (eElement.getTagName() == "phlebotomistId") {
                        phlebotomistId = eElement.getTextContent();
                    }
                    if (eElement.getTagName() == "labTests") {
                        for (int j = 0; j < eElement.getElementsByTagName("test").getLength(); j++) {
                            String id = eElement.getElementsByTagName("test").item(j).getAttributes().getNamedItem("id").getNodeValue();
                            String dxcode = eElement.getElementsByTagName("test").item(j).getAttributes().getNamedItem("dxcode").getNodeValue();
                            testHashMap.put(id, dxcode);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.errFlag=true;
            this.errMsg.add("Error in XML parsing "+ e);
        }
    }

    /**
     * XML Builder
     */
    public XMLBuilder Helperxml(Appointment apt, String url) throws Exception {

        XMLBuilder xml = XMLBuilder.create("Appointment")
                .attribute("date",apt.getApptdate().toString()).attribute("id", apt.getId()).attribute("time",apt.getAppttime().toString())
                .element("uri").text(url+"AptService/Appointments/"+ apt.getId()).up()
                .element("patient").attribute("id",apt.getPatientid().getId())
                .element("uri").up()
                .element("name").text(apt.getPatientid().getName()).up()
                .element("address").text(apt.getPatientid().getAddress()).up()
                .element("insurance").text(String.valueOf(apt.getPatientid().getInsurance())).up()
                .element("dob").text(String.valueOf(apt.getPatientid().getDateofbirth())).up().up()

                .element("phlebotomist").attribute("id",apt.getPhlebid().getId())
                .element("uri").up()
                .element("name").text(apt.getPhlebid().getName()).up().up()

                .element("psc").attribute("id",apt.getPscid().getId())
                .element("uri").up()
                .element("name").text(apt.getPscid().getName()).up().up()
                .element("allLabTests");

        for(AppointmentLabTest labTest : apt.getAppointmentLabTestCollection())
        {
            xml.element("appointmentLabTest")
                    .attribute("appointmentId",labTest.getAppointmentLabTestPK().getApptid())
                    .attribute("dxcode", labTest.getAppointmentLabTestPK().getDxcode()).attribute("labTestId",labTest.getAppointmentLabTestPK().getLabtestid())
                    .element("uri").up();
        }



        return xml;
    }

    /**
     * XML builder for patient only
     */
    public XMLBuilder Helperxml(Patient patient, String url) throws Exception {

        XMLBuilder xml = XMLBuilder.create("Patient");
                xml.element("id").text(patient.getId());
                xml.element("name").text(patient.getName());
        return xml;
    }

    /**
     * XML builder for physician only
     */
    public XMLBuilder Helperxml(Physician physician, String url) throws Exception {

        XMLBuilder xml = XMLBuilder.create("Physician");
        xml.element("id").text(physician.getId());
        xml.element("name").text(physician.getName());
        return xml;
    }

    /**
     * XML builder for Phlebotomist only
     */
    public XMLBuilder Helperxml(Phlebotomist phlebotomist, String url) throws Exception {

        XMLBuilder xml = XMLBuilder.create("Phlebotomist");
        xml.element("id").text(phlebotomist.getId());
        xml.element("name").text(phlebotomist.getName());
        return xml;
    }

    /**
     * XML builder for labTest only
     */
    public XMLBuilder Helperxml(LabTest labTest, String url) throws Exception {

        XMLBuilder xml = XMLBuilder.create("LabTest");
        xml.element("id").text(labTest.getId());
        xml.element("name").text(labTest.getName());
        return xml;
    }

    /**
     * XML builder for Diagnosis only
     */
    public XMLBuilder Helperxml(Diagnosis diagnosis, String url) throws Exception {

        XMLBuilder xml = XMLBuilder.create("Diagnosis");
        xml.element("id").text(diagnosis.getCode());
        xml.element("name").text(diagnosis.getName());
        return xml;
    }

    /**
     * XML builder for PSC only
     */
    public XMLBuilder Helperxml(PSC psc, String url) throws Exception {

        XMLBuilder xml = XMLBuilder.create("PSC");
        xml.element("id").text(psc.getId());
        xml.element("name").text(psc.getName());
        return xml;
    }

    /**
     * Checks if each element given to the xml is a valid element
     */
    public boolean checkTable(String table){
        objs = new ArrayList<>();
        if(table.equals("patient")) {
            objs = dbSingleton.db.getData("Patient", "id='" + patientId + "'");
            if (objs == null | objs.isEmpty()) {
                errFlag = true;
                errMsg.add("Patient ID is invalid");
                return false;
            } else {
                patobj = (Patient) objs.get(0);
            }
        }
        else if(table.equals("apptID")) {
            objs = dbSingleton.db.getData("Appointment", "id='" + appId + "'");
            if (objs == null | objs.isEmpty()) {
                errFlag = true;
                errMsg.add("Appointment ID is invalid");
                return false;
            } else {
                apptobj = (Appointment) objs.get(0);
            }
        }

        else if(table.equals("physician")) {
            objs = dbSingleton.db.getData("Physician", "id='" + physicianId + "'");
            if (objs == null | objs.isEmpty()) {
                errFlag = true;
                errMsg.add("Physician ID is invalid");
                return false;
            } else {
                phyobj = (Physician) objs.get(0);
            }
        }else if(table.equals("psc")) {
            objs = dbSingleton.db.getData("PSC", "id='" + pscId + "'");
            if (objs == null | objs.isEmpty()) {
                errFlag = true;
                errMsg.add("PSC ID is invalid");
                return false;
            } else {
                pscobj = (PSC) objs.get(0);

            }
        }else if(table.equals("phlebotomist")){
            objs = dbSingleton.db.getData("Phlebotomist", "id='" + phlebotomistId + "'");
            if (objs == null | objs.isEmpty()) {
                errFlag = true;
                errMsg.add("Phlebotomist ID is invalid");
                return false;
            } else {
                phlobj = (Phlebotomist) objs.get(0);

            }
        }

        return true;
    }

    /**
     * Checks if each element given to the xml is a valid element - separate check for arraylists
     */
    public boolean checkTable(String table, String id){

        objs = new ArrayList<>();
        if(table =="labTest") {
            objs = dbSingleton.db.getData("LabTest", "id='" + id + "'");
            if (objs == null | objs.isEmpty()) {
                errFlag = true;
                errMsg.add("LabTest Code is invalid");
                return false;
            } else {
                labobj = (LabTest) objs.get(0);
            }
        }

        if(table =="diagnosis") {
            objs = dbSingleton.db.getData("Diagnosis", "id='" + id + "'");
            if (objs == null | objs.isEmpty()) {
                errFlag = true;
                errMsg.add("Diagnosis ID is invalid");
                return false;
            } else {
                dgobj = (Diagnosis) objs.get(0);
            }
        }

        return true;
    }

    /**
     * Checks if the added or edited appointment is available as the chosen time and if there is clash in setting the time with each appointment
     */
    public boolean chkavailAppointment(){
        if(!chkTime(apptTime) || !chkTime(tm.minusMinutes(15).toString()) || !chkTime(tm.plusMinutes(15).toString())){
            this.errFlag=true;
            this.errMsg.add("Appointments can be scheduled only from 8am to 5pm" + '\n');
            return false;
        }
        List<Object> objs;
        objs = dbSingleton.db.getData("Appointment",
                "apptdate='"+date+"' AND pscId='"+pscId+"' AND phlebId='"+phlebotomistId+"' AND appttime between '"+tm.minusMinutes(15).toString()+"' AND '"+tm.plusMinutes(15).toString()+"' ");
        if(objs.isEmpty() | objs == null){
            return true;
        }else{
            this.errFlag=true;
            this.errMsg.add("The Requested time is unavailable\n");
            return false;
        }
    }

    /**
     * Checks if the time of appointment falls between the hours of work
     */
    public boolean chkTime(String apptTime) {
        String tokens[] = apptTime.split(":");
        tm = LocalTime.of(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]));
        LocalTime startTime = LocalTime.of(8,00);
        LocalTime endTime = LocalTime.of(16,59);
        if(tm.isAfter(startTime) && tm.isBefore(endTime)){
            return true;
        }
        this.errFlag=true;
        this.errMsg.add("Appointment cannot be scheduled as Appointment is outside working hours" + '\n');
        return false;
    }

    public void getNewApptId(){
        objs = new ArrayList<>();
        objs = dbSingleton.db.getData("Appointment"," id = (SELECT MAX(id) FROM components.data.Appointment)");
        appId = Integer.parseInt(((Appointment) objs.get(0)).getId())+1;
    }


    public String getAptXSD() {
        return aptXSD;
    }

    public String getDate() {
        return date;
    }

    public String getApptTime() {
        return apptTime;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPhysicianId() {
        return physicianId;
    }

    public String getPscId() {
        return pscId;
    }

    public String getPhlebotomistId() {
        return phlebotomistId;
    }

    public ArrayList<String> getErrMsg(){
        return errMsg;
    }

    public boolean getErrFlag(){return errFlag;}

    public HashMap<String,String> getTestHashMap(){
        return this.testHashMap;
    }

    public int getAppId() {
        return appId;
    }

    public Patient getPatobj() {
        return patobj;
    }

    public Physician getPhyobj() {
        return phyobj;
    }

    public PSC getPscobj() {
        return pscobj;
    }

    public Phlebotomist getPhlobj() {
        return phlobj;
    }

    public LabTest getLabobj() {
        return labobj;
    }

    public Diagnosis getDgobj() {
        return dgobj;
    }


    public String getUpaptXSD() {
        return upaptXSD;
    }

    public Appointment getApptobj() {
        return apptobj;
    }

    public void setAppId(String appId) {
        this.appId = Integer.parseInt(appId);
    }


}


