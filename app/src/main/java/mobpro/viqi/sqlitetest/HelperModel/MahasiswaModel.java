package mobpro.viqi.sqlitetest.HelperModel;

/**
 * Created by Viqi on 17/12/2017.
 */

public class MahasiswaModel {

    private String id_mahasiswa = "id_mahasiswa";
    private String nama_mahasiswa = "nama_mahasiswa";
    private String fakultas = "fakultas";
    private String jurusan = "jurusan";

    public String getId_mahasiswa() {
        return  id_mahasiswa;
    }

    public void setId_mahasiswa(String id_mahasiswa){
        this.id_mahasiswa = id_mahasiswa;
    }

    public String getNama_mahasiswa(){
        return nama_mahasiswa;
    }

    public void setNama_mahasiswa(String nama_mahasiswa){
        this.nama_mahasiswa = nama_mahasiswa;
    }

    public String getFakultas(){
        return fakultas;
    }

    public void setFakultas(String fakultas){
        this.fakultas = fakultas;
    }

    public String getJurusan(){
        return jurusan;
    }

    public void setJurusan(String jurusan){
        this.jurusan = jurusan;
    }
}
