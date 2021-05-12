package com.serverjars.api.request;

import com.google.gson.JsonObject;
import com.serverjars.api.Response;

/**
 * @author Jacobtread
 * -
 * created on 7/10/20 at 1:57 AM
 */
public interface Request<R extends Response> {

    /**
     * @return The url of the request
     */
    String url();

    void setData(R response, JsonObject object);

    R send();

}
