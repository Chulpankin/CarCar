package com.example.favorites.impl.data.datasource

import com.example.favorites.impl.data.model.FavoriteCarDataModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FavoriteCarDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    suspend fun getFavoriteCars(
        userId: String,
        pageSize: Int,
        lastDocumentId: String?
    ): Pair<List<FavoriteCarDataModel>, String?> {
        val baseQuery = firestore
            .collection(COLLECTION_USERS)
            .document(userId)
            .collection(COLLECTION_FAVORITES)
            .orderBy(FIELD_TIMESTAMP, Query.Direction.DESCENDING)

        val query = lastDocumentId?.let { id ->
            val encodedId = encodeDocumentId(id)
            firestore
                .collection(COLLECTION_USERS)
                .document(userId)
                .collection(COLLECTION_FAVORITES)
                .document(encodedId)
                .get()
                .await()
                .takeIf { it.exists() }
                ?.let { baseQuery.startAfter(it) }
        } ?: baseQuery

        val documents = query
            .limit(pageSize.toLong() + 1)
            .get()
            .await()
            .documents

        val itemsToReturn = documents.takeIf { it.size > pageSize }?.dropLast(1) ?: documents

        return Pair(
            itemsToReturn.mapNotNull { document ->
                document.toObject(FavoriteCarDataModel::class.java)?.let { car ->
                    car.copy(id = car.id.takeIf { it.isNotEmpty() } ?: decodeDocumentId(document.id))
                }
            },
            itemsToReturn.takeIf { documents.size > pageSize && it.isNotEmpty() }?.lastOrNull()?.let { doc ->
                doc.toObject(FavoriteCarDataModel::class.java)?.id?.takeIf { it.isNotEmpty() }
                    ?: decodeDocumentId(doc.id)
            }
        )
    }

    suspend fun addFavoriteCar(userId: String, car: FavoriteCarDataModel) {
        val encodedId = encodeDocumentId(car.id)
        firestore
            .collection(COLLECTION_USERS)
            .document(userId)
            .collection(COLLECTION_FAVORITES)
            .document(encodedId)
            .set(car.copy(id = car.id, timestamp = System.currentTimeMillis()))
            .await()
    }

    suspend fun removeFavoriteCar(userId: String, carId: String) {
        val encodedId = encodeDocumentId(carId)
        firestore
            .collection(COLLECTION_USERS)
            .document(userId)
            .collection(COLLECTION_FAVORITES)
            .document(encodedId)
            .delete()
            .await()
    }

    suspend fun isFavorite(userId: String, carId: String): Boolean {
        val encodedId = encodeDocumentId(carId)
        val document = firestore
            .collection(COLLECTION_USERS)
            .document(userId)
            .collection(COLLECTION_FAVORITES)
            .document(encodedId)
            .get()
            .await()
        return document.exists()
    }

    private fun encodeDocumentId(id: String): String {
        return id.replace("/", "_SLASH_")
            .replace("\\", "_BACKSLASH_")
    }

    private fun decodeDocumentId(encodedId: String): String {
        return encodedId.replace("_SLASH_", "/")
            .replace("_BACKSLASH_", "\\")
    }

    companion object {
        private const val COLLECTION_USERS = "users"
        private const val COLLECTION_FAVORITES = "favorites"
        private const val FIELD_TIMESTAMP = "timestamp"
    }
}

