package auth_service.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Table(name = "app_role")
@Entity
public class AppRole {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "description")
    private String description;

    @Column(name = "locked")
    private Boolean isLocked;

    @ManyToMany(mappedBy = "roles")
    private Collection<AppUser> appUser;

    @ManyToMany(mappedBy = "roles")
    private Collection<AppAuthority> authorities;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public AppRole() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Collection<AppAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<AppAuthority> authorities) {
        this.authorities = authorities;
    }

    public Collection<AppUser> getAppUser() {
        return appUser;
    }

    public void setAppUser(Collection<AppUser> appUser) {
        this.appUser = appUser;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }
}
