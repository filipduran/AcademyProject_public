package hr.fer.filip.model

import java.io.Serializable

data class Contributor(val login : String, val id : Int, val contributions : Int) : Serializable {
}