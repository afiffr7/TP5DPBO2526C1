# TP5DPBO2526C1

## Janji
Saya Afif Fadilah Rahman dengan NIM 2508287 mengerjakan TP 5 dalam mata kuliah Desain Pemrograman Berbasis Objek untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

## Desain/fungsi program
Program memiliki tema data trader yang memiliki attribut kategori berupa kategori jenis alat tukar yang digunakan oleh trader tersbeut serta attribut warnaMcLaren yaitu salah satu asset kekayaan trader tersebut.\
Program menyediakan form inputan masing-masing attribut serta tombol untuk memodifikasi inputan atau data yang sudah ada di tabel.

## Penjelasan alur
Desain swing GUI program memiliki 3 komponen text field dan 2 komponen combo box sebagai komponen penerima inputan user. Kemudian ada button add, cancel, dan delete yang terletak di kanan GUI. Tombol add untuk mengirim data inputan dari GUI ke program melalui button event listener dan insert data method. Tombol cancel untuk reset form inputan. Serta tombol delete untuk menghapus data di tabel.
Pada bagian bawah GUI terdapat tabel data yang berisi data dummy dan data yang berhasil diinput oleh user.\
\
Pada program source code terdapat 2 file yaitu 1 file Database sebagai class yang menghubungkan program dengan database dan 1 file PersonMenu sebagai class menu GUI.
Terdapat file .sql juga yang menyimpan database trader. Melalui class database, program akan mengirim hasil CRUD dari user pada menu yang telah disediakan ke database yang ada di xampp.
Kemudian program juga dapat mengambil data dari database di xampp untuk menampilkan tabel.

## Dokumentasi

### Menambahkan data
![Tambah Data](Dokumentasi/TambahData.gif)

### Update data
![Update Data](Dokumentasi/PerbaruiData.gif)

### Delete data
![Form Confirm](Dokumentasi/DeleteConfirmationPopUp.png)
![Hapus Data](Dokumentasi/DeleteData.gif)

### Error Handle
![Tahun Bukan Angka](Dokumentasi/ErrorHandleTahunHarusAngka.png)
![Form Kosong](Dokumentasi/DataMissing.png)
![id sudah dipakai](Dokumentasi/ErrorHandleIdSudahDigunakan.png)