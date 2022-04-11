package auth_service.service;

import auth_service.dto.jwt_dto.JwtDto;
import auth_service.entities.AppAuthority;
import auth_service.entities.AppUser;
import auth_service.entities.UserStatusEnum;
import auth_service.exception.BadPasswordOrUsernameException;
import auth_service.repositoryes.UserRepository;
import auth_service.utils.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class AuthServiceImplV1 implements AuthService{
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    public AuthServiceImplV1(AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils, UserRepository userRepository, EntityManager entityManager) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtils = jwtTokenUtils;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    @Override
    public JwtDto.Response generateAccessToken(JwtDto.Request authRequest) {
        Authentication authentication;
        try {
            authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadPasswordOrUsernameException();
        }
        JwtDto.Response responseEntity = new JwtDto.Response();
        responseEntity.setAccessToken(jwtTokenUtils.generateAccessToken(authentication.getAuthorities(), authentication.getName()));
        return responseEntity;
    }

    /*В качестве идентификатора используем почту*/
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser= userRepository.getAppUserByEmailAndStatusEquals(email, UserStatusEnum.ACTIVE).orElseThrow(BadPasswordOrUsernameException::new);
        Query query = entityManager.createQuery("select a from AppUser as u join AppRole as r on r.id=u.id " +
                "join AppAuthority as a on a.roles=r where u.id=:usrId");
        query.setParameter("usrId", appUser.getId());
        List<AppAuthority> authorities =query.getResultList();
        return new User(appUser.getEmail(),appUser.getPassword(),authorities);
    }
}
