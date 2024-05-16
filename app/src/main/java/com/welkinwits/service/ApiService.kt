package com.welkinwits.service

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
import com.welkinwits.service.respose.homeBanner.refferal.RefferalCodeResponse
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
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("Auth/login")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>

    @POST("Auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @POST("Auth/verifyOTP")
    suspend fun verifyOTP(@Body request: VerifyOTPRequest): Response<VerifyOTPResponse>

    @POST("Auth/resendOTP")
    suspend fun resendOTP(@Body request: ResendOTPRequest): Response<BaseResponse>

    @GET("Auth/academic_levels")
    suspend fun academicLevels(): Response<AcademicLevelResponse>

    @GET("Auth/whatsapp_chat")
    suspend fun whatsAppNumber(): Response<WhatsAppNumberResponse>

    @GET("Auth/country")
    suspend fun country(): Response<CountryResponse>

    @POST("Student/app_banners")
    suspend fun homeBannerVideo(
        @Header("token") token: String,
        @Body request: HomeBannerRequest
    ): Response<HomeBannerResponse>

    @POST("Student/subjects")
    suspend fun subjects(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<SubjectResponse>

    @POST("Student/get_profile")
    suspend fun getProfile(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<ProfileResponse>

    @POST("Student/update_profile")
    suspend fun updateProfile(
        @Header("token") token: String,
        @Body request: UpdateProfileRequest
    ): Response<BaseResponse>


    @POST("Student/add_feedback")
    suspend fun addFeedback(
        @Header("token") token: String,
        @Body request: FeedbackRequest
    ): Response<BaseResponse>


    @POST("Student/news_events")
    suspend fun news(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<NewsResponse>

    @POST("Student/chapters")
    suspend fun chapter(
        @Header("token") token: String,
        @Body request: ChapterRequest
    ): Response<ChapterResponse>

    @POST("Student/topics")
    suspend fun topics(
        @Header("token") token: String,
        @Body request: TopicRequest
    ): Response<TopicResponse>

    @POST("Student/watched")
    suspend fun watched(
        @Header("token") token: String,
        @Body request: WatchedListRequest
    ): Response<BaseResponse>

    @POST("Student/recently_watched")
    suspend fun recentlyWatched(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<TopicResponse>

    @POST("Student/exams")
    suspend fun exams(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<ExamResponse>

    @POST("Student/exam")
    suspend fun exam(
        @Header("token") token: String,
        @Body request: SingleExamRequest
    ): Response<SingleExamResponce>

    @POST("Student/submit_exam")
    suspend fun submitExamAnswer(
        @Header("token") token: String,
        @Body request: ExamSubmitRequest
    ): Response<ExamSubmitResponse>

    @POST("Student/batches")
    suspend fun liveClassBatch(
        @Header("token") token: String,
        @Body request: BatchRequest
    ): Response<LiveClassBatchResponse>

    @POST("Student/live_classes")
    suspend fun liveClasses(
        @Header("token") token: String,
        @Body request: LiveClassRequest
    ): Response<LiveClassesResponse>

    @POST("Student/scrolling_updates")
    suspend fun scrollingUpdates(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<LatestUpdateResponse>

    @POST("Student/job_updates")
    suspend fun jobUpdates(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<JobUpdatesResponse>

    @POST("Student/demo_classes")
    suspend fun demoVideos(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<DemoVideosResponse>

    @POST("Student/job_update")
    suspend fun jobUpdatesDetails(
        @Header("token") token: String,
        @Body request: JobDetailsRequest
    ): Response<JobUpdatesDetailsResponse>

    @POST("Student/study_materials")
    suspend fun studyMaterials(
        @Header("token") token: String,
        @Body request: MaterialRequest
    ): Response<MaterialResponse>

    @POST("Student/mode_of_study")
    suspend fun feePaymentModeOfStudy(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<FeePaymentModeOfStudyResponse>

    @POST("Student/franchises")
    suspend fun studyCenter(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<StudyCenterResponse>

    @POST("Student/fee_packages")
    suspend fun feeStructure(
        @Header("token") token: String,
        @Body request: FeeStructureRequest
    ): Response<FeeStructureResponse>

    @POST("Student/verify_and_create_enrollment")
    suspend fun verifyProceedEnrollment(
        @Header("token") token: String,
        @Body request: VerifyProcedeEnrollmentRequest
    ): Response<VerifyProcedeEnrollmentResponse>

    @POST("Student/part_payments")
    suspend fun partPaymentList(
        @Header("token") token: String,
        @Body request: PartPaymentListRequest
    ): Response<PartPaymentListResponse>

    @POST("Student/part_payments_by_package")
    suspend fun partPaymentListByPackage(
        @Header("token") token: String,
        @Body request: PartPaymentListByPackageRequest
    ): Response<PartPaymentListResponse>

    @POST("Student/init_payment")
    suspend fun paymentInit(
        @Header("token") token: String,
        @Body request: PaymentInitRequest
    ): Response<InitPaymentResponse>

    @POST("Student/create_order")
    suspend fun createOrder(
        @Header("token") token: String,
        @Body request: CreateOrderRequest
    ): Response<CreateOrderResponse>

    @POST("Payment/check_payment_status")
    suspend fun checkOrderStatus(
        @Header("token") token: String,
        @Body request: OrderStatusRequest
    ): Response<OrderStatusResponse>

    @POST("Student/tt_order_history")
    suspend fun getPaymentHistory(
        @Header("token") token: String,
        @Body request: PaymentHistoryRequest
    ): Response<GetPaymentHistoryResponse>

    @POST("Student/analytics")
    suspend fun getAnalytics(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<AnalyticsResponse>

    @POST("Student/get_subscription")
    suspend fun getMySubscriptionPackages(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<MySubscriptionPackageListResponse>

    @POST("Student/tt_check_subscription")
    suspend fun getActiveSubscription(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<GetActiveSubscriptionResponse>

    @POST("Student/chapter_wise_study_notes")
    suspend fun getChapterWiseClassNotes(
        @Header("token") token: String,
        @Body request: ChapterWiseStudyNotesRequest
    ): Response<ChapterWiseStudyNotesResponce>

    @POST("Student/chapter_wise_descriptive_questions")
    suspend fun getDescrpQuesAns(
        @Header("token") token: String,
        @Body request: ChapterWiseStudyNotesRequest
    ): Response<DescrptQuestAnsResponse>

    @POST("Student/get_quiz_by_chapter")
    suspend fun getQuizExamList(
        @Header("token") token: String,
        @Body request: ChapterWiseStudyNotesRequest
    ): Response<GetQuizQuestionResponse>

    @POST("Student/get_quiz")
    suspend fun getQuizSingleExamList(
        @Header("token") token: String,
        @Body request: QuizSingleRequest
    ): Response<GetSingleQuizResponse>

    @POST("Student/submit_quiz")
    suspend fun postQuizAnswerSingle(
        @Header("token") token: String,
        @Body request: PostQuizAnswerRequest
    ): Response<PostQuizAnswerResponce>

    @POST("Student/scholarship_get_exams")
    suspend fun getScholarshipExamsList(
        @Header("token") token: String,
        @Body request: ScholarshipExamsRequest
    ): Response<ScholarshipExamsResponse>

    @POST("Student/scholarship_create_enrollment")
    suspend fun getScholarshipCreateExam(
        @Header("token") token: String,
        @Body request: ScholarshipCreateExamRequest
    ): Response<ScholarshipCreateExamResponse>

    @POST("Student/scholarship_update_enrollment")
    suspend fun getScholarshipUpdateEnrollment(
        @Header("token") token: String,
        @Body request: ScholarshipUpdateEnrollmentRequest
    ): Response<ScholarshipUpdateEnrollmentResponse>

    @POST("Student/scholarship_start_exam")
    suspend fun getScholarshipStartExam(
        @Header("token") token: String,
        @Body request: ScholarshipStartExamRequest
    ): Response<ScholarshipStartExamResponse>

    @POST("Student/scholarship_submit_exam")
    suspend fun getScholarshipExamSubmit(
        @Header("token") token: String,
        @Body request: ScholarshipExamSubmitRequest
    ): Response<ScholarshipExamSubmitResponse>

    @POST("Student/scholarship_redeem")
    suspend fun getScholarshipRedeem(
        @Header("token") token: String,
        @Body request: ScholarshipRedeemRequest
    ): Response<ScholarshipRedeemResponse>

    @POST("Student/my_quiz_history")
    suspend fun getQuizReports(
        @Header("token") token: String,
        @Body request: QuizReportHistoryRequest
    ): Response<QuizReportsResponse>

    @POST("Student/entrance_get_exam_reports")
    suspend fun getEnteranceExamReports(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<EnteranceExamReportsResponse>

    @POST("Student/quiz_report_by_id")
    suspend fun getSingleQuizReport(
        @Header("token") token: String,
        @Body request: SingleQuizReportRequest
    ): Response<SingleQuizReportResponse>

    @POST("Auth/verify_referral_code")
    suspend fun verrifyReferralCode(
        @Body request: RefferalCodeRequest
    ): Response<RefferalCodeResponse>

    @POST("Student/demo_classes_by_subject")
    suspend fun getDemoClassSubject(
        @Header("token") token: String,
        @Body request: DemoClassRequest
    ): Response<DemoClassWithSubjectResponse>

    @POST("Student/tt_get_packages")
    suspend fun getAllSubscriptionList(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<GetAllSubscriptionListResponse>

    @POST("Student/tt_create_order")
    suspend fun getAllSubscriptionDetailsList(
        @Header("token") token: String,
        @Body request: CreateOrderSubscriptionAllListRequest
    ): Response<CreateOrderSubscriptionListResponse>

    @POST("Student/tt_init_payment")
    suspend fun getOrderInitSubscriptionList(
        @Header("token") token: String,
        @Body request: OrderInitSubscriptionAllListRequest
    ): Response<OrderInitSubscriptionListResponse>

    @POST("Payment/tt_check_payment_status")
    suspend fun getOrderStatusSubscriptionList(
        @Header("token") token: String,
        @Body request: OrderCreateStatusSubscriptionListRequest
    ): Response<OrderCreateStatusSubscriptionListResponse>

    @POST("Student/entrance_get_exams")
    suspend fun getEnteranceQuestionTypeList(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<EnteranceExamQuestionTypeResponse>

    @POST("Student/entrance_get_exams")
    suspend fun searchEnteranceExam(
        @Header("token") token: String,
        @Query("query") searchText:String,
        @Body request: StudentIdRequest
    ): Response<EnteranceExamQuestionTypeResponse>

    @POST("Student/entrance_start_exam")
    suspend fun getEnteranceStartExam(
        @Header("token") token: String,
        @Body request: EnteranceStartExamRequest
    ): Response<EnteranceExamStartExamResponse>

    @POST("Student/entrance_submit_exam")
    suspend fun getEnteranceExamSubmit(
        @Header("token") token: String,
        @Body request: EnteranceExamSubmitRequest
    ): Response<ScholarshipExamSubmitResponse>

    @POST("Student/entrance_get_exam_report_by_id")
    suspend fun getSingleEnteranceExamReport(
        @Header("token") token: String,
        @Body request: SingleEnteranceReportRequest
    ): Response<EnteranceExamReportResponse>

    @POST("Student/current_affairs_groups")
    suspend fun getCurrentAffairs(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<CurrentAfairsResponse>

    @POST("Student/current_affairs_posts_by_group")
    suspend fun getCurrentAffairsByGroup(
        @Header("token") token: String,
        @Body request: CurrentAffairByGroupRequest
    ): Response<CurrentAfairsByGroupResponse>

    @POST("Student/current_affairs_posts_by_id")
    suspend fun getCurrentAffairsDetails(
        @Header("token") token: String,
        @Body request: CurrentAffairDetailsRequest
    ): Response<CurrentAfairsDetailsResponse>

    @POST("Student/get_exam_reports")
    suspend fun getExamReport(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<ExamReportResponse>

    @POST("Student/get_exam_report_by_id")
    suspend fun getSingleExamReport(
        @Header("token") token: String,
        @Body request: SingleExamReportRequest
    ): Response<SingleExamReportResponse>

    @POST("Student/entrance_get_exam_keys_by_id")
    suspend fun getEnteranceExamAnswerKey(
        @Header("token") token: String,
        @Body request: EnteranceExamAnswerKeyRequest
    ): Response<EnteranceExamAnswerKeyResponse>

    @POST("Student/get_probable_qa_groups")
    suspend fun getProbableQAGroup(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<ProbableQAGroupResponse>

    @POST("Student/get_probable_qa_questions")
    suspend fun getQAAnswerKey(
        @Header("token") token: String,
        @Body request: QAAnswerKeyRequest
    ): Response<QAAnswerKeyResponse>

    @POST("Student/get_important_links")
    suspend fun getImportantLink(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<ImportantLinkResponse>

    @POST("Student/get_prev_year_questions")
    suspend fun getPreviousYearPaper(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<PreviousYearQuestionResponse>

    @POST("Student/analytics")
    suspend fun getTTAnalytics(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<TTAnalyticsResponse>

    @POST("Student/get_flashcard_groups")
    suspend fun flashCardGroupResponse(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<FlashCardGroupResponse>

    @POST("Student/get_flashcards")
    suspend fun flashCardItemsResponse(
        @Header("token") token: String,
        @Body request: FlashCardItemsRequest
    ): Response<FlashCardItemsResponse>

    @POST("Student/get_academic_levels")
    suspend fun getAcademicLevels(
        @Header("token") token: String,
        @Body request: StudentIdRequest
    ): Response<GetAcadamicLevelsResponse>

    @POST("Student/change_academic_level")
    suspend fun changeAcademicLevel(
        @Header("token") token: String,
        @Body request: ChangeAcadamicStreamSwtichRequest
    ): Response<ChangeAcadamicLevelResponse>

}