package com.vcs.rollout;

import com.vcs.model.entityVO.User;
import com.vcs.model.repositories.UserRepository;

import java.util.List;

public class BetaRollout implements RolloutStratagy{

    @Override
    public List<User> rolloutRelease(int number) {
        UserRepository userRepository = new UserRepository();
        List<User> users = userRepository.getRandomNUsers(number);
        return users;
    }
}
