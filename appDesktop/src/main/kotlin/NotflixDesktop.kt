import androidx.compose.ui.window.application
import com.vickikbt.shared.di.initKoin
import di.desktopModule
import org.koin.core.Koin
import ui.screens.main.MainScreen

lateinit var koin: Koin

fun main() {
    koin = initKoin(enableNetworkLogs = true) { modules(desktopModule) }.koin

    return application {
        MainScreen(applicationScope = this)
    }
}
