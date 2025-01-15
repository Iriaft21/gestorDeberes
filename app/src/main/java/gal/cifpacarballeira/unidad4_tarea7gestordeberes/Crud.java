package gal.cifpacarballeira.unidad4_tarea7gestordeberes;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class Crud {

    public void createHomework(String subject, String description, String duedate, boolean isCompleted, SQLiteDatabase bdWrite){
        ContentValues contentValues = new ContentValues();
        contentValues.put("subject", subject);
        contentValues.put("description", description);
        contentValues.put("duedate", duedate);
        contentValues.put("isCompleted", isCompleted ? 1 : 0);

        bdWrite.insert("homework", null, contentValues);

        bdWrite.close();
    }

    public void updateHomework(){

    }

    public void deleteHomework(){

    }
}
