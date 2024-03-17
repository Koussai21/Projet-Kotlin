package com.example.testform

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val PICK_IMAGE = 100
    private var imageUri: Uri? = null
    private var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Créer un conteneur LinearLayout vertical
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.CENTER
        layout.setBackgroundColor(Color.parseColor("#8A2BE2")) // Couleur de fond violet clair
        layout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )

        // Ajouter un champ de texte pour le pays avec marge à gauche de 10%
        val editTextCountry = EditText(this)
        editTextCountry.hint = "Pays"
        editTextCountry.setTextColor(Color.WHITE) // Texte en blanc
        editTextCountry.setBackgroundColor(Color.TRANSPARENT) // Fond transparent
        val countryLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        countryLayoutParams.setMargins(
            (0.1 * resources.displayMetrics.widthPixels).toInt(),
            0,
            0,
            0
        ) // Marge à gauche de 10%
        editTextCountry.layoutParams = countryLayoutParams
        layout.addView(editTextCountry)

        // Ajouter un champ de texte pour la ville avec marge à gauche de 10%
        val editTextCity = EditText(this)
        editTextCity.hint = "Ville"
        editTextCity.setTextColor(Color.WHITE) // Texte en blanc
        editTextCity.setBackgroundColor(Color.TRANSPARENT) // Fond transparent
        val cityLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        cityLayoutParams.setMargins(
            (0.1 * resources.displayMetrics.widthPixels).toInt(),
            0,
            0,
            0
        ) // Marge à gauche de 10%
        editTextCity.layoutParams = cityLayoutParams
        layout.addView(editTextCity)

        // Ajouter un champ de texte pour les monuments notables avec marge à gauche de 10%
        val editTextMonuments = EditText(this)
        editTextMonuments.hint = "Monuments notables"
        editTextMonuments.setTextColor(Color.WHITE) // Texte en blanc
        editTextMonuments.setBackgroundColor(Color.TRANSPARENT) // Fond transparent
        val monumentsLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        monumentsLayoutParams.setMargins(
            (0.1 * resources.displayMetrics.widthPixels).toInt(),
            0,
            0,
            0
        ) // Marge à gauche de 10%
        editTextMonuments.layoutParams = monumentsLayoutParams
        layout.addView(editTextMonuments)

        // Ajouter un champ de texte pour l'avis avec marge à gauche de 10%
        val editTextReview = EditText(this)
        editTextReview.hint = "Avis"
        editTextReview.setTextColor(Color.WHITE) // Texte en blanc
        editTextReview.setBackgroundColor(Color.TRANSPARENT) // Fond transparent
        val reviewLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        reviewLayoutParams.setMargins(
            (0.1 * resources.displayMetrics.widthPixels).toInt(),
            0,
            0,
            0
        ) // Marge à gauche de 10%
        editTextReview.layoutParams = reviewLayoutParams
        layout.addView(editTextReview)

        // Ajouter un bouton pour sélectionner une image
        val selectImageButton = Button(this)
        selectImageButton.text = "Ajouter une photo"
        selectImageButton.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        selectImageButton.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, PICK_IMAGE)
        }
        layout.addView(selectImageButton)

        // Ajouter un ImageView pour afficher l'image sélectionnée
        imageView = ImageView(this)
        imageView!!.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layout.addView(imageView)

        // Ajouter un bouton pour soumettre le formulaire
        val submitButton = Button(this)
        submitButton.text = "Soumettre"
        submitButton.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        submitButton.setOnClickListener {
            val pays = editTextCountry.text.toString()
            val ville = editTextCity.text.toString()
            val monuments = editTextMonuments.text.toString()
            val avis = editTextReview.text.toString()

            // Traiter les données du formulaire (vous pouvez ajouter votre logique ici)
            val message = "Pays: $pays\nVille: $ville\nMonuments notables: $monuments\nAvis: $avis"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            val voyage = Voyage(pays, ville, monuments, avis, imageUri.toString())
            val intent = Intent(this, DisplayInformation::class.java)
            intent.putExtra("voyage", voyage)
            startActivity(intent)
        }
        layout.addView(submitButton)


        // Définir le layout comme contenu de l'activité
        setContentView(layout)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data?.data
            imageView?.setImageURI(imageUri)
        }
    }
}
