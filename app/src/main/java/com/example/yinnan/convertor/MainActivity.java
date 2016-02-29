package com.example.yinnan.convertor;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner unitTypeSpinner;
    private EditText amount;

    TextView teaspoonTextView, tablespoonTextView, cupTextView, ounceTextView,
            pintTextView, quartTextView, gallonTextView, poundTextView,
            milliliterTextView, literTextView, milligramTextView, kilogramTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialize all TextViews
        initializeTextViews();

        addItemsToUnitTypeSpinner();
        addListenerToUnitTypeSpinner();
        amount = (EditText) findViewById(R.id.amount);
    }

    public void addItemsToUnitTypeSpinner() {
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);
        ArrayAdapter<CharSequence> SpinnerAdpt =
                ArrayAdapter.createFromResource(this, R.array.convertor_type,
                        android.R.layout.simple_spinner_item);
        SpinnerAdpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitTypeSpinner.setAdapter(SpinnerAdpt);

    }

    public void addListenerToUnitTypeSpinner() {
        unitTypeSpinner  = (Spinner) findViewById(R.id.unit_type_spinner);

        unitTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                checkIfConvertingFromTsp(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initializeTextViews() {
        teaspoonTextView = (TextView) findViewById(R.id.tsp_TextView);
        tablespoonTextView = (TextView) findViewById(R.id.tbs_TextView);
        cupTextView = (TextView) findViewById(R.id.cup_TextView);
        ounceTextView = (TextView) findViewById(R.id.oz_TextView);
        pintTextView = (TextView) findViewById(R.id.pint_TextView);
        quartTextView = (TextView) findViewById(R.id.quart_TextView);
        gallonTextView = (TextView) findViewById(R.id.gallon_TextView);
        poundTextView = (TextView) findViewById(R.id.pound_TextView);
        milliliterTextView = (TextView) findViewById(R.id.ml_TextView);
        literTextView = (TextView) findViewById(R.id.liter_TextView);
        milligramTextView = (TextView) findViewById(R.id.mg_TextView);
        kilogramTextView = (TextView) findViewById(R.id.kg_TextView);
    }

    public void checkIfConvertingFromTsp(String currentUnit) {
            if (currentUnit.equals("teaspoon")) {
                updateUnitType(Quantity.Unit.tsp);
            }
            else if(currentUnit.equals("tablespoon")){
                updateUnitType(Quantity.Unit.tbs);
            }
            else if(currentUnit.equals("cup")){
                updateUnitType(Quantity.Unit.cup);
            }
            else if(currentUnit.equals("ounce")){
                updateUnitType(Quantity.Unit.oz);
            }
            else if(currentUnit.equals("pint")){
                updateUnitType(Quantity.Unit.pint);
            }
            else if(currentUnit.equals("quart")){
                updateUnitType(Quantity.Unit.quart);
            }
            else if(currentUnit.equals("gallon")){
                updateUnitType(Quantity.Unit.gallon);
            }
            else if(currentUnit.equals("pound")){
                updateUnitType(Quantity.Unit.pound);
            }
            else if(currentUnit.equals("milliliter")){
                updateUnitType(Quantity.Unit.ml);
            }
            else if(currentUnit.equals("liter")){
                updateUnitType(Quantity.Unit.liter);
            }
            else if(currentUnit.equals("milligram")){
                updateUnitType(Quantity.Unit.mg);
            }
            else {
                updateUnitType(Quantity.Unit.kg);
            }
    }

    private void updateUnitType(Quantity.Unit currentUnit) {
        double doubleToConvert = Double.parseDouble(amount.getText().toString());
        doubleToConvert = currentUnit.toBaseUnit(doubleToConvert);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.tsp, teaspoonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.tbs, tablespoonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.cup, cupTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.oz, ounceTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.pint, pintTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.quart, quartTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.gallon, gallonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.pound, poundTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.ml, milliliterTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.liter, literTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.mg, milligramTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.kg, kilogramTextView);
    }

    private void updateUnitTextFieldUsingTsp(double doubleToConvert, Quantity.Unit unit, TextView UnitTextView) {
        Quantity newUnit = new Quantity (unit.fromBaseUnit(doubleToConvert), unit);
        UnitTextView.setText(newUnit.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
