import androidx.compose.ui.window.application
import com.vickikbt.shared.di.initKoin
import org.koin.core.Koin
import ui.screens.main.MainScreen

lateinit var koin: Koin

fun main() {

    koin = initKoin().koin

    return application {
        MainScreen(applicationScope = this)
    }
}
