-- Database: db_trader

CREATE DATABASE db_trader;
USE db_trader;

CREATE TABLE IF NOT EXISTS trader (
  id varchar(255) PRIMARY KEY,
  nama varchar(255) NOT NULL,
  tahunLahir int NOT NULL,
  kategori varchar(255) NOT NULL,
  warnaMcLaren varchar(255) NOT NULL
);

INSERT INTO trader (id, nama, tahunLahir, kategori, warnaMcLaren) VALUES
('TRD-001', 'Andi Pratama', 1995, 'Crypto', 'Merah'),
('TRD-002', 'Budi Santoso', 1988, 'Forex', 'Biru'),
('TRD-003', 'Citra Dewi', 2000, 'Bitcoin', 'Hijau'),
('TRD-004', 'Deni Wijaya', 1992, 'Crypto', 'Kuning'),
('TRD-005', 'Eka Putra', 1985, 'Forex', 'Ungu'),
('TRD-006', 'Fanya Lestari', 1998, 'Bitcoin', 'Merah'),
('TRD-007', 'Gilang Ramadhan', 1991, 'Crypto', 'Biru'),
('TRD-008', 'Hana Hanifah', 2001, 'Forex', 'Hijau'),
('TRD-009', 'Irfan Hakim', 1987, 'Bitcoin', 'Ungu'),
('TRD-010', 'Joko Susilo', 1994, 'Crypto', 'Kuning');