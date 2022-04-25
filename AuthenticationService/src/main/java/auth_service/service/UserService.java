package auth_service.service;


import auth_service.dto.PaginationEntity;
import auth_service.dto.user_dto.AppUserDto;
import auth_service.entities.UserStatusEnum;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public interface UserService {
   AppUserDto.Response.UserProfile getUserById(Long id);
   Boolean checkEmail(String email);
   Boolean checkPhoneNumber(String phoneNumber);

   PaginationEntity<AppUserDto.Response.UserTable> getAllUser(Integer page, Integer itemInPage, String sortField,
                                                              boolean directSort, String searchField, String searchValue, Set<UserStatusEnum> status);
   void updateAppUser(AppUserDto.Request.CreateOrUpdate userDto);
}
