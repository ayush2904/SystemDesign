package com.vcs.rollout;

import com.vcs.exception.StratagyNotFound;
import com.vcs.model.enums.RolloutStratagyEnum;
import lombok.SneakyThrows;

public class RolloutFactory {
    @SneakyThrows
    public RolloutStratagy getRolloutStratagy(RolloutStratagyEnum st) {
        if(RolloutStratagyEnum.BETA.equals(st)) {
            return new BetaRollout();
        }
        if(RolloutStratagyEnum.PERCENT.equals(st)) {
            return new PercentRollout();
        }
        throw new StratagyNotFound("no stratafy found by this name");
    }
}
