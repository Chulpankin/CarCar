package com.example.carsearch.impl.di;

import androidx.lifecycle.ViewModel;
import com.example.carsearch.api.navigation.CarSearchRouter;
import com.example.carsearch.impl.presentation.CarSearchViewModel;
import com.example.carsearch.impl.presentation.CarSearchViewModel_Factory;
import com.example.common.utils.AppExceptionHandler;
import com.example.data.api.analytics.datasource.AnalyticsService;
import com.example.data.api.car.usecase.GetCarMakesUseCase;
import com.example.data.api.car.usecase.GetCarModelsUseCase;
import com.example.data.api.car.usecase.GetCarTrimsUseCase;
import com.example.data.api.car.usecase.GetCarYearsUseCase;
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
public final class DaggerCarSearchComponent {
  private DaggerCarSearchComponent() {
  }

  public static CarSearchComponent.Factory factory() {
    return new Factory();
  }

  private static final class Factory implements CarSearchComponent.Factory {
    @Override
    public CarSearchComponent create(CarSearchDeps carSearchDeps) {
      Preconditions.checkNotNull(carSearchDeps);
      return new CarSearchComponentImpl(carSearchDeps);
    }
  }

  private static final class CarSearchComponentImpl implements CarSearchComponent {
    private final CarSearchDeps carSearchDeps;

    private final CarSearchComponentImpl carSearchComponentImpl = this;

    private Provider<CarSearchRouter> carSearchRouterProvider;

    private Provider<GetCarYearsUseCase> getCarYearsUseCaseProvider;

    private Provider<GetCarMakesUseCase> getCarMakesUseCaseProvider;

    private Provider<GetCarModelsUseCase> getCarModelsUseCaseProvider;

    private Provider<GetCarTrimsUseCase> getCarTrimsUseCaseProvider;

    private Provider<AppExceptionHandler> appExceptionHandlerProvider;

    private Provider<AnalyticsService> analyticsServiceProvider;

    private Provider<CarSearchViewModel> carSearchViewModelProvider;

    private CarSearchComponentImpl(CarSearchDeps carSearchDepsParam) {
      this.carSearchDeps = carSearchDepsParam;
      initialize(carSearchDepsParam);

    }

    private Map<Class<? extends ViewModel>, javax.inject.Provider<ViewModel>> mapOfClassOfAndProviderOfViewModel(
        ) {
      return Collections.<Class<? extends ViewModel>, javax.inject.Provider<ViewModel>>singletonMap(CarSearchViewModel.class, ((Provider) carSearchViewModelProvider));
    }

    @SuppressWarnings("unchecked")
    private void initialize(final CarSearchDeps carSearchDepsParam) {
      this.carSearchRouterProvider = new CarSearchRouterProvider(carSearchDepsParam);
      this.getCarYearsUseCaseProvider = new GetCarYearsUseCaseProvider(carSearchDepsParam);
      this.getCarMakesUseCaseProvider = new GetCarMakesUseCaseProvider(carSearchDepsParam);
      this.getCarModelsUseCaseProvider = new GetCarModelsUseCaseProvider(carSearchDepsParam);
      this.getCarTrimsUseCaseProvider = new GetCarTrimsUseCaseProvider(carSearchDepsParam);
      this.appExceptionHandlerProvider = new AppExceptionHandlerProvider(carSearchDepsParam);
      this.analyticsServiceProvider = new AnalyticsServiceProvider(carSearchDepsParam);
      this.carSearchViewModelProvider = CarSearchViewModel_Factory.create(carSearchRouterProvider, getCarYearsUseCaseProvider, getCarMakesUseCaseProvider, getCarModelsUseCaseProvider, getCarTrimsUseCaseProvider, appExceptionHandlerProvider, analyticsServiceProvider);
    }

    @Override
    public CarSearchRouter carSearchRouter() {
      return Preconditions.checkNotNullFromComponent(carSearchDeps.carSearchRouter());
    }

    @Override
    public GetCarYearsUseCase getCarYearsUseCase() {
      return Preconditions.checkNotNullFromComponent(carSearchDeps.getCarYearsUseCase());
    }

