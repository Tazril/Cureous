package com.taz.cureous.globalmodels

import com.google.gson.annotations.SerializedName

data class Issue(
    @SerializedName("ID") var id: Int,
    @SerializedName("Name") var name: String,
    @SerializedName("Description") var description: String?,
    @SerializedName("PossibleSymptoms") var possibleSymptons: String?,
    @SerializedName("Synonyms") var synonyms: String?,
    @SerializedName("MedicalCondition") var medicalConditions: String?,
    @SerializedName("ProfName") var profName: String?,
    @SerializedName("TreatmentDescription") var treatmentDescription: String?,
    @SerializedName("Accuracy") var accuracy: Double?,
    @SerializedName("Specialisation") var specialisationList: List<Specialisation>?
)