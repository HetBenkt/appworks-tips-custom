package com.appworkstips.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Via SOAP: http://192.168.56.115:8080/home/appworks_tips/services/
 * Invoking AppWorks Platform REST APIs requires BPMService.war, but is not available yet for 16.6
 * https://forums.opentext.com/forums/discussion/comment/932897#Comment_932897
 *
 */

public class Authentication {
    public static final Logger LOGGER = Logger.getLogger(Authentication.class.getSimpleName());
    /**
     * Make a RESTful call to OTDS passing in OTDS credentials

     * POST: http(s)://[OTDS-FQDN]:[OTDS-Port]/otds/rest/authentication/credentials
     * Body: {"userName": "[OTDS-Username]", "password": "[OTDS-Password]","targetResourceId":"[OTDS-AppWorks-ResourceId]"}

     * POST with data: http://192.168.56.115:8181/otdsws/rest/authentication/credentials
     * Body with data1: {"userName": "otdsdev@AppWorks Platform Partition", "password": "admin","targetResourceId":"5dae6e94-f382-47d5-ba5e-289455e72604"}
     * Body with data2: {"userName": "otadmin@otds.admin", "password": "admin","targetResourceId":"5dae6e94-f382-47d5-ba5e-289455e72604"}

     * @return OTDS Ticket
     */
    public static String getOTDSTicket() {
        try {
            URL url =  new URL("http://192.168.56.115:8181/otdsws/rest/authentication/credentials");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStream outputStream = connection.getOutputStream();
            String input = "{\"userName\": \"otdsdev@AppWorks Platform Partition\", \"password\": \"admin\",\"targetResourceId\":\"5dae6e94-f382-47d5-ba5e-289455e72604\"}";
            outputStream.write(input.getBytes());
            outputStream.flush();

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String output;
            while((output = reader.readLine()) != null) {
                return output;
            }
        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return "";
    }

    public static String getSAMLAssertionArtifact() {
        return "";
    }
}
