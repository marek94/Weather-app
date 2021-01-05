package com.example.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.databinding.FragmentCityInputBinding
import com.example.weatherapp.utils.ResourceResolver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_city_input.*
import javax.inject.Inject

@AndroidEntryPoint
class CityInputFragment : Fragment() {

    @Inject
    lateinit var resourceResolver: ResourceResolver

    private val viewModel: CityInputViewModel by viewModels()

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentCityInputBinding.inflate(inflater, container, false).apply {
            vm = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        show_results.setOnClickListener {
            if (viewModel.validateInput()) {
                viewModel.getWeatherInCity(viewModel.city)
            }
        }

        viewModel.weatherData.observe(viewLifecycleOwner, Observer {
            it?.let {
                sharedViewModel.weatherData.value = it
                viewModel.weatherData.value = null
                val action = CityInputFragmentDirections.actionWeatherDetails()
                findNavController().navigate(action)
            }
        })

        viewModel.errors.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })

        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}