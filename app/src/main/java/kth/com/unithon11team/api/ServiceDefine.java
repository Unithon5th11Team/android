package kth.com.unithon11team.api;

import com.vocketlist.android.network.converter.EnumParameterConverterFactory;
import com.vocketlist.android.network.converter.gson.GsonConverterFactory;
import com.vocketlist.android.network.service.HttpResponseErrorInterceptor;
import com.vocketlist.android.network.service.LazyMockInterceptor;
import com.vocketlist.android.network.service.LoggingInterceptor;
import com.vocketlist.android.network.service.WebkitCookieJar;
import com.vocketlist.android.network.utils.Timeout;

import kth.com.unithon11team.utils.AppApplication;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by kinamare on 2017-04-26.
 */

public final class ServiceDefine {
	public static final LazyMockInterceptor mockInterceptor = new LazyMockInterceptor();


	private static final String BASE_URL = "http://52.79.54.226:3434/";
	private static final String OPEN_API_URL = "http://openapi.seoul.go.kr:8088/";


	private ServiceDefine() {

	}

	public final static OkHttpClient.Builder mDefaultHttpClientBuilder = new OkHttpClient.Builder()
			.cookieJar(new WebkitCookieJar())
			.connectTimeout(Timeout.getConnectionTimeout(), Timeout.UNIT)
			.readTimeout(Timeout.getReadTimeout(), Timeout.UNIT)
			.addInterceptor(new LoginInterceptor())
			.addInterceptor(new HttpResponseErrorInterceptor())
			.addInterceptor(mockInterceptor)
			.addNetworkInterceptor(new LoggingInterceptor(AppApplication.getInstance().getContext()));


	public final static Retrofit retrofit = new Retrofit.Builder()
			.client(mDefaultHttpClientBuilder.build())
			.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
			.addConverterFactory(EnumParameterConverterFactory.create())
			.addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
			.build();

	public final static Retrofit openRetrofit = new Retrofit.Builder()
			.client(mDefaultHttpClientBuilder.build())
			.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
			.addConverterFactory(EnumParameterConverterFactory.create())
			.addConverterFactory(GsonConverterFactory.create()).baseUrl(OPEN_API_URL)
			.build();

}
