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

/**
 * Created by Fusion on 02-Aug-16.
 */
public class ContentProviderClassForPhoneCallDetails extends ContentProvider {
    public  static  final String PROVIDER_NAME_CONTACTDETAILS_PhoneCall="seelaunch.seelaunch.contactdetailsforphonecalls";
    /*  Every provider will be identified by its URI*/
    public static final Uri CONTENT_URI_CONTACTDETAILS_PhoneCall = Uri.parse("content://" + PROVIDER_NAME_CONTACTDETAILS_PhoneCall + "/contactdetailsforphonecalls" );
    // Constant to indentify the requested operation
    private static  final int CONTACT_D_Call=1;
    private static final UriMatcher uriMatcher2;

    static {

        uriMatcher2= new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher2.addURI(PROVIDER_NAME_CONTACTDETAILS_PhoneCall,"contactdetailsforphonecalls",CONTACT_D_Call);
    }
    public DBData_SeeLaunch mDB2;
    @Override
    public boolean onCreate()
    {

        mDB2=new DBData_SeeLaunch(getContext()) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                super.onCreate(db);
            }
        };

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if(uriMatcher2.match(uri)==CONTACT_D_Call)
        {return mDB2.getContactDetailsPhoneCalls();}
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowIDa=mDB2.InsertContactDetailsPhoneCalls(
                values)    ;
        Uri _uri=null;
        if (rowIDa>0)
        {_uri= ContentUris.withAppendedId(CONTENT_URI_CONTACTDETAILS_PhoneCall, rowIDa);}
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
        cnt_a=mDB2.DeleteContactDetailsPhoneCalls(uri,selection,selectionArgs);
        return cnt_a;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
