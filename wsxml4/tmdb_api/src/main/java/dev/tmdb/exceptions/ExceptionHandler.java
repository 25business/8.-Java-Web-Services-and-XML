package dev.tmdb.exceptions;

public class ExceptionHandler {
    private int status;
    private String message;
    private String developer_message;

    public ExceptionHandler() {}

    public ExceptionHandler(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ExceptionHandler(int status, String message, String developer_message) {
        this.status = status;
        this.message = message;
        this.developer_message = developer_message;
    }

    public String toJSONString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"status\" : " + this.status + ",");
        sb.append("\"message\" : \"" + this.message + "\"");
        if(this.status == 500)
            sb.append(", \"developer_message\" : \"" +
                    this.developer_message + "\"");
        sb.append("}");
        return sb.toString();
    }
}
