package hr.fer.filip.model

import java.io.Serializable

data class RepoDetails(val id : Int, val name : String,val description : String, val owner : Owner) : Serializable {

}