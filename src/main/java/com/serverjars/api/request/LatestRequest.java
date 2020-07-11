package com.serverjars.api.request;

import com.google.gson.JsonObject;
import com.serverjars.api.ServerJars;
import com.serverjars.api.response.LatestResponse;

/**
 * @author Jacobtread
 * -
 * created on 7/10/20 at 1:59 AM
 */
public class LatestRequest implements Request<LatestResponse> {

    public final String type;

    /**
     * Requests the latest jar of a type
     *
     * @param type The type of jar to find the latest of
     */
    public LatestRequest(String type) {
        this.type = type;
    }

    @Override
    public String url() {
        return "/fetchLatest/" + type;
    }

    @Override
    public void setData(LatestResponse response, JsonObject object) {
        JsonObject responseObject = object.getAsJsonObject("response");
        response.setLatestJar(ServerJars.parseDetails(responseObject));
    }

    @Override
    public LatestResponse send() {
        return ServerJars.sendRequest(LatestResponse.class, this);
    }

    @Override
    public String toString() {
        return "LatestRequest{" +
                "type='" + type + '\'' +
                '}';
    }
}
