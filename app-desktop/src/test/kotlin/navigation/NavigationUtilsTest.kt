package navigation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import ui.navigation.NavigationItem
import utils.getNavArguments

class NavigationUtilsTest {

    @Test
    fun `navigation with arguments`() {
        val input = "details/20"

        assertEquals(expected = "20", actual = input.getNavArguments())
    }

    @Test
    fun `check route match route`() {
        val input = "details/20"

        val routeIntro=NavigationItem.Details.route.split("/")[0]
        val some = input.contains(routeIntro)

        assertTrue(some)
    }

}
