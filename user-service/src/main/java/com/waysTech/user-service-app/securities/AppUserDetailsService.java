package com.waysTech.scores_of_students.securities;



import com.waysTech.scores_of_students.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AppUserDetailsService implements UserDetailsService {
    private final TeacherRepository teacherRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return teacherRepository.findByEmail(username)
                .map(AppUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("User not in Database"));
    }
}
