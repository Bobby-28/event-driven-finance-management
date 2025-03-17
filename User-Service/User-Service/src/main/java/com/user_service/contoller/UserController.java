package com.user_service.contoller;

import com.user_service.entities.Token;
import com.user_service.entities.User;
import com.user_service.request.LoginRequest;
import com.user_service.service.UserProducer;
import com.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserProducer userProducer;
    @GetMapping()
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getByUserId(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.get(userId));
    }

    @PostMapping("create")
    public ResponseEntity<?> userCreate(@RequestBody User user){
        user.setUserId(UUID.randomUUID().toString());
        userProducer.sendUserData(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Successfully Created");
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest){
        User user= userService.getByEmail(loginRequest.getEmail());
        if(Objects.equals(user.getPassword(), loginRequest.getPassword())){
            Token token= userService.getToken(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header("Authorization", "Bearer " + token.getAccessToken())
                    .header("Authorization", "Bearer " + token.getRefreshToken())
                    .body(token);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is a problem !!!!!");
        }
    }
}
