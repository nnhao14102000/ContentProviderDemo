package dev.nnhao.contentproviderdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import dev.nnhao.utils.ToolUtilities;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickToOpenContact(View view) {
        Intent intent = new Intent(this, ContactContentActivity.class);
        startActivity(intent);
    }

    public void clickToCopy(View view) {
        String desDir = "MyDBs";
        String srcDir = "/data/" + this.getPackageName() + "/databases";
        String result = ToolUtilities.copyFileFromDataDirToSDCard(desDir, srcDir);

        TextView txt = findViewById(R.id.txtFileList);
        txt.setText(result);
    }

    public void clickToOpenOwn(View view) {
        Intent intent = new Intent(this, OwnContentActivity.class);
        startActivity(intent);
    }
}