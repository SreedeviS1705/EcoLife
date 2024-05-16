package com.welkinwits.repository

import com.welkinwits.service.ApiService
import com.welkinwits.service.CreateOrderRequest
import com.welkinwits.service.Result
import com.welkinwits.service.request.*
import com.welkinwits.service.request.exams.SingleExamRequest
import com.welkinwits.service.request.exams.submitAction.ExamSubmitRequest
import com.welkinwits.service.request.liveclass.batch.BatchRequest
import com.welkinwits.service.request.liveclass.liveClass.LiveClassRequest
import com.welkinwits.service.request.quiz.PostQuizAnswerRequest
import com.welkinwits.service.request.quiz.SingleQuizReportRequest
import com.welkinwits.service.request.enteranceExam.EnteranceExamSubmitRequest
import com.welkinwits.service.request.scholarshipExamSubmit.ScholarshipExamSubmitRequest
import com.welkinwits.service.request.scholarshipExamSubmit.ScholarshipRedeemRequest
import com.welkinwits.service.respose.*
import com.welkinwits.service.respose.homeBanner.HomeBannerResponse
import com.welkinwits.service.respose.homeBanner.acadamicStreamSwitch.acadamicLevels.GetAcadamicLevelsResponse
import com.welkinwits.service.respose.homeBanner.acadamicStreamSwitch.changeAcadamicLevel.ChangeAcadamicLevelResponse
import com.welkinwits.service.respose.homeBanner.analytics.AnalyticsResponse
import com.welkinwits.service.respose.homeBanner.chapterWiseStudyNotes.ChapterWiseStudyNotesResponce
import com.welkinwits.service.respose.homeBanner.createOrder.CreateOrderResponse
import com.welkinwits.service.respose.homeBanner.currentAfairs.CurrentAfairsResponse
import com.welkinwits.service.respose.homeBanner.currentAfairs.byGroup.CurrentAfairsByGroupResponse
import com.welkinwits.service.respose.homeBanner.currentAfairs.byGroup.details.CurrentAfairsDetailsResponse
import com.welkinwits.service.respose.homeBanner.demoClassSubject.DemoClassWithSubjectResponse
import com.welkinwits.service.respose.homeBanner.demoVideos.DemoVideosResponse
import com.welkinwits.service.respose.homeBanner.descrpQuestAns.DescrptQuestAnsResponse
import com.welkinwits.service.respose.homeBanner.enteranceExam.EnteranceExamQuestionTypeResponse
import com.welkinwits.service.respose.homeBanner.enteranceExam.answerkey.EnteranceExamAnswerKeyResponse
import com.welkinwits.service.respose.homeBanner.enteranceExam.report.EnteranceExamReportsResponse
import com.welkinwits.service.respose.homeBanner.enteranceExam.report.singleReportResponse.EnteranceExamReportResponse
import com.welkinwits.service.respose.homeBanner.enteranceExam.startExam.EnteranceExamStartExamResponse
import com.welkinwits.service.respose.homeBanner.exam.ExamResponse
import com.welkinwits.service.respose.homeBanner.exam.report.ExamReportResponse
import com.welkinwits.service.respose.homeBanner.exam.report.single.SingleExamReportResponse
import com.welkinwits.service.respose.homeBanner.exam.singleExam.SingleExamResponce
import com.welkinwits.service.respose.homeBanner.exam.singleExam.submitAction.ExamSubmitResponse
import com.welkinwits.service.respose.homeBanner.feePayment.FeePaymentModeOfStudyResponse
import com.welkinwits.service.respose.homeBanner.feeStructure.FeeStructureResponse
import com.welkinwits.service.respose.homeBanner.flashCard.FlashCardGroupResponse
import com.welkinwits.service.respose.homeBanner.flashCard.flashcards.FlashCardItemsResponse
import com.welkinwits.service.respose.homeBanner.getActiveSubscription.GetActiveSubscriptionResponse
import com.welkinwits.service.respose.homeBanner.importantLink.ImportantLinkResponse
import com.welkinwits.service.respose.homeBanner.jobupdates.JobUpdatesResponse
import com.welkinwits.service.respose.homeBanner.jobupdates.details.JobUpdatesDetailsResponse
import com.welkinwits.service.respose.homeBanner.latestUpdates.LatestUpdateResponse
import com.welkinwits.service.respose.homeBanner.liveClass.batch.LiveClassBatchResponse
import com.welkinwits.service.respose.homeBanner.liveClass.liveclasses.LiveClassesResponse
import com.welkinwits.service.respose.homeBanner.myPartPayment.MySubscriptionPackageListResponse
import com.welkinwits.service.respose.homeBanner.partPayment.PartPaymentListResponse
import com.welkinwits.service.respose.homeBanner.paymentHistory.GetPaymentHistoryResponse
import com.welkinwits.service.respose.homeBanner.paymentInit.InitPaymentResponse
import com.welkinwits.service.respose.homeBanner.paymentStatus.OrderStatusResponse
import com.welkinwits.service.respose.homeBanner.previousYearQuestionPapers.PreviousYearQuestionResponse
import com.welkinwits.service.respose.homeBanner.probableQA.ProbableQAGroupResponse
import com.welkinwits.service.respose.homeBanner.probableQA.qaAnswerKey.QAAnswerKeyResponse
import com.welkinwits.service.respose.homeBanner.quiz.GetQuizQuestionResponse
import com.welkinwits.service.respose.homeBanner.quiz.report.QuizReportsResponse
import com.welkinwits.service.respose.homeBanner.quiz.report.singleQuizReport.SingleQuizReportResponse
import com.welkinwits.service.respose.homeBanner.quiz.singlequiz.GetSingleQuizResponse
import com.welkinwits.service.respose.homeBanner.quiz.singlequiz.answer.PostQuizAnswerResponce
import com.welkinwits.service.respose.homeBanner.scholarship.ScholarshipExamsResponse
import com.welkinwits.service.respose.homeBanner.scholarship.scholarshipCreateExam.ScholarshipCreateExamResponse
import com.welkinwits.service.respose.homeBanner.scholarship.scholarshipCreateExam.scholarshipUpdateEnrollment.ScholarshipUpdateEnrollmentResponse
import com.welkinwits.service.respose.homeBanner.scholarship.scholarshipCreateExam.scholarshipUpdateEnrollment.scholarshipStartExam.ScholarshipStartExamResponse
import com.welkinwits.service.respose.homeBanner.scholarship.scholarshipCreateExam.scholarshipUpdateEnrollment.scholarshipStartExam.submit.ScholarshipExamSubmitResponse
import com.welkinwits.service.respose.homeBanner.scholarship.scholarshipCreateExam.scholarshipUpdateEnrollment.scholarshipStartExam.submit.scholarshipRedeem.ScholarshipRedeemResponse
import com.welkinwits.service.respose.homeBanner.studyCenter.StudyCenterResponse
import com.welkinwits.service.respose.homeBanner.subscription.GetAllSubscriptionListResponse
import com.welkinwits.service.respose.homeBanner.subscription.createOrder.CreateOrderSubscriptionListResponse
import com.welkinwits.service.respose.homeBanner.subscription.createOrder.init.OrderInitSubscriptionListResponse
import com.welkinwits.service.respose.homeBanner.subscription.createOrder.statusCheck.OrderCreateStatusSubscriptionListResponse
import com.welkinwits.service.respose.homeBanner.ttAnalytics.TTAnalyticsResponse
import com.welkinwits.service.respose.homeBanner.verifyProcedeEnrollment.VerifyProcedeEnrollmentResponse
import com.welkinwits.util.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentRepository @Inject constructor(
    private val apiService: ApiService,
    val dataStoreManager: DataStoreManager
) {

    fun subjects(token: String, studentIdRequest: StudentIdRequest): Flow<Result<SubjectResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.subjects(token, studentIdRequest) })
        }.flowOn(Dispatchers.IO)

    fun homeBanner(token: String, homeBannerRequest: HomeBannerRequest): Flow<Result<HomeBannerResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.homeBannerVideo(token, homeBannerRequest) })
        }.flowOn(Dispatchers.IO)

    fun news(token: String, studentIdRequest: StudentIdRequest): Flow<Result<NewsResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.news(token, studentIdRequest) })
        }.flowOn(Dispatchers.IO)

    fun getProfile(token: String, studentIdRequest: StudentIdRequest): Flow<Result<ProfileResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getProfile(token, studentIdRequest) })
        }.flowOn(Dispatchers.IO)

    fun updateProfile(token: String, updateProfileRequest: UpdateProfileRequest): Flow<Result<BaseResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.updateProfile(token, updateProfileRequest) })
        }.flowOn(Dispatchers.IO)

    fun addFeedback(token: String, feedbackRequest: FeedbackRequest): Flow<Result<BaseResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.addFeedback(token, feedbackRequest) })
        }.flowOn(Dispatchers.IO)

    fun getChapters(token: String, chapterRequest: ChapterRequest): Flow<Result<ChapterResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.chapter(token, chapterRequest) })
        }.flowOn(Dispatchers.IO)

    fun getTopics(token: String, topicRequest: TopicRequest): Flow<Result<TopicResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.topics(token, topicRequest) })
        }.flowOn(Dispatchers.IO)

    fun addToWatchedList(token: String, watchedListRequest: WatchedListRequest): Flow<Result<BaseResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.watched(token, watchedListRequest) })
        }.flowOn(Dispatchers.IO)

    fun getWatchedList(token: String, studentIdRequest: StudentIdRequest): Flow<Result<TopicResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.recentlyWatched(token, studentIdRequest) })
        }.flowOn(Dispatchers.IO)

    fun getExam(token: String, studentIdRequest: StudentIdRequest): Flow<Result<ExamResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.exams(token, studentIdRequest) })
        }.flowOn(Dispatchers.IO)

    fun getSingleExam(token: String, singleExamRequest: SingleExamRequest): Flow<Result<SingleExamResponce>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.exam(token, singleExamRequest) })
        }.flowOn(Dispatchers.IO)

    fun submitExamAnswer(token: String, examRequest: ExamSubmitRequest): Flow<Result<ExamSubmitResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.submitExamAnswer(token, examRequest) })
        }.flowOn(Dispatchers.IO)

    fun getLiveClassBatch(token: String, request: BatchRequest): Flow<Result<LiveClassBatchResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.liveClassBatch(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getLiveClasses(token: String, request: LiveClassRequest): Flow<Result<LiveClassesResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.liveClasses(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getScrollUpdates(token: String, request: StudentIdRequest): Flow<Result<LatestUpdateResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.scrollingUpdates(token, request) })
        }.flowOn(Dispatchers.IO)

    fun jobUpdates(token: String, request: StudentIdRequest): Flow<Result<JobUpdatesResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.jobUpdates(token, request) })
        }.flowOn(Dispatchers.IO)

    fun demoVideos(token: String, request: StudentIdRequest): Flow<Result<DemoVideosResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.demoVideos(token, request) })
        }.flowOn(Dispatchers.IO)

    fun jobDetails(token: String, request: JobDetailsRequest): Flow<Result<JobUpdatesDetailsResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.jobUpdatesDetails(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getMaterials(token: String, request: MaterialRequest): Flow<Result<MaterialResponse>> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.studyMaterials(token, request) })
        }.flowOn(Dispatchers.IO)

    fun modeOfStudy(token: String, studentIdRequest: StudentIdRequest): Flow<Result<FeePaymentModeOfStudyResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.feePaymentModeOfStudy(token, studentIdRequest) })
        }.flowOn(Dispatchers.IO)

    fun studyCenter(token: String, studentIdRequest: StudentIdRequest): Flow<Result<StudyCenterResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.studyCenter(token, studentIdRequest) })
        }.flowOn(Dispatchers.IO)

    fun feeStructure(token: String, request: FeeStructureRequest): Flow<Result<FeeStructureResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.feeStructure(token, request) })
        }.flowOn(Dispatchers.IO)

    fun verifyProceedEnrollment(token: String, request: VerifyProcedeEnrollmentRequest): Flow<Result<VerifyProcedeEnrollmentResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.verifyProceedEnrollment(token, request) })
        }.flowOn(Dispatchers.IO)

    fun partPaymentList(token: String, request: PartPaymentListRequest): Flow<Result<PartPaymentListResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.partPaymentList(token, request) })
        }.flowOn(Dispatchers.IO)

    fun partPaymentListBypackage(token: String, request: PartPaymentListByPackageRequest): Flow<Result<PartPaymentListResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.partPaymentListByPackage(token, request) })
        }.flowOn(Dispatchers.IO)

    fun paymentInit(token: String, request: PaymentInitRequest): Flow<Result<InitPaymentResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.paymentInit(token, request) })
        }.flowOn(Dispatchers.IO)

    fun createOrder(token: String, request: CreateOrderRequest): Flow<Result<CreateOrderResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.createOrder(token, request) })
        }.flowOn(Dispatchers.IO)

    fun checkOrderStatus(token: String, request: OrderStatusRequest): Flow<Result<OrderStatusResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.checkOrderStatus(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getPaymentHistory(token: String, request: PaymentHistoryRequest): Flow<Result<GetPaymentHistoryResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getPaymentHistory(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getAnalytics(token: String, request: StudentIdRequest): Flow<Result<AnalyticsResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getAnalytics(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getMySubscriptionPackages(token: String, request: StudentIdRequest): Flow<Result<MySubscriptionPackageListResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getMySubscriptionPackages(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getActiveSubscription(token: String, request: StudentIdRequest): Flow<Result<GetActiveSubscriptionResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getActiveSubscription(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getChapterWiseClassNotes(token: String, request: ChapterWiseStudyNotesRequest): Flow<Result<ChapterWiseStudyNotesResponce>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getChapterWiseClassNotes(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getDescrpQuesAns(token: String, request: ChapterWiseStudyNotesRequest): Flow<Result<DescrptQuestAnsResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getDescrpQuesAns(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getQuizExamList(token: String, request: ChapterWiseStudyNotesRequest): Flow<Result<GetQuizQuestionResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getQuizExamList(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getQuizSingleExamList(token: String, request: QuizSingleRequest): Flow<Result<GetSingleQuizResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getQuizSingleExamList(token, request) })
        }.flowOn(Dispatchers.IO)

    fun postQuizAnswerSingle(token: String, request: PostQuizAnswerRequest): Flow<Result<PostQuizAnswerResponce>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.postQuizAnswerSingle(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getScholarshipExamsList(token: String, request: ScholarshipExamsRequest): Flow<Result<ScholarshipExamsResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getScholarshipExamsList(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getScholarshipCreateExam(token: String, request: ScholarshipCreateExamRequest): Flow<Result<ScholarshipCreateExamResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getScholarshipCreateExam(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getScholarshipUpdateEnrollment(token: String, request: ScholarshipUpdateEnrollmentRequest): Flow<Result<ScholarshipUpdateEnrollmentResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getScholarshipUpdateEnrollment(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getScholarshipStartExam(token: String, request: ScholarshipStartExamRequest): Flow<Result<ScholarshipStartExamResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getScholarshipStartExam(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getScholarshipExamSubmit(token: String, request: ScholarshipExamSubmitRequest): Flow<Result<ScholarshipExamSubmitResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getScholarshipExamSubmit(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getScholarshipRedeem(token: String, request: ScholarshipRedeemRequest): Flow<Result<ScholarshipRedeemResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getScholarshipRedeem(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getQuizReports(token: String, studentIdRequest: QuizReportHistoryRequest): Flow<Result<QuizReportsResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getQuizReports(token, studentIdRequest) })
        }.flowOn(Dispatchers.IO)

    fun getEnteranceExamReports(token: String, studentIdRequest: StudentIdRequest): Flow<Result<EnteranceExamReportsResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getEnteranceExamReports(token, studentIdRequest) })
        }.flowOn(Dispatchers.IO)

    fun getSingleQuizReport(token: String, studentIdRequest: SingleQuizReportRequest): Flow<Result<SingleQuizReportResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getSingleQuizReport(token, studentIdRequest) })
        }.flowOn(Dispatchers.IO)

    fun getDemoClassSubject(token: String, request: DemoClassRequest): Flow<Result<DemoClassWithSubjectResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getDemoClassSubject(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getAllSubscriptionList(token: String, request: StudentIdRequest): Flow<Result<GetAllSubscriptionListResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getAllSubscriptionList(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getAllSubscriptionDetailsList(token: String, request: CreateOrderSubscriptionAllListRequest): Flow<Result<CreateOrderSubscriptionListResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getAllSubscriptionDetailsList(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getOrderInitSubscriptionList(token: String, request: OrderInitSubscriptionAllListRequest): Flow<Result<OrderInitSubscriptionListResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getOrderInitSubscriptionList(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getOrderStatusSubscriptionList(token: String, request: OrderCreateStatusSubscriptionListRequest): Flow<Result<OrderCreateStatusSubscriptionListResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getOrderStatusSubscriptionList(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getEnteranceQuestionTypeList(token: String, request: StudentIdRequest): Flow<Result<EnteranceExamQuestionTypeResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getEnteranceQuestionTypeList(token, request) })
        }.flowOn(Dispatchers.IO)

    fun searchEnteranceExam(token: String, request: StudentIdRequest, searchText:String): Flow<Result<EnteranceExamQuestionTypeResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.searchEnteranceExam(token, searchText, request) })
        }.flowOn(Dispatchers.IO)

    fun getEnteranceStartExam(token: String, request: EnteranceStartExamRequest): Flow<Result<EnteranceExamStartExamResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getEnteranceStartExam(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getEnteranceExamSubmit(token: String, request: EnteranceExamSubmitRequest): Flow<Result<ScholarshipExamSubmitResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getEnteranceExamSubmit(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getSingleEnteranceExamReport(token: String, request: SingleEnteranceReportRequest): Flow<Result<EnteranceExamReportResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getSingleEnteranceExamReport(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getCurrentAffairs(token: String, request: StudentIdRequest): Flow<Result<CurrentAfairsResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getCurrentAffairs(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getCurrentAffairsByGroup(token: String, request: CurrentAffairByGroupRequest): Flow<Result<CurrentAfairsByGroupResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getCurrentAffairsByGroup(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getCurrentAffairsDetails(token: String, request: CurrentAffairDetailsRequest): Flow<Result<CurrentAfairsDetailsResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getCurrentAffairsDetails(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getExamReport(token: String, request: StudentIdRequest): Flow<Result<ExamReportResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getExamReport(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getSingleExamReport(token: String, request: SingleExamReportRequest): Flow<Result<SingleExamReportResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getSingleExamReport(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getEnteranceExamAnswerKey(token: String, request: EnteranceExamAnswerKeyRequest): Flow<Result<EnteranceExamAnswerKeyResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getEnteranceExamAnswerKey(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getProbableQAGroup(token: String, request: StudentIdRequest): Flow<Result<ProbableQAGroupResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getProbableQAGroup(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getQAAnswerKey(token: String, request: QAAnswerKeyRequest): Flow<Result<QAAnswerKeyResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getQAAnswerKey(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getImportantLink(token: String, request: StudentIdRequest): Flow<Result<ImportantLinkResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getImportantLink(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getPreviousYearPaper(token: String, request: StudentIdRequest): Flow<Result<PreviousYearQuestionResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getPreviousYearPaper(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getTTAnalytics(token: String, request: StudentIdRequest): Flow<Result<TTAnalyticsResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getTTAnalytics(token, request) })
        }.flowOn(Dispatchers.IO)

    fun flashCardGroupResponse(token: String, request: StudentIdRequest): Flow<Result<FlashCardGroupResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.flashCardGroupResponse(token, request) })
        }.flowOn(Dispatchers.IO)

    fun flashCardItemsResponse(token: String, request: FlashCardItemsRequest): Flow<Result<FlashCardItemsResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.flashCardItemsResponse(token, request) })
        }.flowOn(Dispatchers.IO)

    fun getAcademicLevels(token: String, request: StudentIdRequest): Flow<Result<GetAcadamicLevelsResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.getAcademicLevels(token, request) })
        }.flowOn(Dispatchers.IO)

    fun changeAcademicLevel(token: String, request: ChangeAcadamicStreamSwtichRequest): Flow<Result<ChangeAcadamicLevelResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.changeAcademicLevel(token, request) })
        }.flowOn(Dispatchers.IO)
}