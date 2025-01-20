package gal.cifpacarballeira.unidad4_tarea7gestordeberes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class Crud {

    public static final String TABLE_NAME= "homework";

    public void createHomework(Homework homework, SQLiteDatabase bdWrite){
        if (bdWrite == null) {
            Log.e("Crud", "bdWrite is null");
            return;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put("subject", homework.getSubject());
        contentValues.put("description", homework.getDescription());
        contentValues.put("isCompleted", homework.isCompleted() ? 1 : 0);
        contentValues.put("duedate", homework.getDueDate());


        bdWrite.insert("homework", null, contentValues);

        bdWrite.close();
    }

    public ArrayList<Homework> readHomework(SQLiteDatabase bdRead){
        Cursor cursor = bdRead.rawQuery("SELECT * from " + TABLE_NAME, null);
        ArrayList<Homework> arrayList = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                arrayList.add(new Homework(cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5)== 1? true: false));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }

    public void updateHomework(Homework homework, SQLiteDatabase bdWrite){
        ContentValues contentValues = new ContentValues();
        contentValues.put("subject", homework.getSubject());
        contentValues.put("description", homework.getDescription());
        contentValues.put("duedate", homework.getDueDate());
        contentValues.put("isCompleted", homework.isCompleted() ? 1 : 0);

        String[] id = { String.valueOf(homework.getId()) };

        bdWrite.update("homework", contentValues, "id =?", id);

        bdWrite.close();
    }

    public void deleteHomework(Homework homework, SQLiteDatabase bdWrite){
        String[] id = { String.valueOf(homework.getId()) };
        bdWrite.delete("homework", "id=?", id);

        bdWrite.close();
    }
}
