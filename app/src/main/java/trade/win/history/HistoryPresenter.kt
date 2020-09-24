package trade.win.history

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import trade.win.rest.RestClient

class HistoryPresenter {
    lateinit var iHistory: IHistory

    fun getHistory(token_param: String){
        val callAPI = RestClient.retrofitService().history(token_param)

        callAPI.enqueue(object : Callback<List<HistoryResponse>>{
            override fun onFailure(call: Call<List<HistoryResponse>>, t: Throwable) {
                iHistory.onErrorFailure(t)
            }


            override fun onResponse(
                call: Call<List<HistoryResponse>>,
                response: Response<List<HistoryResponse>>
            ) {
                if (response.isSuccessful){
                    if (response.body()!= null){
                        iHistory.onSuccess(response.body()!!)
                    }
                } else {
                    iHistory.onError(response.message())
                }
            }

        })
    }

}