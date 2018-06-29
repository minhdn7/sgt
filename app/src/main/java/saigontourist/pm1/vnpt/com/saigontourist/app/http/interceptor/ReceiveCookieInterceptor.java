package saigontourist.pm1.vnpt.com.saigontourist.app.http.interceptor;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;
import saigontourist.pm1.vnpt.com.saigontourist.domain.repository.LoginUserCookies;
import timber.log.Timber;

public class ReceiveCookieInterceptor implements Interceptor {

    private LoginUserCookies loginUserCookies;

    public ReceiveCookieInterceptor(LoginUserCookies loginUserCookies) {
        this.loginUserCookies = loginUserCookies;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        List<String> headers = response.headers("Set-Cookie");
        if (headers != null && !headers.isEmpty()) {
            HashSet<String> cookies = new HashSet<String>();
            for (String cookie : headers) {
                Timber.tag("Cookies").d("received: %s", cookie);
                String[] values = cookie.split(";");
                if (values.length > 0) {
                    cookies.add(values[0]);
                }
            }
            loginUserCookies.put(cookies);
        }
        return response;
    }

}