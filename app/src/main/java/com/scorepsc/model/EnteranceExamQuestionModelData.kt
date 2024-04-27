package com.scorepsc.model

import com.scorepsc.service.respose.homeBanner.enteranceExam.startExam.Option

data class EnteranceExamQuestionModelData(
    var mSelectedStatus: Boolean,
    var mQuestion: Option,
    var mQuestionId: Int?,
    var mAllSelectionEnabled: Boolean?,
    var mWrongAnswerStatus: String = "",
    var mCorrectAnswerStatus: String = "",
    var mQuestionIndex: Int = -99,
)