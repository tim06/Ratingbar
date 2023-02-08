import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.github.tim06.ratingbar.sample.MainActivity
import com.github.tim06.ratingbar.sample.TestConstants.RATINGBAR_TEST_TAG
import com.github.tim06.ratingbar.sample.TestConstants.TEXT_TEST_TAG
import org.junit.Rule
import org.junit.Test

class RatingbarTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun ratingBarTest() {
        composeTestRule.onNodeWithTag(RATINGBAR_TEST_TAG).assertExists()
        composeTestRule.onNodeWithTag(TEXT_TEST_TAG).assertTextEquals("0.0")
        composeTestRule.onNodeWithTag(RATINGBAR_TEST_TAG).onChildAt(2).performClick()
        composeTestRule.onNodeWithTag(TEXT_TEST_TAG).assertTextEquals("3.0")
    }
}