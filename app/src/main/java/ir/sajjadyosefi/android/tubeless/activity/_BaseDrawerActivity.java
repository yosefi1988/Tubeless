package ir.sajjadyosefi.android.tubeless.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.splunk.mint.Mint;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.tubeless.BuildConfig;
import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.tubeless.classes.HamburgerMenuDrawable;
import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.Listener.RecyclerItemClickListener;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.classes.model.menu.MultiTitleItem;
import ir.sajjadyosefi.android.tubeless.classes.adapter.menu.SideMenuAdapter;
import ir.sajjadyosefi.android.tubeless.classes.model.menu.SliderMenuItem;
import ir.sajjadyosefi.android.tubeless.classes.model.menu.SliderMenuMultTitle;
import ir.sajjadyosefi.android.tubeless.classes.model.menu.SlidingMenuItemTitle;


/**
 * Created by Sajad on 2/11/2017.
 */
public class _BaseDrawerActivity extends AppCompatActivity implements RecyclerItemClickListener.OnItemClickListener {

    private DrawerLayout drawerLayout;
    private List<SlidingMenuItemTitle> items;
    SideMenuAdapter adapter;


    //private ImageLoader imageLoader = new ImageLoader(this);
    private Toolbar toolbar;
    private final Context context = this;
    private View rootView;

    private RecyclerView rclMenuItem;
    public static ImageView iv_UserImage;
    public static ImageView iv_UserImageCover;
    public static TextView txtUserName;
    public static TextView txtEditUserProfile;

    @Override
    public void onItemClick(View view, int position) {

    }

    private void mint() {
        Mint.setApplicationEnvironment(Mint.appEnvironmentDevelopment);
        Mint.initAndStartSession(_BaseDrawerActivity.this, "91043c9b");
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        super.setContentView(R.layout._activity_navigation_drawer);
        //mint();
        //Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(mContext));


        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.drawer_fl_contentRoot);

        rootView = LayoutInflater.from(this).inflate(layoutResID, viewGroup, true);
        Global.mUser = Global.LoadLogedInUser(context);
        initializeDrawer();



        //Logo And Hamburger
        View view = findViewById(R.id.view);
        ImageView logo = (ImageView) findViewById(R.id.ivTubelessLogo);
        final HamburgerMenuDrawable hamburgerMenuDrawable = new HamburgerMenuDrawable(getResources().getDimensionPixelSize(R.dimen.stroke_width), Color.WHITE, 0x1d34be);

        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(hamburgerMenuDrawable);
        } else {
            view.setBackground(hamburgerMenuDrawable);
        }
        View.OnClickListener clickOnLogoListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hamburgerMenuDrawable.toggle();
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        };
        logo.setOnClickListener(clickOnLogoListener);
        view.setOnClickListener(clickOnLogoListener);
        //End Logo And Hamburger

    }

    protected void injectView() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    public void initializeDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        rclMenuItem = (RecyclerView) findViewById(R.id.rclMenuItem);
        rclMenuItem.setLayoutManager(new LinearLayoutManager(this));

        createMenuItems();

        adapter = new SideMenuAdapter(items);
        rclMenuItem.setAdapter(adapter);
        rclMenuItem.addOnItemTouchListener(new RecyclerItemClickListener(this, this));

        iv_UserImage = (ImageView) findViewById(R.id.imgUser);
        iv_UserImageCover = (ImageView) findViewById(R.id.iv_menuCover);



        txtUserName = (TextView) findViewById(R.id.txtUsername);
        txtEditUserProfile = (TextView) findViewById(R.id.txtEditProfile);


        findViewById(R.id.iv_menuCover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        });







        findViewById(R.id.linSpace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        });
        //getUserPofile();



        injectView();
        userInitialize();

    }

    private void userInitialize() {



        if(Global.mUser != null) {
            //iv_UserImageCover
            //iv_UserImage
            //txtUserName.setOnClickListener(goToProfile);
            //txtEditUserProfile.setOnClickListener(goToProfile);

            if(Global.mUser.getUserName()!= "")
                txtUserName.setText(Global.mUser.getUserName());
            else
                txtUserName.setText(context.getResources().getString(R.string.UnKnown));
            txtEditUserProfile.setText(context.getResources().getString(R.string.EDIT_PROFILE));


            if(Global.mUser.getUserImage() != null && !Global.mUser.getUserImage().equals(""))
                Picasso.with(context)
                        .load(Global.mUser.getUserImage())
                        .into(iv_UserImage);
            else
                Picasso.with(context)
                        .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7fmKV2li2SGhAi9UOpA2K-KO_y1NGcCGrfY2tvjiacoTbRwgn")
                        .into(iv_UserImage);

            if(Global.mUser.getProfileImage() != null && !Global.mUser.getProfileImage().equals(""))
                Picasso.with(context)
                        .load(Global.mUser.getProfileImage())
                        .into(iv_UserImageCover);
            else
                Picasso.with(context)
                        .load("http://www.pngpix.com/wp-content/uploads/2016/06/PNGPIX-COM-McLaren-650S-GT3-Yellow-Race-Car-PNG-Image.png")
                        .into(iv_UserImageCover);

        }else {
            txtUserName.setText("");
            txtEditUserProfile.setText("");
        }
    }

    View.OnClickListener BlankListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(),"Noting",Toast.LENGTH_SHORT).show();;
        }
    };



    View.OnClickListener goToPishnahadatListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

