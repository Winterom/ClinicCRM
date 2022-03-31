package auth_service.service;


import auth_service.dto.AppUserDto;
import auth_service.dto.JwtDto;
import auth_service.dto.PaginationEntity;
import auth_service.entities.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public interface UserService extends UserDetailsService {
   JwtDto.Response generateAccessToken(JwtDto.Request authRequest);
   AppUser registerUser(AppUserDto.Request.Create userDto, HttpServletRequest request);
   Boolean checkEmail(String email,HttpServletRequest request);
   Boolean checkPhoneNumber(String phoneNumber,HttpServletRequest request);
   PaginationEntity<AppUserDto.Response.userTable> getAllUser(Integer page, Integer itemInPage, String sortField, boolean directSort, String searchField, String searchValue,HttpServletRequest request);
}
