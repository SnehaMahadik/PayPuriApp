package com.benow.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;


import com.benow.R;
import com.benow.activity.SettingsActivity;
import com.benow.adapters.NavigationDrawerAdapter;
import com.benow.models.DrawerChildItems;
import com.benow.models.DrawerHeaderItem;
import com.benow.models.DrawerItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Swapnil Kulkarni
 */

public class DrawerFragment extends Fragment implements AdapterView.OnItemClickListener,View.OnClickListener{
 
    private static String TAG = DrawerFragment.class.getSimpleName();
 
    private ListView mlistviewDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private View containerView;
    private static String[] titles = null;
    private FragmentDrawerListener drawerListener;
    ArrayList<DrawerItem> items = new ArrayList<DrawerItem>();
    private NavigationDrawerAdapter adapter;
    private FragmentTransaction fragmentTransaction;
    private ImageView mImageViewUserPic;
    private int PICK_IMAGE_REQUEST=1;
    File direct,file;

    public DrawerFragment() {
 
    }
 
    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }

 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        // drawer labels
        titles = getActivity().getResources().getStringArray(R.array.nav_drawer_labels);
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating view layout
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        mlistviewDrawer = (ListView) layout.findViewById(R.id.ldrawerList);
        mImageViewUserPic=(ImageView)layout.findViewById(R.id.imageViewUserPic);

 
        adapter = new NavigationDrawerAdapter(getActivity().getApplicationContext(), setData());
        mlistviewDrawer.setAdapter(adapter);
        mlistviewDrawer.setOnItemClickListener(this);
        mImageViewUserPic.setOnClickListener(this);
 
        return layout;
    }
 
 
    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
                mDrawerToggle.setDrawerIndicatorEnabled(true);
            }
 
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
 
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };
 
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
 
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position)
        {
            case 1://settings fragment
                /*mDrawerLayout.closeDrawer(containerView);
                Intent intent1 = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent1);*/

                break;
            case 2://settings fragment
                mDrawerLayout.closeDrawer(containerView);
                Intent intent1 = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent1);

                break;
            case 3://About us fragment
               // Fragment fragment = new InboxFragment();
                // changeFragment(fragment,"TAG");
                break;
            case 4:
                Fragment transactionHistoryFragment = new TransactionHistoryFragment();
                 changeFragment(transactionHistoryFragment, "TAG");

                break;
            case 6://Aboutus Fragment
                Fragment fragment_aboutus = new AboutusFragment();
                changeFragment(fragment_aboutus,"AboutusFragment");
                break;
            case 7:
                //Fragment fragment = new InboxFragment();
                //changeFragment(fragment,"TAG");
                break;
            case 8:
                Fragment fragment1 = new InboxFragment();
                changeFragment(fragment1,"TAG");
                break;
            case 10:
                //Fragment fragment = new InboxFragment();
                //changeFragment(fragment,"TAG");
                break;

            case 11:
                //Fragment fragment = new InboxFragment();
                // changeFragment(fragment,"TAG");
                break;
//
            default:
                break;
        }
    }

    public void changeFragment(Fragment fragment, String tag){
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
        mDrawerLayout.closeDrawer(containerView);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.imageViewUserPic:
                pickImageFromGallery();
                break;
           default:
               break;

        }

    }

    private void pickImageFromGallery() {
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    public static interface ClickListener {
        public void onClick(View view, int position);
 
        public void onLongClick(View view, int position);
    }
 
    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
 
        private GestureDetector gestureDetector;
        private ClickListener clickListener;
 
        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
 
                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }
 
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
 
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }
 
        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }
 
        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
 
        }
 
 
    }
 
    public interface FragmentDrawerListener {
        public void onDrawerItemSelected(View view, int position);
    }

    private ArrayList<DrawerItem> setData() {
        items=new ArrayList<DrawerItem>();
        items.add(new DrawerHeaderItem("Lock App"));
       // items.add(new DrawerHeaderItem("Dashboard"));

        items.add(new DrawerHeaderItem("Accounts"));
        items.add(new DrawerChildItems("Settings",R.drawable.ic_vector_settings));
        items.add(new DrawerChildItems("Create Id", R.drawable.ic_vector_id));
        items.add(new DrawerChildItems("Transaction History", R.drawable.ic_transaction_history));


        items.add(new DrawerHeaderItem("Help and Support"));
        items.add(new DrawerChildItems("About us",R.drawable.ic_about_us));
        items.add(new DrawerChildItems("QuickPayPhoneContact us", R.drawable.ic_contact_us));
        items.add(new DrawerChildItems("FAQs", R.drawable.ic_faq));

        items.add(new DrawerHeaderItem("Show your love"));
        items.add(new DrawerChildItems("Invite & earn",R.drawable.ic_invite));
        items.add(new DrawerChildItems("Rate us", R.drawable.ic_rate_us));
        return items;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                String path = Environment.getDataDirectory() + "/MyAppFolder/MyApp/"+bitmap+".png";

                File imgFile = new File(path);
               /* if(imgFile.exists()){

                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                    mImageViewUserPic.setImageBitmap(myBitmap);

                }
                else{
                    mImageViewUserPic.setImageBitmap(bitmap);
                    createDirectoryAndSaveFile(bitmap,"PayPuri/profilepic");
                }*/
                mImageViewUserPic.setImageBitmap(bitmap);
                saveImage(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void saveImage(Bitmap finalBitmap) {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        System.out.println(root +" Root value in saveImage Function");
        File myDir = new File(root + "/folder_name");

        if (!myDir.exists()) {
            myDir.mkdirs();
        }

        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String iname = "Image-" + n + ".jpg";
        File file = new File(myDir, iname);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // Tell the media scanner about the new file so that it is
        // immediately available to the user.
        MediaScannerConnection.scanFile(getActivity(), new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });

        String Image_path = Environment.getExternalStorageDirectory() + "/Pictures/folder_name/" + iname;

        File[] files = myDir.listFiles();
        int numberOfImages = files.length;
        System.out.println("Total images in Folder "+numberOfImages);
    }
}