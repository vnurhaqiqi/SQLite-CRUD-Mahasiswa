package mobpro.viqi.sqlitetest.HelperModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Viqi on 17/12/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "mahasiswa.db";
    private static final String TABLE_MAHASISWA = "tabel_mahasiswa";
    public static final String NAMA_MAHASISWA = "nama_mahasiswa";
    public static final String ID_MAHASISWA = "id_mahasiswa";
    public static final String FAKULTAS = "fakultas";
    public static final String JURUSAN = "jurusan";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //membuatt tabel mahasiswa
        db.execSQL("create table "+ TABLE_MAHASISWA +" ( " +
        ID_MAHASISWA+" INTEGER PRIMARY KEY," +
        NAMA_MAHASISWA+" TEXT, "+
        FAKULTAS+" TEXT, "+
        JURUSAN+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MAHASISWA);
    }

    public boolean createDataMahasiswa(String id_mahasiswa, String nama_mahasiswa, String fakultas, String jurusan) {
        //inisialisasi databse
        SQLiteDatabase db = this.getWritableDatabase();

        //menyiapkan content values
        ContentValues contentValues = new ContentValues();

        //mengambil value untuk content value
        contentValues.put(ID_MAHASISWA, id_mahasiswa);
        contentValues.put(NAMA_MAHASISWA, nama_mahasiswa);
        contentValues.put(FAKULTAS, fakultas);
        contentValues.put(JURUSAN, jurusan);

        //memasukan data ke database
        long result = db.insert(TABLE_MAHASISWA, null, contentValues);
        return result != -1;
    }

    //get semua mahasiswa
    public Cursor readAllDataMahasiswa() {
        SQLiteDatabase db = this.getWritableDatabase();

        //query untuk mendapatkan data
        Cursor result = db.rawQuery("select * from "+TABLE_MAHASISWA,null);
        return result;
    }

    //update singel mahasiswaModel
    public int updateNamaMahasiswa(String id, String nama){
        SQLiteDatabase db = this.getWritableDatabase();

        //menyiapkan content values
        ContentValues values = new ContentValues();

        //mengambil value
        values.put(NAMA_MAHASISWA, nama);

        //mengupdate
        return db.update(TABLE_MAHASISWA, values, ID_MAHASISWA + " = ?",
                new String[] { id });
    }

    public void deleteDataMahasiswa(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        //hapus
        db.delete(TABLE_MAHASISWA, ID_MAHASISWA + " = ?", new String[]{id});
        db.close();
    }
}
