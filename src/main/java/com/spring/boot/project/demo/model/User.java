package com.spring.boot.project.demo.model;

import com.spring.boot.project.demo.unit.ExistOrGenerated;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ExistingOrGenerated")
    @GenericGenerator(name = "ExistingOrGenerated",
            strategy = "com.spring.boot.project.demo.unit.ExistOrGenerated",
            parameters = {
                    @Parameter(name =
                            ExistOrGenerated.VALUE_PREFIX_PARAMETER,
                            value = "U_"),
                    @Parameter(name =
                            ExistOrGenerated.NUMBER_FORMAT_PARAMETER,
                            value = "%010d")})
    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;
    @Column(name = "profile_name", nullable = false, unique = true)
    private String profileName;
    private String password;
    @OneToMany(mappedBy = "user")
    private Set<Review> reviews;
    @ManyToMany
    private Set<UsersRole> roles;
}
