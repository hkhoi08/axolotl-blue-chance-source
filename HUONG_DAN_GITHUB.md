# HƯỚNG DẪN: Build mod tự động bằng GitHub (không cần cài gì trên máy)

Không cần biết lập trình, không cần cài Java/Gradle. Chỉ cần trình duyệt web
và 1 tài khoản GitHub miễn phí. Làm theo đúng từng bước dưới đây.

## BƯỚC 1 — Tạo tài khoản GitHub (bỏ qua nếu đã có)
1. Vào https://github.com/signup
2. Đăng ký bằng email, làm theo hướng dẫn tạo tài khoản (miễn phí)

## BƯỚC 2 — Tạo Repository mới
1. Đăng nhập xong, bấm dấu **"+"** ở góc trên bên phải → chọn **"New repository"**
2. Đặt tên tùy ý, ví dụ: `axolotl-blue-chance`
3. Chọn **Public** (bắt buộc để dùng GitHub Actions miễn phí)
4. KHÔNG tick "Add a README file" (để trống)
5. Bấm **"Create repository"**

## BƯỚC 3 — Upload toàn bộ source code
1. Ở trang repository vừa tạo, tìm dòng chữ **"uploading an existing file"**
   (nằm giữa trang, ngay dưới hướng dẫn dùng git command line) → bấm vào đó
2. Giải nén file `axolotl-blue-chance-source.zip` mình gửi ra một thư mục
   trên máy bạn
3. Kéo thả **TẤT CẢ** file và thư mục con bên trong (bao gồm cả thư mục ẩn
   `.github`) vào ô upload của GitHub
   - Lưu ý: phải kéo thả đúng những gì bên TRONG thư mục
     `axolotl-blue-chance/`, không kéo cả thư mục cha bọc ngoài
   - Nếu trình duyệt không cho kéo thư mục `.github` (một số trình duyệt
     ẩn thư mục bắt đầu bằng dấu chấm), xem mục "GHI CHÚ" bên dưới
4. Cuộn xuống dưới, bấm nút xanh **"Commit changes"**

## BƯỚC 4 — Theo dõi quá trình build
1. Vào tab **"Actions"** ở trên cùng của repository
2. Bạn sẽ thấy 1 dòng chạy tên "Build Fabric Mod" với biểu tượng vòng tròn
   vàng đang xoay (đang build) — đợi khoảng 2-5 phút
3. Khi biểu tượng chuyển thành **dấu tick xanh** → build thành công

   Nếu chuyển thành **dấu X đỏ** → build lỗi, bấm vào đó xem log lỗi,
   copy nội dung lỗi gửi cho mình, mình sẽ sửa code giúp bạn.

## BƯỚC 5 — Tải file .jar về
1. Vẫn ở dòng build đã chạy xong (dấu tick xanh), bấm vào nó
2. Cuộn xuống dưới cùng, thấy mục **"Artifacts"**
3. Bấm vào **"axolotl-blue-chance-jar"** để tải file zip chứa file `.jar` về máy
4. Giải nén, lấy file `.jar` bỏ vào thư mục `mods` của Minecraft (Fabric)

## GHI CHÚ — Nếu không kéo thả được thư mục .github
Một số trình duyệt/máy tính ẩn thư mục có tên bắt đầu bằng dấu chấm. Nếu
gặp trường hợp này:
1. Trên GitHub, sau khi upload xong các file/thư mục còn lại, bấm
   **"Add file" → "Create new file"**
2. Ở ô đặt tên file, gõ chính xác: `.github/workflows/build.yml`
   (gõ cả dấu `/`, GitHub sẽ tự tạo thư mục)
3. Mở file `build.yml` mình gửi bằng Notepad, copy toàn bộ nội dung,
   dán vào ô nội dung trên GitHub
4. Bấm "Commit changes"
5. Quay lại BƯỚC 4

## MUỐN ĐỔI TỈ LỆ MÀU XANH
Trên GitHub, mở file:
`src/main/java/com/example/axolotlbluechance/AxolotlBlueChanceMod.java`
bấm biểu tượng cây bút (Edit), sửa dòng:
```java
public static final float BLUE_CHANCE = 0.5F; // 0.5 = 50%
```
Bấm "Commit changes" — GitHub Actions sẽ tự động build lại file jar mới,
quay lại BƯỚC 4 để tải bản mới.
