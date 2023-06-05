package com.vcs.model.entityVO;

import com.vcs.model.enums.OS;
import com.vcs.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
   private String userName;
   private UserType userType;
   private OS device;
}
