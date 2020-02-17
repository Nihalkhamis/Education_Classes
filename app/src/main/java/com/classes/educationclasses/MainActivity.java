package com.classes.educationclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.classes.educationclasses.Model.EducationLevelsModel;
import com.classes.educationclasses.Model.PrimaryModel;
import com.classes.educationclasses.Network.ConnectorService;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{

     Spinner spinner;
     ArrayList<String> classes;
     ArrayList<EducationLevelsModel> educationLevels;
     ArrayList<BarEntry> barEntries;
     ArrayList<String> classTitles;
     TextView item_spinner;
     BarChart barChart;
     BarDataSet barDataSet;
     BarData barData;
     Description description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        item_spinner = findViewById(R.id.item_spinner);
        classes = new ArrayList<>();
        educationLevels = new ArrayList<>();
        barChart = findViewById(R.id.barChart);
        barEntries = new ArrayList<>();
        classTitles = new ArrayList<>();
        getEducationLevels();

    }
//    private ArrayList getData(){
//        ArrayList<BarEntry> entries = new ArrayList<>();
//        entries.add(new BarEntry(0f, 30f));
//        entries.add(new BarEntry(1f, 80f));
//        entries.add(new BarEntry(2f, 60f));
//        entries.add(new BarEntry(3f, 50f));
//        entries.add(new BarEntry(4f, 70f));
//        entries.add(new BarEntry(5f, 60f));
//        return entries;
//    }



   public void getEducationLevels(){
       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(ConnectorService.BaseURL)
               .addConverterFactory(GsonConverterFactory.create(new Gson())).build();

       ConnectorService connectorService = retrofit.create(ConnectorService.class);

     connectorService.getLevels().enqueue(new Callback<ArrayList<EducationLevelsModel>>() {
         @Override
         public void onResponse(Call<ArrayList<EducationLevelsModel>> call, Response<ArrayList<EducationLevelsModel>> response) {
             Log.d("TTT", "onResponse: "+call.request().url());
             if (response.isSuccessful()){
                 Log.d("TTT", "onResponse: " + response.body().size());
                 Log.d("TTT", "onResponse: " + response.code());

                 educationLevels = response.body();

                 //to sort the arrayList of educationLevels by the getDisplayOrder in it descending
                 Collections.sort(educationLevels, new Comparator<EducationLevelsModel>() {
                     @Override
                     public int compare(EducationLevelsModel o1, EducationLevelsModel o2) {
                         return o1.getDisplayOrder().compareTo(o2.getDisplayOrder());
                     }
                 } );

                 Log.d("TTT", "onResponse: "+educationLevels+"");
                 for (int i =0; i<educationLevels.size(); i++){
                     classes.add(educationLevels.get(i).getEduLevelTitle());
                 }

                 ArrayAdapter<String> adapterTime = new ArrayAdapter<>(MainActivity.this,R.layout.my_spinner_textview,R.id.item_spinner, classes);
                 spinner.setAdapter(adapterTime);
                 spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                     @Override
                     public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                         barEntries.clear();
                         classTitles.clear();
                         Toast.makeText(MainActivity.this, "" + educationLevels.get(i).getEduLevelTitle(), Toast.LENGTH_SHORT).show();
                         connectorService.getPrimaryLevel(educationLevels.get(i).getDisplayOrder().toString()).enqueue(new Callback<ArrayList<PrimaryModel>>() {
                             @Override
                             public void onResponse(Call<ArrayList<PrimaryModel>> call, Response<ArrayList<PrimaryModel>> response) {
                                 Log.d("TTT", "onResponse: "+call.request().url());
                                 if (response.isSuccessful()){
                                    // Log.d("TTT", "onResponse: " + response.body().get(1).getTotalCount()+"");
//                                     Log.d("TTT", "onResponse: " + response.code());
//                                     Log.d("TTT", "onResponse: "+response.body().get(0).getClassTitle()+"");
//                                     Log.d("TTT", "onResponse: "+response.body().get(1).getClassTitle()+"");
//                                     Log.d("TTT", "onResponse: "+response.body().get(2).getClassTitle()+"");
//                                     Log.d("TTT", "onResponse: "+response.body().get(3).getClassTitle()+"");
//                                     Log.d("TTT", "onResponse: "+response.body().get(4).getClassTitle()+"");
                                     //Log.d("TTT", "onResponse: "+response.body().get(5).getClassTitle()+"");

                                     Log.d("TTT", "onResponse: "+classTitles);

                                     for (PrimaryModel primaryModel : response.body()){
                                       barEntries.add(new BarEntry(primaryModel.getEduClassID(),primaryModel.getTotalCount()));
                                       classTitles.add(primaryModel.getClassTitle());
                                         Log.d("TTT", "onResponse: "+primaryModel.getClassTitle());
                                        //String[] arr = classTitles.toArray(new String[classTitles.size()]);
                                         Log.d("TTT", "onResponse: "+classTitles);
                                     }


//                                   classTitles.add("");


//                                     PrimaryModel primaryModel = new PrimaryModel();
//                                     int x = 0;
//                                     for (int i = 0; i < response.body().size(); i++){
//                                       barEntries.add(new BarEntry(x,primaryModel.getTotalCount()));
//                                       classTitles.add("");
//                                       classTitles.add(primaryModel.getClassTitle());
//                                         Log.d("TTT", "onResponse: "+classTitles);
//                                         x++;
//                                     }


                                     IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(classTitles);
                                     Log.d("TTT", "onResponse: "+formatter);


                                     barDataSet = new BarDataSet(barEntries,"");
                                   barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);



                                     barData = new BarData(barDataSet);
                                   barData.setBarWidth(0.9f);
                                   barData.setValueTextSize(10f);

                                     barData.setValueTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

                                     description = new Description();
                                     description.setText("class levels");


                                     barChart.setVisibility(View.VISIBLE);
                                     barChart.animateY(1000);
                                     barChart.setData(barData);
                                     barChart.setFitBars(true);
                                     barChart.setDescription(description);
                                     barChart.setDrawValueAboveBar(true);
                                     barChart.setMaxVisibleValueCount(50);


                                     barChart.getXAxis().setValueFormatter(formatter);
                                     //to show all labels in x-axis
                                     barChart.getXAxis().setCenterAxisLabels(true);

                                     //to remove the colored squares in the bottom
                                     barChart.getLegend().setEnabled(false);


                                     XAxis xAxis =  barChart.getXAxis();
                                         xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                                         xAxis.setDrawLabels(true);
                                     xAxis.setValueFormatter(formatter);
                                     xAxis.setCenterAxisLabels(true);
                                     xAxis.setGranularity(1);
                                     xAxis.setDrawAxisLine(false);

                                     Log.d("TTT", "onResponse: "+xAxis.getValueFormatter().toString());

                                     barChart.invalidate();

                                     
                                 }
                             }

                             @Override
                             public void onFailure(Call<ArrayList<PrimaryModel>> call, Throwable t) {
                                 Log.d("TTT", "onResponse: "+t.getMessage());

                             }
                         });
                     }

                     @Override
                     public void onNothingSelected(AdapterView<?> adapterView) {
                           // classTitles.clear();
                     }
                 });
             }
         }

         @Override
         public void onFailure(Call<ArrayList<EducationLevelsModel>> call, Throwable t) {
             Log.d("TTT", "onResponse: "+t.getMessage());

         }
     });
   }


}
