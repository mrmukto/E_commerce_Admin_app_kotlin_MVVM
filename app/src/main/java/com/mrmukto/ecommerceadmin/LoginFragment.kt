package com.mrmukto.ecommerceadmin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mrmukto.ecommerceadmin.databinding.FragmentLoginBinding

import com.mrmukto.ecommerceadmin.viewmodels.UserViewModel


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val userViewModel : UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      binding = FragmentLoginBinding.inflate(inflater,container,false)
       userViewModel.authLD.observe(viewLifecycleOwner){
            if (it == UserViewModel.Authentication.AUTHENTICATED){
               findNavController().popBackStack()
           }
       }
        userViewModel.errMsgLD.observe(viewLifecycleOwner){
            binding.errMsgTV.text=it
        }
         binding.loginBtn.setOnClickListener {
             val email = binding.emailET.text.toString()
            val pass = binding.passET.text.toString()
             userViewModel.loginAdmin(email,pass)
         }
        return binding.root
    }

}