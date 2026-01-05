package com.example.signup.impl.domain.usecase;

import com.example.data.api.user.AuthService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

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
public final class SignUpUseCaseImpl_Factory implements Factory<SignUpUseCaseImpl> {
  private final Provider<AuthService> authServiceProvider;

  private final Provider<CoroutineDispatcher> coroutineDispatcherProvider;

  public SignUpUseCaseImpl_Factory(Provider<AuthService> authServiceProvider,
      Provider<CoroutineDispatcher> coroutineDispatcherProvider) {
    this.authServiceProvider = authServiceProvider;
    this.coroutineDispatcherProvider = coroutineDispatcherProvider;
  }

  @Override
  public SignUpUseCaseImpl get() {
    return newInstance(authServiceProvider.get(), coroutineDispatcherProvider.get());
  }

  public static SignUpUseCaseImpl_Factory create(Provider<AuthService> authServiceProvider,
      Provider<CoroutineDispatcher> coroutineDispatcherProvider) {
    return new SignUpUseCaseImpl_Factory(authServiceProvider, coroutineDispatcherProvider);
  }

  public static SignUpUseCaseImpl newInstance(AuthService authService,
      CoroutineDispatcher coroutineDispatcher) {
    return new SignUpUseCaseImpl(authService, coroutineDispatcher);
  }
}
