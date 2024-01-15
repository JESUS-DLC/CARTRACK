package dev.jesusdlc.cartrack.domain.util;

import java.util.Arrays;
import java.util.List;

public enum RoleEnum {
    ADMINISTRATOR(Arrays.asList(
            PermissionEnum.READ_ALL_BRANDS,
            PermissionEnum.READ_ONE_BRAND,
            PermissionEnum.CREATE_ONE_BRAND,
            PermissionEnum.UPDATE_ONE_BRAND,
            PermissionEnum.DELETE_ONE_BRAND,

            PermissionEnum.READ_ALL_CARS,
            PermissionEnum.READ_ONE_CAR,
            PermissionEnum.CREATE_ONE_CAR,
            PermissionEnum.UPDATE_ONE_CAR,
            PermissionEnum.DELETE_ONE_CAR,

            PermissionEnum.READ_PROFILE
    )),
    ASSISTANT(Arrays.asList(
            PermissionEnum.READ_ALL_BRANDS,
            PermissionEnum.READ_ONE_BRAND,
            PermissionEnum.UPDATE_ONE_BRAND,

            PermissionEnum.READ_ALL_CARS,
            PermissionEnum.READ_ONE_CAR,
            PermissionEnum.UPDATE_ONE_CAR,

            PermissionEnum.READ_PROFILE

    )),
    CUSTOMER(Arrays.asList(PermissionEnum.READ_PROFILE));

    public List<PermissionEnum> permissionEnums;

    RoleEnum(List<PermissionEnum> permissionEnums) {
        this.permissionEnums = permissionEnums;
    }

    public List<PermissionEnum> getPermissions() {
        return permissionEnums;
    }

    public void setPermissions(List<PermissionEnum> permissionEnums) {
        this.permissionEnums = permissionEnums;
    }
}
