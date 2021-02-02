package com.example.advanced_retrofit_using_real_api;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.advanced_retrofit_using_real_api.DataRelationshipForTeamAndPlayers.PlayerArrayDataListClass;
import com.example.advanced_retrofit_using_real_api.DataRelationshipForTeamAndPlayers.PlayerObjectDesignClass;
import com.example.advanced_retrofit_using_real_api.DataRelationshipForTeamAndPlayers.PlayerPositionClass;
import com.example.advanced_retrofit_using_real_api.DataRelationshipForTeamAndPlayers.TeamClass;
import com.example.advanced_retrofit_using_real_api.GET_RequestCode.ContinentDataClass;
import com.example.advanced_retrofit_using_real_api.GET_RequestCode.ContinentListDataClass;
import com.example.advanced_retrofit_using_real_api.GET_RequestCode.ContinentObjectDataClass;
import com.example.advanced_retrofit_using_real_api.POST_RequestCode.DataObjectClass;
import com.example.advanced_retrofit_using_real_api.POST_RequestCode.LastLoginObject;
import com.example.advanced_retrofit_using_real_api.POST_RequestCode.MainObjectClass;
import com.example.advanced_retrofit_using_real_api.POST_RequestCode.POST_Method_with_FormUrlEncode.CreateUserClass;
import com.example.advanced_retrofit_using_real_api.POST_RequestCode.Response.MainResponseObjectClass;
import com.example.advanced_retrofit_using_real_api.PUT_PATCH_DELETE_RequestCode.ObjectStructure;
import com.example.advanced_retrofit_using_real_api.Upload_Image_with_Retrofit.ResponseImageClass;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    int cid;
    RetrofitClient retrofitClient;
    LastLoginObject lastLoginObject;
    DataObjectClass dataObjectClass;
    private String token_for_AllContinetsDataFromList = "BwneYo1rFZRD6mzhC00plOusGGOGFueIq1HpgY7mUOzXF5ktAXvuBtcD4Rbg";
    private String token_for_PostRequest = "XWwJgvpNqmNT9NtxWuz3mw";
    private String BaseUrlForGET = "https://cricket.sportmonks.com/api/v2.0/";
    private String BaseUrlForPOST = "https://app.fakejson.com/";
    private String BaseUrlForJSONPlaceholder = "https://jsonplaceholder.typicode.com/";
    private String BaseUrlForImageUploadServer = "https://joy-technologies-ltd.com/test/";
    ImageView imageView;
    private static final int CAPTURE_REQUEST_CODE = 0;
    private static final int UPLOAD_REQUEST_CODE = 1;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageViewId);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Uploading.....");

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        
        Retrofit retrofit = new Retrofit
                .Builder()
//                .baseUrl(BaseUrlForGET)
//                .baseUrl(BaseUrlForPOST)
//                .baseUrl(BaseUrlForJSONPlaceholder)
                .baseUrl(BaseUrlForImageUploadServer)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitClient = retrofit.create(RetrofitClient.class);
