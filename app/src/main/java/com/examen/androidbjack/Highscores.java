package com.examen.androidbjack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Highscores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        initHighscore();
    }

    public void initHighscore(){
        String[] List = new String[20];
        List = MainActivity.getList();
        TextView tv = findViewById(R.id.name1);
        tv.setText(List[0]);
        TextView tv01 = findViewById(R.id.score1);
        tv01.setText(List[1]);
        TextView tv2 = findViewById(R.id.name2);
        tv2.setText(List[2]);
        TextView tv02 = findViewById(R.id.score2);
        tv02.setText(List[3]);
        TextView tv3 = findViewById(R.id.name3);
        tv3.setText(List[4]);
        TextView tv03 = findViewById(R.id.score3);
        tv03.setText(List[5]);
        TextView tv4 = findViewById(R.id.name4);
        tv4.setText(List[6]);
        TextView tv04 = findViewById(R.id.score4);
        tv04.setText(List[7]);
        TextView tv5 = findViewById(R.id.name5);
        tv5.setText(List[8]);
        TextView tv05 = findViewById(R.id.score5);
        tv05.setText(List[9]);
        TextView tv6 = findViewById(R.id.name6);
        tv6.setText(List[10]);
        TextView tv06 = findViewById(R.id.score6);
        tv06.setText(List[11]);
        TextView tv7 = findViewById(R.id.name7);
        tv7.setText(List[12]);
        TextView tv07 = findViewById(R.id.score7);
        tv07.setText(List[13]);
        TextView t8 = findViewById(R.id.name8);
        t8.setText(List[14]);
        TextView tv08 = findViewById(R.id.score8);
        tv08.setText(List[15]);
        TextView tv9 = findViewById(R.id.name9);
        tv9.setText(List[16]);
        TextView tv09 = findViewById(R.id.score9);
        tv09.setText(List[17]);
        TextView tv10 = findViewById(R.id.name10);
        tv10.setText(List[18]);
        TextView tv110 = findViewById(R.id.score10);
        tv110.setText(List[19]);
    }
    public void guardarDatos(View view){
        TextView tv = findViewById(R.id.cantidadDeApuesta);
        TextView tvn = findViewById(R.id.nombreNuevo);
        MainActivity.setApuesta(Integer.parseInt(tv.getText().toString()));
        MainActivity.setNombre(tvn.getText().toString());
        finish();
    }
}
