package pcy.study.common.api

interface ApiCode {

    fun getHttpStatusCode(): Int
    fun getCode(): Int
    fun getMessage(): String
}