package com.example.kenstate.personvisitstatusupdate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static com.example.kenstate.personvisitstatusupdate.Constants.Baseurl;

public class PersonAdapter extends ArrayAdapter<Person> {
    private static final String KEY_ITEMID = "CCC NO: ";
    private static final String KEY_NAME = "MFL CODE: ";
    private static final String KEY_DOB = "FACILITY: ";
    private static final String KEY_DESIGNATION = "TCA: ";

    public static List<Person> dataSet;

    public PersonAdapter(List<Person> dataSet, Context mContext) {
        super(mContext, R.layout.personlistrow, dataSet);
        this.dataSet = dataSet;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.personlistrow, null);
        }
        Person employee = dataSet.get(position);
        if (employee != null) {
            //Text View references
            TextView itemId = (TextView) v.findViewById(R.id.cccno);
            TextView itemname = (TextView) v.findViewById(R.id.mflcode);
            TextView category = (TextView) v.findViewById(R.id.facilityname);
            TextView price = (TextView) v.findViewById(R.id.tca);
            ImageView icon =(ImageView) v.findViewById(R.id.icon);

           // String x = Baseurl+ employee.getImg();
            //Updating the text views
            itemId.setText(KEY_ITEMID + employee.getCccno());
            itemname.setText(KEY_NAME + employee.getMflcode());
            category.setText(KEY_DOB + employee.getFacilityname());
            price.setText(KEY_DESIGNATION + employee.getTca());


        }

        return v;
    }
}
