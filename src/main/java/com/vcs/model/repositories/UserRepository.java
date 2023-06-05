package com.vcs.model.repositories;

import com.vcs.exception.UserOutOfBound;
import com.vcs.model.entityVO.User;
import com.vcs.model.entityVO.Version;
import com.vcs.storage.MapBasedStorageImpl;
import com.vcs.storage.Storage;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserRepository {
    private final Storage<String, User> storage= new MapBasedStorageImpl<>();

    public void addUser(User user) {
        storage.set(user.getUserName(),user);
    }

    public void removeUser(User user) {
        storage.remove(user.getUserName());
    }

    public User getUser(User user) {
        return storage.get(user.getUserName());
    }

    @SneakyThrows
    public List<User> getRandomNUsers(int n) {
        List<User> users = new ArrayList<>();
        Set<String> userKeys = storage.keys();
        if(n<userKeys.size()){
        for(String key: userKeys) {
            users.add(storage.get(key));
         }
        } else {
            throw new UserOutOfBound("there are only "+userKeys.size()+ "users");
        }
        return users.subList(0,n);
    }

    public int getSize() {
        return storage.keys().size();
    }

}
