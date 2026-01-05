package com.example.favorites.impl.data.repository;

import com.example.data.api.user.AuthService;
import com.example.favorites.impl.data.datasource.FavoriteCarDataSource;
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
public final class FavoriteCarRepositoryImpl_Factory implements Factory<FavoriteCarRepositoryImpl> {
  private final Provider<FavoriteCarDataSource> dataSourceProvider;

  private final Provider<AuthService> authServiceProvider;

  public FavoriteCarRepositoryImpl_Factory(Provider<FavoriteCarDataSource> dataSourceProvider,
      Provider<AuthService> authServiceProvider) {
    this.dataSourceProvider = dataSourceProvider;
    this.authServiceProvider = authServiceProvider;
  }

  @Override
  public FavoriteCarRepositoryImpl get() {
    return newInstance(dataSourceProvider.get(), authServiceProvider.get());
  }

  public static FavoriteCarRepositoryImpl_Factory create(
      Provider<FavoriteCarDataSource> dataSourceProvider,
      Provider<AuthService> authServiceProvider) {
    return new FavoriteCarRepositoryImpl_Factory(dataSourceProvider, authServiceProvider);
  }

  public static FavoriteCarRepositoryImpl newInstance(FavoriteCarDataSource dataSource,
      AuthService authService) {
    return new FavoriteCarRepositoryImpl(dataSource, authService);
  }
}
