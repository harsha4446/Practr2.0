package in.co.mybsolutions.practrapp;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class BaseActivity extends FragmentActivity {

    String TAG = "==BaseActivity==";

    RecyclerView baseRecyclerView;
//    BaseMenuAdapter baseMenuAdapter;
    //
    public static TextView tvBaseUserFullName, tvBaseUserName, tvBaseUserEmail;
    public static ImageView ivBaseSongBgBlurImage;
//    public static CircularImageView ivBaseUserImage;

    protected LinearLayout llContainerMain, llContainerSub;
    public static Toolbar appToolbar;
    public static RelativeLayout rlMenu, rlBack;

    public static RelativeLayout rlBaseMainHeader;

    protected TextView tvTitle;
    protected TextView tvSubTitle;

    RelativeLayout left_drawer;
    protected DrawerLayout drawer;

    protected RelativeLayout rlSideLogo, rlNext, rlMenu4, rlMenu3, rlMenu2, rlMenu1;
    protected ImageView ivSideLogo, ivMenu, ivMenu4, ivMenu3, ivMenu2, ivMenu1;
    protected TextView tvNext;
    //
    App app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            if (App.blnFullscreenActvitity == true) {
                App.blnFullscreenActvitity = false;
            }
            super.onCreate(savedInstanceState);
            setContentView(R.layout.base_activity);

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            app = (App) getApplicationContext();
            //
            baseInitialization();
            setBaseFonts();
            setBaseClickEvents();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void baseInitialization() {
        try {
            appToolbar = (Toolbar) findViewById(R.id.appToolbar);
            rlBaseMainHeader = (RelativeLayout) findViewById(R.id.rlBaseMainHeader);
            baseRecyclerView = (RecyclerView) findViewById(R.id.baseRecyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
            baseRecyclerView.setLayoutManager(linearLayoutManager);
            baseRecyclerView.setHasFixedSize(true);
            llContainerMain = (LinearLayout) findViewById(R.id.llContainerMain);
            llContainerSub = (LinearLayout) findViewById(R.id.llContainerSub);
            rlMenu = (RelativeLayout) findViewById(R.id.rlMenu);

            rlBack = (RelativeLayout) findViewById(R.id.rlBack);
            tvTitle = (TextView) findViewById(R.id.tvTitle);
            tvSubTitle = (TextView) findViewById(R.id.tvSubTitle);

            // for the handel right side menu click
            rlSideLogo = (RelativeLayout) findViewById(R.id.rlSideLogo);
            rlNext = (RelativeLayout) findViewById(R.id.rlNext);
            rlMenu4 = (RelativeLayout) findViewById(R.id.rlMenu4);
            rlMenu3 = (RelativeLayout) findViewById(R.id.rlMenu3);
            rlMenu2 = (RelativeLayout) findViewById(R.id.rlMenu2);
            rlMenu1 = (RelativeLayout) findViewById(R.id.rlMenu1);

            // for the right side menu images
            ivSideLogo = (ImageView) findViewById(R.id.ivSideLogo);
            ivMenu = (ImageView) findViewById(R.id.ivMenu);
            ivMenu4 = (ImageView) findViewById(R.id.ivMenu4);
            ivMenu3 = (ImageView) findViewById(R.id.ivMenu3);
            ivMenu2 = (ImageView) findViewById(R.id.ivMenu2);
            ivMenu1 = (ImageView) findViewById(R.id.ivMenu1);

            tvNext = (TextView) findViewById(R.id.tvNext);


            tvBaseUserFullName = (TextView) findViewById(R.id.tvBaseUserFullName);
            tvBaseUserName = (TextView) findViewById(R.id.tvBaseUserName);
            tvBaseUserEmail = (TextView) findViewById(R.id.tvBaseUserEmail);
            ivBaseSongBgBlurImage = (ImageView) findViewById(R.id.ivBaseSongBgBlurImage);
//            ivBaseUserImage = (CircularImageView) findViewById(R.id.ivBaseUserImage);
            left_drawer = (RelativeLayout) findViewById(R.id.left_drawer);
            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

            initDrawer();
            setDrawerListAdapter();
            //
            tvTitle.setText(R.string.app_name);

            //
            rlBaseMainHeader.setVisibility(View.GONE);
            appToolbar.setVisibility(View.GONE);
            setEnableDrawer(false);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }


    private void setBaseClickEvents() {
        try {
            //
            rlMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {

                    if (drawer.isDrawerOpen(left_drawer)) {
                        drawer.closeDrawers();
                    } else {
                        drawer.openDrawer(left_drawer);
                    }
                }
            });

            //
            rlBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    onBackPressed();
                }
            });

            //
            llContainerSub.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // TODO Auto-generated method stub
                    System.out.println("===on touch=2=");
                    if (v instanceof EditText) {
                        System.out.println("=touch no hide edittext==2=");
                    } else {
                        System.out.println("===on touch hide=2=");
                        app.hideKeyBoard(v);
                    }
                    return true;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Get Device token - Function
