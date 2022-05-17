import androidx.compose.ui.window.application
import com.vickikbt.shared.di.initKoin
import ui.screens.main.MainScreen

val koinDi = initKoin(enableNetworkLogs = false).koin

fun main() = application {

    MainScreen(applicationScope = this)
}
