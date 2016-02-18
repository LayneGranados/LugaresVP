package com.gapxventuraplaza.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by laynegranadosmogollon on 20/10/15.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    /*         TABLA USUARIO     */

    public static final String TABLE_USER = "usuario";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_LOGIN = "login";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_ROLDB = "roldb";
    public static final String COLUMN_USER_ACTIVO = "activo";

    /*          TABLA SUPERVISION    */
    /*
    *   idlugar
        usuario
        idactividad
        nombrecalificacion
        fecha
    */

    public static final String TABLE_SUPERVISIONGUARDAR = "supervisionguardar";
    public static final String COLUMN_SUPERVISION_ID = "supervision_id";
    public static final String COLUMN_LUGAR_ID = "lugar_id";
    public static final String COLUMN_USUARIO_ID = "usuario_id";
    public static final String COLUMN_ACTIVIDAD_ID = "actividad_id";
    public static final String COLUMN_NOMBRE_CALIFICACION = "nombre_calificacion";
    public static final String COLUMN_FECHA = "fecha";

    private static final String DATABASE_NAME = "vyv.db";
    private static final int DATABASE_VERSION = 3;

    // Database creation sql statement
    private static final String TABLE_USER_CREATE = "create table " + TABLE_USER
                                                + "("
                                                + COLUMN_USER_ID + " integer primary key, "
                                                + COLUMN_USER_LOGIN + " text, "
                                                + COLUMN_USER_PASSWORD + " text, "
                                                + COLUMN_USER_ROLDB + " text, "
                                                + COLUMN_USER_ACTIVO + " integer"
                                                + ");";

    private static final String TABLE_EVENT_CREATE = "create table " + TABLE_SUPERVISIONGUARDAR
                                                + "("
                                                + COLUMN_SUPERVISION_ID + " integer primary key AUTOINCREMENT, "
                                                + COLUMN_LUGAR_ID + " integer, "
                                                + COLUMN_USUARIO_ID + " text, "
                                                + COLUMN_ACTIVIDAD_ID + " integer, "
                                                + COLUMN_NOMBRE_CALIFICACION + " text, "
                                                + COLUMN_FECHA + " text"
                                                + ");";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USER_CREATE);
        db.execSQL(TABLE_EVENT_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUPERVISIONGUARDAR);
        onCreate(db);
    }
}
