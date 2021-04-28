package org.personal.springbootbase.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiConstant {

    private static final String V1 = "v1";

    // User
    public static final String FIND_ALL_USERS_V1 = V1 + "/users";
    public static final String FIND_USER_BY_ID_V1 = V1 + "/users/{id}";
    public static final String CREATE_USER_V1 = V1 + "/users";
    public static final String UPDATE_USER_V1 = V1 + "/users/{id}";
    public static final String DELETE_USER_V1 = V1 + "/users/{id}";
}
