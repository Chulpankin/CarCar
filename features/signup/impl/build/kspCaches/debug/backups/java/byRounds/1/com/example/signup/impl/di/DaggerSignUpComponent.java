package com.example.signup.impl.di;

import androidx.lifecycle.ViewModel;
import com.example.common.utils.AppExceptionHandler;
import com.example.common.utils.ResourceManager;
import com.example.data.api.analytics.datasource.AnalyticsService;
import com.example.data.api.user.AuthService;
import com.example.signup.api.domain.usecase.SignUpUseCase;
import com.example.signup.api.navigation.SignUpRouter;
import com.example.signup.impl.presentation.SignUpViewModel;
import com.example.signup.impl.presentation.SignUpViewModel_Factory;
import com.example.ui.viewmodel.ViewModelProviderFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.CoroutineDispatcher;

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
public final class DaggerSignUpComponent {
  private DaggerSignUpComponent() {
  }

  public static SignUpComponent.Factory factory() {
    return new Factory();
  }

  private static final class Factory implements SignUpComponent.Factory {
    @Override
    public SignUpComponent create(SignUpDeps signUpDeps) {
      Preconditions.checkNotNull(signUpDeps);
      return new SignUpComponentImpl(signUpDeps);
    }
  }

  private static final class SignUpComponentImpl implements SignUpComponent {
    private final SignUpDeps signUpDeps;

    private final SignUpComponentImpl signUpComponentImpl = this;

    private Provider<SignUpRouter> signUpRouterProvider;

    private Provider<SignUpUseCase> signUpUseCaseProvider;

    private Provider<AppExceptionHandler> appExceptionHandlerProvider;

    private Provider<ResourceManager> resourceManagerProvider;

    private Provider<AnalyticsService> analyticsServiceProvider;

    private Provider<SignUpViewModel> signUpViewModelProvider;

    private SignUpComponentImpl(SignUpDeps signUpDepsParam) {
      this.signUpDeps = signUpDepsParam;
      initialize(signUpDepsParam);

    }

    private Map<Class<? extends ViewModel>, javax.inject.Provider<ViewModel>> mapOfClassOfAndProviderOfViewModel(
        ) {
      return Collections.<Class<? extends ViewModel>, javax.inject.Provider<ViewModel>>singletonMap(SignUpViewModel.class, ((Provider) signUpViewModelProvider));
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SignUpDeps signUpDepsParam) {
      this.signUpRouterProvider = new SignUpRouterProvider(signUpDepsParam);
      this.signUpUseCaseProvider = new SignUpUseCaseProvider(signUpDepsParam);
      this.appExceptionHandlerProvider = new AppExceptionHandlerProvider(signUpDepsParam);
      this.resourceManagerProvider = new ResourceManagerProvider(signUpDepsParam);
      this.analyticsServiceProvider = new AnalyticsServiceProvider(signUpDepsParam);
      this.signUpViewModelProvider = SignUpViewModel_Factory.create(signUpRouterProvider, signUpUseCaseProvider, appExceptionHandlerProvider, resourceManagerProvider, analyticsServiceProvider);
    }

    @Override
    public SignUpRouter signUpRouter() {
      return Preconditions.checkNotNullFromComponent(signUpDeps.signUpRouter());
    }

    @Override
    public AuthService authService() {
      return Preconditions.checkNotNullFromComponent(signUpDeps.authService());
    }

    @Override
    public SignUpUseCase signUpUseCase() {
      return Preconditions.checkNotNullFromComponent(signUpDeps.signUpUseCase());
    }

    @Override
    public CoroutineDispatcher coroutineDispatcher() {
      return Preconditions.checkNotNullFromComponent(signUpDeps.coroutineDispatcher());
    }

    @Override
    public AppExceptionHandler appExceptionHandler() {
      return Preconditions.checkNotNullFromComponent(signUpDeps.appExceptionHandler());
    }

    @Override
    public ResourceManager resourceManager() {
      return Preconditions.checkNotNullFromComponent(signUpDeps.resourceManager());
    }

    @Override
    public AnalyticsService analyticsService() {
      return Preconditions.checkNotNullFromComponent(signUpDeps.analyticsService());
    }

    @Override
    public ViewModelProviderFactory getViewModelFactory() {
      return new ViewModelProviderFactory(mapOfClassOfAndProviderOfViewModel());
    }

    private static final class SignUpRouterProvider implements Provider<SignUpRouter> {
      private final SignUpDeps signUpDeps;

      SignUpRouterProvider(SignUpDeps signUpDeps) {
        this.signUpDeps = signUpDeps;
      }

      @Override
      public SignUpRouter get() {
        return Preconditions.checkNotNullFromComponent(signUpDeps.signUpRouter());
      }
    }

    private static final class SignUpUseCaseProvider implements Provider<SignUpUseCase> {
      private final SignUpDeps signUpDeps;

      SignUpUseCaseProvider(SignUpDeps signUpDeps) {
        this.signUpDeps = signUpDeps;
      }

      @Override
      public SignUpUseCase get() {
        return Preconditions.checkNotNullFromComponent(signUpDeps.signUpUseCase());
      }
    }

    private static final class AppExceptionHandlerProvider implements Provider<AppExceptionHandler> {
      private final SignUpDeps signUpDeps;

      AppExceptionHandlerProvider(SignUpDeps signUpDeps) {
        this.signUpDeps = signUpDeps;
      }

      @Override
      public AppExceptionHandler get() {
        return Preconditions.checkNotNullFromComponent(signUpDeps.appExceptionHandler());
      }
    }

    private static final class ResourceManagerProvider implements Provider<ResourceManager> {
      private final SignUpDeps signUpDeps;

      ResourceManagerProvider(SignUpDeps signUpDeps) {
        this.signUpDeps = signUpDeps;
      }

      @Override
      public ResourceManager get() {
        return Preconditions.checkNotNullFromComponent(signUpDeps.resourceManager());
      }
    }

    private static final class AnalyticsServiceProvider implements Provider<AnalyticsService> {
      private final SignUpDeps signUpDeps;

      AnalyticsServiceProvider(SignUpDeps signUpDeps) {
        this.signUpDeps = signUpDeps;
      }

      @Override
      public AnalyticsService get() {
        return Preconditions.checkNotNullFromComponent(signUpDeps.analyticsService());
      }
    }
  }
}
