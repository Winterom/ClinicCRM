package auth_service.service;


import auth_service.dto.role_dto.AppRoleForUserDto;
import auth_service.repositoryes.RoleRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoleServiceV1Impl implements RoleService{
    private final RoleRepository roleRepository;
    private final EntityManager entityManager;

    public RoleServiceV1Impl(RoleRepository roleRepository, EntityManager entityManager) {
        this.roleRepository = roleRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<AppRoleForUserDto> getRolesForUser(Long userId) {
        /*Первым запросом выбираем роли которые принадлежат пользователю*/
        Query query = entityManager.createQuery("select " +
                "new auth_service.dto.role_dto.AppRoleForUserDto(role.id,role.roleName,role.description,true ) " +
                "from AppRole as role left join role.appUser as user where user.id=:param");
        query.setParameter("param",userId);
        List<AppRoleForUserDto>list= query.getResultList();
        /*Первым запросом выбираем все оставшиеся роли которые не принадлежат пользователю*/
        Query query2 = entityManager.createQuery("select distinct " +
                "new auth_service.dto.role_dto.AppRoleForUserDto(role.id,role.roleName,role.description,false ) " +
                "from AppRole as role where role.id not in(:param2) and role.isLocked=false");
        query2.setParameter("param2",list.stream().map(AppRoleForUserDto::getId).collect(Collectors.toList()));
        list.addAll(query2.getResultList());
        return list;
    }
}
