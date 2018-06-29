package saigontourist.pm1.vnpt.com.saigontourist.domain.repository;

/**
 * Created by MinhDN on 18/4/2018.
 */
public class ServiceUrl {
    public static final String POST_USER_LOGIN = "/apiLogin/DangNhapHoiVien";
    public static final String GET_USER_INFO = "/Api_LayThongTinHoiVien";

    public static final String POST_REGISTER_USER = "/api/saigontourist/dang-ky";
    public static final String POST_SUBMIT_REGISTER = "/api/saigontourist/dang-ky/ConfirmOTP";
    public static final String GET_CARD_MEMBER_BARCODE_QRCODE = "/api/saigontourist/thongtinthe";
    public static final String GET_CARD_MEMBER_CAPTCHA= "/captcha.png";
    public static final String GET_CARD_MEMBER_CREATE_PASS_CODE = "/api/thongtinthe/TaoMaPin";
    public static final String GET_CARD_MEMBER_AUTHENTICATION = "api/thongtinthe/xacthucnguoidung";

    //Vpoint
    public static final String GET_LIST_ENTERPRISE_URL = "/mypage.api/rest/merchants/mobileAll";
    public static final String GET_LIST_FIELDS_URL = "/mypage.api/rest/categories/mobileAll";
    public static final String GET_LIST_CITIES_URL = "/mypage.api/rest/areas/mobileAll";
    public static final String GET_DETAIL_NEWS_URL = "/mypage.api/rest/news/{newsId}";
    public static final String GET_LIST_SHOP_BY_NEW_URL = "/mypage.api/rest/news/shopsByNews/{newsId}";
    public static final String GET_DETAIL_INFO_ENTERPRISE_VPOINT_URL = "/mypage.api/rest/partner/{id}";
    public static final String GET_LIST_NEWS_PROMOTION_OF_ENTERPRISE_VPOINT_URL = "mypage.api/rest/merchants/getAllNewsByMerchant/{id}"; //2.10
    public static final String GET_LIST_SHOP_OF_ENTERPRISE_VPOINT_URL = "/mypage.api/rest/merchants/getShopsByMerchant/{id}";


    //Saigon
    public static final String GET_LIST_PROVINCECODE = "/Api_DanhSachDiaBan";
    public static final String GET_LIST_TRADETMARK = "/Api_DanhSachDoanhNghiep";
    public static final String GET_LIST_FIELDSTOURIS = "/Api_DanhSachLinhVuc";
    public static final String GET_DETAIL_SPECIAL_OFFERS = "/Api_XemThongTinChuongTrinhUuDai";
    public static final String GET_SHOP_ON_MAP_SAIGON_URL = "/Api_TimKiemCuaHang";
    public static final String GET_INFO_ENTERPRISE_SAIGON_URL = "/api/Mobile/API_ThongTinDonVi_ByChuongTrinhUuDai";
    public static final String GET_ENTERPRISE_SPECIAL_SAIGON_URL = "/api/Mobile/API_ChiTietUuDai_UuDaiHienCo";
    public static final String GET_ENTERPRISE_SHOP_SAIGON_URL = "/api/Mobile/API_ChiTietUuDai_DanhSachCuaHang";

    // point
    public static final String GET_HISTORY_POINT = "/api/APIForAppMobile/Api_GetLichSuDiem";
    public static final String GET_POINT_INFO = "/api/saigontourist/thongtindiem";
    public static final String CHECK_DIEU_KIEN_TANG_DIEM = "/api/APIForAppMobile/Api_KiemTraDieuKienTangDiem";
    public static final String CHECK_CHUC_NANG_TANG_DIEM = "/api/APIForAppMobile/Api_KiemTraChucNangTangDiem";
    public static final String SEND_POINT = "/api/APIForAppMobile/Api_TangDiem";
    public static final String LIST_SHOP_ON_MAP_VPOINT_URL = "/mypage.api/rest/shops";


    // FAQ
    public static final String GET_FAQ = "/TrangHoiDap";
    public static final String GET_POLICY = "/api/Mobile/API_ChinhSach";

    // change user info
    public static final String CHANGE_PASSWORD = "/DoiMatKhauMB";
    public static final String CHANGE_EMAIL = "/SuaEmail";
    public static final String CHANGE_PHONE_NUMBER = "/SuaSoDienThoai";
    public static final String CHANGE_USER_INFO = "/SuaThongTinHoiVienMB";
    public static final String CREATE_OTP = "/SinhOTPMB";
    public static final String VERIFY_OTP = "/XacThucMaOTP";
    //end

    // notification
    public static final String GET_NOTIFICATION = "/APIGetByHoiVien/GetThongBaoByHoiVien";
    public static final String SEND_TOKEN_FIREBASE = "/api/saigontourist/LuuTokenFirebase";
    // end

    // capcha
    public static final String GET_CAPCHA = "/captcha.png";
    // end
    // forgot password
    public static final String FORGOT_PASSWORD = "/QuenMatKhau";
    // end

    // local
    public static final String GET_PROVINCE = "/API_TinhThanh";
    public static final String GET_DISTRICT = "/API_QuanHuyen";
    public static final String GET_VILLAGE = "/API_PhuongXa";
    // end

    // change user image
    public static final String CHANGE_USER_IMAGE = "/API_DoiAnhDaiDien";
    // end

    // lịch sử hạng
    public static final String GET_HISTORY_RANK = "/lichsuhang";

    // Thông tin tài khoản
    public static final String GET_ACCOUNT_INFO = "/api/saigontourist/thongtintaikhoan";
}