//            Intent intent = new Intent(mContext,NewOffersActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putInt("MODE", Statics.NEW_OFFER);
//            intent.putExtras(bundle);
//            ((Activity)mContext).startActivity(intent);

            drawerLayout.closeDrawer(Gravity.RIGHT);
        }
    };

    View.OnClickListener goToTicketListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            try {
//                Intent intent = new Intent(getApplicationContext(), TicketActivity.class);
//                intent.putExtra("kind", 3);
//                startActivityForResult(intent, 210);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    };
    View.OnClickListener goToMessageListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
//                startActivityForResult(new Intent(getApplicationContext(), MessageActivity.class), 101);
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    };

    View.OnClickListener goToMessageListListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
//                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
//                Bundle bundle = new Bundle();
//
//                if(((TextView)v).getText().toString().equals(mContext.getString(R.string.VIEW_MESSAGE_LIST)))
//                    bundle.putInt("listType",Statics.VIEW_INPUT_MESSAGE);
//                else if(((TextView)v).getText().toString().equals(mContext.getString(R.string.VIEW_SENT_MESSAGE_LIST)))
//                    bundle.putInt("listType",Statics.VIEW_OUTPUT_MESSAGE);
//                else if(((TextView)v).getText().toString().equals(mContext.getString(R.string.VIEW_TRASH_MESSAGE_LIST)))
//                    bundle.putInt("listType",Statics.VIEW_TRASH_MESSAGE);
//
//                intent.putExtras(bundle);
//                startActivity(intent);
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    };

    View.OnClickListener goToProfile = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("Ty","");
                startActivity(intent);
                drawerLayout.closeDrawer(Gravity.RIGHT);
                overridePendingTransition(R.anim.fadeout, R.anim.fadein);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    };
    View.OnClickListener goToGhavanin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getApplicationContext(),WebViewActivity.class);
            Bundle bundle = new Bundle();
    //        bundle.putString("WebType", "report");
    //        bundle.putString("WebType", "feedback");
    //        bundle.putString("WebType", "about");
            bundle.putString("WebType", "rule");
            bundle.putBoolean("isOnline" ,false);
            //bundle.putString("address", "http://www.sajjadyosefi.ir");
            intent.putExtras(bundle);
            startActivity(intent);
            overridePendingTransition(R.anim.fadeout, R.anim.fadein);

            drawerLayout.closeDrawer(Gravity.RIGHT);
        }
    };

    private void createMenuItems() {
        items = new ArrayList<>();

        //BlankListener
        //items.add(new SlidingMenuItemWithSwitch(R.string.privateUser, "privateUser", null));
        //items.add(new SliderMenuItem(R.string.INSTALING,        R.drawable.profile_ic_sajjad, "inviteFriends", R.color.colorPrimary));

        items.add(new SlidingMenuItemTitle(R.string.account));
        if(Global.mUser == null) {
            items.add(new SliderMenuItem(R.string.login, R.string.loginIcon,25, newActivity(LoginActivity.LOGIN), R.color.colorPrimary));
            items.add(new SliderMenuItem(R.string.register, R.string.RegisterIon,25, newActivity(LoginActivity.REGISTER), R.color.colorPrimary));
        }else {
            //items.add(new SliderMenuItem(R.string.forgetPassword, R.drawable.png_forget_password, newActivity(LoginActivity.FORGET_PASSWORD), R.color.colorPrimary));
            items.add(new SliderMenuItem(R.string.changePassword, R.string.changePasswordIcon,21, newActivity(LoginActivity.CHANGE_PASSWORD), R.color.colorPrimary));
        }



        items.add(new SlidingMenuItemTitle(R.string.app_name));

        items.add(new SliderMenuItem(R.string.car_under_control, R.string.loginIcon,25, goToRadyabSelectCar, R.color.colorPrimary));
        items.add(new SliderMenuItem(R.string.registerRadyab, R.string.loginIcon,25, goToRadyabRegister, R.color.colorPrimary));


        items.add(new SliderMenuItem(R.string.ghavanin,    R.string.ghavaninIcon,20, goToGhavanin, R.color.colorPrimary));
        //items.add(new SliderMenuItem(R.string.app_name,    R.drawable.jpg_kosar, goToProfile, R.color.colorPrimary));

        items.add(new SliderMenuItem(R.string.contactUsMenu,         R.string.contactUsMenuIcon,20, newActivity(ContactUsActivity.CONTACT_US),          R.color.colorPrimary));
        items.add(new SliderMenuItem(R.string.AbouteThisVersion,         R.string.AbouteThisVersionIcon,30,ShowAbouteThisVersion(),          R.color.colorPrimary));

        items.add(new SlidingMenuItemTitle(R.string.socialNetwork));
        List<MultiTitleItem> multTitleitems = new ArrayList<>();
        multTitleitems.add(new MultiTitleItem(R.id.linRootWebLog, R.string.InternetIcon,20, "showWebPage", new menuMultiTitleItemClick()));
        multTitleitems.add(new MultiTitleItem(R.id.linRootTelegram, R.string.TelegramIcon,20, "showTelegram", new menuMultiTitleItemClick()));
        multTitleitems.add(new MultiTitleItem(R.id.linRootInstagram,  R.string.InstagramIcon,20,"showInstagram", new menuMultiTitleItemClick()));
        items.add(new SliderMenuMultTitle(-1, multTitleitems));

        if(Global.mUser != null) {
            items.add(new SliderMenuItem(R.string.logout,R.string.logoutIcon,25, logOutListener, R.color.colorPrimary));
        }

        if(Global.avalableSendAppInMenu(context))
            items.add(new SliderMenuItem(R.string.shareApp, R.string.shareAppIcon, 30,shareAppListener, R.color.colorPrimary));

        items.add(new SliderMenuItem(R.string.closeApp, R.string.closeAppIcon, 30,exitListener, R.color.colorPrimary));

    }

    private View.OnClickListener ShowAbouteThisVersion() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String versionName;
                versionName = CommonClass.GetAppVersion(context);
                if(versionName == "0")
                    versionName = BuildConfig.VERSION_NAME;

                Global.ShowMessageDialog(context,"",context.getString(R.string.VersionName) + ":" + versionName);
            }
        };
    }

    View.OnClickListener goToRadyabSelectCar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            final Intent intent = new Intent(getApplicationContext(), SelectCarForRadyab.class);
