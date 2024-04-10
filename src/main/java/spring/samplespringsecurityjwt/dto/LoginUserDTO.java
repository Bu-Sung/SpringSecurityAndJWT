package spring.samplespringsecurityjwt.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spring.samplespringsecurityjwt.entity.UserEntity;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class LoginUserDTO implements UserDetails {

    private final UserDTO userDTO;

    @Override // role 값을 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return userDTO.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return userDTO.getPw();
    }

    @Override
    public String getUsername() {
        return userDTO.getId();
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
