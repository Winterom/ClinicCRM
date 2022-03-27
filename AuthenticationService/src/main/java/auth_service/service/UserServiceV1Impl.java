package auth_service.service;

import auth_service.dto.AppUserDto;
import auth_service.dto.JwtDto;
import auth_service.dto.TableAppUserDto;
import auth_service.dto.WrapperEntityDto;
import auth_service.entityes.AppUser;
import auth_service.exception.BadPasswordOrUsernameException;
import auth_service.exception.EmailExistsException;
import auth_service.exception.PhoneNumberExistsException;
import auth_service.exception.PhoneNumberValidationException;
import auth_service.repositoryes.UserRepository;
import auth_service.utils.JwtTokenUtils;
import auth_service.validators.PhoneNumberValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashSet;

@Service
public class UserServiceV1Impl implements UserService {
    private final UserRepository userRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EntityManager entityManager;

    @Value("${app.credential.expired}")
    private Integer credentialExpired;

    @Value("${jwt.refresh.max_age}")
    private Integer refreshTokenMaxAge;



    public UserServiceV1Impl( UserRepository userRepository,
                             JwtTokenUtils jwtTokenUtils, AuthenticationManager authenticationManager,
                             BCryptPasswordEncoder passwordEncoder, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.jwtTokenUtils = jwtTokenUtils;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.entityManager = entityManager;
    }
    @Override
    public JwtDto.Response generateAccessToken(JwtDto.Request authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadPasswordOrUsernameException();
        }
        UserDetails userDetails = loadUserByUsername(authRequest.getEmail());
        JwtDto.Response responseEntity = new JwtDto.Response();
        responseEntity.setAccessToken(jwtTokenUtils.generateAccessToken(userDetails));
        return responseEntity;
    }

    @Override
    public AppUser registerUser(AppUserDto.Request.Create userDto) {
        if (userRepository.getAppUserByEmailAndIsLockedFalse(userDto.getEmail()).isPresent()){
            throw new EmailExistsException(userDto.getEmail());
        }
        if (userRepository.getAppUserByPhoneNumber(userDto.getPhoneNumber()).isPresent()){
            throw new PhoneNumberExistsException(userDto.getPhoneNumber());
        }
        AppUser usr = new AppUser();
        usr.setFirstname(userDto.getFirstname());
        usr.setLastname(userDto.getLastname());
        usr.setSurname(userDto.getSurname());
        usr.setEmail(userDto.getEmail());
        usr.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if (PhoneNumberValidator.validatePhoneNumber(userDto.getPhoneNumber())){
            usr.setPhoneNumber(userDto.getPhoneNumber());
        }else throw new PhoneNumberValidationException();
        usr.setExpiredCredentials(LocalDateTime.now().plusMonths(credentialExpired));
        usr.setRoles(new HashSet<>());
        return userRepository.save(usr);
    }

    @Override
    public ResponseEntity<WrapperEntityDto<TableAppUserDto>> getAllUser(int page) {
        WrapperEntityDto<TableAppUserDto> wrapper = new WrapperEntityDto<>();
        /*List<AppUser> userList = entityManager.createQuery("SELECT a from AppUser a",AppUser.class).getResultList();
        wrapper.setListWrapper(userList.stream().map(AppUserDto.Response.userTable::new).collect(Collectors.toList()));*/
        TypedQuery<TableAppUserDto> userTable = entityManager.createQuery("SELECT new auth_service.dto.TableAppUserDto(a.id, a.firstname, " +
                "a.surname, a.lastname, a.email, a.phoneNumber, a.isLocked, a.isEmailVerified, " +
                "a.phoneVerified) from AppUser a", TableAppUserDto.class);
        wrapper.setListWrapper(userTable.getResultList());
        return ResponseEntity.ok(wrapper);
    }

    /*В качестве идентификатора используем почту*/
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser= userRepository.getAppUserByEmailAndIsLockedFalse(email).orElseThrow(BadPasswordOrUsernameException::new);
        return new User(appUser.getEmail(),appUser.getPassword(),appUser.getAuthorities());
    }

    @Override
    public Boolean checkEmail(String email){
        return userRepository.getAppUsersByEmail(email).isEmpty();
    }

    @Override
    public Boolean checkPhoneNumber(String phoneNumber){
        return userRepository.getAppUserByPhoneNumber(phoneNumber).isEmpty();
    }


}
