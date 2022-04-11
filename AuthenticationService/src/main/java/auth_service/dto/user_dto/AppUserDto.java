package auth_service.dto.user_dto;


import auth_service.dto.role_dto.AppRoleForUserDto;
import auth_service.entities.AppUser;
import auth_service.entities.UserStatusEnum;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum AppUserDto {
    ;

    private interface Firstname {
        @Size(min = 3, max = 50) String getFirstname();
    }

    private interface Surname {
        @Size(max = 50) String getSurname();
    }

    private interface Lastname {
       String getLastname();
    }

    private interface Password {
        @NotBlank String getPassword();
    }

    private interface Email {
        @javax.validation.constraints.Email String getEmail();
    }

    private interface Telephone {
        @Size(min = 6, max = 25) String getPhoneNumber();
    }

    private interface Status {
        UserStatusEnum getStatus();
    }

    private interface IsEmailVerified {
        Boolean isEmailVerified();
    }

    private interface IsPhoneNumberVerified {
        Boolean isTelephoneVerified();
    }

    private interface Roles {
        List<AppRoleForUserDto> getRoles();
    }

    public enum Request {
        ;
        @NoArgsConstructor
        public static class CreateOrUpdate implements Firstname, Surname, Lastname, Password, Email, Telephone, Status {
            @Setter
            private String firstname;
            @Setter
            private String surname;
            @Setter
            private String lastname;
            @Setter
            private String password;
            @Setter
            private String email;
            @Setter
            private String phoneNumber;
            @Setter
            private UserStatusEnum status;


            public CreateOrUpdate(String firstname, String surname, String lastname, String password, String email, String phoneNumber, UserStatusEnum status) {
                this.firstname = firstname;
                this.surname = surname;
                this.lastname = lastname;
                this.password = password;
                this.email = email;
                this.phoneNumber = phoneNumber;
                this.status = status;
            }

            @Override
            public String getFirstname() {
                return this.firstname;
            }

            @Override
            public String getPassword() {
                return this.password;
            }

            @Override
            public String getEmail() {
                return this.email;
            }

            @Override
            public String getPhoneNumber() {
                return this.phoneNumber;
            }

            @Override
            public String getSurname() {
                return this.surname;
            }

            @Override
            public String getLastname() {
                return this.lastname;
            }

            @Override
            public UserStatusEnum getStatus() {
                return this.status;
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
                this.roles = usr.getRoles().stream().map(AppRoleForUserDto::new).collect(Collectors.toList());
            }
        }
    }
}
