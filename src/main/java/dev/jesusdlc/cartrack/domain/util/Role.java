package dev.jesusdlc.cartrack.domain.util;

import java.util.Arrays;
import java.util.List;

public enum Role {
    ADMINISTRATOR(Arrays.asList(
            Permission.READ_ALL_BRANDS,
            Permission.READ_ONE_BRAND,
            Permission.CREATE_ONE_BRAND,
            Permission.UPDATE_ONE_BRAND,
            Permission.DELETE_ONE_BRAND,

            Permission.READ_ALL_CARS,
            Permission.READ_ONE_CAR,
            Permission.CREATE_ONE_CAR,
            Permission.UPDATE_ONE_CAR,
            Permission.DELETE_ONE_CAR,

            Permission.READ_PROFILE
    )),
    ASSISTANT(Arrays.asList(
            Permission.READ_ALL_BRANDS,
            Permission.READ_ONE_BRAND,
            Permission.UPDATE_ONE_BRAND,

            Permission.READ_ALL_CARS,
            Permission.READ_ONE_CAR,
            Permission.UPDATE_ONE_CAR,

            Permission.READ_PROFILE

    )),
    CUSTOMER(Arrays.asList(Permission.READ_PROFILE));

    public List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
