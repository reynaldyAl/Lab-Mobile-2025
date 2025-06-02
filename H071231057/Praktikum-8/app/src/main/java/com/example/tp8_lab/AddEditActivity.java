package com.example.tp8_lab;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

public class AddEditActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private TextInputEditText titleEditText;
    private TextInputEditText descriptionEditText;
    private TextView timestampTextView;
    private MaterialButton saveButton;
    private MaterialButton deleteButton;
    private MaterialCardView timestampCard;

    private long itemId = -1;
    private boolean isUpdateOperation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        // Setup toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        dbHelper = new DatabaseHelper(this);

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        timestampTextView = findViewById(R.id.timestampTextView);
        timestampCard = findViewById(R.id.timestampCard);
        saveButton = findViewById(R.id.saveButton);
        deleteButton = findViewById(R.id.deleteButton);

        // Check if we're updating an existing item
        if (getIntent().hasExtra("ITEM_ID")) {
            itemId = getIntent().getLongExtra("ITEM_ID", -1);
            if (itemId != -1) {
                isUpdateOperation = true;
                loadItemData(itemId);
                deleteButton.setVisibility(View.VISIBLE);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Edit Note");
                }
            } else {
                deleteButton.setVisibility(View.GONE);
                timestampCard.setVisibility(View.GONE);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Add Note");
                }
            }
        } else {
            // Adding a new item
            deleteButton.setVisibility(View.GONE);
            timestampCard.setVisibility(View.GONE);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("Add Note");
            }
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveItem();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadItemData(long id) {
        Cursor cursor = dbHelper.getDataById(id);
        if (cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TITLE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DESCRIPTION));
            String timestamp = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TIMESTAMP));

            titleEditText.setText(title);
            descriptionEditText.setText(description);
            timestampTextView.setText(timestamp);
            timestampCard.setVisibility(View.VISIBLE);
        }
        cursor.close();
    }

    private void saveItem() {
        String title = titleEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();

        if (title.isEmpty()) {
            Toast.makeText(this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isUpdateOperation) {
            // Show confirmation dialog before updating
            new AlertDialog.Builder(this)
                    .setTitle("Confirm Update")
                    .setMessage("Are you sure you want to update this note?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        dbHelper.updateData(itemId, title, description);
                        Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .setNegativeButton("No", null)
                    .show();
        } else {
            dbHelper.insertData(title, description);
            Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void deleteItem() {
        if (itemId != -1) {
            // Show confirmation dialog before deleting
            new AlertDialog.Builder(this)
                    .setTitle("Confirm Delete")
                    .setMessage("Are you sure you want to delete this note?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        dbHelper.deleteData(itemId);
                        Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }
}