package com.vcs;

import com.vcs.algorithm.AppDiffChecker;
import com.vcs.algorithm.DiffReport;
import com.vcs.algorithm.Diffchecker;
import com.vcs.model.entityVO.Application;
import com.vcs.model.entityVO.User;
import com.vcs.model.entityVO.Version;
import com.vcs.model.enums.OS;
import com.vcs.model.enums.UserType;
import com.vcs.model.repositories.ApplicationRepository;
import com.vcs.model.repositories.UserRepository;
import com.vcs.rollout.RolloutFactory;
import com.vcs.service.VCSManagementService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        ApplicationRepository applicationRepository = new ApplicationRepository();
        UserRepository userRepository = new UserRepository();
        RolloutFactory rolloutFactory = new RolloutFactory();
        Diffchecker<Version> diffchecker = new AppDiffChecker<>();

        VCSManagementService vcsManagementService= new VCSManagementService(applicationRepository,userRepository,rolloutFactory,diffchecker);

        Version versionA1 = new Version(OS.ANDROID,1);
        Version versionO1 = new Version(OS.APPLE,1);
        Version versionA2 = new Version(OS.ANDROID,2);
        Version versionO2 = new Version(OS.APPLE,2);

        Application application = new Application("phonepe",versionA1);

        User ayush = new User("Ayush", UserType.ADMIN_USER,OS.ANDROID);
        User admin = new User("Admin",UserType.ADMIN_USER,OS.ANDROID);
        User jhon = new User("John",UserType.DEVICE_USER,OS.APPLE);

        applicationRepository.add(application,versionA2);
        applicationRepository.add(application,versionA1);

        userRepository.addUser(ayush);
        userRepository.addUser(admin);
        userRepository.addUser(jhon);

        System.out.println(userRepository.getRandomNUsers(2));
        System.out.println(userRepository.getSize());
        System.out.println(userRepository);

        System.out.println(applicationRepository);

        System.out.println(diffchecker.getDiffFromObject(versionA1,versionA2).getReport());

    }
}