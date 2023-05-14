package enjospaa.nos.model

data class QuizQuestion(
    val question: String,
    val correctAnswer: String,
    val wrongAnswers: List<String>
)
