package com.gapxventuraplaza.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import com.gapxventuraplaza.domain.Supervision;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laygrana on 30/10/15.
 */
public class SupervisionDataSource {

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
                                    SQLiteHelper.COLUMN_SUPERVISION_ID,
                                    SQLiteHelper.COLUMN_LUGAR_ID,
                                    SQLiteHelper.COLUMN_EMPLEADO_ID,
                                    SQLiteHelper.COLUMN_FECHA
                                  };

    public SupervisionDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Supervision createSupervision(Integer lugar, Integer empleado, String fecha) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_LUGAR_ID, lugar);
        values.put(SQLiteHelper.COLUMN_EMPLEADO_ID, empleado);
        values.put(SQLiteHelper.COLUMN_FECHA, fecha);

        long insertId = database.insert(SQLiteHelper.TABLE_SUPERVISIONGUARDAR, null, values);
        return null;
    }

    public void deleteSupervision(Integer id) {
        database.delete(SQLiteHelper.TABLE_SUPERVISIONGUARDAR,
                SQLiteHelper.COLUMN_SUPERVISION_ID+ " = " + id, null);
    }

    public List<Supervision> getAllEvents() {
        List<Supervision> comments = new ArrayList<Supervision>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_SUPERVISIONGUARDAR,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Supervision supervision = cursorToSupervision(cursor);
            comments.add(supervision);
            cursor.moveToNext();
        }
        cursor.close();
        return comments;
    }

    private Supervision cursorToSupervision(Cursor cursor) {
        Supervision supervision = new Supervision();
        supervision.setId(cursor.getInt(0));
        supervision.setLugar(cursor.getInt(1));
        supervision.setSupervisor(cursor.getInt(2));
        supervision.setFecha(cursor.getString(3));
        return supervision;
    }
}
