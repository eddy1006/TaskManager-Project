package com.example.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class myDbHandler extends SQLiteOpenHelper {
    public myDbHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + "Tasks" + "("
                + "id" + " INTEGER PRIMARY KEY," + "title"
                + " TEXT, " + "description" + " TEXT" + ")";
        Log.d("mydb","Query being run"+create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = String.valueOf("DROP TABLE IF EXISTS");
        db.execSQL(drop,new String[] {"Tasks"});
        onCreate(db);
    }
    public void addTask(mytasks task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",task.getTitle());
        values.put("description",task.getDescription());
        db.insert("Tasks",null,values);
        Log.d("mydb","successfully added ");
        db.close();
    }
    public List<mytasks> getAllTasks(){
        List<mytasks> tasks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM "+"Tasks";
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()){
            do {
                mytasks task = new mytasks();
                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setTitle(cursor.getString(1));
                task.setDescription(cursor.getString(2));
                tasks.add(task);
            }while (cursor.moveToNext());
        }
        return tasks;
    }
    public int updateTask(mytasks task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",task.getTitle());
        values.put("description",task.getDescription());
        return  db.update("Tasks",values,"id"+"=?",new String[]{String.valueOf(task.getId())});
    }
    public void deleteTask(mytasks task)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Tasks","id"+"=?",new String[]{String.valueOf(task.getId())});
        db.close();
    }

}
