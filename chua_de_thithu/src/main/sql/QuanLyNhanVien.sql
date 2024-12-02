CREATE DATABASE QuanLyNhanVien;
GO

USE QuanLyNhanVien;
GO

CREATE TABLE NhanVien (
    MaNV NVARCHAR(50) PRIMARY KEY,  
    TenNV NVARCHAR(100),           
    DiaChi NVARCHAR(200),           
    GioiTinh BIT                    
);
GO

INSERT INTO NhanVien (MaNV, TenNV, DiaChi, GioiTinh)
VALUES 
(N'NV001', N'Nguyễn Văn A', N'123 Đường A, TP. Hà Nội', 1),
(N'NV002', N'Trần Thị B', N'456 Đường B, TP. Đà Nẵng', 0),
(N'NV003', N'Phạm Văn C', N'789 Đường C, TP. Hồ Chí Minh', 1),
(N'NV004', N'Lê Thị D', N'321 Đường D, TP. Hải Phòng', 0),
(N'NV005', N'Hoàng Văn E', N'654 Đường E, TP. Cần Thơ', 1);
GO