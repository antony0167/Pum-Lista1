package com.example.lista1

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.lista1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var question = -1
    private var points = 0
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    data class Question(val question: String, val answers: List<String>, val correctAnswer: String)

    val quiz = listOf(
        Question(
            "Który z poniższych jest największą planetą Układu Słonecznego?",
            listOf("Mars", "Wenus", "Jowisz", "Saturn"),
            correctAnswer = "Jowisz"
        ),
        Question(
            "Kto napisał 'Pana Tadeusza'?",
            listOf("Adam Mickiewicz", "Juliusz Słowacki", "Henryk Sienkiewicz", "Bolesław Prus"),
            correctAnswer = "Adam Mickiewicz"
        ),
        Question(
            "W którym roku rozpoczęła się II wojna światowa?",
            listOf("1939", "1914", "1945", "1920"),
            correctAnswer = "1939"
        ),
        Question(
            "Jak nazywa się najwyższa góra świata?",
            listOf("Kilimandżaro", "Mont Blanc", "Mount Everest", "K2"),
            correctAnswer = "Mount Everest"
        ),
        Question(
            "Jak nazywa się stolica Włoch?",
            listOf("Madryt", "Berlin", "Paryż", "Rzym"),
            correctAnswer = "Rzym"
        ),
        Question(
            "Który pierwiastek chemiczny ma symbol O?",
            listOf("Wodór", "Tlen", "Azot", "Hel"),
            correctAnswer = "Tlen"
        ),
        Question(
            "Jakie jest największe jezioro w Polsce?",
            listOf("Jezioro Śniardwy", "Jezioro Hańcza", "Jezioro Mamry", "Jezioro Gopło"),
            correctAnswer = "Jezioro Śniardwy"
        ),
        Question(
            "Kto był pierwszym człowiekiem w kosmosie?",
            listOf("Neil Armstrong", "Jurij Gagarin", "Buzz Aldrin", "Valentina Tereshkova"),
            correctAnswer = "Jurij Gagarin"
        ),
        Question(
            "Jak nazywa się najdłuższa rzeka w Polsce?",
            listOf("Odra", "Wisła", "Bug", "Warta"),
            correctAnswer = "Wisła"
        ),
        Question(
            "Który kontynent jest największy pod względem powierzchni?",
            listOf("Afryka", "Ameryka Północna", "Azja", "Australia"),
            correctAnswer = "Azja"
        )
    )



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("question_number", question)
        outState.putInt("user_points", points)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        if (savedInstanceState != null) {
            question = savedInstanceState.getInt("question_number")
            points = savedInstanceState.getInt("user_points")
        }

        binding.nextButton.setOnClickListener {
            if (binding.radioGroup.checkedRadioButtonId != -1) {
                val selectedOptionId = binding.radioGroup.checkedRadioButtonId
                val selectedRadioButton = findViewById<RadioButton>(selectedOptionId)
                val selectedAnswer = selectedRadioButton.text

                if (selectedAnswer == quiz[question].correctAnswer) {
                    points += 10
                }
            }

            binding.radioGroup.clearCheck()

            question++

            if (question < 10) {
                binding.nextButton.text = "Następne pytanie"
                binding.radioGroup.visibility = View.VISIBLE
                binding.progressBar.visibility = View.VISIBLE
                binding.text2.visibility = View.VISIBLE
                binding.text1.text = "Pytanie ${question + 1}/10"
                binding.text2.text = quiz[question].question
                binding.progressBar.progress = (question + 1) * 10
                binding.radioButton.text = quiz[question].answers[0]
                binding.radioButton2.text = quiz[question].answers[1]
                binding.radioButton3.text = quiz[question].answers[2]
                binding.radioButton4.text = quiz[question].answers[3]

            } else {
                binding.radioGroup.visibility = View.INVISIBLE
                binding.progressBar.progress = 100

                if (points > 50) {
                    binding.text1.text = "Gratulacje!!!"
                } else {
                    binding.text1.text = "Hmmmmmm..."
                }

                binding.text2.text = "Twój wynik to ${points}/100 punktów"

                question = -1
                points = 0
                binding.nextButton.text = "Spróbuj jeszcze raz"
            }
        }
    }
}