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
public final class GetCarModelsUseCaseImpl_Factory implements Factory<GetCarModelsUseCaseImpl> {
  private final Provider<CarRepository> repositoryProvider;

  public GetCarModelsUseCaseImpl_Factory(Provider<CarRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetCarModelsUseCaseImpl get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetCarModelsUseCaseImpl_Factory create(Provider<CarRepository> repositoryProvider) {
    return new GetCarModelsUseCaseImpl_Factory(repositoryProvider);
  }

  public static GetCarModelsUseCaseImpl newInstance(CarRepository repository) {
    return new GetCarModelsUseCaseImpl(repository);
  }
}
