package seelaunch.seelaunch;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
public class DBData_SeeLaunch extends SQLiteOpenHelper {


    private static String db_name="LocationsDB";
    private static int version=1;
    private static SQLiteDatabase DB;
    private static SQLiteDatabase DBB;
    public static String DATABASE_TABLE_CONTACT_DETAILS="contactdetails";
    public static String FIELD_CD_ID="id_cd";
    public static String FIELD_NAME="name";
    public static String FIELD_PHONENUMBER="phonenumber";
    public static Cursor MyCursor;
    public static ArrayList<String> ArrayList_Name;
    public static ArrayList<String> ArrayList_PhoneNumber;
    private static String Dummy_Name;
    private static String Dummy_PhoneNumber;


    public static String DATABASE_TABLE_CONTACT_DETAILS_ForPhoneCalls="contactdetailsforphonecalls";
    public static String FIELD_CD_Call_ID="id_cdcall";
    public static String FIELD_NAME_Call="name_call";
    public static String FIELD_PHONENUMBER_Call="phonenumber_call";
    public static Cursor MyCursor_Call;
    public static ArrayList<String> ArrayList_Name_Call;
    public static ArrayList<String> ArrayList_PhoneNumber_Call;
    private static String Dummy_Name_Call;
    private static String Dummy_PhoneNumber_Call;



    public DBData_SeeLaunch(Context context) {
        super(context, db_name, null, version);
           this.DBB=getReadableDatabase();
           this.DB=getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String Create_SQL_ContactDetails=" CREATE TABLE " + DBData_SeeLaunch.DATABASE_TABLE_CONTACT_DETAILS + "(" + DBData_SeeLaunch.FIELD_CD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DBData_SeeLaunch.FIELD_NAME
                + " TEXT," + DBData_SeeLaunch.FIELD_PHONENUMBER + " TEXT " + ")";
        String Create_SQL_ContactDetails_PhoneCall=" CREATE TABLE " + DBData_SeeLaunch.DATABASE_TABLE_CONTACT_DETAILS_ForPhoneCalls + "(" + DBData_SeeLaunch.FIELD_CD_Call_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DBData_SeeLaunch.FIELD_NAME_Call
                + " TEXT," + DBData_SeeLaunch.FIELD_PHONENUMBER_Call + " TEXT " + ")";

        db.execSQL(Create_SQL_ContactDetails);
        db.execSQL(Create_SQL_ContactDetails_PhoneCall);

    }

    public long InsertContactDetails(ContentValues  contentValues)
    {
        long rowID= DB.insert(DATABASE_TABLE_CONTACT_DETAILS, null, contentValues);
        return  rowID;
    }

    public  int DeleteContactDetails(Uri uri, String selection, String[] selectionArgs)
    {
        int cnt=DB.delete(DATABASE_TABLE_CONTACT_DETAILS, selection, selectionArgs);
        return cnt;
    }
    public Cursor getContactDetails()
    {
        return  DB.query(DATABASE_TABLE_CONTACT_DETAILS, new String[]{FIELD_CD_ID, FIELD_NAME, FIELD_PHONENUMBER}, null,null,null,null,null);
    }

    public static Cursor getCursorData()
    {
        String[] allfields = {DBData_SeeLaunch.FIELD_CD_ID,DBData_SeeLaunch.FIELD_NAME, DBData_SeeLaunch.FIELD_PHONENUMBER};
        MyCursor = DBB.query(DBData_SeeLaunch.DATABASE_TABLE_CONTACT_DETAILS, allfields, null, null, null, null, null);
        if (MyCursor!=null)
            MyCursor.moveToFirst();
        return MyCursor;
    }

    public static void LoopingData()

    {
         ArrayList_Name= new ArrayList<String>();
         ArrayList_PhoneNumber= new ArrayList<String>();

        for (MyCursor.moveToFirst(); !MyCursor.isAfterLast(); MyCursor.moveToNext())
        {
            Dummy_Name = MyCursor.getString(MyCursor.getColumnIndex(DBData_SeeLaunch.FIELD_NAME));
            Dummy_PhoneNumber = MyCursor.getString(MyCursor.getColumnIndex(DBData_SeeLaunch.FIELD_PHONENUMBER));
            ArrayList_Name.add(Dummy_Name);
            ArrayList_PhoneNumber.add(Dummy_PhoneNumber);
        }
        MyCursor.close();
    }






    public long InsertContactDetailsPhoneCalls(ContentValues  contentValues)
    {
        long rowID= DB.insert(DATABASE_TABLE_CONTACT_DETAILS_ForPhoneCalls, null, contentValues);
        return  rowID;
    }

    public  int DeleteContactDetailsPhoneCalls(Uri uri, String selection, String[] selectionArgs)
    {
        int cnt=DB.delete(DATABASE_TABLE_CONTACT_DETAILS_ForPhoneCalls, selection, selectionArgs);
        return cnt;
    }
    public Cursor getContactDetailsPhoneCalls()
    {
        return  DB.query(DATABASE_TABLE_CONTACT_DETAILS_ForPhoneCalls, new String[]{FIELD_CD_Call_ID, FIELD_NAME_Call, FIELD_PHONENUMBER_Call}, null,null,null,null,null);
    }

    public static Cursor getCursorDataPhoneCalls()
    {
        String[] allfields = {DBData_SeeLaunch.FIELD_CD_Call_ID,DBData_SeeLaunch.FIELD_NAME_Call, DBData_SeeLaunch.FIELD_PHONENUMBER_Call};
        MyCursor_Call = DBB.query(DBData_SeeLaunch.DATABASE_TABLE_CONTACT_DETAILS_ForPhoneCalls, allfields, null, null, null, null, null);
        if (MyCursor_Call!=null)
            MyCursor_Call.moveToFirst();
        return MyCursor_Call;
    }

    public static void LoopingDataPhoneCall()

    {
        ArrayList_Name_Call= new ArrayList<String>();
        ArrayList_PhoneNumber_Call= new ArrayList<String>();

        for (MyCursor_Call.moveToFirst(); !MyCursor_Call.isAfterLast(); MyCursor_Call.moveToNext())
        {
            Dummy_Name_Call = MyCursor_Call.getString(MyCursor_Call.getColumnIndex(DBData_SeeLaunch.FIELD_NAME_Call));
            Dummy_PhoneNumber_Call = MyCursor_Call.getString(MyCursor_Call.getColumnIndex(DBData_SeeLaunch.FIELD_PHONENUMBER_Call));
            ArrayList_Name_Call.add(Dummy_Name_Call);
            ArrayList_PhoneNumber_Call.add(Dummy_PhoneNumber_Call);
        }
        MyCursor_Call.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }
}
