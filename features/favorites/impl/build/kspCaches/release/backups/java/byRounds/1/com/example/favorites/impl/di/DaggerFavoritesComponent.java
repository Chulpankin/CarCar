package com.example.favorites.impl.di;

import androidx.lifecycle.ViewModel;
import com.example.common.utils.AppExceptionHandler;
import com.example.data.api.analytics.datasource.AnalyticsService;
import com.example.data.api.user.AuthService;
import com.example.favorites.api.domain.repository.FavoriteCarRepository;
import com.example.favorites.api.domain.usecase.GetFavoriteCarsUseCase;
import com.example.favorites.api.navigation.FavoritesRouter;
import com.example.favorites.impl.presentation.FavoritesViewModel;
import com.example.favorites.impl.presentation.FavoritesViewModel_Factory;
import com.example.ui.viewmodel.ViewModelProviderFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import javax.annotation.processing.Generated;

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
public final class DaggerFavoritesComponent {
  private DaggerFavoritesComponent() {
  }

  public static FavoritesComponent.Factory factory() {
    return new Factory();
  }

  private static final class Factory implements FavoritesComponent.Factory {
    @Override
    public FavoritesComponent create(FavoritesDeps favoritesDeps) {
      Preconditions.checkNotNull(favoritesDeps);
      return new FavoritesComponentImpl(favoritesDeps);
    }
  }

  private static final class FavoritesComponentImpl implements FavoritesComponent {
    private final FavoritesDeps favoritesDeps;

    private final FavoritesComponentImpl favoritesComponentImpl = this;

    private Provider<FavoritesRouter> favoritesRouterProvider;

    private Provider<GetFavoriteCarsUseCase> getFavoriteCarsUseCaseProvider;

    private Provider<FavoriteCarRepository> favoriteCarRepositoryProvider;

    private Provider<AnalyticsService> analyticsServiceProvider;

    private Provider<FavoritesViewModel> favoritesViewModelProvider;

    private FavoritesComponentImpl(FavoritesDeps favoritesDepsParam) {
      this.favoritesDeps = favoritesDepsParam;
      initialize(favoritesDepsParam);

    }

    private Map<Class<? extends ViewModel>, javax.inject.Provider<ViewModel>> mapOfClassOfAndProviderOfViewModel(
        ) {
      return Collections.<Class<? extends ViewModel>, javax.inject.Provider<ViewModel>>singletonMap(FavoritesViewModel.class, ((Provider) favoritesViewModelProvider));
    }

    @SuppressWarnings("unchecked")
    private void initialize(final FavoritesDeps favoritesDepsParam) {
      this.favoritesRouterProvider = new FavoritesRouterProvider(favoritesDepsParam);
      this.getFavoriteCarsUseCaseProvider = new GetFavoriteCarsUseCaseProvider(favoritesDepsParam);
      this.favoriteCarRepositoryProvider = new FavoriteCarRepositoryProvider(favoritesDepsParam);
      this.analyticsServiceProvider = new AnalyticsServiceProvider(favoritesDepsParam);
      this.favoritesViewModelProvider = FavoritesViewModel_Factory.create(favoritesRouterProvider, getFavoriteCarsUseCaseProvider, favoriteCarRepositoryProvider, analyticsServiceProvider);
    }

    @Override
    public FavoritesRouter favoritesRouter() {
      return Preconditions.checkNotNullFromComponent(favoritesDeps.favoritesRouter());
    }

    @Override
    public FavoriteCarRepository favoriteCarRepository() {
      return Preconditions.checkNotNullFromComponent(favoritesDeps.favoriteCarRepository());
    }

    @Override
    public GetFavoriteCarsUseCase getFavoriteCarsUseCase() {
      return Preconditions.checkNotNullFromComponent(favoritesDeps.getFavoriteCarsUseCase());
    }

    @Override
    public AppExceptionHandler appExceptionHandler() {
      return Preconditions.checkNotNullFromComponent(favoritesDeps.appExceptionHandler());
    }

    @Override
    public AuthService authService() {
      return Preconditions.checkNotNullFromComponent(favoritesDeps.authService());
    }

    @Override
    public AnalyticsService analyticsService() {
      return Preconditions.checkNotNullFromComponent(favoritesDeps.analyticsService());
    }

    @Override
    public ViewModelProviderFactory getViewModelFactory() {
      return new ViewModelProviderFactory(mapOfClassOfAndProviderOfViewModel());
    }

    private static final class FavoritesRouterProvider implements Provider<FavoritesRouter> {
      private final FavoritesDeps favoritesDeps;

      FavoritesRouterProvider(FavoritesDeps favoritesDeps) {
        this.favoritesDeps = favoritesDeps;
      }

      @Override
      public FavoritesRouter get() {
        return Preconditions.checkNotNullFromComponent(favoritesDeps.favoritesRouter());
      }
    }

    private static final class GetFavoriteCarsUseCaseProvider implements Provider<GetFavoriteCarsUseCase> {
      private final FavoritesDeps favoritesDeps;

      GetFavoriteCarsUseCaseProvider(FavoritesDeps favoritesDeps) {
        this.favoritesDeps = favoritesDeps;
      }

      @Override
      public GetFavoriteCarsUseCase get() {
        return Preconditions.checkNotNullFromComponent(favoritesDeps.getFavoriteCarsUseCase());
      }
    }

    private static final class FavoriteCarRepositoryProvider implements Provider<FavoriteCarRepository> {
      private final FavoritesDeps favoritesDeps;

      FavoriteCarRepositoryProvider(FavoritesDeps favoritesDeps) {
        this.favoritesDeps = favoritesDeps;
      }

      @Override
      public FavoriteCarRepository get() {
        return Preconditions.checkNotNullFromComponent(favoritesDeps.favoriteCarRepository());
      }
    }

    private static final class AnalyticsServiceProvider implements Provider<AnalyticsService> {
      private final FavoritesDeps favoritesDeps;

      AnalyticsServiceProvider(FavoritesDeps favoritesDeps) {
        this.favoritesDeps = favoritesDeps;
      }

      @Override
      public AnalyticsService get() {
        return Preconditions.checkNotNullFromComponent(favoritesDeps.analyticsService());
      }
    }
  }
}
