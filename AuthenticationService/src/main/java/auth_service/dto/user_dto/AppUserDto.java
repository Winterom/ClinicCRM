package auth_service.dto.user_dto;


import auth_service.dto.role_dto.AppRoleForUserDto;
import auth_service.entities.AppUser;
import auth_service.entities.UserStatusEnum;
import auth_service.exception.PhoneNumberValidationException;
import auth_service.validators.PhoneNumberValidator;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum AppUserDto {
    ;

    private interface Firstname {
         void setFirstname(@Size(min = 3, max = 50)String firstname);
    }

    private interface Surname {
         void setSurname(@Size(max = 50)String surname);
    }


    private interface Password {
         void setPassword(@Size(min = 6, max = 50)String password);
    }

    private interface Email {
         void setEmail(@javax.validation.constraints.Email String email);
    }

    private interface Telephone {
         void setPhoneNumber(@Size(min = 6, max = 25) String phone);
    }

    public enum Request {
        ;
        @NoArgsConstructor
        public static class CreateOrUpdate implements Firstname, Surname, Password, Email, Telephone {
            @Getter
            @Setter
            private Long id;
            @Setter@Getter
            private String firstname;
            @Setter@Getter
            private String surname;
            @Setter@Getter
            private String lastname;
            @Setter@Getter
            private String password;
            @Setter@Getter
            private String email;
            @Setter@Getter
            private String phoneNumber;
            @Setter@Getter
            private UserStatusEnum status;
            @Getter@Setter
            private List<Long> roles;


            public CreateOrUpdate(String firstname, String surname, String lastname, String password, String email, String phoneNumber, UserStatusEnum status) {
                this.firstname = firstname;
                this.surname = surname;
                this.lastname = lastname;
                this.password = password;
                this.email = email;
                this.phoneNumber = phoneNumber;
                this.status = status;
            }
            public AppUser getUserFromDto(BCryptPasswordEncoder passwordEncoder,Integer credentialExpired ){
                AppUser usr = new AppUser();
                usr.setId(this.id);
                usr.setFirstname(this.firstname);
                usr.setLastname(this.lastname);
                usr.setSurname(this.surname);
                usr.setEmail(this.email);
                if (PhoneNumberValidator.validatePhoneNumber(this.phoneNumber)){
                    usr.setPhoneNumber(this.phoneNumber);
                }else throw new PhoneNumberValidationException();
                if(this.password!=null){
                    usr.setPassword(passwordEncoder.encode(this.password));
                    usr.setExpiredCredentials(LocalDateTime.now().plusMonths(credentialExpired));
                }
                System.out.println(roles);
                return usr;
            }


        }



        @NoArgsConstructor
        @AllArgsConstructor
        public static class GetAllUserWithFilters{

            @Setter@Getter
            private Integer page;

            @Setter@Getter
            private Integer itemInPage;

            @Setter@Getter
            private String sortField;

            @Setter@Getter
            private Boolean directSort;

            @Setter@Getter
            private String searchField;

            @Setter@Getter
            private String searchValue;

            @Setter@Getter
            private Set<UserStatusEnum> status;

        }
    }

    public enum Response {
        ;
        @ToString
        @NoArgsConstructor
        @AllArgsConstructor
        public static class UserTable {
            @Setter@Getter
            private Long id;
            @Setter@Getter
            private String firstname;
            @Setter@Getter
            private String surname;
            @Setter@Getter
            private String lastname;
            @Setter@Getter
            private String email;
            @Setter@Getter
            private String phoneNumber;
            @Setter@Getter
            private UserStatusEnum status;



            public UserTable(AppUser usr) {
                this.id = usr.getId();
                this.firstname = usr.getFirstname();
                this.surname = usr.getSurname();
                this.lastname = usr.getLastname();
                this.email = usr.getEmail();
                this.phoneNumber = usr.getPhoneNumber();
                this.status = usr.getStatus();
            }



        }

        @NoArgsConstructor
        public static class UserProfile {
            @Setter@Getter
            private Long id;
            @Setter@Getter
            private String firstname;
            @Setter@Getter
            private String surname;
            @Setter@Getter
            private String lastname;
            @Setter@Getter
            private String email;
            @Setter@Getter
            private String phoneNumber;
            @Setter@Getter
            private UserStatusEnum status;
            @Setter@Getter
            private Boolean isEmailVerified;
            @Setter@Getter
            private Boolean isPhoneNumberVerified;
            @Getter@Setter
            private LocalDateTime created_at;
            @Getter@Setter
            private LocalDateTime updated_at;
            @Setter@Getter
            private List<AppRoleForUserDto> roles;

            public UserProfile(AppUser usr){
                this.id = usr.getId();
                this.firstname = usr.getFirstname();
                this.surname = usr.getSurname();
                this.lastname = usr.getLastname();
                this.email = usr.getEmail();
                this.phoneNumber = usr.getPhoneNumber();
                this.status = usr.getStatus();
                this.isEmailVerified = usr.getIsEmailVerified();
                this.isPhoneNumberVerified = usr.getPhoneVerified();
                this.created_at=usr.getCreatedAt();
                this.updated_at=usr.getUpdatedAt();
                this.roles = usr.getRoles().stream().map(AppRoleForUserDto::new).collect(Collectors.toList());
            }
        }
    }
}
