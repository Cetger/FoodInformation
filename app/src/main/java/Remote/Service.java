package Remote;

import Model.Values;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service
{
  @GET("/api/values/")
    Call<Values> getValues();
  @POST("/api/values/")
    Call<Values> postValues(String parameters);
}
