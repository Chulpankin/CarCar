package com.example.data.impl.network.di;

import android.content.Context;
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
public final class NetworkProvidesModule_ProvideHttpClientFactory implements Factory<HttpClient> {
  private final Provider<Context> contextProvider;

  public NetworkProvidesModule_ProvideHttpClientFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public HttpClient get() {
    return provideHttpClient(contextProvider.get());
  }

  public static NetworkProvidesModule_ProvideHttpClientFactory create(
      Provider<Context> contextProvider) {
    return new NetworkProvidesModule_ProvideHttpClientFactory(contextProvider);
  }

  public static HttpClient provideHttpClient(Context context) {
    return Preconditions.checkNotNullFromProvides(NetworkProvidesModule.INSTANCE.provideHttpClient(context));
  }
}
