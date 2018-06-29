package saigontourist.pm1.vnpt.com.saigontourist.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.net.URISyntaxException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseActivity;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppUtil;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.PathUtil;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.CommonApiResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.user.UserInfoResponse;
import saigontourist.pm1.vnpt.com.saigontourist.ui.event.UserInfoEvent;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.ChangeEmailFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.ChangePassFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.ChangePhoneFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.fragment.ChangeUserInfoDetailFragment;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.ChangeAvatarPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.user.GetUserInfoPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.ChangeAvatarView;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.user.GetUserInfoView;

public class UserInfoActivity extends BaseActivity implements GetUserInfoView, ChangeAvatarView {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.text7)
    TextView text7;
    @BindView(R.id.loToolbar)
    LinearLayout loToolbar;
    @BindView(R.id.avatarUser)
    ImageView avatarUser;
    @BindView(R.id.loChangeAvatar)
    TextView loChangeAvatar;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.txtUser)
    TextView txtUser;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.txtEmail)
    TextView txtEmail;
    @BindView(R.id.ln_foget_email)
    LinearLayout lnFogetEmail;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.txtPassword)
    TextView txtPassword;
    @BindView(R.id.loForgetPass)
    LinearLayout loForgetPass;
    @BindView(R.id.text5)
    TextView text5;
    @BindView(R.id.txtPhoneNumber)
    TextView txtPhoneNumber;
    @BindView(R.id.ln_forget_numberphone)
    LinearLayout lnForgetNumberphone;
    @BindView(R.id.text6)
    TextView text6;
    @BindView(R.id.loXemHoSoCaNhan)
    LinearLayout loXemHoSoCaNhan;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;


    private UserInfoEvent userInfoEvent;
    private ChangeEmailFragment changeEmailFragment;
    private ChangePhoneFragment changePhoneFragment;
    private ChangePassFragment changePassFragment;
    private ChangeUserInfoDetailFragment changeUserInfoDetailFragment;
    private static final int RESULT_LOAD_IMAGE = 100;
    private static final int REQUEST_READ_EXTERNAL_STORAGE = 0;
    @Inject
    ChangeAvatarPresenter changeAvatarPresenter;

    @Inject
    GetUserInfoPresenter getUserInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);

        addFragment();
        initView();


    }

