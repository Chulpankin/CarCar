package com.example.data.impl.network.car.usecase;

import com.example.data.api.car.repository.CarRepository;
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
public final class GetCarMakesUseCaseImpl_Factory implements Factory<GetCarMakesUseCaseImpl> {
  private final Provider<CarRepository> repositoryProvider;

  public GetCarMakesUseCaseImpl_Factory(Provider<CarRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetCarMakesUseCaseImpl get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetCarMakesUseCaseImpl_Factory create(Provider<CarRepository> repositoryProvider) {
    return new GetCarMakesUseCaseImpl_Factory(repositoryProvider);
  }

  public static GetCarMakesUseCaseImpl newInstance(CarRepository repository) {
    return new GetCarMakesUseCaseImpl(repository);
  }
}
