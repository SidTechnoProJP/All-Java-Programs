package jwt.jwtutils;

import jwt.MyUserDetails;
//import jwt.repository.UserRepository;
import jwt.UserRepository;
import jwt.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class JwtUserDetailsService implements UserDetailsService {
@Autowired
UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.getUserByEmail(username);
        if (user!=null) {
            return new MyUserDetails(user);
        }

        else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }


}
