package com.example.phoebe.youtiao.commmon.exception;

public final class InlineException extends RuntimeException {
    private static final long serialVersionUID = -6250591501226780557L;

    private InlineException(Throwable e) {
        super(e);
    }

    public static RuntimeException wrap(Throwable e) {
        if (e instanceof InlineException) {
            e = e.getCause();
        } else if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }
        return new RuntimeException(e);
    }

}
