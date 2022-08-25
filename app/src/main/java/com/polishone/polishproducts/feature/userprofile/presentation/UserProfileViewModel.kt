package com.polishone.polishproducts.feature.userprofile.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polishone.polishproducts.common.constants.Resource
import com.polishone.polishproducts.feature.userprofile.data.network.model.GetUserProfileResponse
import com.polishone.polishproducts.feature.userprofile.domain.usecase.GetUserProfileUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val userProfileUsecase: GetUserProfileUsecase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _userProfileResponse = MutableSharedFlow<Resource<GetUserProfileResponse>>()
    val userProfileResponse: SharedFlow<Resource<GetUserProfileResponse>> get() = _userProfileResponse.asSharedFlow()

    fun getUserProfile() {
        viewModelScope.launch {
            userProfileUsecase().collect {
                _userProfileResponse.emit(it)
            }
        }
    }
}