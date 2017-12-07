"# robusta" 
Database menggunakan ORM GreenDAO
Entity table database terdapat pada folder ./database/entity

Terdapat 7 table yang akan digunakan.
 1. IpksTbl : untuk menyimpan data indeks Potensi Kerawanan Sosial
 2. KursusTbl : Untuk menyimpan data kursus
 3. Puskesmas : Untuk menyimpan data puskesmas
 4. RawanBencanaTbl :  Untuk menyimpan data rawanbencana
 5. RptraTbl : Untuk menyimpan data RPTRA
 6. RskhususTbl : Untuk menyimpan data RS khusus
 7. RSUmumTbl : Untuk menyimpan data RS Umum.
 8. SekolahTbl : Untuk menyimpan data sekolah.
 
 
 "# Greendao"
 untuk menggunakan fungsi SQL pada GreenDao , silahkan panggil fungsi Facade.getInstance()
 
contoh :
apabila ingin menquery data sekolah dari table SekolahTbl, maka sintaknya :

List<SekolahTbl> sekolahTbl  = Facade.getInstance().getManageSekolahTbl().getAll();
  
  
