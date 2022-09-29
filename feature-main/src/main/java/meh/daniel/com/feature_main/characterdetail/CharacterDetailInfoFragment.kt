package meh.daniel.com.feature_main.characterdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import meh.daniel.com.core.BaseFragment
import meh.daniel.com.feature_main.R
import meh.daniel.com.feature_main.databinding.FragmentCharacterdetailinfoBinding
import meh.daniel.com.serial_component.model.CharacterDetails

@AndroidEntryPoint
class CharacterDetailInfoFragment : BaseFragment<CharacterDetailInfoViewModel, FragmentCharacterdetailinfoBinding>(R.layout.fragment_characterdetailinfo){

    override val viewModel: CharacterDetailInfoViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCharacterdetailinfoBinding = FragmentCharacterdetailinfoBinding.inflate(inflater, container, false)

    override fun initialize() {
        arguments?.getString("IdHero")?.let {
            viewModel.loadData(it.toInt())
        }
    }
    override fun setupSubscribers() {
        setupHeroState()
    }
    private fun setupHeroState() {
        viewModel.heroState.onEach { state ->
            with(binding) {
                photoNameCV.visibility = if (state is CharacterDetailInfoState.Loaded) View.VISIBLE else View.GONE
                detailInfoCV.visibility = if (state is CharacterDetailInfoState.Loaded) View.VISIBLE else View.GONE
                if(state is CharacterDetailInfoState.Loaded) setData(state.hero)
                progressBar.visibility = if (state is CharacterDetailInfoState.Loading) View.VISIBLE else View.GONE
                messageTxt.visibility = when(state) {
                    is CharacterDetailInfoState.Empty -> View.VISIBLE
                    is CharacterDetailInfoState.Error -> View.VISIBLE
                    else -> View.GONE
                }
                messageTxt.text = when(state) {
                    is CharacterDetailInfoState.Empty -> getString(meh.daniel.com.core_ui.R.string.empty)
                    is CharacterDetailInfoState.Error -> state.error
                    else -> ""
                }
            }
        }.launchIn(viewModel.viewModelScope)
    }

    private fun setData(hero: CharacterDetails){
        with(binding){
            Glide.with(photoHero)
                .load(hero.image)
                .into(photoHero)
            nameHero.text = hero.name
            birthdayHero.text = hero.birthday
            nicknameHero.text = hero.nickname
            statusHero.text = hero.status
        }
    }
}