create database siakad;

create table if not exists admin (
    kode     varchar(10)                     not null primary key,
    password varchar(255)                    not null,
    name     varchar(255)                    not null,
    sex      enum ('Laki-laki', 'Perempuan') not null,
    phone    varchar(15)                     not null,
    address  varchar(255)                    not null
);

create table if not exists dosen (
    nip      varchar(10)                     not null primary key,
    password varchar(255)                    null,
    prodi    varchar(50)                     null,
    name     varchar(100)                    null,
    sex      enum ('Laki-laki', 'Perempuan') not null,
    phone    varchar(15)                     null,
    address  varchar(255)                    null
);

create table if not exists mahasiswa (
    nim      varchar(10)                     not null primary key,
    password varchar(255)                    null,
    prodi    varchar(50)                     null,
    name     varchar(100)                    null,
    sex      enum ('Laki-laki', 'Perempuan') not null,
    phone    varchar(15)                     null,
    address  varchar(255)                    null,
    ipk      double                          null
);

create table if not exists mata_kuliah (
    kode             varchar(10)  not null primary key,
    nama_mata_kuliah varchar(100) null,
    prodi            varchar(50)  null,
    sks              int(2)       null,
    dosen_pengajar   varchar(10)  null
);

create index if not exists idx_mata_kuliah_dosen_pengajar
    on mata_kuliah (dosen_pengajar);

create table if not exists nilai_mata_kuliah (
    kode_mata_kuliah varchar(10) not null,
    mahasiswa_nim    varchar(10) null,
    nilai            double      null
);

create table if not exists pengumuman (
    status    enum ('Active', 'Inactive')                      null,
    publisher varchar(10)                                      not null,
    content   text default 'Hallo semoga hari mu menyenangkan' not null
);
