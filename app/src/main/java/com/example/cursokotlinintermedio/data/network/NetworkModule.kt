package com.example.cursokotlinintermedio.data.network

import com.example.cursokotlinintermedio.data.RepositoryImp
import com.example.cursokotlinintermedio.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class) //Esta es el nivel se accesibilidad del paquete
object NetworkModule {

    @Provides //Con esto proveemos objetos en espeficico
    @Singleton //Este provider sera creado como un singleton
    fun providerRetrofit():Retrofit{
        return Retrofit
                        .Builder()
                        .baseUrl("https://newastro.vercel.app/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
    }

    @Provides
    fun provideHoroscopoApiService(retrofit: Retrofit):HoroscopeApiService{
        //este es el servicio que retorna el objetos con la interfaz y las acciones implementadas
        return retrofit.create(HoroscopeApiService::class.java);
    }

    @Provides
    fun provideRepository(apiService: HoroscopeApiService):Repository{
        return RepositoryImp(apiService);
    }
}

//Para poder hacer la inyeccion de dependencias en
//librerias o de paquetes de terceros, nosotos necesitamos de un
//objeto kotlin que debera ser considerado como un modulo por
//dagger