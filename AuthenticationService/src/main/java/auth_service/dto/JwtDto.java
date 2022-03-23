package auth_service.dto;

import javax.validation.constraints.NotBlank;

public enum JwtDto {;
    private interface Email { @javax.validation.constraints.Email String getEmail(); }
    private interface Password { @NotBlank String getPassword(); }
    private interface AccessToken{String getAccessToken();}

    public static class Response implements AccessToken{
        private String accessToken;

        public Response() {
        }

        @Override
        public String getAccessToken() {
            return this.accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    public static class Request implements Email,Password{
        private String email;
        private String password;

        @Override
        public String getEmail() {
            return this.email;
        }

        public Request() {
        }

        @Override
        public String getPassword() {
            return this.password;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
