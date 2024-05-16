package com.welkinwits.model

data class QuestionModelData(
    var mSelectedStatus: Boolean,
    var mQuestionString: String,
    var mQuestionId: Int?,
    var mAllSelectionEnabled: Boolean?,
    var mWrongAnswerStatus: String = "",
    var mCorrectAnswerStatus: String = "",
)