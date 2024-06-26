```
Set Up Development Environment: Install JDK and IDE.
Plan Project Structure: Organize packages and files.
Design UI: Choose GUI framework, plan components.
Implement UI: Create main frame, button panel, and display panel.
Implement Logic: Handle events, link UI with logic.
Test Application: Write unit tests, perform manual testing.
Package Application: Create JAR file.
Convert to EXE: Use Launch4J.
Distribute Application: Test EXE, create installer if needed.
```
```
Navigate to the correct directory:
bash
cd /mnt/data/java_project/java/calculatorapp
```
```
Compile the Java files:
bash
javac -d out src/com/DanielVermeulen/calculator/main.java src/com/DanielVermeulen/calculator/logic/calculatorlogic.java src/com/DanielVermeulen/calculator/ui/calculatorui.java
```
```
Run the application:
bash
java -cp out com.DanielVermeulen.calculator.main
```

```
jar file
javac -d bin src/com/DanielVermeulen/calculator/ui/calculatorui.java src/com/DanielVermeulen/calculator/logic/calculatorlogic.java src/com/DanielVermeulen/calculator/main.java
jar cfe calculator.jar com.DanielVermeulen.calculator.main -C bin .
```

```
class calculatorlogic {
    - current_value: double
    - current_operator: char
    - result: double

    + constructor(): Initialize fields
    + input_number(char number): Append the number to the current input
    + input_operator(char operator): Store the operator and prepare for the next input
    + calculate(): Perform calculation based on the current operator
    + clear(): Reset all fields
}
```

```
class calculatorui extends JFrame {
    - display: JTextField
    - logic: calculatorlogic

    + constructor(): Initialize UI components and logic
    + update_display(String text): Update display with text
    + action_listener(): Handle button presses and update logic/display
}
```