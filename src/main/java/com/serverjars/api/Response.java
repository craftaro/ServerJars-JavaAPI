package com.serverjars.api;

/**
 * @author Jacobtread
 * -
 * created on 7/10/20 at 1:54 AM
 */
public class Response {

    protected String status;
    private String errorTitle = null;
    private String errorMessage = null;

    /**
     * @return Whether or not the request encountered an error (this can be with the server or the client)
     */
    public boolean isSuccess() {
        return status.equals("success") && errorMessage == null && errorTitle == null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    protected void setError(String title, String message) {
        this.errorTitle = title;
        this.errorMessage = message;
    }

    protected void setStatus(String status) {
        this.status = status;
    }

}
