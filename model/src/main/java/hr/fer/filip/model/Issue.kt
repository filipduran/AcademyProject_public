package hr.fer.filip.model

import java.io.Serializable

data class Issue(val id : Int, val title : String, val state : String) : Serializable {
}