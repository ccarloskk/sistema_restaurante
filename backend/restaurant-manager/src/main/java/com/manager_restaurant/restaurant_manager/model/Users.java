package com.manager_restaurant.restaurant_manager.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@Table(name = "users")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id_user;

    @Column(nullable = false)
    private String user_name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UsersRole role;

    public Users(String user_name, String email, String password, UsersRole role) {
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public Users() {

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UsersRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public Long getId_user() {
        return id_user;
    }

    public Users setId_user(Long id_user) {
        this.id_user = id_user;
        return this;
    }

    public String getUser_name() {
        return user_name;
    }

    public Users setUser_name(String user_name) {
        this.user_name = user_name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Users setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Users setPassword(String password) {
        this.password = password;
        return this;
    }

    public UsersRole getRole() {
           return role;
    }

    public UsersRole setRole(UsersRole role) {
        this.role = role;
        return role;
    }

        @Override
  public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
