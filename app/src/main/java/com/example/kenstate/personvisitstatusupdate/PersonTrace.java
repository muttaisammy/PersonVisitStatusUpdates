package com.example.kenstate.personvisitstatusupdate;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.TextView;

public class PersonTrace extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_visit_no);

        String CCCNO = getIntent().getStringExtra("cccno");
        String MFLCODE = getIntent().getStringExtra("mflcode");
        String FACILITY = getIntent().getStringExtra("facility");
        String TCA = getIntent().getStringExtra("tca");

        TextView cccno = (TextView)findViewById(R.id.editText);
        TextView mflcode = (TextView)findViewById(R.id.editText3);
      TextView facility = (TextView)findViewById(R.id.txtfacility);
       TextView tca = (TextView)findViewById(R.id.tca);



        cccno.setText(CCCNO);
        mflcode.setText(MFLCODE);
        facility.setText(FACILITY);
        tca.setText( TCA);

    }

}
