package com.adarsh.asjquiz

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    private lateinit var scoreboard: TextView
    private lateinit var questionNumber: TextView
    private lateinit var question: TextView
    private lateinit var optionA: RadioButton
    private lateinit var optionB: RadioButton
    private lateinit var optionC: RadioButton
    private lateinit var optionD: RadioButton
    private lateinit var submitButton: Button
    private var answer : String = ""
    private lateinit var questionBank : ArrayList<Questions>
    private var questionNo : Int = 0
    private var score : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        scoreboard = findViewById(R.id.points)
        questionNumber = findViewById(R.id.textView4)
        question = findViewById(R.id.textView5)
        optionA = findViewById(R.id.radioButton)
        optionB = findViewById(R.id.radioButton5)
        optionC = findViewById(R.id.radioButton6)
        optionD = findViewById(R.id.radioButton7)
        submitButton = findViewById(R.id.submitbutton)
        questionBank= ArrayList()
        questionNo=0
        scoreboard.text="Score : 0"
        questionSetter()
        initListeners()
        display(0)
    }
    fun clearAll(){
        optionA.isChecked = false
        optionB.isChecked = false
        optionC.isChecked = false
        optionD.isChecked = false
        optionA.setTextColor(Color.parseColor("#F3F3F3"))
        optionB.setTextColor(Color.parseColor("#F3F3F3"))
        optionC.setTextColor(Color.parseColor("#F3F3F3"))
        optionD.setTextColor(Color.parseColor("#F3F3F3"))
    }
    fun display(index : Int) {
       question.text = questionBank.get(index).question.toString()
        questionNumber.text = "Question No : "+(questionNo+1).toString()
        optionA.text = questionBank.get(index).optionA.toString()
        optionB.text = questionBank.get(index).optionB.toString()
        optionC.text = questionBank.get(index).optionC.toString()
        optionD.text = questionBank.get(index).optionD.toString()
        submitButton.text="Next"
    }
    fun isCorrect(value : String) : Boolean {
        return answer==value
    }
    fun isAnswer(value : String) {
        if (optionA.text == value) {
            optionA.setTextColor(Color.parseColor("#008000"))
        } else if (optionB.text == value) {
            optionB.setTextColor(Color.parseColor("#008000"))
        } else if (optionC.text == value) {
            optionC.setTextColor(Color.parseColor("#008000"))
        } else if (optionD.text == value) {
            optionD.setTextColor(Color.parseColor("#008000"))
        }
        submitButton.text="Next"
    }
    fun isNotAnswer(){
        if (optionA.isChecked) {
            optionA.setTextColor(Color.parseColor("#fc4903"))
        } else if (optionB.isChecked) {
            optionB.setTextColor(Color.parseColor("#fc4903"))
        } else if (optionC.isChecked) {
            optionC.setTextColor(Color.parseColor("#fc4903"))
        } else if (optionD.isChecked) {
            optionD.setTextColor(Color.parseColor("#fc4903"))
        }
        submitButton.text="Next"
    }

    fun initListeners() {
        optionA.setOnClickListener{
            clearAll()
            optionA.isChecked = true
            answer=optionA.text.toString()
            submitButton.text="Check Answer"
        }
        optionB.setOnClickListener{
            clearAll()
            optionB.isChecked = true
            answer=optionB.text.toString()
            submitButton.text="Check Answer"
        }
        optionC.setOnClickListener {
            clearAll()
            optionC.isChecked = true
            answer=optionC.text.toString()
            submitButton.text="Check Answer"
        }
        optionD.setOnClickListener {
            clearAll()
            optionD.isChecked = true
            answer=optionD.text.toString()
            submitButton.text="Check Answer"
        }
        submitButton.setOnClickListener{
            if (isCorrect(questionBank.get(questionNo).answer)&&submitButton.text=="Check Answer")
            {
                isAnswer(questionBank.get(questionNo).answer)
                score=score+10
                scoreboard.text = "Score : " + score.toString()
            }
            else if(!isCorrect(questionBank.get(questionNo).answer)&&submitButton.text=="Check Answer")
            {
                isNotAnswer()
            }
            else if(questionNo < questionBank.size-1) {
                questionNo++
                clearAll()
                display(questionNo)
            }
            else{
                var intent =Intent(this,ResultActivity::class.java)
                intent.putExtra("score",score.toString())
                startActivity(intent)
            }
        }
    }
    fun questionSetter(){
        questionBank.add(
            Questions(
                "Which of these is not a peripheral, in computer terms?",
                "Keyboard",
                "Monitor",
                "Mouse",
                "Motherboard",
                "Motherboard"
            )
        )
        questionBank.add(
            Questions(
                "What does the Internet prefix WWW stand for?",
                "Wide Width Wickets",
                "World Wide Web",
                "Worldwide Weather",
                "Western Washington World",
                "World Wide Web"
            )
        )
        questionBank.add(
            Questions(
                "Who developed Yahoo?",
                "Dennis Ritchie & Ken Thompson",
                "David Filo & Jerry Yang",
                "Vint Cerf & Robert Kahn",
                "Steve Case & Jeff Bezos",
                "David Filo & Jerry Yang"
            )
        )
        questionBank.add(
            Questions(
                "What does XML stand for?",
                "Example Markup Language",
                "Extra Modern Link",
                "X Markup Language",
                "Extensible Markup Language",
                "Extensible Markup Language"
            )
        )
        questionBank.add(
            Questions(
                "Java was introduced by among which of the following organizations?",
                "Sun Microsystems",
                "IBM",
                "Intel",
                "Microsoft",
                "Sun Microsystems"
            )
        )
    }
}

data class Questions (
    var question : String ,
    var optionA : String ,
    var optionB : String ,
    var optionC : String ,
    var optionD : String ,
    var answer : String
    )
