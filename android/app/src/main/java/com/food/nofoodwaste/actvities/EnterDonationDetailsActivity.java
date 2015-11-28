package com.food.nofoodwaste.actvities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import com.food.nofoodwaste.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

public class EnterDonationDetailsActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks{

    private GoogleApiClient mGoogleApiClient;
    private int PLACE_PICKER_REQUEST = 1;
    private RadioButton radioBreakfast,radioLunch,radioDinner;
    private EditText edtQuantity,edtAddress;
    private String foodType = "dinner",quantity = "",address = "",lat = "",lng = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations_details_entry);
        //initView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        radioBreakfast = (RadioButton)findViewById(R.id.radio_breakfast);
        radioLunch = (RadioButton)findViewById(R.id.radio_lunch);
        radioDinner = (RadioButton)findViewById(R.id.radio_dinner);

        edtQuantity = (EditText)findViewById(R.id.edt_quantity);
        edtAddress = (EditText)findViewById(R.id.edt_address);

        mGoogleApiClient = new GoogleApiClient
                .Builder( this )
                .enableAutoManage( this, 0, this )
                .addApi( Places.GEO_DATA_API )
                .addApi( Places.PLACE_DETECTION_API )
                .addConnectionCallbacks( this )
                .addOnConnectionFailedListener( this )
                .build();

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        ab.setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.txt_select_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPlacePicker();
            }
        });

        radioBreakfast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if( mGoogleApiClient != null )
            mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        if( mGoogleApiClient != null && mGoogleApiClient.isConnected() ) {
            //mAdapter.setGoogleApiClient( null );
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    private void displayPlacePicker() {
        if( mGoogleApiClient == null || !mGoogleApiClient.isConnected() )
            return;

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult( builder.build( getApplicationContext() ), PLACE_PICKER_REQUEST );
        } catch ( GooglePlayServicesRepairableException e ) {
            Log.d("PlacesAPI Demo", "GooglePlayServicesRepairableException thrown");
        } catch ( GooglePlayServicesNotAvailableException e ) {
            Log.d( "PlacesAPI Demo", "GooglePlayServicesNotAvailableException thrown" );
        }
    }

    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
        if( requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK ) {
            displayPlace( PlacePicker.getPlace( data, this ) );
        }
    }

    private void displayPlace( Place place ) {
        if( place == null )
            return;

        //DisplayLog.displayNormalLog("MainActivity","Place",place);

        String content = "";
        //if( !TextUtils.isEmpty(place.getName()) ) {
        //    content += "Name: " + place.getName() + "\n";
        //}
        if( !TextUtils.isEmpty(place.getAddress()) ) {
            content += place.getAddress();
        }
        //if( !TextUtils.isEmpty( place.getPhoneNumber() ) ) {
        //    content += "Phone: " + place.getPhoneNumber();
        //}

        if( !TextUtils.isEmpty( String.valueOf(place.getLatLng()) ) ) {
            Log.e("MainActivity", "Latlong: "+String.valueOf(place.getLatLng()));
            LatLng mLatLng = place.getLatLng();
            //isplayLog.displayNormalLog("MainActivity", "Latlong Points", mLatLng.latitude+" - "+mLatLng.longitude);
            lat = String.valueOf(mLatLng.latitude);
            lng = String.valueOf(mLatLng.longitude);

        }
        Log.e("Main ac","address: "+content);

        if (content != null && !content.equals("")){
            edtAddress.setText(content);
        }
        //mTextView.setText( content );
       // medtAddress.setText(content);
    }

    private boolean isValidationSuccess(){
        boolean isSuccess = true;
        //if ()
        return isSuccess;
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
