package com.denofdevelopers.blogdbmanager_parttwo;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper dataBase;
    @BindView(R.id.editName)
    EditText editName;
    @BindView(R.id.editLastName)
    EditText editLastName;
    @BindView(R.id.editDateOfBirth)
    EditText editDateOfBirth;
    @BindView(R.id.editGender)
    EditText editGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dataBase = new DataBaseHelper(this);
    }

    @OnClick(R.id.addButton)
    public void onAddClick() {
        if (!TextUtils.isEmpty(editName.getText().toString())
                && !TextUtils.isEmpty(editLastName.getText().toString())
                && !TextUtils.isEmpty(editDateOfBirth.getText().toString())
                && !TextUtils.isEmpty(editGender.getText().toString())) {
            boolean isDataInserted = dataBase.insertData(editName.getText().toString(), editLastName.getText().toString(), editDateOfBirth.getText().toString(), editGender.getText().toString());
            if (isDataInserted) {
                Toast.makeText(this, "Data inserted to database", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Data NOT inserted!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.viewAll)
    public void onViewAllClick() {
        Cursor data = dataBase.getAllData();
        if (data.getCount() == 0) {
            showDialogWithDbData("Error", "No data found");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            while (data.moveToNext()) {
                stringBuilder.append("ID: ").append(data.getString(0)).append("\n");
                stringBuilder.append("NAME: ").append(data.getString(1)).append("\n");
                stringBuilder.append("LAST NAME: ").append(data.getString(2)).append("\n");
                stringBuilder.append("DATE OF BIRTH: ").append(data.getString(3)).append("\n\n");
            }
            showDialogWithDbData( "Data", stringBuilder.toString());
        }
    }

    public void showDialogWithDbData(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @OnClick(R.id.update)
    public void onUpdateClick() {
        
    }
}
