package com.joseph.kmmsocialapp.common.mapper

interface Mapper<From, To> {

    fun map(from: From): To
}