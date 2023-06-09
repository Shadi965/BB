package enjospaa.nos.model

class QuestionsManager {
    private var questions = mutableListOf<QuizQuestion>()
    var questionList: List<QuizQuestion>

    init {
        questions = mutableListOf(
            QuizQuestion( "After how many Year’s FIFA World Cup held?",
                "4 Years",
                arrayListOf("2 Years", "3 Years", "Every Year") ),
            QuizQuestion( "Which Country won the first FIFA World Cup?",
                "Uruguay",
                arrayListOf("Argentina", "Italy", "Brazil") ),
            QuizQuestion(   "Who won the first ICC World Cup?",
                "West Indies",
                arrayListOf("India", "England", "Australia") ),
            QuizQuestion( "Who won the first T20 World Cup?",
                "India",
                arrayListOf("Pakistan", "Sri Lanka", "Sri Lanka") ),
            QuizQuestion( "Who is known as the Flying Sikh?",
                "Milkha Sing",
                arrayListOf("Michael Johnson", "Usain Bolt", "Carl Lewis") ),
            QuizQuestion( "Who has the Highest Number of Gold Medals in Olympic History?",
                "Michael Phelps",
                arrayListOf("Larisa Latynina", "Mark Spitz", "Saina Nehwal") ),
            QuizQuestion( "What is the 100m World Record of Usain Bolt?",
                "10.12 Sec",
                arrayListOf("14.35 Sec", "9.05 Sec", "10.12 Sec") ),
            QuizQuestion( "What is the Women’s World Record for the 100-Meter Dash?",
                "10.49 Sec",
                arrayListOf(" 12.35 Sec", "10.45 Sec", "9.55 Sec") ),
            QuizQuestion( "Where is the Famous Boxer Mary Kom from?",
                "Manipur",
                arrayListOf("Mizoram", "Nagaland", "Tripura") ),
            QuizQuestion( "How many FIFA World Cup has been played till 2022?",
                "22",
                arrayListOf("29", "52", "14") ),
            QuizQuestion( "How many times has India won the Men’s Hockey World Cup in the Olympics?",
                "1",
                arrayListOf("3", "2", "0") ),
            QuizQuestion( "Which Female has the Most Olympic Gold Medals in Olympic History?",
                "Larisa Latynina",
                arrayListOf("Birgit Fischer", "Marit Bjørgen", "Jenny Thompson") ),
            QuizQuestion( "Who is known as “The Baltimore Bullet”?",
                "Michael Phelps",
                arrayListOf("Roger Federer", "Usain Bolt", "Michael Jordan") ),
            QuizQuestion( "Who is the first Indian Badminton Player to win an Olympic Medal?",
                "Saina Nehwal",
                arrayListOf("Srikanth Kidambi", "Srikanth Kidambi", "P.V. Nehwal") ),
            QuizQuestion( "Which Sport is Performed by the Legend “Muhammad Ali”?",
                "Boxing",
                arrayListOf("Weight Lifting", "Swiming", "Shooting") ),
            QuizQuestion( "When was the first FIFA World Cup held?",
                "1930",
                arrayListOf("1925", "1934", "1818") ),
            QuizQuestion( "When did Cristiano Ronaldo join Juventus Football Club?",
                "2018",
                arrayListOf("2017", "2019", "2016") ),
            QuizQuestion( "Which Country has Won the Most World Snooker Championships?",
                "England",
                arrayListOf("Wales", "Scotland", "Australia") ),
            QuizQuestion( "Who holds the World Record for Solving Rubik’s Cube at 3.47 Seconds?",
                "Yusheng Du",
                arrayListOf("Feliks Zemdegs", "Patrick Ponce", "Patrick Ponce") ),
            QuizQuestion( "Who won the FIFA World Cup in 2018?",
                "France",
                arrayListOf("Germany", "Portugal", "Uraguay") ),
            QuizQuestion( "Who is the Undisputed World Chess Champion from 2007–2013?",
                "Viswanathan Anand",
                arrayListOf("Viswanathan Anand", "Viswanathan Anand", "Veselin Topalov") ),
            QuizQuestion( "Where is Magnus Carlsen from?",
                "Norway",
                arrayListOf("England", "UK", "Germany") ),
            QuizQuestion( "Which is the Largest Football Stadium in the World?",
                "Rungrado 1st of May Stadium",
                arrayListOf("Salt Lake Stadium", "AT&T Stadium", "Melbourne Cricket Ground") ),
            QuizQuestion( "What are the National Sports of China?",
                "Table Tennis",
                arrayListOf("Baseball", "Cricket", "Swimming") ),
            QuizQuestion( "What is the National Game of the USA?",
                "Baseball",
                arrayListOf("Tennis", "Soccer", "Basket Ball") ),
            QuizQuestion( "What is the World Record of Clean & Jerk Men’s 105 Kg Weightlifting?",
                "237 Kg",
                arrayListOf("224 Kg", "206 Kg", "436 Kg") ),
            QuizQuestion( "Who is given the Nickname “God of Cricket”?",
                "Sachin Tendulkar",
                arrayListOf("Ricky Ponting", "MS Dhoni", "Anil Kumble") ),
            QuizQuestion( "Who has won 23 Grand Slam Singles Titles in Women’s Tennis?",
                "Serena Williams",
                arrayListOf("Sania Mirza", "Ashleigh Barty", "Venus Williams") ),
            QuizQuestion( "Who has the Highest Number of Medals in the Olympic Shooting event?",
                "Carl Osburn",
                arrayListOf("Willis A. Lee", "Willis A. Lee", "Alfred Lane") ),
            QuizQuestion( "Which Country won the most FIFA World Cups?",
                "Brazil",
                arrayListOf("Germany", "Argentina", "France") ),
            QuizQuestion( "Who won the Most Grand Prix Motorcycle Racing World Championship?",
                "Giacomo Agostini",
                arrayListOf("Ángel Nieto", "Mike Hailwood", "Carlo Ubbiali") ),
            QuizQuestion( "What is the National Game of India?",
                "Hockey",
                arrayListOf("Football", "Cricket", "Kabaddi") ),
            QuizQuestion( "Which Sport is Ronnie O’Sullivan famous for?",
                "Snooker",
                arrayListOf("Chess", "Tennis", "Poker") ),
            QuizQuestion( "What is the Full Name of the Famous Football Player Ronaldo?",
                "Cristiano Ronaldo dos Santos Aveiro",
                arrayListOf("Cristiano dos Ronaldo", "Cristiano Santos Ronaldo", "Cristiano Santos Ronaldo") ),
            QuizQuestion( "Who is the Only Person to Win 6 Olympic Gold Medals in Archery?",
                "Hubert Van Innis",
                arrayListOf("Hubert Van Innis", "Kim Soo-Nyung", "Park Kyung-mo") ),
            QuizQuestion( "Which Sport is Mark Spitz popularly known for?",
                "Swimming",
                arrayListOf("Cycling", "Snooker", "Golf") ),
            QuizQuestion( "Which Sport is Valentino Rossi Known for?",
                "Motorcycle Racing",
                arrayListOf("Swiming", "Cycling", "Cycling") ),
            QuizQuestion( "What is the current World Record for Men’s High Jump in the Olympic Event?",
                "2.45 m",
                arrayListOf("5.45 m", "4.45 m", "4.45 m") ),
            QuizQuestion( "When was Virat Kohli Selected as the Captain of the Indian Cricket Team (ODI)?",
                "2013",
                arrayListOf("2019", "2017", "2014") ),
            QuizQuestion( "Which Sport does Roger Federer play?",
                "Tennis",
                arrayListOf("Volleyball", "Table Tennis", "Table Tennis") ),
            QuizQuestion( "When did Michael Jordan retire?",
                "2003",
                arrayListOf("2004", "2005", "2013") ),
            QuizQuestion( "What is the National Game of Japan?",
                "Sumo",
                arrayListOf("Boat Racing", "Wrestling", "Sipa") ),
            QuizQuestion( "What is the National Game of Spain?",
                "Football",
                arrayListOf("Wrestling", "Basket Ball", "Baseball") ),
            QuizQuestion( "What is the National Sports of Brazil?",
                "Capoeira",
                arrayListOf("Football", "Pato", "Oina") ),
            QuizQuestion( "What is the National Game of New Zealand?",
                "Rugby Union",
                arrayListOf("Archery", "Cricket", "Golf") ),
            QuizQuestion( "Which Team did Kobe Bryant play for?",
                "Los Angeles Lakers",
                arrayListOf("Chicago Bulls", "Golden State Warriors", "Miami Heat") ),
            QuizQuestion( "What is the Standard Size of a Football Pitch?",
                "105 x 45 m",
                arrayListOf("105 x 45 m", "105 x 45 m", "105 x 45 m") ),
            QuizQuestion( "When did India win its first Olympic Gold Medal?",
                "1928",
                arrayListOf("1948", "1952", "1964") ),
            QuizQuestion( "What is the National Sports of Canada?",
                "Ice hockey",
                arrayListOf("Cricket", "Field Hockey", "Football") ),
            QuizQuestion( "Which Country has Volleyball as its National Sport?",
                "Nepal",
                arrayListOf("Brazil", "Cuba", "Philippines") ),
        )
        questionList = questions.shuffled()
    }

    fun shuffleQuestions() {
        questionList = questions.shuffled()
    }

    fun getVariants(index: Int) : List<String> {
        val variants: MutableList<String> = questionList[index].wrongAnswers.toMutableList()
        variants.add(questionList[index].correctAnswer)
        return variants.shuffled()
    }

    fun checkAnswer(index: Int, answer: String) : Boolean {
        return answer == questionList[index].correctAnswer
    }

}