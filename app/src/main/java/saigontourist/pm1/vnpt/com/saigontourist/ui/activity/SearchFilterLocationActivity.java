package saigontourist.pm1.vnpt.com.saigontourist.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.ObjectKey;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import saigontourist.pm1.vnpt.com.saigontourist.R;
import saigontourist.pm1.vnpt.com.saigontourist.app.BaseActivity;
import saigontourist.pm1.vnpt.com.saigontourist.app.messagebus.MessageEvent;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.AppDef;
import saigontourist.pm1.vnpt.com.saigontourist.app.utils.StringUtils;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.searchmap.DirectionFinder;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.searchmap.DirectionFinderListener;
import saigontourist.pm1.vnpt.com.saigontourist.domain.interactor.searchmap.Route;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.SearchOnMapSaigonResult;
import saigontourist.pm1.vnpt.com.saigontourist.domain.model.specialoffers.SearchOnMapVpointResult;
import saigontourist.pm1.vnpt.com.saigontourist.ui.presenter.specialoffers.SearchDataMapPresenter;
import saigontourist.pm1.vnpt.com.saigontourist.ui.view.specialoffers.SearchDataMapView;


public class SearchFilterLocationActivity extends BaseActivity implements
        OnMapReadyCallback,
        DirectionFinderListener, SearchDataMapView {
    @BindView(R.id.btn_chon_xong)
    Button btnChonXong;


    @BindView(R.id.tv_ban_kinh)
    TextView tv_ban_kinh;
    @BindView(R.id.tv_linh_vuc)
    TextView tv_linh_vuc;
    @BindView(R.id.tv_doanh_nghiep)
    TextView tv_doanh_nghiep;
    @BindView(R.id.lo_hien_thi_bo_loc)
    LinearLayout lo_hien_thi_bo_loc;

    @BindView(R.id.img_my_location)
    ImageView img_my_location;
    @BindView(R.id.img_location)
    ImageView img_location;

    @BindView(R.id.lo_change_padding_my_location)
    LinearLayout lo_change_padding_my_location;
    @BindView(R.id.lo_detail_merchant)
    LinearLayout lo_detail_merchant;

    @BindView(R.id.img_detail_merchant)
    ImageView img_detail_merchant;
    @BindView(R.id.lo_lien_he)
    LinearLayout lo_lien_he;
    @BindView(R.id.lo_dan_duong)
    LinearLayout lo_dan_duong;
    @BindView(R.id.merchant_name)
    TextView merchant_name;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.phone)
    TextView tPhone;

    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.text3)
    TextView text3;
    private boolean mLocationEnableDevice;
    private GoogleMap mMap;
    private View mapView;
    public static final int REQUEST_LOCATION = 0;
    public static final int REQUEST_CALL_PHONE = 1;
    private Location mLastLocation;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;

    private static final String TAG = SearchFilterLocationActivity.class.getSimpleName();
    private String phone;
    private double lat, myLat;
    private double lon, myLon;
    private String addCurrent;
    private String addShop;

    private double finalLast, finalLon;

    private Integer sizeMap = 13;
    private Integer paddingMap = 300;
    @Inject
    SearchDataMapPresenter searchPresenter;
    private String strRadius = "";
    private String strFields = "Tất cả";
    private String strEnterprise = "";
    MessageEvent.ShopOnMapFilter shopMapFilter;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private boolean mLocationPermissionGranted;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private final LatLng mDefaultLocation = new LatLng(21.028511, 105.804817);
    private static final int DEFAULT_ZOOM = 15;

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter_location);
        setTitleToobar("Tìm Kiếm");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        searchPresenter.setView(this);
        searchPresenter.onViewCreate();
        //set fonts
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/SFUFuturaBook.TTF");
        text1.setTypeface(face);
        text2.setTypeface(face);
        text3.setTypeface(face);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapView = mapFragment.getView();
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                lat = mMap.getCameraPosition().target.latitude;
                lon = mMap.getCameraPosition().target.longitude;
//                Log.d("Move", String.valueOf(lat)+ "\n" + String.valueOf(lon));
            }
        });
        mLocationEnableDevice = checkOnGPS();

        // Prompt the user for permission.
        getLocationPermission();

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

