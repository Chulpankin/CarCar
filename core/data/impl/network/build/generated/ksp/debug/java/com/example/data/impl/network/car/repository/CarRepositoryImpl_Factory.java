package com.example.data.impl.network.car.repository;

import com.example.data.impl.network.car.datasource.CarQueryDataSource;
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
public final class CarRepositoryImpl_Factory implements Factory<CarRepositoryImpl> {
  private final Provider<CarQueryDataSource> dataSourceProvider;

  public CarRepositoryImpl_Factory(Provider<CarQueryDataSource> dataSourceProvider) {
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public CarRepositoryImpl get() {
    return newInstance(dataSourceProvider.get());
  }

  public static CarRepositoryImpl_Factory create(Provider<CarQueryDataSource> dataSourceProvider) {
    return new CarRepositoryImpl_Factory(dataSourceProvider);
  }

  public static CarRepositoryImpl newInstance(CarQueryDataSource dataSource) {
    return new CarRepositoryImpl(dataSource);
  }
}
