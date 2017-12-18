package mobpro.viqi.sqlitetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);
    }

    private void moveToActivity(Class classname){
        startActivity(new Intent(MainActivity.this, classname));
    }

    public void goToCreate(View view) {
        moveToActivity(CreateActivity.class);
    }

    public void goToRead(View view) {
        moveToActivity(ReadAcitivity.class);
    }

    public void goToUpdate(View view) {
        moveToActivity(UpdateActivity.class);
    }

    public void goToDelete(View view) {
        moveToActivity(DeleteActivity.class);
    }
}
