package com.example.testform


data class Voyage(
    val pays: String,
    val ville: String,
    val monuments: String,
    val avis: String,
    val imageUri: String? // Ajoutez la propriété pour stocker l'URI de l'image
)
