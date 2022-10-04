package meh.daniel.com.saymynameapp.presentation.screens.characterdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import meh.daniel.com.saymynameapp.R
import meh.daniel.com.saymynameapp.databinding.FragmentCharacterdetailinfoBinding
import meh.daniel.com.saymynameapp.domain.model.CharacterDetails
import meh.daniel.com.saymynameapp.core.BaseFragment

const val ID_CHARACTER = "id_character"

@AndroidEntryPoint
class CharacterDetailInfoFragment : BaseFragment<CharacterDetailInfoViewModel, FragmentCharacterdetailinfoBinding>(R.layout.fragment_characterdetailinfo){

    override val viewModel: CharacterDetailInfoViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCharacterdetailinfoBinding = FragmentCharacterdetailinfoBinding.inflate(inflater, container, false)

    override fun initialize() {
        arguments?.getString(ID_CHARACTER)?.let {
            viewModel.loadData(it.toInt())
        }
    }
    override fun setupSubscribers() {
        setupCharacterState()
    }
    private fun setupCharacterState() {
        viewModel.characterState.onEach { state ->
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
                    is CharacterDetailInfoState.Empty -> getString(R.string.empty)
                    is CharacterDetailInfoState.Error -> state.error
                    else -> ""
                }
            }
        }.launchIn(viewModel.viewModelScope)
    }

    private fun setData(hero: CharacterDetails){
        with(binding){
            Glide.with(photoCharacter)
                .load(hero.image)
                .into(photoCharacter)
            nameCharacter.text = hero.name
            birthdayCharacter.text = hero.birthday
            nicknameCharacter.text = hero.nickname
            statusCharacter.text = hero.status
        }
    }
}