package com.gapxventuraplaza.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.gapxventuraplaza.domain.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laynegranadosmogollon on 21/10/15.
 */
public class UsuarioDataSource {

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = { SQLiteHelper.COLUMN_USER_ID,
                                    SQLiteHelper.COLUMN_USER_LOGIN,
                                    SQLiteHelper.COLUMN_USER_PASSWORD,
                                    SQLiteHelper.COLUMN_USER_ROLDB,
                                    SQLiteHelper.COLUMN_USER_ACTIVO};

    public UsuarioDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Usuario createUsuario(Integer id, String login, String password, String roldb, Integer activo) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_USER_ID, id);
        values.put(SQLiteHelper.COLUMN_USER_LOGIN, login);
        values.put(SQLiteHelper.COLUMN_USER_PASSWORD, password);
        values.put(SQLiteHelper.COLUMN_USER_ROLDB, roldb);
        values.put(SQLiteHelper.COLUMN_USER_ACTIVO, activo);

        long insertId = database.insert(SQLiteHelper.TABLE_USER, null, values);

        Cursor cursor = database.query(SQLiteHelper.TABLE_USER,
                allColumns, SQLiteHelper.COLUMN_USER_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Usuario user = cursorToUsuario(cursor);
        cursor.close();
        return user;
    }

    public void deleteUsuario(int idUsuario) {
        database.delete(SQLiteHelper.TABLE_USER, SQLiteHelper.COLUMN_USER_ID + " = " + idUsuario, null);
    }

    public List<Usuario> getAllUsers() {
        List<Usuario> comments = new ArrayList<Usuario>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_USER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Usuario usuario = cursorToUsuario(cursor);
            comments.add(usuario);
            cursor.moveToNext();
        }
        cursor.close();
        return comments;
    }

    private Usuario cursorToUsuario(Cursor cursor) {
        Usuario usuario = new Usuario();
        usuario.setId(cursor.getInt(0));
        usuario.setLogin(cursor.getString(1));
        usuario.setPassword(cursor.getString(2));
        usuario.setRoldb(cursor.getString(3));
        usuario.setActivo(cursor.getInt(4));
        return usuario;
    }

}
