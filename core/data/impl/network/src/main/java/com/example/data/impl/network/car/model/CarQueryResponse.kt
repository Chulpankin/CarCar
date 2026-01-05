package com.example.data.impl.network.car.model

import com.example.data.api.car.model.CarMakeDataModel
import com.example.data.api.car.model.CarModelDataModel
import com.example.data.api.car.model.CarModelDetailsDataModel
import com.example.data.api.car.model.CarTrimDataModel
import com.example.data.api.car.model.CarYearsDataModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YearsResponse(
    @SerialName("Years")
    val years: YearsData
) {
    @Serializable
    data class YearsData(
        @SerialName("min_year")
        val minYear: String,
        @SerialName("max_year")
        val maxYear: String
    )

    fun toDataModel() = CarYearsDataModel(
        minYear = years.minYear.toInt(),
        maxYear = years.maxYear.toInt()
    )
}

@Serializable
data class MakesResponse(
    @SerialName("Makes")
    val makes: List<MakeData>
) {
    @Serializable
    data class MakeData(
        @SerialName("make_id")
        val makeId: String,
        @SerialName("make_display")
        val makeDisplay: String,
        @SerialName("make_is_common")
        val makeIsCommon: String,
        @SerialName("make_country")
        val makeCountry: String
    )

    fun toDataModel() = makes.map {
        CarMakeDataModel(
            makeId = it.makeId,
            makeDisplay = it.makeDisplay,
            makeIsCommon = it.makeIsCommon,
            makeCountry = it.makeCountry
        )
    }
}

@Serializable
data class ModelsResponse(
    @SerialName("Models")
    val models: List<ModelData>
) {
    @Serializable
    data class ModelData(
        @SerialName("model_name")
        val modelName: String,
        @SerialName("model_make_id")
        val modelMakeId: String
    )

    fun toDataModel() = models.map {
        CarModelDataModel(
            modelName = it.modelName,
            modelMakeId = it.modelMakeId
        )
    }
}

@Serializable
data class TrimsResponse(
    @SerialName("Trims")
    val trims: List<TrimData>
) {
    @Serializable
    data class TrimData(
        @SerialName("model_id")
        val modelId: String,
        @SerialName("model_make_id")
        val modelMakeId: String,
        @SerialName("model_name")
        val modelName: String,
        @SerialName("model_trim")
        val modelTrim: String,
        @SerialName("model_year")
        val modelYear: String
    )

    fun toDataModel() = trims.map {
        CarTrimDataModel(
            modelId = it.modelId,
            modelMakeId = it.modelMakeId,
            modelName = it.modelName,
            modelTrim = it.modelTrim,
            modelYear = it.modelYear
        )
    }
}

@Serializable
data class ModelResponse(
    val data: List<ModelDetailsData>
) {
    @Serializable
    data class ModelDetailsData(
        @SerialName("model_id")
        val modelId: String,
        @SerialName("model_make_id")
        val modelMakeId: String,
        @SerialName("model_name")
        val modelName: String,
        @SerialName("model_year")
        val modelYear: String,
        @SerialName("model_body")
        val modelBody: String? = null,
        @SerialName("model_engine_power_hp")
        val modelEnginePowerHp: String? = null,
        @SerialName("model_drive")
        val modelDrive: String? = null
    )

    fun toDataModel() = data.firstOrNull()?.let {
        CarModelDetailsDataModel(
            modelId = it.modelId,
            modelMakeId = it.modelMakeId,
            modelName = it.modelName,
            modelYear = it.modelYear,
            modelBody = it.modelBody,
            modelEnginePowerHp = it.modelEnginePowerHp,
            modelDrive = it.modelDrive
        )
    } ?: throw IllegalStateException("Model not found")
}
