package gal.cifpacarballeira.unidad4_tarea7gestordeberes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Crud {

    public static final String TABLE_NAME= "homework";

    public void createHomework(Homework homework, SQLiteDatabase bdWrite){
        if (bdWrite == null || !bdWrite.isOpen()) {
            Log.e("Crud", "bdWrite is null or closed");
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("subject", homework.getSubject());
        contentValues.put("description", homework.getDescription());
        contentValues.put("isCompleted", homework.isCompleted() ? 1 : 0);
        contentValues.put("duedate", homework.getDueDate());

        bdWrite.insert("homework", null, contentValues);
    }

    public List<Homework> readHomework(SQLiteDatabase bdRead){
        if (bdRead == null || !bdRead.isOpen()) {
            Log.e("Crud", "bdRead is null or closed");
            return new ArrayList<>();
        }
        Cursor cursor = bdRead.rawQuery("SELECT * from " + TABLE_NAME, null);
        List<Homework> homework = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                homework.add(new Homework(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4) == 1));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return homework;
    }

    public void updateHomework(Homework homework, SQLiteDatabase bdWrite){
        if (bdWrite == null || !bdWrite.isOpen()) {
            Log.e("Crud", "bdWrite is null or closed");
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("subject", homework.getSubject());
        contentValues.put("description", homework.getDescription());
        contentValues.put("duedate", homework.getDueDate());
        contentValues.put("isCompleted", homework.isCompleted() ? 1 : 0);

        String[] id = { String.valueOf(homework.getId()) };

        bdWrite.update("homework", contentValues, "id=?", id);
    }

    public void deleteHomework(Homework homework, SQLiteDatabase bdWrite){
        if (bdWrite == null || !bdWrite.isOpen()) {
            Log.e("Crud", "bdWrite is null or closed");
            return;
        }
        String[] id = { String.valueOf(homework.getId()) };
        bdWrite.delete("homework", "id=?", id);
    }
}
