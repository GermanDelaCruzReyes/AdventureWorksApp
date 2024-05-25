package com.example.adventureworksapp;

import Network.ApiServiceSalesTerritory;
import Network.RetrofitClient;
import Network.SalesTerritory;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {
    private EditText etTerritoryID, etName, etCountryRegionCode, etGroup, etBuscarTerritorio;
    private Button btnSave, btnUpdate, btnDelete, btnSearch, btBack;
    private TextView eterror;
    private ApiServiceSalesTerritory apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = RetrofitClient.getClient("http://adventureworks.somee.com/api/");
        apiService = retrofit.create(ApiServiceSalesTerritory.class);

        etTerritoryID = (EditText) findViewById(R.id.etTerritoryID);
        etName = (EditText) findViewById(R.id.etName);
        etCountryRegionCode = (EditText) findViewById(R.id.etCountryRegionCode);
        etGroup = (EditText) findViewById(R.id.etGroup);
        etBuscarTerritorio = (EditText) findViewById(R.id.etBuscarTerritorio);

        btnSave = (Button) findViewById(R.id.btSave);
        btnUpdate = (Button) findViewById(R.id.btUpdate);
        btnDelete = (Button) findViewById(R.id.btDelete);
        btnSearch = (Button) findViewById(R.id.btBuscarTerritorio);
        btBack = (Button) findViewById(R.id.btPersonalTerritory);

        eterror = (TextView) findViewById(R.id.error);

        btnSave.setOnClickListener(v -> createTerritory());
        btnUpdate.setOnClickListener(v -> updateTerritory());
        btnDelete.setOnClickListener(v -> deleteTerritory());
        btnSearch.setOnClickListener(v -> getTerritoryById());

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }

    private void getTerritoryById(){
        String idStr = etBuscarTerritorio.getText().toString();
        if(!idStr.isEmpty()){
            int id = Integer.parseInt(idStr);
            apiService.getSalesTerritoryById(id).enqueue(new Callback<SalesTerritory>(){
                @Override
                public void onResponse(Call<SalesTerritory> call, Response<SalesTerritory> response){
                    if(response.isSuccessful()){
                        SalesTerritory territory = response.body();
                        if(territory != null){
                            etTerritoryID.setText(String.valueOf(territory.getTerritoryId()));
                            etName.setText(territory.getName());
                            etCountryRegionCode.setText(territory.getCountryRegionCode());
                            etGroup.setText(territory.getGroup());
                            Toast.makeText(MainActivity.this, "Territorio encontrado", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Territorio no encontrado", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<SalesTerritory> call, Throwable t){
                    Toast.makeText(MainActivity.this, "Error al obtener el territorio" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    eterror.setText(t.getMessage());
                }
            });
        } else {
            Toast.makeText(MainActivity.this, "Ingrese el ID del territorio", Toast.LENGTH_SHORT).show();
        }
    }

    private void createTerritory(){
        int territoryId = Integer.parseInt(etTerritoryID.getText().toString());
        String name = etName.getText().toString();
        String countryRegionCode = etCountryRegionCode.getText().toString();
        String groupTerritory = etGroup.getText().toString();
        SalesTerritory territory = new SalesTerritory();

        territory.setTerritoryId(territoryId);
        territory.setName(name);
        territory.setCountryRegionCode(countryRegionCode);
        territory.setGroup(groupTerritory);

        apiService.createSalesTerritory(territory).enqueue(new Callback<SalesTerritory>(){
            @Override
            public void onResponse(Call<SalesTerritory> call, Response<SalesTerritory> response){
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Territorio creado", Toast.LENGTH_SHORT).show();
                    etTerritoryID.setText("");
                    etName.setText("");
                    etCountryRegionCode.setText("");
                    etGroup.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Error al crear el territorio", Toast.LENGTH_SHORT).show();
                }
           }
            @Override
            public void onFailure(Call<SalesTerritory> call, Throwable t){
                Toast.makeText(MainActivity.this, "Error al crear el territorio", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateTerritory(){
        int territoryId = Integer.parseInt(etTerritoryID.getText().toString());
        String name = etName.getText().toString();
        String countryRegionCode = etCountryRegionCode.getText().toString();
        String groupTerritory = etGroup.getText().toString();
        SalesTerritory territory = new SalesTerritory();

        territory.setTerritoryId(territoryId);
        territory.setName(name);
        territory.setCountryRegionCode(countryRegionCode);
        territory.setGroup(groupTerritory);

        apiService.updateSalesTerritory(territoryId, territory).enqueue(new Callback<SalesTerritory>(){
            @Override
            public void onResponse(Call<SalesTerritory> call, Response<SalesTerritory> response){
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Territorio actualizado", Toast.LENGTH_SHORT).show();
                    etTerritoryID.setText("");
                    etName.setText("");
                    etCountryRegionCode.setText("");
                    etGroup.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Error al actualizar el territorio", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SalesTerritory> call, Throwable t){
                Toast.makeText(MainActivity.this, "Error al actualizar el territorio", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteTerritory(){
        int territoryId = Integer.parseInt(etTerritoryID.getText().toString());
        apiService.deleteSalesTerritory(territoryId).enqueue(new Callback<Void>(){
            @Override
            public void onResponse(Call<Void> call, Response<Void> response){
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Territorio eliminado", Toast.LENGTH_SHORT).show();
                    etTerritoryID.setText("");
                    etName.setText("");
                    etCountryRegionCode.setText("");
                    etGroup.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Error al eliminar el territorio", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t){
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}