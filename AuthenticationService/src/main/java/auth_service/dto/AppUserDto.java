package auth_service.dto;


import auth_service.entities.AppUser;
import auth_service.entities.UserStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

public enum AppUserDto {
    ;

    private interface Id {
        @Positive Long getId();
    }

    private interface Firstname {
        @Size(min = 3, max = 50) String getFirstname();
    }

    private interface Surname {
        @Size(max = 50) String getSurname();
    }

    private interface Lastname {
        @Size(min = 3, max = 50) String getLastname();
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

    private interface PhoneNumberVerified {
        Boolean isTelephoneVerified();
    }

    private interface Roles {
        List<RolesDto> getRoles();
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
        @NoArgsConstructor
        public static class userTable implements Id, Firstname, Surname, Lastname, Email, Telephone, Status {
            @Setter
            private Long id;
            @Setter
            private String firstname;
            @Setter
            private String surname;
            @Setter
            private String lastname;
            @Setter
            private String email;
            @Setter
            private String phoneNumber;
            @Setter
            private UserStatusEnum status;


            public userTable(AppUser usr) {
                this.id = usr.getId();
                this.firstname = usr.getFirstname();
                this.surname = usr.getSurname();
                this.lastname = usr.getLastname();
                this.email = usr.getEmail();
                this.phoneNumber = usr.getPhoneNumber();
                this.status = usr.getStatus();
            }

            public userTable(Long id, String firstname, String surname, String lastname,
                             String email, String phoneNumber, UserStatusEnum status) {
                this.id = id;
                this.firstname = firstname;
                this.surname = surname;
                this.lastname = lastname;
                this.email = email;
                this.phoneNumber = phoneNumber;
                this.status = status;
            }

            @Override
            public Long getId() {
                return this.id;
            }

            @Override
            public String getFirstname() {
                return this.firstname;
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
            public String getEmail() {
                return this.email;
            }

            @Override
            public String getPhoneNumber() {
                return this.phoneNumber;
            }

            @Override
            public UserStatusEnum getStatus() {
                return this.status;

            }

        }

    }


}
