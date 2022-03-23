package auth_service.dto;


import auth_service.entityes.AppUser;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

public enum AppUserDto {;
    private interface Id { @Positive Long getId(); }
    private interface Firstname { @Size(min = 3,max = 50) String getFirstname(); }
    private interface Surname { @Size(max = 50) String getSurname(); }
    private interface Lastname { @Size(min = 3,max = 50) String getLastname(); }
    private interface Password { @NotBlank String getPassword(); }
    private interface Email { @javax.validation.constraints.Email String getEmail(); }
    private interface Telephone { @Size(min=6, max = 25) String getPhoneNumber(); }
    private interface IsLocked {Boolean isLocked(); }
    private interface IsEmailVerified {Boolean isEmailVerified(); }
    private interface PhoneNumberVerified {Boolean isTelephoneVerified(); }
    private interface Roles {
        List<RolesDto> getRoles();}

    public enum Request{;
        public static class Create implements Firstname,Surname,Lastname,Password,Email,Telephone{
            private String firstname;
            private String surname;
            private String lastname;
            private String password;
            private String email;
            private String phoneNumber;

            public Create() {
            }

            public Create(String firstname, String surname, String lastname, String password, String email, String phoneNumber) {
                this.firstname = firstname;
                this.surname = surname;
                this.lastname = lastname;
                this.password = password;
                this.email = email;
                this.phoneNumber = phoneNumber;
            }

            public void setFirstname(String firstname) {
                this.firstname = firstname;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
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
        }

    }
    public enum Response{;
        public static class userTable implements Id, Firstname,Surname,Lastname, Email, Telephone, IsLocked, IsEmailVerified, PhoneNumberVerified {
            private Long id;
            private String firstname;
            private String surname;
            private String lastname;
            private String email;
            private String phoneNumber;
            private Boolean isLocked;
            private Boolean isEmailVerified;
            private Boolean isPhoneNumberVerified;

            public userTable() {
            }

            public userTable(AppUser usr){
                this.id = usr.getId();
                this.firstname = usr.getFirstname();
                this.surname = usr.getSurname();
                this.lastname = usr.getLastname();
                this.email = usr.getEmail();
                this.phoneNumber = usr.getPhoneNumber();
                this.isLocked = usr.getLocked();
                this.isEmailVerified = usr.getEmailVerified();
                this.isPhoneNumberVerified = usr.getPhoneVerified();
            }

            public userTable(Long id, String firstname, String surname, String lastname,
                             String email, String phoneNumber, Boolean isLocked, Boolean isEmailVerified,
                             Boolean isPhoneNumberVerified) {
                this.id = id;
                this.firstname = firstname;
                this.surname = surname;
                this.lastname = lastname;
                this.email = email;
                this.phoneNumber = phoneNumber;
                this.isLocked = isLocked;
                this.isEmailVerified = isEmailVerified;
                this.isPhoneNumberVerified = isPhoneNumberVerified;
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
            public Boolean isLocked() {
                return this.isLocked;
            }

            @Override
            public Boolean isEmailVerified() {
                return this.isEmailVerified;
            }

            @Override
            public Boolean isTelephoneVerified() {
                return this.isPhoneNumberVerified;
            }
        }

    }

}
