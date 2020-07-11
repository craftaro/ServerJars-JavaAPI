package com.serverjars.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.serverjars.api.request.JarRequest;
import com.serverjars.api.request.Request;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * @author Jacobtread
 * -
 * created on 7/9/20 at 10:33 PM
 */
public class ServerJars {

    private static final Gson GSON = new GsonBuilder().serializeNulls().create();

    /**
     * Send a request to ServerJars
     *
     * @param clazz The class of the response
     * @param request The request to be sent
     * @param <T> The response type
     * @return A response object or null if the class couldn't be instantiated
     */
    public static <T extends Response> T sendRequest(Class<T> clazz, Request<T> request) {
        T response;
        try {
            response = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("Unable to create response instance!");
            return null;
        }
        URL url;
        BufferedInputStream inputStream = null;
        BufferedReader bufferedReader = null;
        HttpsURLConnection conn = null;
        try {
            url = new URL("https://serverjars.com/api" + request.url());
            conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            inputStream = new BufferedInputStream(conn.getInputStream());
            if (conn.getResponseCode() == 200) {
                if (request instanceof JarRequest) {
                    Files.copy(inputStream, ((JarRequest) request).getOutput(), StandardCopyOption.REPLACE_EXISTING);
                    response.setStatus("success");
                } else {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    JsonObject object = GSON.fromJson(bufferedReader, JsonObject.class);
                    boolean isError = false;
                    String errorTitle = "";
                    String errorMessage = "";
                    if (object.has("status")) {
                        String status = object.getAsJsonPrimitive("status").getAsString();
                        if (status.equals("error")) {
                            isError = true;
                            JsonObject errorObject = object.getAsJsonObject("error");
                            errorTitle = errorObject.getAsJsonPrimitive("title").getAsString();
                            errorMessage = errorObject.getAsJsonPrimitive("message").getAsString();
                        }
                        response.setStatus(status);
                    }
                    if (isError) {
                        response.setError(errorTitle, errorMessage);
                    } else {
                        request.setData(response, object);
                    }
                }
            } else {
                checkErrorStream(response, conn);
            }
        } catch (FileNotFoundException e) {
            if (conn != null) {
                checkErrorStream(response, conn);
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.setError(e.getClass().getSimpleName(), e.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ignored) {
            }
        }
        return response;
    }

    private static void checkErrorStream(Response response, HttpsURLConnection conn) {
        try {
            BufferedInputStream errorStream = new BufferedInputStream(conn.getErrorStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream));
            JsonObject object = GSON.fromJson(bufferedReader, JsonObject.class);
            if (object != null) {
                JsonObject errorObject = object.getAsJsonObject("error");
                String errorTitle = errorObject.getAsJsonPrimitive("title").getAsString();
                String errorMessage = errorObject.getAsJsonPrimitive("message").getAsString();
                response.setStatus("error");
                response.setError(errorTitle, errorMessage);
            }
            errorStream.close();
            bufferedReader.close();
        } catch (IOException ignored) {
        }
    }


    /**
     * Parses jar details object from a json object
     *
     * @param object The object to parse the details from
     * @return The parsed jar details
     */
    public static JarDetails parseDetails(JsonObject object) {
        String version = "unknown";
        String file = "unknown";
        String md5 = "";
        int build = -1;
        if (object.has("version")) {
            version = object.getAsJsonPrimitive("version").getAsString();
        }
        if (object.has("file")) {
            file = object.getAsJsonPrimitive("file").getAsString();
        }
        if (object.has("md5")) {
            md5 = object.getAsJsonPrimitive("md5").getAsString();
        }
        if (object.has("built")) {
            build = object.getAsJsonPrimitive("built").getAsInt();
        }
        return new JarDetails(version, file, md5, build);
    }

}
