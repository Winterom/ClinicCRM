package auth_service.entities;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Table(name = "app_user")
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")/*имя*/
    private String firstname;

    @Column(name = "surname")/*фамилия*/
    private String surname;

    @Column(name = "lastname")/*отчество*/
    private String lastname;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "locked")
    private Boolean isLocked = false;

    @Column(name = "email_verified")
    private Boolean isEmailVerified = false;

    @Column(name = "telephone_verified")
    private Boolean phoneVerified = false;

    @Column(name = "expired_credentials")
    private LocalDateTime expiredCredentials;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<AppRole> roles;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public AppUser() {
    }

    public LocalDateTime getExpiredCredentials() {
        return expiredCredentials;
    }

    public Collection<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<AppRole> roles) {
        this.roles = roles;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<AppAuthority> authorities= new HashSet<>();
        this.roles.forEach(x->authorities.addAll(x.getAuthorities()));
        return authorities;
    }

    public String getPassword() {
        return this.password;
    }


    public String getFirstname() {
        return this.firstname;
    }


    /*Indicates whether the user's credentials (password) has expired. Expired credentials prevent authentication*/

    public boolean isCredentialsNonExpired() {
        return LocalDateTime.now().isBefore(this.expiredCredentials);
    }








    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public Boolean getPhoneVerified() {
        return phoneVerified;
    }

    public String getSurname() {
        return surname;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setFirstname(String username) {
        this.firstname = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String telephone) {
        this.phoneNumber = telephone;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    public void setExpiredCredentials(LocalDateTime expiredCredentials) {
        this.expiredCredentials = expiredCredentials;
    }

    public void setRoles (Set<AppRole> roles){
        this.roles = roles;
    }

    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public void setPhoneVerified(Boolean telephoneVerified) {
        this.phoneVerified = telephoneVerified;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
