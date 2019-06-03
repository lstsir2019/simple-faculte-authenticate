package com.faculte.simplefaculteauthenticate.domain.bean;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"email","password"})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email, password;
 /** 
   * @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
   * @JoinTable(
   *         name = "authority_user",
   *         joinColumns = @JoinColumn(name = "user_id"),
   *         inverseJoinColumns = @JoinColumn(name = "authority_id"))
   * private List<Authority> authorities = new ArrayList();
   * 
   * */

    @OneToMany(mappedBy = "user")
    private List<AuthorityUser> authorityUsers= new ArrayList<>();


    

}
