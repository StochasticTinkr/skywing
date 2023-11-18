# The Menu DSL

```kotlin
val openFileAction = action(name = "Open", icon = openIcon) {
    showOpenDialog()
}
// etc...

with(JFrame()) {
    createMenuBar {
        menu("File") {
            item(action = openFileAction)
            // etc...
        }
        addSeparator()
        menu("Edit") {
            item(action = undoAction)
            item(action = redoAction)
            addSeparator()
            item(action = deleteAction)
            // etc...
        }
    }
}
```