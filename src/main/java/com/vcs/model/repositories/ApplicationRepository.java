package com.vcs.model.repositories;

import com.vcs.model.entityVO.Application;
import com.vcs.model.entityVO.Version;
import com.vcs.storage.MapBasedStorageImpl;
import com.vcs.storage.Storage;
import lombok.Value;

import java.security.Key;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApplicationRepository {

   private final Storage<String, Set<Version>> storage= new MapBasedStorageImpl<>();

    public void add(Application application,Version version) {
        Set<Version> verSet = new HashSet<>();
        if(!storage.isKeyExists(application.getAppname())) {
            verSet.add(version);
            storage.set(application.getAppname(), verSet);
        } else {
            verSet = storage.get(application.getAppname());
            verSet.add(version);
        }
    }

    public void removeVersion(Version version, Application application) {
        Set<Version> verSet;
        if(storage.isKeyExists(application.getAppname())) {
            verSet = storage.get(application.getAppname());
            verSet.remove(version);
            storage.set(application.getAppname(), verSet);
        }
    }

    public Set<Version> get(Application application) {
        return storage.get(application.getAppname());
    }

    public void removeApplication(Application application) {
        storage.remove(application.getAppname());
    }
}
