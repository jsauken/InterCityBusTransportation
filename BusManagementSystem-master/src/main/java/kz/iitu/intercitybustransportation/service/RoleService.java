package kz.iitu.intercitybustransportation.service;

import kz.iitu.intercitybustransportation.model.Role;

public interface RoleService {
    // Method to find a Role by its name
    Role findByName(String name);
}