//        // Get the current location of the device and set the position of the map.
//        getDeviceLocation();

    }

    @Override
    protected void onPause() {
        super.onPause();
        //stop location updates when Activity is no longer active

    }

    @OnClick({R.id.btn_chon_xong, R.id.img_my_location, R.id.lo_dan_duong, R.id.lo_lien_he})
    public void clickBtn(View view) {
        switch (view.getId()) {
            case R.id.img_my_location:
                LatLng latLng = new LatLng(myLat, myLon);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM);
                mMap.animateCamera(cameraUpdate);
                break;

            case R.id.lo_lien_he:
                if (phone != null && !phone.trim().isEmpty()) {
                    checkPhoneCallPermission();
                } else {
                    Toast.makeText(getApplicationContext(), "Không có số điện thoại",
                            Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.lo_dan_duong:
                //lay vi tri cua shop
                sendRequestDirectionFinder();

                img_location.setVisibility(View.GONE);
                lo_detail_merchant.setVisibility(View.GONE);
                break;

            case R.id.btn_chon_xong:
                if (mLocationPermissionGranted && mLocationEnableDevice) {
                    //get filter

                    tv_ban_kinh.setText(strRadius);
                    tv_linh_vuc.setText(strFields);
                    tv_doanh_nghiep.setText(strEnterprise);
                    getDataSearch(shopMapFilter);
                } else {
                    mLocationEnableDevice = checkOnGPS();
                    getLocationPermission();
                    getDeviceLocation();
                }

                break;

        }
    }


    private void checkPhoneCallPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPhoneCallPermission();

        } else {
            // da duoc cap quyen
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(intent);
        }
    }

    private void requestPhoneCallPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CALL_PHONE)) {
            // can duoc cap quyen
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PHONE);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PHONE);
        }
    }


    private void insertMarkersVpoint(List<SearchOnMapVpointResult.ItemSearchOnMap> list) {
        mMap.clear();

        // cố định vị trí lá cờ
        img_location.setVisibility(View.GONE);
        LatLng latLngCurent = new LatLng(lat, lon);

        //cố định vị trí chọn
        finalLast = lat;
        finalLon = lon;

        mMap.addMarker(new MarkerOptions().position(latLngCurent).title("Vị trí hiện tại")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_tim_kiem_flag)));
        try {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();

            for (int i = 0; i < list.size(); i++) {
                LatLng position = new LatLng(list.get(i).getLatitude(), list.get(i).getLongitude());
                Marker marker;
                Integer fieldID = list.get(i).getFieldId();
                switch (fieldID) {
                    case 1:
                        marker = mMap.addMarker(new MarkerOptions().position(position)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_am_thuc_location)));
                        marker.setTag(list.get(i));
                        break;
                    case 2:
                        marker = mMap.addMarker(new MarkerOptions().position(position)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mua_sam_location)));
                        marker.setTag(list.get(i));
                        break;
                    case 3:
                        marker = mMap.addMarker(new MarkerOptions().position(position)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_suc_khoe_location)));
                        marker.setTag(list.get(i));
                        break;
                    case 4:
                        marker = mMap.addMarker(new MarkerOptions().position(position)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_thoitrang_location)));
                        marker.setTag(list.get(i));
                        break;
                    case 5:
                        marker = mMap.addMarker(new MarkerOptions().position(position)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_taichinh_location)));
                        marker.setTag(list.get(i));
                        break;
                    case 6:
                        marker = mMap.addMarker(new MarkerOptions().position(position)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_vien_thong_location)));
                        marker.setTag(list.get(i));
                        break;
                    case 7:
                        marker = mMap.addMarker(new MarkerOptions().position(position)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_van_tai_location)));
                        marker.setTag(list.get(i));
                        break;
                    case 8:
                        marker = mMap.addMarker(new MarkerOptions().position(position)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_du_lich_location)));
                        marker.setTag(list.get(i));
                        break;
                    case 9:
                        marker = mMap.addMarker(new MarkerOptions().position(position)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_thuongmai_dientu_location)));
                        marker.setTag(list.get(i));
                        break;
                    case 10:
                        marker = mMap.addMarker(new MarkerOptions().position(position)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_khac_location)));
                        marker.setTag(list.get(i));
                        break;
                }
                builder.include(latLngCurent);
                builder.include(position);
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        SearchOnMapVpointResult.ItemSearchOnMap obj = (SearchOnMapVpointResult.ItemSearchOnMap) marker.getTag();
                        if (obj == null) {
                            return false;
                        }
                        try {
                            restartView();
//                        showAlertDialog(position, obj);
                            // Hiện thông tin của marker được chọn
                            lo_change_padding_my_location.setPadding(0, 0, 0, 0);
                            lo_detail_merchant.setVisibility(View.VISIBLE);

                            // Ẩn lấy vị trí hiện tại
                            // Ẩn bộ lọc
                            lo_hien_thi_bo_loc.setVisibility(View.GONE);

                            //Hiện ảnh
                            String urlImage = getString(R.string.api_base_url_vpoint) + StringUtils.GET_IMAGE_NEWS_URL + obj.getAvatarId();
                            Glide.with(SearchFilterLocationActivity.this)
                                    .load(urlImage)
                                    .into(img_detail_merchant);
                            //Lấy sđt
                            phone = obj.getPhone();
                            //Hiện tên
                            if (obj.getMerchant() != null) {
                                merchant_name.setText(obj.getMerchant());
                            }
                            if (obj.getName() != null) {
                                name.setText(obj.getName());
                            }
                            if (obj.getAddress() != null) {
                                address.setText(obj.getAddress());
                            }
                            if (obj.getPhone() != null) {
                                tPhone.setText(obj.getPhone());
                            }
                            //Lấy lat- lon -> chỉ đường
//                        fetchAddressCurrent(lat, lon);
                            //Vi tri hien tai
                            addCurrent = finalLast + "," + finalLon;

//                        fetchAddressShop(obj.getLatitude(), obj.getLongitude());
                            //vi tri cua shop
                            if (obj.getLatitude() != 0 || obj.getLongitude() != 0) {
                                addShop = obj.getLatitude() + "," + obj.getLongitude();
                            } else {
                                addShop = null;
                            }

                        } catch (Exception e) {
                        }
                        return false;
                    }
                });

                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        lo_change_padding_my_location.setPadding(0, 0, 0, 250);
                        lo_detail_merchant.setVisibility(View.GONE);
                        lo_hien_thi_bo_loc.setVisibility(View.VISIBLE);
                    }
                });

                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                int width = displayMetrics.widthPixels;
                int height = displayMetrics.heightPixels;
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(builder.build(), width - paddingMap, height - paddingMap, sizeMap);
                mMap.animateCamera(cu);
            }
        } catch (Exception e) {
        }
    }

    private void insertMarkersSaiGon(List<SearchOnMapSaigonResult.Data> list) {
        mMap.clear();

        // cố định vị trí lá cờ
        img_location.setVisibility(View.GONE);
        LatLng latLngCurent = new LatLng(lat, lon);

        //cố định vị trí chọn
        finalLast = lat;
        finalLon = lon;

        mMap.addMarker(new MarkerOptions().position(latLngCurent).title("Vị trí hiện tại")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_tim_kiem_flag)));
        try {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();

            for (int i = 0; i < list.size(); i++) {
                LatLng position = new LatLng(Double.valueOf(list.get(i).getGPS().split(",")[0]),
                        Double.valueOf(list.get(i).getGPS().split(",")[1]));
                Marker marker;
                marker = mMap.addMarker(new MarkerOptions().position(position)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_am_thuc_location)));
                marker.setTag(list.get(i));
                builder.include(latLngCurent);
                builder.include(position);
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        SearchOnMapSaigonResult.Data obj = (SearchOnMapSaigonResult.Data) marker.getTag();
                        if (obj == null) {
                            return false;
                        }
                        try {
                            restartView();
//                        showAlertDialog(position, obj);
                            // Hiện thông tin của marker được chọn
                            lo_change_padding_my_location.setPadding(0, 0, 0, 0);
                            lo_detail_merchant.setVisibility(View.VISIBLE);

                            // Ẩn lấy vị trí hiện tại
                            // Ẩn bộ lọc
                            lo_hien_thi_bo_loc.setVisibility(View.GONE);

                            //Hiện ảnh
                            String urlImage = "";
                            if (!TextUtils.isEmpty(obj.getAnhDaiDien()) && obj.getAnhDaiDien().startsWith("/")) {
                                urlImage = getString(R.string.api_base_url_saigon_image) + obj.getAnhDaiDien();
                            } else if (!TextUtils.isEmpty(obj.getAnhDaiDien())) {
                                urlImage = getString(R.string.api_base_url_saigon_image) + "/" + obj.getAnhDaiDien();
                            }
                            Glide.with(SearchFilterLocationActivity.this)
                                    .load(urlImage)
                                    .apply(new RequestOptions()
                                            .override(80, 80)
                                            .fitCenter()
                                            .dontAnimate()
                                            .dontTransform()
                                            .signature(new ObjectKey(System.currentTimeMillis() / (24 * 60 * 60 * 1000))))
                                    .into(img_detail_merchant);
                            //Lấy sđt
                            phone = obj.getDienThoai();
                            //Hiện tên
                            if (obj.getTenDonVi() != null) {
                                merchant_name.setText(obj.getTenDonVi());
                            }
                            if (obj.getTenDonVi() != null) {
                                name.setText(obj.getTenDonVi());
                            }
                            if (obj.getDiaChi() != null) {
                                address.setText(obj.getDiaChi());
                            }
                            if (obj.getDienThoai() != null) {
                                tPhone.setText(obj.getDienThoai());
                            }
                            //Lấy lat- lon -> chỉ đường
//                        fetchAddressCurrent(lat, lon);
                            //Vi tri hien tai
                            addCurrent = finalLast + "," + finalLon;

//                        fetchAddressShop(obj.getLatitude(), obj.getLongitude());
                            //vi tri cua shop
                            if (obj.getGPS() != null) {
                                addShop = obj.getGPS();
                            } else {
                                addShop = null;
                            }

                        } catch (Exception e) {
                        }

                        return false;
                    }
                });

                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        lo_change_padding_my_location.setPadding(0, 0, 0, 250);
                        lo_detail_merchant.setVisibility(View.GONE);
                        lo_hien_thi_bo_loc.setVisibility(View.VISIBLE);
                    }
                });

                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                int width = displayMetrics.widthPixels;
                int height = displayMetrics.heightPixels;
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(builder.build(), width - paddingMap, height - paddingMap, sizeMap);
                mMap.animateCamera(cu);
            }
        } catch (Exception e) {
        }
    }

    private void restartView() {
        //Hiện tên
        merchant_name.setText("");
        name.setText("");
        address.setText("");
        tPhone.setText("");
    }

    // chi duong
    private void sendRequestDirectionFinder() {
        if (addShop.isEmpty() || addShop == null) {
            Toast.makeText(this, "Không lấy được vị trí shop", Toast.LENGTH_SHORT).show();
            return;
        }
        if (addCurrent.isEmpty()) {
//            Toast.makeText(this, "Please enter destination address!", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            new DirectionFinder(this, addCurrent, addShop).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this, "",
                "Vui lòng chờ trong giây lát !", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline : polylinePaths) {
                polyline.remove();
            }
        }
    }


    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();
        mMap.clear();

        for (Route route : routes) {

            LatLngBounds.Builder b = new LatLngBounds.Builder();
            b.include(route.startLocation);
            b.include(route.endLocation);
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int width = displayMetrics.widthPixels;
            int height = displayMetrics.heightPixels;
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(b.build(), width - paddingMap, height - paddingMap, sizeMap);
            mMap.animateCamera(cu);


            // vị trí shop
            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_tim_kiem_flag))
                    .title(route.startAddress)
                    .position(route.startLocation)));

            //vị trí hiện tại
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    .title(route.endAddress)
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.ShopOnMapFilter event) {
        /* Do something */
        if (event != null) {
            strEnterprise = event.getStrEnterpriseId();
            strFields = event.getStrField();
            strRadius = event.getStrRadius();
            shopMapFilter = event;
        }

    }

    private void getDataSearch(MessageEvent.ShopOnMapFilter event) {
        showProgressBar();
        if (event.getTypeSearch() == 1) {
            searchPresenter.getDataSearchVpointMap(event.getSearchingText(), event.getEnterpriseId(), event.getField()
                    , event.getRadius(), lat, lon);
        } else if (event.getTypeSearch() == 0) {
            getTokenDevIfNeed();
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Glide.with(getApplicationContext()).pauseRequests();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        searchPresenter.onViewDestroy();
    }

    @Override
    public void onGetDataSearchVpointSuccses(List<SearchOnMapVpointResult.ItemSearchOnMap> listData) {
        hideProgressBar();
        if (listData != null && listData.size() > 0) {
            btnChonXong.setVisibility(View.GONE);
            img_my_location.setVisibility(View.GONE);
            if (shopMapFilter.getKindOfSearch() == 1) {
                lo_hien_thi_bo_loc.setVisibility(View.GONE);
            } else {
                lo_hien_thi_bo_loc.setVisibility(View.VISIBLE);
            }

            insertMarkersVpoint(listData);
        } else {
            Toast.makeText(getApplicationContext(), "Không tìm thấy cửa hàng nào.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetDataSearchVpointFailed(String message) {
        hideProgressBar();
        showToast(message);
    }

    @Override
    public void onGetDataSearchSaiGonSuccses(List<SearchOnMapSaigonResult.Data> listData) {
        hideProgressBar();
        if (listData != null && listData.size() > 0) {
            btnChonXong.setVisibility(View.GONE);
            img_my_location.setVisibility(View.GONE);
            if (shopMapFilter.getKindOfSearch() == 1) {
                lo_hien_thi_bo_loc.setVisibility(View.GONE);
            } else {
                lo_hien_thi_bo_loc.setVisibility(View.VISIBLE);
            }
            insertMarkersSaiGon(listData);
        } else {
            Toast.makeText(getApplicationContext(), "Không tìm thấy cửa hàng nào.", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onGetDataSearchSaiGonFailed(String message) {
        hideProgressBar();
        showToast(message);
    }

    @Override
    public void onError(Throwable e) {
        hideProgressBar();

    }

    @Override
    public void onLoadTokenDevUser(String tokenDev) {
        super.onLoadTokenDevUser(tokenDev);
        searchPresenter.getDataSearchSaiGonMap(tokenDev, StringUtils.MaUngDung, shopMapFilter.getField(),
                shopMapFilter.getEnterpriseId(),shopMapFilter.getChuongTrinhUuDaiId(), shopMapFilter.getSearchingText(), shopMapFilter.getRadius(), lat, lon);
    }

    private boolean checkOnGPS() {
        /*check gps*/
        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = service
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!enabled) {
            buildAlertMessageNoGps();

        }
        return enabled;
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Định vị GPS của bạn đã tắt, bạn muốn bật nó để tiếp tục sử dụng chức năng này?")
                .setCancelable(false)
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), AppDef.REQUEST_LOCATION_SOURCE_SETTING);
                    }
                })
                .setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AppDef.REQUEST_LOCATION_SOURCE_SETTING && resultCode == 0) {
            mLocationEnableDevice = checkOnGPS();
            updateLocationUI();
        }
    }

    private void goneDataForNearShop() {
        btnChonXong.setVisibility(View.GONE);
        img_my_location.setVisibility(View.GONE);
        lo_hien_thi_bo_loc.setVisibility(View.GONE);
    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted && mLocationEnableDevice) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastLocation = task.getResult();
                            if (mLastLocation != null) {
                                lat = mLastLocation.getLatitude();
                                lon = mLastLocation.getLongitude();
                                myLat = mLastLocation.getLatitude();
                                myLon = mLastLocation.getLongitude();
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(mLastLocation.getLatitude(),
                                                mLastLocation.getLongitude()), DEFAULT_ZOOM));
                                //Hiển thị cửa hàng gần bạn
                                if (shopMapFilter.getKindOfSearch() == 1) {
                                    goneDataForNearShop();
                                    getDataSearch(shopMapFilter);
                                }
                            }

                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
//                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                getDeviceLocation();
            } else {
//                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }
}
