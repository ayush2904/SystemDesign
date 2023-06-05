package com.vcs.exception;

public class NotAValidVersion extends Exception{
    public NotAValidVersion(String e) {
        super(e);
    }
}
