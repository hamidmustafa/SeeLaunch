package seelaunch.seelaunch;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.sql.SQLException;

public class ContentProviderClassForContactDetails extends ContentProvider {
    public  static  final String PROVIDER_NAME_CONTACTDETAILS="seelaunch.seelaunch.contactdetails";
    /*  Every provider will be identified by its URI*/
    public static final Uri CONTENT_URI_CONTACTDETAILS = Uri.parse("content://" + PROVIDER_NAME_CONTACTDETAILS + "/contactdetails" );
    // Constant to indentify the requested operation
    private static  final int CONTACT_D=1;
    private static final UriMatcher uriMatcher1;

    static {

        uriMatcher1= new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher1.addURI(PROVIDER_NAME_CONTACTDETAILS,"contactdetails",CONTACT_D);
    }
    public DBData_SeeLaunch mDB1;
    @Override
    public boolean onCreate()
    {

        mDB1=new DBData_SeeLaunch(getContext()) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                super.onCreate(db);
            }
        };

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if(uriMatcher1.match(uri)==CONTACT_D)
        {return mDB1.getContactDetails();}
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowIDa=mDB1.InsertContactDetails(values)    ;
        Uri _uri=null;
        if (rowIDa>0)
        {_uri= ContentUris.withAppendedId(CONTENT_URI_CONTACTDETAILS, rowIDa);}
        else {
            try
            {throw  new SQLException("Failed to insert:"+ uri)  ;}
            catch (SQLException e)
            {e.printStackTrace();}
        }

        return _uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int cnt_a=0;
        cnt_a=mDB1.DeleteContactDetails(uri,selection,selectionArgs);
        return cnt_a;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
