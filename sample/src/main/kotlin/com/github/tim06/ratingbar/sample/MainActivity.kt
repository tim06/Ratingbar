package com.github.tim06.ratingbar.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.github.tim06.ratingbar.Ratingbar
import com.github.tim06.ratingbar.sample.TestConstants.RATINGBAR_TEST_TAG
import com.github.tim06.ratingbar.sample.TestConstants.TEXT_TEST_TAG

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentRating by remember { mutableStateOf(0.0) }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .testTag(TEXT_TEST_TAG),
                        text = currentRating.toString()
                    )
                    Ratingbar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .testTag(RATINGBAR_TEST_TAG),
                        count = 5,
                        currentRating = currentRating
                    ) {
                        currentRating = it
                    }
                }
            }
        }
    }
}