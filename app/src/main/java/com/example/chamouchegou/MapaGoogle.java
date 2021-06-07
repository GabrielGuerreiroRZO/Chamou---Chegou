package com.example.chamouchegou;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapaGoogle extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    FusedLocationProviderClient servicoLocalizacao;
    private double latitude, longitude;
    private boolean permitiuGPS = false;
    Location ultimaPosicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_google);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        LocationManager gpsHabilitado = (LocationManager) getSystemService(LOCATION_SERVICE);
        servicoLocalizacao = LocationServices.getFusedLocationProviderClient(this);
        getLasLocation();
        if (!gpsHabilitado.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            Toast.makeText(getApplicationContext(), "Ative o GPS ",Toast.LENGTH_LONG).show();

        }


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION},120);
        }else {
            permitiuGPS = true;
        }



        servicoLocalizacao = LocationServices.getFusedLocationProviderClient(this);

    }

    private void getLasLocation() {
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        recuperarPosicaoAtual();
        adicionarComponentesVisuais();
    }

    private  void recuperarPosicaoAtual() {
        try {
            if (permitiuGPS) {
                Task locationResult = servicoLocalizacao.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            ultimaPosicao = (Location) task.getResult();
                            if (ultimaPosicao != null) {
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(ultimaPosicao.getLatitude(), ultimaPosicao.getLongitude()), 15));
                            } else {
                                Toast.makeText(getApplicationContext(), "Falha recuperar informação", Toast.LENGTH_LONG).show();
                                Log.e("TESTE_GPS", "Execption: %s", task.getException());
                            }

                        }
                    }
                });
            }
        }catch(SecurityException e){
            Log.e("TESTE_GPS", e.getMessage());
        }
    }

    private  void adicionarComponentesVisuais(){
        if (mMap ==  null){
            return;
        }
        try {
            if (permitiuGPS){
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);

                ultimaPosicao = null;
                if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},120);
                }
            }
        }catch (SecurityException e){
            Log.e("Exception %s", e.getMessage());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int errorCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        switch (errorCode) {
            case ConnectionResult.SERVICE_MISSING: // Verifica se o serviço esta disponivel no aparelho
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED: // Verifica se o google service esta atualizado.
            case ConnectionResult.API_DISABLED: // Verifica se o serviço esta desabilitado.
                Log.d("Teste", "Google Play Service Desabilitado.");
                GoogleApiAvailability.getInstance().getErrorDialog(this, errorCode, 0, new DialogInterface.OnCancelListener() { // Inicio: Exibe menssagem de erro para o usuario
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        finish();
                    }
                }).show(); // Fim
                break;
            case ConnectionResult.SUCCESS:
                Log.d("Teste", "Serviço OK");
                break;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        servicoLocalizacao.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){ // Verifica se a localização esta presente. imprimindo a localização
                    Log.i("Teste", location.getLatitude() + "   " + location.getLongitude());
                }else {
                    Log.i("Teste", "Sem dados");
                }
                // Verificando a ultima localização e exibindo caso consiga.
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
        LocationRequest locationRequest = LocationRequest.create();
        //locationRequest.setInterval(15 = 1000); // Intervalo a cada 15 segundos para buscar ultima localização
        //locationRequest.setFastestInterval(5 = 1000); // Busca Localizações disponiveis
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY); // Maior precisao possivel. 100 metros

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(builder.build()).addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException){
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    try {
                        resolvable.startResolutionForResult(MapaGoogle.this, 10);
                    }catch (IntentSender.SendIntentException e1){
                    }
                }
            }
        });
        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationResult == null){
                    return;
                }
                for (Location location: locationResult.getLocations()){

                }
            }
        }; // Metodo para buscar a localização com frequencia.
        servicoLocalizacao.requestLocationUpdates(locationRequest, locationCallback, null);
    }

}