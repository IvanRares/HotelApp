package com.example.hotelapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.hotelapp.entities.Image;
import com.example.hotelapp.entities.Room;
import com.example.hotelapp.entities.RoomType;
import com.example.hotelapp.pojos.ImageAndRoomType;
import com.example.hotelapp.pojos.RoomAndRoomTypes;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class EditImageActivity extends AppCompatActivity {

    EditText imageName;
    Spinner roomTypes;
    ImageView imageData;
    Button saveButton;
    Button deleteButton;
    Button uploadImageButton;
    ImageAndRoomType item;
    List<RoomType> roomTypeList;
    AppDatabase db;
    String option;
    int imageId;
    final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK
                        && result.getData() != null) {
                    Uri photoUri = result.getData().getData();
                    Bitmap bitmap;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
                        imageData.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Edit Images");
        actionBar.setDisplayHomeAsUpEnabled(true);
        option = getIntent().getExtras().getString("Option");
        db = AppDatabase.getInstance(getApplicationContext());
        roomTypeList = db.roomTypeDao().getAllRoomTypes();
        imageName = findViewById(R.id.edit_image_roomName);
        roomTypes = findViewById(R.id.edit_image_roomTypes);
        saveButton = findViewById(R.id.edit_image_saveButton);
        deleteButton = findViewById(R.id.edit_image_deleteButton);
        uploadImageButton = findViewById(R.id.edit_image_selectImage);
        imageData = findViewById(R.id.edit_image_imageData);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_room_type_item, R.id.spinner_room_type_item_roomTypeName, roomTypeList.stream().map(RoomType::getRoomTypeName).collect(Collectors.toList()));
        roomTypes.setAdapter(adapter);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Delete();
            }
        });
        uploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImage();
            }
        });

        if (option.equals("Edit")) {
            imageId = getIntent().getExtras().getInt("imageId");
            item = db.imageDao().getImageById(imageId);
            imageName.setText(item.image.getImageName());
            int pos = adapter.getPosition(item.roomType.getRoomTypeName());
            roomTypes.setSelection(pos);
            System.out.println(imageId);
            System.out.println(item.image.getImageData().length);
            imageData.setImageBitmap(setImageViewWithByteArray(item.image.getImageData()));
            deleteButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void Save() {
        if (!isEmpty()) {
            if (option.equals("Edit")) {
                item.image.setImageName(imageName.getText().toString());
                String selectedRoomType = roomTypes.getSelectedItem().toString();
                int roomTypeId = roomTypeList.stream().filter(x -> x.getRoomTypeName().equals(selectedRoomType)).findFirst().get().getRoomTypeId();
                item.image.setRoomTypeId(roomTypeId);
                item.image.setImageData(imageViewToByteArray());
                db.imageDao().updateImage(item.image);
            } else {
                String selectedRoomType = roomTypes.getSelectedItem().toString();
                int roomTypeId = roomTypeList.stream().filter(x -> x.getRoomTypeName().equals(selectedRoomType)).findFirst().get().getRoomTypeId();
                byte[] imageArray = imageViewToByteArray();
                db.imageDao().insertImage(new Image(roomTypeId, imageName.getText().toString(), imageArray));
            }
            finish();
        }
    }

    private void Delete() {
        item.image.setActive(false);
        db.imageDao().updateImage(item.image);
        finish();
    }

    private void UploadImage() {
        Intent i = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        launcher.launch(i);
    }

    private boolean isEmpty() {
        return (imageName.getText().toString().isEmpty() || imageData.getDrawable() == null);
    }

    private byte[] imageViewToByteArray() {
        Bitmap bitmap = ((BitmapDrawable) imageData.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public Bitmap setImageViewWithByteArray(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

}