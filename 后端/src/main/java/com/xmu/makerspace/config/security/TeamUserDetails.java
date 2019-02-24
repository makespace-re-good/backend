package com.xmu.makerspace.config.security;

import com.xmu.makerspace.domain.TeamEnter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by status200 on 2017/9/18.
 */
public class TeamUserDetails implements UserDetails {

    private String username;

    private String password;

    public TeamUserDetails(TeamEnter teamEnter) {
        username = String.valueOf(teamEnter.getTeamId());
        password = teamEnter.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String roles = "ROLE_TEAM";

        return AuthorityUtils.createAuthorityList(roles);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
