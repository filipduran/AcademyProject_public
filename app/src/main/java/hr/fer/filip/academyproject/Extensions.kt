package hr.fer.filip.academyproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


fun <T> MutableLiveData<T>.toImmutable(): LiveData<T> = this