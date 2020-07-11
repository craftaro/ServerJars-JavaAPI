package com.serverjars.api.request;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.serverjars.api.ServerJars;
import com.serverjars.api.response.TypesResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacobtread
 * -
 * created on 7/10/20 at 8:07 PM
 */
public class TypesRequest implements Request<TypesResponse> {

    public final String type;

    /**
     * Requests types of jars under a category (bedrock, proxies, servers, etc..)
     *
     * @param type A main category type can be null for all
     */
    public TypesRequest(String type) {
        this.type = type;
    }

    /**
     * Requests all types of jars with any categories
     */
    public TypesRequest() {
        this(null);
    }

    @Override
    public String url() {
        return "/fetchTypes" + (type == null ? "" : "/" + type);
    }

    @Override
    public void setData(TypesResponse response, JsonObject object) {
        JsonElement responseElement = object.get("response");
        if (responseElement.isJsonArray()) {
            JsonArray responseArray = responseElement.getAsJsonArray();
            for (JsonElement jsonElement : responseArray) {
                if (jsonElement.isJsonPrimitive()) {
                    response.addChildren(jsonElement.getAsJsonPrimitive().getAsString());
                }
            }
        } else {
            JsonObject responseObject = responseElement.getAsJsonObject();
            for (String key : responseObject.keySet()) {
                JsonElement element = responseObject.get(key);
                List<String> types = new ArrayList<>();
                if (element.isJsonArray()) {
                    JsonArray typeArray = element.getAsJsonArray();
                    for (JsonElement jsonElement : typeArray) {
                        if (jsonElement.isJsonPrimitive()) {
                            types.add(jsonElement.getAsJsonPrimitive().getAsString());
                        }
                    }
                }
                response.addTypes(key, types);
            }

        }
    }

    @Override
    public TypesResponse send() {
        return ServerJars.sendRequest(TypesResponse.class, this);
    }

    @Override
    public String toString() {
        return "TypesRequest{" +
                "type='" + type + '\'' +
                '}';
    }
}
