package com.example.cursokotlinintermedio.data.core.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val tokenManager: TokenManager):Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        //Response de return es la respuesta que se mandara a retrofit
        val request:Request = chain.request()//capturamos la peticon
                                   .newBuilder()//la reconstruimos
                                   .header("Autorization", tokenManager.getToken())//agregamos inf extra a la peticon recontruida
                                   .build(); //contruye la peticion
        return chain.proceed(request); //continua con la peticion pero con la nuestra
    }
}
//Los interceptores nos pueden ayudar a poder con el manejo de Tokens
//como esta en este ejemplo


//Hacemos un ejemplos de un manejador de token
class TokenManager @Inject constructor(){
    fun getToken():String = "Exaple token";
}