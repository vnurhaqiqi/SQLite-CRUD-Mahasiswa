package mobpro.viqi.sqlitetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import mobpro.viqi.sqlitetest.HelperModel.DatabaseHelper;

public class CreateActivity extends AppCompatActivity {

    private DatabaseHelper DatabaseHelper;
    private EditText editIdMahasiswa, editNamaMahasiswa, editFakultas, editJurusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        getSupportActionBar().setTitle("Tambah Data Mahasiswa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //inisialisasi edit text
        editIdMahasiswa = (EditText) findViewById(R.id.editIdMahasiswa);
        editNamaMahasiswa = (EditText) findViewById(R.id.editNamaMahasiswa);
        editFakultas = (EditText) findViewById(R.id.editFakultas);
        editJurusan = (EditText) findViewById(R.id.editJurusan);

        //inisialisasi db helper
        DatabaseHelper = new DatabaseHelper(this);
    }

    public void doSimpan(View view) {

        //get text dari edit text
        String id = editIdMahasiswa.getText().toString();
        String nama = editNamaMahasiswa.getText().toString();
        String fakultas = editFakultas.getText().toString();
        String jurusan = editJurusan.getText().toString();

        //validasi input
        if (id.length()> 1 && nama.length()> 2 && fakultas.length()>2 && jurusan.length()>2) {

            //menyimpan value
            boolean isSaved = DatabaseHelper.createDataMahasiswa(id, nama, fakultas, jurusan);

            if (isSaved) {
                Toast.makeText(getApplicationContext(), "Data Berhasil disimpan", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CreateActivity.this, MainActivity.class));
            } else {
                Toast.makeText(getApplicationContext(), "Data Gagal disimpan", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Lengkapi Data", Toast.LENGTH_SHORT).show();
        }
    }
}
