# Hotel Booking Management System - Backend (Java Spring Boot)

## 專案簡介
一個基於 Spring Boot 和 MySQL 的飯店房間管理系統，提供房間管理、訂房系統管理等功能。

## 技術
- **後端**: Java Spring Boot
- **前端**: Angular
- **數據庫**: MySQL

## 啟動指南
1. 安裝所需依賴：`mvn install`
2. 啟動後端：`mvn spring-boot:run`
3. 啟動前端：進入 `/angular` 目錄，執行 `ng serve`

## 文件結構
```
/src
 ├── main
 │   ├── java/com/service/HotelService
 │   │   ├── config
 │   │   │   ├── CorsFilter.java
 │   │   │   ├── JwtAuthFilter.java
 │   │   │   └── WebSecurityConfiguration.java
 │   │   ├── controller
 │   │   │   ├── admin
 │   │   │   │   ├── ReservationController.java
 │   │   │   │   └── RoomsController.java
 │   │   │   ├── customer
 │   │   │   │   ├── BookingController.java
 │   │   │   │   └── RoomController.java
 │   │   │   └── UserAuthController.java
 │   │   ├── dto
 │   │   │   ├── AuthenticationReq.java
 │   │   │   ├── AuthenticationRes.java
 │   │   │   ├── RoomResDto.java
 │   │   │   ├── RoomsDto.java
 │   │   │   ├── RoomStatusDto.java
 │   │   │   ├── RoomStatusResDto.java
 │   │   │   ├── SignReq.java
 │   │   │   └── UserDto.java
 │   │   ├── entity
 │   │   │   ├── Rooms.java
 │   │   │   ├── RoomStatus.java
 │   │   │   └── User.java
 │   │   ├── enums
 │   │   │   ├── RoomsStatus.java
 │   │   │   └── UserRole.java
 │   │   ├── repo
 │   │   │   ├── RoomsRepo.java
 │   │   │   ├── RoomStatusRepo.java
 │   │   │   └── UserRepo.java
 │   │   ├── service
 │   │   │   ├── admin
 │   │   │   │   ├── ReservationService.java
 │   │   │   │   ├── ReservationServiceImpl.java
 │   │   │   │   ├── RoomsService.java
 │   │   │   │   └── RoomsServiceImpl.java
 │   │   │   ├── customer
 │   │   │   │   ├── BookingService.java
 │   │   │   │   ├── BookingServiceImpl.java
 │   │   │   │   ├── RoomService.java
 │   │   │   │   └── RoomServiceImpl.java
 │   │   │   ├── AuthService.java
 │   │   │   ├── AuthServiceImpl.java
 │   │   │   ├── UserService.java
 │   │   │   └── UserServiceImpl.java
 │   │   ├── utill
 │   │   │   └── JwtUtill.java
 │   │   └── HotelServiceApplication.java
 │   └── resources
 │       └── application.properties
 └── test
     └── java/com/service/HotelService
         └── HotelServiceApplicationTests.java
```

## 文件解釋
/src/main/java/com/service/HotelService
主程式區，包含整個應用的核心邏輯模組。
📁 config/
配置類別，例如 CORS 過濾器、安全性設定等。
範例檔案：JwtAuthFilter.java, WebSecurityConfiguration.java
📁 controller/
負責處理 API 請求的進入點（REST API）。
區分 admin（管理者）與 customer（顧客）功能。
範例：BookingController.java, RoomsController.java, UserAuthController.java
📁 dto/ (Data Transfer Object)
用於資料交換的物件模型（輸入輸出格式）。
範例：AuthenticationRes.java, RoomStatusDto.java
📁 entity/
對應資料庫的實體類別（JPA Entity）。
範例：Rooms.java, User.java
📁 enums/
儲存專案中使用的列舉類型。
範例：RoomsStatus.java, UserRole.java
📁 repo/
資料存取層（DAO），使用 Spring Data JPA。
範例：UserRepo.java, RoomsRepo.java
📁 service/
商業邏輯層（Service Layer）。
分為 admin 和 customer 服務，搭配 interface + impl 模式。
範例：
RoomsService.java / RoomsServiceImpl.java
AuthService.java / AuthServiceImpl.java
📁 utill/
工具類（如 JWT 工具類）
範例：JwtUtill.java

📄 HotelServiceApplication.java
專案主程式入口（Spring Boot 啟動點）
