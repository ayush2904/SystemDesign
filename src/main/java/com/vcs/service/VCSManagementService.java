package com.vcs.service;

import com.vcs.algorithm.DiffReport;
import com.vcs.algorithm.Diffchecker;
import com.vcs.exception.InvalidUser;
import com.vcs.exception.NotAValidVersion;
import com.vcs.model.entityVO.Application;
import com.vcs.model.entityVO.User;
import com.vcs.model.entityVO.Version;
import com.vcs.model.enums.RolloutStratagyEnum;
import com.vcs.model.enums.TaskType;
import com.vcs.model.repositories.ApplicationRepository;
import com.vcs.model.repositories.UserRepository;
import com.vcs.rollout.RolloutFactory;
import com.vcs.rollout.RolloutStratagy;
import com.vcs.util.VCSUtility;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Set;

public class VCSManagementService {

    private final ApplicationRepository applicationRepository ;
    private final UserRepository userRepository;

    private final RolloutFactory rolloutFactory;

    private final Diffchecker<Version> diffchecker;

    public VCSManagementService(ApplicationRepository applicationRepository, UserRepository userRepository, RolloutFactory rolloutFactory, Diffchecker<Version> diffchecker) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.rolloutFactory = rolloutFactory;
        this.diffchecker = diffchecker;
    }

    public void uploadNewVersion(Application application, Version version) {
        applicationRepository.add(application,version);
    }

    @SneakyThrows
    private void createUpdatePatch(Application application, Version fromVersion, Version toVersion, User user) {
        if(VCSUtility.isAdminUser(user)){
            DiffReport report = diffchecker.getDiffFromObject(fromVersion,toVersion);
        } else {
            throw new InvalidUser("only admin can do that");
        }
    }

    @SneakyThrows
   private void releaseVersion(Application application, Version version, User user, RolloutStratagyEnum rolloutStratagy) {
       if(VCSUtility.isAdminUser(user)){
          applicationRepository.add(application,version);
           List<User> users = rolloutFactory.getRolloutStratagy(rolloutStratagy).rolloutRelease(2);
           for(User deviceUser : users) {
               executeTask(TaskType.INSTALL,user,application,version);
           }
       } else {
           throw new InvalidUser("only admin can do that");
       }
   }

    private Version checkForUpdates(Application application, Version currentVersion) {
        Set<Version> versions =applicationRepository.get(application);
        for(Version version : versions) {
            if(currentVersion.getVersionNumber() < version.getVersionNumber())
                return version;
        }
        return null;
    }

    @SneakyThrows
    private void executeTask(TaskType taskType, User user, Application application, Version version) {
        if(TaskType.INSTALL.equals(taskType)) {
            if(VCSUtility.isValidVersion(user, version))
            VCSUtility.install(user,application,version);
            else
                throw  new NotAValidVersion("not a valid versoion");
        }

        if(TaskType.UPDATE.equals(taskType)) {
            Version version1 = checkForUpdates(application,version);
            if(version1!=null&&VCSUtility.isValidVersion(user, version1))
                VCSUtility.install(user,application,version);
            else
                throw  new NotAValidVersion("not a valid versoion");
        }
    }

}
