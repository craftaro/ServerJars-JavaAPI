package com.serverjars.api.request;

import com.google.gson.JsonObject;
import com.serverjars.api.Response;
import com.serverjars.api.ServerJars;

import java.io.File;
import java.nio.file.Path;

/**
 * @author Jacobtread
 * -
 * created on 7/10/20 at 8:40 PM
 */
public class JarRequest implements Request<Response> {

    private final String type;
    private final String version;
    private final Path output;

    /**
     * Request to download a jar of a specific type and version to the output file
     *
     * @param type The type of jar to find the latest of
     * @param version The version of the jar to download (null for latest)
     * @param output The output file to download to
     */
    public JarRequest(String type, String version, Path output) {
        this.type = type;
        this.version = version;
        this.output = output;
    }

    public JarRequest(String type, String version, File output) {
        this(type, version, output.toPath());
    }


    /**
     * Request to download the latest jar of a specific type to the output file
     *
     * @param type The type of jar to find the latest of
     * @param output The output file to download to
     */
    public JarRequest(String type, Path output) {
        this(type, null, output);
    }

    public JarRequest(String type, File output) {
        this(type, null, output.toPath());
    }

    @Override
    public String url() {
        return "/fetchJar/" + type + (version == null ? "" : "/" + version);
    }

    @Override
    public void setData(Response response, JsonObject object) {

    }

    @Override
    public Response send() {
        return ServerJars.sendRequest(Response.class, this);
    }

    /**
     * @return The output path
     */
    public Path getOutput() {
        return output;
    }
}
