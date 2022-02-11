package com.ntr.service.impl.auth;

import com.ntr.entity.Account;
import com.ntr.entity.User;
import com.ntr.service.AccountService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final AccountService accountService;

    public UserDetailServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // select account from DB
        Account account = accountService.getBaseMapper().selectById(username);
        // account exists or not
        if(Objects.isNull(account)) {
            throw new UsernameNotFoundException("Account doesn't exist");
        }
        List<GrantedAuthority> auths =
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_"+account.getRole());
        // create a User object to authenticate
        return new User(account, auths);
    }
}
