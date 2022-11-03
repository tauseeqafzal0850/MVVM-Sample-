package com.example.mvvm.data.implementations

import android.app.Application
import com.example.mvvm.R
import com.example.mvvm.core.Resource
import com.example.mvvm.data.db.database.AppDatabase
import com.example.mvvm.data.models.UserClass
import com.example.mvvm.di.domain.repository.DatabaseRepository
import com.example.mvvm.di.modules.MVVMApplication
import com.example.mvvm.di.responses.DatabaseResponse
import com.example.mvvm.presentation.utils.Utility
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(private val userDatabase: AppDatabase) :
    DatabaseRepository {

     override fun addUser(userClass: UserClass): Flow<Resource<DatabaseResponse>> =
        flow {
            emit(
                Resource.Loading(
                    data = DatabaseResponse()
                )
            )
            try {
                val apiCall = userDatabase.userDao().addUser(userClass)
                    emit(Resource.Success(DatabaseResponse("Employee Saved Successfully",null)))
            } catch (e: HttpException) {
                val errorInfo = Utility.parseError(e.response())
                emit(
                    Resource.Error(
                        message = errorInfo!!,
                        data = DatabaseResponse(errorInfo.toString())
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Couldn't reach server, check your internet connection.",
                        data = DatabaseResponse(e.message.toString())
                    )
                )
            }
        }

    override fun getUsersList(): Flow<Resource<DatabaseResponse>> =
        flow {
            emit(
                Resource.Loading(
                    data = DatabaseResponse()
                )
            )
            try {
                val apiCall = userDatabase.userDao().getAllUsers()
                if(apiCall.isNotEmpty()) {
                    emit(Resource.Success(DatabaseResponse("Data Retrieved", apiCall)))
                }
                else
                {
                    emit(
                        Resource.Error(
                            message = MVVMApplication.appContext!!.getString(R.string.no_data_found),
                            data = DatabaseResponse(MVVMApplication.appContext!!.getString(R.string.no_data_found),null)
                        )
                    )
                }
            } catch (e: HttpException) {
                val errorInfo = Utility.parseError(e.response())
                emit(
                    Resource.Error(
                        message = errorInfo!!,
                        data = DatabaseResponse(errorInfo.toString(),null)
                    )
                )
            }
        }
}