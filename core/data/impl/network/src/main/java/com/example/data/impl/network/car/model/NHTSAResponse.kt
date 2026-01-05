package com.example.data.impl.network.car.model

import com.example.data.api.car.model.CarMakeDataModel
import com.example.data.api.car.model.CarModelDataModel
import com.example.data.api.car.model.CarModelDetailsDataModel
import com.example.data.api.car.model.CarTrimDataModel
import com.example.data.api.car.model.CarYearsDataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NHTSAYearsResponse(
    @SerialName("Results")
    val results: List<YearResult>? = null
) {
    @Serializable
    data class YearResult(
        @SerialName("Model_Year")
        val modelYear: String? = null
    )

    fun toDataModel(): CarYearsDataModel {
        val years = (results ?: emptyList()).mapNotNull { it.modelYear?.toIntOrNull() }
        return CarYearsDataModel(
            minYear = years.minOrNull() ?: 2000,
            maxYear = years.maxOrNull() ?: 2024
        )
    }
}

@Serializable
data class NHTSAMakesResponse(
    @SerialName("Count")
    val count: Int? = null,
    @SerialName("Message")
    val message: String? = null,
    @SerialName("SearchCriteria")
    val searchCriteria: String? = null,
    @SerialName("Results")
    val results: List<MakeResult>? = null
) {
    @Serializable
    data class MakeResult(
        @SerialName("MakeId")
        val makeId: Int? = null,
        @SerialName("MakeName")
        val makeName: String? = null
    )

    fun toDataModel() = (results ?: emptyList()).mapNotNull {
        if (it.makeId != null && it.makeName != null) {
            CarMakeDataModel(
                makeId = it.makeId.toString(),
                makeDisplay = it.makeName,
                makeIsCommon = "1",
                makeCountry = "US"
            )
        } else null
    }
}

@Serializable
data class NHTSAModelsResponse(
    @SerialName("Results")
    val results: List<ModelResult>? = null
) {
    @Serializable
    data class ModelResult(
        @SerialName("Make_ID")
        val makeId: String? = null,
        @SerialName("Make_Name")
        val makeName: String? = null,
        @SerialName("Model_ID")
        val modelId: String? = null,
        @SerialName("Model_Name")
        val modelName: String? = null
    )

    fun toDataModel() = (results ?: emptyList()).mapNotNull {
        if (it.modelName != null && it.makeId != null) {
            CarModelDataModel(
                modelName = it.modelName,
                modelMakeId = it.makeId
            )
        } else null
    }
}

@Serializable
data class NHTSATrimsResponse(
    @SerialName("Results")
    val results: List<TrimResult>? = null
) {
    @Serializable
    data class TrimResult(
        @SerialName("Make_ID")
        val makeId: String? = null,
        @SerialName("Make_Name")
        val makeName: String? = null,
        @SerialName("Model_ID")
        val modelId: String? = null,
        @SerialName("Model_Name")
        val modelName: String? = null,
        @SerialName("Model_Year")
        val modelYear: String? = null,
        @SerialName("Trim")
        val trim: String? = null
    )

    fun toDataModel() = (results ?: emptyList()).mapNotNull {
        if (it.modelId != null && it.makeId != null && it.modelName != null) {
            CarTrimDataModel(
                modelId = it.modelId,
                modelMakeId = it.makeId,
                modelName = it.modelName,
                modelTrim = it.trim.orEmpty(),
                modelYear = it.modelYear.orEmpty()
            )
        } else null
    }
}

@Serializable
data class NHTSAModelDetailsResponse(
    @SerialName("Results")
    val results: List<ModelDetailsResult>? = null
) {
    @Serializable
    data class ModelDetailsResult(
        @SerialName("Make_ID")
        val makeId: String? = null,
        @SerialName("Make_Name")
        val makeName: String? = null,
        @SerialName("Model_ID")
        val modelId: String? = null,
        @SerialName("Model_Name")
        val modelName: String? = null,
        @SerialName("Model_Year")
        val modelYear: String? = null,
        @SerialName("Body_Class")
        val bodyClass: String? = null,
        @SerialName("Engine_Configuration")
        val engineConfiguration: String? = null,
        @SerialName("Drive_Type")
        val driveType: String? = null
    )

    fun toDataModel() = (results ?: emptyList()).firstOrNull()?.let {
        if (it.modelId != null && it.makeId != null && it.modelName != null) {
            CarModelDetailsDataModel(
                modelId = it.modelId,
                modelMakeId = it.makeId,
                modelName = it.modelName,
                modelYear = it.modelYear.orEmpty(),
                modelBody = it.bodyClass,
                modelEnginePowerHp = it.engineConfiguration,
                modelDrive = it.driveType
            )
        } else throw IllegalStateException("Model data incomplete")
    } ?: throw IllegalStateException("Model not found")
}

