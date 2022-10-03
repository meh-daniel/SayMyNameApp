package meh.daniel.com.saymynameapp.presentation.screens.characterlist;

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
public final class CharacterListViewModel_Factory implements Factory<CharacterListViewModel> {
  private final Provider<SerialRepository> repositoryProvider;

  public CharacterListViewModel_Factory(Provider<SerialRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public CharacterListViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static CharacterListViewModel_Factory create(
      Provider<SerialRepository> repositoryProvider) {
    return new CharacterListViewModel_Factory(repositoryProvider);
  }

  public static CharacterListViewModel newInstance(SerialRepository repository) {
    return new CharacterListViewModel(repository);
  }
}
