package mobpro.viqi.sqlitetest;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import mobpro.viqi.sqlitetest.HelperModel.DatabaseHelper;

public class ReadAcitivity extends AppCompatActivity {

    private List<String> listMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Lihat Data Mahasiswa");

        ListView listView = (ListView) findViewById(R.id.listMahasiswa);
        listMahasiswa = new ArrayList<>();

        //inisialisasi db helper
        DatabaseHelper DataBaseHelper = new DatabaseHelper(this);

        //get semua data
        Cursor cursor = DataBaseHelper.readAllDataMahasiswa();

        if (cursor.moveToFirst()){
            do {
                //get data
                String namaMahasiswa = cursor.getString(cursor.getColumnIndex(DataBaseHelper.NAMA_MAHASISWA));
                String fakultas = cursor.getString(cursor.getColumnIndex(DataBaseHelper.FAKULTAS));
                String jurusan = cursor.getString(cursor.getColumnIndex(DataBaseHelper.JURUSAN));

                //menambahkan data pada list
                listMahasiswa.add(namaMahasiswa+" - "+fakultas+" - "+jurusan);
            } while (cursor.moveToNext());
        }

        //menyiapkan adapter
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listMahasiswa);

        //set adapter
        listView.setAdapter(itemsAdapter);
    }
}
