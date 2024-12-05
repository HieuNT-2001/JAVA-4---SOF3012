-- Create a new database called 'BITITS'
-- Connect to the 'master' database to run this snippet
USE master
GO
-- Create the new database if it does not exist already
IF NOT EXISTS (
    SELECT [name]
FROM sys.databases
WHERE [name] = N'BITITS'
)
CREATE DATABASE BITITS
GO

USE BITITS
GO

-- Create a new table called '[Customers]' in schema '[dbo]'
-- Drop the table if it already exists
IF OBJECT_ID('[dbo].[Customers]', 'U') IS NOT NULL
DROP TABLE [dbo].[Customers]
GO
-- Create the table in the specified schema
CREATE TABLE [dbo].[Customers]
(
    [Id] INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    -- Primary Key column
    [Lastname] NVARCHAR(50) NOT NULL,
    [Firstname] NVARCHAR(50) NOT NULL,
    [Gender] BIT NOT NULL,
    [BirthYear] DATE NOT NULL,
    [Email] NVARCHAR(255) NOT NULL,
    [PhoneNumber] NVARCHAR(15) NOT NULL,
    [Password] NVARCHAR(50) NOT NULL,
    -- Specify more columns here
);
GO

-- Insert rows into table 'Customers' in schema '[dbo]'
INSERT INTO [dbo].[Customers]
    ([Lastname], [Firstname], [Gender], [BirthYear], [Email], [PhoneNumber], [Password])
VALUES
    ('Nguyen', 'An', 1, '1990-05-15', 'nguyenan@example.com', '+841234567890', 'Pass123!'),
    ('Tran', 'Binh', 0, '1995-03-20', 'tranbinh@example.com', '+841112223334', 'Secure456$'),
    ('Le', 'Hoa', 0, '1998-07-12', 'lehoa@example.com', '+849876543210', 'MyPass789#'),
    ('Pham', 'Minh', 1, '1993-12-01', 'phamminh@example.com', '+841234000111', 'HelloWorld123'),
    ('Hoang', 'Linh', 0, '1997-09-23', 'hoanglinh@example.com', '+849001122334', 'Test@321'),
    ('Do', 'Nam', 1, '1991-01-19', 'donam@example.com', '+848812345678', 'Password@123'),
    ('Vu', 'Phuong', 0, '1996-11-03', 'vuphuong@example.com', '+847700123456', 'Phuong!123'),
    ('Dang', 'Thanh', 1, '1992-04-14', 'dangthanh@example.com', '+846601112223', 'Thanh@456!'),
    ('Bui', 'Mai', 0, '1999-08-25', 'buimai@example.com', '+845551234567', 'Mai#Pass789'),
    ('Ngo', 'Khai', 1, '1994-06-30', 'ngokhai@example.com', '+844440987654', 'Khai$2023!');
GO
