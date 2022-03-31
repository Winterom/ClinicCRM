package auth_service.service;


import auth_service.dto.AppUserDto;
import auth_service.dto.JwtDto;
import auth_service.dto.PaginationEntity;
import auth_service.entities.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService extends UserDetailsService {
   JwtDto.Response generateAccessToken(JwtDto.Request authRequest);
   AppUser registerUser(AppUserDto.Request.Create userDto);
   Boolean checkEmail(String email);
   Boolean checkPhoneNumber(String email);
   PaginationEntity<AppUserDto.Response.userTable> getAllUser(Integer page, Integer itemInPage, String sortField, boolean directSort, String searchField, String searchValue);
}
