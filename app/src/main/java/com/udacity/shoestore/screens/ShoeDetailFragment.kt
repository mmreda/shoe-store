package com.udacity.shoestore.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding

class ShoeDetailFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false)


        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        binding.CancelButton.setOnClickListener{
            // navigate back to shoe list screen
            findNavController().popBackStack()
        }

        binding.SaveButton.setOnClickListener{
            if (viewModel.allFieldsNotEmpty()){
                viewModel.buildShoe()
                viewModel.buildShoeComplete()
                // navigate back to shoe list screen
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Please Fill All Fields To Save",
                    Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}