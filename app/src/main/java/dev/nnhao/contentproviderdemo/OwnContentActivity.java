package dev.nnhao.contentproviderdemo;

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

import dev.nnhao.provider.BooksProvider;

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

    public void clickToRetrieveTitle(View view) {
        Uri allTitle = BooksProvider.CONTENT_URI;

        Cursor c = null;
        CursorLoader loader = new CursorLoader(this,
                allTitle, null, null, null, BooksProvider.KEY_TITLE + " desc");
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
}