package com.xmu.makerspace.config.security;

import com.xmu.makerspace.dao.TeamEnterRepository;
import com.xmu.makerspace.domain.TeamEnter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by status200 on 2017/9/18.
 */
@Component
public class TeamUserDetailsService implements UserDetailsService {

    private final TeamEnterRepository teamEnterRepository;

    private Log log = LogFactory.getLog(TeamUserDetailsService.class);

    public TeamUserDetailsService(TeamEnterRepository teamEnterRepository) {
        this.teamEnterRepository = teamEnterRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            log.info(String.format("Authenticating User %s",username));

            int teamId = Integer.parseInt(username);
            TeamEnter teamEnter = teamEnterRepository.findByTeamId(teamId);

            return new TeamUserDetails(teamEnter);
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("Team username only allow integer");
        }

    }
}