//    public void getDeviceToken() {
//        try {
//            App.showLog(TAG + "=getDeviceToken==");
//            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//            App.showLogTAG(TAG + "==InstanceID token: ==" + FirebaseInstanceId.getInstance().getToken());
//            if (refreshedToken != null && refreshedToken.length() > 5) {
//                App.sharePrefrences.setPref(Const.strDeviceId, refreshedToken);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    private void setBaseFonts() {
        try {
//            tvTitle.setTypeface(App.getRajdhaniBold());
//            tvNext.setTypeface(App.getRajdhaniBold());
//            //
//            tvBaseUserFullName.setTypeface(App.getRajdhaniBold());
//            tvBaseUserName.setTypeface(App.getRajdhaniMed());
//            tvBaseUserEmail.setTypeface(App.getRajdhaniMed());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void setDrawerListAdapter() {
        try {
            App.showLog(TAG + "==0000==setDrawerListAdapter==");
//            if (baseRecyclerView != null && getBaseMenuListData().size() > 0) {
//                App.showLog(TAG + "==1111==setDrawerListAdapter==");
//                baseMenuAdapter = new BaseMenuAdapter(BaseActivity.this, getBaseMenuListData());
//                baseRecyclerView.setAdapter(baseMenuAdapter);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initDrawer() {
        try {
            left_drawer.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {// TODO Auto-generated method stub
                    // TODO Auto-generated method stub
                    App.showLog("==base menu act==", "===on touch==");

                    if (v instanceof EditText) {
                        App.showLog("==base menu act==", "=touch no hide edittext==2=");
                    } else {
                        App.showLog("==base menu act==", "===on touch hide=2=");
                        //   v.clearFocus();
                        app.hideSoftKeyboardMy(BaseActivity.this);
                    }
                    return true;
                }
            });


            drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    try {
                        App.showLog("==onDrawerOpened===");
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                }

                @Override
                public void onDrawerClosed(View drawerView) {

                    App.showLog("==onDrawerClosed===");

                    if (drawerView instanceof EditText) {
                        App.showLog("==base menu act==", "=touch no hide edittext==2=");
                    } else {
                        App.showLog("==base menu act==", "===on touch hide=2=");
                        //   v.clearFocus();
                        app.hideSoftKeyboardMy(BaseActivity.this);
                    }
                }

                @Override
                public void onDrawerStateChanged(int newState) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setEnableDrawer(boolean blnEnable) {
        if (blnEnable == true) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }


//    public ArrayList<BaseSideMenuModel> getBaseMenuListData() {
//
//        ArrayList<BaseSideMenuModel> arrayListMenuItems = new ArrayList<>();
//
//        arrayListMenuItems.add(new BaseSideMenuModel(App.APP_FEED, R.drawable.ic_feed, 0));
//        arrayListMenuItems.add(new BaseSideMenuModel(App.APP_SHOP, R.drawable.ic_shop, 1));
//        arrayListMenuItems.add(new BaseSideMenuModel(App.APP_SELL, R.drawable.ic_sell, 2));
//        arrayListMenuItems.add(new BaseSideMenuModel(App.APP_NEWS, R.drawable.ic_news, 3));
//        if (App.sharePrefrences.getStringPref(Const.isLogin) != null &&
//                App.sharePrefrences.getStringPref(Const.isLogin).length() > 0 &&
//                App.sharePrefrences.getStringPref(Const.isLogin).equalsIgnoreCase(App.TRUE)) {
//            arrayListMenuItems.add(new BaseSideMenuModel("@" + App.sharePrefrences.getStringPref(Const.strUsername), R.drawable.ic_profile, 4));
//        } else {
//            arrayListMenuItems.add(new BaseSideMenuModel(App.APP_PROFILE, R.drawable.ic_profile, 4));
//        }
//        // arrayListMenuItems.add(new BaseSideMenuModel(App.APP_PROFILE, R.drawable.ic_profile, 4));
////        arrayListMenuItems.add(new BaseSideMenuModel(App.APP_LOGOUT, R.drawable.ic_logout, 5));
//
//        return arrayListMenuItems;
//    }
//
//
//    public class BaseMenuAdapter extends RecyclerView.Adapter<BaseMenuAdapter.VersionViewHolder> {
//        ArrayList<BaseSideMenuModel> mArrayList;
//        Context mContext;
//
//        public BaseMenuAdapter(Context context, ArrayList<BaseSideMenuModel> arrayList) {
//            mArrayList = arrayList;
//            mContext = context;
//        }
//
//        @Override
//        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_base_activity_side_menu_items, viewGroup, false);
//            VersionViewHolder viewHolder = new VersionViewHolder(view);
//            return viewHolder;
//        }
//
//
//        @Override
//        public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int position) {
//            try {
//                if (mArrayList != null && mArrayList.size() > 0) {
//                    final BaseSideMenuModel model = mArrayList.get(position);
//
//                    final String menu_selected_id = App.sharePrefrences.getStringPref(Const.strMenuSelectedId);
//                    // App.showLogTAG(TAG + "==menu_selected_id==" + menu_selected_id);
//
//                    if (menu_selected_id != null && menu_selected_id.length() > 0) {
//                        try {
//                            int intMenu = 0;
//                            intMenu = Integer.parseInt(menu_selected_id);
//
//                            if (model.menu_id == intMenu) {
//                                versionViewHolder.tvMenuName.setTypeface(App.getRajdhaniBold());
//                                versionViewHolder.cstLatout.setBackgroundColor(ContextCompat.getColor(BaseActivity.this, R.color.clrTRGreyColor));
//                            } else {
//                                versionViewHolder.tvMenuName.setTypeface(App.getRajdhaniMed());
//                                versionViewHolder.cstLatout.setBackgroundColor(ContextCompat.getColor(BaseActivity.this, R.color.clrWhite));
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        App.sharePrefrences.setPref(Const.strMenuSelectedId, "0");
//                    }
//                    // //
//                    versionViewHolder.tvMenuName.setText(model.menu_name);
//                    versionViewHolder.ivMenuIcon.setImageResource(model.menu_icon);
//                    //
//                    versionViewHolder.cstLatout.setOnClickListener(new View.OnClickListener() {
//                        @SuppressLint("NewApi")
//                        @Override
//                        public void onClick(View view) {
//
//                            drawer.closeDrawer(GravityCompat.START);
//
//                            App.sharePrefrences.setPref(Const.strMenuSelectedId, "" + model.menu_id);
//                            App.showLog("===selected menu id===pref==" + menu_selected_id);
//
//                            if (model.menu_id == 0) { // Dashboard
//                                if (!menu_selected_id.equalsIgnoreCase("0")) {
//                                    new Handler().postDelayed(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            Intent intent = new Intent(BaseActivity.this, ActDashboard.class);
//                                            intent.putExtra(Const.ITAG_FROM, "BaseActivity");
//                                            startActivity(intent);
//                                            animStartActivity();
//                                        }
//                                    }, 280);
//                                }
//                            } else if (model.menu_id == 2) { // Sell
//                                if (!menu_selected_id.equalsIgnoreCase("2")) {
//                                    new Handler().postDelayed(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            Intent intent = new Intent(BaseActivity.this, ActSellNewOne.class);
//                                            intent.putExtra(Const.ITAG_FROM, "BaseActivity");
//                                            startActivity(intent);
//                                            animStartActivity();
//                                        }
//                                    }, 280);
//                                }
//                            } else if (model.menu_id == 4) { // Profile("4")) {
//                                new Handler().postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Intent intent = new Intent(BaseActivity.this, ActProfile.class);
//                                        intent.putExtra(Const.ITAG_FROM, "BaseActivity");
//                                        startActivity(intent);
//                                        animStartActivity();
//                                    }
//                                }, 280);
//                            }
////                            else if (model.menu_id == 5) { // Logout
////                                showLogoutDialog();
////                            }
//                            baseMenuAdapter.notifyDataSetChanged();
//                        }
//                    });
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        @Override
//        public int getItemCount() {
//            return mArrayList.size();
//        }
//
//
//        class VersionViewHolder extends RecyclerView.ViewHolder {
//
//            ImageView ivMenuIcon;
//            TextView tvMenuName;
//            ConstraintLayout cstLatout;
//
//            public VersionViewHolder(View itemView) {
//                super(itemView);
//
//                ivMenuIcon = (ImageView) itemView.findViewById(R.id.ivMenuIcon);
//                tvMenuName = (TextView) itemView.findViewById(R.id.tvMenuName);
//                cstLatout = (ConstraintLayout) itemView.findViewById(R.id.cstLatout);
//                /*
//                * FONTS
//                * */
//                tvMenuName.setTypeface(App.getRajdhaniMed());
//            }
//        }
//    }


    public void animStartActivity() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    public void animFinishActivity() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    @Override
    protected void onResume() {
        try {
            App.showLog(TAG + "==onResume()==");
            //
//            setUserDataToSideMenu();
            //
//            if (App.sharePrefrences.getStringPref(Const.strUserId) != null &&
//                    App.sharePrefrences.getStringPref(Const.strUserId).length() > 0) {
//                if (App.isInternetAvail(BaseActivity.this)) {
//                    asyncUpdateDeviveToken();
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onResume();
    }


//    protected void setUserDataToSideMenu() {
//        try {
//            App.showLog(TAG + "==setUserDataToSideMenu==");
//
//            if (App.sharePrefrences.getStringPref(Const.isLogin) != null &&
//                    App.sharePrefrences.getStringPref(Const.isLogin).length() > 0 &&
//                    App.sharePrefrences.getStringPref(Const.isLogin).equalsIgnoreCase(App.TRUE)) {
//
//                if (App.sharePrefrences.getStringPref(Const.strUserId) != null &&
//                        App.sharePrefrences.getStringPref(Const.strUserId).length() > 0) {
//                    //
//                    if (App.sharePrefrences.getStringPref(Const.strUserFullName) != null &&
//                            App.sharePrefrences.getStringPref(Const.strUserFullName).length() > 0) {
//                        tvBaseUserFullName.setText(App.sharePrefrences.getStringPref(Const.strUserFullName));
//                    }
//                    //
//                    if (App.sharePrefrences.getStringPref(Const.strUsername) != null &&
//                            App.sharePrefrences.getStringPref(Const.strUsername).length() > 0) {
//                        tvBaseUserName.setText(App.sharePrefrences.getStringPref(Const.strUsername));
//                    }
//                    //
//                    if (App.sharePrefrences.getStringPref(Const.strUserEmail) != null &&
//                            App.sharePrefrences.getStringPref(Const.strUserEmail).length() > 0) {
//                        tvBaseUserEmail.setText(App.sharePrefrences.getStringPref(Const.strUserEmail));
//                    }
//                    //
//                    if (App.sharePrefrences.getStringPref(Const.strUserPic) != null &&
//                            App.sharePrefrences.getStringPref(Const.strUserPic).length() > 0) {
//                        Const.setCircularImageViewImage(BaseActivity.this, ivBaseUserImage,
//                                App.sharePrefrences.getStringPref(Const.strUserPic), 200, 200);
//                    }
//                    //
//                    if (App.sharePrefrences.getStringPref(Const.strUserCoverPic) != null &&
//                            App.sharePrefrences.getStringPref(Const.strUserCoverPic).length() > 0) {
//                        Glide.with(this).load(App.sharePrefrences.getStringPref(Const.strUserCoverPic))
//                                .apply(bitmapTransform(new BlurTransformation(BaseActivity.this)))
//                                .into(ivBaseSongBgBlurImage);
//                    } else {
//                        Const.setCircularImageViewImage(BaseActivity.this, ivBaseUserImage,
//                                App.sharePrefrences.getStringPref(Const.strUserPic), 200, 200);
//                    }
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        //	setCloseDrawerMenu(true);
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(llContainerMain.getWindowToken(), 0);
        super.onPause();
    }


    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        System.out.println("=====Base onStop====");
        //	setCloseDrawerMenu(true);
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub

        System.out.println("=====Base onDestroy====");
        super.onDestroy();
    }


    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
        } else {
            try {
                super.onBackPressed();
                animFinishActivity();
            } catch (Exception e) {
                e.printStackTrace();
                App.showLog("==Exception on base back click====");
            }
        }
    }



    protected void logoutFinishAllActivity() {
        try {
            App.showLog("=====logoutFinishAllActivity===");
            // String strDeviceId = App.sharePrefrences.getStringPref(Const.strDeviceId);
//            App.sharePrefrences.clearPrefValues();
//            // App.sharePrefrences.setPref(Const.strDeviceId, strDeviceId);
//            App.sharePrefrences.setPref(Const.isLogin, App.FALSE);

            NotificationManager notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notifManager.cancelAll();

//            Intent iv = new Intent(BaseActivity.this, ActLogin.class);
//            iv.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            iv.putExtra(Const.ITAG_FROM, "BaseActivity");
//            startActivity(iv);
//            App.myFinishActivityRefresh(BaseActivity.this, iv);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                finishAffinity();
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void showExitDialog() {
        try {
            if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawers();
            }

            final Dialog dialog = new Dialog(BaseActivity.this);
            dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // for dialog shadow
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
            dialog.setContentView(R.layout.popup_exit);

            // set values for custom dialog components - text, image and button
            TextView tvExitMessage = (TextView) dialog.findViewById(R.id.tvMessage);
            TextView tvCancel = (TextView) dialog.findViewById(R.id.tvCancel);
            TextView tvOK = (TextView) dialog.findViewById(R.id.tvOk);
            //
//            tvExitMessage.setTypeface(App.getRajdhaniBold());
//            tvOK.setTypeface(App.getRajdhaniBold());
//            tvCancel.setTypeface(App.getRajdhaniMed());
            //
            String strAlertMessageExit = "Are you sure you want to exit?";
            String strYes = "Exit";
            String strNo = "Cancel";

            tvExitMessage.setText(strAlertMessageExit);
            tvCancel.setText(strNo);
            tvOK.setText(strYes);

            dialog.show();

            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            dialog.dismiss();
                        }
                    }, 280);
                }
            });

            tvOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialog.dismiss();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            App.myFinishActivity(BaseActivity.this);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                finishAffinity();
                            }
                            onBackPressed();
                            return;
                        }
                    }, 280);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
