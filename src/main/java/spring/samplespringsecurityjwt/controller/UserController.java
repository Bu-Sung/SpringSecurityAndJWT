package spring.samplespringsecurityjwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.samplespringsecurityjwt.dto.UserDTO;
import spring.samplespringsecurityjwt.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("join")
    public String userJoin(@RequestBody UserDTO userDTO){
        if(userService.userJoin(userDTO)){
            return "User Join Success";
        }else{
            return "User Join Failed";
        }
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "Admin Page";
    }

    @GetMapping("/")
    public String mainPage(){
        return "Main Page";
    }
}
