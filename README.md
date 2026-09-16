# springboot-restapi-ajax

Bài tập Phần 2 (về nhà) - Lập trình Web (Restful API):

- **Mục 3**: CRUD REST API cho bảng `Category` (và mở rộng thêm cho `Product`) theo hướng dẫn
  "HƯỚNG DẪN CRUD API CATEGORY TRÊN SPRING BOOT 3".
- **Mục 4**: Cấu hình Swagger 3 (springdoc-openapi) theo hướng dẫn
  "CẤU HÌNH SWAGGER2 VÀ SWAGGER 3 TRÊN SPRING BOOT".
- **Mục 5**: Viết API và render lên giao diện bằng AJAX (jQuery) cho CRUD Category và Product
  theo hướng dẫn "HƯỚNG DẪN AJAX VỚI RESTFUL API TRONG SPRING BOOT".

## Công nghệ
Spring Boot 4.1.1, Spring Data JPA, Thymeleaf + Layout Dialect, Lombok,
springdoc-openapi-starter-webmvc-ui 3.1.1 (Swagger3), jQuery AJAX, Bootstrap 5, SQL Server.

## Cấu trúc
```
vn.iotstar
 ├── config/StorageProperties.java, WebConfig.java
 ├── exception/StorageException.java, StorageFileNotFoundException.java
 ├── entity/Category.java, Product.java              // quan hệ 1-N
 ├── repository/CategoryRepository.java, ProductRepository.java
 ├── service/ICategoryService, CategoryServiceImpl
 │        IProductService, ProductServiceImpl
 │        IStorageService, FileSystemStorageServiceImpl   // upload file
 ├── model/Response.java                              // {status, message, body}
 ├── controllers/HomeController.java                   // render trang AJAX
 └── controllers/api/
        CategoryAPIController.java   // GET / POST getCategory / addCategory / updateCategory / deleteCategory
        ProductApiController.java    // GET / getProduct / addProduct / updateProduct / deleteProduct
resources/templates
 ├── layouts/layout.html, fragments/header.html, footer.html
 ├── categories/ajax.html   // CRUD Category bằng jQuery AJAX + modal Bootstrap
 └── products/ajax.html     // CRUD Product bằng jQuery AJAX + modal Bootstrap
```

## API Endpoints

| Method | URL                          | Mô tả                     |
|--------|------------------------------|---------------------------|
| GET    | /api/category                | Lấy tất cả Category       |
| POST   | /api/category/getCategory    | Lấy 1 Category theo id    |
| POST   | /api/category/addCategory    | Thêm Category (multipart) |
| PUT    | /api/category/updateCategory | Cập nhật Category          |
| DELETE | /api/category/deleteCategory | Xóa Category               |
| GET    | /api/product                 | Lấy tất cả Product         |
| POST   | /api/product/getProduct      | Lấy 1 Product theo id      |
| POST   | /api/product/addProduct      | Thêm Product (multipart)   |
| PUT    | /api/product/updateProduct   | Cập nhật Product            |
| DELETE | /api/product/deleteProduct   | Xóa Product                 |

Tất cả API trả về theo format `Response { status, message, body }`.

## Chạy chương trình
1. Tạo database: xem `database.sql` (`CREATE DATABASE webst2_restapi;`).
2. Sửa `src/main/resources/application.properties`: đổi `spring.datasource.password` cho đúng
   tài khoản SQL Server trên máy bạn.
3. Chạy `SpringbootRestApiAjaxApplication.java` hoặc `mvn spring-boot:run`.
4. Truy cập:
   - Giao diện AJAX: http://localhost:8090/categories và http://localhost:8090/products
   - Swagger UI: http://localhost:8090/swagger-ui/index.html
   - OpenAPI JSON: http://localhost:8090/v3/api-docs

## Ghi chú
- File icon/ảnh sản phẩm lưu vật lý trong thư mục `uploads/`, CSDL chỉ lưu tên file.
- Khi cập nhật mà không chọn ảnh mới, hệ thống tự giữ ảnh cũ (xử lý trong `CategoryServiceImpl.save()`
  và `ProductServiceImpl.save()`).
