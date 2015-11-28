package com.food.nofoodwaste.actvities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.food.nofoodwaste.utils.AppSharedPreferences;
import com.food.nofoodwaste.utils.MyConstants;
import com.food.nofoodwaste.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edtName,edtPhone;
    private boolean isVolunteer;
    private AppCompatCheckBox chkIsVolunteer;
    private String name,mobile;
    AppSharedPreferences appSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        ab.setDisplayHomeAsUpEnabled(true);

        appSharedPreferences = new AppSharedPreferences(getApplicationContext());

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

        chkIsVolunteer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isVolunteer = true;
                }else{
                    isVolunteer = true;
                }
            }
        });
    }

    private void doLogin() {
        name = edtName.getText().toString().trim();
        mobile = edtPhone.getText().toString().trim();
        if (isValidationSuccess()){
            doLoginTask();
            savePreferences();
        }else {
            //displayToast();
        }
    }

    private void savePreferences() {
        appSharedPreferences.saveStringPreferences(MyConstants.PREF_KEY_NAME,name);
        appSharedPreferences.saveStringPreferences(MyConstants.PREF_KEY_MOBILE,mobile);
        appSharedPreferences.saveBooleanPreferences(MyConstants.PREF_KEY_IS_VOLUNTEER, isVolunteer);
        appSharedPreferences.saveBooleanPreferences(MyConstants.PREF_KEY_IS_LOGGEDIN, true);
    }

    private void doLoginTask() {
        Intent intent = new Intent(getApplicationContext(),DashBoardActivity.class);
        startActivity(intent);
    }

    private void displayToast(String toastMsg) {
        Toast.makeText(getApplicationContext(),toastMsg,Toast.LENGTH_SHORT).show();
    }

    private boolean isValidationSuccess() {
        boolean isSuccess = true;

        if (name.length() < 3){
            displayToast(getString(R.string.valid_name));
            isSuccess = false;
        }else if(!mobile.matches(MyConstants.REG_EXP_MOBILE)){
            displayToast(getString(R.string.valid_mobile));
            isSuccess = false;
        }
        return isSuccess;
    }

    private void initView() {
        edtName = (EditText)findViewById(R.id.edt_name);
        edtPhone = (EditText)findViewById(R.id.edt_phone);

        chkIsVolunteer = (AppCompatCheckBox)findViewById(R.id.chk_is_volunteer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
