package com.example.zzq.zzhuan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    public static final String TABLE_NAME="users";
    public static final String ID_FIELD="_id";
    public static final String TEL_FIELD="tel";
    public static final String NICKNAME_FIELD="nickname";
    public static final String PASSWORD_FIELD="password";
    public static final String SEX_FIELD="sex";
    public static final String ADDRESS_FIELD="address";
    public static final String IS_ADMIN_FIELD="isadmin";
    public DatabaseManager(Context context){
        super(context, "zzhuan_db2",/*cursor */null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME
                + " (" + ID_FIELD + " INTEGER PRIMARY KEY NOT NULL, "
                +TEL_FIELD + " TEXT NOT NULL,"
                +NICKNAME_FIELD + " TEXT NOT NULL,"
                +PASSWORD_FIELD+ " TEXT NOT NULL,"
                +SEX_FIELD + " INTEGER NOT NULL,"
                +ADDRESS_FIELD + " TEXT NOT NULL,"
                +IS_ADMIN_FIELD+" INTEGER DEFAULT 0 "
                +");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean isHave(long id){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor result = null;
        result = db.query("users",null,"_id=? ",new String[]{Long.toString(id)},null,null,null);
        /*result.moveToFirst();
        System.out.println("FUCKING"+id+"-"+"-"+result.getString(0)+"-"+result.getString(1)+"-"+
                result.getString(2)+"-"+result.getString(3)+"-"+result.getString(4)+"-"+result.getString(5)+"-"+
                result.getString(6));*/
        return result.getCount()!=0;
    }

    public User findById(long id){
        User user = new User();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor result = null;
        result = db.query("users",null,"_id=? ",new String[]{Long.toString(id)},null,null,null);
        result.moveToFirst();
        if(result.getCount()==0)return null;
        user.setUid(result.getLong(result.getColumnIndexOrThrow(ID_FIELD)));
        user.setTel(result.getString(result.getColumnIndexOrThrow(TEL_FIELD)));
        user.setNickname(result.getString(result.getColumnIndexOrThrow(NICKNAME_FIELD)));
        user.setPassword(result.getString(result.getColumnIndexOrThrow(PASSWORD_FIELD)));
        user.setSex(result.getInt(result.getColumnIndexOrThrow(SEX_FIELD)));
        user.setAddress(result.getString(result.getColumnIndexOrThrow(ADDRESS_FIELD)));
        user.setIsadmin(result.getInt(result.getColumnIndexOrThrow(IS_ADMIN_FIELD)));
        return user;
    }

    public boolean checkLogin(long uid,String password){
        User user = findById(uid);
        return password.equals(user.getPassword());
    }

    public long addUser(User user){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id",user.getUid());
        values.put("password",user.getPassword());
        values.put("tel",user.getTel());
        values.put("sex",user.getSex());
        values.put("address",user.getAddress());
        values.put("nickname",user.getNickname());
        values.put("isadmin",user.getIsadmin());
        long retid = db.insert("users",null,values);
        db.close();
        return retid;
    }

    public int delUser(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_NAME,ID_FIELD+" = ?",new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }

    public int updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id",user.getUid());
        values.put("password",user.getPassword());
        values.put("tel",user.getTel());
        values.put("sex",user.getSex());
        values.put("address",user.getAddress());
        values.put("nickname",user.getNickname());
        values.put("isadmin",user.getIsadmin());
        return db.update(TABLE_NAME,values,ID_FIELD+" = ?",new String[]{String.valueOf(user.getUid())});
    }

}
