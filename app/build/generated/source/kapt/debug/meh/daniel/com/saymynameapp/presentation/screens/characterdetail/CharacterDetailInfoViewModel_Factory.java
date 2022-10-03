package meh.daniel.com.saymynameapp.presentation.screens.characterdetail;

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
public final class CharacterDetailInfoViewModel_Factory implements Factory<CharacterDetailInfoViewModel> {
  private final Provider<SerialRepository> repositoryProvider;

  public CharacterDetailInfoViewModel_Factory(Provider<SerialRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public CharacterDetailInfoViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static CharacterDetailInfoViewModel_Factory create(
      Provider<SerialRepository> repositoryProvider) {
    return new CharacterDetailInfoViewModel_Factory(repositoryProvider);
  }

  public static CharacterDetailInfoViewModel newInstance(SerialRepository repository) {
    return new CharacterDetailInfoViewModel(repository);
  }
}
