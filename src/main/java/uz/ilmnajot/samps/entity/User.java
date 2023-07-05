package uz.ilmnajot.samps.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.ilmnajot.samps.abstractClass.AbsEntity;
import uz.ilmnajot.samps.enums.RoleName;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbsEntity implements UserDetails { //BUNI KO'CHIRISH

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String username; //here is username is email

    @Column(nullable = false)
    private String password;

    private boolean deleted;

    private boolean active;

    private String emailCode;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @ManyToOne
    private Course course;

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Note> notes;



    private boolean accountNotExpired = true;

    private boolean accountNonLocked  = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.roleName.name());
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public User(String fullName, String username, String password, RoleName roleName) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.roleName = roleName;
    }
}
