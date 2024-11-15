package com.example.user_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "systemuser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String cognitoId;
    private String email;
    private boolean isVerified;
    private String role;

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public void setCognitoId(String cognitoId) {
        this.cognitoId = cognitoId;
    }
    public void setEmail(String email) {
        this.email= email;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public void setIsVerified(boolean isVerified){ this.isVerified = isVerified;}



}
