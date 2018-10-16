package Remote;

import Model.Values;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Service
{
  @GET("/api/values/")
    Call<Values> getValues();
}
