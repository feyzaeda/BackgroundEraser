package com.example.backgrounderaser;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private MyCustomView myCustomView = new MyCustomView(MainActivity.this);
    private Button savedButton = findViewById(R.id.buttonSave);
    private Button undoButoon = findViewById(R.id.buttonUndo);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MyCustomView myCustomView = new MyCustomView(MainActivity.this);

        RelativeLayout rootLayout = findViewById(R.id.activity_main);
        rootLayout.addView(myCustomView);

        savedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder saveDialog = new AlertDialog.Builder(MainActivity.this);
                saveDialog.setTitle("save?");
                saveDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myCustomView.setDrawingCacheEnabled(true);
                        String imgSaved = MediaStore.Images.Media.insertImage(getContentResolver(),myCustomView.getDrawingCache(), UUID.randomUUID().toString()+".png","drawing");
                        if (imgSaved!=null){
                            Toast savedToast = Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG);
                            savedToast.show();
                        }
                        else {
                            Toast unSaved = Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG);
                            unSaved.show();
                        }
                        myCustomView.destroyDrawingCache();
                    }
                });
            }
        });

    }

    /*public void undo(View view) {
        MyCustomView myCustomView = new MyCustomView(MainActivity.this);
        myCustomView.Undo();
    }*/

}
