package com.appworkstips.utils;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.appworkstips.services.documentum.utils.PropertiesUtils.getProperyValue;
import static java.util.Objects.requireNonNull;

/**
 * Via SOAP: http://192.168.56.115:8080/home/appworks_tips/services/
 * Invoking AppWorks Platform REST APIs requires BPMService.war, but is not available yet for 16.6
 * https://forums.opentext.com/forums/discussion/comment/932897#Comment_932897
 */
public class Authentication {
    private static final Logger LOGGER = Logger.getLogger(Authentication.class.getSimpleName());

    private Authentication() {
        //Hide the implicit public one
    }

    /**
     * Make a ReST call to OTDS passing in OTDS credentials to get an OTDS ticket
     * <p>
     * POST: http(s)://[OTDS-FQDN]:[OTDS-Port]/otds/rest/authentication/credentials
     * Body: {"userName": "[OTDS-Username]", "password": "[OTDS-Password]","targetResourceId":"[OTDS-AppWorks-ResourceId]"}
     * <p>
     * POST with data: http://192.168.56.115:8181/otdsws/rest/authentication/credentials
     * Body with data1: {"userName": "otdsdev@AppWorks Platform Partition", "password": "admin","targetResourceId":"5dae6e94-f382-47d5-ba5e-289455e72604"}
     * Body with data2: {"userName": "otadmin@otds.admin", "password": "admin","targetResourceId":"5dae6e94-f382-47d5-ba5e-289455e72604"}
     *
     * @return OTDS Ticket
     */
    static String getOTDSTicket() {
        try {
            HttpURLConnection connection = createConnection(getProperyValue("authentication_url"), "POST", "application/json");

            if (connection == null) {
                return "";
            } else {
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(requireNonNull(getProperyValue("input_json")).getBytes());

                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                JSONObject jsonObject = new JSONObject(reader.readLine());
                log(jsonObject);

                outputStream.close();
                inputStream.close();
                inputStreamReader.close();
                reader.close();
                connection.disconnect();

                return jsonObject.getString("ticket");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return "";
    }

    private static void log(JSONObject jsonObject) {
        jsonObject.keySet().forEach(keyStr -> {
            Object keyValue = jsonObject.get(keyStr);
            LOGGER.info(String.format("%s == %s", keyStr, keyValue));
        });
    }

    public static HttpURLConnection createConnection(String urlString, String method, String contentType) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-Type", contentType);
            return connection;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }
}
