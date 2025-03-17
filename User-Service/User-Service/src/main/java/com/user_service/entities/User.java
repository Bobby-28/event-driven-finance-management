package com.user_service.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    String userId;
    @Column(name = "username", nullable = false)
    String username;
    @Column(name = "password", nullable = false)
    String password;
    @Column(name = "email", nullable = false)
    String email;
}
