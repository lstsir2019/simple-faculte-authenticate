package com.faculte.simplefaculteauthenticate.domain.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = "user")
public class AuthorityUser {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Authority authority;

    public AuthorityUser(User user, Authority authority) {
        this.user = user;
        this.authority = authority;
    }


}
