-- Tạo cơ sở dữ liệu de2
CREATE DATABASE de2;
GO

-- Sử dụng cơ sở dữ liệu de2
USE de2;
GO

-- Tạo bảng BienTheSanPham
CREATE TABLE BienTheSanPham (
    MaBienThe INT IDENTITY(1,1) PRIMARY KEY,
    TenBienThe NVARCHAR(50),
	MoTa NVARCHAR(MAX),
    Gia DECIMAL(18,2) NOT NULL,
    TrangThai INT
);
GO

-- Tạo bảng SanPham
CREATE TABLE SanPham (
    MaSanPham BIGINT IDENTITY(1,1) PRIMARY KEY,
    TenSanPham NVARCHAR(50),
	NgaySanXuat DATE,
    MoTa NVARCHAR(MAX),
    GiaBan DECIMAL(18,2),
	SoLuong INT,
    HangSanXuat NVARCHAR(50),
    DanhMuc NVARCHAR(50),
	AnhDaiDien NVARCHAR(50),
	TrangThai INT,

	MaBienThe INT REFERENCES BienTheSanPham(MaBienThe)
);
GO

-- Chèn dữ liệu vào bảng BienTheSanPham
INSERT INTO BienTheSanPham (TenBienThe, MoTa, Gia, TrangThai) VALUES
('Biến thể A', 'Mô tả cho Biến thể A', 100000, 1),
('Biến thể B', 'Mô tả cho Biến thể B', 200000, 1),
('Biến thể C', 'Mô tả cho Biến thể C', 300000, 1),
('Biến thể D', 'Mô tả cho Biến thể D', 400000, 0),
('Biến thể E', 'Mô tả cho Biến thể E', 500000, 0),
('Biến thể F', 'Mô tả cho Biến thể F', 600000, 1),
('Biến thể G', 'Mô tả cho Biến thể G', 700000, 1),
('Biến thể H', 'Mô tả cho Biến thể H', 800000, 0),
('Biến thể I', 'Mô tả cho Biến thể I', 900000, 0),
('Biến thể J', 'Mô tả cho Biến thể J', 1000000, 1);
GO

-- Chèn dữ liệu vào bảng SanPham
INSERT INTO SanPham (TenSanPham, NgaySanXuat, MoTa, GiaBan, SoLuong, HangSanXuat, DanhMuc, AnhDaiDien, TrangThai, MaBienThe) VALUES
('Sản phẩm 1', '2022-01-01', 'Mô tả sản phẩm 1', 120000, 100, 'Hãng A', 'Danh mục X', 'sp1.jpg', 1, 1),
('Sản phẩm 2', '2022-02-01', 'Mô tả sản phẩm 2', 220000, 200, 'Hãng B', 'Danh mục Y', 'sp2.jpg', 1, 2),
('Sản phẩm 3', '2022-03-01', 'Mô tả sản phẩm 3', 320000, 300, 'Hãng C', 'Danh mục Z', 'sp3.jpg', 1, 3),
('Sản phẩm 4', '2022-04-01', 'Mô tả sản phẩm 4', 420000, 400, 'Hãng D', 'Danh mục X', 'sp4.jpg', 0, 4),
('Sản phẩm 5', '2022-05-01', 'Mô tả sản phẩm 5', 520000, 500, 'Hãng E', 'Danh mục Y', 'sp5.jpg', 0, 5),
('Sản phẩm 6', '2022-06-01', 'Mô tả sản phẩm 6', 620000, 600, 'Hãng F', 'Danh mục Z', 'sp6.jpg', 1, 6),
('Sản phẩm 7', '2022-07-01', 'Mô tả sản phẩm 7', 720000, 700, 'Hãng G', 'Danh mục X', 'sp7.jpg', 1, 7),
('Sản phẩm 8', '2022-08-01', 'Mô tả sản phẩm 8', 820000, 800, 'Hãng H', 'Danh mục Y', 'sp8.jpg', 0, 8),
('Sản phẩm 9', '2022-09-01', 'Mô tả sản phẩm 9', 920000, 900, 'Hãng I', 'Danh mục Z', 'sp9.jpg', 0, 9),
('Sản phẩm 10', '2022-10-01', 'Mô tả sản phẩm 10', 1020000, 1000, 'Hãng J', 'Danh mục X', 'sp10.jpg', 1, 10);
GO
