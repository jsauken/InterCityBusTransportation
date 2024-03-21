package kz.iitu.intercitybustransportation.repository;

import kz.iitu.intercitybustransportation.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findRoleByName(String name);
}

