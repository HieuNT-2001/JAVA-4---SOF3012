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
    [Id] VARCHAR(20) NOT NULL PRIMARY KEY, -- Primary Key column
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