package hr.fer.filip.model

import java.io.Serializable

data class Owner(val id : Int, val login : String, val type : String) : Serializable {
}