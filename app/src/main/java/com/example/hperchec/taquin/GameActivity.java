package com.example.hperchec.taquin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by hperchec on 18/05/18.
 */

public class GameActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    ImageAdapter adapter;
    ArrayList<Bitmap> aBitmap;
    Bitmap black;
    int gridSize;
    GridView gv;
    int[][] Matrice;
    int[][] MatriceReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        // Déclarer objet Bitmap (image entière)
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.sonic);
        black = BitmapFactory.decodeResource(getResources(), R.drawable.black);
        // Log.d("tag", String.valueOf(b.getHeight()));
        // Log.d("tag", String.valueOf(b.getWidth()));

        // Créer un ArrayList de Bitmap pour ImageAdapter
        aBitmap = new ArrayList<Bitmap>();

        // Récupération de la variable taille de la grille depuis l'activité PreGameActivity
        Intent res = getIntent();
        gridSize = res.getIntExtra("chosenSize", 1);
        // Log de la taille de la grille reçue en paramètre
        // Log.d("tag", String.valueOf(gridSize));

        Matrice = new int[gridSize][gridSize];

        MatriceReference = new int[gridSize][gridSize];

        int k=0;
        for(int i=0;i<gridSize;i++) {
            for (int j = 0; j < gridSize; j++) {
                Matrice[i][j] = k;
                MatriceReference[i][j] = k;
                k++;
            }
        }

        // Récupération de la GridView
        gv = (GridView) findViewById(R.id.gridview);
        gv.setNumColumns(gridSize);

        // Initialisation des évènements onClick sur la gridView
        gv.setOnItemClickListener(this);

        int taille=Math.min(b.getWidth(),b.getHeight())-1;
        // Log.d("tag", String.valueOf(gvColumnWidth));

        // Définition de la taille d'une case
        float ce = taille/gridSize;

        Log.d("tag", String.valueOf(ce));
        int cellSize=Math.round(ce);
        Log.d("tag", String.valueOf(cellSize));

        // Redimensionnement de l'image d'origine
        b = Bitmap.createBitmap(b, 0, 0,taille,taille);
        black = Bitmap.createBitmap(black, 0, 0,cellSize,cellSize);
        // Création des images découpées

        int posX = 0;
        int posY = 0;




        for (int line = 1; line <= gridSize; line++ ){
            for (int column = 1; column <= gridSize; column++){
                // Ajout de l'image entière au tableau
                if( (line * column) == (gridSize*gridSize) ){
                    aBitmap.add(black);
                }
                else{
                    Bitmap toto = Bitmap.createBitmap(b, posX, posY, cellSize, cellSize);
                    aBitmap.add(toto);
                }
                posX = posX + cellSize;
            }
            posY = posY + cellSize;
            posX = 0;

        }

        // Instanciation de ImageAdapter
        adapter = new ImageAdapter(this,aBitmap);

        // On lie l'adapter à la GridView
        gv.setAdapter(adapter);

        megaMelangeDeLaMortQuiTue();

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("click",String.valueOf(position));
       int ligne=position/gridSize;
        int col=position%gridSize;
        Log.d("tag", String.valueOf(position));
        int temp;
        Bitmap tempBmp;
        // Bas
        if( (ligne+1) < gridSize && Matrice[ligne+1][col]==8 )
        {
            temp = Matrice[ligne][col];
            Matrice[ligne][col] = 8;
            Matrice[ligne+1][col] = temp;
            tempBmp = aBitmap.get(position);
            aBitmap.set(position+gridSize, tempBmp);
            aBitmap.set(position, black);
        }
        // Haut
        else if((ligne-1) >= 0 && Matrice[ligne-1][col]==8 )
        {
            temp = Matrice[ligne][col];
            Matrice[ligne][col] = 8;
            Matrice[ligne-1][col] = temp;
            tempBmp = aBitmap.get(position);
            aBitmap.set(position-gridSize, tempBmp);
            aBitmap.set(position, black);
        }
        // Droite
        else if((col+1) < gridSize && Matrice[ligne][col+1]==8 )
        {
            temp = Matrice[ligne][col];
            Matrice[ligne][col] = 8;
            Matrice[ligne][col+1] = temp;
            tempBmp = aBitmap.get(position);
            aBitmap.set(position+1, tempBmp);
            aBitmap.set(position, black);
        }
        // Gauche
        else if( (col-1) >= 0 && Matrice[ligne][col-1]==8)
        {
            temp = Matrice[ligne][col];
            Matrice[ligne][col] = 8;
            Matrice[ligne][col-1] = temp;
            tempBmp = aBitmap.get(position);
            aBitmap.set(position-1, tempBmp);
            aBitmap.set(position, black);
        }

        // Instanciation de ImageAdapter
        adapter = new ImageAdapter(this,aBitmap);

        // On lie l'adapter à la GridView
        gv.setAdapter(adapter);

        checkImageOrder();

    }

    public void checkImageOrder(){
        int badValue = 0;
        for(int i=0;i<gridSize;i++) {
            for (int j = 0; j < gridSize; j++) {
                if (Matrice[i][j] != MatriceReference[i][j]) {
                    badValue = 1;
                }
            }
        }
        if( badValue == 0){
            Log.d("Check :", "Partie gagnée! Bravo!");
            Intent postGameActivity = new Intent(GameActivity.this, PostGameActivity.class);
            startActivity(postGameActivity);
        }
    }

    public void megaMelangeDeLaMortQuiTue(){
        int positionInitiale;
        Bitmap tempBmp;
        int tempMat;
        ArrayList<String> mouvements = new ArrayList<String>();
        mouvements.add("haut");
        mouvements.add("bas");
        mouvements.add("gauche");
        mouvements.add("droite");
        positionInitiale = (gridSize*gridSize) - 1;
        for (int i=0;i<2000;i++) {
            int move = 0 + (int)(Math.random() * ((3 - 0) + 1));
            int ligne = positionInitiale / gridSize;
            int col = positionInitiale % gridSize;
            if (mouvements.get(move) == "haut"){
                if((positionInitiale - gridSize) >= 0){
                    // On échange de position
                    tempBmp = aBitmap.get(positionInitiale - gridSize);
                    aBitmap.set(positionInitiale, tempBmp);
                    aBitmap.set(positionInitiale - gridSize, black);
                    positionInitiale = positionInitiale - gridSize;
                    Log.d("Pos", "Position : "+positionInitiale);
                    Log.d("Pos", "Ligne : "+ligne+" , Col : "+col);
                    /*tempMat = Matrice[ligne][col];/mnt/roon/users/hperchec/AndroidStudioProjects
                    Matrice[ligne][col] = 8;
                    Matrice[ligne-1][col] = tempMat;*/
                }
            }
            else if(mouvements.get(move) == "bas"){
                if((positionInitiale + gridSize) <= gridSize*gridSize -1 ){
                    // On échange de position
                    tempBmp = aBitmap.get(positionInitiale + gridSize);
                    aBitmap.set(positionInitiale, tempBmp);
                    aBitmap.set(positionInitiale + gridSize, black);
                    positionInitiale = positionInitiale + gridSize;
                    Log.d("Pos", "Position : "+positionInitiale);
                    Log.d("Pos", "Ligne : "+ligne+" , Col : "+col);
                    /*tempMat = Matrice[ligne][col];
                    Matrice[ligne][col] = 8;
                    Matrice[ligne+1][col] = tempMat;*/
                }
            }
            else if(mouvements.get(move) == "gauche"){
                if((positionInitiale - 1) >= 0){
                    // On échange de position
                    tempBmp = aBitmap.get(positionInitiale - 1);
                    aBitmap.set(positionInitiale, tempBmp);
                    aBitmap.set(positionInitiale - 1, black);
                    positionInitiale = positionInitiale - 1;
                    Log.d("Pos", "Position : "+positionInitiale);
                    Log.d("Pos", "Ligne : "+ligne+" , Col : "+col);
                    /* tempMat = Matrice[ligne][col];
                    Matrice[ligne][col] = 8;
                    Matrice[ligne][col-1] = tempMat; */
                }
            }
            else if(mouvements.get(move) == "droite"){
                if((positionInitiale + 1) <= gridSize*gridSize - 1){
                    // On échange de position
                    tempBmp = aBitmap.get(positionInitiale + 1);
                    aBitmap.set(positionInitiale, tempBmp);
                    aBitmap.set(positionInitiale + 1, black);
                    positionInitiale = positionInitiale + 1;
                    Log.d("Pos", "Position : "+positionInitiale);
                    Log.d("Pos", "Ligne : "+ligne+" , Col : "+col);
                    /* tempMat = Matrice[ligne][col];
                    Matrice[ligne][col] = 8;
                    Matrice[ligne][col+1] = tempMat;*/
                }
            }
        }

        // Instanciation de ImageAdapter
        adapter = new ImageAdapter(this,aBitmap);

        // On lie l'adapter à la GridView
        gv.setAdapter(adapter);

    }


}
