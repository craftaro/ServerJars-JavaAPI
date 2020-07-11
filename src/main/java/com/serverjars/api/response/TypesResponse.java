package com.serverjars.api.response;

import com.serverjars.api.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jacobtread
 * -
 * created on 7/10/20 at 8:07 PM
 */
public class TypesResponse extends Response {

    private final List<String> requestedChildren = new ArrayList<>();
    private final Map<String, List<String>> allTypes = new HashMap<>();

    public void addChildren(String type) {
        requestedChildren.add(type);
    }

    public void addTypes(String main, List<String> children) {
        allTypes.put(main, children);
    }

    public List<String> getRequestedChildren() {
        return requestedChildren;
    }

    public Map<String, List<String>> getAllTypes() {
        return allTypes;
    }

    /**
     * Determine whether or not the response is for children
     *
     * @return Whether or not children were requested
     */
    public boolean isChildren() {
        return requestedChildren.size() > 0;
    }

}
