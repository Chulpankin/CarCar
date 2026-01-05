package com.example.favorites.impl.data.datasource;

import com.google.firebase.firestore.FirebaseFirestore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class FavoriteCarDataSource_Factory implements Factory<FavoriteCarDataSource> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  public FavoriteCarDataSource_Factory(Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public FavoriteCarDataSource get() {
    return newInstance(firestoreProvider.get());
  }

  public static FavoriteCarDataSource_Factory create(
      Provider<FirebaseFirestore> firestoreProvider) {
    return new FavoriteCarDataSource_Factory(firestoreProvider);
  }

  public static FavoriteCarDataSource newInstance(FirebaseFirestore firestore) {
    return new FavoriteCarDataSource(firestore);
  }
}
