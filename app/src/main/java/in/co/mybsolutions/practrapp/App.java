package in.co.mybsolutions.practrapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.multidex.MultiDex;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Android on 2/9/2018.
 */

public class App extends Application {

    static App mInstance;
    public static boolean blnFullscreenActvitity = false;
    public static String DB_NAME = "luxxary.db";
    static Context mContext;
    public static String DB_PATH = Environment.getExternalStorageDirectory().toString() + "/" + App.APP_FOLDERNAME;
    public static String APP_SD_CARD_PATH = Environment.getExternalStorageDirectory().toString();
    public static String APP_FOLDERNAME = ".Luxxary";
    public static String APP_SELL_FOLDERNAME = ".Sell";
    public static String PREF_NAME = "luxxary";
//    public static SharePrefrences sharePrefrences;
    public static String _RS = "₹";
    //
    public static String TRUE = "true";
    public static String FALSE = "false";
    public static String APP_MODE = "0"; // 0 - Developement & 1 - Publish
    public static String APP_TYPE = "android"; // android/ios
    public static String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static String FB_USERTYPE = "facebook", GOOGLE_USERTYPE = "google", NORMAL_USERTYPE = "normal";


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            mInstance = this;
//            Fresco.initialize(this);
            MultiDex.install(this);
            mContext = getApplicationContext();
//            sharePrefrences = new SharePrefrences(App.this);
            //
//            createAppFolder();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }


    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }


    public static void GenerateKeyHash() {
        try {
            PackageInfo info = mContext.getPackageManager().getPackageInfo(mContext.getApplicationContext().getPackageName(),
                    PackageManager.GET_SIGNATURES); //GypUQe9I2FJr2sVzdm1ExpuWc4U= android pc -2 key
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                App.showLog("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<String> getAllImageFromFolderPath(String path, String folder_name) {
        ArrayList<String> f = new ArrayList<>();
        File[] listFile;
        File file = new File(path, folder_name);

        if (file.isDirectory()) {
            listFile = file.listFiles();
            for (int i = 0; i < listFile.length; i++) {
                f.add(listFile[i].getAbsolutePath());
            }
        }
        return f;
    }


    public static void removeAllImagesFromFolder(String path) {
        File dir = new File(path);
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                new File(dir, children[i]).delete();
            }
        }
    }


    public static String countAdminRateOverListingPricing(String listing_price) {
        String final_res = "";
        double admin_rate = 0.05; // 5%
        try {
            if (listing_price != null && listing_price.length() > 0) {
                String temp_str = listing_price.replace("$", "");
                double temp1 = Double.parseDouble(temp_str.toString());
                App.showLogTAG("==temp1==" + temp1);
                double res1 = temp1 * admin_rate;
                App.showLogTAG("==res1==" + res1);
                double price = temp1 - res1;
                App.showLogTAG("==price==" + price);
//                String s = String.format("%.2f", price);
                final_res = String.format("%.2f", price);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return final_res;
    }


    public static ShapeDrawable drawCircle(Context context, int width, int height, int color) {
        ShapeDrawable oval = null;
        try {
            oval = new ShapeDrawable(new OvalShape());
            oval.setIntrinsicHeight(height);
            oval.setIntrinsicWidth(width);
            oval.getPaint().setColor(color);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return oval;
    }


    public static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    public static String removeLastThreeChar(String str) {
        return str.substring(0, str.length() - 3);
    }


//    public static Typeface getRajdhaniMed() {
//        tfRajdhaniMed = Typeface.createFromAsset(mContext.getAssets(), "fonts/rajdhani_medium.ttf");
//        return tfRajdhaniMed;
//    }
//
//
//    public static Typeface getRajdhaniBold() {
//        tfRajdhaniBold = Typeface.createFromAsset(mContext.getAssets(), "fonts/rajdhani_bold.ttf");
//        return tfRajdhaniBold;
//    }


    private void createAppFolder() {
        try {
            String sdCardPath = Environment.getExternalStorageDirectory().toString();
            File file2 = new File(sdCardPath + "/" + App.APP_FOLDERNAME + "");
            if (!file2.exists()) {
                if (!file2.mkdirs()) {
                    System.out.println("==Create Directory " + App.APP_FOLDERNAME + "====");
                } else {
                    System.out.println("==No--1Create Directory " + App.APP_FOLDERNAME + "====");
                }
            } else {
                System.out.println("== already created---No--2Create Directory " + App.APP_FOLDERNAME + "====");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    public static void showToastLong(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showToastShort(Context context, String strMessage) {
        Toast.makeText(context, strMessage, Toast.LENGTH_SHORT).show();
    }


    // //
    public static void showLog(String strMessage) {
        Log.v("==App==", "--strMessage--" + strMessage);
    }

    public static void showLogTAG(String strMessage) {
        Log.e("==TAG==", "--screen--" + strMessage);
    }

    public static void showLogResponce(String strTag, String strMessage) {
        Log.w("==RESPONSE==" + strTag, "--strResponse--" + strMessage);
    }

    public static void showLogApi(String strMessage) {
        Log.v("==APP==", "--strMessage--" + strMessage);
    }

    public static void showLog(String strTag, String strMessage) {
        Log.v("==APP==strTag==" + strTag, "--strMessage--" + strMessage);
    }

    // //
    public static void showSnackBar(View view, String strMessage) {
        try {
            Snackbar snackbar = Snackbar.make(view, strMessage, Snackbar.LENGTH_SHORT);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.BLACK);
            TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            //textView.setTextColor(Color.WHITE);
//            textView.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
            textView.setTextColor(ContextCompat.getColor(mContext, R.color.clrWhite));
            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void showSnackBarWithAction(View view, String strMessage, String strActionName) {
        try {
            Snackbar snackbar = Snackbar
                    .make(view, strMessage, Snackbar.LENGTH_LONG)
                    .setAction(strActionName, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // snackbar.dismiss();
                        }
                    });
            ;
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.BLACK);
            TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            //textView.setTextColor(Color.WHITE);
            snackbar.setActionTextColor(Color.RED);
//            textView.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
            textView.setTextColor(ContextCompat.getColor(mContext, R.color.clrWhite));
            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void showSnackBarLong(View view, String strMessage) {
        Snackbar.make(view, strMessage, Snackbar.LENGTH_LONG).show();
    }


    public static void setTaskBarColored(Activity context, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = context.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // window.setStatusBarColor(Color.BLUE);
            window.setStatusBarColor(ContextCompat.getColor(context, color));
        }
    }

    public static boolean isInternetAvail(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i < info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }


    /*
    * Will get only digit from alpha numeric values i.e. abc456 - result=456
    * */
    public static String stripNonDigits(
            final CharSequence input /* inspired by seh's comment */) {
        final StringBuilder sb = new StringBuilder(
                input.length() /* also inspired by seh's comment */);
        for (int i = 0; i < input.length(); i++) {
            final char c = input.charAt(i);
            if (c > 47 && c < 58) {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    public static String getOnlyDigits(String s) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(s);
        String number = matcher.replaceAll("");
        return number;
    }

    public static String getOnlyStrings(String s) {
        Pattern pattern = Pattern.compile("[^a-z A-Z]");
        Matcher matcher = pattern.matcher(s);
        String number = matcher.replaceAll("");
        return number;
    }

    public static String getOnlyAlfaNumeric(String s) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(s);
        String number = matcher.replaceAll(" ");
        return number;
    }


    public static String getCurrentDate() {
        String current_date = "";
        try {
            Date c = Calendar.getInstance().getTime();
            System.out.println("Current date & time => " + c);

            SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
            current_date = df.format(c);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return current_date;
    }


    public static String getCurrentDateTimeWhileSendMessage() {
        String current_date = "";
        try {
            Date c = Calendar.getInstance().getTime();
            System.out.println("Current date & time => " + c);

//            SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            current_date = df.format(c);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return current_date;
    }


    public static String getUserJoinedDate(String convert_date_string) {
        String final_date = "";
        if (convert_date_string != null) {

            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat outputFormat = new SimpleDateFormat("MMM yyyy");
                String inputDateStr = convert_date_string;
                Date date = null;
                date = inputFormat.parse(inputDateStr);
                //String outputDateStr = outputFormat.format(date);
                final_date = outputFormat.format(date);
                final_date.toLowerCase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return final_date;
    }


    public static String compareTwoDates(String str_date1, String str_date2) {
        String current_date = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
            Date date1 = sdf.parse(str_date1);
            Date date2 = sdf.parse(str_date2);

            System.out.println("date1 : " + sdf.format(date1));
            System.out.println("date2 : " + sdf.format(date2));

            if (date1.compareTo(date2) > 0) {
                current_date = "Date1>Date2";
                System.out.println("Date1 is Greater than Date2 - Date1 is after Date2");
            } else if (date1.compareTo(date2) < 0) {
                current_date = "Date1<Date2";
                System.out.println("Date1 is Less than Date2 - Date1 is before Date2");
            } else if (date1.compareTo(date2) == 0) {
                current_date = "Date1=Date2";
                System.out.println("Date1 is equal to Date2");
            } else {
                current_date = "";
                System.out.println("How to get here?");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return current_date;
    }


    public void hideKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideSoftKeyboardMy(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }


    public static void myStartActivity(Activity activity, Intent intent) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public static void myFinishActivityRefresh(Activity activity, Intent intent) {
        activity.startActivity(intent);
        activity.finish();
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    public static void myFinishActivity(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    public static void setStatusBarTranslucent(boolean makeTranslucent, Activity activity) {
        if (makeTranslucent) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }


    public static GradientDrawable getRoundDrawable(int shapeColor) {
        GradientDrawable drawable = null;
        try {
            drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.OVAL);
            drawable.setColor(shapeColor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drawable;
    }


    public static String getddMMMyyyy(String convert_date_string) {
        String final_date = "";
        if (convert_date_string != null) {

            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy h:mm a");
                String inputDateStr = convert_date_string;
                Date date = null;
                date = inputFormat.parse(inputDateStr);
                //String outputDateStr = outputFormat.format(date);
                final_date = outputFormat.format(date);
                final_date.toLowerCase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return final_date;
    }


    public static String getFragEventSubmissionDeadlineDate(String convert_date_string) {
        String final_date = "";
        if (convert_date_string != null) {

            try {
                // Tue May 15 18:35:00 GMT+05:30 2018
                SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss Z yyyy",  Locale.US);
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                String inputDateStr = convert_date_string;
                Date date = null;
                date = inputFormat.parse(inputDateStr);
                //String outputDateStr = outputFormat.format(date);
                final_date = outputFormat.format(date);
                final_date.toLowerCase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return final_date;
    }


    public static String getddMMMMyyyyWithoutTime(String convert_date_string) {
        String final_date = "";
        if (convert_date_string != null) {

            try {
                // SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
                String inputDateStr = convert_date_string;
                Date date = null;
                date = inputFormat.parse(inputDateStr);
                //String outputDateStr = outputFormat.format(date);
                final_date = outputFormat.format(date);
                final_date.toLowerCase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return final_date;
    }

//    public static RequestBody get_RequestBody(String str) {
//        RequestBody r_strUserName = App.createPartFromString(str);
//        return r_strUserName;
//    }
//
//    public static RequestBody createPartFromString(String partString) {
//        return RequestBody.create(MultipartBody.FORM, partString);
//    }
}
