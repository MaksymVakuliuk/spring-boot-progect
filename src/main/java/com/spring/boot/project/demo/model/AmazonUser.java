package com.spring.boot.project.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "AmazonUser")
public class AmazonUser {
    @Id
    @Column(name = "UserId", unique = true, nullable = false)
    private String userId;
    @Column(name = "ProfileName", unique = true, nullable = false)
    private String profileName;
}
