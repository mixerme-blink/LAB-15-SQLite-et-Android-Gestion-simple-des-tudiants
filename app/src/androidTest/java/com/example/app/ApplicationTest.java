package projet.fst.ma.app;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import projet.fst.ma.app.classes.Etudiant;
import projet.fst.ma.app.service.EtudiantService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EtudiantService es = new EtudiantService(this);
        es.resetDatabase(); // repart de id=1 à chaque test

        // Insertion
        es.create(new Etudiant("ALAMI",   "ALI"));
        es.create(new Etudiant("RAMI",    "AMAL"));
        es.create(new Etudiant("SAFI",    "MHMED"));
        es.create(new Etudiant("SELAOUI", "REDA"));
        es.create(new Etudiant("ALAMI",   "WAFA"));

        // Liste initiale
        Log.d("TEST", "=== Liste initiale ===");
        for (Etudiant e : es.findAll())
            Log.d("TEST", e.toString());

        // Suppression id=3
        Etudiant aSupprimer = es.findById(3);
        if (aSupprimer != null) {
            es.delete(aSupprimer);
            Log.d("TEST", "Supprimé : " + aSupprimer.getNom());
        }

        // Liste après suppression
        Log.d("TEST", "=== Après suppression ===");
        for (Etudiant e : es.findAll())
            Log.d("TEST", e.toString());
    }
}