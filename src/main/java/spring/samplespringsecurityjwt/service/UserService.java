package spring.samplespringsecurityjwt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.samplespringsecurityjwt.dto.UserDTO;
import spring.samplespringsecurityjwt.dto.LoginUserDTO;
import spring.samplespringsecurityjwt.entity.UserEntity;
import spring.samplespringsecurityjwt.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean userJoin(UserDTO userDTO){
        if(userRepository.existsById(userDTO.getId())){
            return false;
        }else{
            userRepository.save(UserEntity.builder()
                    .id(userDTO.getId())
                    .pw(bCryptPasswordEncoder.encode(userDTO.getPw()))
                    .role(userDTO.getRole())
                    .build());
            return true;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findById(username).get();

        if(userEntity != null){
            return new LoginUserDTO(UserDTO.builder()
                    .id(userEntity.getId())
                    .pw(userEntity.getPw())
                    .role(userEntity.getRole())
                    .build());
        }

        return null;
    }
}
