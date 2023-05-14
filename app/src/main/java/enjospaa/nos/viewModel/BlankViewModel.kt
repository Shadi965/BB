package enjospaa.nos.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import enjospaa.nos.model.QuestionsManager

class BlankViewModel : ViewModel() {

    val quizQuestions = QuestionsManager()
    var variants: List<String>? = null

    val questionNumber = MutableLiveData(0)
    val currentScore = MutableLiveData(0)

    fun nextQuestion(){
        questionNumber.value?.let { questionNumber.value = it + 1 }
    }

    fun addScore() {
        currentScore.value?.let { currentScore.value = it + 1 }
    }
}