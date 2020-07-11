package com.serverjars.api.response;

import com.serverjars.api.JarDetails;
import com.serverjars.api.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacobtread
 * -
 * created on 7/10/20 at 7:49 PM
 */
public class AllResponse extends Response {

    private final List<JarDetails> jars = new ArrayList<>();

    public void add(JarDetails jarDetails) {
        jars.add(jarDetails);
    }

    public List<JarDetails> getJars() {
        return jars;
    }

    @Override
    public String toString() {
        return "AllResponse{" +
                "jars=" + jars +
                '}';
    }
}
