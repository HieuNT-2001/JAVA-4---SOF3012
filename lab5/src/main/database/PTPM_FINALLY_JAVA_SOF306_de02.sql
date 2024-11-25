-- Tạo cơ sở dữ liệu de2
CREATE DATABASE de04;
GO

-- Sử dụng cơ sở dữ liệu de2
USE de04;
GO


-- Tạo bảng SanPham
CREATE TABLE SanPham
(
    MaSanPham BIGINT IDENTITY(1,1) PRIMARY KEY,
    -- Khóa chính
    TenSanPham NVARCHAR(50),
    -- Tên sản phẩm
    NgaySanXuat DATE,
    -- Ngày sản xuất
    MoTa NVARCHAR(MAX),
    -- Mô tả sản phẩm
    GiaBan DECIMAL(18,2),
    -- Giá bán
    SoLuong INT,
    -- Số lượng tồn kho
    HangSanXuat NVARCHAR(50),
    -- Hãng sản xuất
    DanhMuc NVARCHAR(50),
    -- Danh mục sản phẩm
    AnhDaiDien NVARCHAR(50),
    -- Ảnh đại diện
    TrangThai INT
    -- Trạng thái (1 - hoạt động, 0 - không hoạt động)
);
GO

-- Tạo bảng BienTheSanPham
CREATE TABLE BienTheSanPham
(
    MaBienThe INT IDENTITY(1,1) PRIMARY KEY,
    -- Khóa chính
    MaSanPham BIGINT NOT NULL REFERENCES SanPham(MaSanPham),
    -- Khóa ngoại tham chiếu tới SanPham
    TenBienThe NVARCHAR(50),
    -- Tên biến thể
    MoTa NVARCHAR(MAX),
    -- Mô tả biến thể
    Gia DECIMAL(18,2) NOT NULL,
    -- Giá biến thể
    TrangThai INT
    -- Trạng thái (1 - hoạt động, 0 - không hoạt động)
);
GO

INSERT INTO SanPham
    (TenSanPham, NgaySanXuat, MoTa, GiaBan, SoLuong, HangSanXuat, DanhMuc, AnhDaiDien, TrangThai)
VALUES
    (N'Sản phẩm A', '2023-01-01', N'Sản phẩm cao cấp', 500000, 100, N'Hãng X', N'Danh mục 1', N'sp_a.jpg', 1),
    (N'Sản phẩm B', '2023-02-01', N'Sản phẩm phổ thông', 300000, 200, N'Hãng Y', N'Danh mục 2', N'sp_b.jpg', 1),
    (N'Sản phẩm C', '2023-03-01', N'Sản phẩm trung cấp', 400000, 150, N'Hãng Z', N'Danh mục 3', N'sp_c.jpg', 1),
    (N'Sản phẩm D', '2023-04-01', N'Sản phẩm độc đáo', 700000, 80, N'Hãng X', N'Danh mục 1', N'sp_d.jpg', 1),
    (N'Sản phẩm E', '2023-05-01', N'Sản phẩm giá rẻ', 200000, 300, N'Hãng Y', N'Danh mục 2', N'sp_e.jpg', 1);

INSERT INTO BienTheSanPham
    (MaSanPham, TenBienThe, MoTa, Gia, TrangThai)
VALUES
    (1, N'Biến thể A1', N'Màu Đỏ, Size M', 520000, 1),
    (1, N'Biến thể A2', N'Màu Đỏ, Size L', 530000, 1),
    (2, N'Biến thể B1', N'Màu Xanh, Size S', 310000, 1),
    (2, N'Biến thể B2', N'Màu Xanh, Size M', 320000, 1),
    (3, N'Biến thể C1', N'Màu Trắng, Size M', 410000, 1),
    (3, N'Biến thể C2', N'Màu Trắng, Size L', 420000, 1),
    (4, N'Biến thể D1', N'Màu Đen, Size XL', 710000, 1),
    (4, N'Biến thể D2', N'Màu Đen, Size XXL', 720000, 1),
    (5, N'Biến thể E1', N'Màu Hồng, Size S', 210000, 1),
    (5, N'Biến thể E2', N'Màu Hồng, Size M', 220000, 1);

select *
from SanPham
select *
from BienTheSanPham