//    @Override
//    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
//        initView();
//        return super.onCreateView(parent, name, context, attrs);
//    }

    private void addFragment() {
        changeEmailFragment = new ChangeEmailFragment();
        changePhoneFragment = new ChangePhoneFragment();
        changePassFragment = new ChangePassFragment();
        changeUserInfoDetailFragment = new ChangeUserInfoDetailFragment();
    }


    public void initView() {
        changeAvatarPresenter.setView(this);
        changeAvatarPresenter.onViewCreate();
        getUserInfoPresenter.setView(this);
        getUserInfoPresenter.onViewCreate();
        //set fonts
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SFUFuturaBook.TTF");
        loChangeAvatar.setTypeface(face);
        text1.setTypeface(face);
        text2.setTypeface(face);
        text3.setTypeface(face);
        text4.setTypeface(face);
        text5.setTypeface(face);
        text6.setTypeface(face);
        text7.setTypeface(face);

        txtName.setTypeface(face);
        txtUser.setTypeface(face);
        txtEmail.setTypeface(face);
        txtPhoneNumber.setTypeface(face);
        // end
        try {
            userInfoEvent = EventBus.getDefault().getStickyEvent(UserInfoEvent.class);
            if (userInfoEvent != null) {
                txtUser.setText(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getTenDangNhap());
                txtName.setText(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getTenHoiVien());
                txtEmail.setText(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getEmail());
                txtPhoneNumber.setText(userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getSoDienThoai());
                if (userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getAnhDaiDien() != null) {
                    String urlImage = getString(R.string.api_base_url_saigon_tourist) + "/" + userInfoEvent.getUserInfoResponse().getData().getThongtin().get(0).getAnhDaiDien();
                    Glide.with(this)
                            .load(urlImage)
                            .into(avatarUser);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.btnBack, R.id.loChangeAvatar, R.id.ln_foget_email, R.id.loForgetPass,
            R.id.ln_forget_numberphone, R.id.loXemHoSoCaNhan, R.id.avatarUser})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                onBackPressed();
                this.finish();
                break;

            case R.id.ln_foget_email:
                replaceFramgment(changeEmailFragment);
                break;
            case R.id.loForgetPass:
                replaceFramgment(changePassFragment);
                break;
            case R.id.ln_forget_numberphone:
                replaceFramgment(changePhoneFragment);
                break;
            case R.id.loXemHoSoCaNhan:
                replaceFramgment(changeUserInfoDetailFragment);
                break;
            case R.id.avatarUser:
                break;
            case R.id.loChangeAvatar:
                if (userInfoEvent != null) {
                    checkPermissonForImage();
                }

                break;
        }
    }

    private void replaceFramgment(Fragment fragment) {
        FragmentTransaction transList = getSupportFragmentManager()
                .beginTransaction();
        transList.replace(R.id.fragment_container, fragment);
        transList.commit();
    }


    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }


    private void checkPermissonForImage() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                ) {
            requestCameraPermission();

        } else {
            // da duoc cap quyen
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGE);
        }
    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_READ_EXTERNAL_STORAGE);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_READ_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == REQUEST_READ_EXTERNAL_STORAGE) {
            // Check if the only required permission has been granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // duoc cap quyen
                Toast.makeText(getApplicationContext(), R.string.permision_available_camera,
                        Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(i, RESULT_LOAD_IMAGE);
            } else {
                // khong duoc cap quyen
                showDialog();
            }
        }
    }

    private void showDialog() {
        AlertDialog alert;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("App chưa được cấp quyền sử dụng, bạn có muốn bật không ?")
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                        Uri.fromParts("package", getPackageName(), null));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        alert = builder.create();
        alert.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
                Uri uri = data.getData();
                sendUpload(uri);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //gốc
    private void sendUpload(Uri uri) {

//        String filePath = getRealPathFromURIPath(uri, UserInfoActivity.this);
        // get file part
        String filePath = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            File file = new File(uri.getPath());//create path from uri
            final String[] split = file.getPath().split(":");//split the path.
            filePath = split[1];//assign it to a string(your choice).
        }else {
            try {
                filePath= PathUtil.getPath(UserInfoActivity.this,uri);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        // end
        getCameraPhotoOrientation(UserInfoActivity.this, uri, filePath);

        File file1 = new File(filePath);

        File file = AppUtil.saveBitmapToFileSony(UserInfoActivity.this, file1, uri);

        //Lấy dung lượng file ảnh
        long length = file.length();
        length = length / 1024;

//        // change uri to file
//        File file = new File(getPath(uri));
//        // end
        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
//        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        showProgressBar();
        changeAvatarPresenter.changeAvatar(tinyDB.getString(getString(R.string.TOKEN_USER)), fileToUpload);

    }

    public String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        String result;
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);

        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(filePathColumn[0]);
            result = cursor.getString(idx);
            cursor.close();
            return result;
        }
    }

    public int getCameraPhotoOrientation(Context context, Uri imageUri, String imagePath) {
        int rotate = 0;
        try {
            context.getContentResolver().notifyChange(imageUri, null);
            File imageFile = new File(imagePath);

            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }

//            Log.i("RotateImage", "Exif orientation: " + orientation);
//            Log.i("RotateImage", "Rotate value: " + rotate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) return null;
        int column_index =             cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s=cursor.getString(column_index);
        cursor.close();
        return s;
    }

    // view response
    @Override
    public void onChangeAvatarSuccses(CommonApiResult response) {
        hideProgressBar();
        changeAvatarSuccess(response.getErrorDesc());
    }

    @Override
    public void onChangeAvatarFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onChangeAvatarError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    @Override
    public void onGetUserInfoSuccses(UserInfoResponse response) {
        hideProgressBar();
        UserInfoEvent userInfoEvent = new UserInfoEvent(response);
        EventBus.getDefault().postSticky(userInfoEvent);
        tinyDB.putString(getString(R.string.USER_PHONE), response.getData().getThongtin().get(0).getSoDienThoai());
        tinyDB.putString(getString(R.string.USER_NAME), response.getData().getThongtin().get(0).getTenDangNhap());
        Intent refresh = new Intent(UserInfoActivity.this, UserInfoActivity.class);
        this.finish();
        startActivity(refresh);
    }

    @Override
    public void onGetUserInfoFailed(String message) {
        hideProgressBar();
        dilogThongBao(message);
    }

    @Override
    public void onGetUserInfoError(Throwable e) {
        hideProgressBar();
        showToast(e.toString());
    }

    public void changeAvatarSuccess(String message){
        android.support.v7.app.AlertDialog.Builder dialogBuilder = new android.support.v7.app.AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final android.view.View dialogView = inflater.inflate(R.layout.custom_dialog_show_toast, null);
        dialogBuilder.setView(dialogView);

        final android.support.v7.app.AlertDialog b = dialogBuilder.create();
        b.setCanceledOnTouchOutside(false);
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        b.show();
        TextView text_message = (TextView) dialogView.findViewById(R.id.text_message);
        Button btnYes = (Button) dialogView.findViewById(R.id.btnYes);

        text_message.setText(message);

        btnYes.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                b.dismiss();
                getUserInfoPresenter.getUserInfo(tinyDB.getString(getString(R.string.TOKEN_USER)), tinyDB.getString(getString(R.string.DEVICE_ID)));
            }
        });

    }
}
