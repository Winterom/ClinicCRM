package auth_service.exception;

public class AppRoleNotFoundException extends RuntimeException{
    public AppRoleNotFoundException(String roleName, Long id) {
        super(geErrorMessage(roleName,id));
    }

    private static String geErrorMessage (String roleName, Long id){
        StringBuilder errMessage = new StringBuilder("Not found role with ");
        if (roleName !=null){
            errMessage.append("name ").append(roleName);
        }
        if (id != null){
            errMessage.append("id ").append(id);
        }
        return errMessage.toString();
    }
}
