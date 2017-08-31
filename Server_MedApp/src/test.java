import business.BusinessLayer;

/**
 * Created by Harish ALagesan on 5/10/2017.
 */
public class test {
    public static void main(String[] args) {
        BusinessLayer b = new BusinessLayer();

        b.postnewAppointments("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?>\n" +
                "<appointment>\n" +
                "    <date>2016-12-30</date>\n" +
                "    <time>10:00</time>\n" +
                "    <patientId>220</patientId>\n" +
                "    <physicianId>20</physicianId>\n" +
                "    <pscId>520</pscId>\n" +
                "    <phlebotomistId>110</phlebotomistId>\n" +
                "    <labTests>\n" +
                "        <test id=\"86900\" dxcode=\"292.9\" />\n" +
                "        <test id=\"86609\" dxcode=\"307.3\" />\n" +
                "    </labTests>\n" +
                "</appointment>","jhgfds");
        System.out.println(b.isErrFlag());
    }
}
