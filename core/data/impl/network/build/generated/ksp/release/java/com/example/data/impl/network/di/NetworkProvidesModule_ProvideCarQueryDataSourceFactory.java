package com.example.data.impl.network.di;

import com.example.data.impl.network.car.datasource.CarQueryDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.ktor.client.HttpClient;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("com.example.common.di.AppScope")
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
public final class NetworkProvidesModule_ProvideCarQueryDataSourceFactory implements Factory<CarQueryDataSource> {
  private final Provider<HttpClient> httpClientProvider;

  public NetworkProvidesModule_ProvideCarQueryDataSourceFactory(
      Provider<HttpClient> httpClientProvider) {
    this.httpClientProvider = httpClientProvider;
  }

  @Override
  public CarQueryDataSource get() {
    return provideCarQueryDataSource(httpClientProvider.get());
  }

  public static NetworkProvidesModule_ProvideCarQueryDataSourceFactory create(
      Provider<HttpClient> httpClientProvider) {
    return new NetworkProvidesModule_ProvideCarQueryDataSourceFactory(httpClientProvider);
  }

  public static CarQueryDataSource provideCarQueryDataSource(HttpClient httpClient) {
    return Preconditions.checkNotNullFromProvides(NetworkProvidesModule.INSTANCE.provideCarQueryDataSource(httpClient));
  }
}
