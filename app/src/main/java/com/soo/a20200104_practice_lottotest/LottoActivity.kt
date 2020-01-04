package com.soo.a20200104_practice_lottotest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_lotto.*
import org.w3c.dom.Text
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class LottoActivity : BaseActivity() {


    var totalWinMoney = 0L
    var usedMoney = 0L


    val winLottoNumArr = ArrayList<Int>()
    val winLottoNumTextViewList = ArrayList<TextView>()
    val myLottoNumTextViewList = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lotto)
        setupEvents()
        setValues()

    }


    override fun setupEvents() {

        buyOneLottoBtn.setOnClickListener {
            //            6개의 숫자를 랜덤으로 생성 -> 텍스트뷰 6개에 반영
            makeWinLottoNum()

//            몇등인지 판단하기
            checkLottoRank()

        }

    }

    fun checkLottoRank() {

//        등수 판단?
//        내가 가진 숫자 / 당첨번호 하나하나 비교, 같은숫자가 몇개인가 ? 세어야함
//        이 갯수에 따라 등수 판정
//        6개-1등 / 5개-3등 / 4개-4등 / 3개-5등

//        같은 숫자의 갯수를 세어 주는 변수
        var correctCount = 0

//        내가 가진 숫자들을 모두 꺼내보자
//        몇 개의 숫자를 맞췄는지 correctCount에 저장
        for (myNumTxt in myLottoNumTextViewList) {
//            각 텍스트뷰에 적힌 숫자가 String 형태 -> int로 변환
            val num = myNumTxt.text.toString().toInt()

            Log.d("적혀있는숫자들",num.toString())

//            당첨번호를 둘러보자
            for (winNum in winLottoNumArr) {

//                같은 숫자를 찾았다면
                if (num == winNum) {
                    correctCount ++
                    break
                }
            }
        }

//        맞춘 갯수에 따라 등수 판정

        if (correctCount == 6) {
            totalWinMoney += 50000000000
        }
        else if (correctCount == 5) {
            totalWinMoney += 1500000
        }
        else if (correctCount == 4) {
            totalWinMoney += 50000
        }
        else if (correctCount == 3) {
            totalWinMoney += 5000
        }
        else {
        }

        totalWinMoneyTxt.text = String.format("%,d 원", totalWinMoney)

//        사용금액 : 한 장 살때마다 천원씩 증가.
        usedMoney += 1000
        usedMoneyTxt.text = String.format("%,d 원", usedMoney)

    }



    fun makeWinLottoNum() {

//        기존의 당첨번호 싸그리 삭제.
//        6개의 당첨 번호 생성 => 6번 반복
//        랜덤으로 숫자 생성 -> 1~45 / 중복x
//        조건을 통과하면 -> 당첨번호 목록으로 추가 -> 배열 사용
//        작은 숫자부터 차례로 정렬
//        완료되면 6개 텍스트뷰에 반영.

        winLottoNumArr.clear()

        for (i in 0..5) {
            while (true) {
                val randomInt = Random.nextInt(45) + 1   // 0~44의 랜덤값 + 1 => 1~45

                var isDuplOk = true  // 중복검사 통과한다고 먼저 전제

                for (num in winLottoNumArr) {
                    if (randomInt == num) {

                        isDuplOk = false
                        break

                    }
                }

                if (isDuplOk) {
                    winLottoNumArr.add(randomInt)
                    break
                }


            }
        }

//        Collections 클래스의 기능을 이용해서 Arraylist 내부 값을 정렬 기능 이용.
        Collections.sort(winLottoNumArr)

//       6개의 당첨번호 / 각 자리의 텍스트뷰를 매칭하는 for문

        for (i in 0..5) {

//            상황에 맞는 텍스트뷰 / 당첨번호를 뽑아와서
            val tempTextView = winLottoNumTextViewList.get(i)
            val winNum = winLottoNumArr.get(i)

//            값으로 세팅
            tempTextView.text = winNum.toString()

        }

    }


    override fun setValues() {

        winLottoNumTextViewList.add(lottoNumTxt01)
        winLottoNumTextViewList.add(lottoNumTxt02)
        winLottoNumTextViewList.add(lottoNumTxt03)
        winLottoNumTextViewList.add(lottoNumTxt04)
        winLottoNumTextViewList.add(lottoNumTxt05)
        winLottoNumTextViewList.add(lottoNumTxt06)

        myLottoNumTextViewList.add(myNumTxt01)
        myLottoNumTextViewList.add(myNumTxt02)
        myLottoNumTextViewList.add(myNumTxt03)
        myLottoNumTextViewList.add(myNumTxt04)
        myLottoNumTextViewList.add(myNumTxt05)
        myLottoNumTextViewList.add(myNumTxt06)

    }

}
