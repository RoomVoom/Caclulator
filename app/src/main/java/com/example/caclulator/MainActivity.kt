package com.example.caclulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //AC - очистка всего
        tv_clear.setOnClickListener {
            tv_result.text = ""
            tv_exp.text = ""
        }

        //Числа
        tv_one.setOnClickListener { print_in_field("1", true) }
        tv_two.setOnClickListener { print_in_field("2", true) }
        tv_three.setOnClickListener { print_in_field("3", true) }
        tv_four.setOnClickListener { print_in_field("4", true) }
        tv_five.setOnClickListener { print_in_field("5", true) }
        tv_six.setOnClickListener { print_in_field("6", true) }
        tv_seven.setOnClickListener { print_in_field("7", true) }
        tv_eight.setOnClickListener { print_in_field("8", true) }
        tv_nine.setOnClickListener { print_in_field("9", true) }
        tv_zero.setOnClickListener { print_in_field("0", true) }


        //Действия и прочие знаки
        tv_open.setOnClickListener { print_in_field("(", false) }
        tv_close.setOnClickListener { print_in_field(")", false) }
        tv_plus.setOnClickListener { print_in_field("+", false) }
        tv_minus.setOnClickListener { print_in_field("-", false) }
        tv_mul.setOnClickListener { print_in_field("*", false) }
        tv_div.setOnClickListener { print_in_field("/", false) }
        tv_dot.setOnClickListener { print_in_field(".", false) }


        //Backspace - стирание одного символа с конца
        iv_backspace.setOnClickListener {
            val stroka = tv_exp.text.toString()
            if(stroka.isNotEmpty())
            {
                tv_exp.text = stroka.substring(0, stroka.length-1)
            }
            tv_result.text = ""

        }

        //Equals - рассчет решения.
        tv_equals.setOnClickListener {
            try{
                val nado_vichislit = ExpressionBuilder(tv_exp.text.toString()).build()
                val result = nado_vichislit.evaluate()
                val result_drob = result.toLong()
                if(result == result_drob.toDouble())
                tv_result.text = result_drob.toString()
                else
                tv_result.text = result.toString()
            }
            catch(e:Exception){
                Log.d("Исключение:", " " +e.message)
            }



        }

        }
//Функция, которая отвечает за появление знаков на поле ввода.
    fun print_in_field(symbol: String, ochistka: Boolean) {

        if(tv_result.text.isNotEmpty())
        {
            tv_exp.text = ""
        }
        if(ochistka)
        {
            tv_result.text = ""
            tv_exp.append(symbol)
        }
        else
        {
            tv_exp.append(tv_result.text)
            tv_exp.append(symbol)
            tv_result.text =""
        }
    }
}