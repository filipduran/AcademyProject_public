package hr.fer.filip.model

import java.io.Serializable

data class Repository(val id : Int, val name : String, val description : String) : Serializable {
}
