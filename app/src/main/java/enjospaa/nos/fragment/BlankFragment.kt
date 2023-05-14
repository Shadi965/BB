package enjospaa.nos.fragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import enjospaa.nos.databinding.FragmentBlankBinding
import enjospaa.nos.viewModel.BlankViewModel

class BlankFragment : Fragment() {

    companion object {
        fun newInstance() = BlankFragment()
    }

    private val QUIZ_PREFERENCES = "quiz_preferences"
    private val RECORD_KEY = "record_score"

    private lateinit var binding: FragmentBlankBinding
    private lateinit var viewModel: BlankViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlankBinding.inflate(inflater,container, false)

        val preferences = context?.getSharedPreferences(QUIZ_PREFERENCES, Context.MODE_PRIVATE)
        var recordScore = preferences?.getInt(RECORD_KEY, 0)

        viewModel = ViewModelProvider(this)[BlankViewModel::class.java]
        val questions = viewModel.quizQuestions
        val numOfQuestions = questions.questionList.size
        viewModel.questionNumber.observe(viewLifecycleOwner) {
            if (it == 0 && it < numOfQuestions) {
                with(binding) {
                    currentScoreField.visibility = View.GONE
                    counterField.visibility = View.GONE
                    variantsTable.visibility = View.GONE

                    questionField.text = "Sports quiz"
                    recordField.text = "Record: $recordScore"
                    gameStartButton.text = "Start"

                    gameStartButton.visibility = View.VISIBLE
                    gameStartButton.setOnClickListener { view ->
                        view.visibility = View.GONE
                        viewModel.nextQuestion()
                        currentScoreField.visibility = View.VISIBLE
                        counterField.visibility = View.VISIBLE
                        variantsTable.visibility = View.VISIBLE
                    }
                }
            }
            else if (it in 1..numOfQuestions) {
                viewModel.variants = questions.getVariants(it - 1)

                val onClickAnswer = View.OnClickListener {view ->
                    if (questions.checkAnswer(it - 1, (view as Button).text.toString())) {
                        viewModel.addScore()
                    }
                    viewModel.nextQuestion()
                }

                with(binding) {
                    questionField.text = questions.questionList[it - 1].question
                    counterField.text = "$it/$numOfQuestions"
                    firstVariant.text = viewModel.variants!![0]
                    firstVariant.setOnClickListener(onClickAnswer)
                    secondVariant.text = viewModel.variants!![1]
                    secondVariant.setOnClickListener(onClickAnswer)
                    thirdVariant.text = viewModel.variants!![2]
                    thirdVariant.setOnClickListener(onClickAnswer)
                    fourthVariant.text = viewModel.variants!![3]
                    fourthVariant.setOnClickListener(onClickAnswer)
                }
            }
            else {
                if (viewModel.currentScore.value!! < recordScore!!)
                    binding.questionField.text = "You answered ${viewModel.currentScore.value} out of $numOfQuestions questions!\n Your record is $recordScore"
                else
                    binding.questionField.text = "You broke your record by answering $recordScore out of $numOfQuestions questions!"
                with(binding) {
                    recordField.visibility = View.GONE
                    currentScoreField.visibility = View.GONE
                    variantsTable.visibility = View.GONE

                    gameStartButton.visibility = View.VISIBLE
                    gameStartButton.text = "Play again"
                    gameStartButton.setOnClickListener {
                        gameStartButton.visibility = View.GONE
                        questions.shuffleQuestions()
                        viewModel.questionNumber.value = 1
                        viewModel.currentScore.value = 0
                        recordField.visibility = View.VISIBLE
                        currentScoreField.visibility = View.VISIBLE
                        variantsTable.visibility = View.VISIBLE
                    }
                }
            }
        }
        viewModel.currentScore.observe(viewLifecycleOwner) {
            with(binding) {
                currentScoreField.text = "Current score: $it"
                if (it >= recordScore!!) {
                    recordScore = it
                    preferences?.edit()?.putInt(RECORD_KEY, it)?.apply()
                }
                recordField.text = "Record: $recordScore"
            }
        }

        return binding.root
    }
}