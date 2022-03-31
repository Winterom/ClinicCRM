package auth_service.entities;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Table(name = "authorities")
@Entity
public class AppAuthority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private AppAuthoritiesEnum authority;

    @Column(name = "description")
    private String description;


    @ManyToMany
    @JoinTable(name = "roles_authorities",
            joinColumns = @JoinColumn(name = "authority_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<AppRole> roles;

    public AppAuthority(AppAuthoritiesEnum authorities) {
        this.authority = authorities;
    }

    public AppAuthority() {
    }

    public void setAuthority(AppAuthoritiesEnum authorities) {
        this.authority = authorities;
    }

    public Collection<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<AppRole> roles) {
        this.roles = roles;
    }

    @Override
    public String getAuthority() {
        return this.authority.name();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.authority.name();
    }
}
