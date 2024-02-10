package was.utils;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CookieSessionUtil {

    public static final String COOKIE_SESSION_NAME = "MyWas_ID";

    private static final Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    private CookieSessionUtil() {
    }

    public static String createSession(Object value) {
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);
        return sessionId;
    }

    public static Object getSession(String uuid) {
        return sessionStore.get(uuid);
    }
}
