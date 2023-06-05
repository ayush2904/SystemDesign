package com.vcs.rollout;

import com.vcs.model.entityVO.User;
import com.vcs.model.repositories.UserRepository;

import java.util.List;

public class PercentRollout implements RolloutStratagy{
    @Override
    public List<User> rolloutRelease(int number) {
        UserRepository userRepository = new UserRepository();
        int total = userRepository.getSize();
        int requiredNumber = (total/number)*100;
        List<User> users = userRepository.getRandomNUsers(requiredNumber);
        return users;
    }
}
