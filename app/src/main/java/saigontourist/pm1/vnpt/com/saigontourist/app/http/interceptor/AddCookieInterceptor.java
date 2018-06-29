package saigontourist.pm1.vnpt.com.saigontourist.app.http.interceptor;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.LoginUserCookies;
import timber.log.Timber;

public class AddCookieInterceptor implements Interceptor {

    private LoginUserCookies loginUserCookies;

    public AddCookieInterceptor(LoginUserCookies loginUserCookies) {
        this.loginUserCookies = loginUserCookies;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        HashSet<String> cookies = loginUserCookies.get();
        StringBuilder sb = new StringBuilder();
        for (String cookie : cookies) {
            sb.append(cookie).append("; ");
        }

        if (sb.length() > 0) {
            Timber.tag("Cookies").d("sent: %s", sb.toString());
            builder.addHeader("Cookie", sb.toString());
        }

        return chain.proceed(builder.build());
    }

}