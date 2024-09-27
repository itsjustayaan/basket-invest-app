package com.working.authentication;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.working.model.InvestmentAdvisor;
import com.working.model.Investor;
import com.working.services.InvestmentAdvisor.InvestmentAdvisorServiceImpl;
import com.working.services.Investor.InverstorServiceImpl;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private InvestmentAdvisorServiceImpl investmentAdvisorImpl;

    @Autowired
    private InverstorServiceImpl investorImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        InvestmentAdvisor advisor = investmentAdvisorImpl.findByEmail(username);
        if (advisor != null) {
            return new User(advisor.getIaEmail(), advisor.getIaPassword(), 
                    List.of(new SimpleGrantedAuthority(advisor.getRole().getRole())));
        }

        Investor investor = investorImpl.findByEmail(username);
        if (investor != null) {
            return new User(investor.getEmail(), investor.getInvestorPassword(), 
                    List.of(new SimpleGrantedAuthority(investor.getRole().getRole())));
        }

        throw new UsernameNotFoundException("User not found with Email: " + username);
    }
}
