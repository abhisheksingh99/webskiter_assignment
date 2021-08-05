package com.webskitters.assignment.ui.fragments.add


import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.webskitters.assignment.data.WSData
import com.webskitters.assignment.databinding.FragmentAddUserBinding
import com.webskitters.assignment.ui.fragments.CommonViewModel
import com.webskitters.assignment.ui.fragments.user.UserViewModel
import com.webskitters.assignment.utils.BitmapManager
import java.io.IOException


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AddUserFragment : Fragment() , AddUserListner {

    private val args by navArgs<AddUserFragmentArgs>()
    private val mToDoViewModel: UserViewModel by viewModels()
    private val commonViewModel: CommonViewModel by viewModels()
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentAddUserBinding? = null
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

        _binding = FragmentAddUserBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.adduser = commonViewModel
         commonViewModel.addUserListner = this

        if (args!=null && args.currentItem!=null)
        {
            commonViewModel.setData(args.currentItem!!)
            val image: Bitmap = BitmapManager.byteToBitmap(args.currentItem!!.image!!)
            binding.imageView2.setImageBitmap(image)

        }

        binding.imageView2.setOnClickListener {
            mGetContent.launch("image/*")
        }




        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddUserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onsucess() {
        val newData = WSData(
            commonViewModel.id,
            commonViewModel.name.toString(),
            commonViewModel.email.toString(),
            commonViewModel.phNumber.toString(),
            commonViewModel.address.toString(),
            commonViewModel.userImage
        )
        if (commonViewModel.IsUpdate)
        {
            mToDoViewModel.updateData(newData)
        }
        else{
            mToDoViewModel.insertData(newData)
        }

        Toast.makeText(requireActivity(), "sucess", Toast.LENGTH_LONG).show()
    }

    override fun onError() {
        Toast.makeText(requireActivity(), "onError", Toast.LENGTH_LONG).show()
    }


    var mGetContent = registerForActivityResult(
        GetContent()
    ) { uri ->

        if (uri!=null)
        {
            commonViewModel.userImage= readBytes(requireContext(),uri)
            val image: Bitmap = BitmapManager.byteToBitmap(commonViewModel.userImage!!)
            binding.imageView2.setImageBitmap(image)

        }

    }


    @Throws(IOException::class)
    private fun readBytes(context: Context, uri: Uri): ByteArray? =
        context.contentResolver.openInputStream(uri)?.buffered()?.use { it.readBytes() }

}