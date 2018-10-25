package Remote;

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


}
