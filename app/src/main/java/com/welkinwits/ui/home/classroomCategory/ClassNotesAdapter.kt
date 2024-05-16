package com.welkinwits.ui.home.classroomCategory

import android.content.Context
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.welkinwits.R
import com.github.islamkhsh.CardSliderAdapter
import com.welkinwits.service.respose.homeBanner.chapterWiseStudyNotes.ChapterWiseStudyNotesResponce
import com.welkinwits.util.URLImageParser

class ClassNotesAdapter(var mContext:Context, var mPdfInterface:IPdfLoad) :
    CardSliderAdapter<ClassNotesAdapter.FuturePlusSliderViewHolder>() {
    private var mList: ArrayList<ChapterWiseStudyNotesResponce.Datum> ?= null
    private lateinit var mWrittenNotesAdapter:ClassWrittenNotesAdapter
    private lateinit var mClassStudyDocAdapter:ClassStudyDocAdapter
    private lateinit var mContentAdapter:ClassNotesContentAdapter
    override fun getItemCount():Int {
        return if (mList == null || mList!!.size == 0) {
            0
        } else {
            mList!!.size;
        }
        //return 10
    }
    public fun updateList(list: ArrayList<ChapterWiseStudyNotesResponce.Datum>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuturePlusSliderViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_single_class_notes, parent, false)
        return FuturePlusSliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FuturePlusSliderViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val item = mList?.get(holder.adapterPosition)
        /*Picasso.get()
            .load(item?.imageUrl)
            .resize(800, 800)
            .centerInside()
            .into(holder.imageSlider)*/
        //loadHtmlContent(holder.description, item?.notes)
        /*val showData: String = item?.notes!!.replace("\n", "")
        val p = URLImageParser(holder.description, mContext)
        val htmlAsSpanned = Html.fromHtml(item?.notes, p, null)
        holder.description.text = ""
        holder.description.text = htmlAsSpanned
        if(position == 0) {
            Log.d("htmlAsSpanned", ""+htmlAsSpanned)
        }*/


    }

    class FuturePlusSliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //var imageSlider:ImageView = view.findViewById(R.id.sliderImageId)
        var writtenNotesImgRecyclerView:RecyclerView = view.findViewById(R.id.writtenNotesImgRecyclerView)
        var studyDocRecyclerView:RecyclerView = view.findViewById(R.id.studyDocRecyclerView)
        var contentRecyclerView:RecyclerView = view.findViewById(R.id.contentRecyclerView)
        var description:TextView = view.findViewById(R.id.textView51)
        var index:TextView = view.findViewById(R.id.textView50)
        var heading1:TextView = view.findViewById(R.id.textView52)
        var heading2:TextView = view.findViewById(R.id.textView53)
    }

    override fun bindVH(holder: FuturePlusSliderViewHolder, position: Int){
        val item = mList?.get(position)
        if(item?.writtenNotes?.isEmpty() == true) {
            holder.heading1.visibility = View.GONE
        } else {
            holder.heading1.visibility = View.VISIBLE
        }

        if(item?.studyDocs?.isEmpty() == true) {
            holder.heading2.visibility = View.GONE
        } else {
            holder.heading2.visibility = View.VISIBLE
        }
        mWrittenNotesAdapter = ClassWrittenNotesAdapter(item?.writtenNotes, mContext)
        mClassStudyDocAdapter = ClassStudyDocAdapter(item?.studyDocs, mPdfInterface)
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
            holder.writtenNotesImgRecyclerView.adapter = mWrittenNotesAdapter
            holder.writtenNotesImgRecyclerView.layoutManager = mManger
            holder.studyDocRecyclerView.adapter = mClassStudyDocAdapter
            holder.studyDocRecyclerView.layoutManager = mManger1
            holder.contentRecyclerView.adapter = mContentAdapter
            holder.contentRecyclerView.layoutManager = mManger2
            holder.description.text= item?.notes
            holder.index.text= ""+(position+1)
            //loadHtmlContent(holder.description, item?.notes)
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