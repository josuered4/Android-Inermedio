package com.example.cursokotlinintermedio.data.network

import com.example.cursokotlinintermedio.BuildConfig.BASE_URL
import com.example.cursokotlinintermedio.data.RepositoryImp
import com.example.cursokotlinintermedio.data.core.interceptors.AuthInterceptor
import com.example.cursokotlinintermedio.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class) //Esta es el nivel se accesibilidad del paquete
object NetworkModule {

    @Provides //Con esto proveemos objetos en espeficico
    @Singleton //Este provider sera creado como un singleton
    fun providerRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit
                        .Builder()
                        .baseUrl(BASE_URL)
                        .client(okHttpClient) //.client ayuda a configurar la peticion
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
    }

    @Provides
    @Singleton
    fun providerOkHttpClient(authInterceptor: AuthInterceptor):OkHttpClient{
        val interceptor:HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        //este interceptor nos muesta to do el cuerpo de la peticion
        //tambien los interceptores tienen diferentes tipos de log
        return OkHttpClient
                        .Builder()
                        .addInterceptor(interceptor)
                        .addInterceptor(authInterceptor) //se pueden agregar mas de un interceptor
                        .build();
    }

    @Provides
    fun provideHoroscopoApiService(retrofit: Retrofit):HoroscopeApiService{
        //este es el servicio que retorna el objetos con la interfaz y las acciones implementadas, pero es generico
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