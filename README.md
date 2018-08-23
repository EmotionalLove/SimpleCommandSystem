# SimpleCommandSystem
A library to help you make text-command systems in your programs.

# A simple how-to.

First, create a class that extends `SimpleCommand` and implements the `onCommand()` method.

```Java
public class TestCommand extends SimpleCommand {

  public SimpleCommand() {
    super("test") // insert what you want you user to type, minus the prefix.
  }
  @Override
  public void onCommand() {
      // whatever code you want to execute, put it here.
      if (this.getArguments() == null) {
        // show an error
      }
      if (this.getArguments()[0].equals("no u") /*do stuff*/;
  }
```

After that, you want to create a `SimpleCommandProcessor` in your main class, for example. You'll want to then register your commands.

```Java
public class Main {
    public static SimpleCommandProcessor COMMAND_PROCESSOR;
    public static void main(String[] args) {
      COMMAND_PROCESSOR = new SimpleCommandProcessor("/"); //whatever you want your command prefix to be, put it there.
      COMMAND_PROCESSOR.register(new TestCommand());
    }
}
```

Next, you'll want to go to wherever your user input is passed, and make a call to `.processCommand()`

```Java
public class inputHandlerClass {
// ...
  public void handleUserInput(String inputtedText) {
    if (inputtedText.startsWith(Main.COMMAND_PROCESSOR.getCommandPrefix()) {
      Main.COMMAND_PROCESSOR.processCommand(inputtedText);
      return;
    }
  }
}
```

And, it's simple as that, really.
