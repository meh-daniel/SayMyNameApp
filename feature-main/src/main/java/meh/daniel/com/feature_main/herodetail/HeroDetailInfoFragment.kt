package meh.daniel.com.feature_main.herodetail

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
import meh.daniel.com.feature_main.databinding.FragmentHerodetailinfoBinding

@AndroidEntryPoint
class HeroDetailInfoFragment : BaseFragment<HeroDetailInfoViewModel, FragmentHerodetailinfoBinding>(R.layout.fragment_herodetailinfo){

    override val viewModel: HeroDetailInfoViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHerodetailinfoBinding = FragmentHerodetailinfoBinding.inflate(inflater, container, false)

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
                photoNameCV.visibility = if (state is HeroDetailInfoState.Loaded) View.VISIBLE else View.GONE
                detailInfoCV.visibility = if (state is HeroDetailInfoState.Loaded) View.VISIBLE else View.GONE
                if(state is HeroDetailInfoState.Loaded) setData(state.hero)
                progressBar.visibility = if (state is HeroDetailInfoState.Loading) View.VISIBLE else View.GONE
                messageTxt.visibility = when(state) {
                    is HeroDetailInfoState.Empty -> View.VISIBLE
                    is HeroDetailInfoState.Error -> View.VISIBLE
                    else -> View.GONE
                }
                messageTxt.text = when(state) {
                    is HeroDetailInfoState.Empty -> getString(meh.daniel.com.core_ui.R.string.empty)
                    is HeroDetailInfoState.Error -> state.error
                    else -> ""
                }
            }
        }.launchIn(viewModel.viewModelScope)
    }

    private fun setData(hero: meh.daniel.com.serial_component.model.HeroDetails){
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