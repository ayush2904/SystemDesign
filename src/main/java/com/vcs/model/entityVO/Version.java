package com.vcs.model.entityVO;

import com.vcs.model.enums.OS;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Version {
    private OS supportedOS;
    private int versionNumber;
}
