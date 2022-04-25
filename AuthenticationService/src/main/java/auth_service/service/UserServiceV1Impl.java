package auth_service.service;

import auth_service.dto.PaginationEntity;
import auth_service.dto.user_dto.AppUserDto;
import auth_service.entities.AppUser;
import auth_service.entities.UserStatusEnum;
import auth_service.exception.AppUserNotFoundException;

import auth_service.repositoryes.UserRepository;
import auth_service.repositoryes.specification.AppUserSpecification;
import auth_service.validators.FieldNameChecker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service("SimpleUserService")
public class UserServiceV1Impl  implements UserService{
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;
    private final EntityManager entityManager;

    @Value("${app.credential.expired}")
    private Integer credentialExpired;




    public UserServiceV1Impl( UserRepository userRepository,
                             BCryptPasswordEncoder passwordEncoder, EntityManager entityManager) {
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
        this.entityManager = entityManager;
    }


    @Override
    public AppUserDto.Response.UserProfile getUserById(Long id) {
        AppUser user = userRepository.getAppUserById(id).orElseThrow(AppUserNotFoundException::new);
        return new AppUserDto.Response.UserProfile(user);
    }


    @Override
    @Transactional
    public void updateAppUser(AppUserDto.Request.CreateOrUpdate userDto) {

        if(userDto.getId()==null||userDto.getPassword()!=null){
            AppUser usr = userDto.getUserFromDto(passwordEncoder, this.credentialExpired);
            entityManager.persist(usr);
            entityManager.flush();

        }

        Query query = entityManager.createQuery("update AppUser as u  set u.surname =:surname,u.firstname=:firstname," +
                "u.lastname=:lastname, u.email=:email,u.phoneNumber=:phone, " +
                "u.status=:status, u.updatedAt=current_timestamp  where u.id=:id");

        query.setParameter("id",userDto.getId());
        query.setParameter("surname",userDto.getSurname());
        query.setParameter("firstname",userDto.getFirstname());
        if (userDto.getLastname()==null){
            userDto.setLastname(" ");
        }
        query.setParameter("lastname",userDto.getLastname());
        query.setParameter("email",userDto.getEmail());
        query.setParameter("phone",userDto.getPhoneNumber());
        query.setParameter("status", userDto.getStatus());
        query.executeUpdate();
    }



    @Override
    public Boolean checkEmail(String email){
        return userRepository.getAppUsersByEmail(email).isEmpty();
    }

    @Override
    public Boolean checkPhoneNumber(String phoneNumber){
        return userRepository.getAppUserByPhoneNumber(phoneNumber).isEmpty();
    }

    @Override
    public PaginationEntity<AppUserDto.Response.UserTable> getAllUser(Integer page, Integer itemInPage, String sortField, boolean directSort,
                                                                      String searchField, String searchValue, Set<UserStatusEnum> status
                                                                      ) {
        if(page==null){
            page=1;
        }
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
        /*Если строка запроа пуста то спецификацию не добавляем*/
        if (searchValue!=null){
            spec= Specification.where(AppUserSpecification.valueLike(searchField,searchValue));
        }else{
            spec = Specification.where(null);
        }
        Specification<AppUser> specStatus = Specification.where(AppUserSpecification.statusValue(status));
        Page<AppUser> pageable = userRepository.findAll(spec.and(specStatus), PageRequest.of(page - 1, itemInPage,sort));
        List<AppUserDto.Response.UserTable> result = pageable.getContent().stream().map(AppUserDto.Response.UserTable::new).collect(Collectors.toList());
        return new PaginationEntity<>(pageable.getTotalPages(), page, result);
    }


}
