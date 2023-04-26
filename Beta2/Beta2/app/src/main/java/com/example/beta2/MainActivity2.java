package com.example.beta2;

import android.content.Intent;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private final int GALLERY_REQ_CODE = 1000;
    ImageView imgGallery;

    private String selectedblock, selectedcampus;                 //vars to hold the values of selected State and District
    private TextView tvcampusSpinner, tvblockSpinner;             //declaring TextView to show the errors
    private Spinner campusSpinner, blockSpinner;                  //Spinners
    private ArrayAdapter<CharSequence> stateAdapter, districtAdapter;   //declare adapters for the spinners
    private EditText NameText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imgGallery = findViewById(R.id.imgGallery);
        Button btnGallery = findViewById(R.id.btnGallery);

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == GALLERY_REQ_CODE) {
                //for Gallery

                imgGallery.setImageURI(data.getData());
            }
        }

        //State Spinner Initialisation
        campusSpinner = findViewById(R.id.spinner_campus);//Finds a view that was identified by the android:id attribute in xml

        //Populate ArrayAdapter using string array and a spinner layout that we will define
        stateAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_campus, R.layout.spinner_layout);

        // Specify the layout to use when the list of choices appear
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        campusSpinner.setAdapter(stateAdapter);            //Set the adapter to the spinner to populate the State Spinner

        //When any item of the stateSpinner uis selected
        campusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Define City Spinner but we will populate the options through the selected state
                blockSpinner = findViewById(R.id.spinner_block);

                selectedcampus = campusSpinner.getSelectedItem().toString();      //Obtain the selected State

                int parentID = parent.getId();
                if (parentID == R.id.spinner_campus) {
                    switch (selectedcampus) {
                        case "Select the campus":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_block, R.layout.spinner_layout);
                            break;
                        case "Steve Biko":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_Steve_Biko_districts, R.layout.spinner_layout);
                            break;
                        case "Ritson":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_Ritson_districts, R.layout.spinner_layout);
                            break;
                        case "Ml Sultan":
                            districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_Ml_Sultan_districts, R.layout.spinner_layout);
                            break;

                        default:
                            break;
                    }
                    districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     // Specify the layout to use when the list of choices appears
                    blockSpinner.setAdapter(districtAdapter);        //Populate the list of Districts in respect of the State selected

                    //To obtain the selected District from the spinner
                    blockSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            selectedblock = blockSpinner.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button submitButton;                                //To display the selected State and District
        submitButton = findViewById(R.id.button_submit);
        tvcampusSpinner = findViewById(R.id.textView_campus);
        tvblockSpinner = findViewById(R.id.textView_block);

        submitButton.setOnClickListener(v -> {

            if (selectedcampus.equals("Select the campus")) {
                Toast.makeText(MainActivity2.this, "Please select the campus from the list", Toast.LENGTH_LONG).show();
                tvcampusSpinner.setError("campus is required!");      //To set error on TextView
                tvcampusSpinner.requestFocus();
            } else if (selectedblock.equals("Select the block")) {
                Toast.makeText(MainActivity2.this, "Please select the block from the list", Toast.LENGTH_LONG).show();
                tvblockSpinner.setError("block is required!");
                tvblockSpinner.requestFocus();
                tvcampusSpinner.setError(null);
            } else {
                tvcampusSpinner.setError(null);
                tvblockSpinner.setError(null);
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,Survey.class);
                startActivity(intent);
                Toast.makeText(MainActivity2.this, "Successfully Submitted", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
