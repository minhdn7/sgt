package saigontourist.pm1.vnpt.com.saigontourist.ui.view.user;

import android.graphics.Bitmap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.View;

/**
 * Created by MinhDN on 3/5/2018.
 */
public interface GetCapChaView extends View {
    void onLoadCapChaSuccess(Bitmap response);
    void onLoadCapChaError(Throwable e);
}