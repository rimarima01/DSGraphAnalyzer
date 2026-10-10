/**
 * Console submenu for the Stack component.
 * Also includes a real stack application: balanced-bracket checker.
 *
 * Owner: Ahamed (ANT.Ahamed) - Stack component
 */
public class StackMenu {

    private final ArrayStack stack;

    public StackMenu(ArrayStack stack) {
        this.stack = stack;
    }

    public void run() {
        while (true) {
            System.out.println("\n--------------- STACK OPERATIONS ---------------");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Display Stack");
            System.out.println("5. Check Balanced Brackets (stack application)");
            System.out.println("6. Return to Main Menu");
            int choice = InputHelper.readInt("Enter your choice: ", 1, 6);

            try {
                switch (choice) {
                    case 1 -> {
                        int value = InputHelper.readInt("Enter value to push: ");
                        stack.push(value);
                        System.out.println("  Pushed " + value + ".");
                        ResultLog.add("Stack", "Push " + value);
                    }
                    case 2 -> {
                        int value = stack.pop();
                        System.out.println("  Popped " + value + ".");
                        ResultLog.add("Stack", "Pop -> " + value);
                    }
                    case 3 -> {
                        int value = stack.peek();
                        System.out.println("  Top element is " + value + ".");
                        ResultLog.add("Stack", "Peek -> " + value);
                    }
                    case 4 -> stack.display();
                    case 5 -> checkBrackets();
                    case 6 -> { return; }
                }
            } catch (IllegalStateException e) {
                System.out.println("  [!] " + e.getMessage());
            }
        }
    }

    /** Uses a stack to check if (), [] and {} are balanced in an expression. */
    private void checkBrackets() {
        String expression = InputHelper.readLine("Enter an expression, e.g. {[(a+b)*c]}: ");
        ArrayStack bracketStack = new ArrayStack(Math.max(1, expression.length()));
        boolean balanced = true;

        for (char c : expression.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                bracketStack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (bracketStack.isEmpty()) {
                    balanced = false;
                    break;
                }
                char open = (char) bracketStack.pop();
                if ((c == ')' && open != '(') || (c == ']' && open != '[') || (c == '}' && open != '{')) {
                    balanced = false;
                    break;
                }
            }
        }
        if (!bracketStack.isEmpty()) {
            balanced = false;
        }
        System.out.println("  Expression is " + (balanced ? "BALANCED." : "NOT balanced."));
        ResultLog.add("Stack", "Bracket check \"" + expression + "\" -> " + (balanced ? "balanced" : "not balanced"));
    }
}
