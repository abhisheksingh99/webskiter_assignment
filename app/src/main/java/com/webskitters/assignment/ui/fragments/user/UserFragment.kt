package com.webskitters.assignment.ui.fragments.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.webskitters.assignment.R
import com.webskitters.assignment.data.WSData
import com.webskitters.assignment.databinding.FragmentUserBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class UserFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private val userViewModel: UserViewModel by viewModels()

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val userAdapter: UserAdapter by lazy { UserAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        // Setup RecyclerView
        setupRecyclerview()

        binding.floatingActionButton.setOnClickListener {
            binding.floatingActionButton.findNavController().navigate(R.id.addUserFragment)
        }

        // Observe LiveData
        userViewModel.getAllData.observe(viewLifecycleOwner, { data ->

            userViewModel.checkIfDatabaseEmpty(data)
            userAdapter.setData(data)
        })

        return binding.root
    }

    private fun setupRecyclerview() {

        binding.imageList.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = userAdapter

            userAdapter.setListner(object : UserAdapter.UserAdapterClickListner{
                override fun onClick(position: Int, imageList: WSData) {

                    val action = UserFragmentDirections.actionUserFragmentToAddUserFragment(imageList)
                    findNavController().navigate(action)

                }

                override fun onLongClick(position: Int, imageList: WSData) {
                    DeleteUser(imageList)
                }

            })

        }



    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun DeleteUser(imageList: WSData) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Alert")
        builder.setMessage("are you sure want to delete the user?" )

        builder.setPositiveButton("Yes") { dialog, which ->

            userViewModel.deleteItem(imageList)

        }

        builder.setNegativeButton("No") { dialog, which ->

        }

        builder.show()

    }
}