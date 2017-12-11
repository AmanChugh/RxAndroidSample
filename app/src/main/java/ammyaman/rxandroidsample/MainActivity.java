package ammyaman.rxandroidsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "your_base_url";
    private ApiInterface mApiInterface;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);

            Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();

            mApiInterface = retrofit.create(ApiInterface.class);
    }

    public void onLogin(View view) {
        callLoginApi(username.getText().toString(),password.getText().toString());
    }

    public void callLoginApi(String username,String password) {
        HashMap<String,String> data = new HashMap<>();
        data.put("username",username);
        data.put("password",password);


        mApiInterface.getLoginData(data).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).cache()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // show progressbar
                    }

                    @Override
                    public void onNext(String value) {
//hide progressbar
                        Log.d("response",value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
