package com.bbawker.webservice.security;

import com.bbawker.webservice.domain.ETOAdmin.EtoAccounts;
import com.bbawker.webservice.domain.ETOAdmin.EtoAccountsRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

public class SecurityMember extends User {

    private static final String ROLE_PREFIX = "ROLE_";
    private static final long serialVersionUID = 1L;

    public SecurityMember(EtoAccounts etoAccounts) {
        super(etoAccounts.getEmail(), etoAccounts.getPassword(), makeGrantedAuthority(etoAccounts.getRoles()));
    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<EtoAccountsRole> roles){
        List<GrantedAuthority> list = new ArrayList<>();
        roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
        return list;
    }
}
