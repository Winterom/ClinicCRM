package auth_service.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_role")
@Entity
public class AppRole {
    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Setter @Getter
    @Column(name = "role_name")
    private String roleName;

    @Setter @Getter
    @Column(name = "description")
    private String description;

    @Setter @Getter
    @Column(name = "locked")
    private Boolean isLocked;

    @Setter @Getter
    @ManyToMany(mappedBy = "roles")
    private Collection<AppUser> appUser;

    @Setter @Getter
    @OneToMany(mappedBy = "roles")
    private Collection<AppAuthority> authorities;

    @Setter @Getter
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Setter @Getter
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
