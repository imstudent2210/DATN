//package com.graduate.touslestemp.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import jakarta.persistence.*;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@Entity
//@Table(name="User")
//public class User implements UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="id")
//    private Long id;
//    private String username;
//    private String password;
//    private String phone;
//    private String email;
//    private String name;
//    @Lob
//    @Column(columnDefinition = "MEDIUMBLOB")
//    private String profile;
//
//    private Boolean enable = true;
//
//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "user")
//    @JsonIgnore
//    private Set<AccountRole> userRole = new HashSet<>();
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<Authority> set = new HashSet<>();
//        this.userRole.forEach(userRole1 -> {
//            set.add(new Authority(userRole1.getRole().getRoleName()));
//        });
//        return set;
//    }
//
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return enable;
//    }
//
//
//}