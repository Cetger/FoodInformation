package Remote;

import Model.CategoryClass;
import Model.CategoryLanguage;
import Model.CreateUserClass;
import Model.ErrorClass;
import Model.LanguagesClass;
import Model.LoginClass;
import Model.Parameter;
import Model.PostResponse;
import Model.Values;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service
{
  @GET("/api/values/")
    Call<Values> getValues();
  @POST("/api/values/")
  Call<PostResponse> getResponse(@Body Parameter Value);
  @POST("/api/values/")
  Call<Values> postBarcodeNumber(@Body String value);
  @POST("/api/User/CreateUser/")
  Call<CreateUserClass> CreateUser(@Body CreateUserClass value);
  @POST("/api/Login/CheckUserOnLogin/")
  Call<LoginClass> Login(@Body LoginClass value);
  @POST("/api/Language/GetLanguageList/")
  Call<LanguagesClass> Languages();
  @POST("/api/Error/GetErrorList/")
  Call<ErrorClass> Errors();
  @POST("/api/Category/GetProductCategoriesByLanguageCode/")
  Call<CategoryClass> Categories(@Body CategoryLanguage categoryLanguage);
}
