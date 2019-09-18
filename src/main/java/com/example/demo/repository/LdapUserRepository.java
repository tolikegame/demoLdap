package com.example.demo.repository;

import com.example.demo.model.LdapUser;
import org.springframework.data.ldap.repository.LdapRepository;

import java.util.List;

public interface LdapUserRepository extends LdapRepository<LdapUser> {
    LdapUser findAllByAccount(String account);
    List<LdapUser> findAll();
}
