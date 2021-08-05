package com.webskitters.assignment.ui.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.webskitters.assignment.R
import com.webskitters.assignment.databinding.FragmentHomeBinding
import com.webskitters.assignment.model.ImageListItem
import com.webskitters.assignment.repo.Repository


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {
    private var menuCheck: MenuItem? = null

    private val imageAdapter: ImageAdapter by lazy { ImageAdapter() }
    private var param1: String? = null
    private var param2: String? = null
    private val homeViewModel : HomeViewModel by viewModels()
//    lateinit var viewlaypoy:View

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

//        viewlaypoy=inflater.inflate(R.layout.fragment_home, container, false)
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        var repository=Repository()
//        var homeViewModelFactory = MainViewModelFactory(repository)
//        homeViewModel=ViewModelProvider(this,homeViewModelFactory).get(HomeViewModel::class.java)
        homeViewModel.getImageList()
        homeViewModel.myResponse.observe(requireActivity(), Observer {

            it.body().let {
                if (it != null) {
                    homeViewModel.insertData(it)

                    homeViewModel.getAllData.observe(viewLifecycleOwner, { data ->

                        imageAdapter.setData(data)
                    })

                }
            }

            Log.d("Abhishek", "====" + Gson().toJson(it.body()))

        })






        binding.imageList.apply {
            layoutManager=GridLayoutManager(requireActivity(), 2)
            adapter=imageAdapter
            imageAdapter.setListner(object : ImageAdapter.ImageAdapterClickListner {
                override fun onClick(position: Int, imageList: ImageListItem) {

                    if (imageList.IsSelected) {
                        imageList.IsSelected = false
                    } else {
                        imageList.IsSelected = true
                    }


//                    imageAdapter.updateRow(position,imageList)

                    homeViewModel.updateData(imageList)


                }
            })
        }

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.topbar_menu, menu)

        val search = menu.findItem(R.id.app_bar_search)
        menuCheck= menu!!.findItem(R.id.select_item)


        homeViewModel.getCount.observe(viewLifecycleOwner, { data ->
            menuCheck!!.title = data.toString()
            Log.d("Abhishek", "=COunt===" + data)
        })


        val searchView = search.actionView as SearchView


        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                imageAdapter.filter.filter(newText)
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.select_item -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

}