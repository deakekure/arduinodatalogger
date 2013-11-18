/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta;

/**
 *
 * @author zakyalvan
 */
public class ApplicationException extends RuntimeException {
    public ApplicationException() {
        super();
    }
    public ApplicationException(String message) {
        super(message);
    }
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
