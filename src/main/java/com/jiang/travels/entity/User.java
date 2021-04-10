package com.jiang.travels.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
@ToString
public class User implements Serializable {

    private String id;
    private String username;
    private String password;
    private String email;

}
