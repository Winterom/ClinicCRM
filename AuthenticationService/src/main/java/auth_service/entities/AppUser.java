package auth_service.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@NoArgsConstructor
@Table(name = "app_user")
@Entity
public class AppUser {

    @Setter @Getter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Setter @Getter
    @Column(name = "firstname")/*имя*/
    private String firstname;

    @Setter @Getter
    @Column(name = "surname")/*фамилия*/
    private String surname;

    @Setter @Getter
    @Column(name = "lastname")/*отчество*/
    private String lastname;

    @Setter @Getter
    @Column(name = "password")
    private String password;

    @Setter @Getter
    @Column(name = "email")
    private String email;

    @Setter @Getter
    @Column(name = "phone_number")
    private String phoneNumber;

    @Setter @Getter
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatusEnum status;

    @Setter @Getter
    @Column(name = "email_verified")
    private Boolean isEmailVerified = false;

    @Setter @Getter
    @Column(name = "telephone_verified")
    private Boolean phoneVerified = false;

    @Setter @Getter
    @Column(name = "expired_credentials")
    private LocalDateTime expiredCredentials;

    @Setter @Getter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<AppRole> roles;

    @Setter @Getter
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Setter @Getter
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}
