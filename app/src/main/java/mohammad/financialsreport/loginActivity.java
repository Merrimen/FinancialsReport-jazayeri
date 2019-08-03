package mohammad.financialsreport;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import mohammad.financialsreport.models.EmployeeEntity;
import mohammad.financialsreport.utilities.PublicMethods;
import mohammad.financialsreport.customViews.MyButton;
import mohammad.financialsreport.customViews.MyEditText;
import mohammad.financialsreport.utilities.AppExecutors;

public class loginActivity extends AppCompatActivity {

    MyEditText username, password;
    MyButton btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bind();

    }

    void bind() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        findViewById(R.id.btnLogin).setOnClickListener(V -> {
            if (V.getId() == R.id.btnLogin) {
                String userValue = username.text();
                String passValue = password.text();
                if (!userValue.isEmpty()) {
                    login(userValue, passValue);

                } else PublicMethods.toast(this, getString(R.string.enter_again));

                EmployeeEntity employeeEntity = new EmployeeEntity();
                employeeEntity.setUsername(userValue);
                employeeEntity.setPassword(passValue);
                storeLogin();
                username.setText("");
                password.setText("");

            }
        });

    }

    void login(String username, String password) {

        AppExecutors executors = AppExecutors.getInstance();
        executors.diskIO().execute(() -> {
            EmployeeEntity employeeEntity = AppDatabase.getInstance(this).getEmployeeEntityDAO().getByUsername(username);
            executors.mainThread().execute(() -> checkUser(employeeEntity,password));

        });

    }

    private void checkUser(EmployeeEntity employeeEntity ,String password) {
        if (employeeEntity != null) {

           // Log.d(TAG, "checkUser: ");
            if (employeeEntity.getPassword().equals(password)) {
                Intent intent = new Intent(loginActivity.this, MainActivity.class);
                intent.putExtra(MainActivity.USERID, employeeEntity.getUserid());
                startActivity(intent);
            } else {
                PublicMethods.toast(this, getString(R.string.pass_is_wrong));
            }
        } else {
            PublicMethods.toast(this, getString(R.string.user_doesnot_exist));
        }
    }

    public void storeLogin(){
        PublicMethods.setData("username",username.text());
        PublicMethods.setData("password",password.text());
    }

}