//
//    public void showLogoutDialog() {
//        try {
//            if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
//                drawer.closeDrawers();
//            }
//
//            final Dialog dialog = new Dialog(BaseActivity.this);
//            dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // for dialog shadow
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
//            dialog.setContentView(R.layout.popup_exit);
//
//            // set values for custom dialog components - text, image and button
//            TextView tvExitMessage = (TextView) dialog.findViewById(R.id.tvMessage);
//            TextView tvCancel = (TextView) dialog.findViewById(R.id.tvCancel);
//            TextView tvOK = (TextView) dialog.findViewById(R.id.tvOk);
//            //
//            tvExitMessage.setTypeface(App.getRajdhaniBold());
//            tvOK.setTypeface(App.getRajdhaniBold());
//            tvCancel.setTypeface(App.getRajdhaniMed());
//            //
//            String strAlertMessageExit = "Are you sure you want to logout?";
//            String strYes = "Logout";
//            String strNo = "Cancel";
//
//            tvExitMessage.setText(strAlertMessageExit);
//            tvCancel.setText(strNo);
//            tvOK.setText(strYes);
//
//            dialog.show();
//
//            tvCancel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    new Handler().postDelayed(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            dialog.dismiss();
//                        }
//                    }, 280);
//                }
//            });
//
//            tvOK.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    dialog.dismiss();
//                    logoutFinishAllActivity();
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
