package kz.iitu.intercitybustransportation.service.impl;


import kz.iitu.intercitybustransportation.model.Role;
import kz.iitu.intercitybustransportation.repository.RoleRepository;
import kz.iitu.intercitybustransportation.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        // Find role by name using the roleDao
        Role role = roleRepository.findRoleByName(name);
        return role;
    }
}
