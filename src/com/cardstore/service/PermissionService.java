package com.cardstore.service;

import java.util.Set;

import com.cardstore.entity.Permission;
import com.cardstore.entity.Role;

public class PermissionService {
	// Method to check if the user has the required permission
	public static boolean hasPermission(Role role, String permissionName) {
		if (role == null) {
			return false;
		}

		Set<Permission> permissions = role.getPermissions();
		for (Permission permission : permissions) {
			if (permission.getName().equals(permissionName)) {
				return true;
			}
		}

		return false;
	}
}