//            finish();
//            startActivity(intent);
//            overridePendingTransition(R.anim.fadeout, R.anim.fadein);
            Global.ShowMessageDialog(context,"",context.getString(R.string.commingSoon));
        }
    };

    View.OnClickListener goToRadyabRegister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            final Intent intent = new Intent(getApplicationContext(), RegisterPhoneActivity.class);
//            finish();
//            startActivity(intent);
//            overridePendingTransition(R.anim.fadeout, R.anim.fadein);
            Global.ShowMessageDialog(context,"",context.getString(R.string.commingSoon));
        }
    };

    private View.OnClickListener newRadyabActivity() {
//        final Intent intent = new Intent(getApplicationContext(), RegisterPhoneActivity.class);
////        Bundle bundle = new Bundle();
////        bundle.putInt(LoginActivity.Type, RegisterPhoneActivity.class);
////        intent.putExtras(bundle);
//        finish();
//        startActivity(intent);
//        overridePendingTransition(R.anim.fadeout, R.anim.fadein);
        return null;
    }
    private View.OnClickListener newActivity(final int activityType) {
        final Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        switch (activityType) {
            case LoginActivity.LOGIN:
            {
                return new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(LoginActivity.Type,activityType);
                        intent.putExtras(bundle);
                        finish();
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadeout, R.anim.fadein);

                    }
                };
            }
            case LoginActivity.REGISTER:
            {
                return new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(LoginActivity.Type,activityType);
                        intent.putExtras(bundle);
                        finish();
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                    }
                };
            }
            case LoginActivity.FORGET_PASSWORD:
            {
                return new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(LoginActivity.Type,activityType);
                        intent.putExtras(bundle);
                        finish();
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                    }
                };
            }
            case LoginActivity.CHANGE_PASSWORD:
            {
                return new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(LoginActivity.Type,activityType);
                        intent.putExtras(bundle);
                        finish();
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                    }
                };
            }
            case ContactUsActivity.CONTACT_US:
            {
                return new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), ContactUsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt(ContactUsActivity.Type,activityType);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                    }
                };
            }
        }
        return null;
    }

    private class menuMultiTitleItemClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            try {
                Method method = _BaseDrawerActivity.this.getClass().getMethod(v.getTag().toString());
                method.invoke(_BaseDrawerActivity.this, null);

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }





    View.OnClickListener logOutListener  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //EventBus.getDefault().post(new MessageEvent("logOut"));
            Global.mUser = null;

            Intent intent = new Intent(context,MainActivity.class);
            ((Activity)context).startActivity(intent);
            ((Activity)context).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
            ((Activity)context).finish();
            //Global.ShowMessageDialog(mContext,"",mContext.getResources().getString(R.string.LOGOUT_SUCCSESS));
            Toast.makeText(context,context.getResources().getString(R.string.LOGOUT_SUCCSESS), Toast.LENGTH_SHORT).show();
            Global.ClearLogedInUser(context);

            drawerLayout.closeDrawer(Gravity.RIGHT);
        }
    };
    View.OnClickListener shareAppListener  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            overridePendingTransition(R.anim.fadeout, R.anim.fadein);


            ApplicationInfo app = getApplicationContext().getApplicationInfo();
            String filePath2 = app.sourceDir;

            Intent intent = new Intent(Intent.ACTION_SEND);

            intent.setType("*/*");

            File originalApk = new File(filePath2);

            try {
                File tempFile = new File(getExternalCacheDir() + "/ExtractedApk");
                //If directory doesn't exists create new
                if (!tempFile.isDirectory())
                    if (!tempFile.mkdirs())
                        return;
                tempFile = new File(tempFile.getPath() + "/" + getString(app.labelRes).replace(" ","").toLowerCase() + ".apk");
                if (!tempFile.exists()) {
                    if (!tempFile.createNewFile()) {
                        return;
                    }
                }
                InputStream in = new FileInputStream(originalApk);
                OutputStream out = new FileOutputStream(tempFile);

                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                System.out.println("File copied.");
                // باز کردن پنجره اشتراک گذاری
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempFile));
                startActivity(Intent.createChooser(intent, "اشتراک گذاری با"));

            } catch (IOException e) {
                e.printStackTrace();
            }



        }
    };


    View.OnClickListener exitListener  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            overridePendingTransition(R.anim.fadeout, R.anim.fadein);
            finish();
        }
    };

    public void showTelegram() {
        drawerLayout.closeDrawer(Gravity.RIGHT);

        final String appName = "org.telegram.messenger";
        final String appName1 = "com.parsitelg.telegram";
        final String appName2 = "org.telegram.plus";
        final String appName3 = "com.hanista.moboplus";
        final String appName4 = "com.hanista.mobogram";
        final String appName5 = "com.hanista.mobogram.two";
        final String appName6 = "com.hanista.mobogram.three";
        final String appName7 = "ir.persianfox.messenger";
        final String appName8 = "com.telegram.hame.mohamad";
        final String appName9 = "org.ir.talaeii";
        final String appName10 = "ir.rrgc.telegram";
        final String appName11 = "ir.felegram";

        if (CommonClass.isAppAvailable(this, appName)) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(browserIntent);
        } else {
            if (CommonClass.isAppAvailable(this, appName1)) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(browserIntent);
            } else {
                if (CommonClass.isAppAvailable(this, appName2)) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(browserIntent);
                } else {
                    if (CommonClass.isAppAvailable(this, appName3)) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(browserIntent);
                    } else {
                        if (CommonClass.isAppAvailable(this, appName4)) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(browserIntent);
                        } else {
                            if (CommonClass.isAppAvailable(this, appName5)) {
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(browserIntent);
                            } else {
                                if (CommonClass.isAppAvailable(this, appName6)) {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(browserIntent);
                                } else {
                                    if (CommonClass.isAppAvailable(this, appName7)) {
                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                                        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(browserIntent);
                                    } else {
                                        if (CommonClass.isAppAvailable(this, appName8)) {
                                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                                            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(browserIntent);
                                        } else {
                                            if (CommonClass.isAppAvailable(this, appName9)) {
                                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                                                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(browserIntent);
                                            } else {
                                                if (CommonClass.isAppAvailable(this, appName10)) {
                                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                                                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    startActivity(browserIntent);
                                                } else {
                                                    if (CommonClass.isAppAvailable(this, appName11)) {
                                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                                                        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                        startActivity(browserIntent);
                                                    } else {
                                                        Global.ShowMessageDialog(context,"","شما تلگرام را نصب نکرده اید.");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

//        if(CommonClass.isNetworkConnected(mContext)) {
//        } else
//            Toast.makeText(mContext,"showTelegram", Toast.LENGTH_LONG).show();
    }
    public void showInstagram() {
        drawerLayout.closeDrawer(Gravity.RIGHT);

        final String appName = "com.instagram.android";

        if (CommonClass.isAppAvailable(this, appName)) {
//            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/Tubeless_SN"));
//            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(browserIntent);


            Intent iIntent = getPackageManager().getLaunchIntentForPackage(appName);
            iIntent.setComponent(new ComponentName( "com.instagram.android", "com.instagram.android.activity.UrlHandlerActivity"));
            iIntent.setData( Uri.parse( "http://instagram.com/_u/Tubeless_SN") );
            startActivity(iIntent);
        } else {
            //showDownloadDialog(R.string.downloadTelegram, appName);
            Global.ShowMessageDialog(context,"","شما اینستاگرام را نصب نکرده اید.");
        }

    }


    public void showWebPage() {


        Global.ShowMessageDialog(context,"","در حال آماده سازی هستیم.");
        //ok
//        Intent intent = new Intent(getApplicationContext(),WebViewActivity.class);
//        Bundle bundle = new Bundle();
//
////        bundle.putString("WebType", "report");
////        bundle.putString("WebType", "feedback");
////        bundle.putString("WebType", "about");
//        bundle.putString("WebType", "help");
//
//
//        bundle.putBoolean("isOnline" ,false);
//        bundle.putString("address", "http://www.sajjadyosefi.ir");
//
//        drawerLayout.closeDrawer(Gravity.RIGHT);
//        intent.putExtras(bundle);
//        startActivity(intent);
    }
}
