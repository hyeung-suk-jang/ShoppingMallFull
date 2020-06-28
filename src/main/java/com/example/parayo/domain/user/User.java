package com.example.parayo.domain.user;

import com.example.parayo.domain.baseEntity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @JsonFormat
    @ApiModelProperty(value = "이름", required = false)
    @Column(name = "name", length = 30, nullable = true)
    private String name;

    @JsonFormat
    @ApiModelProperty(value = "이메일", required = true)
    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    @Column(name = "password", length = 255, nullable = true)
    private String password;

    @JsonFormat
    @Column(name = "fcm_token", length = 255, nullable = true)
    private String fcmToken;

    public User(String email, String hashedPassword, String name, String fcmToken) {
        this.email = email;
        this.password = hashedPassword;
        this.name = name;
        this.fcmToken = fcmToken;
    }

    public void updateToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
