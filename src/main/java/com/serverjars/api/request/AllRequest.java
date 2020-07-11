package com.serverjars.api.request;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.serverjars.api.ServerJars;
import com.serverjars.api.response.AllResponse;

/**
 * @author Jacobtread
 * -
 * created on 7/10/20 at 1:59 AM
 */
public class AllRequest implements Request<AllResponse> {

    public final String type;
    public final int max;

    /**
     * Requests all jars of a type with a maximum amount of jars
     *
     * @param type The type of jar to find all of
     * @param max  The maximum amount of jars to find (if -1 then all jars will be returned)
     */
    public AllRequest(String type, int max) {
        this.type = type;
        this.max = max;
    }

    /**
     * Requests all jars of a type
     *
     * @param type The type of jar to find all of
     */
    public AllRequest(String type) {
        this(type, -1);
    }

    @Override
    public String url() {
        return "/fetchAll/" + type + (max > 0 ? "/" + max : "");
    }

    @Override
    public void setData(AllResponse response, JsonObject object) {
        JsonArray responseObject = object.getAsJsonArray("response");
        for (JsonElement jsonElement : responseObject) {
            if (jsonElement.isJsonObject()) {
                JsonObject jarObject = jsonElement.getAsJsonObject();
                response.add(ServerJars.parseDetails(jarObject));
            }
        }
    }

    @Override
    public AllResponse send() {
        return ServerJars.sendRequest(AllResponse.class, this);
    }

    @Override
    public String toString() {
        return "AllRequest{" +
                "type='" + type + '\'' +
                ", max=" + max +
                '}';
    }
}
