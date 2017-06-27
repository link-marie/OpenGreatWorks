package com.linknext.libgreatworks;

import android.app.Application;
import android.content.Context;

/**
 */
public class Common extends Application {

    static private Application app;
    static private Context ctx;

    public Common() {
        Common.app = this;
    }

    static public String getAppName() {
        return "GreatWorks";
    }

    public static Context getCtx() {
        return ctx;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Common.ctx = app.getApplicationContext();
    }

    public enum kCategory {
        Animation( -1, R.drawable.img_work_animation ),
        App( -1, R.drawable.img_work_app ),
        Client( -1, R.drawable.img_work_client ),
        Data( -1, R.drawable.img_work_data ),
        Dept( -1, R.drawable.img_work_dept ),
        Dialog( -1, R.drawable.img_work_dialog ),
        Engine( -1, R.drawable.img_work_engine ),
        Image( -1, R.drawable.img_work_image ),
        Info( -1, R.drawable.img_work_information ),
        Layout( -1, R.drawable.img_work_layout ),
        List( -1, R.drawable.img_work_list ),
        Material( -1, R.drawable.img_work_material ),
        Media( -1, R.drawable.img_work_media ),
        Monetize( -1, R.drawable.img_work_monetize ),
        Menu( -1, R.drawable.img_work_menu ),
        Network( -1, R.drawable.img_work_network ),
        Others( -1, R.drawable.img_work_others ),
        View( -1, R.drawable.img_work_view ),

        LastItem( -1, -1 ),

        Search( R.string.categoryTitleSearch, R.drawable.img_work_search_result ),
        New( R.string.categoryTitleNew, R.drawable.img_work_new ),
        Favorite( R.string.categoryTitleFavorite, R.drawable.img_work_favorite );;

        public static final int size = LastItem.ordinal();
        private int idStr;
        private int idImg;

        kCategory( int idStr, int imgId ) {
            this.idStr = idStr;
            this.idImg = imgId;
        }

        static public kCategory getEnum( int i ) {
            if( i < 0 ) {
                return Others;
            }
            if( i >= size ) {
                return Others;
            }
            return values()[i];
        }

        public int getIdStr() {
            return idStr;
        }

        public int getIdImg() {
            return idImg;
        }
    }

    public enum kSort {
        Default,
        Rate,
        New,;

        static public int size = values().length;

        static public String[] getLabels() {
            String[] labels = new String[size];
            for( kSort e : values() ) {
                labels[e.ordinal()] = e.name();
            }
            return labels;
        }

        static public kSort getEnum( int i ) {
            return values()[i];
        }
    }
}

