package com.robby.mobile_03_20182;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.robby.mobile_03_20182.entity.Student;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * @author Robby
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ll_root)
    ScrollView root;
    @BindView(R.id.cb_menu_1)
    CheckBox cbMenu1;
    @BindView(R.id.cb_menu_2)
    CheckBox cbMenu2;
    @BindView(R.id.cb_menu_3)
    CheckBox cbMenu3;
    @BindView(R.id.radio_male)
    RadioButton rbMale;
    @BindView(R.id.radio_female)
    RadioButton rbFemale;
    @BindView(R.id.spin_entity)
    Spinner spinStudent;
    @BindView(R.id.spin_string)
    Spinner spinDepartment;
    private ArrayList<String> departments;
    private ArrayAdapter<String> departmentAdapter;
    private ArrayList<Student> students;
    private ArrayAdapter<Student> studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        departments = new ArrayList<>();
        departments.add("S1 Teknik Informatika");
        departments.add("S1 Sistem Informasi");
        departmentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, departments);
        spinDepartment.setAdapter(departmentAdapter);

        students = new ArrayList<>();
        students.add(new Student("1072001", "John Doe"));
        students.add(new Student("1072002", "Cid Caramel"));
        studentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, students);
        spinStudent.setAdapter(studentAdapter);
    }

    @OnClick(R.id.btn_submit)
    public void submitAction() {
        int count = countChecked(cbMenu1, cbMenu2, cbMenu3);
        String values = choiceChecked(cbMenu1, cbMenu2, cbMenu3);
        String radioSelected = rbMale.isSelected() ? rbMale.getText().toString() : rbFemale.getText().toString();
        Toast.makeText(this, "Selected Gender: " + radioSelected, Toast.LENGTH_SHORT).show();
        Snackbar snackbar = Snackbar.make(root, "Choice: " + count + " (" + values + ")", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_a:
                Snackbar.make(root, R.string.app_menu_1, Snackbar.LENGTH_LONG).show();
                return true;
            case R.id.mn_b:
                Snackbar.make(root, R.string.app_menu_2, Snackbar.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnCheckedChanged({R.id.cb_menu_1, R.id.cb_menu_2, R.id.cb_menu_3})
    public void menuChecked(CheckBox cb) {
        if (cb.isChecked()) {
            Toast.makeText(this, cb.getText() + " is checked", Toast.LENGTH_SHORT).show();
        }
    }

    @OnCheckedChanged({R.id.radio_male, R.id.radio_female})
    public void genderChecked(RadioButton radioButton) {
        if (radioButton.isChecked()) {
            Toast.makeText(this, radioButton.getText() + " is Selected", Toast.LENGTH_SHORT).show();
        }
    }

    private int countChecked(CheckBox... checkBoxes) {
        int count = 0;
        for (CheckBox c : checkBoxes) {
            if (c.isChecked()) {
                count++;
            }
        }
        return count;
    }

    private String choiceChecked(CheckBox... checkBoxes) {
        StringBuilder builder = new StringBuilder();
        for (CheckBox c : checkBoxes) {
            if (c.isChecked()) {
                builder.append(c.getText());
                builder.append("; ");
            }
        }
        if (builder.length() > 0) {
            return builder.toString().trim().substring(0, builder.length() - 2);
        }
        return "";
    }
}
