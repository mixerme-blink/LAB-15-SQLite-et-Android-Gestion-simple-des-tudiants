package projet.fst.ma.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import projet.fst.ma.app.classes.Etudiant;
import projet.fst.ma.app.service.EtudiantService;

public class MainActivity extends AppCompatActivity {

    private EditText etNom, etPrenom, etId;
    private TextView tvRes;
    private EtudiantService es;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        es       = new EtudiantService(this);
        etNom    = findViewById(R.id.nom);
        etPrenom = findViewById(R.id.prenom);
        etId     = findViewById(R.id.id);
        tvRes    = findViewById(R.id.res);

        Button btnValider    = findViewById(R.id.bn);
        Button btnChercher   = findViewById(R.id.load);
        Button btnSupprimer  = findViewById(R.id.delete);

        // ── VALIDER ────────────────────────────────────
        btnValider.setOnClickListener(v -> {
            String nom    = etNom.getText().toString().trim();
            String prenom = etPrenom.getText().toString().trim();

            if (nom.isEmpty() || prenom.isEmpty()) {
                Toast.makeText(this, "Nom et Prénom requis", Toast.LENGTH_SHORT).show();
                return;
            }

            es.create(new Etudiant(nom, prenom));
            etNom.setText("");
            etPrenom.setText("");
            Toast.makeText(this, "Étudiant ajouté ✓", Toast.LENGTH_SHORT).show();
        });

        // ── CHERCHER ───────────────────────────────────
        btnChercher.setOnClickListener(v -> {
            String txt = etId.getText().toString().trim();
            if (txt.isEmpty()) {
                Toast.makeText(this, "Saisir un id", Toast.LENGTH_SHORT).show();
                return;
            }

            Etudiant e = es.findById(Integer.parseInt(txt));
            if (e == null) {
                tvRes.setText("");
                Toast.makeText(this, "Étudiant introuvable", Toast.LENGTH_SHORT).show();
                return;
            }
            tvRes.setText(e.getNom() + " " + e.getPrenom());
        });

        // ── SUPPRIMER ──────────────────────────────────
        btnSupprimer.setOnClickListener(v -> {
            String txt = etId.getText().toString().trim();
            if (txt.isEmpty()) {
                Toast.makeText(this, "Saisir un id", Toast.LENGTH_SHORT).show();
                return;
            }

            Etudiant e = es.findById(Integer.parseInt(txt));
            if (e == null) {
                Toast.makeText(this, "Aucun étudiant à supprimer", Toast.LENGTH_SHORT).show();
                return;
            }

            es.delete(e);
            tvRes.setText("");
            etId.setText("");
            Toast.makeText(this, "Supprimé : " + e.getNom(), Toast.LENGTH_SHORT).show();
        });
    }
}