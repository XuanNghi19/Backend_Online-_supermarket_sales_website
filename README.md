Giải thích các Annotation
@RequiredArgsConstructor : được sử dụng để tự động tạo ra một constructor (inject từ các bean) chỉ chứa các tham số được đánh dấu là final hoặc @NonNull.<br>
  => thay thế cho @Autowire <br>
@JsonProperty : được sử dụng để xác định tên của thuộc tính khi chuyển đổi giữa đối tượng Java và JSON.

FIX LỖI LẦN 1: Không tạo thành công Repository gây xung đột Hibernate
  + Lưu ý cần khai báo @ManyToOne hoặc những anotation tương tự khi muốn tham chiếu khóa ngoài đến các Entity khác
  + Khi khai báo JpaRepository nên để ý import org.hibernate.stat.Statistics; Vì nó là khai báo tên bảng chứ không phải Entity
