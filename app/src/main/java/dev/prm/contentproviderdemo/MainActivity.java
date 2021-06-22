package dev.prm.contentproviderdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import dev.prm.utils.ToolUtilities;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS},1);
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_CONTACTS}, 1);
    }

    public void clickToOpenContact(View view) {
        intent = new Intent(MainActivity.this, ContactContentActivity.class);
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
        intent = new Intent(MainActivity.this, OwnContentActivity.class);
        startActivity(intent);
    }
}