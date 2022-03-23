package auth_service.utils;

import javax.servlet.http.Cookie;

public class RefreshCookie extends Cookie {
    public RefreshCookie(String name, String value, int maxAge) {
        super(name, value);
        setHttpOnly(true);
        setMaxAge(maxAge);
    }
}
