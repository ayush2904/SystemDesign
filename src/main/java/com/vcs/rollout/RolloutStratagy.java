package com.vcs.rollout;

import com.vcs.model.entityVO.User;

import java.util.List;

public interface RolloutStratagy {
    public List<User> rolloutRelease(int number);
}
