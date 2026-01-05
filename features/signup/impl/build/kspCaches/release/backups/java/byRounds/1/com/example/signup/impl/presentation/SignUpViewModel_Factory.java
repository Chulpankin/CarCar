package com.example.signup.impl.presentation;

import com.example.common.utils.AppExceptionHandler;
import com.example.common.utils.ResourceManager;
import com.example.data.api.analytics.datasource.AnalyticsService;
import com.example.signup.api.domain.usecase.SignUpUseCase;
import com.example.signup.api.navigation.SignUpRouter;
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
public final class SignUpViewModel_Factory implements Factory<SignUpViewModel> {
  private final Provider<SignUpRouter> routerProvider;

  private final Provider<SignUpUseCase> signUpUseCaseProvider;

  private final Provider<AppExceptionHandler> appExceptionHandlerProvider;

  private final Provider<ResourceManager> resourceManagerProvider;

  private final Provider<AnalyticsService> analyticsServiceProvider;

  public SignUpViewModel_Factory(Provider<SignUpRouter> routerProvider,
      Provider<SignUpUseCase> signUpUseCaseProvider,
      Provider<AppExceptionHandler> appExceptionHandlerProvider,
      Provider<ResourceManager> resourceManagerProvider,
      Provider<AnalyticsService> analyticsServiceProvider) {
    this.routerProvider = routerProvider;
    this.signUpUseCaseProvider = signUpUseCaseProvider;
    this.appExceptionHandlerProvider = appExceptionHandlerProvider;
    this.resourceManagerProvider = resourceManagerProvider;
    this.analyticsServiceProvider = analyticsServiceProvider;
  }

  @Override
  public SignUpViewModel get() {
    return newInstance(routerProvider.get(), signUpUseCaseProvider.get(), appExceptionHandlerProvider.get(), resourceManagerProvider.get(), analyticsServiceProvider.get());
  }

  public static SignUpViewModel_Factory create(Provider<SignUpRouter> routerProvider,
      Provider<SignUpUseCase> signUpUseCaseProvider,
      Provider<AppExceptionHandler> appExceptionHandlerProvider,
      Provider<ResourceManager> resourceManagerProvider,
      Provider<AnalyticsService> analyticsServiceProvider) {
    return new SignUpViewModel_Factory(routerProvider, signUpUseCaseProvider, appExceptionHandlerProvider, resourceManagerProvider, analyticsServiceProvider);
  }

  public static SignUpViewModel newInstance(SignUpRouter router, SignUpUseCase signUpUseCase,
      AppExceptionHandler appExceptionHandler, ResourceManager resourceManager,
      AnalyticsService analyticsService) {
    return new SignUpViewModel(router, signUpUseCase, appExceptionHandler, resourceManager, analyticsService);
  }
}
