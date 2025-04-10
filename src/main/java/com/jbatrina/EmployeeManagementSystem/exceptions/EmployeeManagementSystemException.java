package com.jbatrina.EmployeeManagementSystem.exceptions;

import org.springframework.http.HttpStatus;

public class EmployeeManagementSystemException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private int id;
    private String contextMessage;
    private Object contextObject;
    private HttpStatus httpStatus;

    protected EmployeeManagementSystemException(String baseMessage, int id, String contextMessage, Object contextObject, HttpStatus httpStatus) {
        super(baseMessage);
        this.id = id;
        this.contextMessage = contextMessage;
        this.contextObject = contextObject;
        this.httpStatus = httpStatus;
    }

    protected EmployeeManagementSystemException(String baseMessage, int id, String contextMessage, Object contextObject) {
        this(baseMessage, id, contextMessage, contextObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public EmployeeManagementSystemException(int id, String contextMessage, Object contextObject) {
        this("An EmployeeManagementSystem error has occurred.", id, contextMessage, contextObject);
    }

    public EmployeeManagementSystemException(int id, String contextMessage) {
        this(id, contextMessage, null);
    }

    public EmployeeManagementSystemException(int id) {
        this(id, "", null);
    }

    // Getters and Setters
    // NOTE: for Setters, we return `this` to enable chaining (like bulilder pattern)
    @Override
    public String getMessage() {
        return String.format("%s%s", super.getMessage(), getExtraInfoMessage(id, contextMessage));
    }

    protected String getExtraInfoMessage(int id, String message) {
        return String.format("(Object ID: %d%s)", id,
            (message.isBlank()) ? "" : String.format("\n\t%s)", message)
        );
    }

    public int getId() {
        return id;
    }

    public EmployeeManagementSystemException setId(int id) {
        this.id = id;
        return this;
    }

    public EmployeeManagementSystemException setContextMessage(String contextMessage) {
        this.contextMessage = contextMessage;
        return this;
    }

    public String getContextMessage() {
        return contextMessage;
    }

    public Object getContextObject() {
        return contextObject;
    }

    public EmployeeManagementSystemException setContextObject(Object contextObject) {
        this.contextObject = contextObject;
        return this;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public EmployeeManagementSystemException setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }
}
