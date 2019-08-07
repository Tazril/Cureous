package com.taz.cureous.specialisationlist.model

import com.taz.cureous.globalmodels.Specialisation
import com.taz.cureous.helper.Urls
import com.taz.cureous.helper.Urls.LANGUAGE
import com.taz.cureous.helper.Urls.TOKEN
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SpecialisationListApi {

    @GET(Urls.SPECIALISATIONS)
    fun getSpecialisationsListResponse(
        @Query("symptoms") symptomIdList: String,
        @Query("gender") gender: String,
        @Query("year_of_birth") yearOfBirth: Int,
        @Query("token") defToken: String = TOKEN,
        @Query("language") defLang: String = LANGUAGE
    ): Single<List<Specialisation>>
}

