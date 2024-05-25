package com.example.adventureworksapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import Network.RetrofitClient;
import Network.PersonalTerritory;
import Network.ApiServicePersonalTerritory;


public class MainActivity2 extends AppCompatActivity {
    private EditText etBusinessEntityId, etTerritoryId, etSalesQuota, etBonus, etComissionPct, etBuscarPersonal;
    private Button btnSave, btnUpdate, btnDelete, btnSearch, btBack;

    private ApiServicePersonalTerritory apiService;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        Retrofit retrofit = RetrofitClient.getClient("http://adventureworks.somee.com/api/");
        apiService = retrofit.create(ApiServicePersonalTerritory.class);

        etBusinessEntityId = findViewById(R.id.etBusinessEntityID);
        etTerritoryId = findViewById(R.id.etTerritoryID);
        etSalesQuota = findViewById(R.id.etSalesQuota);
        etBonus = findViewById(R.id.etBonus);
        etComissionPct = findViewById(R.id.etCommissionPct);
        etBuscarPersonal = findViewById(R.id.etBuscarPersonal);

        btnSave = findViewById(R.id.btnAsignarPersonal);
        btnUpdate = findViewById(R.id.btUpdate);
        btnDelete = findViewById(R.id.btDelete);
        btnSearch = findViewById(R.id.btBuscarPersonal);
        btBack = findViewById(R.id.btBack);

        btnSearch.setOnClickListener(v -> getPersonalTerritory());
        btnSave.setOnClickListener(v -> createPersonalTerritory());
        btnUpdate.setOnClickListener(v -> updatePersonalTerritory());
        btnDelete.setOnClickListener(v -> deletePersonalTerritory());

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void getPersonalTerritory() {
        String idStr = etBuscarPersonal.getText().toString();
        if(!idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            apiService.getPersonalTerritoryById(id).enqueue(new Callback<PersonalTerritory>(){
                @Override
                public void onResponse(Call<PersonalTerritory> call, Response<PersonalTerritory> response) {
                    if(response.isSuccessful()) {
                        PersonalTerritory personalTerritory = response.body();
                        if(personalTerritory != null) {
                            etBusinessEntityId.setText(String.valueOf(personalTerritory.getBusinessEntityId()));
                            etTerritoryId.setText(String.valueOf(personalTerritory.getTerritoryId()));
                            etSalesQuota.setText(String.valueOf(personalTerritory.getSalesQuota()));
                            etBonus.setText(String.valueOf(personalTerritory.getBonus()));
                            etComissionPct.setText(String.valueOf(personalTerritory.getCommissionPct()));
                            Toast.makeText(MainActivity2.this, "PersonalTerritory encontrado", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity2.this, "PersonalTerritory no encontrado", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity2.this, "Error al obtener PersonalTerritory", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<PersonalTerritory> call, Throwable t) {
                    Toast.makeText(MainActivity2.this, "Error al obtener PersonalTerritory2", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(MainActivity2.this, "Ingrese un ID válido", Toast.LENGTH_SHORT).show();
        }
    }

    private void createPersonalTerritory() {
        int businessEntityid = Integer.parseInt(etBusinessEntityId.getText().toString());
        int territoryId = Integer.parseInt(etTerritoryId.getText().toString());
        double salesQuota = Double.parseDouble(etSalesQuota.getText().toString());
        double bonus = Double.parseDouble(etBonus.getText().toString());
        double commissionPct = Double.parseDouble(etComissionPct.getText().toString());
        PersonalTerritory personalTerritory = new PersonalTerritory();

        personalTerritory.setBusinessEntityId(businessEntityid);
        personalTerritory.setTerritoryId(territoryId);
        personalTerritory.setSalesQuota(salesQuota);
        personalTerritory.setBonus(bonus);
        personalTerritory.setCommissionPct(commissionPct);

        apiService.createPersonalTerritory(personalTerritory).enqueue(new Callback<PersonalTerritory>() {
            @Override
            public void onResponse(Call<PersonalTerritory> call, Response<PersonalTerritory> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(MainActivity2.this, "PersonalTerritory creado", Toast.LENGTH_SHORT).show();
                    etBusinessEntityId.setText("");
                    etTerritoryId.setText("");
                    etSalesQuota.setText("");
                    etBonus.setText("");
                    etComissionPct.setText("");
                } else {
                    Toast.makeText(MainActivity2.this, "Error al crear PersonalTerritory", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<PersonalTerritory> call, Throwable t) {
                Toast.makeText(MainActivity2.this, "Error al crear PersonalTerritory", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updatePersonalTerritory() {
        int businessEntityid = Integer.parseInt(etBusinessEntityId.getText().toString());
        int territoryId = Integer.parseInt(etTerritoryId.getText().toString());
        double salesQuota = Double.parseDouble(etSalesQuota.getText().toString());
        double bonus = Double.parseDouble(etBonus.getText().toString());
        double commissionPct = Double.parseDouble(etComissionPct.getText().toString());
        PersonalTerritory personalTerritory = new PersonalTerritory();

        personalTerritory.setBusinessEntityId(businessEntityid);
        personalTerritory.setTerritoryId(territoryId);
        personalTerritory.setSalesQuota(salesQuota);
        personalTerritory.setBonus(bonus);
        personalTerritory.setCommissionPct(commissionPct);

        apiService.updatePersonalTerritory(businessEntityid, personalTerritory).enqueue(new Callback<PersonalTerritory>(){
            @Override
            public void onResponse(Call<PersonalTerritory> call, Response<PersonalTerritory> response) {
                if(response.isSuccessful()) {
                Toast.makeText(MainActivity2.this, "PersonalTerritory actualizado", Toast.LENGTH_SHORT).show();
                etBusinessEntityId.setText("");
                etTerritoryId.setText("");
                etSalesQuota.setText("");
                etBonus.setText("");
                etComissionPct.setText("");
                } else {
                    Toast.makeText(MainActivity2.this, "Error al actualizar PersonalTerritory", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<PersonalTerritory> call, Throwable t) {
                Toast.makeText(MainActivity2.this, "Error al actualizar PersonalTerritory", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deletePersonalTerritory() {
        int businessEntityid = Integer.parseInt(etBusinessEntityId.getText().toString());
        apiService.deletePersonalTerritory(businessEntityid).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity2.this, "PersonalTerritory eliminado", Toast.LENGTH_SHORT).show();
                    etBusinessEntityId.setText("");
                    etTerritoryId.setText("");
                    etSalesQuota.setText("");
                    etBonus.setText("");
                    etComissionPct.setText("");
                } else {
                    Toast.makeText(MainActivity2.this, "Error al eliminar PersonalTerritory", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}