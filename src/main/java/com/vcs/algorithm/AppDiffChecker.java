package com.vcs.algorithm;

public class AppDiffChecker<Application> implements Diffchecker<Application>{
    @Override
    public DiffReport getDiffFromObject(Application element1, Application element2) {
        if(!element1.toString().equalsIgnoreCase(element2.toString())) {
            DiffReport diff = new DiffReport();
            diff.setReport(" DiffReport ---"+element1.toString()+"--"+element2.toString());
            return diff;
        }
        return null;
    }
}
