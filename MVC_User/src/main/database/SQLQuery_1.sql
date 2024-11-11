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

-- Insert rows into table 'Users' in schema '[dbo]'
INSERT INTO [dbo].[Users]
( -- Columns to insert data into
 [Id], [Password], [Fullname], [Email], [Admin]
)
VALUES
( -- First row: values for the columns in the list above
 'user1', 'password1', 'Nguyen Van A', 'user1@example.com', 0
),
( -- Second row: values for the columns in the list above
 'user2', 'password2', 'Nguyen Van B', 'user2@example.com', 1
),
-- Add more rows here
('user3', 'password3', 'Nguyen Van C', 'user3@example.com', 0);
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
    [Id] INT NOT NULL PRIMARY KEY, -- Primary Key column
    [VideoId] CHAR(11) NOT NULL,
    [UserId] NVARCHAR(20) NOT NULL,
    [LikeDate] DATE NOT NULL
    -- Specify more columns here
);
GO

-- Insert rows into table 'Videos' in schema '[dbo]'
INSERT INTO [dbo].[Videos]
( -- Columns to insert data into
 [Id], [Title], [Poster], [Description], [Active], [Views]
)
VALUES
( -- First row: values for the columns in the list above
 'video000001', 'title1', 'poster1', 'description1', 1, 10
),
( -- Second row: values for the columns in the list above
 'video000002', 'title2', 'poster2', 'description2', 1, 15
),
( -- Second row: values for the columns in the list above
 'video000003', 'title3', 'poster3', 'description3', 1, 20
)
-- Add more rows here
GO