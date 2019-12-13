package com.example.androidproficiencyexercise.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproficiencyexercise.R
import com.example.androidproficiencyexercise.model.AboutCanada
import com.example.androidproficiencyexercise.util.getProgressDrawable
import com.example.androidproficiencyexercise.util.loadImage
import kotlinx.android.synthetic.main.item_canada.view.*

class CanadaListAdapter (var results: ArrayList<AboutCanada>) :
    RecyclerView.Adapter<CanadaListAdapter.CanadaViewHolder>() {

    fun updateCanadaData(newResults: List<AboutCanada>) {
        results.clear()
        results.addAll(newResults)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CanadaViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_canada, parent, false)
    )

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: CanadaViewHolder, position: Int) {
        holder.bind(results[position])
    }

    class CanadaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val articleName = view.title_textView
        private val hrefImage = view.href_image
        private val descriptionTextView = view.description_textView
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(result: AboutCanada) {
            articleName.text = result.title
            descriptionTextView.text = result.description
            hrefImage.loadImage(result.imageHref, progressDrawable)
        }
    }
}