package hr.fer.filip.academyproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import retrofit2.HttpException
import java.lang.Exception


fun <T> MutableLiveData<T>.toImmutable(): LiveData<T> = this

suspend fun <T> CoroutineScope.safeResponse(func : suspend CoroutineScope.() -> T) : Result<T> {
    return try {
        Result.Success(func.invoke(this))
    } catch(e : HttpException) {
        Result.Error(e)
    } catch (e : Exception) {
        Result.Error(e)
    }

}