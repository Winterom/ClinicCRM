package auth_service.service;


import auth_service.dto.AppUserDto;
import auth_service.dto.JwtDto;
import auth_service.dto.TableAppUser;
import auth_service.dto.WrapperEntityDto;
import auth_service.entityes.AppUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public interface UserService extends UserDetailsService {
   JwtDto.Response generateAccessToken(JwtDto.Request authRequest);
   AppUser registerUser(AppUserDto.Request.Create userDto);
   ResponseEntity<WrapperEntityDto<TableAppUser>> getAllUser(int page);
   Boolean checkEmail(String email);
   Boolean checkPhoneNumber(String email);

}
