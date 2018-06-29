package saigontourist.pm1.vnpt.com.saigontourist.app.di;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import saigontourist.pm1.vnpt.com.saigontourist.R;

@Module(complete = false, library = true)
public class RestAdapterProviderModule {

    @Provides
    @Named("server_saigon")
    public Retrofit.Builder provideRestAdapterBuilderSaigon(Context context, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(context.getString(R.string.api_base_url_saigon_tourist))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

    @Provides
    @Named("server_vpoint")
    public Retrofit.Builder provideRestAdapterBuilderVpoint(Context context, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(context.getString(R.string.api_base_url_vpoint))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

}