package com.example.data.impl.network.car.datasource

import com.example.data.api.car.model.CarMakeDataModel
import com.example.data.api.car.model.CarModelDataModel
import com.example.data.api.car.model.CarModelDetailsDataModel
import com.example.data.api.car.model.CarTrimDataModel
import com.example.data.api.car.model.CarYearsDataModel
import com.example.data.impl.network.car.model.MakesResponse
import com.example.data.impl.network.car.model.ModelResponse
import com.example.data.impl.network.car.model.ModelsResponse
import com.example.data.impl.network.car.model.TrimsResponse
import com.example.data.impl.network.car.model.YearsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders

class CarQueryDataSource(
    private val httpClient: HttpClient,
    private val baseUrl: String
) {

    suspend fun getYears(): CarYearsDataModel {
        return httpClient.get(baseUrl) {
            header(HttpHeaders.Accept, "application/json")
            parameter("cmd", "getYears")
        }.body<YearsResponse>().toDataModel()
    }

    suspend fun getMakes(year: Int?): List<CarMakeDataModel> {
        return httpClient.get(baseUrl) {
            header(HttpHeaders.Accept, "application/json")
            parameter("cmd", "getMakes")
            year?.let { parameter("year", it) }
        }.body<MakesResponse>().toDataModel()
    }

    suspend fun getModels(
        make: String,
        year: Int?,
        body: String?
    ): List<CarModelDataModel> {
        return httpClient.get(baseUrl) {
            header(HttpHeaders.Accept, "application/json")
            parameter("cmd", "getModels")
            parameter("make", make)
            year?.let { parameter("year", it) }
            body?.let { parameter("body", it) }
        }.body<ModelsResponse>().toDataModel()
    }

    suspend fun getTrims(
        make: String?,
        model: String?,
        year: Int?,
        body: String?,
        keyword: String?
    ): List<CarTrimDataModel> {
        if (make == null || model == null) return emptyList()
        
        return httpClient.get(baseUrl) {
            header(HttpHeaders.Accept, "application/json")
            parameter("cmd", "getTrims")
            parameter("make", make)
            parameter("model", model)
            year?.let { parameter("year", it) }
            body?.let { parameter("body", it) }
            keyword?.let { parameter("keyword", it) }
        }.body<TrimsResponse>().toDataModel()
    }

    suspend fun getModelById(modelId: String): CarModelDetailsDataModel {
        return httpClient.get(baseUrl) {
            header(HttpHeaders.Accept, "application/json")
            parameter("cmd", "getModel")
            parameter("model", modelId)
        }.body<ModelResponse>().toDataModel()
    }
}

