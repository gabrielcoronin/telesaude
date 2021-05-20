package com.fiap.telesaude.com.fiap.telesaude.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fiap.telesaude.com.fiap.telesaude.ui.viewmodels.ConsultModel
import com.fiap.telesaude.databinding.ListItemConsultBinding
import com.fiap.telesaude.ui.fragments.ConsultationListFragment
import com.fiap.telesaude.ui.fragments.ConsultationListFragmentDirections

class ConsultListAdapter(private val fragment: ConsultationListFragment) :
    ListAdapter<ConsultModel, ConsultListAdapter.ViewHolder>(ConsultDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val consult = getItem(position)

        holder.apply {
            bind(fragment, consult)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemConsultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class ViewHolder(
        private val binding: ListItemConsultBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(fragment: ConsultationListFragment, item: ConsultModel) {
            binding.apply {
                textConsult.text = item.consultName
                textPrice.text = "R$ ${item.consultPrice}"
                executePendingBindings()
            }
            setupListener(fragment, item)
        }

        private fun setupListener(fragment: ConsultationListFragment, item: ConsultModel) {
            binding.apply {
                btnAddId.setOnClickListener {
                    fragment.navigate(
                        ConsultationListFragmentDirections.actionConsultationListFragmentToConsultationFragment(
                            item.consultName,
                            item.consultDescription,
                            item.consultId
                        )
                    )
                }
            }
        }
    }
}

private class ConsultDiffCallback : DiffUtil.ItemCallback<ConsultModel>() {

    override fun areItemsTheSame(oldItem: ConsultModel, newItem: ConsultModel): Boolean {
        return oldItem.consultName == newItem.consultName
    }

    override fun areContentsTheSame(oldItem: ConsultModel, newItem: ConsultModel): Boolean {
        return oldItem == newItem
    }
}