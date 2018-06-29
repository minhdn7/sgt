package saigontourist.pm1.vnpt.com.saigontourist.app.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.internal.Utils;
import saigontourist.pm1.vnpt.com.saigontourist.R;

import static com.bumptech.glide.load.resource.bitmap.TransformationUtils.rotateImage;

/**
 * Created by MinhDN on 20/4/2018.
 */
public class AppUtil {

    public static boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }
        return isInBackground;
    }

    public static void showDialog(final Context context, final Class<?> activity) {
        AlertDialog alert;

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(StringUtils.NO_INTERNET)
                .setCancelable(false)
                .setPositiveButton("Setting",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            }
                        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        context.startActivity(new Intent(context, activity));
                    }
                });

        alert = builder.create();
        alert.show();
    }

    public static boolean isEmailValid(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            if (target.length() < 6 || target.length() > 13) {
                return false;
            } else {
                return android.util.Patterns.PHONE.matcher(target).matches();
            }
        }
    }

    public static void hideKeyboard(Context context){
        ((InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE))
                .toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }

    public static void showKeybroad(Context context){
        ((InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE))
                .toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }


    public static boolean checkNull(String string){
        return string != null;
    }

    public static boolean checkNull(Integer integer){
        return integer != null;
    }

    public static boolean checkNull(Object object){
        return object != null;
    }

    @SuppressWarnings("ThrowablePrintedToSystemOut")
    public static String convertDateToString(Date date, String format) {
        String dateStr = null;
        DateFormat df = new SimpleDateFormat(format);
        try {
            dateStr = df.format(date);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dateStr;
    }

    public static Date convertStringToDate(String dateStr, String format) {
        Date date = null;
        DateFormat df = new SimpleDateFormat(format);
        try {
            date = df.parse(dateStr);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return date;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            }
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }




    public static void showDialogToast(final Context context, String strText){
        LayoutInflater factory = LayoutInflater.from(context);
        View view = factory.inflate(R.layout.custom_dialog_show_toast, null);
        final AlertDialog yesDialog = new AlertDialog.Builder(context).create();
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 64);
        yesDialog.getWindow().setBackgroundDrawable(inset);
        yesDialog.setView(view);
        TextView text = view.findViewById(R.id.text_message);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/SFUFuturaBook.TTF");
        text.setTypeface(face);
        text.setText(strText);
        view.findViewById(R.id.btnYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yesDialog.dismiss();
            }
        });

        yesDialog.show();
    }
    public static void showDialogToast(final Activity context, String strText){
        LayoutInflater factory = LayoutInflater.from(context);
        View view = factory.inflate(R.layout.custom_dialog_show_toast, null);
        final AlertDialog yesDialog = new AlertDialog.Builder(context).create();
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 64);
        yesDialog.getWindow().setBackgroundDrawable(inset);
        yesDialog.setView(view);
        TextView text = view.findViewById(R.id.text_message);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/SFUFuturaBook.TTF");
        text.setTypeface(face);
        text.setText(strText);
        view.findViewById(R.id.btnYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yesDialog.dismiss();
            }
        });

        yesDialog.show();
    }

    public static void showDialogToastEvent(final Activity context, String strText, final Class<?> nextActivity){
        LayoutInflater factory = LayoutInflater.from(context);
        View view = factory.inflate(R.layout.custom_dialog_show_toast, null);
        final AlertDialog yesDialog = new AlertDialog.Builder(context).create();
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 64);
        yesDialog.getWindow().setBackgroundDrawable(inset);
        yesDialog.setView(view);
        TextView text = view.findViewById(R.id.text_message);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/SFUFuturaBook.TTF");
        text.setTypeface(face);
        text.setText(strText);
        view.findViewById(R.id.btnYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yesDialog.dismiss();
                context.startActivity(new Intent(context, nextActivity) );
                context.finish();
            }
        });

        yesDialog.show();
    }
    //giai ma chuoi string sang List
    public static List<LatLng> decodePolyLine(final String poly) {
        int len = poly.length();
        int index = 0;
        List<LatLng> decoded = new ArrayList<>();
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int b;
            int shift = 0;
            int result = 0;
            do {
                b = poly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = poly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            decoded.add(new LatLng(
                    lat / 100000d, lng / 100000d
            ));
        }
        return decoded;
    }

    /**
     * URL-encodes everything between "/"-characters.
     * Encodes spaces as '%20' instead of '+'.
     */
    public static String encodeUri( String uri )
    {
        String newUri = "";
        StringTokenizer st = new StringTokenizer( uri, "/ ", true );
        while ( st.hasMoreTokens())
        {
            String tok = st.nextToken();
            if ( tok.equals( "/" ))
                newUri += "/";
            else if ( tok.equals( " " ))
                newUri += "%20";
            else
            {
                newUri += URLEncoder.encode( tok );
                // For Java 1.4 you'll want to use this instead:
                // try { newUri += URLEncoder.encode( tok, "UTF-8" ); } catch ( java.io.UnsupportedEncodingException uee ) {}
            }
        }
        return newUri;
    }

    public static File saveBitmapToFileSony(Activity context, File file, Uri selectedImage){
        try {
            // BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            FileInputStream inputStream = new FileInputStream(file);
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();

            // The new size we want to scale to
            final int REQUIRED_SIZE=75;

            // Find the correct scale value. It should be the power of 2.

            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

//            BitmapFactory.Options o2 = new BitmapFactory.Options();
//            o2.inSampleSize = scale;
            o.inSampleSize = calculateInSampleSize(o, 300, 300);
            o.inJustDecodeBounds = false;

            Bitmap selectedBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

            try {
                selectedBitmap = rotateImageIfRequiredSony(context, selectedBitmap, Uri.fromFile(file.getAbsoluteFile()));

            } catch (Exception e) {
                e.printStackTrace();
            }

            inputStream.close();
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 50 , outputStream);

            return file;
        } catch (Exception e) {
            return null;
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee a final image
            // with both dimensions larger than or equal to the requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

            // This offers some additional logic in case the image has a strange
            // aspect ratio. For example, a panorama may have a much larger
            // width than height. In these cases the total pixels might still
            // end up being too large to fit comfortably in memory, so we should
            // be more aggressive with sample down the image (=larger inSampleSize).

            final float totalPixels = width * height;

            // Anything more than 2x the requested pixels we'll sample down further
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;

            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
        }
        return inSampleSize;
    }

    public static Bitmap rotateImageIfRequired(Activity context, Bitmap img, Uri selectedImage) throws IOException {
        InputStream input = context.getContentResolver().openInputStream(selectedImage);
        ExifInterface ei;
        if (Build.VERSION.SDK_INT > 23)
            ei = new ExifInterface(input);
        else
            ei = new ExifInterface(selectedImage.getPath());

        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }

    public static Bitmap rotateImageIfRequiredSony(Activity context, Bitmap img, Uri selectedImage) throws IOException {
        InputStream input = context.getContentResolver().openInputStream(selectedImage);
        ExifInterface ei;
        if (Build.VERSION.SDK_INT > 23)
            ei = new ExifInterface(input);
        else
            ei = new ExifInterface(selectedImage.getPath());

        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }
}
