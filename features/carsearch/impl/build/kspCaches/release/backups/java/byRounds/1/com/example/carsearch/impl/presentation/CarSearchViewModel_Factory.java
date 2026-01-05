package com.example.carsearch.impl.presentation;

import com.example.carsearch.api.navigation.CarSearchRouter;
import com.example.common.utils.AppExceptionHandler;
import com.example.data.api.analytics.datasource.AnalyticsService;
import com.example.data.api.car.usecase.GetCarMakesUseCase;
import com.example.data.api.car.usecase.GetCarModelsUseCase;
import com.example.data.api.car.usecase.GetCarTrimsUseCase;
import com.example.data.api.car.usecase.GetCarYearsUseCase;
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
public final class CarSearchViewModel_Factory implements Factory<CarSearchViewModel> {
  private final Provider<CarSearchRouter> routerProvider;

  private final Provider<GetCarYearsUseCase> getCarYearsUseCaseProvider;

  private final Provider<GetCarMakesUseCase> getCarMakesUseCaseProvider;

  private final Provider<GetCarModelsUseCase> getCarModelsUseCaseProvider;

  private final Provider<GetCarTrimsUseCase> getCarTrimsUseCaseProvider;

  private final Provider<AppExceptionHandler> appExceptionHandlerProvider;

  private final Provider<AnalyticsService> analyticsServiceProvider;

  public CarSearchViewModel_Factory(Provider<CarSearchRouter> routerProvider,
      Provider<GetCarYearsUseCase> getCarYearsUseCaseProvider,
      Provider<GetCarMakesUseCase> getCarMakesUseCaseProvider,
      Provider<GetCarModelsUseCase> getCarModelsUseCaseProvider,
      Provider<GetCarTrimsUseCase> getCarTrimsUseCaseProvider,
      Provider<AppExceptionHandler> appExceptionHandlerProvider,
      Provider<AnalyticsService> analyticsServiceProvider) {
    this.routerProvider = routerProvider;
    this.getCarYearsUseCaseProvider = getCarYearsUseCaseProvider;
    this.getCarMakesUseCaseProvider = getCarMakesUseCaseProvider;
    this.getCarModelsUseCaseProvider = getCarModelsUseCaseProvider;
    this.getCarTrimsUseCaseProvider = getCarTrimsUseCaseProvider;
    this.appExceptionHandlerProvider = appExceptionHandlerProvider;
    this.analyticsServiceProvider = analyticsServiceProvider;
  }

  @Override
  public CarSearchViewModel get() {
    return newInstance(routerProvider.get(), getCarYearsUseCaseProvider.get(), getCarMakesUseCaseProvider.get(), getCarModelsUseCaseProvider.get(), getCarTrimsUseCaseProvider.get(), appExceptionHandlerProvider.get(), analyticsServiceProvider.get());
  }

  public static CarSearchViewModel_Factory create(Provider<CarSearchRouter> routerProvider,
      Provider<GetCarYearsUseCase> getCarYearsUseCaseProvider,
      Provider<GetCarMakesUseCase> getCarMakesUseCaseProvider,
      Provider<GetCarModelsUseCase> getCarModelsUseCaseProvider,
      Provider<GetCarTrimsUseCase> getCarTrimsUseCaseProvider,
      Provider<AppExceptionHandler> appExceptionHandlerProvider,
      Provider<AnalyticsService> analyticsServiceProvider) {
    return new CarSearchViewModel_Factory(routerProvider, getCarYearsUseCaseProvider, getCarMakesUseCaseProvider, getCarModelsUseCaseProvider, getCarTrimsUseCaseProvider, appExceptionHandlerProvider, analyticsServiceProvider);
  }

  public static CarSearchViewModel newInstance(CarSearchRouter router,
      GetCarYearsUseCase getCarYearsUseCase, GetCarMakesUseCase getCarMakesUseCase,
      GetCarModelsUseCase getCarModelsUseCase, GetCarTrimsUseCase getCarTrimsUseCase,
      AppExceptionHandler appExceptionHandler, AnalyticsService analyticsService) {
    return new CarSearchViewModel(router, getCarYearsUseCase, getCarMakesUseCase, getCarModelsUseCase, getCarTrimsUseCase, appExceptionHandler, analyticsService);
  }
}
