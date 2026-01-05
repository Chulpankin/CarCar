package com.example.favorites.impl.domain.usecase;

import com.example.favorites.api.domain.repository.FavoriteCarRepository;
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
public final class IsFavoriteUseCaseImpl_Factory implements Factory<IsFavoriteUseCaseImpl> {
  private final Provider<FavoriteCarRepository> favoriteCarRepositoryProvider;

  public IsFavoriteUseCaseImpl_Factory(
      Provider<FavoriteCarRepository> favoriteCarRepositoryProvider) {
    this.favoriteCarRepositoryProvider = favoriteCarRepositoryProvider;
  }

  @Override
  public IsFavoriteUseCaseImpl get() {
    return newInstance(favoriteCarRepositoryProvider.get());
  }

  public static IsFavoriteUseCaseImpl_Factory create(
      Provider<FavoriteCarRepository> favoriteCarRepositoryProvider) {
    return new IsFavoriteUseCaseImpl_Factory(favoriteCarRepositoryProvider);
  }

  public static IsFavoriteUseCaseImpl newInstance(FavoriteCarRepository favoriteCarRepository) {
    return new IsFavoriteUseCaseImpl(favoriteCarRepository);
  }
}
