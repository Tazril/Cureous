package com.taz.cureous.symptomsuggestions.model

import com.taz.cureous.globalmodels.Symptom
import com.taz.cureous.helper.Urls
import com.taz.cureous.helper.Urls.LANGUAGE
import com.taz.cureous.helper.Urls.TOKEN
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SymptomSuggestionListApi {

    @GET(Urls.SYMPTOM_SUGGESTION)
    fun getSymptomsListResponse(
        @Query("symptoms") symptomIdList: String,
        @Query("gender") gender: String,
        @Query("year_of_birth") yearOfBirth: Int,
        @Query("token") defToken: String = TOKEN,
        @Query("language") defLang: String = LANGUAGE
    ): Single<List<Symptom>>
}

//Testing
//fun main(args: Array<String>) {
//    TOKEN =
//        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRhenJpbHBhcnZlejk2QGdtYWlsLmNvbSIsInJvbGUiOiJVc2VyIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvc2lkIjoiMjY2NiIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvdmVyc2lvbiI6IjEwOSIsImh0dHA6Ly9leGFtcGxlLm9yZy9jbGFpbXMvbGltaXQiOiIxMDAiLCJodHRwOi8vZXhhbXBsZS5vcmcvY2xhaW1zL21lbWJlcnNoaXAiOiJCYXNpYyIsImh0dHA6Ly9leGFtcGxlLm9yZy9jbGFpbXMvbGFuZ3VhZ2UiOiJlbi1nYiIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvZXhwaXJhdGlvbiI6IjIwOTktMTItMzEiLCJodHRwOi8vZXhhbXBsZS5vcmcvY2xhaW1zL21lbWJlcnNoaXBzdGFydCI6IjIwMTktMDctMDMiLCJpc3MiOiJodHRwczovL2F1dGhzZXJ2aWNlLnByaWFpZC5jaCIsImF1ZCI6Imh0dHBzOi8vaGVhbHRoc2VydmljZS5wcmlhaWQuY2giLCJleHAiOjE1NjI1MjA3NDksIm5iZiI6MTU2MjUxMzU0OX0.2boQje2Jue7kuJs1ZdMSJPsjZvh9GMHfX5b7sxO00PE"
//    val api = ApiClient.retroClient.create(SymptomSuggestionListApi::class.java)
//    api.getSymptomsListResponse().enqueue(object : Callback<List<Symptom>> {
//        override fun onFailure(call: Call<List<Symptom>>, t: Throwable) {
//
//        }
//
//        override fun onResponse(call: Call<List<Symptom>>, response: Response<List<Symptom>>) {
//            if (response.isSuccessful)
//                println(response.body().toString())
//            else
//                print(response.code().toString())
//
//            println("asdasdad")
//        }
//    })
//}