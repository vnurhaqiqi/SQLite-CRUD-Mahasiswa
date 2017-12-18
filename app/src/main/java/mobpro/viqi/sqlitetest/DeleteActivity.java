package mobpro.viqi.sqlitetest;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mobpro.viqi.sqlitetest.HelperModel.DatabaseHelper;
import mobpro.viqi.sqlitetest.HelperModel.MahasiswaModel;

public class DeleteActivity extends AppCompatActivity {

    private List<String> listMahasiswa;
    private String idMahasiswa;
    private DatabaseHelper DatabaseHelper;
    private String TAG = getClass().getSimpleName();
    private ListView listView;
    private List<MahasiswaModel> listModelMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Hapus Data Mahasiswa");

        listView = (ListView) findViewById(R.id.listMahasiswa);

        listMahasiswa = new ArrayList<>();
        listModelMahasiswa = new ArrayList<>();

        //menampilkan data
        showData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {

                //pop up confirmation delete
                new AlertDialog.Builder(DeleteActivity.this)
                        .setTitle("Konfirmasi")
                        .setMessage("Hapus data "+listMahasiswa.get(position)+"?" )
                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("HAPUS", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //get the id
                                String itemId = listModelMahasiswa.get(position).getId_mahasiswa();

                                //delete the data
                                DatabaseHelper.deleteDataMahasiswa(itemId);

                                //clear data on list
                                listMahasiswa.clear();

                                //show message its deleted
                                Toast.makeText(getApplicationContext(), "Data berhasil dihapus", Toast.LENGTH_SHORT).show();

                                //show data again > refresh
                                showData();
                            }
                        })
                        .show();

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
