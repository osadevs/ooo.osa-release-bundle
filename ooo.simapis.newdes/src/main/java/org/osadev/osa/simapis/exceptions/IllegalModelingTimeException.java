package org.osadev.osa.simapis.exceptions;

public class IllegalModelingTimeException extends RuntimeException {

    private static final long serialVersionUID = 1091703170731L;

    public IllegalModelingTimeException(String format, Object value) {
        super(String.format(format, value));
    }
}