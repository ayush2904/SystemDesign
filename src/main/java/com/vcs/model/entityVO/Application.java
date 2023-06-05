package com.vcs.model.entityVO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class Application {
    private String appname;
    private Version latestVersion;
}
