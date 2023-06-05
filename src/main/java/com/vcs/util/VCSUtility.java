package com.vcs.util;

import com.vcs.model.entityVO.Application;
import com.vcs.model.entityVO.User;
import com.vcs.model.entityVO.Version;
import com.vcs.model.enums.UserType;

public class VCSUtility {
    private VCSUtility(){}

    public static void install(User user, Application application, Version version) {
        System.out.println("application installed");
    }

    public static boolean isValidVersion(User user, Version version) {
        if(user.getUserType().equals(UserType.DEVICE_USER) && user.getDevice().equals(version.getSupportedOS()))
           return true;
        else
            return false;
    }

    public static boolean isAdminUser(User user) {
        return user.getUserType().equals(UserType.ADMIN_USER);
    }

}
