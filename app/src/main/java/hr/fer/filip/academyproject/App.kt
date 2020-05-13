package hr.fer.filip.academyproject

import android.app.Application
import hr.fer.filip.academyproject.network.Network

class App : Application() {


    override fun onCreate() {
        super.onCreate()

        Network.init(this)
    }
}