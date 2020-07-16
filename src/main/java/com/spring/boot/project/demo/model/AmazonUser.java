package com.spring.boot.project.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "amazonUser")
public class AmazonUser {
    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;
    @Column(name = "profile_name", nullable = false)
    private String profileName;
}
