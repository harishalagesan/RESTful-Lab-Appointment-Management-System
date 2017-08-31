package service;

import business.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/AptService")
public class AptService {
    // The Java method will process HTTP GET requests
    @Context
    private UriInfo context;
    private BusinessLayer blayer;

    /**
     * Creates a new instance of AppointmentService
     */
    public AptService() {
        blayer = new BusinessLayer();
    }

    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClickedMessage() {
        // Return some cliched textual content
        return "Puter I am home...... Server up and running";
    }

    /**
     * Get the Wadl for all services
     */
    @Path("/Services")
    @GET
    @Produces("application/xml")
    public Response wadl() {

        String url = this.context.getBaseUri() + "application.wadl";
        String doc = blayer.wadldoc(url);
        return this.responseGenerator(doc);
    }

    /**
     * Get all appointments
     */
    @Path("/Appointments")
    @GET
    @Produces("application/xml")
    public  Response getallAppointments(){

        String url = this.context.getBaseUri().toString();
        String doc = blayer.getallAppointments(url,"0");
        return this.responseGenerator(doc);
    }

    /**
     * Get all patient details
     */
    @Path("/PatientList")
    @GET
    @Produces("application/xml")
    public  Response getallPatients(){

        String url = this.context.getBaseUri().toString();
        String doc = blayer.getallPatients(url,"0");
        return this.responseGenerator(doc);
    }

    /**
     * Get all physician details
     */
    @Path("/PhysicianList")
    @GET
    @Produces("application/xml")
    public  Response getallPhysicians(){

        String url = this.context.getBaseUri().toString();
        String doc = blayer.getallPhysicians(url,"0");
        return this.responseGenerator(doc);
    }

    /**
     * Get all Phlebotomist details
     */
    @Path("/PhlebotomistList")
    @GET
    @Produces("application/xml")
    public  Response getallPhlebotomists(){

        String url = this.context.getBaseUri().toString();
        String doc = blayer.getallPhlebotomists(url,"0");
        return this.responseGenerator(doc);
    }

    /**
     * Get all LabTest details
     */
    @Path("/LabTestList")
    @GET
    @Produces("application/xml")
    public  Response getallLabTests(){

        String url = this.context.getBaseUri().toString();
        String doc = blayer.getallLabTests(url,"0");
        return this.responseGenerator(doc);
    }

    /**
     * Get all Diagnosis details
     */
    @Path("/DiagnosisList")
    @GET
    @Produces("application/xml")
    public  Response getallDiagnosis(){

        String url = this.context.getBaseUri().toString();
        String doc = blayer.getallDiagnosis(url,"0");
        return this.responseGenerator(doc);
    }

    /**
     * Get all PSC details
     */
    @Path("/PSCList")
    @GET
    @Produces("application/xml")
    public  Response getallPSC(){

        String url = this.context.getBaseUri().toString();
        String doc = blayer.getallPSC(url,"0");
        return this.responseGenerator(doc);
    }


    /**
     * Get individual appointments by providing appointment ID
     */
    @Path("/Appointments/{appointmentID}")
    @GET
    @Produces("application/xml")
    public  Response getSpecificAppointments(@PathParam("appointmentID") String id){

        String url = this.context.getBaseUri().toString();
        String doc = blayer.getallAppointments(url, id);
        return this.responseGenerator(doc);
    }

    /**
     * Create new Appointment
     */
    @Path("/Appointments")
    @POST
    @Produces("application/xml")
    @Consumes("application/xml")
    public  Response postnewAppointments(String xml){

        String url = this.context.getBaseUri().toString();
        try {

            xml = URLDecoder.decode(xml,"UTF-8");
            System.out.println(xml);
            String[] data = xml.split("xml=");
            if(data.length > 1){
                xml = data[1];
            }else{
                xml = data[0];
            }

            System.out.println(xml);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported Encoding Exception");
            String doc = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                    "<AppointmentList>\n" +
                    "    <error>ERROR:Encoding Error</error>\n" +
                    "</AppointmentList>";
            return this.responseGenerator(doc);
        }
        String doc = blayer.postnewAppointments(xml,url);
        return this.responseGenerator(doc);
    }

    /**
     * Edit and Update an existing appointment
     */
    @Path("/Appointments/{appointmentID}")
    @PUT
    @Produces("application/xml")
    @Consumes("application/xml")
    public  Response updateSpecificAppointments(@PathParam("appointmentID") String id, String xml){
        String url = this.context.getBaseUri().toString();

        try {

            xml = URLDecoder.decode(xml,"UTF-8");
            System.out.println(xml);
            String[] data = xml.split("xml=");
            if(data.length > 1){
                xml = data[1];
            }else{
                xml = data[0];
            }
            System.out.println(xml);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported Encoding Exception");
            String doc ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
                    "<AppointmentList>\n" +
                    "    <error>ERROR:Encoding Error</error>\n" +
                    "</AppointmentList>";
            return this.responseGenerator(doc);
        }
        String doc = blayer.updateSpecificAppointments(xml,url,id);
        return this.responseGenerator(doc);
    }


    private Response responseGenerator (String doc){

        return Response.status(Response.Status.OK).entity(doc).build();
    }



}