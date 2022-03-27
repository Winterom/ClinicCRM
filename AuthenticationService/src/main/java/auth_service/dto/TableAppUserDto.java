package auth_service.dto;

import auth_service.entityes.AppUser;

public class TableAppUserDto {
    private Long id;
    private String firstname;
    private String surname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private Boolean isLocked;
    private Boolean isEmailVerified;
    private Boolean isPhoneNumberVerified;

    public TableAppUserDto() {
    }

    public TableAppUserDto(AppUser usr){
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

    public TableAppUserDto(Long id, String firstname, String surname, String lastname,
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


    public Long getId() {
        return this.id;
    }


    public String getFirstname() {
        return this.firstname;
    }


    public String getSurname() {
        return this.surname;
    }


    public String getLastname() {
        return this.lastname;
    }


    public String getEmail() {
        return this.email;
    }


    public String getPhoneNumber() {
        return this.phoneNumber;
    }


    public Boolean isLocked() {
        return this.isLocked;
    }


    public Boolean isEmailVerified() {
        return this.isEmailVerified;
    }


    public Boolean isTelephoneVerified() {
        return this.isPhoneNumberVerified;
    }
}