//        deleteData();
//        updateUserInfoPATCH();
//        updateUserInfoPUT();
//        createUserFormUrl();
//        postValueMethod();
//        getAllContinentsDataFromList();
//        getContinentDataById();
//        getTeamData();
    }

    // Click capture image button to open camera and capture photo
    public void captureImage(View v){
        if(CheckPermission()) {
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(captureIntent, CAPTURE_REQUEST_CODE);
        }
    }

    // Click upload image button to upload image from gallery
    public void uploadImage(View v){
        if(CheckPermission()){
            Intent uploadIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(uploadIntent, UPLOAD_REQUEST_CODE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CAPTURE_REQUEST_CODE: {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);

                progressDialog.show();
                imageUploadToServer(bitmap);
            } break;

            case UPLOAD_REQUEST_CODE: {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imageView.setImageBitmap(bitmap);

                    progressDialog.show();
                    imageUploadToServer(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } break;
        }
    }

    // Upload to server through Retrofit
    private void imageUploadToServer(Bitmap bitmap){
        // Compress image file size and Convert image-byte into string in order to store in api
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        String imageString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
        String imageName = "Redwan_" + Calendar.getInstance().getTimeInMillis();

        Call<ResponseImageClass> call = retrofitClient.CaptureUploadImage(imageName, imageString);
        call.enqueue(new Callback<ResponseImageClass>() {
            @Override
            public void onResponse(Call<ResponseImageClass> call, Response<ResponseImageClass> response) {
                Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseImageClass> call, Throwable t) {
                Log.d("Response ", t.getMessage());
                progressDialog.dismiss();
            }
        });
    }

    // Check camera and storage permission
    public boolean CheckPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Permission")
                        .setMessage("Please accept required permissions")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_LOCATION);

                                startActivity(new Intent(MainActivity
                                        .this, MainActivity.class));
                                MainActivity.this.overridePendingTransition(0, 0);
                            }
                        })
                        .create().show();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else { return true; }
    }

    // From now all of the below codes are to GET, POST, PUT, PATCH and DELETE data from REST API
    // DELETE
    private void deleteData(){
        Call<Void> call = retrofitClient.deleteRequest(5);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200){
                    Log.d("Deleted Id ", "5");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Response ", "Failure");
            }
        });
    }

    // PATCH
    private void updateUserInfoPATCH(){
        ObjectStructure objectStructure = new ObjectStructure(null, "App seldom crashes", 3);
        Call<ObjectStructure> call = retrofitClient.patchRequest(5, objectStructure);
        call.enqueue(new Callback<ObjectStructure>() {
            @Override
            public void onResponse(Call<ObjectStructure> call, Response<ObjectStructure> response) {
                Log.d("User Id ", String.valueOf(response.body().getUserId()));
                Log.d("Title ", response.body().getTitle());
                Log.d("Body ", response.body().getBody());
            }

            @Override
            public void onFailure(Call<ObjectStructure> call, Throwable t) {
                Log.d("Response ", "Failure");
            }
        });
    }

    // PUT
    private void updateUserInfoPUT(){
        ObjectStructure objectStructure = new ObjectStructure("", "App never crashes", 3);
        Call<ObjectStructure> call = retrofitClient.putRequest(5, objectStructure);
        call.enqueue(new Callback<ObjectStructure>() {
            @Override
            public void onResponse(Call<ObjectStructure> call, Response<ObjectStructure> response) {
                Log.d("User Id ", String.valueOf(response.body().getUserId()));
                Log.d("Title ", response.body().getTitle());
                Log.d("Body ", response.body().getBody());
            }

            @Override
            public void onFailure(Call<ObjectStructure> call, Throwable t) {
                Log.d("Response ", "Failure");
            }
        });
    }

    // POST
    private void createUserFormUrl(){
        Call<CreateUserClass> call = retrofitClient.createUser(8737, "Crash Report", "App performs well, doesn't crash");
        call.enqueue(new Callback<CreateUserClass>() {
            @Override
            public void onResponse(Call<CreateUserClass> call, Response<CreateUserClass> response) {
                Log.d("User Id ", String.valueOf(response.body().getUserId()));
                Log.d("Title ", response.body().getTitle());
                Log.d("Body ", response.body().getBody());
            }

            @Override
            public void onFailure(Call<CreateUserClass> call, Throwable t) {
                Log.d("Response ", "Failure");
            }
        });
    }

    // POST
    private void postValueMethod(){
        lastLoginObject = new LastLoginObject("dateTime|UNIX", "internetIP4");
        dataObjectClass = new DataObjectClass("name", "personNickname", "internetEmail",
                "personGender", lastLoginObject);
        MainObjectClass mainObjectClass = new MainObjectClass(token_for_PostRequest, dataObjectClass);
        Call<MainResponseObjectClass> mainResponse = retrofitClient.getPostValue(mainObjectClass);

        mainResponse.enqueue(new Callback<MainResponseObjectClass>() {
            @Override
            public void onResponse(Call<MainResponseObjectClass> call, Response<MainResponseObjectClass> response) {
                String name = response.body().getName();
                String id = response.body().getId();
                String email = response.body().getEmail();
                String gender = response.body().getGender();

                LastLoginObject obj = response.body().getLast_login();
                String timeDate = obj.getDate_time();
                String ip = obj.getIp4();

                Log.d("Name ", name);
                Log.d("Id ", id);
                Log.d("Email ", email);
                Log.d("Gender ", gender);
                Log.d("Date & Time ", timeDate);
                Log.d("IP4 ", ip);
            }

            @Override
            public void onFailure(Call<MainResponseObjectClass> call, Throwable t) {
                Log.d("Failure", t.getMessage());
            }
        });
    }

    // GET
    private void getTeamData() {
        Call<TeamClass> call = retrofitClient.getTeamData(10, token_for_AllContinetsDataFromList);
        call.enqueue(new Callback<TeamClass>() {
            @Override
            public void onResponse(Call<TeamClass> call, Response<TeamClass> response) {
                if(response.isSuccessful()){
                    cid = response.body().getCountry_Id();
                    String cname = response.body().getCountryName();

                    Call<PlayerArrayDataListClass> callPlayer = retrofitClient.getPlayerData(token_for_AllContinetsDataFromList, cid);
                    callPlayer.enqueue(new Callback<PlayerArrayDataListClass>() {
                        @Override
                        public void onResponse(Call<PlayerArrayDataListClass> call, Response<PlayerArrayDataListClass> response) {
                            List<PlayerObjectDesignClass> list =  response.body().getData();
                            for(PlayerObjectDesignClass obj : list){
                                String fullname = obj.getFullname();
                                String dateOfBirth = obj.getDateofbirth();
                                String gender = obj.getGender();

                                PlayerPositionClass playerPositionClass = obj.getPosition();
                                String positionName = playerPositionClass.getName();
                                int pid = playerPositionClass.getId();

                                Log.d("Country name ", String.valueOf(cname));
                                Log.d("Country Id ", String.valueOf(cid));
                                Log.d("Player name ", String.valueOf(fullname));
                                Log.d("Date of Birth ", String.valueOf(dateOfBirth));
                                Log.d("Gender ", String.valueOf(gender));
                                Log.d("Player position ", String.valueOf(positionName));
                                Log.d("Position Id ", String.valueOf(pid) + "\n\n");
                            }
                        }

                        @Override
                        public void onFailure(Call<PlayerArrayDataListClass> call, Throwable t) {
                            Log.d("Player Response", "Failure for " + t.getMessage());
                        }
                    });
                } else {
                    Log.d("Response", "Failure");
                }
            }

            @Override
            public void onFailure(Call<TeamClass> call, Throwable t) {
                Log.d("Team Response", "Failure for " + t.getMessage());
            }
        });
    }

    // GET
    private void getAllContinentsDataFromList(){
        Call<ContinentListDataClass> call = retrofitClient.getData(token_for_AllContinetsDataFromList);
        call.enqueue(new Callback<ContinentListDataClass>() {
            @Override
            public void onResponse(Call<ContinentListDataClass> call, Response<ContinentListDataClass> response) {
                if(response.isSuccessful()){
                    List<ContinentDataClass> dataList = response.body().getData();

                    for(ContinentDataClass continentDataClass : dataList){
                        Log.d("Resource ", continentDataClass.getResource());
                        Log.d("ID ", String.valueOf(continentDataClass.getId()));
                        Log.d("Name ", continentDataClass.getName());
                        Log.d("Updated at ", continentDataClass.getUpdated_at());
                    }
                } else {
                    Log.d("Response", "Failure");
                }
            }

            @Override
            public void onFailure(Call<ContinentListDataClass> call, Throwable t) {
                Log.d("Response", "Failure");
            }
        });
    }

    // GET
    private void getContinentDataById(){
        Call<ContinentObjectDataClass> call = retrofitClient.getData(2, token_for_AllContinetsDataFromList);
        call.enqueue(new Callback<ContinentObjectDataClass>() {
            @Override
            public void onResponse(Call<ContinentObjectDataClass> call, Response<ContinentObjectDataClass> response) {
                if(response.isSuccessful()){
                    ContinentDataClass continentDataClass = response.body().getData();

                    Log.d("Resource ", continentDataClass.getResource());
                    Log.d("ID ", String.valueOf(continentDataClass.getId()));
                    Log.d("Name ", continentDataClass.getName());
                    Log.d("Updated at ", continentDataClass.getUpdated_at());
                } else {
                    Log.d("Response", "Failure");
                }
            }

            @Override
            public void onFailure(Call<ContinentObjectDataClass> call, Throwable t) {
                Log.d("Response", "Failure");
            }
        });
    }
}
