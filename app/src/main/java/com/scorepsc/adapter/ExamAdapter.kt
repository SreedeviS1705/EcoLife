package com.scorepsc.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.scorepsc.databinding.LayoutItemExamBinding
import com.scorepsc.service.respose.homeBanner.exam.ExamResponse
import com.scorepsc.ui.home.exam.IExamList

class ExamAdapter(var mClick:IExamList) :
    ListAdapter<ExamResponse.Datum, ExamAdapter.ExamViewHolder>(Companion) {

    private lateinit var onItemClick: (item: ExamResponse.Datum) -> Unit

    class ExamViewHolder(val binding: LayoutItemExamBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<ExamResponse.Datum>() {
        override fun areItemsTheSame(
            oldItem: ExamResponse.Datum,
            newItem: ExamResponse.Datum
        ): Boolean = oldItem.examId == newItem.examId

        override fun areContentsTheSame(
            oldItem: ExamResponse.Datum,
            newItem: ExamResponse.Datum
        ): Boolean = oldItem.examId == newItem.examId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutItemExamBinding.inflate(layoutInflater, parent, false)
        return ExamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExamViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.name.text = item.name
        holder.binding.subjectName.text = item.subjectName
        holder.binding.examCreatedOn.text = "Exam created on "+item.addedOn
        if(item.attended.equals("1")) {
            holder.binding.bottomContainer.visibility = View.VISIBLE
        } else {
            holder.binding.bottomContainer.visibility = View.GONE
        }
        //holder.binding.desc.text = item.topicName
        holder.binding.cloud.load(item.thumbnail) {
            /*crossfade(true)
            placeholder(ColorDrawable(Color.LTGRAY))
            error(ColorDrawable(Color.LTGRAY))
            transformations(RoundedCornersTransformation(8f))
            scale(Scale.FIT)*/
        }
        holder.itemView.setOnClickListener {
            onItemClick.invoke(item)
        }
        //Show Exam Result
        holder.binding?.textView111?.setOnClickListener {
            mClick.clickEvent()
        }
    }

    fun onItemClick(onItemClick: (ExamResponse.Datum) -> Unit) {
        this.onItemClick = onItemClick
    }
}