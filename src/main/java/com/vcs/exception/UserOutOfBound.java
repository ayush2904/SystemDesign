package com.vcs.exception;

import com.vcs.model.repositories.UserRepository;

public class UserOutOfBound extends  Exception{
    public UserOutOfBound(String e){
        super(e);
    }
}
