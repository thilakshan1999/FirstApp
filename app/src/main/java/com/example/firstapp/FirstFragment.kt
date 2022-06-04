package com.example.firstapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firstapp.Api.User
import com.example.firstapp.Api.UserApi
import com.example.firstapp.Api.UserApiInstance
import com.example.firstapp.databinding.FragmentFirstBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding?=null
    private val binding get()=_binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentFirstBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener(){
            val id=binding.editTextTextPersonId.text.toString()
            val user=UserApiInstance.api.getUser(id)
            user.enqueue(object:Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    val body=response.body()
                    body?.let {
                        binding.textView.text="Id:"+it.id+
                            "\nName:"+it.name+
                                "\nUserName:"+it.username+
                                "\nEmail:"+it.email

                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.i("FirstFragment", "error"+t.message)
                }

            })
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



