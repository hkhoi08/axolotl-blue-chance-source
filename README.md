# Axolotl Blue Chance (Fabric Mod cho Minecraft 26.2)

Mod này tăng tỉ lệ axolotl con sinh ra là màu **xanh (blue)** hiếm lên khoảng
**50%** mỗi lần 2 axolotl sinh sản, thay vì tỉ lệ gốc 1/1200 (~0.083%).

## VÌ SAO MÌNH GỬI SOURCE CODE THAY VÌ FILE .JAR ĐÃ BUILD SẴN?

Việc build mod Fabric cần tải các thư viện gốc của Minecraft (Mojang) và
Fabric Loader/API từ maven.fabricmc.net, libraries.minecraft.net... Môi
trường chạy code của mình không có quyền truy cập các server đó, nên
mình không thể tự biên dịch ra file `.jar` cuối cùng. Bạn cần build ở máy
mình với 3 bước dưới đây (chỉ mất khoảng 5-10 phút, không cần biết code).

## YÊU CẦU

- Java Development Kit (JDK) **21 trở lên** đã cài đặt
- Kết nối Internet (để Gradle tải thư viện Minecraft/Fabric)
- Đã cài Fabric Loader 26.2 trong Minecraft Launcher (bạn nói là đã có rồi)

## CÁC BƯỚC BUILD

1. Giải nén project này ra một thư mục, ví dụ `axolotl-blue-chance/`

2. Mở terminal / CMD tại thư mục đó, chạy lệnh để tạo Gradle wrapper (chỉ cần làm 1 lần):
   ```
   gradle wrapper --gradle-version 8.11
   ```
   (Nếu máy bạn chưa có lệnh `gradle`, tải Gradle tại https://gradle.org/install/
   hoặc dùng IDE IntelliJ IDEA — mở thư mục này bằng IntelliJ, nó sẽ tự tạo wrapper)

3. Build mod:
   ```
   ./gradlew build          (Mac/Linux)
   gradlew.bat build         (Windows)
   ```

4. File `.jar` sẽ nằm ở: `build/libs/axolotl-blue-chance-1.0.0.jar`
   Copy file này vào thư mục `mods` của Minecraft (cùng chỗ bạn để Fabric API).

## NẾU BUILD BỊ LỖI (rất có thể xảy ra vì đây là bản 26.2 quá mới)

Bản 26.2 vừa chuyển hẳn từ obfuscated + Yarn mappings sang code gốc của
Mojang (unobfuscated). Mình đã viết mixin dựa trên cấu trúc class `Axolotl`
đã ổn định nhiều năm qua trong Mojang mappings, nhưng **không thể đảm bảo
100%** tên phương thức `getBreedOffspring` hay `setVariant` không đổi tên
trong bản 26.2 vì mình không truy cập được source thật của bản này.

Nếu Gradle báo lỗi kiểu "cannot find method getBreedOffspring" hoặc tương tự:

1. Chạy `./gradlew genSources` để giải nén source code thật của Minecraft 26.2
2. Trong IDE (khuyên dùng IntelliJ IDEA + plugin Fabric), mở class `Axolotl.java`
   đã được decompile, tìm phương thức tạo con khi breeding (thường gần chỗ
   class implement `AgeableMob` hoặc extends `Animal`)
3. Sửa lại đúng tên trong file `src/main/java/.../mixin/AxolotlMixin.java`
   (chỉ cần đổi 2 chỗ: tên method trong `@Inject(method = "...")` và tên hàm
   `.setVariant(...)` nếu nó có tên khác)

Nếu bạn gặp lỗi cụ thể, dán nội dung lỗi vào đây, mình sẽ giúp sửa tiếp.

## THAY ĐỔI TỈ LỆ

Muốn đổi 50% thành số khác? Mở file
`src/main/java/com/example/axolotlbluechance/AxolotlBlueChanceMod.java`,
sửa dòng:
```java
public static final float BLUE_CHANCE = 0.5F; // 0.5 = 50%, 0.25 = 25%, ...
```
