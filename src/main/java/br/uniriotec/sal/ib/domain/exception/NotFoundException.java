package br.uniriotec.sal.ib.domain.exception;

import java.util.Map;

public class NotFoundException extends RuntimeException {
    private Map<String, Object> error;

    public NotFoundException(Map<String, Object> error) {
        super("Not Found Exception");
        this.error = error;
    }

    public NotFoundException(String msg) {
        super(msg);
    }


    public Map<String, Object> getError() {
        return error;
    }

    public void setError(Map<String, Object> error) {
        this.error = error;
    }
}
