package com.example.listview3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ArrayList<Mois> laListeDep=new ArrayList<Mois>();
    ArrayList<Mois> laListeAr=new ArrayList<Mois>();
    String tab[]={"Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décenmbre"};
    Button lesButton[]= new Button[10];
    ListView listDep, listAr;
    AdapterPerso adaptateurDep;
    AdapterPerso adaptateurAr;
    TextView zoneTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        Ajouter();
        listDep.setOnItemClickListener(new LstClickItem());
        listAr.setOnItemClickListener(new LstClickItem());
    }

    private void Init(){
        for (int i=0; i<10; i++){
            String buttonID="Btn"+i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            lesButton[i]=findViewById(resID);
            lesButton[i].setOnClickListener(this);
        }
        lesButton[2].setEnabled(false);
        lesButton[3].setEnabled(false);
        lesButton[4].setEnabled(false);
    }

    private void Ajouter(){
        for  (int i=0; i<tab.length; i++){
            laListeDep.add(new Mois(tab[i], true));
        }
        adaptateurDep = new AdapterPerso(this, laListeDep);
        adaptateurAr = new AdapterPerso(this, laListeAr);
        listDep = findViewById(R.id.ListDep);
        listDep.setAdapter(adaptateurDep);
        listAr=findViewById(R.id.ListAr);
        listAr.setAdapter(adaptateurAr);
        zoneTxt=findViewById(R.id.ZoneTxt);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Btn0:
                UnDir(true);
                break;
            case R.id.Btn1:
                ToutDir(true);
                break;
            case R.id.Btn2:
                UnDir(false);
                break;
            case R.id.Btn3:
                ToutDir(false);
                break;
            case R.id.Btn4:
                Clear(adaptateurDep, adaptateurAr);
                adaptateurAr.notifyDataSetChanged();
                break;
            case R.id.Btn5:
                Clear(adaptateurAr);
                Ajouter();
                break;
            case R.id.Btn6:
                Deplacement(adaptateurDep);
                break;
            case R.id.Btn7:
                Deplacement(adaptateurAr);
                break;
            case R.id.Btn8:
                UnSelect(adaptateurDep);
                break;
            case R.id.Btn9:
                UnSelect(adaptateurAr);
                break;
        }
    }

    private void UnSelect(AdapterPerso adapterPerso){
        for (int i=0; i<adapterPerso.getArrayMois().size(); i++){
            if (adapterPerso.getArrayMois().get(i).isSelecte()){
                adapterPerso.getArrayMois().get(i).select();
            }
        }
        adapterPerso.notifyDataSetChanged();
    }
    private void UnDir(boolean direction){
        if (direction){
            for (int i=adaptateurDep.getArrayMois().size()-1; i>=0; i--){
                if (adaptateurDep.getArrayMois().get(i).isSelecte()) {
                    adaptateurAr.getArrayMois().add(adaptateurDep.getArrayMois().get(i));
                    adaptateurDep.getArrayMois().get(i).select();
                    adaptateurDep.getArrayMois().remove(i);
                }
                if (adaptateurDep.getArrayMois().isEmpty()){
                    lesButton[0].setEnabled(false);
                    lesButton[1].setEnabled(false);
                }
                lesButton[2].setEnabled(true);
                lesButton[3].setEnabled(true);
                lesButton[4].setEnabled(false);
            }
        }else{
            for (int i=adaptateurAr.getArrayMois().size()-1; i>=0; i--) {
                if (adaptateurAr.getArrayMois().get(i).isSelecte()){
                    adaptateurDep.getArrayMois().add(adaptateurAr.getArrayMois().get(i));
                    adaptateurAr.getArrayMois().get(i).select();
                    adaptateurAr.getArrayMois().remove(i);
                }
                if (adaptateurAr.getArrayMois().isEmpty()){
                    lesButton[2].setEnabled(false);
                    lesButton[3].setEnabled(false);
                }
                lesButton[0].setEnabled(true);
                lesButton[1].setEnabled(true);
                lesButton[4].setEnabled(true);
            }
        }
        adaptateurDep.notifyDataSetChanged();
        adaptateurAr.notifyDataSetChanged();
    }
    private void ToutDir(boolean direction){
        if (direction){
            for (int i=adaptateurDep.getArrayMois().size()-1; i>=0; i--){
                    adaptateurAr.getArrayMois().add(adaptateurDep.getArrayMois().get(i));
                    if (adaptateurDep.getArrayMois().get(i).isSelecte())
                        adaptateurDep.getArrayMois().get(i).select();
                    adaptateurDep.getArrayMois().remove(i);
            }
            Droite();
        }else{
            for (int i=adaptateurAr.getArrayMois().size()-1; i>=0; i--) {
                    adaptateurDep.getArrayMois().add(adaptateurAr.getArrayMois().get(i));
                    if (adaptateurAr.getArrayMois().get(i).isSelecte())
                        adaptateurAr.getArrayMois().get(i).select();
                    adaptateurAr.getArrayMois().remove(i);
            }
            Gauche();

        }
        adaptateurDep.notifyDataSetChanged();
        adaptateurAr.notifyDataSetChanged();
    }
    private void Gauche(){
        lesButton[0].setEnabled(true);
        lesButton[1].setEnabled(true);
        lesButton[2].setEnabled(false);
        lesButton[3].setEnabled(false);
        lesButton[4].setEnabled(false);
    }
    private void Droite(){
        lesButton[0].setEnabled(false);
        lesButton[1].setEnabled(false);
        lesButton[2].setEnabled(true);
        lesButton[3].setEnabled(true);
        lesButton[4].setEnabled(true);
    }
    private void Clear(AdapterPerso adapterPerso, AdapterPerso adapterPerso2){
        adapterPerso.getArrayMois().clear();
        adapterPerso2.getArrayMois().clear();
        Gauche();
    }
    private void Clear(AdapterPerso adapterPerso){
        adapterPerso.getArrayMois().clear();
        Gauche();
    }
    private void Deplacement(AdapterPerso adapterPerso){
        if (!TextUtils.isEmpty(zoneTxt.getText())){
            adapterPerso.getArrayMois().add(new Mois(zoneTxt.getText().toString(), true));
            zoneTxt.setText("");
            adapterPerso.notifyDataSetChanged();
        }else{
            Log.i("test", "test");
            Toast.makeText(this, "le champs vide", Toast.LENGTH_SHORT).show();
        }
    }

    private class LstClickItem implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            AdapterPerso adapterPerso = (AdapterPerso)parent.getAdapter();
            if (adapterPerso.getArrayMois().get(position).est_affiche())
                adapterPerso.getArrayMois().get(position).select();
            adapterPerso.notifyDataSetChanged();
        }
    }

}