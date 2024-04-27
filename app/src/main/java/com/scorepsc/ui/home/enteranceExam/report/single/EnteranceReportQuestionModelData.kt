package com.scorepsc.ui.home.enteranceExam.report.single

import com.scorepsc.service.respose.homeBanner.enteranceExam.report.singleReportResponse.Option

data class EnteranceReportQuestionModelData(
    var mSelectedStatus: Boolean,
    var mQuestionString: Option,
    var mQuestionId: Int?,
    var mAllSelectionEnabled: Boolean?,
    var mWrongAnswerStatus: String = "",
    var mCorrectAnswerStatus: Option?
)