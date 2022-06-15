package com.mrmukto.ecommerceadmin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import com.mrmukto.ecommerceadmin.adapters.DashboardItemAdapter
import com.mrmukto.ecommerceadmin.databinding.FragmentDashboardBinding
import com.mrmukto.ecommerceadmin.models.DashboardItemType
import com.mrmukto.ecommerceadmin.viewmodels.UserViewModel

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private val userViewModel: UserViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        userViewModel.authLD.observe(viewLifecycleOwner) {
            if (it == UserViewModel.Authentication.UNAUTHENTICATED) {
                findNavController().navigate(R.id.action_dashboardFragment_to_loginFragment)
            }
        }
        val adapter = DashboardItemAdapter{
            navigateToDashboardItemPage(it)
        }
        val glm = GridLayoutManager(requireActivity(), 2)
        binding.dashboardRV.layoutManager = glm
        binding.dashboardRV.adapter = adapter
        return binding.root
    }

    private fun navigateToDashboardItemPage(it: DashboardItemType) {
        when(it) {
            DashboardItemType.ADD_PRODUCT -> findNavController().navigate(R.id.action_dashboardFragment_to_addProductFragment)
            DashboardItemType.VIEW_PRODUCT -> findNavController().navigate(R.id.action_dashboardFragment_to_viewProductFragment)
            DashboardItemType.CATEGORY -> findNavController().navigate(R.id.action_dashboardFragment_to_categoryFragment)
            DashboardItemType.SETTINGS -> findNavController().navigate(R.id.action_dashboardFragment_to_settingsFragment)
        }
    }

}