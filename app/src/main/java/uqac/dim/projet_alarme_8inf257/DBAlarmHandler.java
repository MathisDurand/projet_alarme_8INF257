package uqac.dim.projet_alarme_8inf257;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class DBAlarmHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    private final Context myContext;
    private SQLiteDatabase myDataBase;

    private static final String DB_NAME = "alarmDB.db";
    private static final String DB_PATH = "/data/data/uqac.dim.projet_alarme_8inf257/databases/";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME_SAVED_ALARMS = "savedAlarms";
    private static final String ID_SAVED_ALARMS = "id";
    private static final String HOUR_SAVED_ALARMS = "hourSaved";
    private static final String ID_MINIGAME_SAVED_ALARMS = "idMiniGame";
    private static final String ID_RINGTONE_SAVED_ALARMS = "idRingtone";

    private static final String TABLE_NAME_MINIGAMES = "savedAlarms";
    private static final String ID_MINIGAMES = "id";
    private static final String NAME_MINIGAMES = "name";

    private static final String TABLE_NAME_RINGTONE = "savedAlarms";
    private static final String ID_RINGTONE = "id";
    private static final String NAME_RINGTONE = "name";


    // creating a constructor for our database handler.
    public DBAlarmHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.myContext = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.disableWriteAheadLogging();
        }
    }

    private boolean checkDataBase(){

        Log.v("DIM", "checkDataBase");

        boolean checkDB = false;

        try{
            String myPath = DB_PATH + DB_NAME;
            Log.v("DIM", "myPath : " + myPath);

            File dbfile = new File(myPath);
            Log.v("DIM", "dbfile : " + dbfile);
            checkDB = dbfile.exists();
            Log.v("DIM", "checkDB : " + checkDB);
        }
        catch(SQLiteException e){
            Log.v("DIM", "PAS SUPPOSE PASSER ICI JAMAIS");
        }
        return checkDB;
    }

    public void createDatabase() throws IOException {

        Log.v("DIM", "*********createDatabase***********");

        boolean dbExist = checkDataBase();

        if(dbExist) {
            Log.v("DIM", "LA BD EXISTE");
            // By calling this method here onUpgrade will be called on a
            // writeable database, but only if the version number has been
            // bumped
            //onUpgrade(myDataBase, DATABASE_VERSION_old, DATABASE_VERSION);
        }
        else{
            Log.v("DIM", "LA BD N'EXISTE PAS");

            try {
                this.getReadableDatabase();
                Log.v("DIM", "close");
                this.close();
                Log.v("DIM", "copydatabase");
                copyDataBase();
                Log.v("DIM", "copie effectue avec succes");
            }
            catch (IOException e){
                throw new Error("Error copying database");
            }
        }
    }

    private void copyDataBase() throws IOException {

        String outFileName = DB_PATH + DB_NAME;

        Log.v("DIM", "outFileName : " + outFileName);
        Log.v("DIM", "inFileName : " + DB_NAME);

        OutputStream myOutput = new FileOutputStream(outFileName);
        Log.v("DIM", "inFileName : YO");
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        Log.v("DIM", "myInput : " + myInput);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            String test = "";
            for (byte b:buffer)
                test += (char)b;
            Log.v("DIM", test);

            myOutput.write(buffer, 0, length);
        }
        myInput.close();
        myOutput.flush();
        myOutput.close();
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {}

    // this method is use to add new course to our sqlite database.
    public void addNewAlarm(String hour, int minigameID, int ringtoneID) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(HOUR_SAVED_ALARMS, hour);
        values.put(ID_MINIGAME_SAVED_ALARMS, minigameID);
        values.put(ID_RINGTONE_SAVED_ALARMS, ringtoneID);

        db.insert(TABLE_NAME_SAVED_ALARMS, null, values);

        db.close();
    }

    public void addNewMinigame(String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME_MINIGAMES, name);

        db.insert(TABLE_NAME_MINIGAMES, null, values);

        db.close();
    }

    public void addNewRingtone(String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME_RINGTONE, name);

        db.insert(TABLE_NAME_RINGTONE, null, values);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        /*db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MINIGAMES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_RINGTONE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SAVED_ALARMS);
        onCreate(db);*/
        if(newVersion>oldVersion){
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void openDatabase() throws SQLException {

        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public String getDataDeLaBD(){

        StringBuilder sb = new StringBuilder();

        Log.v("DIM", "isOpen : " + myDataBase.isOpen());
        Log.v("DIM", "isReadOnly : " + myDataBase.isReadOnly());
        Log.v("DIM", "NAME : " + myDataBase.getPath());

        Cursor cursor = myDataBase.rawQuery("select * from savedAlarms",null);

        Log.v("DIM", "MyDataBase : " + myDataBase.getPath() + " */* " + myDataBase.toString());

        String nomColonnes[] = cursor.getColumnNames();

        Log.v("DIM", "Nombre Colonnes : " + nomColonnes.length);

        for (String nomColonne : nomColonnes)
            Log.v("DIM", "NOM DE COLONNE : " + nomColonne);

        Log.v("DIM", "cursor.getCount() : " + cursor.getCount());

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                String id = cursor.getString(0);
                String nom = cursor.getString(1);
                String niveau = cursor.getString(2);

                sb.append("\n").append(id).append("\n").append(nom).append("\n").append(niveau);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return sb.toString();
    }
}