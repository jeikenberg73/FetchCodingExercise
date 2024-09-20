package com.example.fetchcodingexercise.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchcodingexercise.model.Hire
import com.example.fetchcodingexercise.network.FetchHireApi
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

/**
 * This interface is used to define the states the retrieval of the hire list is in.
 * @property Success This state is set when the hire list is retrieved properly
 * @property Error This state is set when there is an issue with retrieving the list properly.
 * @property Loading This state is set when the list is in the process of being retrieved.
 */
sealed interface FetchHireState {
    data class Success(val hiredList: List<Hire>) : FetchHireState
    data object Error : FetchHireState
    data object Loading : FetchHireState
}

/**
 * This is the view model for providing the Fetch Hire App with the data that it needs.
 */
class FetchHireViewModel : ViewModel() {
    /**
     * This variable is used to hold the state of the retrieval of the hire list. It sets it
     * to Loading by default.
     */
    var hireState: FetchHireState by mutableStateOf(FetchHireState.Loading)
        private set

    /**
     * When the view model initializes it starts by going out and retrieving the hire list.
     */
    init {
        getHireList()
    }

    /**
     * This function attempts to retrieve the hire list from the internet. Then it filters and
     * sorts the list and passes it to the FetchHireState to hold for the view to
     * display the data. If there is an error in retrieving the list it sets the state to error.
     */
    fun getHireList() {
        /**
         * Retrieving the hire list from the internet needs to be done in a background task,
         * so the viewModelScope.launch is used.
         */
        viewModelScope.launch {
            // Hire state is set to Loading first.
            hireState = FetchHireState.Loading
            // Hire state attempts to retrieve the hire list from the service provided in
            // FetchHireApi.
            hireState = try {
                // Get hire list from the FetchHireApi service.
                var hireList = FetchHireApi.service.getHireList()
                // Filter the list from null and empty names.
                hireList = filterHireList(hireList)

                // Sort the list by ListId and then name.
                hireList = sortHireList(hireList)
                // Set the state to Success and pass it the list.
                FetchHireState.Success(hireList)
            } catch (e: IOException) {
                // Set the state to error if an IO exception is found.
                FetchHireState.Error
            } catch (e: HttpException) {
                // Set the state to error if there is an Http exception is found.
                FetchHireState.Error
            }
        }
    }

    /**
     * This function filters the list to remove all null and empty name hires.
     */
    private fun filterHireList(hiredList: List<Hire>) : List<Hire> {
        return hiredList.filter { it.name != null && it.name != ""}
    }

    /**
     * This function sorts the hire list by listId first and then sorts it by name.
     */
    private fun sortHireList(hireList: List<Hire>) : List<Hire> {
        // Sort by name
        val nameSort = hireList.sortedBy { it.name }
        // Sort by listId
        val listIdSort = nameSort.sortedBy { it.listId }
        return listIdSort
    }
}