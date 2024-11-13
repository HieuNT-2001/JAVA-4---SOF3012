-- Create a new database called 'PolyOE'
-- Connect to the 'master' database to run this snippet
USE master
GO
-- Create the new database if it does not exist already
IF NOT EXISTS (
    SELECT [name]
        FROM sys.databases
        WHERE [name] = N'PolyOE'
)
CREATE DATABASE PolyOE
GO

USE PolyOE;
GO

-- Create a new table called '[Users]' in schema '[dbo]'
-- Drop the table if it already exists
IF OBJECT_ID('[dbo].[Users]', 'U') IS NOT NULL
DROP TABLE [dbo].[Users]
GO
-- Create the table in the specified schema
CREATE TABLE [dbo].[Users]
(
    [Id] NVARCHAR(20) NOT NULL PRIMARY KEY, -- Primary Key column
    [Password] NVARCHAR(50) NOT NULL,
    [Fullname] NVARCHAR(50) NOT NULL,
    [Email] NVARCHAR(50) NOT NULL,
    [Admin] BIT NOT NULL
    -- Specify more columns here
);
GO

-- Create a new table called '[Videos]' in schema '[dbo]'
-- Drop the table if it already exists
IF OBJECT_ID('[dbo].[Videos]', 'U') IS NOT NULL
DROP TABLE [dbo].[Videos]
GO
-- Create the table in the specified schema
CREATE TABLE [dbo].[Videos]
(
    [Id] CHAR(11) NOT NULL PRIMARY KEY, -- Primary Key column
    [Title] NVARCHAR(50) NOT NULL,
    [Poster] NVARCHAR(50) NOT NULL,
    [Description] NVARCHAR(MAX) NOT NULL,
    [Active] BIT NOT NULL,
    [Views] INT NOT NULL
    -- Specify more columns here
);
GO

-- Create a new table called '[Favorites]' in schema '[dbo]'
-- Drop the table if it already exists
IF OBJECT_ID('[dbo].[Favorites]', 'U') IS NOT NULL
DROP TABLE [dbo].[Favorites]
GO
-- Create the table in the specified schema
CREATE TABLE [dbo].[Favorites]
(
    [Id] INT NOT NULL PRIMARY KEY IDENTITY(1,1), -- Primary Key column
    [VideoId] CHAR(11) NOT NULL,
    [UserId] NVARCHAR(20) NOT NULL,
    [LikeDate] DATE NOT NULL,
    -- Specify more columns here
    FOREIGN KEY (VideoId) REFERENCES Videos(Id),
    FOREIGN KEY (UserId) REFERENCES Users(Id)
);
GO

-- Insert 5 records into [Users] table
INSERT INTO [dbo].[Users] (Id, Password, Fullname, Email, Admin) VALUES 
(N'U001', N'password123', N'John Doe', N'johndoe@example.com', 0),
(N'U002', N'password456', N'Jane Smith', N'janesmith@example.com', 1),
(N'U003', N'password789', N'Bill Gates', N'billgates@example.com', 0),
(N'U004', N'password101', N'Elon Musk', N'elonmusk@example.com', 0),
(N'U005', N'password202', N'Ada Lovelace', N'adalovelace@example.com', 1);

-- Insert 5 records into [Videos] table
INSERT INTO [dbo].[Videos] (Id, Title, Poster, Description, Active, Views) VALUES 
(N'V001', N'Video One', N'poster1.jpg', N'This is the description for Video One', 1, 100),
(N'V002', N'Video Two', N'poster2.jpg', N'This is the description for Video Two', 1, 200),
(N'V003', N'Video Three', N'poster3.jpg', N'This is the description for Video Three', 0, 150),
(N'V004', N'Video Four', N'poster4.jpg', N'This is the description for Video Four', 1, 50),
(N'V005', N'Video Five', N'poster5.jpg', N'This is the description for Video Five', 1, 300);

-- Insert 5 records into [Favorites] table
INSERT INTO [dbo].[Favorites] (VideoId, UserId, LikeDate) VALUES 
(N'V001', N'U001', '2024-01-01'),
(N'V002', N'U002', '2024-01-02'),
(N'V003', N'U003', '2024-01-03'),
(N'V004', N'U004', '2024-01-04'),
(N'V005', N'U005', '2024-01-05');
