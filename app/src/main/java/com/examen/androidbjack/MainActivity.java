package com.examen.androidbjack;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Carta[] baraja = new Carta[52];
    LinearLayout mano,manoDealer;
    int posicion,apostando,dinero,valorEnMano,cantCartas,valorDealer;
    int[] cartasMano = new int[10];
    boolean plantado = false;
    static int[] highscore = new int[10];
    static String[] nombre = new String[10];

    static String[] getList(){
        String[] arg = new String[20];
        int cont =0;
        for(int i = 2;i<20;i+=2){
            arg[i] = nombre[i/2];
            arg[i+1] = Integer.toString(highscore[i/2]);
        }
        return arg;
    }

    static int cantidadApuesta = 100;
    public static void setApuesta(int x){
        cantidadApuesta = x;
    }
    static String nombreJg = "Default";
    public static void setNombre(String x){
        nombreJg = x;
    }
    
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mano = findViewById(R.id.mano);
        manoDealer = findViewById(R.id.manoDealer);
        dinero = 1000;
        valorEnMano = 0;
        apostando = cantidadApuesta;
        nuevaBaraja();
        //baraja[1] = baraja[12];
        barajar();
        loadHighscores();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Save:
                for(int i = 0;i<10;i++){
                    if(dinero>highscore[i]){
                        System.out.println(dinero+" es mnas grande que "+ highscore[i]);
                        DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Scores").child("Score"+(i+1));
                        reff.child("name").setValue(nombreJg);
                        reff.child("score").setValue(dinero);
                        return true;
                    }
                }
                return true;
            case R.id.VerPuntuaciones:
                Intent myIntent = new Intent(getBaseContext(),   Highscores.class);
                startActivity(myIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void loadHighscores(){
        DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Scores");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                collectPhoneNumbers((Map<String,Object>) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void collectPhoneNumbers(Map<String,Object> users) {
        int cont = 0;
        ArrayList<String> phoneNumbers = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            nombre[cont]=singleUser.get("name").toString();
            highscore[cont] = Integer.parseInt(singleUser.get("score").toString());
            cont++;
        }

        System.out.println(phoneNumbers.toString());
    }

    public void nuevaBaraja() {
        int cont = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                Carta c = new Carta(i, j);
                baraja[cont]=c;
                cont++;
            }
        }
        ImageView imv01 = new ImageView(this);
        imv01.setImageResource(R.drawable.c1);
        baraja[0].setIve(imv01);
        ImageView imv02 = new ImageView(this);
        imv02.setImageResource(R.drawable.c2);
        baraja[1].setIve(imv02);
        ImageView imv03 = new ImageView(this);
        imv03.setImageResource(R.drawable.c3);
        baraja[2].setIve(imv03);
        ImageView imv04 = new ImageView(this);
        imv04.setImageResource(R.drawable.c4);
        baraja[3].setIve(imv04);
        ImageView imv05 = new ImageView(this);
        imv05.setImageResource(R.drawable.c5);
        baraja[4].setIve(imv05);
        ImageView imv06 = new ImageView(this);
        imv06.setImageResource(R.drawable.c6);
        baraja[5].setIve(imv06);
        ImageView imv07 = new ImageView(this);
        imv07.setImageResource(R.drawable.c7);
        baraja[6].setIve(imv07);
        ImageView imv08 = new ImageView(this);
        imv08.setImageResource(R.drawable.c8);
        baraja[7].setIve(imv08);
        ImageView imv09 = new ImageView(this);
        imv09.setImageResource(R.drawable.c9);
        baraja[8].setIve(imv09);
        ImageView imv010 = new ImageView(this);
        imv010.setImageResource(R.drawable.c10);
        baraja[9].setIve(imv010);
        ImageView imv011 = new ImageView(this);
        imv011.setImageResource(R.drawable.c11);
        baraja[10].setIve(imv011);
        ImageView imv012 = new ImageView(this);
        imv012.setImageResource(R.drawable.c12);
        baraja[11].setIve(imv012);
        ImageView imv013 = new ImageView(this);
        imv013.setImageResource(R.drawable.c13);
        baraja[12].setIve(imv013);
        ImageView imv11 = new ImageView(this);
        imv11.setImageResource(R.drawable.d1);
        baraja[13].setIve(imv11);
        ImageView imv12 = new ImageView(this);
        imv12.setImageResource(R.drawable.d2);
        baraja[14].setIve(imv12);
        ImageView imv13 = new ImageView(this);
        imv13.setImageResource(R.drawable.d3);
        baraja[15].setIve(imv13);
        ImageView imv14 = new ImageView(this);
        imv14.setImageResource(R.drawable.d4);
        baraja[16].setIve(imv14);
        ImageView imv15 = new ImageView(this);
        imv15.setImageResource(R.drawable.d5);
        baraja[17].setIve(imv15);
        ImageView imv16 = new ImageView(this);
        imv16.setImageResource(R.drawable.d6);
        baraja[18].setIve(imv16);
        ImageView imv17 = new ImageView(this);
        imv17.setImageResource(R.drawable.d7);
        baraja[19].setIve(imv17);
        ImageView imv18 = new ImageView(this);
        imv18.setImageResource(R.drawable.d8);
        baraja[20].setIve(imv18);
        ImageView imv19 = new ImageView(this);
        imv19.setImageResource(R.drawable.d9);
        baraja[21].setIve(imv19);
        ImageView imv110 = new ImageView(this);
        imv110.setImageResource(R.drawable.d10);
        baraja[22].setIve(imv110);
        ImageView imv111 = new ImageView(this);
        imv111.setImageResource(R.drawable.d11);
        baraja[23].setIve(imv111);
        ImageView imv112 = new ImageView(this);
        imv112.setImageResource(R.drawable.d12);
        baraja[24].setIve(imv112);
        ImageView imv113 = new ImageView(this);
        imv113.setImageResource(R.drawable.d13);
        baraja[25].setIve(imv113);
        ImageView imv21 = new ImageView(this);
        imv21.setImageResource(R.drawable.h1);
        baraja[26].setIve(imv21);
        ImageView imv22 = new ImageView(this);
        imv22.setImageResource(R.drawable.h2);
        baraja[27].setIve(imv22);
        ImageView imv23 = new ImageView(this);
        imv23.setImageResource(R.drawable.h3);
        baraja[28].setIve(imv23);
        ImageView imv24 = new ImageView(this);
        imv24.setImageResource(R.drawable.h4);
        baraja[29].setIve(imv24);
        ImageView imv25 = new ImageView(this);
        imv25.setImageResource(R.drawable.h5);
        baraja[30].setIve(imv25);
        ImageView imv26 = new ImageView(this);
        imv26.setImageResource(R.drawable.h6);
        baraja[31].setIve(imv26);
        ImageView imv27 = new ImageView(this);
        imv27.setImageResource(R.drawable.h7);
        baraja[32].setIve(imv27);
        ImageView imv28 = new ImageView(this);
        imv28.setImageResource(R.drawable.h8);
        baraja[33].setIve(imv28);
        ImageView imv29 = new ImageView(this);
        imv29.setImageResource(R.drawable.h9);
        baraja[34].setIve(imv29);
        ImageView imv210 = new ImageView(this);
        imv210.setImageResource(R.drawable.h10);
        baraja[35].setIve(imv210);
        ImageView imv211 = new ImageView(this);
        imv211.setImageResource(R.drawable.h11);
        baraja[36].setIve(imv211);
        ImageView imv212 = new ImageView(this);
        imv212.setImageResource(R.drawable.h12);
        baraja[37].setIve(imv212);
        ImageView imv213 = new ImageView(this);
        imv213.setImageResource(R.drawable.h13);
        baraja[38].setIve(imv213);
        ImageView imv31 = new ImageView(this);
        imv31.setImageResource(R.drawable.s1);
        baraja[39].setIve(imv31);
        ImageView imv32 = new ImageView(this);
        imv32.setImageResource(R.drawable.s2);
        baraja[40].setIve(imv32);
        ImageView imv33 = new ImageView(this);
        imv33.setImageResource(R.drawable.s3);
        baraja[41].setIve(imv33);
        ImageView imv34 = new ImageView(this);
        imv34.setImageResource(R.drawable.s4);
        baraja[42].setIve(imv34);
        ImageView imv35 = new ImageView(this);
        imv35.setImageResource(R.drawable.s5);
        baraja[43].setIve(imv35);
        ImageView imv36 = new ImageView(this);
        imv36.setImageResource(R.drawable.s6);
        baraja[44].setIve(imv36);
        ImageView imv37 = new ImageView(this);
        imv37.setImageResource(R.drawable.s7);
        baraja[45].setIve(imv37);
        ImageView imv38 = new ImageView(this);
        imv38.setImageResource(R.drawable.s8);
        baraja[46].setIve(imv38);
        ImageView imv39 = new ImageView(this);
        imv39.setImageResource(R.drawable.s9);
        baraja[47].setIve(imv39);
        ImageView imv310 = new ImageView(this);
        imv310.setImageResource(R.drawable.s10);
        baraja[48].setIve(imv310);
        ImageView imv311 = new ImageView(this);
        imv311.setImageResource(R.drawable.s11);
        baraja[49].setIve(imv311);
        ImageView imv312 = new ImageView(this);
        imv312.setImageResource(R.drawable.s12);
        baraja[50].setIve(imv312);
        ImageView imv313 = new ImageView(this);
        imv313.setImageResource(R.drawable.s13);
        baraja[51].setIve(imv313);




    }


    public void barajar() {
        posicion = 0;
        for (int actual = 0; actual < 52; actual++) {
            Carta temp = baraja[actual];
            int nuevapos = (int) (Math.random() * 52);
            baraja[actual]= baraja[nuevapos];
            baraja[nuevapos] = temp;
        }
    }

    public void updateTVs(){
        TextView tvvm = findViewById(R.id.valorDeMano);
        TextView tvca = findViewById(R.id.valorEnApuesta);
        TextView tvbl = findViewById(R.id.billetera);
        actualizarValorMano();
        tvvm.setText("Mano: "+valorEnMano);
        tvca.setText("Apostando: " +apostando);
        tvbl.setText("Billetera: "+dinero+"$");
    }

    public void subirApuesta(View view){
        if(plantado||cantCartas>1){
            //Alert
        }else {
            if(dinero>=cantidadApuesta) {
                apostando += cantidadApuesta;
                dinero -= cantidadApuesta;
                updateTVs();
            }else{
                Toast.makeText(this, "No tienes suficiente dinero", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public int valorarCarta(int valor,int valorEnMano){

        switch (valor) {
            case 0:
                if ((valorEnMano + 11) <= 21) {
                    System.out.println("esto: "+valorEnMano);
                    return 11;

                } else {
                    return 1;
                }

            case 9:
            case 11:
            case 12:
            case 10:
                return 10;


            default:
                return valor + 1;

        }


    }


    public boolean tePasaste(int valorEnMano,int cantCartas,int[] cartasMano){
        boolean pasaste = true;

        if(valorEnMano>21){
            for(int i = 0;i<cantCartas;i++){
                if(cartasMano[i]==11&&valorEnMano>21){
                    cartasMano[i] = 1;
                    actualizarValorMano();
                }
            }
        }
        if(valorEnMano<=21){
            pasaste = false;
        }

        return pasaste;
    }

    public void mePlanto(View view){
        /*
        if(plantado){
            reset();
        }/*¡¡*/
        plantado = true;
        if(valorDealer == 0) {
            do {
                Carta cartaActual = baraja[posicion++];
                ImageView nuevaC = cartaActual.getIve();
                TableRow.LayoutParams params = new TableRow.LayoutParams();
                params.weight = 0;
                params.setMargins(550, 550, 550, 550);
                nuevaC.setLayoutParams(params);
                manoDealer.addView(nuevaC, 150, 150);
                valorDealer += valorarCarta(cartaActual.getValor(), valorDealer);
            } while (valorDealer < 17);
            if (valorEnMano <= 21) {
                if (valorDealer > 21 || valorEnMano > valorDealer) {
                    ganaste(false);
                } else {
                    setTVText("Te gano", "el Dealer");

                }
            }
        }
    }

    public void ganaste(boolean bj){
        if(!bj) {
            setTVText("Has", "Ganado");
        }else{
            Toast.makeText(this,"GANASTE! BLACK JACK",Toast.LENGTH_LONG).show();
        }
        dinero += (apostando*2)+cantidadApuesta;
        //reset();
    }

    public void setTVText(String txt1, String txt2){
            TextView tvvm = findViewById(R.id.valorDeMano);
            TextView tvca = findViewById(R.id.valorEnApuesta);
            tvvm.setText(txt1);
            tvca.setText(txt2);
    }


    public void actualizarValorMano(){
        valorEnMano = 0;
        for(int i = 0;i<cantCartas;i++){
            valorEnMano+=cartasMano[i];
        }
    }

    public void reset(){
        if(dinero==0){
            Toast.makeText(this, "No tienes suficiente dinero", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(getIntent());
        }else {
            mano.removeAllViews();
            plantado=false;
            valorEnMano = 0;
            valorDealer = 0;
            apostando = cantidadApuesta;
            dinero -= cantidadApuesta;
            cartasMano = new int[10];
            cantCartas = 0;
            manoDealer.removeAllViews();

        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("Dinero", dinero);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        dinero = savedInstanceState.getInt("Dinero");

    }





    public void siguienteCarta(View view) {
        if (plantado) {
            reset();
            updateTVs();
        }else {
            if (posicion == 52) {
                barajar();
                posicion = 0;
            }
            if (valorEnMano < 21) {
                Carta cartaActual = baraja[posicion];
                ImageView nuevaC = cartaActual.getIve();
                TableRow.LayoutParams params = new TableRow.LayoutParams();
                params.weight = 0;
                nuevaC.setLayoutParams(params);
                mano.addView(nuevaC, 150, 150);
                cartasMano[cantCartas] = valorarCarta(cartaActual.getValor(),valorEnMano);
                valorEnMano += valorarCarta(cartaActual.getValor(),valorEnMano);
                cantCartas++;
                if(cantCartas==2&&valorEnMano==21){
                    ganaste(true);
                }
                tePasaste(valorEnMano,cantCartas,cartasMano);
                updateTVs();
                posicion++;

                if (tePasaste(valorEnMano,cantCartas,cartasMano)) {
                    setTVText("Has","Perdido");
                }
            } else {
                reset();

                updateTVs();
            }
        }

    }
}
