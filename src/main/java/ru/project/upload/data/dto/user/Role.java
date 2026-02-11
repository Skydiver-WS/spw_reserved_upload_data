package ru.project.upload.data.dto.user;

import lombok.Data;


@Data
public class Role {

    private RolesType rolesType;


    public static Role from (RolesType rolesType) {
        Role role = new Role();
        role.setRolesType(rolesType);
        return role;
    }
}