    @Override
    public GetCarMakesUseCase getCarMakesUseCase() {
      return Preconditions.checkNotNullFromComponent(carSearchDeps.getCarMakesUseCase());
    }

    @Override
    public GetCarModelsUseCase getCarModelsUseCase() {
      return Preconditions.checkNotNullFromComponent(carSearchDeps.getCarModelsUseCase());
    }

    @Override
    public GetCarTrimsUseCase getCarTrimsUseCase() {
      return Preconditions.checkNotNullFromComponent(carSearchDeps.getCarTrimsUseCase());
    }

    @Override
    public AppExceptionHandler appExceptionHandler() {
      return Preconditions.checkNotNullFromComponent(carSearchDeps.appExceptionHandler());
    }

    @Override
    public AnalyticsService analyticsService() {
      return Preconditions.checkNotNullFromComponent(carSearchDeps.analyticsService());
    }

    @Override
    public ViewModelProviderFactory getViewModelFactory() {
      return new ViewModelProviderFactory(mapOfClassOfAndProviderOfViewModel());
    }

    private static final class CarSearchRouterProvider implements Provider<CarSearchRouter> {
      private final CarSearchDeps carSearchDeps;

      CarSearchRouterProvider(CarSearchDeps carSearchDeps) {
        this.carSearchDeps = carSearchDeps;
      }

      @Override
      public CarSearchRouter get() {
        return Preconditions.checkNotNullFromComponent(carSearchDeps.carSearchRouter());
      }
    }

    private static final class GetCarYearsUseCaseProvider implements Provider<GetCarYearsUseCase> {
      private final CarSearchDeps carSearchDeps;

      GetCarYearsUseCaseProvider(CarSearchDeps carSearchDeps) {
        this.carSearchDeps = carSearchDeps;
      }

      @Override
      public GetCarYearsUseCase get() {
        return Preconditions.checkNotNullFromComponent(carSearchDeps.getCarYearsUseCase());
      }
    }

    private static final class GetCarMakesUseCaseProvider implements Provider<GetCarMakesUseCase> {
      private final CarSearchDeps carSearchDeps;

      GetCarMakesUseCaseProvider(CarSearchDeps carSearchDeps) {
        this.carSearchDeps = carSearchDeps;
      }

      @Override
      public GetCarMakesUseCase get() {
        return Preconditions.checkNotNullFromComponent(carSearchDeps.getCarMakesUseCase());
      }
    }

    private static final class GetCarModelsUseCaseProvider implements Provider<GetCarModelsUseCase> {
      private final CarSearchDeps carSearchDeps;

      GetCarModelsUseCaseProvider(CarSearchDeps carSearchDeps) {
        this.carSearchDeps = carSearchDeps;
      }

      @Override
      public GetCarModelsUseCase get() {
        return Preconditions.checkNotNullFromComponent(carSearchDeps.getCarModelsUseCase());
      }
    }

    private static final class GetCarTrimsUseCaseProvider implements Provider<GetCarTrimsUseCase> {
      private final CarSearchDeps carSearchDeps;

      GetCarTrimsUseCaseProvider(CarSearchDeps carSearchDeps) {
        this.carSearchDeps = carSearchDeps;
      }

      @Override
      public GetCarTrimsUseCase get() {
        return Preconditions.checkNotNullFromComponent(carSearchDeps.getCarTrimsUseCase());
      }
    }

    private static final class AppExceptionHandlerProvider implements Provider<AppExceptionHandler> {
      private final CarSearchDeps carSearchDeps;

      AppExceptionHandlerProvider(CarSearchDeps carSearchDeps) {
        this.carSearchDeps = carSearchDeps;
      }

      @Override
      public AppExceptionHandler get() {
        return Preconditions.checkNotNullFromComponent(carSearchDeps.appExceptionHandler());
      }
    }

    private static final class AnalyticsServiceProvider implements Provider<AnalyticsService> {
      private final CarSearchDeps carSearchDeps;

      AnalyticsServiceProvider(CarSearchDeps carSearchDeps) {
        this.carSearchDeps = carSearchDeps;
      }

      @Override
      public AnalyticsService get() {
        return Preconditions.checkNotNullFromComponent(carSearchDeps.analyticsService());
      }
    }
  }
}
