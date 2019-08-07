package com.taz.cureous.helper

object Urls {
    var TOKEN = ""
    const val LOGIN = "https://authservice.priaid.ch/login"
    const val DLOGIN = "https://sandbox-authservice.priaid.ch/login"
    const val HEADER = "Authorization: Bearer Pe62Q_GMAIL_COM_AUT:VdiH+aWrv2hhSAaG0UreeA=="
    const val DHEADER = "Authorization: Bearer tazrilparvez96@gmail.com:PzW8zrru9EvbnzemdV96tw=="
    const val LANGUAGE = "en-gb"
    const val ID = "ID"

    const val ISSUE_LIST = "issues"
    const val ISSUE_INFO = "issues/{$ID}/info"
    const val SYMPTOM_LIST = "symptoms"
    const val SYMPTOM_SUGGESTION = "symptoms/proposed"
    const val SPECIALISATIONS = "diagnosis/specialisations"
    const val DIAGNOSIS = "diagnosis"


}