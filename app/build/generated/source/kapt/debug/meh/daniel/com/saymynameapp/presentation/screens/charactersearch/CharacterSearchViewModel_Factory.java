package meh.daniel.com.saymynameapp.presentation.screens.charactersearch;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import meh.daniel.com.saymynameapp.domain.SerialRepository;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class CharacterSearchViewModel_Factory implements Factory<CharacterSearchViewModel> {
  private final Provider<SerialRepository> repositoryProvider;

  public CharacterSearchViewModel_Factory(Provider<SerialRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public CharacterSearchViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static CharacterSearchViewModel_Factory create(
      Provider<SerialRepository> repositoryProvider) {
    return new CharacterSearchViewModel_Factory(repositoryProvider);
  }

  public static CharacterSearchViewModel newInstance(SerialRepository repository) {
    return new CharacterSearchViewModel(repository);
  }
}
