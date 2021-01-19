package com.pparreno.cameratrial.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.pparreno.cameratrial.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel

    private lateinit var profileBinding: FragmentProfileBinding

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        profileViewModel =
                ViewModelProvider(this).get(ProfileViewModel::class.java)
        profileBinding = FragmentProfileBinding.inflate(inflater, container, false)

        recyclerView = profileBinding.recyclerView

        return profileBinding.root
    }

    override fun onStart() {
        super.onStart()
        // initialize staggered grid layout manager
        StaggeredGridLayoutManager(
            2, // span count
            StaggeredGridLayoutManager.VERTICAL // orientation
        ).apply {
            // specify the layout manager for recycler view
            this.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            recyclerView.layoutManager = this
        }

        // finally, data bind the recycler view with adapter
        recyclerView.adapter = ProfileRecyclerViewAdapter()
    }
}