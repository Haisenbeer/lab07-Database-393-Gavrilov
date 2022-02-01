package com.example.lab07_database_393_gavrilov;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txt_key;
    EditText txt_value;

    DB mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_key = findViewById(R.id.insert_key);
        txt_value = findViewById(R.id.insert_value);

        mydb = new DB(this, "mybase.db", null, 1);
    }

    public void on_Select_Click(View v)
    {
        String key = txt_key.getText().toString();
        txt_value.setText(mydb.doSelect(key));
    }

    public void on_Insert_Click(View v)
    {
        String key = txt_key.getText().toString();

        if (mydb.doSelect(key) != "not found!") {
            Toast.makeText(this, "This key is already there", Toast.LENGTH_SHORT).show();
            return;
        }

        String value = txt_value.getText().toString();

        mydb.doInsert(key, value);
    }

    public void on_Update_Click(View v)
    {
        String key = txt_key.getText().toString();

        if (mydb.doSelect(key) == "not found!")
        {
            Toast.makeText(this, "Key does not exist", Toast.LENGTH_SHORT).show();
            return;
        }

        String value = txt_value.getText().toString();

        mydb.doUpgrade(key, value);
    }

    public void on_Delete_Click(View v)
    {
        String key = txt_key.getText().toString();

        if (mydb.doSelect(key) == "not found!")
        {
            Toast.makeText(this, "Key does not exist", Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to delete this key?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mydb.doDelete(key);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
    }
}