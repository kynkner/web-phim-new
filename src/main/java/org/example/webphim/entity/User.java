package org.example.webphim.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.webphim.model.enums.UserRole;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    @Column(nullable = false, unique = true)
    String email;

    String password;
    String avatar;

    @Enumerated(EnumType.STRING)
    UserRole role;

    Date createdAt;
    Date updatedAt;
}
