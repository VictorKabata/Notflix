import androidx.compose.ui.window.application
import com.vickikbt.shared.di.initKoin
import ui.screens.main.MainScreen



fun main() = application {

    MainScreen(applicationScope = this)
}
