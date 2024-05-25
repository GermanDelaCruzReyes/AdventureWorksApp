package Network;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiServiceSalesTerritory {
    @GET("salesTerritory/{id}")
    Call<SalesTerritory> getSalesTerritoryById(@Path("id") int id);

    @POST("salesTerritory")
    Call<SalesTerritory> createSalesTerritory(@Body SalesTerritory salesTerritory);

    @PUT("salesTerritory/{id}")
    Call<SalesTerritory> updateSalesTerritory(@Path("id") int id, @Body SalesTerritory salesTerritory);

    @DELETE("salesTerritory/{id}")
    Call<Void> deleteSalesTerritory(@Path("id") int id);
}
