package hr.fer.filip.model

import java.io.Serializable

data class Pull(val id : Int, val state : String, val title : String) : Serializable {
}