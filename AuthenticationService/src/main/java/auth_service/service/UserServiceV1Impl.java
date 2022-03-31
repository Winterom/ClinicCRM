package auth_service.service;

import auth_service.dto.AppUserDto;
import auth_service.dto.JwtDto;
import auth_service.dto.PaginationEntity;
import auth_service.entities.AppUser;
import auth_service.exception.BadPasswordOrUsernameException;
import auth_service.exception.EmailExistsException;
import auth_service.exception.PhoneNumberExistsException;
import auth_service.exception.PhoneNumberValidationException;
import auth_service.repositoryes.UserRepository;
import auth_service.repositoryes.specification.AppUserSpecification;
import auth_service.utils.JwtTokenUtils;
import auth_service.validators.FieldNameChecker;
import auth_service.validators.PhoneNumberValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


@Service("SimpleUserService")
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
    public AppUser registerUser(AppUserDto.Request.Create userDto,HttpServletRequest request) {
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


    /*В качестве идентификатора используем почту*/
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser= userRepository.getAppUserByEmailAndIsLockedFalse(email).orElseThrow(BadPasswordOrUsernameException::new);
        return new User(appUser.getEmail(),appUser.getPassword(),appUser.getAuthorities());
    }

    @Override
    public Boolean checkEmail(String email, HttpServletRequest request){
        return userRepository.getAppUsersByEmail(email).isEmpty();
    }

    @Override
    public Boolean checkPhoneNumber(String phoneNumber,HttpServletRequest request){
        return userRepository.getAppUserByPhoneNumber(phoneNumber).isEmpty();
    }

    @Override
    public PaginationEntity<AppUserDto.Response.userTable> getAllUser(Integer page, Integer itemInPage, String sortField, boolean directSort, String searchField, String searchValue,HttpServletRequest request) {
        /*ASC по возрастанию Desc убывание*/
        Sort.Direction direction =Sort.Direction.ASC;
        if (!directSort){
            direction = Sort.Direction.DESC;
        }
        /* По умолчанию сортируем по полю id*/
        Sort sort;
        String field = FieldNameChecker.checkFieldName(AppUser.class,sortField);
        if (field!=null){
             sort = Sort.by(direction,sortField);
        }else {
            sort = Sort.by(direction,"id");
        }
        /*Добавляем спецификацию исходя из параметров запроса*/
        Specification<AppUser> spec;
        if (searchValue!=null){
            spec= Specification.where(AppUserSpecification.valueLike(searchField,searchValue));
        }else{
            spec = Specification.where(null);
        }
        Page<AppUser> pageable = userRepository.findAll(spec, PageRequest.of(page - 1, itemInPage,sort));
        List<AppUserDto.Response.userTable> result = pageable.getContent().stream().map(AppUserDto.Response.userTable::new).collect(Collectors.toList());
        return new PaginationEntity<>(pageable.getTotalPages(), page, result);
    }


}
