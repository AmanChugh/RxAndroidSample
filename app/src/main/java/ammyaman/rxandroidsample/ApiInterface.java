package ammyaman.rxandroidsample;


import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by CH-E01449 on 03-08-2017.
 */

public interface ApiInterface {


    @POST("Login")
    Observable<String> getLoginData(@Body HashMap<String, String> requestData);

    @POST("HomeApiN")
    Observable<String> getHomeData(@Body HashMap<String, String> requestData);
}
