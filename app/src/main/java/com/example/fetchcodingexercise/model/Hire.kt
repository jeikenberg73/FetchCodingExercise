package com.example.fetchcodingexercise.model

import kotlinx.serialization.Serializable


/**
 *  Hire data class
 *
 *  @property id The id of the hire object in the list
 *  @property listId The listed id of the object
 *  @property name The name of the hire object
 *
 *  This data class holds the properties of the hire object and makes it serializable to
 *  allow JSON objects to be placed into this data object.
 */
@Serializable
data class Hire(
    val id: Int,
    val listId: Int,
    val name: String?
)