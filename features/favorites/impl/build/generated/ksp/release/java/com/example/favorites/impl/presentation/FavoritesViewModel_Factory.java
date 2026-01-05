package com.example.favorites.impl.presentation;

import com.example.data.api.analytics.datasource.AnalyticsService;
import com.example.favorites.api.domain.repository.FavoriteCarRepository;
import com.example.favorites.api.domain.usecase.GetFavoriteCarsUseCase;
import com.example.favorites.api.navigation.FavoritesRouter;
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
public final class FavoritesViewModel_Factory implements Factory<FavoritesViewModel> {
  private final Provider<FavoritesRouter> routerProvider;

  private final Provider<GetFavoriteCarsUseCase> getFavoriteCarsUseCaseProvider;

  private final Provider<FavoriteCarRepository> favoriteCarRepositoryProvider;

  private final Provider<AnalyticsService> analyticsServiceProvider;

  public FavoritesViewModel_Factory(Provider<FavoritesRouter> routerProvider,
      Provider<GetFavoriteCarsUseCase> getFavoriteCarsUseCaseProvider,
      Provider<FavoriteCarRepository> favoriteCarRepositoryProvider,
      Provider<AnalyticsService> analyticsServiceProvider) {
    this.routerProvider = routerProvider;
    this.getFavoriteCarsUseCaseProvider = getFavoriteCarsUseCaseProvider;
    this.favoriteCarRepositoryProvider = favoriteCarRepositoryProvider;
    this.analyticsServiceProvider = analyticsServiceProvider;
  }

  @Override
  public FavoritesViewModel get() {
    return newInstance(routerProvider.get(), getFavoriteCarsUseCaseProvider.get(), favoriteCarRepositoryProvider.get(), analyticsServiceProvider.get());
  }

  public static FavoritesViewModel_Factory create(Provider<FavoritesRouter> routerProvider,
      Provider<GetFavoriteCarsUseCase> getFavoriteCarsUseCaseProvider,
      Provider<FavoriteCarRepository> favoriteCarRepositoryProvider,
      Provider<AnalyticsService> analyticsServiceProvider) {
    return new FavoritesViewModel_Factory(routerProvider, getFavoriteCarsUseCaseProvider, favoriteCarRepositoryProvider, analyticsServiceProvider);
  }

  public static FavoritesViewModel newInstance(FavoritesRouter router,
      GetFavoriteCarsUseCase getFavoriteCarsUseCase, FavoriteCarRepository favoriteCarRepository,
      AnalyticsService analyticsService) {
    return new FavoritesViewModel(router, getFavoriteCarsUseCase, favoriteCarRepository, analyticsService);
  }
}
