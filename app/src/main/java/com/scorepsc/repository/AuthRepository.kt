package com.scorepsc.repository

import com.scorepsc.service.*
import com.scorepsc.service.request.*
import com.scorepsc.service.respose.*
import com.scorepsc.service.respose.homeBanner.refferal.RefferalCodeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val apiService: ApiService) {

    fun signIn(signInRequest: SignInRequest): Flow<Result<SignInResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.signIn(signInRequest) })
        }.flowOn(Dispatchers.IO)

    fun signUp(signUpRequest: SignUpRequest): Flow<Result<SignUpResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.signUp(signUpRequest) })
        }.flowOn(Dispatchers.IO)

    fun verifyOTP(verifyOTPRequest: VerifyOTPRequest): Flow<Result<VerifyOTPResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.verifyOTP(verifyOTPRequest) })
        }.flowOn(Dispatchers.IO)

    fun resendOTP(resendOTPRequest: ResendOTPRequest): Flow<Result<BaseResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.resendOTP(resendOTPRequest) })
        }.flowOn(Dispatchers.IO)

    fun academicLevel(): Flow<Result<AcademicLevelResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.academicLevels() })
        }.flowOn(Dispatchers.IO)

    fun whatsAppNumber(): Flow<Result<WhatsAppNumberResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.whatsAppNumber() })
        }.flowOn(Dispatchers.IO)

    fun country(): Flow<Result<CountryResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.country() })
        }.flowOn(Dispatchers.IO)

    fun verrifyReferralCode(request: RefferalCodeRequest): Flow<Result<RefferalCodeResponse>?> =
        flow {
            emit(Result.loading())
            emit(Result.getResponse { apiService.verrifyReferralCode(request) })
        }.flowOn(Dispatchers.IO)
}