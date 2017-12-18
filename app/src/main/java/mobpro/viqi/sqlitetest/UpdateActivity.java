package mobpro.viqi.sqlitetest;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import mobpro.viqi.sqlitetest.HelperModel.DatabaseHelper;
import mobpro.viqi.sqlitetest.HelperModel.MahasiswaModel;

public class UpdateActivity extends AppCompatActivity {

    private List<String> listMahasiswa;
    private List<MahasiswaModel> listModelMahasiswa;
    private DatabaseHelper DatabaseHelper;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Update Data Mahasiswa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.listMahasiswa);

        listMahasiswa = new ArrayList<>();
        listModelMahasiswa = new ArrayList<>();

        //inisialisasi data
        DatabaseHelper = new DatabaseHelper(this);

        //menampilkan data
        showData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                String nama_mahasiswa = listModelMahasiswa.get(position).getNama_mahasiswa();
                builder.setTitle("Update Nama : "+ nama_mahasiswa);

                // Set up the input
                final EditText input = new EditText(UpdateActivity.this);

                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setText(nama_mahasiswa);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String namUpdate = input.getText().toString();

                        //update the nama
                        DatabaseHelper.updateNamaMahasiswa(listModelMahasiswa.get(position).getId_mahasiswa(), namUpdate);

                        //clear the list
                        listModelMahasiswa.clear();
                        listMahasiswa.clear();

                        //show data
                        showData();

                    }
                });
                builder.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }

    private void showData() {
        //inisialisasi db helper
        DatabaseHelper = new DatabaseHelper(this);

        //mengambil semua data
        Cursor cursor = DatabaseHelper.readAllDataMahasiswa();

        if (cursor.moveToFirst()) {
            do {
                MahasiswaModel mahasiswaModel = new MahasiswaModel();

                //get data
                String id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.ID_MAHASISWA));
                String namaMahasiswa = cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAMA_MAHASISWA));
                String fakultas = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FAKULTAS));
                String jurusan = cursor.getString(cursor.getColumnIndex(DatabaseHelper.JURUSAN));

                //menambahkan ke list
                listMahasiswa.add(namaMahasiswa+ " - "+fakultas+" - "+jurusan);

                //set data ke model
                mahasiswaModel.setId_mahasiswa(id);
                mahasiswaModel.setNama_mahasiswa(namaMahasiswa);
                mahasiswaModel.setFakultas(fakultas);
                mahasiswaModel.setJurusan(jurusan);

                //menambahkan model ke list
                listModelMahasiswa.add(mahasiswaModel);
            } while (cursor.moveToNext());
        }

        //menyiapkan adapter
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listMahasiswa);

        //set adapter
        listView.setAdapter(itemsAdapter);
    }
}
