package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedViewModel
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false)

        binding. viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.AddNewShoe.setOnClickListener{
            findNavController().navigate(ShoeListFragmentDirections
                .actionShoeListFragmentToShoeDetailFragment())
        }

        viewModel.shoeList.observe(this.viewLifecycleOwner, Observer { shoeList->
            for (shoe in shoeList){
                addView(binding.linearLayout, shoe)
            }
        })

        @Suppress("DEPRECATION")
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logut_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       findNavController().navigate(ShoeListFragmentDirections
           .actionShoeListFragmentToLoginFragment())
        return true
    }

    private fun addView(parent: ViewGroup?, shoe: Shoe){
        val shoeBinding: ShoeItemBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.shoe_item, parent, false)

        shoeBinding.shoe = shoe
        binding.linearLayout.addView(shoeBinding.root)
    }
}