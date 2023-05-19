package com.gft.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gft.demo.domain.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_api")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String email;

    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    LocalDate dateOfBirth;

    String address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Skill> skills = new HashSet<>();

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt;

    public static User toEntity(UserModel model) {
        if(model == null)
            return null;

        return User.builder()
                .id(model.getId())
                .name(model.getName())
                .email(model.getEmail())
                .dateOfBirth(model.getDateOfBirth())
                .address(model.getAddress())
                .build();
    }

    @PrePersist
    void prePersist(){
        this.createdAt = LocalDateTime.now();
    }

}
