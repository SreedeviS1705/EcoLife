package com.scorepsc.ui.home.classroomCategory.descriptiveQuesAns

import android.content.Context
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.CardSliderAdapter
import com.scorepsc.R
import com.scorepsc.service.respose.homeBanner.descrpQuestAns.DescrptQuestAnsResponse
import com.scorepsc.ui.home.classroomCategory.ClassNotesContentAdapter
import com.scorepsc.util.URLImageParser

class DescQuesAnsAdapter(var mContext:Context, var mPdfInterface:IDescptQuesAnsPdfLoad) :
    CardSliderAdapter<DescQuesAnsAdapter.FuturePlusSliderViewHolder>() {
    private var mList: ArrayList<DescrptQuestAnsResponse.Datum> ?= null
    private lateinit var mAdditionalInfoAdapter:AdditionalInfoAdapter
    private lateinit var mAdditionalDocAdapter:AdditionalDocAdapter
    private lateinit var mContentAdapter: ClassNotesContentAdapter
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
        //return 10
    }
    public fun updateList(list: ArrayList<DescrptQuestAnsResponse.Datum>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuturePlusSliderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_single_des_ques_ans, parent, false)
        return FuturePlusSliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FuturePlusSliderViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        /*val item = sliderList?.get(holder.adapterPosition)
        Picasso.get()
            .load(item?.imageUrl)
            .resize(800, 800)
            .centerInside()
            .into(holder.imageSlider)*/

    }

    class FuturePlusSliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //var imageSlider:ImageView = view.findViewById(R.id.sliderImageId)
        var writtenNotesImgRecyclerView:RecyclerView = view.findViewById(R.id.writtenNotesImgRecyclerView)
        var studyDocRecyclerView:RecyclerView = view.findViewById(R.id.studyDocRecyclerView)
        var contentRecyclerView:RecyclerView = view.findViewById(R.id.contentRecyclerView)
        var description:TextView = view.findViewById(R.id.textView51)
        var question:TextView = view.findViewById(R.id.textView54)
        var heading1:TextView = view.findViewById(R.id.textView52)
        var heading2:TextView = view.findViewById(R.id.textView53)
    }

    override fun bindVH(holder: FuturePlusSliderViewHolder, position: Int){
        val item = mList?.get(position)
        if(item?.info?.isEmpty() == true) {
            holder.heading1.visibility = View.GONE
        } else {
            holder.heading1.visibility = View.VISIBLE
        }

        if(item?.docs?.isEmpty() == true) {
            holder.heading2.visibility = View.GONE
        } else {
            holder.heading2.visibility = View.VISIBLE
        }
        mAdditionalInfoAdapter = AdditionalInfoAdapter(item?.info, mContext)
        mAdditionalDocAdapter = AdditionalDocAdapter(item?.docs, mPdfInterface)
        mContentAdapter = ClassNotesContentAdapter(item?.content, mContext)

        val mManger = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL ,false)
        val mManger1 = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL ,false)
        val mManger2 = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL ,false)
        holder.itemView.run {
            /*Picasso.get()
                .load(item?.banner)
                .resize(1000, 800)
                .centerInside()
                .into(holder.imageSlider)*/
            holder.writtenNotesImgRecyclerView.adapter = mAdditionalInfoAdapter
            holder.writtenNotesImgRecyclerView.layoutManager = mManger
            holder.studyDocRecyclerView.adapter = mAdditionalDocAdapter
            holder.studyDocRecyclerView.layoutManager = mManger1
            holder.contentRecyclerView.adapter = mContentAdapter
            holder.contentRecyclerView.layoutManager = mManger2
            holder.description.text= item?.answer
            holder.question.text= item?.question
            loadHtmlContent(holder.description, item?.answer)
        }
    }

    private fun loadHtmlContent(description: TextView, notes: String?) {
        Log.d("loadHtmlContent", "loadHtmlContent: ")
        /*var htmlData:String = "<h1>Hello</h1>\n" +
                "<p style=\"color:red;\">rocky asdasda sdasd rocky asdasda sdasdrocky asdasda sdasd</p> <img src=\"https://toistudent.timesofindia.indiatimes.com/cms/newsimages/image/Sneha/note-3.jpg\">\n" +
                "\n" +
                "<p>&nbsp;</p>\n" +
                "\n" +
                "<p>rocky asdasda sdasdrocky asdasda sdasdrocky asdasda sdasdrocky asdasda sdasdrocky asdasda sdasdrocky asdasda sdasd</p>"*/
        val showData: String = notes!!.replace("\n", "")
        val p = URLImageParser(description, mContext)
        val htmlAsSpanned = Html.fromHtml(showData, p, null)
        description.text = htmlAsSpanned;
    }
}