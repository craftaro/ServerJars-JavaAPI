package com.serverjars.api.response;

import com.serverjars.api.JarDetails;
import com.serverjars.api.Response;

/**
 * @author Jacobtread
 * -
 * created on 7/10/20 at 2:00 AM
 */
public class LatestResponse extends Response {

    public JarDetails latestJar;

    public void setLatestJar(JarDetails latestJar) {
        this.latestJar = latestJar;
    }

    public JarDetails getLatestJar() {
        return latestJar;
    }

    @Override
    public String toString() {
        return "LatestResponse{" +
                "latestJar=" + latestJar +
                '}';
    }
}
