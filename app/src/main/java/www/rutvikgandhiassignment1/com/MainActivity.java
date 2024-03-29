package www.rutvikgandhiassignment1.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;

public class  MainActivity extends AppCompatActivity {

    public double type_P, addFeaturesUn_P, addFeatures_UP, design_P, tools_P;
    public String message, type_S,type_Spinner, date_1, prov, ad_ToolS, ad_FeatureS, ad_FeatureSS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the views

        EditText name = findViewById(R.id.edt_Name);
        RadioGroup type = findViewById(R.id.rgType);
        CheckBox unlimited = findViewById(R.id.cbUnlimited);
        CheckBox updates = findViewById(R.id.cbUpdates);
        Spinner sType = findViewById(R.id.spnType);
        RadioGroup tools = findViewById(R.id.rgTools);
        AutoCompleteTextView province = findViewById(R.id.acProvince);
        CalendarView calender = findViewById(R.id.calendarView);
        Button calculate = findViewById(R.id.btnCalculate);
        RadioButton b1 = findViewById(R.id.rd_1);
        RadioButton b2 = findViewById(R.id.rd_2);
        RadioButton b3 = findViewById(R.id.rd_3);

        updates.findViewById(R.id.cbUpdates).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFeatures_UP = 2.70;
                ad_FeatureS = "Update";
            }
        });
        unlimited.findViewById(R.id.cbUnlimited).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFeatures_UP = 2.70;
                ad_FeatureSS = "Update";
            }
        });

        type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rdMonthly) {
                    type_S = "Monthly subscription for:";
                    type_P = 12.50;
                    b1.setText(R.string.m1);
                    b2.setText(R.string.m2);
                    b3.setText(R.string.m3);
                    tools.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int j) {
                            if(j == R.id.rd_1)
                            {
                                tools_P = 0.0;
                                ad_ToolS = "None";
                            }
                            else if (j == R.id.rd_2) {
                                tools_P = 2.20;
                                ad_ToolS = "Custom Print";
                            }
                            else if (j == R.id.rd_3) {
                                tools_P = 3.70;
                                ad_ToolS = "Pro-Tools";
                            }

                        }
                    });

                } else if (i == R.id.rdYearly) {
                    type_S = "Yearly subscription for:";
                    type_P = 140;
                    b1.setText(R.string.y1);
                    b2.setText(R.string.y2);
                    b3.setText(R.string.y3);
                    tools.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int j) {
                            if(j == R.id.rd_1)
                            {
                                tools_P = 0.0;
                                ad_ToolS = "None";
                            }
                            else if (j == R.id.rd_2) {
                                tools_P = 3.50;
                                ad_ToolS = "Material Designs";
                            }
                            else if (j == R.id.rd_3) {
                                tools_P = 4.50;
                                ad_ToolS = "Premium-Tools";
                            }

                        }
                    });

                }

            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this,
                R.array.design_type, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner

        sType.setAdapter(adapter);

        sType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                type_Spinner = selectedItem;
                switch (type_Spinner){
                    case "Labels":
                        design_P = 2.50;
                        break;
                    case "Cards":
                        design_P = 3.50;
                        break;
                    case "Badges":
                        design_P = 4.20;
                        break;
                    default:
                        design_P = 0.0;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] data = {"Ontario", "British Columbia", "Alberta", "Saskatchewan", "Manitoba", "Nova Scotia", "Newfoundland and Labrador", "New Brunswick", "Prince Edward Island", "Northwest Territories", "Nunavut", "Yukon", "Quebec"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, data);
        province.setAdapter(adapter1);
        province.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                prov = selected;
            }
        });

        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int date) {
                String y = String.valueOf(year);
                String m = String.valueOf(month);
                String d = String.valueOf(date);
                date_1 = d+"/"+m+"/"+y;
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DecimalFormat d = new DecimalFormat("0.00");
                double totalWTP = type_P+addFeaturesUn_P+addFeatures_UP+design_P+tools_P;
                double taxP = (totalWTP*(0.13));
                double totalP = ((totalWTP)+(taxP));
                String name1 = name.getText().toString();
                message = "On "+date_1+", for "+name1+" from "+prov+", a "+type_S+" "+type_Spinner+", with "+ad_ToolS+", and "+ad_FeatureS+", Cost: $"+d.format(totalP)+".";
                Snackbar snackbar = Snackbar.make(view, ""+message, Snackbar.LENGTH_LONG);
                snackbar.setTextMaxLines(8);
                snackbar.show();
            }
        });

}
}