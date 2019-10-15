package com.bbawker.webservice.security;

import com.bbawker.webservice.domain.ETOAdmin.EtoAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    EtoAccountsRepository etoAccountsRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return
                Optional.ofNullable(etoAccountsRepository.findByEmail(email))
                        .filter(m -> m!= null)
                        .map(m -> new SecurityMember(m)).get();
    }
}
