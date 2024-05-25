package Network;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiServicePersonalTerritory {
    @GET("personterritory")
    Call<List<PersonalTerritory>> getPersonTerritories();

    @GET("personterritory/{id}")
    Call<PersonalTerritory> getPersonalTerritoryById(@Path("id") int id);

    @PUT("personterritory/{id}")
    Call<PersonalTerritory> updatePersonalTerritory(@Path("id") int id, @Body PersonalTerritory personalTerritory);

    @POST("personterritory")
    Call<PersonalTerritory> createPersonalTerritory(@Body PersonalTerritory personalTerritory);

    @DELETE("personterritory/{id}")
    Call<Void> deletePersonalTerritory(@Path("id") int id);
}
