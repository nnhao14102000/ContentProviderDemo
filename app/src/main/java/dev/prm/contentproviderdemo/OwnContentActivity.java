package dev.prm.contentproviderdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dev.prm.provider.BooksProvider;

public class OwnContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_content);
    }

    public void clickToAddTitle(View view) {
        EditText txtTitle = findViewById(R.id.edtTitle);
        EditText txtIsbn = findViewById(R.id.edtISBN);

        ContentValues values = new ContentValues();
        values.put(BooksProvider.KEY_TITLE, txtTitle.getText().toString());
        values.put(BooksProvider.KEY_ISBN, txtIsbn.getText().toString());

        Uri uri = getContentResolver().insert(BooksProvider.CONTENT_URI, values);
        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void clickToUpdateTitle(View view) {
        EditText txtTitle = findViewById(R.id.edtTitle);
        EditText txtIsbn = findViewById(R.id.edtISBN);

        ContentValues values = new ContentValues();
        values.put(BooksProvider.KEY_TITLE, txtTitle.getText().toString());
        values.put(BooksProvider.KEY_ISBN, txtIsbn.getText().toString());
        int i = getContentResolver().update(BooksProvider.CONTENT_URI, values, "isbn = ?", new String[]{txtIsbn.getText().toString()});

    }

    public void clickToDeleteTitle(View view) {
        EditText editId = findViewById(R.id.editId);
        ContentValues values = new ContentValues();
        String id = editId.getText().toString();
        if (!id.isEmpty()) {
            values.put(BooksProvider.KEY_ID, id);
            int i = getContentResolver().delete(BooksProvider.CONTENT_URI, "_id = ?", new String[]{id});
            System.out.println(i);
        }
    }

    public void clickToRetrieveTitle(View view) {
        Uri uri = BooksProvider.CONTENT_URI;

        Cursor c = null;
        CursorLoader loader = new CursorLoader(this,
                uri, null, null, null, BooksProvider.KEY_TITLE + " desc");

        c = loader.loadInBackground();

        if(c.moveToFirst()){
            String result = "All Retrieved Title:\n";
            do{
                int id = c.getColumnIndex(BooksProvider.KEY_ID);
                int title = c.getColumnIndex(BooksProvider.KEY_TITLE);
                int isbn = c.getColumnIndex(BooksProvider.KEY_ISBN);


                result += c.getString(id) + " - " +
                        c.getString(title) + " - " +
                        c.getString(isbn) + "\n";

            }while (c.moveToNext());
            TextView txt = findViewById(R.id.txtResult);
            txt.setText(result);
        }
    }

    public void clickToViewTitle(View view) {
        Uri uri = BooksProvider.CONTENT_URI;
        EditText editId = findViewById(R.id.editId);
        String idStr = editId.getText().toString();

        Cursor c = null;
        CursorLoader loader = new CursorLoader(this,
                uri, null, "_id = ?", new String[] {idStr}, BooksProvider.KEY_TITLE + " desc");

        TextView txt = findViewById(R.id.txtResult);
        c = loader.loadInBackground();
        if(c.moveToFirst()){
            String result = "All Title with ID: " + idStr + "\n";
            do{
                int id = c.getColumnIndex(BooksProvider.KEY_ID);
                int title = c.getColumnIndex(BooksProvider.KEY_TITLE);
                int isbn = c.getColumnIndex(BooksProvider.KEY_ISBN);


                result += c.getString(id) + " - " +
                        c.getString(title) + " - " +
                        c.getString(isbn) + "\n";

            }while (c.moveToNext());
            txt.setText(result);
        } else {
            txt.setText("No record found");
        }
    }
}