USE [ParkingDB]
GO
ALTER TABLE [dbo].[Wards] DROP CONSTRAINT [FK_Wards_Districts_DistrictId]
GO
ALTER TABLE [dbo].[Vehicles] DROP CONSTRAINT [FK_Vehicles_VehicleTypes_VehicleTypeId]
GO
ALTER TABLE [dbo].[Vehicles] DROP CONSTRAINT [FK_Vehicles_Users_UserID]
GO
ALTER TABLE [dbo].[ValidTokens] DROP CONSTRAINT [FK_ValidTokens_Users_UserId]
GO
ALTER TABLE [dbo].[Users] DROP CONSTRAINT [FK_Users_Wards_WardId]
GO
ALTER TABLE [dbo].[Users] DROP CONSTRAINT [FK_Users_Districts_DistrictId]
GO
ALTER TABLE [dbo].[Users] DROP CONSTRAINT [FK_Users_Cities_CityId]
GO
ALTER TABLE [dbo].[Slots] DROP CONSTRAINT [FK_Slots_VehicleTypes_VehicleTypeId]
GO
ALTER TABLE [dbo].[Invoices] DROP CONSTRAINT [FK_Invoices_Vehicles_VehicleId]
GO
ALTER TABLE [dbo].[Invoices] DROP CONSTRAINT [FK_Invoices_Slots_SlotId]
GO
ALTER TABLE [dbo].[Districts] DROP CONSTRAINT [FK_Districts_Cities_CityId]
GO
ALTER TABLE [dbo].[Accounts] DROP CONSTRAINT [FK_Accounts_Users_UserId]
GO
ALTER TABLE [dbo].[VehicleTypes] DROP CONSTRAINT [DF__VehicleTy__Price__5AEE82B9]
GO
ALTER TABLE [dbo].[VehicleTypes] DROP CONSTRAINT [DF__VehicleTy__Price__3F466844]
GO
ALTER TABLE [dbo].[VehicleTypes] DROP CONSTRAINT [DF__VehicleTy__Price__3E52440B]
GO
ALTER TABLE [dbo].[VehicleTypes] DROP CONSTRAINT [DF__VehicleTy__Price__3D5E1FD2]
GO
ALTER TABLE [dbo].[Vehicles] DROP CONSTRAINT [DF__Vehicles__IsPark__47DBAE45]
GO
ALTER TABLE [dbo].[Invoices] DROP CONSTRAINT [DF__Invoices__TotalP__412EB0B6]
GO
/****** Object:  Index [IX_Wards_DistrictId]    Script Date: 7/21/2022 11:06:21 AM ******/
DROP INDEX [IX_Wards_DistrictId] ON [dbo].[Wards]
GO
/****** Object:  Index [IX_Vehicles_VehicleTypeId]    Script Date: 7/21/2022 11:06:21 AM ******/
DROP INDEX [IX_Vehicles_VehicleTypeId] ON [dbo].[Vehicles]
GO
/****** Object:  Index [IX_Vehicles_UserID]    Script Date: 7/21/2022 11:06:21 AM ******/
DROP INDEX [IX_Vehicles_UserID] ON [dbo].[Vehicles]
GO
/****** Object:  Index [IX_ValidTokens_UserId]    Script Date: 7/21/2022 11:06:21 AM ******/
DROP INDEX [IX_ValidTokens_UserId] ON [dbo].[ValidTokens]
GO
/****** Object:  Index [IX_Users_WardId]    Script Date: 7/21/2022 11:06:21 AM ******/
DROP INDEX [IX_Users_WardId] ON [dbo].[Users]
GO
/****** Object:  Index [IX_Users_DistrictId]    Script Date: 7/21/2022 11:06:21 AM ******/
DROP INDEX [IX_Users_DistrictId] ON [dbo].[Users]
GO
/****** Object:  Index [IX_Users_CityId]    Script Date: 7/21/2022 11:06:21 AM ******/
DROP INDEX [IX_Users_CityId] ON [dbo].[Users]
GO
/****** Object:  Index [IX_Slots_VehicleTypeId]    Script Date: 7/21/2022 11:06:21 AM ******/
DROP INDEX [IX_Slots_VehicleTypeId] ON [dbo].[Slots]
GO
/****** Object:  Index [IX_Invoices_VehicleId]    Script Date: 7/21/2022 11:06:21 AM ******/
DROP INDEX [IX_Invoices_VehicleId] ON [dbo].[Invoices]
GO
/****** Object:  Index [IX_Invoices_SlotId]    Script Date: 7/21/2022 11:06:21 AM ******/
DROP INDEX [IX_Invoices_SlotId] ON [dbo].[Invoices]
GO
/****** Object:  Index [IX_Districts_CityId]    Script Date: 7/21/2022 11:06:21 AM ******/
DROP INDEX [IX_Districts_CityId] ON [dbo].[Districts]
GO
/****** Object:  Index [IX_Accounts_UserId]    Script Date: 7/21/2022 11:06:21 AM ******/
DROP INDEX [IX_Accounts_UserId] ON [dbo].[Accounts]
GO
/****** Object:  Table [dbo].[Wards]    Script Date: 7/21/2022 11:06:21 AM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Wards]') AND type in (N'U'))
DROP TABLE [dbo].[Wards]
GO
/****** Object:  Table [dbo].[VehicleTypes]    Script Date: 7/21/2022 11:06:21 AM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[VehicleTypes]') AND type in (N'U'))
DROP TABLE [dbo].[VehicleTypes]
GO
/****** Object:  Table [dbo].[Vehicles]    Script Date: 7/21/2022 11:06:21 AM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Vehicles]') AND type in (N'U'))
DROP TABLE [dbo].[Vehicles]
GO
/****** Object:  Table [dbo].[ValidTokens]    Script Date: 7/21/2022 11:06:21 AM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ValidTokens]') AND type in (N'U'))
DROP TABLE [dbo].[ValidTokens]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 7/21/2022 11:06:21 AM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Users]') AND type in (N'U'))
DROP TABLE [dbo].[Users]
GO
/****** Object:  Table [dbo].[Slots]    Script Date: 7/21/2022 11:06:21 AM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Slots]') AND type in (N'U'))
DROP TABLE [dbo].[Slots]
GO
/****** Object:  Table [dbo].[Invoices]    Script Date: 7/21/2022 11:06:21 AM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Invoices]') AND type in (N'U'))
DROP TABLE [dbo].[Invoices]
GO
/****** Object:  Table [dbo].[Districts]    Script Date: 7/21/2022 11:06:21 AM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Districts]') AND type in (N'U'))
DROP TABLE [dbo].[Districts]
GO
/****** Object:  Table [dbo].[Cities]    Script Date: 7/21/2022 11:06:21 AM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Cities]') AND type in (N'U'))
DROP TABLE [dbo].[Cities]
GO
/****** Object:  Table [dbo].[Accounts]    Script Date: 7/21/2022 11:06:21 AM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Accounts]') AND type in (N'U'))
DROP TABLE [dbo].[Accounts]
GO
/****** Object:  Table [dbo].[__EFMigrationsHistory]    Script Date: 7/21/2022 11:06:21 AM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[__EFMigrationsHistory]') AND type in (N'U'))
DROP TABLE [dbo].[__EFMigrationsHistory]
GO
USE [master]
GO
/****** Object:  Database [ParkingDB]    Script Date: 7/21/2022 11:06:21 AM ******/
DROP DATABASE [ParkingDB]
GO
/****** Object:  Database [ParkingDB]    Script Date: 7/21/2022 11:06:21 AM ******/
CREATE DATABASE [ParkingDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ParkingDB', FILENAME = N'F:\Database\ParkingDB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'ParkingDB_log', FILENAME = N'F:\Database\ParkingDB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [ParkingDB] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ParkingDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ParkingDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ParkingDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ParkingDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ParkingDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ParkingDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [ParkingDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ParkingDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ParkingDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ParkingDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ParkingDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ParkingDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ParkingDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ParkingDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ParkingDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ParkingDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [ParkingDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ParkingDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ParkingDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ParkingDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ParkingDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ParkingDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ParkingDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ParkingDB] SET RECOVERY FULL 
GO
ALTER DATABASE [ParkingDB] SET  MULTI_USER 
GO
ALTER DATABASE [ParkingDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ParkingDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ParkingDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ParkingDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [ParkingDB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [ParkingDB] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'ParkingDB', N'ON'
GO
ALTER DATABASE [ParkingDB] SET QUERY_STORE = OFF
GO
USE [ParkingDB]
GO
/****** Object:  Table [dbo].[__EFMigrationsHistory]    Script Date: 7/21/2022 11:06:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[__EFMigrationsHistory](
	[MigrationId] [nvarchar](150) NOT NULL,
	[ProductVersion] [nvarchar](32) NOT NULL,
 CONSTRAINT [PK___EFMigrationsHistory] PRIMARY KEY CLUSTERED 
(
	[MigrationId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Accounts]    Script Date: 7/21/2022 11:06:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Accounts](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](max) NULL,
	[Password] [nvarchar](max) NULL,
	[Role] [nvarchar](max) NULL,
	[UserId] [int] NOT NULL,
 CONSTRAINT [PK_Accounts] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cities]    Script Date: 7/21/2022 11:06:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cities](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](max) NULL,
 CONSTRAINT [PK_Cities] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Districts]    Script Date: 7/21/2022 11:06:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Districts](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](max) NULL,
	[CityId] [int] NOT NULL,
 CONSTRAINT [PK_Districts] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Invoices]    Script Date: 7/21/2022 11:06:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoices](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[CheckinTime] [datetime2](7) NULL,
	[CheckoutTime] [datetime2](7) NULL,
	[VehicleId] [nvarchar](450) NULL,
	[SlotId] [nvarchar](450) NULL,
	[TotalPaid] [float] NOT NULL,
 CONSTRAINT [PK_Invoices] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Slots]    Script Date: 7/21/2022 11:06:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Slots](
	[Id] [nvarchar](450) NOT NULL,
	[Status] [bit] NOT NULL,
	[VehicleTypeId] [int] NOT NULL,
 CONSTRAINT [PK_Slots] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 7/21/2022 11:06:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](max) NULL,
	[Email] [nvarchar](max) NULL,
	[Phone] [nvarchar](max) NULL,
	[Feedback] [nvarchar](max) NULL,
	[CityId] [int] NULL,
	[DistrictId] [int] NULL,
	[WardId] [int] NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ValidTokens]    Script Date: 7/21/2022 11:06:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ValidTokens](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Value] [nvarchar](max) NULL,
	[UserId] [int] NOT NULL,
 CONSTRAINT [PK_ValidTokens] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Vehicles]    Script Date: 7/21/2022 11:06:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Vehicles](
	[Id] [nvarchar](450) NOT NULL,
	[VehicleName] [nvarchar](max) NULL,
	[VehicleBrand] [nvarchar](max) NULL,
	[UserID] [int] NOT NULL,
	[VehicleTypeId] [int] NOT NULL,
	[IsParking] [bit] NOT NULL,
 CONSTRAINT [PK_Vehicles] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[VehicleTypes]    Script Date: 7/21/2022 11:06:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[VehicleTypes](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[TypeName] [nvarchar](max) NULL,
	[PricePerHour] [float] NOT NULL,
	[PricePerMonth] [float] NOT NULL,
	[PricePerWeek] [float] NOT NULL,
	[PricePerDay] [float] NOT NULL,
	[PricePerYear] [float] NOT NULL,
 CONSTRAINT [PK_VehicleTypes] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Wards]    Script Date: 7/21/2022 11:06:21 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Wards](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](max) NULL,
	[DistrictId] [int] NOT NULL,
 CONSTRAINT [PK_Wards] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[__EFMigrationsHistory] ([MigrationId], [ProductVersion]) VALUES (N'20220609003909_add_1', N'6.0.5')
INSERT [dbo].[__EFMigrationsHistory] ([MigrationId], [ProductVersion]) VALUES (N'20220609004711_add_2', N'6.0.5')
INSERT [dbo].[__EFMigrationsHistory] ([MigrationId], [ProductVersion]) VALUES (N'20220609005336_add_3', N'6.0.5')
INSERT [dbo].[__EFMigrationsHistory] ([MigrationId], [ProductVersion]) VALUES (N'20220609010032_add_4', N'6.0.5')
INSERT [dbo].[__EFMigrationsHistory] ([MigrationId], [ProductVersion]) VALUES (N'20220609012119_add_5', N'6.0.5')
INSERT [dbo].[__EFMigrationsHistory] ([MigrationId], [ProductVersion]) VALUES (N'20220609012205_add_6', N'6.0.5')
INSERT [dbo].[__EFMigrationsHistory] ([MigrationId], [ProductVersion]) VALUES (N'20220609012625_add_7', N'6.0.5')
INSERT [dbo].[__EFMigrationsHistory] ([MigrationId], [ProductVersion]) VALUES (N'20220610121742_add_8', N'6.0.5')
INSERT [dbo].[__EFMigrationsHistory] ([MigrationId], [ProductVersion]) VALUES (N'20220613075335_add_9', N'6.0.5')
INSERT [dbo].[__EFMigrationsHistory] ([MigrationId], [ProductVersion]) VALUES (N'20220615083125_add_10', N'6.0.5')
INSERT [dbo].[__EFMigrationsHistory] ([MigrationId], [ProductVersion]) VALUES (N'20220615094803_add_11', N'6.0.5')
INSERT [dbo].[__EFMigrationsHistory] ([MigrationId], [ProductVersion]) VALUES (N'20220626092426_addTokenTable', N'6.0.6')
GO
SET IDENTITY_INSERT [dbo].[Accounts] ON 

INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Role], [UserId]) VALUES (1, N'test', N'test', N'User', 1)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Role], [UserId]) VALUES (2, N'test2', N'test2', NULL, 2)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Role], [UserId]) VALUES (3, N'a1', N'a1111', N'User', 3)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Role], [UserId]) VALUES (4, N'admin', N'admin', N'Admin', 4)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Role], [UserId]) VALUES (5, N'user', N'EE11CBB19052E40B07AAC0CA060C23EE', N'User', 5)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Role], [UserId]) VALUES (6, N'user2', N'7E58D63B60197CEB55A1C487989A3720', N'User', 6)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Role], [UserId]) VALUES (7, N'user3', N'92877AF70A45FD6A2ED7FE81E1236B78', N'User', 7)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Role], [UserId]) VALUES (8, N'user4', N'3F02EBE3D7929B091E3D8CCFDE2F3BC6', N'User', 8)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Role], [UserId]) VALUES (9, N'phong', N'9F48495BB4B98AC37A1A72C7E6490C7A', N'User', 9)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Role], [UserId]) VALUES (10, N'phong1', N'8C0E4FE20CE7752A5E3441AC873AEA3A', N'User', 10)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Role], [UserId]) VALUES (11, N'phong2', N'86E256DA41D824DE44E181B13992D898', N'User', 11)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Role], [UserId]) VALUES (12, N'phong3', N'D744DC411DC674EBFEBD7B1680B7085C', N'User', 12)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Role], [UserId]) VALUES (13, N'phong4', N'370BFCD7A30DE990924EF7EBED829C2C', N'User', 13)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Role], [UserId]) VALUES (14, N'phong5', N'E9A3E7B8FC6E1F9D66E968113EBE1BA8', N'User', 14)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Role], [UserId]) VALUES (15, N'phong6', N'026B4B6356892D23BBAFAF67A1E5ED88', N'User', 15)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Role], [UserId]) VALUES (16, N'phong7', N'C1D94AEC6C6ABC0DA8D44DAB6EF630C8', N'User', 16)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Role], [UserId]) VALUES (17, N'phong8', N'DA8DF582E1202402D169A90D3E88E9A7', N'User', 17)
INSERT [dbo].[Accounts] ([Id], [Username], [Password], [Role], [UserId]) VALUES (18, N'phong9', N'AAEC15CA45D53852E115B4DD832375D3', N'User', 18)
SET IDENTITY_INSERT [dbo].[Accounts] OFF
GO
SET IDENTITY_INSERT [dbo].[Cities] ON 

INSERT [dbo].[Cities] ([Id], [Name]) VALUES (1, N'Đà Nẵng')
INSERT [dbo].[Cities] ([Id], [Name]) VALUES (2, N'Hà Nội')
INSERT [dbo].[Cities] ([Id], [Name]) VALUES (3, N'TP HCM')
SET IDENTITY_INSERT [dbo].[Cities] OFF
GO
SET IDENTITY_INSERT [dbo].[Districts] ON 

INSERT [dbo].[Districts] ([Id], [Name], [CityId]) VALUES (1, N'Hải Châu', 1)
INSERT [dbo].[Districts] ([Id], [Name], [CityId]) VALUES (2, N'Sơn Trà', 1)
INSERT [dbo].[Districts] ([Id], [Name], [CityId]) VALUES (3, N'Cẩm Lệ', 1)
SET IDENTITY_INSERT [dbo].[Districts] OFF
GO
SET IDENTITY_INSERT [dbo].[Invoices] ON 

INSERT [dbo].[Invoices] ([Id], [CheckinTime], [CheckoutTime], [VehicleId], [SlotId], [TotalPaid]) VALUES (3, CAST(N'2022-01-01T00:00:00.0000000' AS DateTime2), CAST(N'2022-10-01T00:00:00.0000000' AS DateTime2), N'A43-111111', N'A1', 20000)
INSERT [dbo].[Invoices] ([Id], [CheckinTime], [CheckoutTime], [VehicleId], [SlotId], [TotalPaid]) VALUES (4, CAST(N'2022-02-22T00:00:00.0000000' AS DateTime2), CAST(N'2022-02-23T00:00:00.0000000' AS DateTime2), N'A43-123456', N'A2', 12000)
INSERT [dbo].[Invoices] ([Id], [CheckinTime], [CheckoutTime], [VehicleId], [SlotId], [TotalPaid]) VALUES (5, CAST(N'2022-03-03T00:00:00.0000000' AS DateTime2), NULL, N'A43-111222', N'A1', 0)
INSERT [dbo].[Invoices] ([Id], [CheckinTime], [CheckoutTime], [VehicleId], [SlotId], [TotalPaid]) VALUES (6, CAST(N'2022-03-03T00:00:00.0000000' AS DateTime2), NULL, N'A43-123456', N'A4', 0)
INSERT [dbo].[Invoices] ([Id], [CheckinTime], [CheckoutTime], [VehicleId], [SlotId], [TotalPaid]) VALUES (7, CAST(N'2022-06-19T10:26:21.0000000' AS DateTime2), CAST(N'2022-06-19T16:31:50.0000000' AS DateTime2), N'A43-987654', N'A2', 60000)
INSERT [dbo].[Invoices] ([Id], [CheckinTime], [CheckoutTime], [VehicleId], [SlotId], [TotalPaid]) VALUES (8, CAST(N'2022-07-20T09:18:26.0000000' AS DateTime2), CAST(N'2022-07-20T23:11:06.0000000' AS DateTime2), N'A43-555555', N'A2', 130000)
INSERT [dbo].[Invoices] ([Id], [CheckinTime], [CheckoutTime], [VehicleId], [SlotId], [TotalPaid]) VALUES (9, CAST(N'2022-07-20T18:55:47.0000000' AS DateTime2), NULL, N'A43-555558', N'B1', 0)
INSERT [dbo].[Invoices] ([Id], [CheckinTime], [CheckoutTime], [VehicleId], [SlotId], [TotalPaid]) VALUES (10, CAST(N'2022-07-20T20:19:53.0000000' AS DateTime2), CAST(N'2022-07-20T23:12:12.0000000' AS DateTime2), N'A43-100001', N'A6', 20000)
INSERT [dbo].[Invoices] ([Id], [CheckinTime], [CheckoutTime], [VehicleId], [SlotId], [TotalPaid]) VALUES (11, CAST(N'2022-07-20T20:24:41.0000000' AS DateTime2), CAST(N'2022-07-20T23:21:05.0000000' AS DateTime2), N'B43-852963', N'D1', 40000)
INSERT [dbo].[Invoices] ([Id], [CheckinTime], [CheckoutTime], [VehicleId], [SlotId], [TotalPaid]) VALUES (12, CAST(N'2022-07-20T20:26:18.0000000' AS DateTime2), CAST(N'2022-07-20T23:17:55.0000000' AS DateTime2), N'C43-559944', N'E1', 60000)
INSERT [dbo].[Invoices] ([Id], [CheckinTime], [CheckoutTime], [VehicleId], [SlotId], [TotalPaid]) VALUES (13, CAST(N'2022-07-20T20:32:39.0000000' AS DateTime2), CAST(N'2022-07-20T23:31:29.0000000' AS DateTime2), N'A43-123321', N'A5', 20000)
INSERT [dbo].[Invoices] ([Id], [CheckinTime], [CheckoutTime], [VehicleId], [SlotId], [TotalPaid]) VALUES (14, CAST(N'2022-07-20T23:22:21.0000000' AS DateTime2), CAST(N'2022-07-20T23:22:44.0000000' AS DateTime2), N'A43-555555', N'A3', 0)
INSERT [dbo].[Invoices] ([Id], [CheckinTime], [CheckoutTime], [VehicleId], [SlotId], [TotalPaid]) VALUES (15, CAST(N'2022-07-20T23:25:38.0000000' AS DateTime2), CAST(N'2022-07-20T23:25:42.0000000' AS DateTime2), N'C43-559944', N'E2', 0)
SET IDENTITY_INSERT [dbo].[Invoices] OFF
GO
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A1', 1, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A10', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A11', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A12', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A13', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A14', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A15', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A16', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A17', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A18', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A19', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A2', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A20', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A21', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A22', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A3', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A4', 1, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A5', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A6', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A7', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A8', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'A9', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B1', 1, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B10', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B11', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B12', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B13', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B14', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B15', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B16', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B17', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B18', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B19', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B2', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B20', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B21', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B22', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B3', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B4', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B5', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B6', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B7', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B8', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'B9', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C1', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C10', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C11', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C12', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C13', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C14', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C15', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C16', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C17', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C18', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C19', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C2', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C20', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C21', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C22', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C3', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C4', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C5', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C6', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C7', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C8', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'C9', 0, 1)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D1', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D10', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D11', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D12', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D13', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D14', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D15', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D16', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D17', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D18', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D19', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D2', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D20', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D21', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D22', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D3', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D4', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D5', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D6', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D7', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D8', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'D9', 0, 2)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'E1', 0, 3)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'E10', 0, 3)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'E11', 0, 3)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'E12', 0, 3)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'E13', 0, 3)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'E14', 0, 3)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'E2', 0, 3)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'E3', 0, 3)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'E4', 0, 3)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'E5', 0, 3)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'E6', 0, 3)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'E7', 0, 3)
GO
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'E8', 0, 3)
INSERT [dbo].[Slots] ([Id], [Status], [VehicleTypeId]) VALUES (N'E9', 0, 3)
GO
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Feedback], [CityId], [DistrictId], [WardId]) VALUES (1, N'Test user', N'test@gmail.com', N'000000000', NULL, 1, 1, 1)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Feedback], [CityId], [DistrictId], [WardId]) VALUES (2, N'name', N'test2@gmail.com', N'123987675', NULL, 1, 1, 2)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Feedback], [CityId], [DistrictId], [WardId]) VALUES (3, N'a11', N'a1', N'123123123', NULL, NULL, NULL, NULL)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Feedback], [CityId], [DistrictId], [WardId]) VALUES (4, N'admin name', N'admin@parking.com', N'123456789', NULL, NULL, NULL, NULL)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Feedback], [CityId], [DistrictId], [WardId]) VALUES (5, N'Useruser', NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Feedback], [CityId], [DistrictId], [WardId]) VALUES (6, N'Useruser2', NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Feedback], [CityId], [DistrictId], [WardId]) VALUES (7, N'Useruser3', NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Feedback], [CityId], [DistrictId], [WardId]) VALUES (8, N'Useruser4', NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Feedback], [CityId], [DistrictId], [WardId]) VALUES (9, N'thai phong', N'phong123@gmail.com', N'012 345 6789', NULL, NULL, NULL, NULL)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Feedback], [CityId], [DistrictId], [WardId]) VALUES (10, N'thai phong 1', N'phong1@gmail.com', N'123 123 1234', NULL, NULL, NULL, NULL)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Feedback], [CityId], [DistrictId], [WardId]) VALUES (11, N'Userphong2', NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Feedback], [CityId], [DistrictId], [WardId]) VALUES (12, N'Userphong3', NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Feedback], [CityId], [DistrictId], [WardId]) VALUES (13, N'Userphong4', NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Feedback], [CityId], [DistrictId], [WardId]) VALUES (14, N'Userphong5', NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Feedback], [CityId], [DistrictId], [WardId]) VALUES (15, N'Userphong6', NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Feedback], [CityId], [DistrictId], [WardId]) VALUES (16, N'Userphong7', NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Feedback], [CityId], [DistrictId], [WardId]) VALUES (17, N'Userphong8', NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Users] ([Id], [Name], [Email], [Phone], [Feedback], [CityId], [DistrictId], [WardId]) VALUES (18, N'Userphong9', NULL, NULL, NULL, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
SET IDENTITY_INSERT [dbo].[ValidTokens] ON 

INSERT [dbo].[ValidTokens] ([Id], [Value], [UserId]) VALUES (1, N'bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiIxIiwicm9sZSI6IlVzZXIiLCJuYmYiOjE2NTYyNTI2MTMsImV4cCI6MTY1NjI2MzQxMywiaWF0IjoxNjU2MjUyNjEzfQ.w8OIk5PVD5nV5xijrySdP1L4yKw3TqRlbbpduSactG4', 1)
INSERT [dbo].[ValidTokens] ([Id], [Value], [UserId]) VALUES (2, NULL, 2)
INSERT [dbo].[ValidTokens] ([Id], [Value], [UserId]) VALUES (3, NULL, 3)
INSERT [dbo].[ValidTokens] ([Id], [Value], [UserId]) VALUES (4, NULL, 4)
INSERT [dbo].[ValidTokens] ([Id], [Value], [UserId]) VALUES (5, N'bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiI1Iiwicm9sZSI6IlVzZXIiLCJuYmYiOjE2NTc4MTU2OTcsImV4cCI6MTY1NzgyNjQ5NywiaWF0IjoxNjU3ODE1Njk3fQ.MRMsTMLCPRbLSZTfXrk4_lpgdkVz8_1X48w-oucRoKs', 5)
INSERT [dbo].[ValidTokens] ([Id], [Value], [UserId]) VALUES (6, NULL, 6)
INSERT [dbo].[ValidTokens] ([Id], [Value], [UserId]) VALUES (7, N'bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiI3Iiwicm9sZSI6IlVzZXIiLCJuYmYiOjE2NTYyNjE0MzMsImV4cCI6MTY1NjI3MjIzMywiaWF0IjoxNjU2MjYxNDMzfQ.XZoDpCpZQFkXo_IJZtRKjbR-3uCRHxVolKaIcWLFtH4', 7)
INSERT [dbo].[ValidTokens] ([Id], [Value], [UserId]) VALUES (8, NULL, 8)
INSERT [dbo].[ValidTokens] ([Id], [Value], [UserId]) VALUES (9, N'bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiI5Iiwicm9sZSI6IlVzZXIiLCJuYmYiOjE2NTgxNTk1NDUsImV4cCI6MTY1ODE3MDM0NSwiaWF0IjoxNjU4MTU5NTQ1fQ.dcjBNbNhGbFpPUhD8maeT-V-2A4leB6P9HilqnkDkB0', 9)
SET IDENTITY_INSERT [dbo].[ValidTokens] OFF
GO
INSERT [dbo].[Vehicles] ([Id], [VehicleName], [VehicleBrand], [UserID], [VehicleTypeId], [IsParking]) VALUES (N'A43-100001', NULL, NULL, 9, 1, 0)
INSERT [dbo].[Vehicles] ([Id], [VehicleName], [VehicleBrand], [UserID], [VehicleTypeId], [IsParking]) VALUES (N'A43-111111', N'aaa', N'aaa', 2, 2, 0)
INSERT [dbo].[Vehicles] ([Id], [VehicleName], [VehicleBrand], [UserID], [VehicleTypeId], [IsParking]) VALUES (N'A43-111222', N'Toyota', N'Toyota', 1, 1, 1)
INSERT [dbo].[Vehicles] ([Id], [VehicleName], [VehicleBrand], [UserID], [VehicleTypeId], [IsParking]) VALUES (N'A43-123321', N'Nissan', N'Nissan', 9, 1, 0)
INSERT [dbo].[Vehicles] ([Id], [VehicleName], [VehicleBrand], [UserID], [VehicleTypeId], [IsParking]) VALUES (N'A43-123456', N'Toyota1', N'Toyota', 1, 1, 1)
INSERT [dbo].[Vehicles] ([Id], [VehicleName], [VehicleBrand], [UserID], [VehicleTypeId], [IsParking]) VALUES (N'A43-555555', NULL, NULL, 9, 1, 0)
INSERT [dbo].[Vehicles] ([Id], [VehicleName], [VehicleBrand], [UserID], [VehicleTypeId], [IsParking]) VALUES (N'A43-555556', NULL, NULL, 10, 1, 0)
INSERT [dbo].[Vehicles] ([Id], [VehicleName], [VehicleBrand], [UserID], [VehicleTypeId], [IsParking]) VALUES (N'A43-555558', NULL, NULL, 9, 1, 1)
INSERT [dbo].[Vehicles] ([Id], [VehicleName], [VehicleBrand], [UserID], [VehicleTypeId], [IsParking]) VALUES (N'A43-987654', N'Honda1', N'Honda', 1, 1, 0)
INSERT [dbo].[Vehicles] ([Id], [VehicleName], [VehicleBrand], [UserID], [VehicleTypeId], [IsParking]) VALUES (N'B43-559977', NULL, NULL, 11, 2, 0)
INSERT [dbo].[Vehicles] ([Id], [VehicleName], [VehicleBrand], [UserID], [VehicleTypeId], [IsParking]) VALUES (N'B43-852963', N'Huyndai', N'Huyndai', 9, 2, 0)
INSERT [dbo].[Vehicles] ([Id], [VehicleName], [VehicleBrand], [UserID], [VehicleTypeId], [IsParking]) VALUES (N'B43-999999', N'Huyndai1', N'Huyndai', 1, 3, 0)
INSERT [dbo].[Vehicles] ([Id], [VehicleName], [VehicleBrand], [UserID], [VehicleTypeId], [IsParking]) VALUES (N'C43-559944', N'', N'', 9, 3, 0)
GO
SET IDENTITY_INSERT [dbo].[VehicleTypes] ON 

INSERT [dbo].[VehicleTypes] ([Id], [TypeName], [PricePerHour], [PricePerMonth], [PricePerWeek], [PricePerDay], [PricePerYear]) VALUES (1, N'Car', 10000, 1200000, 400000, 100000, 6000000)
INSERT [dbo].[VehicleTypes] ([Id], [TypeName], [PricePerHour], [PricePerMonth], [PricePerWeek], [PricePerDay], [PricePerYear]) VALUES (2, N'Bus', 20000, 2400000, 800000, 200000, 12000000)
INSERT [dbo].[VehicleTypes] ([Id], [TypeName], [PricePerHour], [PricePerMonth], [PricePerWeek], [PricePerDay], [PricePerYear]) VALUES (3, N'Truck', 30000, 3600000, 1200000, 300000, 18000000)
SET IDENTITY_INSERT [dbo].[VehicleTypes] OFF
GO
SET IDENTITY_INSERT [dbo].[Wards] ON 

INSERT [dbo].[Wards] ([Id], [Name], [DistrictId]) VALUES (1, N'Thanh Bình', 1)
INSERT [dbo].[Wards] ([Id], [Name], [DistrictId]) VALUES (2, N'Thuận Phước', 1)
SET IDENTITY_INSERT [dbo].[Wards] OFF
GO
/****** Object:  Index [IX_Accounts_UserId]    Script Date: 7/21/2022 11:06:22 AM ******/
CREATE NONCLUSTERED INDEX [IX_Accounts_UserId] ON [dbo].[Accounts]
(
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_Districts_CityId]    Script Date: 7/21/2022 11:06:22 AM ******/
CREATE NONCLUSTERED INDEX [IX_Districts_CityId] ON [dbo].[Districts]
(
	[CityId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [IX_Invoices_SlotId]    Script Date: 7/21/2022 11:06:22 AM ******/
CREATE NONCLUSTERED INDEX [IX_Invoices_SlotId] ON [dbo].[Invoices]
(
	[SlotId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [IX_Invoices_VehicleId]    Script Date: 7/21/2022 11:06:22 AM ******/
CREATE NONCLUSTERED INDEX [IX_Invoices_VehicleId] ON [dbo].[Invoices]
(
	[VehicleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_Slots_VehicleTypeId]    Script Date: 7/21/2022 11:06:22 AM ******/
CREATE NONCLUSTERED INDEX [IX_Slots_VehicleTypeId] ON [dbo].[Slots]
(
	[VehicleTypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_Users_CityId]    Script Date: 7/21/2022 11:06:22 AM ******/
CREATE NONCLUSTERED INDEX [IX_Users_CityId] ON [dbo].[Users]
(
	[CityId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_Users_DistrictId]    Script Date: 7/21/2022 11:06:22 AM ******/
CREATE NONCLUSTERED INDEX [IX_Users_DistrictId] ON [dbo].[Users]
(
	[DistrictId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_Users_WardId]    Script Date: 7/21/2022 11:06:22 AM ******/
CREATE NONCLUSTERED INDEX [IX_Users_WardId] ON [dbo].[Users]
(
	[WardId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_ValidTokens_UserId]    Script Date: 7/21/2022 11:06:22 AM ******/
CREATE NONCLUSTERED INDEX [IX_ValidTokens_UserId] ON [dbo].[ValidTokens]
(
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_Vehicles_UserID]    Script Date: 7/21/2022 11:06:22 AM ******/
CREATE NONCLUSTERED INDEX [IX_Vehicles_UserID] ON [dbo].[Vehicles]
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_Vehicles_VehicleTypeId]    Script Date: 7/21/2022 11:06:22 AM ******/
CREATE NONCLUSTERED INDEX [IX_Vehicles_VehicleTypeId] ON [dbo].[Vehicles]
(
	[VehicleTypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_Wards_DistrictId]    Script Date: 7/21/2022 11:06:22 AM ******/
CREATE NONCLUSTERED INDEX [IX_Wards_DistrictId] ON [dbo].[Wards]
(
	[DistrictId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Invoices] ADD  DEFAULT ((0.0000000000000000e+000)) FOR [TotalPaid]
GO
ALTER TABLE [dbo].[Vehicles] ADD  DEFAULT (CONVERT([bit],(0))) FOR [IsParking]
GO
ALTER TABLE [dbo].[VehicleTypes] ADD  DEFAULT ((0.0000000000000000e+000)) FOR [PricePerMonth]
GO
ALTER TABLE [dbo].[VehicleTypes] ADD  DEFAULT ((0.0000000000000000e+000)) FOR [PricePerWeek]
GO
ALTER TABLE [dbo].[VehicleTypes] ADD  DEFAULT ((0.0000000000000000e+000)) FOR [PricePerDay]
GO
ALTER TABLE [dbo].[VehicleTypes] ADD  DEFAULT ((0.0000000000000000e+000)) FOR [PricePerYear]
GO
ALTER TABLE [dbo].[Accounts]  WITH CHECK ADD  CONSTRAINT [FK_Accounts_Users_UserId] FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Accounts] CHECK CONSTRAINT [FK_Accounts_Users_UserId]
GO
ALTER TABLE [dbo].[Districts]  WITH CHECK ADD  CONSTRAINT [FK_Districts_Cities_CityId] FOREIGN KEY([CityId])
REFERENCES [dbo].[Cities] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Districts] CHECK CONSTRAINT [FK_Districts_Cities_CityId]
GO
ALTER TABLE [dbo].[Invoices]  WITH CHECK ADD  CONSTRAINT [FK_Invoices_Slots_SlotId] FOREIGN KEY([SlotId])
REFERENCES [dbo].[Slots] ([Id])
GO
ALTER TABLE [dbo].[Invoices] CHECK CONSTRAINT [FK_Invoices_Slots_SlotId]
GO
ALTER TABLE [dbo].[Invoices]  WITH CHECK ADD  CONSTRAINT [FK_Invoices_Vehicles_VehicleId] FOREIGN KEY([VehicleId])
REFERENCES [dbo].[Vehicles] ([Id])
GO
ALTER TABLE [dbo].[Invoices] CHECK CONSTRAINT [FK_Invoices_Vehicles_VehicleId]
GO
ALTER TABLE [dbo].[Slots]  WITH CHECK ADD  CONSTRAINT [FK_Slots_VehicleTypes_VehicleTypeId] FOREIGN KEY([VehicleTypeId])
REFERENCES [dbo].[VehicleTypes] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Slots] CHECK CONSTRAINT [FK_Slots_VehicleTypes_VehicleTypeId]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_Users_Cities_CityId] FOREIGN KEY([CityId])
REFERENCES [dbo].[Cities] ([Id])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_Users_Cities_CityId]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_Users_Districts_DistrictId] FOREIGN KEY([DistrictId])
REFERENCES [dbo].[Districts] ([Id])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_Users_Districts_DistrictId]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_Users_Wards_WardId] FOREIGN KEY([WardId])
REFERENCES [dbo].[Wards] ([Id])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_Users_Wards_WardId]
GO
ALTER TABLE [dbo].[ValidTokens]  WITH CHECK ADD  CONSTRAINT [FK_ValidTokens_Users_UserId] FOREIGN KEY([UserId])
REFERENCES [dbo].[Users] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ValidTokens] CHECK CONSTRAINT [FK_ValidTokens_Users_UserId]
GO
ALTER TABLE [dbo].[Vehicles]  WITH CHECK ADD  CONSTRAINT [FK_Vehicles_Users_UserID] FOREIGN KEY([UserID])
REFERENCES [dbo].[Users] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Vehicles] CHECK CONSTRAINT [FK_Vehicles_Users_UserID]
GO
ALTER TABLE [dbo].[Vehicles]  WITH CHECK ADD  CONSTRAINT [FK_Vehicles_VehicleTypes_VehicleTypeId] FOREIGN KEY([VehicleTypeId])
REFERENCES [dbo].[VehicleTypes] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Vehicles] CHECK CONSTRAINT [FK_Vehicles_VehicleTypes_VehicleTypeId]
GO
ALTER TABLE [dbo].[Wards]  WITH CHECK ADD  CONSTRAINT [FK_Wards_Districts_DistrictId] FOREIGN KEY([DistrictId])
REFERENCES [dbo].[Districts] ([Id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Wards] CHECK CONSTRAINT [FK_Wards_Districts_DistrictId]
GO
USE [master]
GO
ALTER DATABASE [ParkingDB] SET  READ_WRITE 
GO
