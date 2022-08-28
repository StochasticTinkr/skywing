package uibuilder.impl

import com.stoachstictinkr.skywing.uibuilder.JFrameSpec
import com.stoachstictinkr.skywing.uibuilder.RootPaneSpec
import com.stoachstictinkr.skywing.uibuilder.SpecResolver
import com.stoachstictinkr.skywing.uibuilder.impl.AbstractFrameBuilder
import com.stoachstictinkr.skywing.uibuilder.impl.RootPaneBuilder
import javax.swing.JFrame
import javax.swing.WindowConstants

class JFrameBuilder : AbstractFrameBuilder<JFrame>(), JFrameSpec, RootPaneSpec by RootPaneBuilder() {
    private var defaultCloseOperation: Int = WindowConstants.HIDE_ON_CLOSE

    override fun doNothingOnClose() {
        defaultCloseOperation = WindowConstants.DO_NOTHING_ON_CLOSE
    }

    override fun hideOnClose() {
        defaultCloseOperation = WindowConstants.HIDE_ON_CLOSE
    }

    override fun disposeOnClose() {
        defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
    }

    override fun exitOnClose() {
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    }

    override fun getInstance(resolver: SpecResolver): JFrame {
        return JFrame()
    }

    override fun configure(component: JFrame, resolver: SpecResolver) {
        super.configure(component, resolver)
        component.defaultCloseOperation = defaultCloseOperation
    }

    override fun configureBeforePacking(component: JFrame, resolver: SpecResolver) {
        super.configureBeforePacking(component, resolver)
        configureRootPane(component, resolver)
    }
}