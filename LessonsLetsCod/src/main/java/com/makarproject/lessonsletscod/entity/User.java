package com.makarproject.lessonsletscod.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.boot.devtools.restart.AgentReloader.isActive;

@Entity
@Table(name = "users_s")
@Component
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = "password")
    @NotBlank(message = "password is required field!")
    @Size(min = 7, max = 150, message = "Size of password in diapazon: 7-50 simbols")
    @Pattern(regexp = "(.*)+[A-Za-z]", message = "You can use only [A-Za-z] and [А-Я][а-я] and symbols(0-9)")
    public String password;


    @Column(name = "username")
    @NotBlank(message = "username is required field!")
    @Size(min = 2, max = 50, message = "Size of username in diapazon: 2-50 simbols")
    public String username;

    @Column(name = "active")
    private boolean active;
    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER) //фактически заменяет таблицу для хранения Enum
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"))//данное поле будет храниться в отдельной таблице, которую мы не описывали в бд (мы создали табличку ролей, которая будет связываться с текущей табличкой через user_id
    @Enumerated(EnumType.STRING) // мы хотим хранить этот Enum в виде строки
    private Set<Role> roles = new HashSet<>();

//    private boolean action;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }


    public void setUsername(String username) {
        this.username = username;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean isActive() {
        return active;
    }
}
