package com.busstation.services;

import com.busstation.payload.request.RoleRequest;
import com.busstation.payload.response.RoleResponse;

public interface RoleService {

    RoleResponse createRole(RoleRequest request);

    RoleResponse updateRole(String roleId,RoleRequest request);

    Boolean deleteRole(String roleId);

}
