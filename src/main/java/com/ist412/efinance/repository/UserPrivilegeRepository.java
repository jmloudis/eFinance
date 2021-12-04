package com.ist412.efinance.repository;

import com.ist412.efinance.model.UserPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPrivilegeRepository extends JpaRepository<UserPrivilege, Long> {

    UserPrivilege findByName(String name);
}
